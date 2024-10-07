package hva.app.employee;

import hva.core.Employee;
import hva.core.Hotel;
import pt.tecnico.uilib.menus.Command;
import pt.tecnico.uilib.menus.CommandException;
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
  protected void execute() {
    //FIXME implement command

    Map<String, Employee> employees = _receiver.getEmployees(); // Obtém o mapa de funcionários do hotel

    // Verifica se existem funcionários registrados
    if (employees.isEmpty()) {
      _display.addLine("Nenhum funcionário registrado.");
    } else {
      // Itera sobre o mapa de funcionários e exibe suas informações
      for (Employee employee : employees.values()) {
        _display.addLine(employee.getEmployeeId() + " - " + employee.getEmployeeName());
      }
    }
    
    // Exibe a lista no terminal
    _display.display();
  }
}
