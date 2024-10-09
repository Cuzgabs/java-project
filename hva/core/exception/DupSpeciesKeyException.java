package hva.core.exception;

public class DupSpeciesKeyException extends Exception {
    private String _speciesId;

    public DupSpeciesKeyException(String speciesId) {
        super("Species with Id: " + speciesId + "already exists.");
        _speciesId = speciesId;
    }

    public String getSpeciesId() {
        return _speciesId;
    }
}
