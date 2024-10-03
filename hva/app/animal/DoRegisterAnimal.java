package hva.app.animal;

import hva.core.Animal;
import hva.core.Hotel;
import hva.core.Species;
import hva.app.exception.DuplicateAnimalKeyException;
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


  // Verifica se a espécie existe no hotel
  Species species = _receiver.getSpecies(speciesId);
  
   // Se a espécie não existir, cria uma nova espécie
    if (species == null) {
      String speciesName = Form.requestString(Prompt.speciesName());  // Pergunta pelo nome da espécie
      species = new Species(speciesId, speciesName);
    }

  // Verifica se o animal já existe
  if (_receiver.getAnimals().containsKey(animalId)) {
    throw new DuplicateAnimalKeyException(animalId);  // Lança exceção se o animal já estiver registrado
  }


  // Cria o novo animal e registra no hotel
  Animal animal = new Animal(animalId, animalName, species);
  _receiver.DoRegisterAnimal(animal); // Registra o animal no hotel
  }
}

