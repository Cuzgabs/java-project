package hva.core;

import hva.app.exception.DuplicateTreeKeyException;
import hva.app.exception.UnknownHabitatKeyException;
import hva.app.exception.UnknownSpeciesKeyException;
import hva.core.exception.*;
import java.io.*;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import hva.core.Parser;
import javax.swing.text.html.HTMLEditorKit;
// FIXME import classes

public class Hotel implements Serializable {

  @Serial
  private static final long serialVersionUID = 202407081733L;
  private String _currentSeason = "Primavera";  // Estação inicial é "Primavera"
  private HashMap<String, Animal> _animals;
  private HashMap<String, Species> _species;
  private HashMap<String, Employee> _employees;
  private LinkedHashMap<String, Habitats> _habitats;
  private HashMap<String, Vaccine> _vaccines;
  private HashMap<String, Arvore> _arvores;  // Mapa de árvores associadas ao habitat
  private String _filename; // Adicionando o atributo para armazenar o nome do arquivo
  private Parser parser;


  // _animals = new HashMap<>;
  // FIXME define attributes
  // FIXME define contructor(s)
  // FIXME define more methods
  

 // Construtor
  public Hotel() {
    // Inicializa o mapa de animais e outras entidades necessárias
    _animals = new HashMap<>();  // Inicializa corretamente o mapa de animais
    _species = new HashMap<>();  // Inicializa o mapa de espécies (se necessário)
    _employees = new HashMap<>();
    _habitats = new LinkedHashMap<>(); // Inicializa como LinkedHashMap
    _vaccines = new HashMap<>();
    _arvores = new HashMap<>();
    _filename = null; // Inicializa o filename como nulo
    parser = new Parser(this);  // Passa a instância atual de Hotel

  }

  
  public HashMap<String, Animal> getAnimals() {return _animals;}
  public HashMap<String, Employee> getEmployees() {return _employees;}
  public HashMap<String, Habitats> getHabitats() {return _habitats;}
  public HashMap<String, Vaccine> getVaccines() {return _vaccines;}
  public HashMap<String, Arvore> getArvores() {return _arvores;}

  /**
   * Read text input file and create corresponding domain entities.
   * 
   * @param filename name of the text input file
   * @throws UnrecognizedEntryException if some entry is not correct
   * @throws IOException if there is an IO erro while processing the text file
   **/
  void importFile(String filename) throws UnrecognizedEntryException, IOException /* FIXME maybe other exceptions */  {
    //FIXME implement method
    parser.parseFile(filename);
  }


 // Método para retornar o nome do arquivo associado
    public String getFilename() {
        return _filename;
    }

    // Método para definir o nome do arquivo associado
    public void setFilename(String filename) {
        this._filename = filename;
    }




  public void addTreeToHabitat(String habitatId, String treeId) throws UnknownHabitatKeyException, NoArvoreKeyException {
    Habitats habitat = _habitats.get(habitatId);
    if (habitat == null) {
        throw new UnknownHabitatKeyException(habitatId);  // Lança exceção se o habitat não existir
    }

    Arvore tree = _arvores.get(treeId);
    if (tree == null) {
        throw new NoArvoreKeyException(treeId);  // Lança exceção se a árvore não existir
    }

    habitat.addTree(tree);
  }




// Método para criar e registrar uma árvore
  public void createTree(String treeId, String name, String type, int age, int difficulty) throws DupArvoreKeyException, NoArvoreTypeException {
    if (_arvores.containsKey(treeId)) {
      throw new DupArvoreKeyException(treeId);  // Exceção se a árvore já estiver registrada
    }
    // Verifica se o tipo de árvore é válido
    if (!type.equals("Caduca") && !type.equals("Perene")) {
        throw new NoArvoreTypeException(type);  // Exceção se o tipo de árvore for inválido
    }
    // Criação da nova árvore
    Arvore tree = new Arvore(treeId, name, age, type, difficulty, _currentSeason);
    // Adiciona a árvore ao mapa de árvores
    _arvores.put(treeId, tree);
  }


