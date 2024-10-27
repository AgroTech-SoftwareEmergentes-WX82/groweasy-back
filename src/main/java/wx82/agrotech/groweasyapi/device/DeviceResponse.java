package wx82.agrotech.groweasyapi.device;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class DeviceResponse {
    private Integer id;
    private String name;
    private String typeDevice;
    private String topic;
}
