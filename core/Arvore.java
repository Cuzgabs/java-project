package hva.core;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;


public class Arvore implements Serializable {
    private String _arvoreId;
    private String _arvoreNome;
    private int _arvoreIdade;
    private String _tipoFolha;
    private int _dificuldadeLimpeza;
    private String _estacao;
    private String _cicloBiologico; // Adicione este atributo para manter o ciclo biológico atualizado


    // Construtor
    public Arvore(String arvoreId, String arvoreNome, int arvoreIdade, String tipoFolha, int dificuldadeLimpeza,
            String estacaoAtual) {
        _arvoreId = arvoreId;
        _arvoreNome = arvoreNome;
        _arvoreIdade = arvoreIdade;
        _tipoFolha = tipoFolha;
        _dificuldadeLimpeza = dificuldadeLimpeza;

    _estacao = (estacaoAtual == null || estacaoAtual.isEmpty()) ? "Primavera" : estacaoAtual;

    _cicloBiologico = calcularCicloBiologico(); // Inicializa o ciclo biológico com base na estação inicial
    }


    private String calcularCicloBiologico() {
        // Mapeamento das árvores com base no tipo de folha e estação do ano
        Map<String, Map<String, String>> ciclosBiologicos = new HashMap<>();

        // Map para folhas caducas
        Map<String, String> caduca = new HashMap<>();
        caduca.put("Inverno", "SEMFOLHAS");
        caduca.put("Primavera", "GERARFOLHAS");
        caduca.put("Verão", "COMFOLHAS");
        caduca.put("Outono", "LARGARFOLHAS");

        // Map para folhas perenes
        Map<String, String> perene = new HashMap<>();
        perene.put("Inverno", "LARGARFOLHAS");
        perene.put("Primavera", "GERARFOLHAS");
        perene.put("Verão", "COMFOLHAS");
        perene.put("Outono", "COMFOLHAS");

        // Adiciona os mapas ao dicionário principal
        ciclosBiologicos.put("CADUCA", caduca);
        ciclosBiologicos.put("PERENE", perene);

        // Obtém o ciclo biológico com base no tipo de folha e estação
        Map<String, String> cicloPorTipo = ciclosBiologicos.get(_tipoFolha);
        
        if (cicloPorTipo != null) {
            return cicloPorTipo.get(_estacao); // Retorna o ciclo correto
        }

        return "TIPO DE FOLHA DESCONHECIDO"; // Se o tipo de folha não for encontrado
    }



    // Getters
    public String getArvoreId() {
        return _arvoreId;
    }

    public String getArvoreNome() {
        return _arvoreNome;
    }

    public int getArvoreIdade() {
        return _arvoreIdade;
    }

    public String getTipoFolha() {
        return _tipoFolha;
    }

    public int getDificuldadeLimpeza() {
        return _dificuldadeLimpeza;
    }

    public String getEstacao() {
        return _estacao;
    }



    public void atualizarEstado(String novaEstacao) {
        _estacao = novaEstacao; // Atualiza a estação da árvore
        _cicloBiologico = calcularCicloBiologico(); // Recalcula o ciclo biológico

    }


    public void avancarEstacao() {
    // Criação de uma lista com as estações em ordem
    String[] estacoes = {"Primavera", "Verão", "Outono", "Inverno"};
    
    // Procura o índice da estação atual
    for (int i = 0; i < estacoes.length; i++) {
        if (estacoes[i].equals(_estacao)) {
            // Avança para a próxima estação, garantindo que volte ao início se estiver no final da lista
            _estacao = estacoes[(i + 1) % estacoes.length];
            break;
        }
    }

    // Atualiza o ciclo biológico após mudar a estação
    _cicloBiologico = calcularCicloBiologico();
    }


    // Método que retorna a representação em string da árvore
    @Override
    public String toString() {
        return String.format("ÁRVORE|%s|%s|%d|%d|%s|%s",
                _arvoreId,
                _arvoreNome,
                _arvoreIdade,
                _dificuldadeLimpeza,
                _tipoFolha,
                _cicloBiologico);
    }
}
