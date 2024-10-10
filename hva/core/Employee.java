package hva.core;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Employee implements Serializable {
    private String _employeeId;
    private String _employeeName;
    private List<String> _responsibilities;  // Lista para armazenar responsabilidades
    private String _employeeType;

    public Employee(String employeeId, String employeeName, String employeeType ) {
        _employeeId = employeeId;
        _employeeName = employeeName;
        _responsibilities = new ArrayList<>();  // Inicializa a lista de responsabilidades
        _employeeType = employeeType;
    }

    public String getEmployeeId() {return _employeeId;}
    public String getEmployeeName() {return _employeeName;}
    public String getEmployeeType() {return _employeeType;}
    
    // Método para adicionar responsabilidades
    public void addResponsibility(String responsibility) {
        _responsibilities.add(responsibility);
    }

    // Método para retornar as responsabilidades do funcionário
    public List<String> getResponsibilities() {
        return _responsibilities;
    }
}


