package wx82.agrotech.groweasyapi.notifications;


import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import wx82.agrotech.groweasyapi.device.Device;
import wx82.agrotech.groweasyapi.exception.ResourceNotFoundException;
import wx82.agrotech.groweasyapi.user.User;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
@Transactional
public class NotificationService {

    private final NotificationRepository notificationRepository;
    private final NotificationMapper notificationMapper;

    public Integer save(NotificationRequest request, Authentication connectedUser){
        User user = ((User) connectedUser.getPrincipal());
        Notification notification = notificationMapper.toNotification(request);
        notification.setUser(user);
        return notificationRepository.save(notification).getId();
    }

    public List<NotificationResponse> findAllNotificationsByIdUser(Authentication connectedUser){
        User user = ((User) connectedUser.getPrincipal());
        List<Notification> notifications = notificationRepository.findByUser(user.getId());
        return notifications.stream()
                .map(notificationMapper::toNotificationResponse)
                .toList();
    }

    public NotificationResponse findNotificationByIdUserAndIdDevice(int idDevice, int idUser){
        Optional<Notification> notification = notificationRepository.findByIdUserAndIdDevice(idDevice,idUser);
        return notification.map(notificationMapper::toNotificationResponse).orElse(null);
    }

    public void deleteNotification(int idNotification) {
        if(!notificationRepository.existsById(idNotification)){
            throw new ResourceNotFoundException("Notification with id " + idNotification + " not found");
        }
        notificationRepository.deleteById(idNotification);
    }

    public NotificationResponse updateNotification(int idNotification, NotificationRequest request) {
        Notification notification = notificationRepository.findById(idNotification)
                .orElseThrow(() -> new ResourceNotFoundException("Notification with ID " + idNotification + " not found."));

        notification.setEmail(request.email());
        notification.setMessage(request.message());
        notification.setThreshold(request.threshold());
        notification.setTime(request.time());
        notification.setDevice(Device.builder().id(request.idDevice()).build());
        return notificationMapper.toNotificationResponse(notificationRepository.save(notification));
    }



}
