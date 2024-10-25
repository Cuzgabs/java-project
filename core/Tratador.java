package hva.core;

import hva.core.exception.NoEmployeeResponsibilityException;
import hva.core.exception.NoResponsibility;

import java.util.List;
import java.util.stream.Collectors;

public class Tratador extends Employee {
    public Tratador(Hotel hotel, String employeeId, String employeeName, String employeeType, List<Responsibility> responsabilidades) {
        super(hotel, employeeId, employeeName, employeeType, responsabilidades);
    }

    // Retorna apenas responsabilidades de habitat atribuídas a este tratador
    public List<Habitats> getHabitats() {
        return getResponsibilities().stream()
                .filter(r -> r instanceof Habitats)
                .map(r -> (Habitats) r)
                .collect(Collectors.toList());
    }

  

    @Override
    public int getSatisfaction() {
        // Implementar a lógica de satisfação específica do tratador, se aplicável
        return 300; // Exemplo de valor fixo
    }
}
