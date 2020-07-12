package models;

import org.junit.Rule;
import org.junit.Test;

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
        assertEquals(1, testSighting.getId());
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

}