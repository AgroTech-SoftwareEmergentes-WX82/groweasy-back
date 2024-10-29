package wx82.agrotech.groweasyapi.notifications;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class NotificationResponse {
    private Integer id;
    private String email;
    private String message;
    private Double threshold;
    private Integer time;
    private String nameDevice;
}
