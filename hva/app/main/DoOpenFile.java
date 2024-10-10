package hva.app.main;

import hva.core.HotelManager;
import hva.app.exception.FileOpenFailedException;
import hva.core.exception.UnavailableFileException;
import pt.tecnico.uilib.forms.Form;
import pt.tecnico.uilib.menus.Command;
import pt.tecnico.uilib.menus.CommandException;

//FIXME add more imports if needed

/**
 * Command to open a file.
 */
class DoOpenFile extends Command<HotelManager> {
  DoOpenFile(HotelManager receiver) {
    super(Label.OPEN_FILE, receiver);
  }

  @Override
  protected final void execute() throws CommandException {
    /*
      try {
      //FIXME implement command
      } catch (UnavailableFileException efe) {
      throw new FileOpenFailedException(efe);
      }
    */

   try {
      // Solicita o nome do arquivo ao usuário
      String filename = Form.requestString(Prompt.openFile());

      // Tenta carregar o estado do hotel a partir do arquivo
      _receiver.load(filename);
      
    } catch (UnavailableFileException e) {
      // Lida com a exceção caso o arquivo não esteja disponível
      throw new FileOpenFailedException(e);  // Lança a exceção personalizada
    }
  }
}
