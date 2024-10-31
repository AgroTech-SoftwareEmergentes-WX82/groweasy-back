package wx82.agrotech.groweasyapi.history;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import wx82.agrotech.groweasyapi.statistics.StaticsSensor;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface ValueTransactionHistoryRepository extends JpaRepository<ValueTransactionHistory, Integer> {

    @Query("""
            SELECT valueHistory
            FROM ValueTransactionHistory  valueHistory
            WHERE valueHistory.device.id = :valueDeviceId
    """)
    List<ValueTransactionHistory> findAllByTransactionId(@Param("valueDeviceId") Integer valueDeviceId);

    @Query(value = "SELECT * FROM VALUES v WHERE DEVICE_ID = :idDevice ORDER BY v.CREATED_DATE DESC LIMIT 1", nativeQuery = true)
    ValueTransactionHistory findLastTemperatureByUserId(@Param("idDevice") Integer idDevice);

    @Query(value = """
        SELECT 
            DATE_TRUNC('minute', created_date) AS time,
            AVG(value) AS avg_value,
            MIN(value) AS min_value,
            MAX(value) AS max_value
        FROM 
            values
        WHERE 
            created_date BETWEEN :startDate AND :endDate AND device_id = :deviceId
        GROUP BY 
            time
        ORDER BY 
            time
        """, nativeQuery = true)
    List<Object[]> findStatisticsByDeviceIdAndDateRangeRaw(
            @Param("startDate") LocalDateTime startDate,
            @Param("endDate") LocalDateTime endDate,
            @Param("deviceId") Long deviceId);

}
