package hva.core;

import java.util.*;

public class VetSatisfactionFormula implements SatisfactionStrategy {
    @Override
    public int calculateSatisfaction(Employee employee,Hotel hotel) {
        Veterinario vet = (Veterinario) employee;
        int sum = 0;
        List<Species> speciesList = vet.getEspecies();

        for (Species sp : speciesList) {
            int n_vets = hotel.findAllResponsibleEmployees(sp,Veterinario.class);
           
            int pop = sp.getPopulation();
            sum += pop / n_vets;
        }
        return Math.round(20 - sum);
    }
} 
