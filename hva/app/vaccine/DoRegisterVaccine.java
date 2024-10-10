package hva.app.vaccine;

import hva.core.exception.DupVaccineKeyException;
import hva.core.exception.NoSpeciesKeyException;
import hva.core.Hotel;
import hva.app.exception.UnknownSpeciesKeyException;
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
    List<String> speciesIdsList = List.of(speciesIdsInput.split(",")); // Cria uma lista a partir do input
    String[] speciesIds = speciesIdsList.toArray(new String[0]);  // Converte para String[]


    try {
      // Verifica se o ID da vacina já está registrado no hotel
      if (_receiver.getVaccines().containsKey(vaccineId)) {
        throw new DuplicateVaccineKeyException(vaccineId); // Lança exceção se a vacina já estiver registrada
      }

      // Cria uma nova vacina
      _receiver.registerVaccine(vaccineId, vaccineName, speciesIds); // Adiciona ao hotel
      
      // Confirma o registro
      _display.addLine("Vacina registrada com sucesso.");
    } catch (DupVaccineKeyException e) {
      throw new DuplicateVaccineKeyException(vaccineId); // Exceção específica da aplicação
    } catch (NoSpeciesKeyException e) {
      throw new UnknownSpeciesKeyException(e.getMessage()); // Exceção específica da aplicação
    }
    
    _display.display(); // Exibe a mensagem final
  }
}
