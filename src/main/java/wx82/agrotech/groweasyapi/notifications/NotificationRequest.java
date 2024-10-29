package wx82.agrotech.groweasyapi.notifications;

public record NotificationRequest(
        String email,
        String message,
        Double threshold,
        Integer time,
        Integer idDevice
) {
}
