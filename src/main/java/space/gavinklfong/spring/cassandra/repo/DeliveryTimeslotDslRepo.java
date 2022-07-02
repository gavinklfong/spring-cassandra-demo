package space.gavinklfong.spring.cassandra.repo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.cassandra.core.CassandraOperations;
import org.springframework.data.cassandra.core.UpdateOptions;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Component;
import space.gavinklfong.spring.cassandra.models.DeliveryTimeslot;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.springframework.data.cassandra.core.query.Criteria.where;
import static org.springframework.data.cassandra.core.query.Query.query;

@Component
public class DeliveryTimeslotDslRepo {

    @Autowired
    private CassandraOperations cassandraOperations;

    public Optional<DeliveryTimeslot> findById(DeliveryTimeslot.Key key) {
        return Optional.ofNullable(cassandraOperations.selectOne(
                query(where("delivery_date").is(key.getDeliveryDate()))
                        .and(where("start_time").is(key.getStartTime()))
                        .and(where("delivery_team_id").is(key.getDeliveryTeamId())),
                DeliveryTimeslot.class));
    }

    public List<DeliveryTimeslot> findByDeliveryDate(LocalDate deliveryDate) {
        return cassandraOperations
                .select(query(where("delivery_date").is(deliveryDate))
                                .sort(Sort.by(Sort.Direction.ASC, "start_time")),
                        DeliveryTimeslot.class);
    }

    public Boolean updateIfEmptyCustomerId(DeliveryTimeslot deliveryTimeslot) {
        return cassandraOperations.update(
                deliveryTimeslot,
                UpdateOptions.builder().ifCondition(
                        query(where("reserved_by_customer_id")
                                .is(UUID.fromString("00000000-0000-0000-0000-000000000000")))
                ).build())
                .wasApplied();
    }
}
