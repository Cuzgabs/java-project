package hva.core;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Animal implements Serializable {
    private String _animalId;
    private String _animalName;
    private Species _species;
    private Habitats _habitat;
    private List<String> _healthHistory;

    public Animal(String animalId, String animalName, Species species, Habitats habitat) {
        _animalId = animalId;
        _animalName = animalName;
        _species = species;
        _healthHistory = new ArrayList<>();
        _habitat = habitat;

    }

    public Species getSpecies() {
        return _species;
    }

    public String getAnimalName() {
        return _animalName;
    }

    public String getAnimalId() {
        return _animalId;
    }

    public List<String> getHealthHistory() {
        return _healthHistory;
    }

    public void addHealthEvent(String event) {
        _healthHistory.add(event);
    }

    

    public double getSatisfactionOfAnimal() {
        return _habitat.calcularSatisfacaoAnimal(this);
    }


    public Habitats getHabitat() {
        return _habitat;
    }

    public void setHabitat(Habitats habitat) {
        _habitat = habitat;
    }

    @Override
    public String toString() {
        String habitatId = (_habitat != null) ? _habitat.getHabitatId() : "Sem habitat";
        String healthHistory = _healthHistory.isEmpty() ? "VOID" : String.join(",", _healthHistory);

        return String.format("ANIMAL|%s|%s|%s|%s|%s",
                _animalId,
                _animalName,
                _species.getSpeciesId(),
                healthHistory,
                habitatId);
    }
}
