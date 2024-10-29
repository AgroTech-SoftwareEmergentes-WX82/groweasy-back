package wx82.agrotech.groweasyapi.notifications;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface NotificationRepository extends JpaRepository<Notification, Integer> {


    @Query("""
            SELECT notification
            FROM Notification notification
            WHERE notification.user.id = :idUser
            """)
    List<Notification> findByUser(@Param("idUser") int idUser);

    @Query("""
            SELECT notification
            FROM Notification notification
            WHERE notification.user.id = :idUser
            AND notification.device.id = :idDevice
            """)
    Optional<Notification> findByIdUserAndIdDevice(int idDevice, int idUser);
}
