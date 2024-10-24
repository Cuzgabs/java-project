package hva.core.exception;

public class NoHabitatKeyException extends Exception {
    private String _habitatId;

    public NoHabitatKeyException(String habitatId) {
        super("Unknown habitat: " + habitatId);
        _habitatId = habitatId;
    }

    public String getHabitatId() {
        return _habitatId;
    }
}
