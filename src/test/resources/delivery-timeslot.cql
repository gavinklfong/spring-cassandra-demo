-- Create a keyspace
CREATE KEYSPACE IF NOT EXISTS supermarket WITH REPLICATION = { 'class' : 'SimpleStrategy', 'replication_factor' : '1' };

-- Create delivery timeslot
CREATE TABLE IF NOT EXISTS supermarket.delivery_timeslots (
  delivery_date date,
  start_time time,
  delivery_team_id uuid,
  confirmed boolean,
  reserved_by_customer_id uuid,
  reservation_expiry timestamp,
  PRIMARY KEY (delivery_date, start_time, delivery_team_id)
);

-- Insert sample delivery timeslot
INSERT INTO supermarket.delivery_timeslots (delivery_date, start_time, delivery_team_id, reserved_by_customer_id, reservation_expiry, confirmed)
VALUES ('2022-02-15', '09:00:00', f53a21cb-f056-499b-a6b2-3832232fa6e6, 00000000-0000-0000-0000-000000000000, '1970-01-01T00:00:00.001+0000', false);
INSERT INTO supermarket.delivery_timeslots (delivery_date, start_time, delivery_team_id, reserved_by_customer_id, reservation_expiry, confirmed)
VALUES ('2022-02-15', '12:00:00', e0681835-b273-4455-b42a-41687cecfb60, 00000000-0000-0000-0000-000000000000, '1970-01-01T00:00:00.001+0000', false);
INSERT INTO supermarket.delivery_timeslots (delivery_date, start_time, delivery_team_id, reserved_by_customer_id, reservation_expiry, confirmed)
VALUES ('2022-02-15', '09:00:00', e0681835-b273-4455-b42a-41687cecfb60, 28a76352-0102-45ae-81c7-aeb25cc228f9, '2022-02-05T15:00:00.000+0000', true);
INSERT INTO supermarket.delivery_timeslots (delivery_date, start_time, delivery_team_id, reserved_by_customer_id, reservation_expiry, confirmed)
VALUES ('2022-02-16', '12:00:00', e0681835-b273-4455-b42a-41687cecfb60, 00000000-0000-0000-0000-000000000000, '1970-01-01T00:00:00.001+0000', false);
INSERT INTO supermarket.delivery_timeslots (delivery_date, start_time, delivery_team_id, reserved_by_customer_id, reservation_expiry, confirmed)
VALUES ('2022-02-16', '16:00:00', e0681835-b273-4455-b42a-41687cecfb60, 00000000-0000-0000-0000-000000000000, '1970-01-01T00:00:00.001+0000', false);

