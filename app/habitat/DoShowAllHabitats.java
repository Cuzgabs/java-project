package hva.app.habitat;

import hva.core.Habitats;
import hva.core.Hotel;
import java.util.ArrayList;
import pt.tecnico.uilib.menus.Command;
import pt.tecnico.uilib.menus.CommandException;

/**
 * Show all habitats of this zoo hotel.
 **/
class DoShowAllHabitats extends Command<Hotel> {

  DoShowAllHabitats(Hotel receiver) {
    super(Label.SHOW_ALL_HABITATS, receiver);
  }

  @Override
  protected void execute() throws CommandException {

    ArrayList<Habitats> habitats = _receiver.getOrderedHabitats();

    // Exibe todos os habitats (sem verificar se a lista está vazia)
    for (Habitats habitat : habitats) {
      _display.addLine(habitat.toString().trim());  // Certifica que não há espaços desnecessários
    }

    _display.display();
  }
}
