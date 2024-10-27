package wx82.agrotech.groweasyapi.history;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ValueTransactionHistoryRepository extends JpaRepository<ValueTransactionHistory, Integer> {

    @Query("""
            SELECT valueHistory
            FROM ValueTransactionHistory  valueHistory
            WHERE valueHistory.device.id = :valueDeviceId
    """)
    List<ValueTransactionHistory> findAllByTransactionId(@Param("valueDeviceId") Integer valueDeviceId);
}
