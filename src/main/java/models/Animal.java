package models;

import java.util.Objects;

public class Animal {
    private int animal_id;
    private String animal_name;
    private String animal_health;
    private String animal_age;
    private int id;

    public Animal(int animal_id, String animal_name, String animal_age, String animal_health, int id ){
        this.animal_id = animal_id;
        this.animal_name = animal_name;
        this.animal_age = animal_age;
        this.animal_health = animal_health;
        this.id = 0;
    }

    public String getAnimal_age(){
        return animal_age;
    }
    public int getAnimal_id(){
        return animal_id;
    }
    public String getAnimal_name(){
        return animal_name;
    }
    public String getAnimal_health(){
        return animal_health;
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
                    this.getAnimal_age().equals(newAnimal.getAnimal_age())&&
                    this.getAnimal_health().equals(newAnimal.getAnimal_health())&&
                    this.getId()==(newAnimal.getId())&&
                    this.getAnimal_id()==(newAnimal.getAnimal_id());
        }
    }
    @Override
    public int hashCode() {
        return Objects.hash(animal_id, animal_name, animal_health, animal_age, id);
    }
}
