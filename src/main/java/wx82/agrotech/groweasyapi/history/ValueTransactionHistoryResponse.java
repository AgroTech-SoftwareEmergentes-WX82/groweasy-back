package wx82.agrotech.groweasyapi.history;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ValueTransactionHistoryResponse {
    private Integer id;
    private Double value;
    private String unitOfMeasure;
    private LocalDateTime createdAt;
}
