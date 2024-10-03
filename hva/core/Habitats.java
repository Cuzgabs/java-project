package hva.core;

public class Habitats {
    private String _habitatId;
    private String _habitatName;
    private int _habitatArea;

    public Habitats (String habitatId, String habitatName, int habitatArea) {
        _habitatId = habitatId;
        _habitatName = habitatName;
        _habitatArea = habitatArea;
    }

    public int gethabitatArea() {return _habitatArea;}
    public String getHabitatId() {return _habitatId;}
    public String getHabitatName() {return _habitatName;}
}
