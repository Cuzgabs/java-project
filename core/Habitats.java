package hva.core;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;


public class Habitats implements Serializable {
    private String _habitatId;
    private String _habitatName;
    private int _habitatArea;
    private HashMap<String, Arvore> _arvores;
    private HashMap<String, Animal> _animals;
    private HashMap<String, String> _influences;

    public Habitats(String habitatId, String habitatName, int habitatArea) {
        _habitatId = habitatId;
        _habitatName = habitatName;
        _habitatArea = habitatArea;
        _arvores = new HashMap<>();
        _animals = new HashMap<>();
        _influences = new HashMap<>();
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
    

    public void setInfluence(Species species, String influence) {
        _influences.put(species.getSpeciesId(), influence);
    }




    public List<Arvore> getArvoresOrdenadas() {
        List<Arvore> listaArvores = new ArrayList<>(_arvores.values());
        
        // Ordenar primeiro alfabeticamente pelo nome, depois numericamente pelo ID
        Collections.sort(listaArvores, Comparator
                .comparing(Arvore::getArvoreId) // Altere para o método que retorna o nome da árvore
                .thenComparing(Arvore::getArvoreNome)); // Altere para o método que retorna o ID da árvore

        return listaArvores;
    }

    public HashMap<String, Arvore> getArvores() {
        return _arvores;
    }

    // Método que verifica se uma árvore já existe no habitat
    public boolean hasTree(String treeId) {
        return _arvores.containsKey(treeId); // Verifica se já existe uma árvore com o ID
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

    public void setArea(int novaArea) {
        _habitatArea = novaArea;
    }

    public HashMap<String, Animal> getAnimals() {
        return _animals;
    }

    public void removeAnimal(String animalId) {
        _animals.remove(animalId);
    }




    public int getNumAnimalsOfSameSpecies(Animal animal) {
        int count = 0;
        for (Animal a : _animals.values()) {
            if (!a.getAnimalId().equals(animal.getAnimalId()) && a.getSpecies().equals(animal.getSpecies())) {
                count++;
            }
        }
        return count;
    }

    public int getNumAnimalsOfDifferentSpecies(Animal animal) {
        int count = 0;
        for (Animal a : _animals.values()) {
            if (!a.getSpecies().equals(animal.getSpecies())) {
                count++;
            }
        }
        return count;
    }

    public int getNumAnimals() {
        return _animals.size();
    }

    public double getArea() {
        return _habitatArea;
    }

    public double getAdequacao(Animal animal) {
        return calcularSatisfacaoAnimal(animal);  // Exemplo de como conectar os cálculos
    }





    public double calcularSatisfacaoAnimal(Animal animal) {
        int mesmaEspecie = getNumAnimalsOfSameSpecies(animal);
        int especieDiferente = getNumAnimalsOfDifferentSpecies(animal);
        
        double populacao = getNumAnimals();
        if (populacao == 0) {
            return 0; // Evitar divisão por zero
        }

        double influencia = getInfluencia(animal);



        double satisfacao = 20 + 3 * mesmaEspecie - 2 * especieDiferente + (_habitatArea / populacao) + influencia;

        return satisfacao;
    }

    private double getInfluencia(Animal animal) {
        String influence = _influences.get(animal.getSpecies().getSpeciesId());
        if ("POS".equals(influence)) return 20;
        if ("NEG".equals(influence)) return -20;
        return 0;
    }



    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(String.format("HABITAT|%s|%s|%d|%d\n",
                _habitatId,
                _habitatName,
                _habitatArea,
                getNumeroDeArvores()));

        for (Arvore arvore : getArvoresOrdenadas()) {
        sb.append(arvore.toString()).append("\n");
    }

        return sb.toString();
    }
    
}    