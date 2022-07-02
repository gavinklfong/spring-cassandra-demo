package space.gavinklfong.spring.cassandra.repo;

import org.springframework.data.cassandra.repository.Query;
import org.springframework.data.repository.CrudRepository;
import space.gavinklfong.spring.cassandra.models.DeliveryTimeslot;

import java.time.LocalDate;
import java.util.List;

public interface DeliveryTimeslotDslRepo extends CrudRepository<DeliveryTimeslot, DeliveryTimeslot.Key> {
    @Query("SELECT * FROM delivery_timeslots WHERE delivery_date = ?0")
    List<DeliveryTimeslot> findByDelivery(LocalDate deliveryDate);
}
