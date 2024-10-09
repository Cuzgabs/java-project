package hva.app.habitat;

import hva.core.Arvore;
import hva.core.Hotel;
import hva.core.Habitats;
import hva.app.exception.UnknownHabitatKeyException;
import hva.app.exception.DuplicateTreeKeyException;
import pt.tecnico.uilib.forms.Form;
import pt.tecnico.uilib.menus.Command;
import pt.tecnico.uilib.menus.CommandException;
//FIXME add more imports if needed

/**
 * Add a new tree to a given habitat of the current zoo hotel.
 **/
class DoAddTreeToHabitat extends Command<Hotel> {

  DoAddTreeToHabitat(Hotel receiver) {
    super(Label.ADD_TREE_TO_HABITAT, receiver);
    //FIXME add command fields
  }
  
  @Override
  protected void execute() throws CommandException {
    // Solicitar os dados da árvore
    String habitatId = Form.requestString(Prompt.habitatKey());
    String treeId = Form.requestString(Prompt.treeKey());
    String treeName = Form.requestString(Prompt.treeName());
    int treeAge = Form.requestInteger(Prompt.treeAge());
    String leafType = Form.requestString(Prompt.treeType()); // "Caduca" ou "Perene"
    int cleaningDifficulty = Form.requestInteger(Prompt.treeDifficulty());
    
    // Verificar se o habitat existe
    Habitats habitat = _receiver.getHabitats().get(habitatId);
    if (habitat == null) {
      throw new UnknownHabitatKeyException(habitatId);
    }
    
    // Verificar se o ID da árvore já existe no habitat
    if (habitat.getArvores().containsKey(treeId)) {
      throw new DuplicateTreeKeyException(treeId);
    }
    
    // **Criação da árvore antes de adicionar ao habitat**
    Arvore tree = new Arvore(treeId, treeName, treeAge, leafType, cleaningDifficulty, _receiver.getCurrentSeason());

    // Adicionar a árvore ao habitat
    habitat.addTree(tree);  // Supondo que existe um método addTree() em Habitats
    
    // Confirmar a adição
    _display.addLine("Árvore adicionada ao habitat|" + treeId + "|" + treeName);
    _display.display();
  }
}
