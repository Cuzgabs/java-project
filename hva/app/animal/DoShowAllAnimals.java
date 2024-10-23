package hva.app.animal;

import hva.core.Animal;
import hva.core.Hotel;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import pt.tecnico.uilib.menus.Command;
import pt.tecnico.uilib.menus.CommandException;

/**
 * Show all animals registered in this zoo hotel.
 */
class DoShowAllAnimals extends Command<Hotel> {

  DoShowAllAnimals(Hotel receiver) {
    super(Label.SHOW_ALL_ANIMALS, receiver);
  }

  @Override
  protected final void execute() throws CommandException {
    Map<String, Animal> animals = _receiver.getAnimals();

    // Ordena e exibe todos os animais, sem verificar se a lista está vazia
    List<Animal> sortedAnimals = new ArrayList<>(animals.values());
    sortedAnimals.sort(Comparator.comparing(Animal::getAnimalId));

    for (Animal animal : sortedAnimals) {
      _display.addLine(animal.toString());
    }

    _display.display();
  }
}
