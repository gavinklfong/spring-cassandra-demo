-- Create a keyspace
CREATE KEYSPACE IF NOT EXISTS supermarket WITH REPLICATION = { 'class' : 'SimpleStrategy', 'replication_factor' : '1' };

-- Define Address user-defined type
CREATE TYPE supermarket.address_type (
  street_address text,
  city text,
  country text,
  zipcode text
);

-- Create customer table
CREATE TABLE supermarket.customers (
  id uuid,
  name text,
  telephone text,
  email text,
  default_address_key text,
  address frozen<address_type>,
  PRIMARY KEY (id)
);

-- Insert customer records
INSERT INTO supermarket.customers (id, name, telephone, email, address)
VALUES (28a76352-0102-45ae-81c7-aeb25cc228f9, 'John', '012345678912345', 'john@gmail.com',
{street_address: '2284 Lenna Lodge', city: 'Longmont', country: 'Belgium', zipcode: '05874'});
INSERT INTO supermarket.customers (id, name, telephone, email, address)
VALUES (7febc928-a5d0-40d5-ad71-ef7ebe2f2fe3, 'Peter', '012345678912345', 'peter@gmail.com',
{street_address: '1059 Ardella Center', city: 'Dundalk', country: 'Dominican Republic', zipcode: '47368'});
