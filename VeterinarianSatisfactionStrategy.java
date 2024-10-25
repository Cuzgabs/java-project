package hva.core;

import java.util.*;

public class VeterinarianSatisfactionStrategy implements SatisfactionStrategy {
    @Override
    public int calculateSatisfaction(Employee employee) {
        Veterinario vet = (Veterinario) employee;
        int sum = 0;
        List<Species> speciesList = vet.getEspecies();

        for (Species sp : speciesList) {
            int n_vets = Hotel.findAllResponsibleEmployees(Veterinario.class, sp);
            int pop = sp.getPopulation();
            sum += pop / n_vets;
        }
        return Math.round(20 - sum);
    }
} 
