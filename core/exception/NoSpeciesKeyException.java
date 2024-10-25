package hva.core.exception;

public class NoSpeciesKeyException extends Exception {
    private String _speciesId;

    public NoSpeciesKeyException(String speciesId) {
        super(speciesId);
        _speciesId = speciesId;
    }

    public String getSpeciesId() {
        return _speciesId;
    }
}