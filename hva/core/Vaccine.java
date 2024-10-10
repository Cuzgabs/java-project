package hva.core;

import java.io.Serializable;
import java.util.List;

public class Vaccine implements Serializable {
    private String _vaccineId;
    private String _vaccineName;
    private List<String> _speciesIds; // Mude para List<String>
    private int _numberOfApplications; // Adicione este campo para contar aplicações


    public Vaccine(String vaccineId, String vaccineName, List<String> speciesIds) {
        _vaccineId = vaccineId;
        _vaccineName = vaccineName;
        _speciesIds = speciesIds; // Inicializa a lista de IDs das espécies
        _numberOfApplications = 0; // Inicialmente, o número de aplicações é zero


    }

    public String getVaccineId() {return _vaccineId;}
    public String getVaccineName() {return _vaccineName;}
    public List<String> getSpeciesIds() {return _speciesIds;}
    public int getNumberOfApplications() {return _numberOfApplications; }
    public void incrementApplications() {_numberOfApplications++;}
}
