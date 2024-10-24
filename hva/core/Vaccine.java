package hva.core;

import java.io.Serializable;
import java.util.List;
import java.util.Collections;

public class Vaccine implements Serializable {
    private String _vaccineId;
    private String _vaccineName;
    private List<String> _speciesIds;
    private int _numberOfApplications;

    public Vaccine(String vaccineId, String vaccineName, List<String> speciesIds) {
        _vaccineId = vaccineId;
        _vaccineName = vaccineName;
        _speciesIds = speciesIds;
        _numberOfApplications = 0;

    }

    public String getVaccineId() {
        return _vaccineId;
    }

    public String getVaccineName() {
        return _vaccineName;
    }

    public List<String> getSpeciesIds() {
        return _speciesIds;
    }

    public int getNumberOfApplications() {
        return _numberOfApplications;
    }

    public void incrementApplications() {
        _numberOfApplications++;
    }

    public void sortSpecies() {
        Collections.sort(_speciesIds);
    }

    @Override
    public String toString() {
        if (_speciesIds.isEmpty()) {
            return String.format("VACINA|%s|%s|%d", _vaccineId, _vaccineName, _numberOfApplications);
        } else {
            String species = String.join(",", _speciesIds);
            return String.format("VACINA|%s|%s|%d|%s", _vaccineId, _vaccineName, _numberOfApplications, species);
        }
    }
}
