package models;

import org.junit.Rule;
import org.junit.Test;

import java.util.Arrays;

import static org.junit.Assert.*;

public class SightingTest {

    @Rule
    public DatabaseRule database = new DatabaseRule();

    @Test
    public void sighting_instantiatesCorrectly_true(){
        Sighting testSighting = new Sighting (1, "kodera", "jackwonder");
        assertEquals(true, testSighting instanceof Sighting);
    }
    @Test
    public void Sighting_instantiatesWithLocation_String(){
        Sighting testSighting = new Sighting (1, "kodera", "jackwonder");
        assertEquals("kodera", testSighting.getLocation());
    }
    @Test
    public void sighting_instantiatesId_int(){
        Sighting testSighting = new Sighting (1, "kodera", "jackwonder");
        assertEquals(1, testSighting.getAnimalId());
    }
    @Test
    public void sighting_instantiatesWithRanger_String(){
        Sighting testSighting = new Sighting (1, "kodera", "jackwonder");
        assertEquals("jackwonder", testSighting.getRanger());
    }
    @Test
    public void equals_returnTrueIfSightingAndAnimalAreSame_true(){
        Sighting testSighting = new Sighting (1, "kodera", "jackwonder");
        Sighting anotherSighting = new Sighting(1, "kodera", "jackwonder");
        assertEquals(true, testSighting.equals(anotherSighting));
    }
    @Test
    public void save_returnsTrueIfSightingAndAnimalAreSame(){
        Sighting testSighting = new Sighting (1, "kodera", "jackwonder");
        testSighting.save();
        assertEquals(true, Sighting.all().get(0).equals(testSighting));
    }
    @Test
    public void save_assignsIdToSighting(){
        Sighting testSighting = new Sighting(1, "kodera", "jackwonder");
        testSighting.save();
        Sighting savedSighting = Sighting.all().get(0);
        assertEquals(savedSighting.getId(), testSighting.getId());
    }
    @Test
    public void all_returnsAllInstancesOfSighting_true(){
        Sighting firstSighting = new Sighting(1, "kodera", "jackwonder");
        firstSighting.save();
        Sighting secondSighting = new Sighting(1, "kodera", "jackwonder");
        secondSighting.save();
        assertEquals(true, Sighting.all().get(0).equals(firstSighting));
        assertEquals(true, Sighting.all().get(1).equals(secondSighting));
    }
    @Test
    public void find_returnsSightingWithSameId_secondSighting(){
        Sighting firstSighting = new Sighting(1, "kodera", "jackwonder");
        firstSighting.save();
        Sighting secondSighting = new Sighting(1, "kodera", "jackwonder");
        secondSighting.save();
        assertEquals(Sighting.find(secondSighting.getId()), secondSighting);
    }
    @Test
    public void save_savesAnimalIdIntoDatabase_true(){
        Animal testAnimal = new Animal ("kwach", 1, "stable");
        testAnimal.save();
        Sighting testSighting = new Sighting(testAnimal.getId(), "kodera", "jackwonder");
        testSighting.save();
        Sighting savedSighting = Sighting.find(testSighting.getId());
        assertEquals(savedSighting.getAnimalId(), testAnimal.getId());
    }
    @Test
    public void getSightings_retrievesAllSightingsFromDatabase_sightingList() throws Exception{
        Animal testAnimal = new Animal ("kwach", 1, "stable");
        testAnimal.save();
        Sighting firstSighting = new Sighting(testAnimal.getId(), "kodera", "jackwonder");
        firstSighting.save();
        Sighting secondSighting = new Sighting(testAnimal.getId(), "kodera", "jackwonder");
        secondSighting.save();
        Sighting[] sightings = new Sighting[] {firstSighting, secondSighting};
        assertTrue(testAnimal.getSightings().containsAll(Arrays.asList(sightings)));

    }


}