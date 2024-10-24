
package hva.core;

import hva.app.exception.UnknownAnimalKeyException;
import hva.core.exception.*;
import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;

/**
 * Classe que representa o hotel, armazena informações sobre animais, espécies,
 * funcionários, habitats, vacinas e árvores.
 * Permite gerir o hotel e realizar operações como registar animais,
 * funcionários e vacinas.
 */
public class Hotel implements Serializable {

  @Serial
  private static final long serialVersionUID = 202407081733L;
  private String estacaoAtual; // Declaração da variável
  private int _currentSeason;   // Se você precisa dessa variável
  private HashMap<String, Animal> _animals;
  private HashMap<String, Species> _species;
  private HashMap<String, Employee> _employees;
  private ArrayList<Habitats> _habitats;
  private HashMap<String, Vaccine> _vaccines;
  private HashMap<String, Arvore> _arvores;
  private String _filename;
  private transient Parser _parser;

  /**
   * Construtor da classe Hotel.
   * Inicializa as estruturas de dados usadas para armazenar animais, espécies,
   * funcionários, habitats, vacinas e árvores.
   */
  public Hotel() {
    _animals = new HashMap<>();
    _species = new HashMap<>();
    _employees = new HashMap<>();
    _habitats = new ArrayList<>();
    _vaccines = new HashMap<>();
    _arvores = new HashMap<>();
    _filename = null;
    _parser = new Parser(this);
  }

  /** @return Retorna o mapa de animais cadastrados. */
  public HashMap<String, Animal> getAnimals() {
    return _animals;
  }

  /** @return Retorna o mapa de funcionários cadastrados. */
  public HashMap<String, Employee> getEmployees() {
    return _employees;
  }

  /** @return Retorna a lista de habitats cadastrados. */
  public ArrayList<Habitats> getHabitats() {
    return _habitats;
  }

  /** @return Retorna o mapa de vacinas cadastradas. */
  public HashMap<String, Vaccine> getVaccines() {
    return _vaccines;
  }

  /** @return Retorna o mapa de árvores cadastradas. */
  public HashMap<String, Arvore> getArvores() {
    return _arvores;
  }

  /**
   * Lê um arquivo de texto de entrada e cria as entidades correspondentes no
   * domínio.
   * 
   * @param filename Nome do arquivo de texto de entrada.
   * @throws UnrecognizedEntryException Se alguma entrada no arquivo for inválida.
   * @throws IOException                Se ocorrer um erro de I/O durante o
   *                                    processamento do arquivo.
   */
  void importFile(String filename) throws UnrecognizedEntryException, IOException {
    _parser.parseFile(filename);
  }

  /** @return Retorna o nome do arquivo associado ao hotel. */
  public String getFilename() {
    return _filename;
  }

  /**
   * Define o nome do arquivo associado ao hotel.
   * 
   * @param filename Nome do arquivo a ser associado.
   */
  public void setFilename(String filename) {
    this._filename = filename;
  }

  /**
   * Adiciona uma árvore a um habitat especificado.
   * 
   * @param habitatId ID do habitat.
   * @param treeId    ID da árvore.
   * @throws NoHabitatKeyException Se o habitat não for encontrado.
   * @throws NoArvoreKeyException  Se a árvore não for encontrada.
   */
  public void addTreeToHabitat(String habitatId, String treeId) throws NoHabitatKeyException, NoArvoreKeyException {
    Habitats habitat = findHabitatById(habitatId);
    Arvore tree = _arvores.get(treeId);
    if (tree == null) {
      throw new NoArvoreKeyException(treeId);
    }
    habitat.addTree(tree);
  }


  public Species findSpeciesById(String speciesId) throws NoSpeciesKeyException {
    // Lógica para encontrar a espécie pelo ID
    if (!_species.containsKey(speciesId)) {
        throw new NoSpeciesKeyException(speciesId);
    }
    return _species.get(speciesId);
  }


  /**
   * Encontra um habitat pelo seu ID.
   * 
   * @param habitatId ID do habitat a ser encontrado.
   * @return O habitat correspondente ao ID.
   * @throws NoHabitatKeyException Se o habitat não for encontrado.
   */
  public Habitats findHabitatById(String habitatId) throws NoHabitatKeyException {
    for (Habitats habitat : _habitats) {
      if (habitat.getHabitatId().equals(habitatId)) {
        return habitat;
      }
    }
    throw new NoHabitatKeyException(habitatId);
  }

