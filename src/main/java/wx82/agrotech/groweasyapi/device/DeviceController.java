package wx82.agrotech.groweasyapi.device;

import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import wx82.agrotech.groweasyapi.common.PageResponse;
import wx82.agrotech.groweasyapi.user.User;

import java.util.List;

@RestController
@RequestMapping("devices")
@RequiredArgsConstructor
@Tag(name = "Device")
public class DeviceController {

    private final DeviceService service;

    @PostMapping
    public ResponseEntity<Integer> saveDevice(
            @Valid @RequestBody DeviceRequest request,
            Authentication connectedUser
    ){
        return ResponseEntity.ok(service.save(request, connectedUser));
    }

    @GetMapping
    public ResponseEntity<List<DeviceResponse>> findAllDevices(
            Authentication connectedUser
    ) {
        return ResponseEntity.ok(service.findAllDevices(connectedUser));
    }

    @DeleteMapping("/{deviceId}")
    public ResponseEntity<Void> deleteDevice(
            @PathVariable Integer deviceId,
            Authentication connectedUser
    ) {
        service.delete(deviceId, connectedUser);
        return ResponseEntity.noContent().build(); // Retorna 204 No Content
    }

}
