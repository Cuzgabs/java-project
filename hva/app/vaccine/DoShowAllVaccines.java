package hva.app.vaccine;

import hva.core.Hotel;
import hva.core.Vaccine;
import java.util.Map;
import pt.tecnico.uilib.menus.Command;

/**
 * Show all vaccines.
 **/
class DoShowAllVaccines extends Command<Hotel> {

  DoShowAllVaccines(Hotel receiver) {
    super(Label.SHOW_ALL_VACCINES, receiver);
  }

  @Override
  protected final void execute() {
    Map<String, Vaccine> vaccines = _receiver.getVaccines();

    if (vaccines.isEmpty()) {
      _display.addLine("Nenhuma vacina registrada.");
    } else {
      for (Vaccine vaccine : vaccines.values()) {
        _display.addLine(vaccine.toString());
      }
    }

    _display.display();
  }
}
