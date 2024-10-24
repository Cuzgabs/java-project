package hva.core.exception;

public class DupArvoreKeyException extends Exception {
    private String _arvoreId;

    public DupArvoreKeyException(String arvoreId) {
        super("Tree with Id: " + arvoreId + "already exists.");
        _arvoreId = arvoreId;
    }

    public String getArvoreId() {
        return _arvoreId;
    }
}
