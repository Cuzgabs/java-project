package hva.app.animal;

import hva.app.exception.UnknownAnimalKeyException;
import hva.core.Hotel;
import hva.core.exception.NoAnimalKey;
import pt.tecnico.uilib.forms.Form;
import pt.tecnico.uilib.menus.Command;
import pt.tecnico.uilib.menus.CommandException;

/**
 * Shows the satisfaction of a given animal.
 */
class DoShowSatisfactionOfAnimal extends Command<Hotel> {

  DoShowSatisfactionOfAnimal(Hotel receiver) {
    super(Label.SHOW_SATISFACTION_OF_ANIMAL, receiver);
  }
  
  @Override
  protected final void execute() throws CommandException {

    String animalId = Form.requestString("Identificador único do animal: ");
    double satisfaction;



    try {
        satisfaction = _receiver.getSatisfactionOfAnimal(animalId);
        // Usar o toString do animal para obter a satisfação
        _display.popup(String.valueOf(Math.round(satisfaction)));
    } catch (NoAnimalKey e) {
        throw new UnknownAnimalKeyException(e.getId());
    }
  }
}
