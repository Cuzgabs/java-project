package hva.app.habitat;

import hva.app.exception.DuplicateHabitatKeyException;
import hva.core.Hotel;
import hva.core.exception.DupHabitatKeyException;
import pt.tecnico.uilib.forms.Form;
import pt.tecnico.uilib.menus.Command;
import pt.tecnico.uilib.menus.CommandException;

/**
 * Add a new habitat to this zoo hotel.
 **/
class DoRegisterHabitat extends Command<Hotel> {

  DoRegisterHabitat(Hotel receiver) {
    super(Label.REGISTER_HABITAT, receiver);
  }

  @Override
  protected void execute() throws CommandException {
    String habitatId = Form.requestString(Prompt.habitatKey());
    String habitatName = Form.requestString(Prompt.habitatName());
    int habitatArea = Form.requestInteger(Prompt.habitatArea());

    try {
      _receiver.registerHabitat(habitatId, habitatName, habitatArea);
    } catch (DupHabitatKeyException e) {
      throw new DuplicateHabitatKeyException(habitatId);
    }
  }
}
