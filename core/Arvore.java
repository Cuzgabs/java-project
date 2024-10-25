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
    private Hotel _hotel;
    private int _estacoesPassadas =0;

    // Construtor
    public Arvore(Hotel hotel, String arvoreId, String arvoreNome, int arvoreIdade, String tipoFolha, int dificuldadeLimpeza) {
        _arvoreId = arvoreId;

        _arvoreNome = arvoreNome;
        _arvoreIdade = arvoreIdade;
        _tipoFolha = tipoFolha;
        _hotel=hotel;
        _dificuldadeLimpeza = dificuldadeLimpeza;

    }
    private String calcularCicloBiologico(Estacao estacao) {
        // Mapeamento das árvores com base no tipo de folha e estação do ano
        Map<String, Map<Estacao, String>> ciclosBiologicos = new HashMap<>();

        // Map para folhas caducas
        Map<Estacao, String> caduca = new HashMap<>();
        caduca.put(Estacao.WINTER, "SEMFOLHAS");
        caduca.put(Estacao.SPRING, "GERARFOLHAS");
        caduca.put(Estacao.SUMMER, "COMFOLHAS");
        caduca.put(Estacao.FALL, "LARGARFOLHAS");

        // Map para folhas perenes
        Map<Estacao, String> perene = new HashMap<>();
        perene.put(Estacao.WINTER, "LARGARFOLHAS");
        perene.put(Estacao.SPRING, "GERARFOLHAS");
        perene.put(Estacao.SUMMER, "COMFOLHAS");
        perene.put(Estacao.FALL, "COMFOLHAS");

        // Adiciona os mapas ao dicionário principal
        ciclosBiologicos.put("CADUCA", caduca);
        ciclosBiologicos.put("PERENE", perene);

        // Obtém o ciclo biológico com base no tipo de folha e estação
        Map<Estacao, String> cicloPorTipo = ciclosBiologicos.get(_tipoFolha);
        
        if (cicloPorTipo != null) {
            return cicloPorTipo.get(estacao); // Retorna o ciclo correto
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
    public void aumentaIdade(){
        _estacoesPassadas++;
        if(_estacoesPassadas==4){
            _arvoreIdade++;
            _estacoesPassadas=0;
        }
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



   
    // Método que retorna a representação em string da árvore
    @Override
    public String toString() {
        return String.format("ÁRVORE|%s|%s|%d|%d|%s|%s",
                _arvoreId,
                _arvoreNome,
                _arvoreIdade,
                _dificuldadeLimpeza,
                _tipoFolha,
                calcularCicloBiologico(_hotel.getCurrentSeason()));
    }
}
