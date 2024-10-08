package hva.core.exception;

public class NoArvoreKeyException extends Exception {
    private String _arvoreId;

    public NoArvoreKeyException(String arvoreId) {
        super("Unknown arvore: " + arvoreId);
        _arvoreId = arvoreId;
    }

    public String getHabitatId() {
        return _arvoreId;
    }
}
