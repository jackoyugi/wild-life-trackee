SET MODE PostgresQL;

CREATE TABLE IF NOT EXISTS animals(

    id SERIAL PRIMARY KEY,
    name VARCHAR;
    age INTEGER;
    health VARCHAR
);