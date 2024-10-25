package hva.app.employee;

import hva.app.exception.UnknownEmployeeKeyException;
import hva.core.Employee;
import hva.core.Hotel;
import pt.tecnico.uilib.forms.Form;
import pt.tecnico.uilib.menus.Command;
import pt.tecnico.uilib.menus.CommandException;

/**
 * Show the satisfaction of a given employee.
 **/
class DoShowSatisfactionOfEmployee extends Command<Hotel> {

  DoShowSatisfactionOfEmployee(Hotel receiver) {
    super(Label.SHOW_SATISFACTION_OF_EMPLOYEE, receiver);
  }

  @Override
  protected void execute() throws CommandException {
    // Criação do formulário para obter o identificador do funcionário
    String employeeId = Form.requestString("Identificador único do funcionário: ");

    try {
      // Obtém o funcionário a partir do ID
      Employee employee = _receiver.getEmployeeById(employeeId);

      if (employee == null) {
        throw new UnknownEmployeeKeyException(employeeId);
      }

      // Obtém a satisfação do funcionário e arredonda
      int satisfaction = Math.round(employee.getSatisfactionOfEmployee());

      // Exibe a satisfação
      _display.popup(satisfaction);

    } catch (UnknownEmployeeKeyException e) {
      // Trata a exceção caso o identificador do funcionário não seja encontrado
      _display.popup("Erro: Identificador do funcionário desconhecido.");
    }
  }
}