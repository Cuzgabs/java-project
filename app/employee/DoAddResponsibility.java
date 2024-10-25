package hva.app.employee;

import hva.app.exception.NoResponsibilityException;
import hva.app.exception.UnknownEmployeeKeyException;
import hva.core.Employee;
import hva.core.Hotel;
import hva.core.Tratador;
import hva.core.Veterinario;
import hva.core.exception.NoEmployeeResponsibilityException;
import hva.core.exception.NoResponsibility;
import pt.tecnico.uilib.forms.Form;
import pt.tecnico.uilib.menus.Command;
import pt.tecnico.uilib.menus.CommandException;

/**
 * Add a new responsability to an employee: species to veterinarians and 
 * habitats to zookeepers.
 **/
class DoAddResponsibility extends Command<Hotel> {

  DoAddResponsibility(Hotel receiver) {
    super(Label.ADD_RESPONSABILITY, receiver);
  }
  
  @Override
  protected void execute() throws CommandException {
    String employeeId = Form.requestString(Prompt.employeeKey());
    String responsibilityId = Form.requestString(Prompt.responsibilityKey());

    Employee employee;
    
    employee = _receiver.getEmployeeById(employeeId);
    if (employee == null) {
        // Mensagem de erro caso o funcionário não exista
        throw new UnknownEmployeeKeyException(employeeId);
    }

    
    
      // Verificar se o funcionário é veterinário ou tratador
      if (employee instanceof Veterinario) {
        Veterinario vet = (Veterinario) employee;
        // Atribui a responsabilidade da espécie ao veterinário
        try{
          vet.addResponsibility(responsibilityId);
        } catch(NoResponsibility ne){
          throw new NoResponsibilityException(vet.getEmployeeId(), responsibilityId);
        }
        
        
      } else if (employee instanceof Tratador) {
        Tratador keeper = (Tratador) employee;
        try{
          keeper.addResponsibility(responsibilityId);
        }catch(NoResponsibility ne){
          throw new NoResponsibilityException(keeper.getEmployeeId(), responsibilityId);
        }
        
       

      } else {
        throw new NoResponsibilityException("Funcionário não qualificado", responsibilityId); // Passa dois parâmetros
      }
    
  }
}
