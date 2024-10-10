package hva.app.employee;

import hva.core.Hotel;
import hva.core.exception.DupEmployeeKeyException;
import hva.app.exception.DuplicateEmployeeKeyException;
import pt.tecnico.uilib.forms.Form;
import pt.tecnico.uilib.menus.Command;
import pt.tecnico.uilib.menus.CommandException;
import java.util.List;
import java.util.ArrayList;

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
    
    String employeeType;
    while (true) {
      employeeType = Form.requestString(Prompt.employeeType()); // "VET" ou "TRT"
      if (employeeType.equals("VET") | employeeType.equals("TRT")) {
        break; // Tipo válido, saia do loop
      } else {
        _display.addLine("Tipo inválido. Por favor, insira 'VET' para Veterinário ou 'TRT' para Tratador.");
      }
    }
      
    List<String> responsibilities = new ArrayList<>();


    // Para Tratador, peça os habitats que ele pode limpar
    if (employeeType.equals("TRT")) {
      while (true) {
        // Aqui você pode usar outro método para coletar o ID do habitat
        String habitatId = Form.requestString("Digite o ID do habitat (ou deixe em branco para terminar):");
        if (habitatId.isEmpty()) {
          break; // Se o usuário não digitar nada, saia do loop
        }
        responsibilities.add(habitatId); // Adiciona o habitat à lista de responsabilidades
      }
    }


    // Para Veterinário, peça as espécies que ele pode tratar
    if (employeeType.equals("VET")) {
      while (true) {
        String speciesId = Form.requestString("Digite o ID da espécie (ou deixe em branco para terminar):");
        if (speciesId.isEmpty()) {
          break; // Se o usuário não digitar nada, saia do loop
        }
        responsibilities.add(speciesId); // Adiciona a espécie à lista de responsabilidades
      }
    }


    try {
      // Registra o funcionário com base no tipo
      if (employeeType.equals("VET")) {
        _receiver.registerVeterinario(employeeId, employeeName, employeeType, responsibilities); // Adapte para a função correta
      } else if (employeeType.equals("TRT")) {
        _receiver.registerTratador(employeeId, employeeName, employeeType, responsibilities); // Adapte para a função correta
      }
    } catch (DupEmployeeKeyException e) {
      throw new DuplicateEmployeeKeyException(employeeId);
    }

    _display.addLine("Funcionário registrado com sucesso.");
    _display.display(); // Exibe a mensagem
  }
}