package wx82.agrotech.groweasyapi.notifications;

import org.springframework.stereotype.Service;
import wx82.agrotech.groweasyapi.device.Device;

@Service
public class NotificationMapper {

    public Notification toNotification(NotificationRequest request){
        return Notification.builder()
                .email(request.email())
                .message(request.message())
                .threshold(request.threshold())
                .time(request.time())
                .device(Device.builder()
                        .id(request.idDevice())
                        .build()
                )
                .build();
    }

    public NotificationResponse toNotificationResponse(Notification notification){
        return NotificationResponse.builder()
                .id(notification.getId())
                .email(notification.getEmail())
                .message(notification.getMessage())
                .threshold(notification.getThreshold())
                .time(notification.getTime())
                .nameDevice(notification.getDevice().getName())
                .build();
    }




}
