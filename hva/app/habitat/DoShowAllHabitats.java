package hva.app.habitat;

import hva.core.Habitats;
import hva.core.Hotel;
import pt.tecnico.uilib.menus.Command;
import pt.tecnico.uilib.menus.CommandException;
import java.util.Map;

//FIXME add more imports if needed

/**
 * Show all habitats of this zoo hotel.
 **/
class DoShowAllHabitats extends Command<Hotel> {

  DoShowAllHabitats(Hotel receiver) {
    super(Label.SHOW_ALL_HABITATS, receiver);
  }
  
  @Override
  protected void execute() {
    //FIXME implement command

    Map<String, Habitats> habitats = _receiver.getHabitats(); // Obtém o mapa de habitats do hotel

    // Verifica se existem habitats registrados
    if (habitats.isEmpty()) {
      _display.addLine("Nenhum habitat registado.");
    } else {
      // Itera sobre o mapa de habitats e exibe suas informações
      for (Habitats habitat : habitats.values()) {
        String output = String.format("HABITAT|%s|%s|%d|%d", 
                                      habitat.getHabitatId(), 
                                      habitat.getHabitatName(), 
                                      habitat.gethabitatArea(), 
                                      habitat.getNumeroDeArvores());
        _display.addLine(output);
      }
    }

    // Exibe a lista no terminal
    _display.display();
  }
}
