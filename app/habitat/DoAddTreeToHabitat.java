package hva.app.habitat;

import hva.app.exception.DuplicateTreeKeyException;
import hva.app.exception.UnknownHabitatKeyException;
import hva.core.Arvore;
import hva.core.Habitats; // Nova exceção
import hva.core.Hotel;
import hva.core.exception.NoHabitatKeyException;
import pt.tecnico.uilib.forms.Form;
import pt.tecnico.uilib.menus.Command;
import pt.tecnico.uilib.menus.CommandException;

/**
 * Add a new tree to a given habitat of the current zoo hotel.
 **/
class DoAddTreeToHabitat extends Command<Hotel> {

  DoAddTreeToHabitat(Hotel receiver) {
    super(Label.ADD_TREE_TO_HABITAT, receiver);
  }

  @Override
  protected void execute() throws CommandException {
    // Solicitar os dados da árvore
    String habitatId = Form.requestString(Prompt.habitatKey());
    String treeId = Form.requestString(Prompt.treeKey());
    String treeName = Form.requestString(Prompt.treeName());
    int treeAge = Form.requestInteger(Prompt.treeAge());
    int cleaningDifficulty = Form.requestInteger(Prompt.treeDifficulty());

    // Repetir até obter um tipo válido
    String leafType;
    while (true) {
      leafType = Form.requestString(Prompt.treeType());
      if (leafType.equals("PERENE") || leafType.equals("CADUCA")) {
        break;
      } else {
        _display.addLine("Tipo de árvore inválido. Deve ser 'PERENE' ou 'CADUCA'.");
      }
    }

    try {
      Habitats habitat = _receiver.findHabitatById(habitatId);

      // Verifica se já existe uma árvore com o mesmo ID no habitat
      if (habitat.hasTree(treeId)) {
        throw new DuplicateTreeKeyException(treeId); // Lança exceção se duplicada
      }

      // *Criação da árvore antes de adicionar ao habitat*
      Arvore tree = new Arvore(treeId, treeName, treeAge, leafType, cleaningDifficulty, _receiver.getCurrentSeason());
      habitat.addTree(tree);

      // Exibe os detalhes da árvore criada após adicioná-la ao habitat
      _display.addLine(tree.toString());

    } catch (NoHabitatKeyException e) {
      throw new UnknownHabitatKeyException(habitatId);
    } 

    _display.display();
  }
}