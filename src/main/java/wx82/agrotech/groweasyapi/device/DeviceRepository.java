package wx82.agrotech.groweasyapi.device;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface DeviceRepository extends JpaRepository<Device, Integer> {

    @Query("""
            SELECT device
            FROM Device device
            WHERE device.user.id = :userId
            """)
    List<Device> findAllDevices(Integer userId);
}
