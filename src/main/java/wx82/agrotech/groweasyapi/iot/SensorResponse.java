package wx82.agrotech.groweasyapi.iot;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SensorResponse {

    private Double temperature;
    private Double humidity;
    private Double luminosity;
}