  /**
   * Cria e registra uma nova árvore.
   * 
   * @param treeId     ID da árvore.
   * @param name       Nome da árvore.
   * @param type       Tipo da árvore (Caduca ou Perene).
   * @param age        Idade da árvore.
   * @param difficulty Nível de dificuldade de limpeza.
   * @throws DupArvoreKeyException Se a árvore já estiver registaada.
   * @throws NoArvoreTypeException Se o tipo de árvore for inválido.
   */
  public void createTree(String treeId, String name, String type, int age, int difficulty)
      throws DupArvoreKeyException, NoArvoreTypeException {
    if (_arvores.containsKey(treeId)) {
      throw new DupArvoreKeyException(treeId);
    }
    if (!type.equals("CADUCA") && !type.equals("PERENE")) {
      throw new NoArvoreTypeException(type);
    }
    Arvore tree = new Arvore(treeId, name, age, type, difficulty, estacaoAtual);
    _arvores.put(treeId, tree);
  }

  /** @return Retorna uma árvore pelo seu ID. */
  public Arvore getTree(String treeId) throws NoArvoreKeyException {
    Arvore tree = _arvores.get(treeId);
    if (tree == null) {
      throw new NoArvoreKeyException(treeId);
    }
    return tree;
  }

  /** @return Retorna uma espécie pelo seu ID. */
  public Species getSpecies(String speciesId) {
    return _species.get(speciesId);
  }

  /**
   * Regista um novo animal no hotel.
   * 
   * @param animalId  ID do animal.
   * @param name      Nome do animal.
   * @param habitatId ID do habitat.
   * @param speciesId ID da espécie.
   * @throws NoHabitatKeyException Se o habitat não for encontrado.
   * @throws NoSpeciesKeyException Se a espécie não for encontrada.
   * @throws DupAnimalKeyException Se o animal já estiver registado.
   */
  public void registerAnimal(String animalId, String name, String habitatId, String speciesId)
      throws NoHabitatKeyException, NoSpeciesKeyException, DupAnimalKeyException {
    Habitats habitat = findHabitatById(habitatId);
    if (habitat == null) {
      throw new NoHabitatKeyException(habitatId);
    }
    Species species = _species.get(speciesId);
    if (species == null) {
      throw new NoSpeciesKeyException(speciesId);
    }
    if (_animals.containsKey(animalId)) {
      throw new DupAnimalKeyException(animalId);
    }
    Animal animal = new Animal(animalId, name, species, habitat);
    _animals.put(animalId, animal);
    habitat.addAnimal(animal);
  }


  public double getSatisfactionOfAnimal(String animalId) throws UnknownAnimalKeyException {
    Animal animal = _animals.get(animalId);
    if (animal == null) {
      throw new UnknownAnimalKeyException(animalId);
    }
    return animal.getSatisfactionOfAnimal();
  }



  /**
   * Regista uma nova espécie.
   * 
   * @param id   ID da espécie.
   * @param name Nome da espécie.
   * @throws DupSpeciesKeyException Se a espécie já estiver registada.
   */
  public void registerSpecies(String id, String name) throws DupSpeciesKeyException {
    if (_species.containsKey(id)) {
      throw new DupSpeciesKeyException(id);
    }
    Species specie = new Species(id, name);
    _species.put(id, specie);
  }

  /**
   * Regista um novo funcionário.
   * 
   * @param employeeId   ID do funcionário.
   * @param name         Nome do funcionário.
   * @param employeeType Tipo do funcionário (VET ou TRT).
   * @throws DupEmployeeKeyException Se o funcionário já estiver registado.
   */


