package hva.core;

import java.util.ArrayList;
import java.util.List;

public class Employee {
    private String _employeeId;
    private String _employeeName;
    private List<String> _responsibilities;  // Lista para armazenar responsabilidades


    public Employee(String employeeId, String employeeName) {
        _employeeId = employeeId;
        _employeeName = employeeName;
        _responsibilities = new ArrayList<>();  // Inicializa a lista de responsabilidades

    }

    public String getEmployeeId() {return _employeeId;}
    public String getEmployeeName() {return _employeeName;}

    // Método para adicionar responsabilidades
    public void addResponsibility(String responsibility) {
        _responsibilities.add(responsibility);
    }

    // Método para retornar as responsabilidades do funcionário
    public List<String> getResponsibilities() {
        return _responsibilities;
    }
}


