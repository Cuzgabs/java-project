package hva.app.main;

import hva.core.HotelManager;
import hva.core.exception.MissingFileAssociationException;
import pt.tecnico.uilib.forms.Form;
import pt.tecnico.uilib.menus.Command;
import pt.tecnico.uilib.menus.CommandException;
import java.io.IOException;
// FIXME add more imports if needed

/**
 * Save to file under current name (if unnamed, query for name).
 */
class DoSaveFile extends Command<HotelManager> {
  DoSaveFile(HotelManager receiver) {
    super(Label.SAVE_FILE, receiver, r -> r.getHotel() != null);
  }

  @Override
  protected final void execute() {
    // FIXME implement command and create a local Form

    try {
      // Verifica se há um arquivo associado
      if (_receiver.getHotel().getFilename() == null) {
        // Solicita o nome do arquivo ao usuário

     
        
        String filename = Form.requestString("Ficheiro sem nome. " + Prompt.saveAs());

        

        _receiver.saveAs(filename); // Salva com o novo nome e associa ao hotel
      } else {
        // Se já houver um arquivo associado, chama o método de salvar
        _receiver.save();
      }


    } catch (MissingFileAssociationException e) {
      // Lida com a exceção caso o arquivo não esteja associado
      _display.addLine("Erro: " + e.getMessage());
      _display.display();
    } catch (IOException e) {
      // Lida com possíveis erros de I/O ao salvar o arquivo
      _display.addLine("Erro ao salvar o arquivo: " + e.getMessage());
      _display.display();
    }
  }
}