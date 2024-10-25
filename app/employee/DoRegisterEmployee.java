package hva.app.employee;

import hva.app.exception.DuplicateEmployeeKeyException;
import hva.core.Hotel;
import hva.core.exception.DupEmployeeKeyException;
import pt.tecnico.uilib.forms.Form;
import pt.tecnico.uilib.menus.Command;
import pt.tecnico.uilib.menus.CommandException;

/**
 * Adds a new employee to this zoo hotel.
 **/
class DoRegisterEmployee extends Command<Hotel> {

  DoRegisterEmployee(Hotel receiver) {
    super(Label.REGISTER_EMPLOYEE, receiver);
  }

  @Override
  protected void execute() throws CommandException {

    String employeeId = Form.requestString(Prompt.employeeKey());
    String employeeName = Form.requestString(Prompt.employeeName());

    String employeeType;
    // Solicita o tipo do funcionário e valida
    while (true) {
      employeeType = Form.requestString(Prompt.employeeType());
      if (employeeType.equals("VET") || employeeType.equals("TRT")) {
        break;
      } else {
        _display.addLine("Tipo inválido. Por favor, insira 'VET' para Veterinário ou 'TRT' para Tratador.");
      }
    }

    try {
      // Registra o funcionário conforme o tipo (VET ou TRT)
      if (employeeType.equals("VET")) {
        _receiver.registerVeterinario(employeeId, employeeName, employeeType, null);  // Sem responsabilidades iniciais
      } else if (employeeType.equals("TRT")) {
        _receiver.registerTratador(employeeId, employeeName, employeeType, null);  // Sem responsabilidades iniciais
      }
    } catch (DupEmployeeKeyException e) {
      throw new DuplicateEmployeeKeyException(employeeId);
    }

    _display.display();
  }
}