package hva.app.employee;

import hva.app.exception.DuplicateEmployeeKeyException;
import hva.core.Hotel;
import hva.core.exception.DupEmployeeKeyException;
import java.util.ArrayList;
import java.util.List;
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
    while (true) {
      employeeType = Form.requestString(Prompt.employeeType());
      if (employeeType.equals("VET") | employeeType.equals("TRT")) {
        break;
      } else {
        _display.addLine("Tipo inválido. Por favor, insira 'VET' para Veterinário ou 'TRT' para Tratador.");
      }
    }

    List<String> responsibilities = new ArrayList<>();

    if (employeeType.equals("TRT")) {
      while (true) {
        String habitatId = Form.requestString("Digite o ID do habitat (ou deixe em branco para terminar):");
        if (habitatId.isEmpty()) {
          break;
        }
        responsibilities.add(habitatId);
      }
    }

    if (employeeType.equals("VET")) {
      while (true) {
        String speciesId = Form.requestString("Digite o ID da espécie (ou deixe em branco para terminar):");
        if (speciesId.isEmpty()) {
          break;
        }
        responsibilities.add(speciesId);
      }
    }

    try {
      // Regista o funcionário com base no tipo
      if (employeeType.equals("VET")) {
        _receiver.registerVeterinario(employeeId, employeeName, employeeType, responsibilities);

      } else if (employeeType.equals("TRT")) {
        _receiver.registerTratador(employeeId, employeeName, employeeType, responsibilities);
      }
    } catch (DupEmployeeKeyException e) {
      throw new DuplicateEmployeeKeyException(employeeId);
    }

    _display.addLine("Funcionário registrado com sucesso.");
    _display.display();
  }
}