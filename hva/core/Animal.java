package hva.core;

public class Animal {
    private String _animalId;
    private String _animalName;
    private Species _species;

    public Animal(String animalId, String animalName,Species species){
        _animalId = animalId;
        _animalName = animalName;
        _species = species; 
    }

    public Species getSpecies() {return _species;}
    public String getName() {return _animalName;}
    public String getId() {return _animalId;}
}

