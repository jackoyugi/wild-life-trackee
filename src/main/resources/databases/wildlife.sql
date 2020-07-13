SET MODE PostgresQL;

CREATE TABLE IF NOT EXISTS animals(

    id SERIAL PRIMARY KEY,
    name VARCHAR,
    age INTEGER,
    health VARCHAR
);
CREATE TABLE IF NOT EXISTS sightings(
    id SERIAL PRIMARY KEY,
    animalId INTEGER,
    location VARCHAR,
    ranger VARCHAR,
);
CREATE TABLE IF NOT EXISTS endangeredanimals(
    id SERIAL PRIMARY KEY,
    animalId INTEGER,
    name VARCHAR,
    status VARCHAR,
    old VARCHAR,
);
CREATE TABLE IF NOT EXISTS eanimals(
    id SERIAL PRIMARY KEY,
    eanimalid INTEGER,
    animalid INTEGER,
);