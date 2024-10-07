package hva.app.employee;

import hva.core.Employee;
import hva.core.Hotel;
import hva.app.exception.DuplicateEmployeeKeyException;
import pt.tecnico.uilib.forms.Form;
import pt.tecnico.uilib.menus.Command;
import pt.tecnico.uilib.menus.CommandException;
//FIXME add more imports if needed

/**
 * Adds a new employee to this zoo hotel.
 **/
class DoRegisterEmployee extends Command<Hotel> {

  DoRegisterEmployee(Hotel receiver) {
    super(Label.REGISTER_EMPLOYEE, receiver);
    //FIXME add command fields
  }
  
  @Override
  protected void execute() throws CommandException {
    //FIXME implement command

    String employeeId = Form.requestString(Prompt.employeeKey());
    String employeeName = Form.requestString(Prompt.employeeName());

    // Verifica se o ID do funcionário já está registrado no hotel
    if (_receiver.getEmployees().containsKey(employeeId)) {
      throw new DuplicateEmployeeKeyException(employeeId);  // Lança exceção se o funcionário já estiver registrado
    }

    // Cria um novo funcionário
    Employee employee = new Employee(employeeId, employeeName);

    // Registra o funcionário no hotel
    _receiver.registerEmployee(employee);

  }
}
