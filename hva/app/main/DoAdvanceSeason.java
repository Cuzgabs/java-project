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

        // Adiciona depuração para verificar a estação global após avanço
        _display.addLine("Estação global após avanço: " + globalSeason);

        // Atualiza todas as árvores em todos os habitats
        List<Arvore> allTrees = _receiver.getAllTrees();

        // Adiciona depuração para verificar o número de árvores
        _display.addLine("Número de árvores: " + allTrees.size());

        for (Arvore tree : allTrees) {
            // Obtém a estação atual da árvore
            String currentTreeSeason = tree.getEstacao();

            // Depuração para verificar a estação recebida diretamente da árvore
            _display.addLine("Estação recebida da árvore: " + currentTreeSeason);
            
            // Determina o índice da estação atual da árvore
            int treeSeasonIndex = -1;
            for (int i = 0; i < seasons.length; i++) {
                if (seasons[i].equals(currentTreeSeason)) {
                    treeSeasonIndex = i;
                    break;
                }
            }

            // Verifica se a estação atual foi mapeada corretamente
            if (treeSeasonIndex == -1) {
                _display.addLine("Erro: Estação desconhecida para a árvore.");
                continue;
            }

            // Adiciona depuração para verificar o índice da estação atual da árvore
            _display.addLine("Índice da estação atual da árvore: " + treeSeasonIndex);

            // Avança a estação da árvore para a próxima estação
            int newTreeSeasonIndex = (treeSeasonIndex + 1) % 4; // Ciclo entre 0 a 3
            
            // Atualiza a estação da árvore com o novo valor
            tree.setSeason(seasons[newTreeSeasonIndex]); // Define a nova estação pelo nome

            // Adiciona depuração para verificar a estação final após atualização
            _display.addLine("Estação da árvore após avanço: " + seasons[newTreeSeasonIndex]);
        }

        // Exibe a nova estação global
        _display.addLine(String.format("Estação global final: %d", globalSeason));
        _display.display();
    }
}
