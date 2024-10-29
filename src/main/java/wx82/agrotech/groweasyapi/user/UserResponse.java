package wx82.agrotech.groweasyapi.user;

import jakarta.persistence.Column;
import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserResponse {

    private Integer id;
    private String firstname;
    private String lastname;
    private LocalDate dateOfBirth;
    private String email;

}
