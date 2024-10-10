package hva.app.animal;

import hva.core.Animal;
import hva.core.Hotel;
import pt.tecnico.uilib.menus.Command;
import pt.tecnico.uilib.menus.CommandException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
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
      // Transforma o mapa em uma lista e ordena os animais por animalId
        List<Animal> sortedAnimals = new ArrayList<>(animals.values());
        sortedAnimals.sort(Comparator.comparing(Animal::getAnimalId));

      // Itera sobre o mapa de animais e exibe suas informações
      for (Animal animal : sortedAnimals) {
        String habitatId = (animal.getHabitat() != null) ? animal.getHabitat().getHabitatId() : "Sem habitat";
        String healthHistory = animal.getHealthHistory().isEmpty() ? "VOID" : String.join(",", animal.getHealthHistory());


        String output = String.format("ANIMAL|%s|%s|%s|%s|%s", 
                                      animal.getAnimalId(), 
                                      animal.getAnimalName(), 
                                      animal.getSpecies().getSpeciesId(), 
                                      healthHistory, 
                                      habitatId);
        _display.addLine(output);
      }
    }
    
    // Exibe a lista no terminal
    _display.display();
  }
}