  public void registerEmployee(String employeeId, String name, String employeeType) throws DupEmployeeKeyException {
    Employee employee;

    if (_employees.containsKey(employeeId)) {
      throw new DupEmployeeKeyException(employeeId);
    }

    if (employeeType.equalsIgnoreCase("TRT")) {
      // Registre um Tratador com habitats vazios para começar
      employee = new Tratador(employeeId, name, employeeType, new ArrayList<>());
    } else if (employeeType.equalsIgnoreCase("VET")) {
      // Registre um Veterinário com espécies vazias para começar
      employee = new Veterinario(employeeId, name, employeeType, new ArrayList<>());
    } else {
      throw new IllegalArgumentException("Tipo de funcionário desconhecido: " + employeeType);
    }

    _employees.put(employeeId, employee);
  }



  public Employee getEmployeeById(String employeeId) {
    return _employees.get(employeeId);
  }



  /**
   * Regista um tratador.
   * 
   * @param employeeId   ID do tratador.
   * @param employeeName Nome do tratador.
   * @param employeeType Tipo do funcionário.
   * @param habitats     Lista de habitats que o tratador é responsável.
   * @throws DupEmployeeKeyException Se o tratador já estiver registado.
   */
  public void registerTratador(String employeeId, String employeeName, String employeeType, List<String> habitats)
      throws DupEmployeeKeyException {
    if (_employees.containsKey(employeeId)) {
      throw new DupEmployeeKeyException(employeeId);
    }
    Tratador tratador = new Tratador(employeeId, employeeName, employeeType, habitats);
    _employees.put(employeeId, tratador);
  }

  /**
   * Regista um veterinário.
   * 
   * @param employeeId   ID do veterinário.
   * @param employeeName Nome do veterinário.
   * @param employeeType Tipo do funcionário.
   * @param species      Lista de espécies que o veterinário pode tratar.
   * @throws DupEmployeeKeyException Se o veterinário já estiver registado.
   */
  public void registerVeterinario(String employeeId, String employeeName, String employeeType, List<String> species)
      throws DupEmployeeKeyException {
    if (_employees.containsKey(employeeId)) {
      throw new DupEmployeeKeyException(employeeId);
    }
    Veterinario veterinario = new Veterinario(employeeId, employeeName, employeeType, species);
    _employees.put(employeeId, veterinario);
  }

  /**
   * Adiciona uma responsabilidade a um funcionário.
   * 
   * @param employeeId     ID do funcionário.
   * @param responsibility Responsabilidade a ser atribuída.
   * @throws NoEmployeeResponsibilityException Se o funcionário não for
   *                                           encontrado.
   */
  public void addResponsibility(String employeeId, String responsibility) throws NoEmployeeResponsibilityException {
    Employee employee = _employees.get(employeeId);
    if (employee == null) {
      throw new NoEmployeeResponsibilityException(employeeId);
    }
    employee.addResponsibility(responsibility);
  }

  /**
   * Regista um novo habitat.
   * 
   * @param habitatId   ID do habitat.
   * @param habitatName Nome do habitat.
   * @param habitatArea Área do habitat.
   * @return O habitat registado.
   * @throws DupHabitatKeyException Se o habitat já estiver registado.
   */
  public Habitats registerHabitat(String habitatId, String habitatName, int habitatArea) throws DupHabitatKeyException {
    for (Habitats habitat : _habitats) {
      if (habitat.getHabitatId().equals(habitatId)) {
        throw new DupHabitatKeyException(habitatId);
      }
    }
    Habitats habitat = new Habitats(habitatId, habitatName, habitatArea);
    _habitats.add(habitat);
    return habitat;
  }




  public boolean existsHabitat(String habitatId) {
    for (Habitats habitat : _habitats) {
      if (habitat.getHabitatId().equals(habitatId)) {
          return true;
      }
    }
    return false;
  }



  public Habitats getHabitatById(String habitatId) {
    for (Habitats habitat : _habitats) {
        if (habitat.getHabitatId().equals(habitatId)) {
            return habitat;
        }
    }
    return null; // ou lance uma exceção
  }


  public void transferAnimalToHabitat(String animalId, String habitatId) throws NoHabitatKeyException {
    Animal animal = getAnimal(animalId);
    Habitats habitat = getHabitatById(habitatId);
    
    if (habitat != null && animal != null) {
        // Remover animal do habitat atual (se necessário)
        if (animal.getHabitat() != null) {
            animal.getHabitat().removeAnimal(animalId);
        }
        // Adicionar animal ao novo habitat
        habitat.addAnimal(animal);
        animal.setHabitat(habitat); // Método para atualizar o habitat do animal
    } else {
        throw new NoHabitatKeyException(habitatId); // Lança exceção se o habitat não existir
    }
  }



