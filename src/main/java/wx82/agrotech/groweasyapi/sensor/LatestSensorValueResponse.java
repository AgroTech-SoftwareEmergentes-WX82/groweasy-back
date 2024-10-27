package wx82.agrotech.groweasyapi.sensor;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class LatestSensorValueResponse {
    private Integer id;
    private Double temperature;
    private Double humidity;
    private Double luminosity;
    private LocalDateTime updatedAt;
}