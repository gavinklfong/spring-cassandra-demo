package space.gavinklfong.spring.cassandra.repo;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import space.gavinklfong.spring.cassandra.models.CustomerWithAddress;

import java.util.UUID;

@Repository
public interface CustomerWithAddressesRepo extends CrudRepository<CustomerWithAddress, UUID> {
}
