package hva.app.habitat;

import hva.app.exception.UnknownHabitatKeyException;
import hva.core.Arvore;
import hva.core.Habitats;
import hva.core.Hotel;
import hva.core.exception.NoHabitatKeyException;
import java.util.List;
import pt.tecnico.uilib.forms.Form;
import pt.tecnico.uilib.menus.Command;
import pt.tecnico.uilib.menus.CommandException;

/**
 * Show all trees in a given habitat.
 **/
class DoShowAllTreesInHabitat extends Command<Hotel> {

  DoShowAllTreesInHabitat(Hotel receiver) {
    super(Label.SHOW_TREES_IN_HABITAT, receiver);
  }

  @Override
  protected void execute() throws CommandException {
    // Solicitar o ID do habitat
    String habitatId = Form.requestString(Prompt.habitatKey());

    try {
      // Buscar o habitat com o ID fornecido
      Habitats habitat = _receiver.findHabitatById(habitatId);

      // Obter a lista de árvores ordenadas
      List<Arvore> arvores = habitat.getArvoresOrdenadas();

      // Exibir as árvores no formato especificado
      for (Arvore arvore : arvores) {
        _display.addLine(arvore.toString()); // toString de Arvore deve estar no formato correto
      }

    } catch (NoHabitatKeyException e) {
      // Se o habitat não for encontrado, lançar exceção de habitat desconhecido
      throw new UnknownHabitatKeyException(habitatId);
    }

    _display.display();
  }
}