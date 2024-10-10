package hva.core;

import java.util.List;


public class Tratador extends Employee {
    private List<String> _habitats; 
    private int _satisfacao;

    public Tratador(String employeeId, String employeeName, String employeeType, List<String> habitats) {
        super(employeeId, employeeName, employeeType);
        _habitats = habitats;
        _satisfacao = 300;
    }

    public List<String> getHabitats() {
        return _habitats;
    }

    public void adicionarHabitat(String habitatId) {
        if (!_habitats.contains(habitatId)) {
            _habitats.add(habitatId);
        }
    }

    public void removerHabitat(String habitatId) {
        _habitats.remove(habitatId);
    }

    
    public void calcularSatisfacao(int area, int populacao, int esforcoLimpeza, int numeroTratadores) {
        int trabalhoNoHabitat = area + 3 * populacao + esforcoLimpeza;
        _satisfacao = 300 - (trabalhoNoHabitat / numeroTratadores);
    }

    public int getSatisfacao() {
        return _satisfacao;
    }
}