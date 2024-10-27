package wx82.agrotech.groweasyapi.iot;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface SensorRepository extends JpaRepository<Sensor, Integer> {

    @Query(value = "SELECT s.TEMPERATURE FROM SENSORS s WHERE USER_ID = :idUser ORDER BY s.created_date DESC LIMIT 1", nativeQuery = true)
    Optional<Double> findLastTemperatureByUserId(@Param("idUser") Integer idUser);

    @Query(value = "SELECT s.HUMIDITY FROM SENSORS s WHERE USER_ID = :idUser ORDER BY s.created_date DESC LIMIT 1", nativeQuery = true)
    Optional<Double> findLastHumidityByUserId(Integer idUser);

    @Query(value = "SELECT s.LUMINOSITY FROM SENSORS s WHERE USER_ID = :idUser ORDER BY s.created_date DESC LIMIT 1", nativeQuery = true)
    Optional<Double> findLastLuminosityByUserId(Integer idUser);
}
