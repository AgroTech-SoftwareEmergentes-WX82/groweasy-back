package wx82.agrotech.groweasyapi.history;

import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.apache.coyote.Response;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import wx82.agrotech.groweasyapi.device.DeviceRequest;

import java.util.List;

@RestController
@RequestMapping("values")
@RequiredArgsConstructor
@Tag(name = "Value")
public class ValueTransactionController {

    private final ValueTransactionHistoryService service;

    @PostMapping
    public ResponseEntity<Integer> saveValue(
            @Valid @RequestBody ValueTransactionHistoryRequest request
    ){
        return ResponseEntity.ok(service.save(request));
    }

    @GetMapping("/device/{device-id}")
    public ResponseEntity<List<ValueTransactionHistoryResponse>> getAllValues(
            @PathVariable("device-id") Integer deviceId
    ){
        return ResponseEntity.ok(service.findAllValuesHistory(deviceId));
    }

}
