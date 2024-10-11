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

    if (habitats.isEmpty()) {
      _display.addLine("Nenhum habitat registado.");
    } else {
      for (Habitats habitat : habitats) {
        _display.addLine(habitat.toString());
      }
    }

    _display.display();
  }
}
