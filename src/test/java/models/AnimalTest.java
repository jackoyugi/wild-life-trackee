package models;

import org.junit.Rule;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

public class AnimalTest {

    @Rule
    public DatabaseRule database = new DatabaseRule();

    @Test
    public void animal_instantiatesCorrectly_true(){
        Animal testAnimal = new Animal("kwach", 1, "stable");
        assertEquals(true, testAnimal instanceof Animal);
    }
    @Test
    public void getAnimal_age_InstantiatesWithAge_Minus(){
        Animal testAnimal = new Animal( "kwach", 1, "stable");
        assertEquals(1, testAnimal.getAnimal_age());
    }

    @Test
    public void getAnimalName_InstantiatesWithAnimalName_Kwach(){
        Animal testAnimal = new Animal( "kwach", 1, "stable");
        assertEquals("kwach", testAnimal.getAnimal_name());
    }
    @Test
    public void getAnimalHealth_InstantiatesWithHealthCorrectly_Health(){
        Animal testAnimal = new Animal("kwach", 1, "stable");
        assertEquals("stable", testAnimal.getAnimal_health());
    }
    @Test
    public void setId_InstantiatesWithIdCorrectly_Id(){
        Animal testAnimal = new Animal( "kwach", 1, "stable");
            assertEquals(0, testAnimal.getId());
    }
    @Test
    public void equals_returnsTrueIfNameAgeHealthIsSame_true(){
        Animal firstAnimal = new Animal( "kwach", 1, "stable");
        Animal anotherAnimal  = new Animal("kwach", 1, "stable");
        assertTrue(firstAnimal.equals(anotherAnimal));
    }
    @Test
    public void save_insertObjectIntoDatabase_animal(){
        Animal testAnimal = new Animal("kwach", 1, "stable");
        testAnimal.save();
        assertTrue(Animal.all().get(0).equals(testAnimal));
    }
    @Test
    public void all_returnAllInstancesOfAnimal_true(){
        Animal firstAnimal = new Animal("kwach", 1, "stable");
        firstAnimal.save();
        Animal secondAnimal = new Animal( "kwach", 1, "stable");
        secondAnimal.save();
        assertEquals(true, Animal.all().get(0).equals(firstAnimal));
        assertEquals(true, Animal.all().get(1).equals(secondAnimal));
    }
    @Test
    public void save_assignedIdObject(){
        Animal testAnimal = new Animal("kwach", 1, "stable");
        testAnimal.save();
        Animal savedAnimal = Animal.all().get(0);
        assertEquals(testAnimal.getId(), savedAnimal.getId());
    }
    @Test
    public void find_returnsAnimalWithSameId_secondAnimal(){
        Animal firstAnimal = new Animal("kwach", 1, "stable");
        firstAnimal.save();
        Animal secondAnimal = new Animal("kwach", 1, "stable");
        secondAnimal.save();
        assertEquals(Animal.find(secondAnimal.getId()), secondAnimal);
    }
    @Test
    public void getEndangeredAnimals_returnsAllEndangeredAnimals_list(){
        EndangeredAnimal testEndanger = new EndangeredAnimal(1, "chiew", "weak", "young");
        testEndanger.save();
        Animal testAnimal = new Animal("kwach", 1, "stable");
        testAnimal.save();
        testEndanger.addAnimal(testAnimal);
        List savedEndangeredAnimals = testAnimal.getEndangeredAnimals();
        assertEquals(1, savedEndangeredAnimals.size());

    }

}