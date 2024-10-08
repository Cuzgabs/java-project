package hva.core.exception;

public class NoArvoreTypeException extends Exception {
    private String _tipoFolha;

    public NoArvoreTypeException(String tipoFolha) {
        super("Unknown species: " + tipoFolha);
        _tipoFolha = tipoFolha;
    }

    public String getArvoreId() {
        return _tipoFolha;
    }
}
