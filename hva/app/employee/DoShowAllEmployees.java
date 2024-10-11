package hva.app.employee;

import hva.core.Employee;
import hva.core.Hotel;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import pt.tecnico.uilib.menus.Command;
import pt.tecnico.uilib.menus.CommandException;

/**
 * Show all employees of this zoo hotel.
 **/
class DoShowAllEmployees extends Command<Hotel> {

  DoShowAllEmployees(Hotel receiver) {
    super(Label.SHOW_ALL_EMPLOYEES, receiver);
  }

  @Override
  protected void execute() throws CommandException {
    Map<String, Employee> employees = _receiver.getEmployees();

    if (employees.isEmpty()) {
      _display.addLine("Nenhum funcionário registado.");
    } else {
      List<Employee> sortedEmployees = new ArrayList<>(employees.values());
      sortedEmployees.sort(Comparator.comparing(Employee::getEmployeeId));

      for (Employee employee : sortedEmployees) {
        _display.addLine(employee.toString()); // Usa o toString de Employee
      }
    }
    _display.display();
  }
}