package hva.core.exception;

public class DupHabitatKeyException extends Exception {
    private String _habitatId;

    public DupHabitatKeyException(String habitatId) {
        super("Habitat with Id: " + habitatId + "already exists.");
        _habitatId = habitatId;
    }

    public String getHabitatId() {
        return _habitatId;
    }
}
