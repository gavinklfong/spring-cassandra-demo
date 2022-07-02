package space.gavinklfong.spring.cassandra.repo;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.testcontainers.containers.CassandraContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;
import space.gavinklfong.spring.cassandra.models.SimpleCustomer;

import java.util.Optional;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@Testcontainers
public class DeliveryTimeslotRepoTest {
    @Container
    static final CassandraContainer container =
            new CassandraContainer("cassandra")
                    .withInitScript("simple-customer.cql");

    @DynamicPropertySource
    static void dataSourceProperties(DynamicPropertyRegistry registry) {
        registry.add("spring.data.cassandra.keyspace-name", () -> "supermarket");
        registry.add("spring.data.cassandra.contact-points", () -> container.getHost());
        registry.add("spring.data.cassandra.port", () -> container.getMappedPort(9042));
        registry.add("spring.data.cassandra.local-datacenter", () -> "datacenter1");
    }

    @Autowired
    SimpleCustomerRepo simpleCustomerRepo;

    @Test
    void testFindById() {
        final String CUSTOMER_ID = "7febc928-a5d0-40d5-ad71-ef7ebe2f2fe3";
        Optional<SimpleCustomer> customer = simpleCustomerRepo.findById(UUID.fromString(CUSTOMER_ID));
        assertThat(customer).isNotNull().isNotEmpty();
        assertThat(customer.get().getId()).isEqualTo(UUID.fromString(CUSTOMER_ID));
    }

    @Test
    void testSave() {
        SimpleCustomer newCustomer = SimpleCustomer.builder()
                .id(UUID.randomUUID())
                .name("new customer")
                .email("new-customer@test.com")
                .telephone("1234567890")
                .build();
        simpleCustomerRepo.save(newCustomer);

        Optional<SimpleCustomer> newlySavedCustomer = simpleCustomerRepo.findById(newCustomer.getId());
        assertThat(newlySavedCustomer).isNotNull().isNotEmpty().get().isEqualTo(newCustomer);
    }
}
