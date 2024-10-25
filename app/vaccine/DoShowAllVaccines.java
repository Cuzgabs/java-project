package hva.app.vaccine;

import hva.core.Hotel;
import hva.core.Vaccine;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
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

    // Ordena as vacinas por ID, ignorando maiúsculas e minúsculas
    List<Vaccine> sortedVaccines = new ArrayList<>(vaccines.values());
    sortedVaccines.sort(Comparator.comparing(v -> v.getVaccineId().toLowerCase()));

    // Exibe as vacinas ordenadas
    for (Vaccine vaccine : sortedVaccines) {
      vaccine.sortSpecies();
      _display.addLine(vaccine.toString());
    }

    _display.display();
  }
}