  public Arvore getTree(String treeId) throws NoArvoreKeyException {
      Arvore tree = _arvores.get(treeId);
      if (tree == null) {
          throw new NoArvoreKeyException(treeId);  // Lança exceção se a árvore não for encontrada
      }
      return tree;
  }




  public Species getSpecies(String speciesId) {
    return _species.get(speciesId); // Retorna a espécie pelo ID ou null se não existir
  }


  public void registerAnimal(String animalId, String name, String habitatId, String speciesId) 
        throws NoHabitatKeyException, NoSpeciesKeyException, DupAnimalKeyException {
    // Verifica se o habitat existe
    Habitats habitat = _habitats.get(habitatId);
    if (habitat == null){
        throw new NoHabitatKeyException(habitatId);
    } 
    // Verifica se a espécie existe
    Species species = _species.get(speciesId);
    if (species == null) {
        throw new NoSpeciesKeyException(speciesId);
    } 
    // Verifica se o ID do animal já foi registrado
    if (_animals.containsKey(animalId)) {
        throw new DupAnimalKeyException(animalId); // Lança exceção se o animal já estiver registrado
    }
    Animal animal = new Animal(animalId, name, species);
    _animals.put(animalId, animal);
    habitat.addAnimal(animal); // Assumindo que o método addAnimal está implementado em Habitats
  }


  public void registerSpecies(String id, String name) throws DupSpeciesKeyException {
    if (_species.containsKey(id)) {
      throw new DupSpeciesKeyException(id);  // Lança exceção se o ID da espécie já estiver registrado
    }
    Species specie = new Species(id, name);
    _species.put(id, specie);
  }


  public void registerEmployee(String employeeId, String name) throws DupEmployeeKeyException {
    if (_employees.containsKey(employeeId)) {
      throw new DupEmployeeKeyException(employeeId);  // Lança exceção se o ID do funcionário já estiver registrado
    }
    Employee employee = new Employee(employeeId, name);
    _employees.put(employeeId, employee);
  }



// Método para adicionar responsabilidade a um funcionário
  public void addResponsibility(String employeeId, String responsibility) throws NoEmployeeResponsibilityException {
      Employee employee = _employees.get(employeeId);
      if (employee == null) {
          throw new NoEmployeeResponsibilityException(employeeId);  // Exceção caso o funcionário não exista
      }
      employee.addResponsibility(responsibility);  // Adiciona a responsabilidade ao funcionário
  }



  public Habitats registerHabitat(String habitatId, String habitatName, int habitatArea) throws DupHabitatKeyException {
    if (_habitats.containsKey(habitatId)) {
        throw new DupHabitatKeyException(habitatId);
    }
    Habitats habitat = new Habitats(habitatId, habitatName, habitatArea);
    _habitats.put(habitatId, habitat);
    return habitat;
  }



  
  public void registerVaccine(String vaccineId, String vaccineName, String[] speciesIds) throws DupVaccineKeyException, NoSpeciesKeyException {
    if (_vaccines.containsKey(vaccineId)) {
      throw new DupVaccineKeyException(vaccineId);  // Exceção se a vacina já estiver registrada
    }

    // Verifica se todas as espécies existem
    for (String speciesId : speciesIds) {
      if (!_species.containsKey(speciesId)) {
        throw new NoSpeciesKeyException(speciesId);  // Exceção se uma das espécies não existir
      }
    }

    Vaccine vaccine = new Vaccine(vaccineId, vaccineName, speciesIds);
    _vaccines.put(vaccineId, vaccine);
  }


  // Adiciona uma árvore ao habitat
  public void addTree(Arvore tree) {
      _arvores.put(tree.getArvoreId(), tree);
  }


  // Método para obter a estação atual
  public String getCurrentSeason() {
    return _currentSeason;
  }

  // Método para definir uma nova estação
  public void setCurrentSeason(String season) {
    _currentSeason = season;
  }
}