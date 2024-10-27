package wx82.agrotech.groweasyapi.device;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;
import wx82.agrotech.groweasyapi.common.BaseEntity;
import wx82.agrotech.groweasyapi.history.ValueTransactionHistory;
import wx82.agrotech.groweasyapi.user.User;

import java.util.List;

@Getter
@Setter
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Device extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;
    private String typeDevice;
    private String topic;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @OneToMany(mappedBy = "device")
    private List<ValueTransactionHistory> value;
}
