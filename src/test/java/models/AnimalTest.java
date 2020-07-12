package models;

import org.junit.Test;

import static org.junit.Assert.*;

public class AnimalTest {

    @Test
    public void animal_instantiatesCorrectly_true(){
        Animal testAnimal = new Animal(1, "kwach", "minus", "stable", 0);
        assertEquals(true, testAnimal instanceof Animal);
    }
    @Test
    public void getAnimal_age_InstantiatesWithAge_Minus(){
        Animal testAnimal = new Animal(1, "kwach", "minus", "stable", 0);
        assertEquals("minus", testAnimal.getAnimal_age());
    }
    @Test
    public void getAnimalId_InstantiatesWithAnimalId_1(){
        Animal testAnimal = new Animal(1, "kwach", "minus", "stable", 0);
        assertEquals(1, testAnimal.getAnimal_id());
    }
    @Test
    public void getAnimalName_InstantiatesWithAnimalName_Kwach(){
        Animal testAnimal = new Animal(1, "kwach", "minus", "stable", 0);
        assertEquals("kwach", testAnimal.getAnimal_name());
    }
    @Test
    public void getAnimalHealth_InstantiatesWithHealthCorrectly_Health(){
        Animal testAnimal = new Animal(1, "kwach", "minus", "stable", 0);
        assertEquals("stable", testAnimal.getAnimal_health());
    }
    @Test
    public void setId_InstantiatesWithIdCorrectly_Id(){
        Animal testAnimal = new Animal(1, "kwach", "minus", "stable", 0);
            assertEquals(0, testAnimal.getId());
    }
    @Test
    public void equals_returnsTrueIfNameIsSame_true(){
        Animal testAnimal = new Animal(1, "kwach", "minus", "stable", 0);
        Animal anotherAnimal  = new Animal(1, "kwach", "minus", "stable", 0);
        assertTrue(testAnimal.equals(anotherAnimal));
    }
    @Test
    public void save_insertObjectIntoDatabase_Animal(){
        Animal testAnimal = new Animal(1, "kwach", "minus", "stable", 0);
        testAnimal.save();
        assertTrue(Animal.all().get(0).equals(testAnimal));
    }

}