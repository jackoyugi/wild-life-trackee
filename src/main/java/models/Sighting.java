package models;

import java.time.format.DateTimeFormatter;

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

    public String getLocation() {
        return location;
    }

    public int getId() {
        return animalId;
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
                    this.getId()==newSighting.getId()&&
                    this.getRanger().equals(newSighting.getRanger());
        }
    }
}
