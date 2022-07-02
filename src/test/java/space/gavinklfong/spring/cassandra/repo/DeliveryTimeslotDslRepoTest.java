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
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@Testcontainers
public class DeliveryTimeslotDslRepoTest {
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
    void testFindByDeliveryDate() {
        List<DeliveryTimeslot> timeslots = deliveryTimeslotRepo.findByDeliveryDate(LocalDate.of(2022, 2, 15));
        assertThat(timeslots).hasSize(3);
    }
}
