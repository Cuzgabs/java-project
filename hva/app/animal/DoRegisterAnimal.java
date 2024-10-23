package hva.app.animal;

import hva.app.exception.DuplicateAnimalKeyException;
import hva.app.exception.UnknownHabitatKeyException;
import hva.app.exception.UnknownSpeciesKeyException;
import hva.core.Hotel;
import hva.core.Species;
import hva.core.exception.DupAnimalKeyException;
import hva.core.exception.NoHabitatKeyException;
import hva.core.exception.NoSpeciesKeyException;
import pt.tecnico.uilib.forms.Form;
import pt.tecnico.uilib.menus.Command;
import pt.tecnico.uilib.menus.CommandException;

/**
 * Register a new animal in this zoo hotel.
 */
class DoRegisterAnimal extends Command<Hotel> {

  DoRegisterAnimal(Hotel receiver) {
    super(Label.REGISTER_ANIMAL, receiver);
  }

  @Override
  protected final void execute() throws CommandException {
    String animalId = Form.requestString(Prompt.animalKey());
    String animalName = Form.requestString(Prompt.animalName());
    String speciesId = Form.requestString(Prompt.speciesKey());
    String habitatId = Form.requestString(hva.app.habitat.Prompt.habitatKey());

    Species species = getOrCreateSpecies(speciesId);

    if (species == null) {
      String speciesName = Form.requestString(Prompt.speciesName());
      species = new Species(speciesId, speciesName);
    }

    try {
      _receiver.registerAnimal(animalId, animalName, habitatId, speciesId);
    } catch (DupAnimalKeyException e) {
      throw new DuplicateAnimalKeyException(animalId);
    } catch (NoHabitatKeyException e) {
      throw new UnknownHabitatKeyException(habitatId);
    } catch (NoSpeciesKeyException e) {
      throw new UnknownSpeciesKeyException(speciesId);
    }
  }

  private Species getOrCreateSpecies(String speciesId) throws CommandException {
    Species species = _receiver.getSpecies(speciesId);
    String speciesName = Form.requestString(Prompt.speciesName());
    return (species != null) ? species : new Species(speciesId, speciesName);
  }

}
