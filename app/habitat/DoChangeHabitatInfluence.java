package hva.app.habitat;

import hva.app.exception.UnknownHabitatKeyException;
import hva.app.exception.UnknownSpeciesKeyException;
import hva.core.Animal;
import hva.core.Habitats;
import hva.core.Hotel;
import hva.core.Species;
import hva.core.exception.NoHabitatKeyException;
import hva.core.exception.NoSpeciesKeyException;
import pt.tecnico.uilib.menus.Command;
import pt.tecnico.uilib.menus.CommandException;


/**
 * Associate (positive or negatively) a species to a given habitat.
 **/
class DoChangeHabitatInfluence extends Command<Hotel> {

  DoChangeHabitatInfluence(Hotel receiver) {
    super(Label.CHANGE_HABITAT_INFLUENCE, receiver);
    addStringField("habitatId", Prompt.habitatKey()); // Identificador do habitat
    addStringField("speciesId", hva.app.animal.Prompt.speciesKey()); // Identificador da espécie
    addStringField("influence", Prompt.habitatInfluence()); // Influência do habitat
  }
  
  @Override
  protected void execute() throws CommandException {
    String habitatId = stringField("habitatId");
    String speciesId = stringField("speciesId");
    String influence = stringField("influence");

    // Validação da influência
    while (!isValidInfluence(influence)) {
        influence = Prompt.habitatInfluence(); // Repetir a pergunta se a resposta não for válida
    }

    Habitats habitat;
    Species species;

    try {
        habitat = _receiver.findHabitatById(habitatId); // Tenta encontrar o habitat
    } catch (NoHabitatKeyException e) {
        throw new UnknownHabitatKeyException(habitatId); // Lança exceção se o habitat não existir
    }

    try {
        species = _receiver.findSpeciesById(speciesId); // Tenta encontrar a espécie
    } catch (NoSpeciesKeyException e) {
        throw new UnknownSpeciesKeyException(speciesId); // Lança exceção se a espécie não existir
    }

    // Altera a influência do habitat sobre a espécie
    habitat.setInfluence(species, influence); // Certifique-se de que essa função exista

    // Aqui você pode querer recalcular a satisfação para todos os animais no habitat, se necessário
    for (Animal animal : habitat.getAnimals().values()) {
        double satisfaction = habitat.calcularSatisfacaoAnimal(animal);
        // Exibir ou processar a nova satisfação conforme necessário
    }
  }

  // Método para verificar se a influência é válida
  private boolean isValidInfluence(String influence) {
      return influence.equals("POS") || influence.equals("NEG") || influence.equals("NEU");
  }


}
