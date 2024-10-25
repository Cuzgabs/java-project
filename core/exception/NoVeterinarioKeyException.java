package hva.core.exception;

public class NoVeterinarioKeyException extends Exception {
    private String _veterinarianId;

    public NoVeterinarioKeyException(String veterinarianId) {
        super(veterinarianId);
        _veterinarianId = veterinarianId;
    }

    public String getVeterinarioId() {
        return _veterinarianId;
    }
}