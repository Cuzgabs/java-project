package hva.app.animal;

import hva.core.Hotel;
import hva.core.Animal;
import hva.app.exception.UnknownAnimalKeyException;
import hva.app.exception.UnknownHabitatKeyException;
import hva.core.exception.NoHabitatKeyException;
import pt.tecnico.uilib.menus.Command;
import pt.tecnico.uilib.menus.CommandException;
import pt.tecnico.uilib.forms.Form;

/**
 * Transfers a given animal to a new habitat of this zoo hotel.
 */
class DoTransferToHabitat extends Command<Hotel> {

  DoTransferToHabitat(Hotel hotel) {
    super(Label.TRANSFER_ANIMAL_TO_HABITAT, hotel);
  }
  
  @Override
  protected final void execute() throws CommandException {
    String animalId = Form.requestString(Prompt.animalKey());
    String habitatId = Form.requestString(hva.app.habitat.Prompt.habitatKey());

    // Verifica se o animal existe
    Animal animal = _receiver.getAnimal(animalId);
    if (animal == null) {
        throw new UnknownAnimalKeyException(animalId);
    }

    // Verifica se o habitat existe
    if (!_receiver.existsHabitat(habitatId)) {
        throw new UnknownHabitatKeyException(habitatId);
    }

    // Transfere o animal para o novo habitat
    try {
        _receiver.transferAnimalToHabitat(animalId, habitatId);
    } catch (NoHabitatKeyException e) {
        // Esta exceção não deve ocorrer, pois já verificamos a existência do habitat
        throw new UnknownHabitatKeyException("Erro ao transferir animal: habitat não encontrado.");
    }
  }
}
