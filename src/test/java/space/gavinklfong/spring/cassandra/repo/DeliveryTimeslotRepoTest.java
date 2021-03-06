package space.gavinklfong.spring.cassandra.repo;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.testcontainers.containers.CassandraContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;
import space.gavinklfong.spring.cassandra.models.DeliveryTimeslot;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@Testcontainers
public class DeliveryTimeslotRepoTest {
    @Container
    static final CassandraContainer container =
            new CassandraContainer("cassandra")
                    .withInitScript("delivery-timeslot.cql");

    @DynamicPropertySource
    static void dataSourceProperties(DynamicPropertyRegistry registry) {
        registry.add("spring.data.cassandra.keyspace-name", () -> "supermarket");
        registry.add("spring.data.cassandra.contact-points", () -> container.getHost());
        registry.add("spring.data.cassandra.port", () -> container.getMappedPort(9042));
        registry.add("spring.data.cassandra.local-datacenter", () -> "datacenter1");
    }

    @Autowired
    DeliveryTimeslotRepo deliveryTimeslotRepo;

    @Test
    void testFindById() {
        Optional<DeliveryTimeslot> deliveryTimeslotOptional = deliveryTimeslotRepo.findById(
                DeliveryTimeslot.Key.builder()
                        .deliveryDate(LocalDate.parse("2022-02-15"))
                        .startTime(LocalTime.parse("09:00"))
                        .deliveryTeamId(UUID.fromString("f53a21cb-f056-499b-a6b2-3832232fa6e6"))
                        .build());
        assertThat(deliveryTimeslotOptional).isNotEmpty();
    }

    @Test
    void testFindByDeliveryDate() {
        List<DeliveryTimeslot> timeslots = deliveryTimeslotRepo.findByDeliveryDate(LocalDate.of(2022, 2, 15));
        assertThat(timeslots).hasSize(3)
                .isSortedAccordingTo(Comparator.comparing(a -> a.getKey().getStartTime()));
    }
}
