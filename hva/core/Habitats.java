package hva.core;

import java.io.Serializable;
import java.util.HashMap;

public class Habitats implements Serializable {
    private String _habitatId;
    private String _habitatName;
    private int _habitatArea;
    private HashMap<String, Arvore> _arvores;
    private HashMap<String, Animal> _animals;

    public Habitats(String habitatId, String habitatName, int habitatArea) {
        _habitatId = habitatId;
        _habitatName = habitatName;
        _habitatArea = habitatArea;
        _arvores = new HashMap<>();
        _animals = new HashMap<>();
    }

    public int gethabitatArea() {
        return _habitatArea;
    }

    public String getHabitatId() {
        return _habitatId;
    }

    public String getHabitatName() {
        return _habitatName;
    }

    public int getNumeroDeArvores() {
        return _arvores.size();
    }

    public HashMap<String, Arvore> getArvores() {
        return _arvores;
    }

    public void addTree(Arvore tree) {
        if (tree != null) {
            _arvores.put(tree.getArvoreId(), tree);
        } else {
            throw new IllegalArgumentException("Árvore não pode ser nula");
        }
    }

    public void addAnimal(Animal animal) {
        if (animal != null) {
            _animals.put(animal.getAnimalId(), animal);
        } else {
            throw new IllegalArgumentException("Animal não pode ser nulo");
        }
    }

    public HashMap<String, Animal> getAnimals() {
        return _animals;
    }

    @Override
    public String toString() {
        return String.format("HABITAT|%s|%s|%d|%d",
                _habitatId,
                _habitatName,
                _habitatArea,
                getNumeroDeArvores());
    }
}
