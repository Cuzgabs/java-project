package hva.app.vaccine;

import hva.core.Vaccine;
import hva.core.exception.DupVaccineKeyException;
import hva.core.exception.NoSpeciesKeyException;
import hva.core.Hotel;
import hva.app.exception.UnknownSpeciesKeyException;

import java.util.Arrays;
import java.util.List;

import hva.app.exception.DuplicateVaccineKeyException;
import pt.tecnico.uilib.forms.Form;
import pt.tecnico.uilib.menus.Command;
import pt.tecnico.uilib.menus.CommandException;
//FIXME add more imports if needed

/**
 * Apply a vaccine to a given animal.
 **/
class DoRegisterVaccine extends Command<Hotel> {

  DoRegisterVaccine(Hotel receiver) {
    super(Label.REGISTER_VACCINE, receiver);
    //FIXME add command fields
  }

  @Override
  protected final void execute() throws CommandException {
    //FIXME implement command

    // Solicita os dados da vacina
    String vaccineId = Form.requestString(Prompt.vaccineKey());
    String vaccineName = Form.requestString(Prompt.vaccineName());
    String speciesIdsInput = Form.requestString(Prompt.listOfSpeciesKeys());
    String[] speciesIds = speciesIdsInput.split(","); // Supondo que os IDs são separados por vírgula


    // Verifica se o ID da vacina já está registrado no hotel
    if (_receiver.getVaccines().containsKey(vaccineId)) {
      throw new DuplicateVaccineKeyException(vaccineId);  // Lança exceção se a vacina já estiver registrada
    }

    // Cria uma nova vacina
    Vaccine vaccine = new Vaccine(vaccineId, vaccineName, speciesIds);

  try {
    // Registra a vacina no hotel
    _receiver.registerVaccine(vaccineId, vaccineName, speciesIds);
  } catch (DupVaccineKeyException e) {
      throw new DuplicateVaccineKeyException(vaccineId); // Exceção específica da aplicação
  } catch (NoSpeciesKeyException e) {
      throw new UnknownSpeciesKeyException(e.getMessage()); // Exceção específica da aplicação
  }    
    // Confirma o registro
    _display.display();
  }
}
