package wx82.agrotech.groweasyapi.history;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
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


@Slf4j
@Service
@RequiredArgsConstructor
public class ValueTransactionHistoryService {

    private final ValueTransactionHistoryRepository valueRepository;
    private final ValueTransactionHistoryMapper valueTransactionHistoryMapper;

    public Integer save(ValueTransactionHistoryRequest request) {
        ValueTransactionHistory valueHistory = valueTransactionHistoryMapper.toValueTransactionHistory(request);
        // value request
        return valueRepository.save(valueHistory).getId();
    }

    public List<ValueTransactionHistoryResponse> findAllValuesHistory(Integer idDevices){
        List<ValueTransactionHistory> devices = valueRepository.findAllByTransactionId(idDevices);
        return devices.stream()
                .map(valueTransactionHistoryMapper::toValueTransactionHistoryResponse)
                .toList();
    }

    public Double getLastValueSensor(Integer idDevice){
        return valueRepository.findLastTemperatureByUserId(idDevice)
                .orElseThrow(() -> new RuntimeException("Sensor not found or Sensor not value"));
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



}
