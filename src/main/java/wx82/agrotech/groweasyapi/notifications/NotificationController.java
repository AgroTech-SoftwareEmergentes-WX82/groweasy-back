package wx82.agrotech.groweasyapi.notifications;

import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import wx82.agrotech.groweasyapi.exception.ResourceNotFoundException;

import java.util.List;

@RestController
@RequestMapping("notification")
@RequiredArgsConstructor
@Tag(name = "Notification")
public class NotificationController {

    private final NotificationService service;

    @PostMapping
    public ResponseEntity<Integer> saveNotification(
            @Valid @RequestBody NotificationRequest request,
            Authentication connectedUser) {
        int notificationId = service.save(request, connectedUser);
        return ResponseEntity.status(HttpStatus.CREATED).body(notificationId);
    }

    @GetMapping("/user/getNotifications")
    public ResponseEntity<List<NotificationResponse>> getNotification(
            Authentication connectedUser){
        return ResponseEntity.ok(service.findAllNotificationsByIdUser(connectedUser));
    }

    @PutMapping("/update/{idNotification}")
    public ResponseEntity<NotificationResponse> updateNotification(
            @PathVariable int idNotification,
            @RequestBody NotificationRequest request) {
        NotificationResponse updatedNotification = service.updateNotification(idNotification, request);
        return ResponseEntity.ok(updatedNotification);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteNotification(@PathVariable int id) {
        service.deleteNotification(id);
        return ResponseEntity.noContent().build();
    }



}
