package hva.app.employee;

import hva.core.Employee;
import hva.core.Hotel;
import hva.core.exception.DupEmployeeKeyException;
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

    try {
      // Tenta registrar o funcionário no hotel
      _receiver.registerEmployee(employeeId, employeeName);
    } catch (DupEmployeeKeyException e) {
      // Lança exceção de aplicação se o funcionário já estiver registrado
      throw new DuplicateEmployeeKeyException(employeeId);
    }


    
  }
}
