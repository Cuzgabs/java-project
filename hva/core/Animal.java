package hva.core;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Animal implements Serializable {
    private String _animalId;
    private String _animalName;
    private Species _species;
    private Habitats _habitat;  // Habitat associado
    private List<String> _healthHistory;  // Lista de eventos de saúde (historial)


    public Animal(String animalId, String animalName,Species species, Habitats habitat){
        _animalId = animalId;
        _animalName = animalName;
        _species = species; 
        _healthHistory = new ArrayList<>();  // Inicializa o historial de saúde como uma lista vazia
        _habitat = habitat; // Inicializa o habitat como nulo

    }

    public Species getSpecies() {return _species;}
    public String getAnimalName() {return _animalName;}
    public String getAnimalId() {return _animalId;}
    public List<String> getHealthHistory() { return _healthHistory;}
    public void addHealthEvent(String event) {_healthHistory.add(event);}
    public Habitats getHabitat() {return _habitat;}
}

