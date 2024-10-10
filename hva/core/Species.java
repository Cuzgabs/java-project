package hva.core;

import java.io.Serializable;

public class Species implements Serializable {
    private String _speciesId;
    private String _speciesName;

    public Species(String speciesId, String speciesName){
        _speciesId = speciesId;
        _speciesName = speciesName; 
    }

    public String getSpeciesName() {return _speciesName;}
    public String getSpeciesId() {return _speciesId;}
}
