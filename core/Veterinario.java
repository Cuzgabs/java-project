package hva.core;

import hva.core.exception.NoEmployeeResponsibilityException;
import hva.core.exception.NoResponsibility;

import java.util.List;
import java.util.stream.Collectors;

public class Veterinario extends Employee {
    public Veterinario(Hotel hotel, String employeeId, String employeeName, String employeeType,List<Responsibility> responsabilidades) {
        super(hotel, employeeId, employeeName, employeeType,responsabilidades);
    }

    // Retrieves only species responsibilities assigned to this veterinarian
    public List<Species> getEspecies() {
        return getResponsibilities().stream()
                .filter(r -> r instanceof Species)
                .map(r -> (Species) r)
                .collect(Collectors.toList());
    }

 

}