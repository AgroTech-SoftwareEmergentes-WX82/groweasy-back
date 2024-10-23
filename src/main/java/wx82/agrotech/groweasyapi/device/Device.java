package wx82.agrotech.groweasyapi.device;

import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;
import wx82.agrotech.groweasyapi.common.BaseEntity;

@Getter
@Setter
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Device extends BaseEntity {

    private String name;
    private String typeDevice;
    private String topic;
}
