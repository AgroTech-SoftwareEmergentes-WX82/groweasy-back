package wx82.agrotech.groweasyapi.history;

import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.mail.MessagingException;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.apache.coyote.Response;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import wx82.agrotech.groweasyapi.device.DeviceRequest;
import wx82.agrotech.groweasyapi.statistics.StaticsSensor;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("values")
@RequiredArgsConstructor
@Tag(name = "Value")
public class ValueTransactionController {

    private final ValueTransactionHistoryService service;

    @PostMapping
    public ResponseEntity<Integer> saveValue(
            @Valid @RequestBody ValueTransactionHistoryRequest request,
            Authentication connectedUser
    ) throws MessagingException {
        return ResponseEntity.ok(service.save(request, connectedUser));
    }

    @GetMapping("/device/{device-id}")
    public ResponseEntity<List<ValueTransactionHistoryResponse>> getAllValues(
            @PathVariable("device-id") Integer deviceId
    ){
        return ResponseEntity.ok(service.findAllValuesHistory(deviceId));
    }

    @GetMapping("/lastValueSensor/{device-id}")
    public ResponseEntity<ValueTransactionHistoryResponse> getLastValueSensor(
            @PathVariable("device-id") Integer deviceId) {
        return ResponseEntity.ok(service.getLastValueSensor(deviceId));
    }

    @GetMapping("/sensor-statistics")
    public ResponseEntity<List<StaticsSensor>> getValueStatistics(
            @RequestParam("startDate") String startDate,
            @RequestParam("endDate") String endDate,
            @RequestParam("deviceId") Long deviceId) {
        List<StaticsSensor> statistics = service.getValueSensorStatistics(startDate, endDate, deviceId);
        return ResponseEntity.ok(statistics);
    }


}
