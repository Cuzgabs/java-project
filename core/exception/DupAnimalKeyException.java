package hva.core.exception;

public class DupAnimalKeyException extends Exception {
    private String _animalId;

    public DupAnimalKeyException(String animalId) {
        super("Animal with Id: " + animalId + "already exists.");
        _animalId = animalId;
    }

    public String getAnimalId() {
        return _animalId;
    }
}
