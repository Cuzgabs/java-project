package hva.core;

public class Species {
    private String _speciesId;
    private String _speciesName;

    public Species(String speciesId, String speciesName){
        _speciesId = speciesId;
        _speciesName = speciesName; 
    }

    public String getName() {return _speciesName;}
    public String getId() {return _speciesId;}
}
