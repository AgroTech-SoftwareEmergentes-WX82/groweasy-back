package wx82.agrotech.groweasyapi.iot;

import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import wx82.agrotech.groweasyapi.device.DeviceResponse;
import wx82.agrotech.groweasyapi.history.ValueTransactionHistoryRequest;

import java.util.List;
/*
@RestController
@RequestMapping("sensors")
@RequiredArgsConstructor
@Tag(name = "Sensors")
public class SensorController {

    private final SensorService service;

    @PostMapping
    public ResponseEntity<Integer> saveValue(
            @Valid @RequestBody SensorRequest request
    ){
        return ResponseEntity.ok(service.save(request));
    }

    @GetMapping("/temperature/last")
    public ResponseEntity<Double> getLastTemperature(Authentication connectedUser) {
        return ResponseEntity.ok(service.getLastTemperature(connectedUser));
    }

    @GetMapping("/humidity/last")
    public ResponseEntity<Double> getLastHumidity(Authentication connectedUser) {
        return ResponseEntity.ok(service.getLastHumidity(connectedUser));
    }

    @GetMapping("/luminosity/last")
    public ResponseEntity<Double> getLastLuminosity(Authentication connectedUser) {
        return ResponseEntity.ok(service.getLastLuminosity(connectedUser));
    }

}*/
