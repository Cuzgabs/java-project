package hva.core;

import java.util.List;

public class Vaccine {
    private String _vaccineId;
    private String _vaccineName;
    private String[] _speciesIds; // Agora é um array de IDs de espécies


    public Vaccine(String vaccineId, String vaccineName, String[] speciesIds) {
        _vaccineId = vaccineId;
        _vaccineName = vaccineName;
        _speciesIds = speciesIds; // Inicializa a lista de IDs das espécies


    }

    public String getVaccineId() {return _vaccineId;}
    public String getVaccineName() {return _vaccineName;}
    public String[] getSpeciesIds() {return _speciesIds;}

}
