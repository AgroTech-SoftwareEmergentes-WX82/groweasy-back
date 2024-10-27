package wx82.agrotech.groweasyapi.statistics;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class StaticsSensor {

    private LocalDateTime time;
    private Double avgValue;
    private Double minValue;
    private Double maxValue;
}
