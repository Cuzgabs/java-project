package hva.core;

import hva.core.exception.NoEmployeeResponsibilityException;
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

    public void adicionarHabitat(String habitatId) throws NoEmployeeResponsibilityException {
        if (_habitats.contains(habitatId)) {
            throw new NoEmployeeResponsibilityException("Habitat já atribuído" + habitatId);
        }
        _habitats.add(habitatId);
    }


    public void removerHabitat(String habitatId) throws NoEmployeeResponsibilityException {
        if (!_habitats.remove(habitatId)) {
            throw new NoEmployeeResponsibilityException("Habitat não atribuído" + habitatId);
        }
        _habitats.remove(habitatId); // Remove a espécie da lista
        removeResponsibility(habitatId);
    }


    

    @Override
    public int getSatisfactionOfEmployee() {
        return _satisfacao;
    }
}