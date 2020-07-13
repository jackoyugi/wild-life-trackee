package models;

import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

public class EndangeredAnimalTest {
    @Test
    public void EndangeredAnimalInstantiatesCorrectly_true(){
        EndangeredAnimal testEndanger = new EndangeredAnimal(1, "chiew", "weak", "young");
        assertEquals(true, testEndanger instanceof EndangeredAnimal);
    }
    @Test
    public void getAnimalIdInstantiatesCorrectly_true(){
        EndangeredAnimal testEndanger = new EndangeredAnimal(1, "chiew", "weak", "young");
        assertEquals(1, testEndanger.getAnimalId());
    }
    @Test
    public void getName_EndangerAnimalInstantiatesWithName_true(){
        EndangeredAnimal testEndanger = new EndangeredAnimal(1, "chiew", "weak", "young");
        assertEquals("chiew", testEndanger.getName());
    }
    @Test
    public void getStatus_EndangeredAnimalInstantiatesWithStatus_String(){
        EndangeredAnimal testEndanger = new EndangeredAnimal(1, "chiew", "weak", "young");
        assertEquals("weak", testEndanger.getStatus());
    }
    @Test
    public void getAgeStatus_EndangeredAnimalInstantiatesWithYoung_String(){
        EndangeredAnimal testEndanger = new EndangeredAnimal(1,"chiew", "weak", "young");
        assertEquals("young", testEndanger.getAgeStatus());
    }
    @Test
    public void equals_returnsTrueIfNameStatusAndOldAreSame_true(){
        EndangeredAnimal testEndanger = new EndangeredAnimal(1, "onger", "strong", "infant");
        EndangeredAnimal anotherEndanger = new EndangeredAnimal(1, "onger", "strong", "infant");
        assertEquals(true, testEndanger.equals(anotherEndanger));
    }
    @Test
    public void save_insertsObjectIntoDatabase_EndangeredAnimal(){
        EndangeredAnimal testEndanger = new EndangeredAnimal(1, "chiew", "weak", "young");
        testEndanger.save();
        assertEquals(true, EndangeredAnimal.all().get(0).equals(testEndanger));
    }
    @Test
    public void all_returnsAllInstancesOfEndangeredAnimal_true(){
        EndangeredAnimal firstEndanger = new EndangeredAnimal(1, "chiew", "weak", "young");
        firstEndanger.save();
        EndangeredAnimal secondEndanger = new EndangeredAnimal(1, "chiew", "weak", "young");
        secondEndanger.save();
        assertEquals(true, EndangeredAnimal.all().get(0).equals(firstEndanger));
        assertEquals(true, EndangeredAnimal.all().get(1).equals(secondEndanger));
    }
//    @Test
//    public void addAnimal_addsAnimalToEndangeredAnimal(){
//        EndangeredAnimal testEndanger = new EndangeredAnimal(1, "chiew", "weak", "young");
//        testEndanger.save();
//        Animal testAnimal = new Animal("kwach", 1, "stable");
//        testAnimal.save();
//        testEndanger.addAnimal(testAnimal);
//        Animal savedAnimal = testEndanger.getAnimals().get(0);
//        assertTrue(testAnimal.equals(savedAnimal));
//    }
//    @Test
//    public void getAnimals_returnsAllAnimals_list(){
//        EndangeredAnimal testEndanger = new EndangeredAnimal(1, "chiew", "weak", "young");
//        testEndanger.save();
//        Animal testAnimal = new Animal ("kwach", 1, "stable");
//        testAnimal.save();
//        testEndanger.addAnimal(testAnimal);
//        List savedAnimals = testEndanger.getAnimals();
//        assertEquals(savedAnimals.size(), 1);
//    }


}