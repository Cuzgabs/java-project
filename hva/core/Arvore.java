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

    // Construtor
    public Arvore(String arvoreId, String arvoreNome, int arvoreIdade, String tipoFolha, int dificuldadeLimpeza,
            String estacaoAtual) {
        _arvoreId = arvoreId;
        _arvoreNome = arvoreNome;
        _arvoreIdade = arvoreIdade;
        _tipoFolha = tipoFolha;
        _dificuldadeLimpeza = dificuldadeLimpeza;
        _estacao = estacaoAtual;
    }


    public String getCicloBiologico() {
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

        // Devolve o ciclo biológico correspondente
        return ciclosBiologicos.getOrDefault(_tipoFolha, new HashMap<>()).getOrDefault(_estacao, "CICLO DESCONHECIDO");
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

    public void setSeason(String season) {
        _estacao = season; // Atualiza a estação da árvore
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
                getCicloBiologico());
    }
}

