package hva.core;

import java.io.Serializable;
import java.util.*;
import java.util.stream.Collectors;

import hva.app.exception.NoResponsibilityException;
import hva.core.Habitats;
import hva.core.Species;
import hva.core.exception.NoHabitatKeyException;
import hva.core.exception.NoResponsibility;
import hva.core.exception.NoSpeciesKeyException;

public abstract class Employee implements Serializable {
    private String _employeeId;
    private String _employeeName;
    private HashMap<String, Responsibility> _responsibilities;
    private String _employeeType;
    private SatisfactionStrategy _satisfactionStrategy;
    private Hotel _hotel;

    public Employee(Hotel hotel, String employeeId, String employeeName, String employeeType, List<Responsibility> responsibilities) {
        _employeeId = employeeId;
        _employeeName = employeeName;
        _employeeType = employeeType;
        _hotel = hotel;
        _responsibilities = new HashMap<>(0);
    
        // Populate the _responsibilities map using each Responsibility's ID as the key
        for (Responsibility responsibility : responsibilities) {
            _responsibilities.put(responsibility.getResponsibilityId(), responsibility);
        }
    }

    public String getEmployeeId() {
        return _employeeId;
    }

    public String getEmployeeName() {
        return _employeeName;
    }

    public String getEmployeeType() {
        return _employeeType;
    }
    public static HashMap<String, Species> mapAnimalsToSpecies(HashMap<String, Animal> animals) {
        HashMap<String, Species> speciesMap = new HashMap<>();
        
        for (Map.Entry<String, Animal> entry : animals.entrySet()) {
            String key = entry.getKey();
            Animal animal = entry.getValue();
            speciesMap.put(key, animal.getSpecies());
        }
    
    return speciesMap;
    }
    public static HashMap<String, Habitats> mapHabitatsById(ArrayList<Habitats> habitats) {
        HashMap<String, Habitats> habitatMap = new HashMap<>();
    
        for (Habitats habitat : habitats) {
            habitatMap.put(habitat.getHabitatId(), habitat);
        }
    
        return habitatMap;
    }

    public void addResponsibility(String responsabId)throws NoResponsibility {//falta excepcoes
        if(_responsibilities.containsKey(responsabId)){return;}//excepcao de ja existe responsability
        Responsibility r;
        r = _hotel.getHabitatById(responsabId);
        if(r == null){
            r = _hotel.getSpeciesById(responsabId);
        }
        
        _responsibilities.put(r.getResponsibilityId(),r);
        
    }

    public void removeResponsibility(String responsabId) {
        if(_responsibilities.containsKey(responsabId)){_responsibilities.remove(responsabId);}//excepcao tb
        
    }

    public List<Responsibility> getResponsibilities() {
        List<Responsibility> sortedList = new ArrayList<>(_responsibilities.values());
        Collections.sort(sortedList, Comparator.comparing(r -> r.getResponsibilityId().toLowerCase()));
        return Collections.unmodifiableList(sortedList);

    }


    public int getSatisfaction() {
        if (_satisfactionStrategy != null) {
            return _satisfactionStrategy.calculateSatisfaction(this,_hotel);
        }
        throw new IllegalStateException("Satisfaction strategy not set");
    }

    public void setSatisfactionStrategy(SatisfactionStrategy strategy) {
        _satisfactionStrategy = strategy;
    }



    @Override
    public String toString() {
        // Concatenate the IDs of each responsibility with a comma between them
        String responsibilities = _responsibilities.isEmpty() 
            ? "" 
            : String.join(",", getResponsibilities().stream()
                                                .map(Responsibility::getResponsibilityId)
                                                .collect(Collectors.toList()));

        // If the employee has no responsibilities, omit the field
        if (responsibilities.isEmpty()) {
            return String.format("%s|%s|%s", _employeeType, _employeeId, _employeeName);
        } else {
            return String.format("%s|%s|%s|%s", _employeeType, _employeeId, _employeeName, responsibilities);
        }
    }
}
