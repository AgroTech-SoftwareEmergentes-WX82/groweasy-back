package wx82.agrotech.groweasyapi.sensor;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;
import wx82.agrotech.groweasyapi.user.User;

import java.time.LocalDateTime;

@Getter
@Setter
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "LATEST_SENSOR_VALUE")
public class LatestSensorValue {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private Integer userId;
    private Double temperature;
    private Double humidity;
    private Double luminosity;

    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

}
