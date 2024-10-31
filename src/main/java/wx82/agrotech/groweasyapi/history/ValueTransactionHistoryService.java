package wx82.agrotech.groweasyapi.history;

import jakarta.mail.MessagingException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import wx82.agrotech.groweasyapi.email.EmailTemplateName;
import wx82.agrotech.groweasyapi.notifications.NotificationResponse;
import wx82.agrotech.groweasyapi.notifications.NotificationService;
import wx82.agrotech.groweasyapi.notifications.email.EmailAlertService;
import wx82.agrotech.groweasyapi.notifications.email.EmailTemplateAlert;
import wx82.agrotech.groweasyapi.statistics.StaticsSensor;
import wx82.agrotech.groweasyapi.user.User;
import wx82.agrotech.groweasyapi.util.DateTimeUtil;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@Slf4j
@Service
@RequiredArgsConstructor
public class ValueTransactionHistoryService {

    private final ValueTransactionHistoryRepository valueRepository;
    private final ValueTransactionHistoryMapper valueTransactionHistoryMapper;

    private final EmailAlertService emailAlertService;
    private final NotificationService notificationService;

    @Value("${application.mailing.frontend.webControlDeviceUrl}")
    private String webControlDeviceUrl;

    public Integer save(ValueTransactionHistoryRequest request, Authentication connectedUser) throws MessagingException {
        User user = ((User) connectedUser.getPrincipal());
        ValueTransactionHistory valueHistory = valueTransactionHistoryMapper.toValueTransactionHistory(request);
        NotificationResponse notificationResponse = notificationService.findNotificationByIdUserAndIdDevice(request.deviceId(), user.getId());
        log.info("Notification Response: " + notificationResponse.toString());
        if(request.value() > notificationResponse.getThreshold()){
            sendAlertEmail(notificationResponse, user, request);
        }
        return valueRepository.save(valueHistory).getId();
    }

    public List<ValueTransactionHistoryResponse> findAllValuesHistory(Integer idDevices){
        List<ValueTransactionHistory> devices = valueRepository.findAllByTransactionId(idDevices);
        return devices.stream()
                .map(valueTransactionHistoryMapper::toValueTransactionHistoryResponse)
                .toList();
    }

    public ValueTransactionHistoryResponse getLastValueSensor(Integer idDevice) {
        ValueTransactionHistory valueSensor = valueRepository.findLastTemperatureByUserId(idDevice);
        if (valueSensor == null) {
            throw new RuntimeException("Sensor not found or Sensor has no value");
        }
        return valueTransactionHistoryMapper.toValueTransactionHistoryResponse(valueSensor);
    }

    public List<StaticsSensor> getValueSensorStatistics(String startDateString, String endDateString, Long deviceId) {
        String pattern = "yyyy-MM-dd HH:mm:ss"; // Define tu patrón aquí
        LocalDateTime startDate;
        LocalDateTime endDate;

        try {
            startDate = DateTimeUtil.convertStringToLocalDateTime(startDateString, pattern);
            endDate = DateTimeUtil.convertStringToLocalDateTime(endDateString, pattern);
        } catch (DateTimeParseException e) {
            throw new IllegalArgumentException("Invalid date format. Please use: " + pattern, e);
        }

        List<Object[]> rawResults;
        try {
            rawResults = valueRepository.findStatisticsByDeviceIdAndDateRangeRaw(startDate, endDate, deviceId);
        } catch (Exception e) {
            throw new RuntimeException("Error fetching statistics from the database", e);
        }

        List<StaticsSensor> statistics = new ArrayList<>();
        for (Object[] result : rawResults) {
            try {
                // Convertir el Timestamp a LocalDateTime
                LocalDateTime time = ((Timestamp) result[0]).toLocalDateTime();
                Double avgValue = (Double) result[1];
                Double minValue = (Double) result[2];
                Double maxValue = (Double) result[3];

                BigDecimal roundedAvgValue = BigDecimal.valueOf(avgValue).setScale(2, RoundingMode.HALF_UP);

                StaticsSensor staticsSensor = new StaticsSensor(time, roundedAvgValue.doubleValue(), minValue, maxValue);
                statistics.add(staticsSensor);
            } catch (ClassCastException e) {
                throw new RuntimeException("Error casting result to expected types", e);
            }
        }

        return statistics;
    }

    private void sendAlertEmail(NotificationResponse notificationResponse, User user, ValueTransactionHistoryRequest request) throws MessagingException {
        emailAlertService.sendEmail(
                notificationResponse.getEmail(),
                user.fullName(),
                notificationResponse.getNameDevice(),
                request.value(),
                request.unitOfMeasure(),
                notificationResponse.getMessage(),
                EmailTemplateAlert.ACTIVATE_ALERT,
                webControlDeviceUrl,
                "Alerta de Dispositivo"
        );
    }



}
