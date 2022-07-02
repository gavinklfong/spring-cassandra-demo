package space.gavinklfong.spring.cassandra.repo;

import org.springframework.data.cassandra.repository.Query;
import org.springframework.data.repository.CrudRepository;
import space.gavinklfong.spring.cassandra.models.DeliveryTimeslot;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.UUID;

public interface DeliveryTimeslotRepo extends CrudRepository<DeliveryTimeslot, DeliveryTimeslot.Key> {
    @Query("SELECT * FROM delivery_timeslots WHERE delivery_date = ?0 ORDER BY start_time ASC")
    List<DeliveryTimeslot> findByDeliveryDate(LocalDate deliveryDate);
}
