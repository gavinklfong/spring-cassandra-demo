-- Create a keyspace
CREATE KEYSPACE IF NOT EXISTS supermarket WITH REPLICATION = { 'class' : 'SimpleStrategy', 'replication_factor' : '1' };

-- Create customer table
CREATE TABLE IF NOT EXISTS supermarket.customers (
id uuid,
name text,
telephone text,
email text,
PRIMARY KEY (id)
);

-- Insert customer records
INSERT INTO supermarket.customers (id, name, telephone, email)
VALUES (28a76352-0102-45ae-81c7-aeb25cc228f9, 'John', '012345678912345', 'john@gmail.com');
INSERT INTO supermarket.customers (id, name, telephone, email)
VALUES (7febc928-a5d0-40d5-ad71-ef7ebe2f2fe3, 'Peter', '012345678912345', 'peter@gmail.com');
