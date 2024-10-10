package hva.core.exception;

public class NoSpeciesKeyException extends Exception {
    private String _SpeciesId;

    public NoSpeciesKeyException(String speciesId) {
        super("Unknown species: " + speciesId);
        _SpeciesId = speciesId;
    }

    public String getSpeciesId() {
        return _SpeciesId;
    }
}