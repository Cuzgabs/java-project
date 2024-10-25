package hva.core;

import hva.core.exception.NoEmployeeResponsibilityException;
import java.util.List;

public class Veterinario extends Employee {
    private List<Species> _especies;
    private int _satisfacao;

    public Veterinario(String employeeId, String employeeName, String employeeType, List<Species> especies) {
        super(employeeId, employeeName, employeeType);
        _especies = especies;
        _satisfacao = 20;
        _especies.addAll(especies);
    }

    public List<Species> getEspecies() {
        return _especies;
    }

    public void adicionarEspecie(Species especieId) throws NoEmployeeResponsibilityException {
        if (_especies.contains(especieId)) {
            throw new NoEmployeeResponsibilityException("Espécie já atribuída" + especieId);
        }
        _especies.add(especieId);
    }


    public void removerEspecie(Species especie) throws NoEmployeeResponsibilityException {
        System.out.println("Tentando remover: " + especie);
        System.out.println("Espécies atuais: " + _especies);
        System.out.println("Responsabilidades atuais: " + getResponsibilities());

        // Verifica se a espécie está presente
        if (!_especies.contains(especie)) {
            throw new NoEmployeeResponsibilityException("A espécie '" + especie + "' não está atribuída a este veterinário.");
        }

        // Remove a espécie
        _especies.remove(especie);
        if (getResponsibilities().contains(especie)) {
            removeResponsibility(especie);
        }

        System.out.println("Especie removida: " + especie);
        System.out.println("Espécies após remoção: " + _especies);
        System.out.println("Responsabilidades após remoção: " + getResponsibilities());
    }



    

    @Override
    public int getSatisfactionOfEmployee() {
        return _satisfacao;
    }


}