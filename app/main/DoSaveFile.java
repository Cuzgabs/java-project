package hva.app.main;

import hva.core.HotelManager;
import hva.core.exception.MissingFileAssociationException;
import java.io.IOException;
import pt.tecnico.uilib.forms.Form;
import pt.tecnico.uilib.menus.Command;

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
            if (_receiver.getHotel().getFilename() == null) {
                String filename = Form.requestString("Ficheiro sem nome. Guardar ficheiro como: ");

                _receiver.saveAs(filename);

            } else {
                _receiver.save();

                _display.addLine("Estado da aplicação salvo com sucesso.");
            }

        } catch (MissingFileAssociationException e) {
            _display.addLine("Erro: " + e.getMessage());
        } catch (IOException e) {
            _display.addLine("Erro ao guardar o arquivo: " + e.getMessage());
        } catch (Exception e) {
            _display.addLine("Erro inesperado: " + e.getMessage());
        } finally {
            _display.display();
        }
    }
}