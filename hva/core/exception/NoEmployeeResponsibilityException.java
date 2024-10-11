package hva.core.exception;

public class NoEmployeeResponsibilityException extends Exception {
    private String _employeeResponsibility;

    public NoEmployeeResponsibilityException(String employeeResponsibility) {
        super("Unknown employee: " + employeeResponsibility);
        _employeeResponsibility = employeeResponsibility;
    }

    public String getEmployeeResponsibility() {
        return _employeeResponsibility;
    }
}
