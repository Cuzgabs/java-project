package hva.app.vaccine;

import hva.app.exception.DuplicateVaccineKeyException;
import hva.app.exception.UnknownSpeciesKeyException;
import hva.core.Hotel;
import hva.core.exception.DupVaccineKeyException;
import hva.core.exception.NoSpeciesKeyException;
import java.util.List;
import pt.tecnico.uilib.forms.Form;
import pt.tecnico.uilib.menus.Command;
import pt.tecnico.uilib.menus.CommandException;

/**
 * Apply a vaccine to a given animal.
 **/
class DoRegisterVaccine extends Command<Hotel> {

  DoRegisterVaccine(Hotel receiver) {
    super(Label.REGISTER_VACCINE, receiver);
  }

  @Override
  protected final void execute() throws CommandException {

    String vaccineId = Form.requestString(Prompt.vaccineKey());
    String vaccineName = Form.requestString(Prompt.vaccineName());
    String speciesIdsInput = Form.requestString(Prompt.listOfSpeciesKeys());
    List<String> speciesIdsList = List.of(speciesIdsInput.split(","));
    String[] speciesIds = speciesIdsList.toArray(new String[0]);

    try {
      // Tenta registrar a vacina no hotel
      _receiver.registerVaccine(vaccineId, vaccineName, speciesIds);

    } catch (DupVaccineKeyException e) {
      // Lança exceção de duplicidade com a mensagem correta
      throw new DuplicateVaccineKeyException(vaccineId);

    } catch (NoSpeciesKeyException e) {
      // Lança exceção caso a espécie não seja encontrada
      throw new UnknownSpeciesKeyException(e.getMessage());
    }

    _display.display();
  }
}