package hva.app.animal;

import hva.core.Hotel;
import hva.core.Species;
import hva.core.exception.DupAnimalKeyException;
import hva.core.exception.NoHabitatKeyException;
import hva.core.exception.NoSpeciesKeyException;
import hva.app.exception.DuplicateAnimalKeyException;
import hva.app.exception.UnknownHabitatKeyException;
import hva.app.exception.UnknownSpeciesKeyException;
import pt.tecnico.uilib.forms.Form;
import pt.tecnico.uilib.menus.Command;
import pt.tecnico.uilib.menus.CommandException;
//FIXME add more imports if needed

/**
 * Register a new animal in this zoo hotel.
 */
class DoRegisterAnimal extends Command<Hotel> {

  DoRegisterAnimal(Hotel receiver) {
    super(Label.REGISTER_ANIMAL, receiver);
    //FIXME add command fields
  }
  
  @Override
  protected final void execute() throws CommandException {
    //FIXME implement command
    String animalId = Form.requestString(Prompt.animalKey());
    String animalName = Form.requestString(Prompt.animalName());
    String speciesId = Form.requestString(Prompt.speciesKey());
    String habitatId = Form.requestString(hva.app.habitat.Prompt.habitatKey());



  // Verifica se a espécie existe no hotel
  Species species = _receiver.getSpecies(speciesId);
  /*
   * try {
   * adicionar animal
   * catch excepcao 1 do core - duplicate id do core
   *  throw excepcao da app - throw new DuplicateAnimalKeyException(animalId);
   * catch excepcao 2 do core
   * throw excepcao da app
   * }
   */
   // Se a espécie não existir, cria uma nova espécie
    if (species == null) {
      String speciesName = Form.requestString(Prompt.speciesName());  // Pergunta pelo nome da espécie
      species = new Species(speciesId, speciesName);
    }

  // Verifica se o animal já existe
  if (_receiver.getAnimals().containsKey(animalId)) {
    throw new DuplicateAnimalKeyException(animalId);  
  }



  try {
      // Tenta registrar o animal e captura exceções relevantes
      _receiver.registerAnimal(animalId, animalName, habitatId, speciesId);
    } catch (DupAnimalKeyException e) {
      throw new DuplicateAnimalKeyException(animalId); // Traduz para uma exceção do comando
    } catch (NoHabitatKeyException e) {
      throw new UnknownHabitatKeyException(habitatId); // Traduz para uma exceção do comando
    } catch (NoSpeciesKeyException e) {
      throw new UnknownSpeciesKeyException(speciesId); // Traduz para uma exceção do comando
    }  
  }
}

