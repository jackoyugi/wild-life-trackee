package models;

import org.sql2o.Connection;

import java.util.List;
import java.util.Objects;

public class Animal {
    private String name;
    private String health;
    private int age;
    private int id;

    public Animal(String name, int age, String health ){
        this.name = name;
        this.age = age;
        this.health = health;
    }
    public static List<Animal> all() {
        String sql = "SELECT * FROM animals";
        try (Connection con = Database.sql2o.open()) {
            return con.createQuery(sql).executeAndFetch(Animal.class);
        }
    }

    public int getAnimal_age(){
        return age;
    }

    public String getAnimal_name(){
        return name;
    }
    public String getAnimal_health(){
        return health;
    }
    public int getId() {
        return id;
    }
    @Override
    public boolean equals(Object otherAnimal){
        if (!(otherAnimal instanceof Animal)){
            return false;
        }else{
            Animal newAnimal = (Animal) otherAnimal;
            return this.getAnimal_name().equals(newAnimal.getAnimal_name())&&
                    this.getAnimal_age()==(newAnimal.getAnimal_age())&&
                    this.getAnimal_health().equals(newAnimal.getAnimal_health());

        }
    }
    @Override
    public int hashCode() {
        return Objects.hash(name, health, age);
    }

    public void save(){
        try(Connection con = Database.sql2o.open()){
            String sql = "INSERT INTO animals (name, age, health) VALUES (:name, :age, :health);";
            this.id = (int) con.createQuery(sql, true)
                    .addParameter("name", this.name)
                    .addParameter("age", this.age)
                    .addParameter("health", this.health)
                    .executeUpdate()
                    .getKey();
        }
    }
    public static Animal find(int id) {
        try(Connection con = Database.sql2o.open()){
            String sql = "SELECT * FROM animals where id=:id";
            Animal animal = con.createQuery(sql)
                    .addParameter("id", id)
                    .executeAndFetchFirst(Animal.class);
            return animal;
        }
    }

}