package hva.app.main;

import hva.app.exception.FileOpenFailedException;
import hva.core.HotelManager;
import hva.core.exception.MissingFileAssociationException;
import hva.core.exception.UnavailableFileException;
import java.io.IOException;
import pt.tecnico.uilib.forms.Form;
import pt.tecnico.uilib.menus.Command;
import pt.tecnico.uilib.menus.CommandException;

/**
 * Command to open a file.
 */
class DoOpenFile extends Command<HotelManager> {
  DoOpenFile(HotelManager receiver) {
    super(Label.OPEN_FILE, receiver);
  }

  @Override
  protected final void execute() throws CommandException {
    try {
      // Primeira verificação se há alterações não salvas
      if (_receiver.hasChanged()) {
        String saveResponse = Form.requestString(Prompt.saveBeforeExit());

        // Se o usuário escolheu salvar, verificar se há nome de arquivo
        if (saveResponse.equals("s") || saveResponse.equals("sim")) {
          if (_receiver.getFilename() != null) {
            // Se houver nome de arquivo, salvar diretamente
            _receiver.save();
          } else {
            // Solicitar nome do arquivo caso ainda não tenha
            String newFilename = Form.requestString(Prompt.newSaveAs());
            _receiver.saveAs(newFilename);
          }
        }
      }

      // Solicitar o nome do arquivo para abrir
      String filename = Form.requestString(Prompt.openFile());
      _receiver.load(filename);

    } catch (MissingFileAssociationException | IOException e) {
      throw new FileOpenFailedException(e);
    } catch (UnavailableFileException e) {
      throw new FileOpenFailedException(e);
    }
  }
}
