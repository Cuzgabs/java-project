package hva.core.exception;

public class NoResponsibility extends Exception {
    private String _id;

    public NoResponsibility(String id) {
        super("No Responsibility " + id);
        _id = id;
    }

    public String getId() {
    return _id;
    }
}
