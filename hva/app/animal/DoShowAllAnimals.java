package hva.app.animal;

import hva.core.Animal;
import hva.core.Hotel;
import pt.tecnico.uilib.menus.Command;
import pt.tecnico.uilib.menus.CommandException;
import java.util.Map;

/**
 * Show all animals registered in this zoo hotel.
 */
class DoShowAllAnimals extends Command<Hotel> {

  DoShowAllAnimals(Hotel receiver) {
    super(Label.SHOW_ALL_ANIMALS, receiver);
  }

  @Override
  protected final void execute() throws CommandException {
    Map<String, Animal> animals = _receiver.getAnimals(); // Obtém o mapa de animais do hotel

    // Verifica se existem animais registrados
    if (animals.isEmpty()) {
      _display.addLine("Nenhum animal registado.");
    } else {
      // Itera sobre o mapa de animais e exibe suas informações
      for (Animal animal : animals.values()) {
        _display.addLine(animal.getId() + " - " + animal.getName() + " (" + animal.getSpecies().getName() + ")");
      }
    }
    
    // Exibe a lista no terminal
    _display.display();
  }
}
