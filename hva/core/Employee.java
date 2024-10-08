package hva.core;

public class Employee {
    private String _employeeId;
    private String _employeeName;

    public Employee(String employeeId, String employeeName) {
        _employeeId = employeeId;
        _employeeName = employeeName;
    }

    public String getEmployeeId() {return _employeeId;}
    public String getEmployeeName() {return _employeeName;}

    public void addResponsibility(String responsibility) {
        responsibilities.put(responsibility, responsibility);
    }
}


