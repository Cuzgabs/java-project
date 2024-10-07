package hva.core;

import java.util.HashMap;

public class Habitats {
    private String _habitatId;
    private String _habitatName;
    private int _habitatArea;
    private HashMap<String, Arvore> _arvores;  // Mapa de árvores associadas ao habitat


    public Habitats (String habitatId, String habitatName, int habitatArea) {
        _habitatId = habitatId;
        _habitatName = habitatName;
        _habitatArea = habitatArea;
        _arvores = new HashMap<>();  // Inicializa o mapa de árvores

    }

    public int gethabitatArea() {return _habitatArea;}
    public String getHabitatId() {return _habitatId;}
    public String getHabitatName() {return _habitatName;}
    public int getNumeroDeArvores() {return _arvores.size();}
    public HashMap<String, Arvore> getArvores() {return _arvores;}
    public void addTree(Arvore tree) {_arvores.put(tree.getArvoreId(), tree);}
}
