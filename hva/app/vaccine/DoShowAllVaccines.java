package hva.app.vaccine;

import hva.core.Vaccine;
import hva.core.Hotel;
import pt.tecnico.uilib.menus.Command;
import pt.tecnico.uilib.menus.CommandException;
import java.util.Map;

//FIXME add more imports if needed

/**
 * Show all vaccines.
 **/
class DoShowAllVaccines extends Command<Hotel> {

  DoShowAllVaccines(Hotel receiver) {
    super(Label.SHOW_ALL_VACCINES, receiver);
    //FIXME add command fields
  }
  
  @Override
  protected final void execute() {
    //FIXME implement command

    Map<String, Vaccine> vaccines = _receiver.getVaccines(); // Obtém o mapa de vacinas do hotel

    // Verifica se existem vacinas registradas
    if (vaccines.isEmpty()) {
      _display.addLine("Nenhuma vacina registrada.");
    } else {
      // Itera sobre o mapa de vacinas e exibe suas informações
      for (Vaccine vaccine : vaccines.values()) {
        _display.addLine(vaccine.getVaccineId() + " - " + vaccine.getVaccineName());
      }
    }
    
    // Exibe a lista no terminal
    _display.display();
  }
}
