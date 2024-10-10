package hva.app.employee;

import hva.core.Employee;
import hva.core.Hotel;
import pt.tecnico.uilib.menus.Command;
import pt.tecnico.uilib.menus.CommandException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
//FIXME add more imports if needed

/**
 * Show all employees of this zoo hotel.
 **/
class DoShowAllEmployees extends Command<Hotel> {

  DoShowAllEmployees(Hotel receiver) {
    super(Label.SHOW_ALL_EMPLOYEES, receiver);
  }
  
  @Override
  protected void execute() throws CommandException {
    //FIXME implement command
    Map<String, Employee> employees = _receiver.getEmployees(); // Obtém o mapa de funcionários do hotel
  
    // Verifica se existem funcionários registrados
    if (employees.isEmpty()) {
      _display.addLine("Nenhum funcionário registado.");
    } else {
      // Transforma o mapa em uma lista e ordena os funcionários por employeeId
        List<Employee> sortedEmployees = new ArrayList<>(employees.values());
        sortedEmployees.sort(Comparator.comparing(Employee::getEmployeeId));

        // Itera sobre a lista de funcionários e exibe suas informações
      for (int i = 0; i < sortedEmployees.size(); i++) {
            Employee employee = sortedEmployees.get(i); // Obtém o funcionário atual
            String type = employee.getEmployeeType();
            String responsibilities = (employee.getResponsibilities().isEmpty()) ? "" : String.join(",", employee.getResponsibilities());

            // Formata a saída sem incluir um delimitador se não houver responsabilidades
            String output;
            if (responsibilities.isEmpty()) {
                output = String.format("%s|%s|%s", type, employee.getEmployeeId(), employee.getEmployeeName());
            } else {
                output = String.format("%s|%s|%s|%s", type, employee.getEmployeeId(), employee.getEmployeeName(), responsibilities);
            }

            _display.addLine(output);
        
      }
    }
  }
}


