package hva.core;

public class Vaccine {
    private String _vaccineId;
    private String _vaccineName;

    public Vaccine(String vaccineId, String vaccineName) {
        _vaccineId = vaccineId;
        _vaccineName = vaccineName;
    }

    public String getVaccineId() {return _vaccineId;}
    public String getVaccineName() {return _vaccineName;}

}
