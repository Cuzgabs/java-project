package hva.app.vaccine;

import hva.core.Vaccine;
import hva.core.Hotel;
import pt.tecnico.uilib.menus.Command;
import pt.tecnico.uilib.menus.CommandException;
import java.util.Map;
import java.util.List;


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
        String output;
        List<String> speciesIds = vaccine.getSpeciesIds(); // Obtém as espécies associadas

        if (speciesIds.isEmpty()) {
          // Caso não haja espécies, não exibe a barra vertical após o número de aplicações
          output = String.format("VACINA|%s|%s|%d", 
                                 vaccine.getVaccineId(), 
                                 vaccine.getVaccineName(), 
                                 vaccine.getNumberOfApplications());
        } else {
          // Exibe normalmente quando há espécies
          String species = String.join(",", speciesIds);
          output = String.format("VACINA|%s|%s|%d|%s", 
                                 vaccine.getVaccineId(), 
                                 vaccine.getVaccineName(), 
                                 vaccine.getNumberOfApplications(), 
                                 species);
        }

        _display.addLine(output);
      }
    }

    // Exibe a lista no terminal
    _display.display();
  }
}
