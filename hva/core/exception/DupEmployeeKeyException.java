package hva.core.exception;

public class DupEmployeeKeyException extends Exception {
   private String _employeeId;

    public DupEmployeeKeyException(String employeeId) {
        super("Employee with Id: " + employeeId + "already exists.");
        _employeeId = employeeId;
    }

    public String getEmployeeId() {
        return _employeeId;
    } 
}
