package hva.core;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public abstract class Employee implements Serializable {
    private String _employeeId;
    private String _employeeName;
    private List<String> _responsibilities;
    private String _employeeType;

    public Employee(String employeeId, String employeeName, String employeeType) {
        _employeeId = employeeId;
        _employeeName = employeeName;
        _responsibilities = new ArrayList<>();
        _employeeType = employeeType;
    }

    public String getEmployeeId() {
        return _employeeId;
    }

    public String getEmployeeName() {
        return _employeeName;
    }

    public String getEmployeeType() {
        return _employeeType;
    }

    public void addResponsibility(String responsibility) {
        _responsibilities.add(responsibility);
    }

    public List<String> getResponsibilities() {
        return _responsibilities;
    }

    public abstract int getSatisfactionOfEmployee();


    @Override
    public String toString() {
        String responsibilities = _responsibilities.isEmpty() ? "" : String.join(",", _responsibilities);

        // Se o funcionário não tiver responsabilidades, omite o campo
        if (responsibilities.isEmpty()) {
            return String.format("%s|%s|%s", _employeeType, _employeeId, _employeeName);
        } else {
            return String.format("%s|%s|%s|%s", _employeeType, _employeeId, _employeeName, responsibilities);
        }
    }
}
