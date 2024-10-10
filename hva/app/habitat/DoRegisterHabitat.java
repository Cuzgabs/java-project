package hva.app.habitat;

import hva.core.Hotel;
import hva.core.exception.DupHabitatKeyException;
import hva.app.exception.DuplicateHabitatKeyException;
import pt.tecnico.uilib.forms.Form;
import pt.tecnico.uilib.menus.Command;
import pt.tecnico.uilib.menus.CommandException;
//FIXME add more imports if needed

/**
 * Add a new habitat to this zoo hotel.
 **/
class DoRegisterHabitat extends Command<Hotel> {

  DoRegisterHabitat(Hotel receiver) {
    super(Label.REGISTER_HABITAT, receiver);
    //FIXME add command fields
  }
  
  @Override
  protected void execute() throws CommandException {
    //FIXME implement command

     // Solicita os dados do habitat
    String habitatId = Form.requestString(Prompt.habitatKey());
    String habitatName = Form.requestString(Prompt.habitatName());
    int habitatArea = Form.requestInteger(Prompt.habitatArea());

     try {
      // Tenta registrar o habitat no hotel
      _receiver.registerHabitat(habitatId, habitatName, habitatArea);
    } catch (DupHabitatKeyException e) {
      // Lança exceção de aplicação se o habitat já estiver registrado
      throw new DuplicateHabitatKeyException(habitatId);
    }
    
     // Confirma o registro do habitat
    _display.addLine("HABITAT|" + habitatId + "|" + habitatName + "|" + habitatArea + "|0");
    _display.display();
  }
}
