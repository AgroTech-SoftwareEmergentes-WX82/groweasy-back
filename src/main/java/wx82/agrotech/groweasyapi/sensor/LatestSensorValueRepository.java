package wx82.agrotech.groweasyapi.sensor;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface LatestSensorValueRepository extends JpaRepository<LatestSensorValue, Integer> {

    Optional<LatestSensorValue> findByUserId(Integer userId);

}
