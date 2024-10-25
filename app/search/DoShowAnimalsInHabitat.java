package hva.app.search;

import hva.app.exception.UnknownHabitatKeyException;
import hva.core.Animal;
import hva.core.Habitats;
import hva.core.Hotel;
import hva.core.exception.NoHabitatKeyException;
import java.util.Collection;
import pt.tecnico.uilib.forms.Form;
import pt.tecnico.uilib.menus.Command;
import pt.tecnico.uilib.menus.CommandException;

/**
 * Show all animals of a given habitat.
 **/
class DoShowAnimalsInHabitat extends Command<Hotel> {

  DoShowAnimalsInHabitat(Hotel receiver) {
    super(Label.ANIMALS_IN_HABITAT, receiver);
  }

  @Override
  protected void execute() throws CommandException {
    // Solicita o identificador do habitat ao utilizador
  String habitatId = Form.requestString(hva.app.habitat.Prompt.habitatKey());

    try {
        // Verifica se o habitat existe
        Habitats habitat = _receiver.findHabitatById(habitatId); // Método correto que retorna um Habitat
        
        // Obtém todos os animais no habitat
        Collection<Animal> animals = habitat.getAnimals().values();

        // Exibe informações sobre cada animal no habitat
        for (Animal animal : animals) {
            _display.addLine(animal.toString());
        }

        // Exibe o resultado final
        _display.display();
    } catch (NoHabitatKeyException e) {
        // Se o habitat não for encontrado, lança a exceção personalizada
        throw new UnknownHabitatKeyException(habitatId);
    }
  }
}

