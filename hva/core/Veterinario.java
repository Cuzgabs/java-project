package hva.core;

import java.util.List;

public class Veterinario extends Employee {
    private List<String> _especies;  
    private int _satisfacao;

    public Veterinario(String employeeId, String employeeName, String employeeType, List<String> especies) {
        super(employeeId, employeeName, employeeType);
        _especies = especies;
        _satisfacao = 20;  
    }


    public List<String> getEspecies() {
        return _especies;
    }

    
    public void adicionarEspecie(String especieId) {
        if (!_especies.contains(especieId)) {
            _especies.add(especieId);
        }
    }


    public void removerEspecie(String especieId) {
        _especies.remove(especieId);
    }


    public void calcularSatisfacao(int populacao, int numeroVeterinarios) {
        _satisfacao = 20 - (populacao / numeroVeterinarios);
    }

    public int getSatisfacao() {
        return _satisfacao;
    }
}