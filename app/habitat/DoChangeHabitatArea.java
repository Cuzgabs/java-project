package hva.app.habitat;

import hva.app.exception.UnknownHabitatKeyException;
import hva.core.Habitats;
import hva.core.Hotel;
import hva.core.exception.NoHabitatKeyException;
import pt.tecnico.uilib.menus.Command;
import pt.tecnico.uilib.menus.CommandException;

/**
 * Change the area of a given habitat.
 **/
class DoChangeHabitatArea extends Command<Hotel> {

  DoChangeHabitatArea(Hotel receiver) {
    super(Label.CHANGE_HABITAT_AREA, receiver);
    addStringField("habitatId", Prompt.habitatKey()); // Pede o identificador do habitat
    addIntegerField("novaArea", Prompt.habitatArea()); // Pede a nova área do habitat
  }
  
  @Override
  protected void execute() throws CommandException {
    String habitatId = stringField("habitatId");
    int novaArea = integerField("novaArea");

    Habitats habitat = null; // Declara a variável habitat aqui

    try {
      habitat = _receiver.findHabitatById(habitatId); // Atribui o valor ao habitat
    } catch (NoHabitatKeyException e) {
      throw new UnknownHabitatKeyException (habitatId); // Lança exceção
    }

    habitat.setArea(novaArea); // Atualiza a área do habitat
  }
}
