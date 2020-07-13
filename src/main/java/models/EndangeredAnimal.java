package models;

import org.sql2o.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class EndangeredAnimal {
    private int animalId;
    private String name;
    private String status;
    private String old;
    private int id;

    public EndangeredAnimal(int animalId, String name, String status, String old){
        this.animalId = animalId;
        this.name=name;
        this.status=status;
        this.old=old;
    }

    public static List< EndangeredAnimal> all() {
        String sql = "SELECT * FROM endangeredanimals";
        try(Connection con = Database.sql2o.open()){
            return con.createQuery(sql).executeAndFetch(EndangeredAnimal.class);
        }
    }

    public int getId(){
        return id;
    }

    public int getAnimalId(){
        return animalId;
    }

    public String getName() {
        return name;
    }

    public String getStatus() {
        return status;
    }

    public String getAgeStatus() {
        return old;
    }
    @Override
    public boolean equals(Object otherEndanger){
        if(!(otherEndanger instanceof EndangeredAnimal)){
            return false;
        }else{
            EndangeredAnimal newEndanger = (EndangeredAnimal) otherEndanger;
            return this.getName().equals(newEndanger.getName())&&
                    this.getAgeStatus().equals(newEndanger.getAgeStatus())&&
                    this.getStatus().equals(newEndanger.getStatus())&&
                    this.getAnimalId()==(newEndanger.getAnimalId());
        }
    }

    @Override
    public int hashCode() {
        return Objects.hash(animalId, name, status, old, id);
    }
    public void save(){
        try(Connection con = Database.sql2o.open()){
            String sql = "INSERT INTO endangeredanimals(animalId, name, status, old) VALUES(:animalId, :name, :status, :old)";
            this.id = (int) con.createQuery(sql, true)
                    .addParameter("animalId", this.animalId)
                    .addParameter("name", this.name)
                    .addParameter("status", this.status)
                    .addParameter("old", this.old)
                    .executeUpdate()
                    .getKey();
        }
    }
    public void addAnimal(Animal animal){
        try (Connection con = Database.sql2o.open()){
            String sql = "INSERT INTO endangredanimals_animals (endangeredanimal_id, animal_id) VALUES (:endangeredanimal_id, :animal_id)";
            con.createQuery(sql)
                    .addParameter("endangeredanimal_id", this.getId())
                    .addParameter("animal_id", this.getId())
                    .executeUpdate();


        }
    }
    public List<Animal> getAnimals(){
        try(Connection con = Database.sql2o.open()){
            String joinQuery = "SELECT animal_id FROM endangeredanimals_animals WHERE endangeredanimals_id = :endangeredanimals_id";
            List <Integer> animalIds = con.createQuery(joinQuery)
                    .addParameter("endangeredanimals_id", this.getId())
                    .executeAndFetch(Integer.class);
            List<Animal> animals = new ArrayList<Animal>();
            for (Integer animalId : animalIds){
                String animalQuery = "SELECT * FORM animals WHERE id = :animalId";
                Animal animal = con.createQuery(animalQuery)
                        .addParameter("animalId", "animalId")
                        .executeAndFetchFirst(Animal.class);
                animals.add(animal);
            }
            return animals;
        }
    }

    public void add(EndangeredAnimal endangeredAnimal) {
    }
}
