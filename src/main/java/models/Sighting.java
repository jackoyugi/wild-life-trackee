package models;

import org.sql2o.Connection;

import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Objects;

public class Sighting {
    private String location;
    private String ranger;
    private int animalId;
    private int id;
    private DateTimeFormatter time;

    public Sighting(int animalId, String location, String ranger){
        this.animalId=animalId;
        this.location=location;
        this.ranger=ranger;
    }

    public static List< Sighting> all() {
        String sql = "SELECT * FROM sightings";
        try(Connection con = Database.sql2o.open()){
            return con.createQuery(sql).executeAndFetch(Sighting.class);
        }
    }

    public static Sighting find(int id) {
        try(Connection con = Database.sql2o.open()){
            String sql = "SELECT * FROM sightings where id=:id";
            Sighting sighting = con.createQuery(sql)
                    .addParameter("id", id)
                    .executeAndFetchFirst(Sighting.class);
            return sighting;
        }catch (IndexOutOfBoundsException ex){
            System.out.println("we are sorry for that");
            return null;
        }
    }

    public String getLocation() {
        return location;
    }

    public int getAnimalId() {
        return animalId;
    }

    public int getId(){
        return id;
    }

    public String getRanger() {
        return ranger;
    }
    @Override
    public boolean equals(Object otherSighting){
        if(!(otherSighting instanceof Sighting)){
            return false;
        }else{
            Sighting newSighting = (Sighting) otherSighting;
            return this.getLocation().equals(newSighting.getLocation())&&
                    this.getAnimalId()==newSighting.getAnimalId()&&
                    this.getRanger().equals(newSighting.getRanger());
        }
    }

    @Override
    public int hashCode() {
        return Objects.hash(location, ranger, animalId, id, time);
    }
    public void save(){
        try(Connection con = Database.sql2o.open()){
            String sql = "INSERT INTO sightings(animalId, location, ranger)VALUES(:animalId, :location, :ranger);";
            this.id = (int) con.createQuery(sql, true)
                    .addParameter("animalId", this.animalId)
                    .addParameter("location", this.location)
                    .addParameter("ranger", this.ranger)
                    .executeUpdate()
                    .getKey();
        }
    }
    public void delete() {
        try (Connection con = Database.sql2o.open()) {
            String sql = "DELETE FROM sightings WHERE id = :id;";
            con.createQuery(sql)
                    .addParameter("id", id)
                    .executeUpdate();
        }
    }

}
