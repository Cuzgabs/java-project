package hva.core;

import java.io.Serializable;

public class Species implements Serializable,Responsibility {
    private String _speciesId;
    private String _speciesName;
    private int _population;


    public Species(String speciesId, String speciesName) {
        _speciesId = speciesId;
        _speciesName = speciesName;
    }

    public String getSpeciesName() {
        return _speciesName;
    }

    public String getSpeciesId() {
        return _speciesId;
    }

    public int getPopulation() {
        return _population;
    }

    public void addToPopulation() {
        _population++;
    }
    
    @Override
    public String getResponsibilityId(){
        return getSpeciesId();
    }
}
