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
    try {
        // Verifica se há um arquivo associado
        if (_receiver.getHotel().getFilename() == null) {
            _display.addLine("Ficheiro sem nome."); // Mensagem antes de solicitar o nome
            String filename = Form.requestString(Prompt.saveAs());
            
            // Tenta salvar com o novo nome
            _receiver.saveAs(filename); 
        } else {
            // Se já houver um arquivo associado, chama o método de salvar
            _receiver.save();
        }
        
        // Mensagem de sucesso após salvar
        _display.addLine("Estado da aplicação salvo com sucesso.");
    } catch (MissingFileAssociationException e) {
        _display.addLine("Erro: " + e.getMessage());
    } catch (IOException e) {
        // Lida com erros de I/O, mas não informa que o problema foi com 'Habitats'
        _display.addLine("Erro ao salvar o arquivo: " + e.getMessage());
    } catch (Exception e) {
        // Captura qualquer outra exceção não tratada, permitindo que você verifique se é algo inesperado
        _display.addLine("Erro inesperado: " + e.getMessage());
    } finally {
        _display.display(); // Certifica-se de que a mensagem final seja exibida
    }
  }
}