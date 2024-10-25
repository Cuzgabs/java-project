
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
        // Mapeamento das estações para números
        String[] seasons = {"Primavera", "Verão", "Outono", "Inverno"};
        
        // Avança a estação globalmente no sistema
        _receiver.advanceSeason();
        int globalSeason = _receiver.getCurrentSeason(); // Obtém a nova estação global (de 0 a 3)


        // Atualiza todas as árvores em todos os habitats
        List<Arvore> allTrees = _receiver.getAllTrees();


        for (Arvore tree : allTrees) {
            // Obtém a estação atual da árvore
            tree.atualizarEstado(seasons[globalSeason]);

        }

        // Exibe a nova estação global
        _display.addLine(String.format("%d", globalSeason));
        _display.display();
    }
}
