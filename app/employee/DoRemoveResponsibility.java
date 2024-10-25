package hva.app.employee;

import hva.app.exception.NoResponsibilityException;
import hva.app.exception.UnknownEmployeeKeyException;
import hva.core.Employee;
import hva.core.Hotel;
import hva.core.Tratador;
import hva.core.Veterinario;
import hva.core.exception.NoEmployeeResponsibilityException;
import pt.tecnico.uilib.forms.Form;
import pt.tecnico.uilib.menus.Command;
import pt.tecnico.uilib.menus.CommandException;

/**
 * Remove a given responsability from a given employee of this zoo hotel.
 **/
class DoRemoveResponsibility extends Command<Hotel> {

  DoRemoveResponsibility(Hotel receiver) {
    super(Label.REMOVE_RESPONSABILITY, receiver);
  }
  
  @Override
  protected void execute() throws CommandException {
    String employeeId = Form.requestString(Prompt.employeeKey());
    String responsibilityId = Form.requestString(Prompt.responsibilityKey());

    Employee employee = _receiver.getEmployeeById(employeeId);
    if (employee == null) {
        throw new UnknownEmployeeKeyException("Funcionário não encontrado: " + employeeId);
    }

    try {
      // Verificar se o funcionário é veterinário ou tratador
      if (employee instanceof Veterinario) {
        Veterinario vet = (Veterinario) employee;
        vet.removerEspecie(responsibilityId);

      } else if (employee instanceof Tratador) {
        Tratador keeper = (Tratador) employee;
        keeper.removerHabitat(responsibilityId);
      } else {
        throw new NoResponsibilityException("Funcionário não qualificado", responsibilityId);
      }
    } catch (NoEmployeeResponsibilityException e) {
        throw new NoResponsibilityException("Responsabilidade não atribuída", responsibilityId);
    }

  }
}
