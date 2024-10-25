package hva.core.exception;

public class NoAnimalKey extends Exception{
    private String _animalId;

    public NoAnimalKey(String animalId) {
        super("Unknown animal" + animalId);
        _animalId = animalId;
    }

    public String getId() {
        return _animalId;
    }
}