  /**
   * Obtém todos os habitats ordenados por ID.
   * 
   * @return Lista de habitats ordenada por ID.
   */
  public ArrayList<Habitats> getOrderedHabitats() {
    // Usar comparador que ignora maiúsculas e minúsculas e ordena
    ArrayList<Habitats> sortedHabitats = new ArrayList<>(_habitats);
    Collections.sort(_habitats, Comparator.comparing(h -> h.getHabitatId().toLowerCase()));
    return _habitats;
  }

  // Novo método para listar animais em um habitat específico
  public List<Animal> getAnimalsInHabitat(String habitatId) throws NoHabitatKeyException {
    Habitats habitat = findHabitatById(habitatId);
    return new ArrayList<>(habitat.getAnimals().values());
  }



  public Animal getAnimal(String animalId) {
        // Lógica para buscar e retornar o animal com o ID fornecido.
        return _animals.get(animalId); // Exemplo simples
  }



  /**
   * Regista uma nova vacina.
   * 
   * @param vaccineId       ID da vacina.
   * @param vaccineName     Nome da vacina.
   * @param speciesIdsArray Array de IDs das espécies para as quais a vacina pode
   *                        ser aplicada.
   * @throws DupVaccineKeyException Se a vacina já estiver registada.
   * @throws NoSpeciesKeyException  Se alguma das espécies for desconhecida.
   */
  public void registerVaccine(String vaccineId, String vaccineName, String[] speciesIdsArray)
      throws DupVaccineKeyException, NoSpeciesKeyException {
    List<String> speciesIds = Arrays.asList(speciesIdsArray);

    if (_vaccines.containsKey(vaccineId)) {
      throw new DupVaccineKeyException(vaccineId);
    }

    for (String speciesId : speciesIds) {
      if (!_species.containsKey(speciesId)) {
        throw new NoSpeciesKeyException(speciesId);
      }
    }

    Vaccine vaccine = new Vaccine(vaccineId, vaccineName, speciesIds);
    _vaccines.put(vaccineId, vaccine);
  }

  /**
   * Adiciona uma árvore ao hotel.
   * 
   * @param tree A árvore a ser adicionada.
   */
  public void addTree(Arvore tree) {
    _arvores.put(tree.getArvoreId(), tree);
  }

  /** @return Retorna a estação atual. */
  public String getCurrentSeason() {
    return estacaoAtual;
  }

  /**
   * Define a estação atual.
   * 
   * @param season Nova estação.
   */
  

    /**
   * Avança a estação para a próxima e atualiza todas as árvores nos habitats.
   * 
   * As estações seguem a ordem: 0 - Primavera, 1 - Verão, 2 - Outono, 3 - Inverno.
   * Ao avançar a estação, todas as árvores presentes nos habitats têm seus ciclos de vida
   * atualizados conforme o novo valor da estação.
   */
  public void advanceSeason() {
      _currentSeason = (_currentSeason + 1) % 4; // Avança para a próxima estação (0 a 3)
      // Atualiza a estação de cada árvore em cada habitat
      for (Habitats habitat : _habitats) {
          for (Arvore arvore : habitat.getArvores().values()) {
            arvore.atualizarEstado(estacaoAtual); // Atualiza o estado da árvore com base na estação atual.
          }
      }
  }

  /**
   * Define a estação atual explicitamente.
   * 
   * @param season Nova estação (0 - Primavera, 1 - Verão, 2 - Outono, 3 - Inverno).
   */
  public void setCurrentSeason(int season) {
      if (season < 0 || season > 3) {
          throw new IllegalArgumentException("Estação inválida. Deve ser entre 0 e 3.");
      }
      _currentSeason = season;
      // Atualiza as árvores de acordo com a nova estação
      for (Habitats habitat : _habitats) {
          for (Arvore arvore : habitat.getArvores().values()) {
              arvore.atualizarEstado(estacaoAtual);
          }
      }
  }

}
