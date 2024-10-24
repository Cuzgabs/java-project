package hva.app.animal;

import hva.app.exception.UnknownAnimalKeyException;
import hva.core.Hotel;
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
    // Criação do formulário para obter o identificador do animal
    Form form = new Form();
    String animalId = form.requestString("Identificador do animal:");


    try {
      // Obtém a satisfação do animal
      double satisfaction = _receiver.getSatisfactionOfAnimal(animalId);
      
      // Exibe a satisfação arredondada
      _display.popup("Satisfação do animal " + animalId + ": " + Math.round(satisfaction));
    } catch (UnknownAnimalKeyException e) {
      // Trata a exceção caso o identificador do animal não seja encontrado
      _display.popup("Erro: Identificador do animal desconhecido.");
    }
  }
}
