package hva.core;

import java.util.HashMap;

public class Habitats {
    private String _habitatId;
    private String _habitatName;
    private int _habitatArea;
    private HashMap<String, Arvore> _arvores;  // Mapa de árvores associadas ao habitat
    private HashMap<String, Animal> _animals; // Mapa de animais associadas ao habitat

    public Habitats (String habitatId, String habitatName, int habitatArea) {
        _habitatId = habitatId;
        _habitatName = habitatName;
        _habitatArea = habitatArea;
        _arvores = new HashMap<>();  // Inicializa o mapa de árvores
        _animals = new HashMap<>(); // Inicializa o mapa de animais
    }

    public int gethabitatArea() {return _habitatArea;}
    public String getHabitatId() {return _habitatId;}
    public String getHabitatName() {return _habitatName;}
    public int getNumeroDeArvores() {return _arvores.size();}
    public HashMap<String, Arvore> getArvores() {return _arvores;}
    public void addTree(Arvore tree) {_arvores.put(tree.getArvoreId(), tree);}
    public void addAnimal(Animal animal) { _animals.put(animal.getId(), animal); } // Método para adicionar animais

}
