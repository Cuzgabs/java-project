
package hva.app.main;

import hva.core.Arvore;
import hva.core.HotelManager;
import java.util.List;
import pt.tecnico.uilib.menus.Command;

/**
 * Command for advancing the season of the system.
 **/

class DoAdvanceSeason extends Command<HotelManager> {
    DoAdvanceSeason(HotelManager receiver) {
        super(Label.ADVANCE_SEASON, receiver);
    }

    @Override
    protected final void execute() {
        int output = _receiver.getHotel().avancarEstacao();
        _display.addLine(output);
        _display.display();
    }
}
