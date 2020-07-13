package models;

import org.junit.rules.ExternalResource;
import org.sql2o.Connection;
import org.sql2o.Sql2o;

public class DatabaseRule extends ExternalResource {
    @Override
    protected void before() {
        Database.sql2o = new Sql2o("jdbc:postgresql://localhost:5432/wildlife_test", "jackoyugi", "00100");
    }

    @Override
    protected void after() {
        try(Connection con = Database.sql2o.open()) {
            String deleteAnimalsQuery = "DELETE FROM animals *;";
            String deleteSightingQuery = "DELETE FROM sightings *;";
            String deleteEndangeredAnimalQuery = "DELETE FROM endangeredanimals *;";
            String deleteJoinsQuery = "DELETE FROM endangeredanimals_animals *";
            con.createQuery(deleteAnimalsQuery).executeUpdate();
            con.createQuery(deleteSightingQuery).executeUpdate();
            con.createQuery(deleteEndangeredAnimalQuery).executeUpdate();
            con.createQuery(deleteJoinsQuery).executeUpdate();
        }
    }
}
