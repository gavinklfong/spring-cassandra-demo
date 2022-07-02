package space.gavinklfong.spring.cassandra.models;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Data;
import lombok.experimental.FieldDefaults;
import org.springframework.data.cassandra.core.cql.PrimaryKeyType;
import org.springframework.data.cassandra.core.mapping.*;

import java.io.Serializable;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.UUID;

@Data
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
@Table("delivery_timeslots")
public class DeliveryTimeslot {
    @PrimaryKeyClass
    @Data
    @Builder
    public static class Key implements Serializable {
        @PrimaryKeyColumn(name = "delivery_date", ordinal = 0, type = PrimaryKeyType.PARTITIONED)
        LocalDate deliveryDate;

        @PrimaryKeyColumn(name = "start_time", ordinal = 1, type = PrimaryKeyType.CLUSTERED)
        LocalTime startTime;

        @PrimaryKeyColumn(name = "delivery_team_id", ordinal = 2, type = PrimaryKeyType.CLUSTERED)
        UUID deliveryTeamId;
    }

    @PrimaryKey
    DeliveryTimeslot.Key key;

    @Column("reserved_by_customer_id")
    UUID reservedByCustomerId;

    @Column("reservation_expiry")
    Instant reservationExpiry;

    @Column("confirmed")
    Boolean confirmed;
}
