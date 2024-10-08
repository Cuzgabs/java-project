package hva.core;

import hva.app.exception.UnknownEmployeeKeyException;
import hva.app.exception.UnknownHabitatKeyException;
import hva.app.exception.UnknownSpeciesKeyException;
import hva.core.exception.*;
import java.io.*;
import java.util.HashMap;
import hva.core.Parser;
// FIXME import classes

public class Hotel implements Serializable {

  @Serial
  private static final long serialVersionUID = 202407081733L;
  private String _currentSeason = "Primavera";  // Estação inicial é "Primavera"
  private HashMap<String, Animal> _animals;
  private HashMap<String, Species> _species;
  private HashMap<String, Employee> _employees;
  private HashMap<String, Habitats> _habitats;
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
    _habitats = new HashMap<>(); // Inicializa o mapa de habitats
    _vaccines = new HashMap<>();
    _arvores = new HashMap<>();
    _filename = null; // Inicializa o filename como nulo

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


  public String getFilename() {return _filename;}
  public void setFilename(String filename) {_filename = filename;}
  public void setCurrentSeason(String season) {_currentSeason = season;}




// Método para adicionar uma árvore ao habitat e também ao mapa global de árvores
  public void addTreeToHabitat(String habitatId, Arvore tree) throws UnknownHabitatKeyException {
    Habitats habitat = _habitats.get(habitatId);
  
    // Adiciona a árvore ao habitat
    habitat.addTree(tree);
    
    // Adiciona a árvore ao mapa global de árvores
    _arvores.put(tree.getArvoreId(), tree);
  }


  public Species getSpecies(String speciesId) {
    return _species.get(speciesId); // Retorna a espécie pelo ID ou null se não existir
  }


  public void registerAnimal(String animalId, String name, String habitatId, String speciesId) {
    Habitats habitat = _habitats.get(habitatId);
    Species species = _species.get(speciesId);
    
    Animal animal = new Animal(animalId, name, species);
    _animals.put(animalId, animal);
    habitat.addAnimal(animal); // Você deve implementar o método addAnimal na classe Habitats
  }


  public void registerSpecies(String speciesId, String name) {
    Species species = new Species(speciesId, name);
    _species.put(speciesId, species);
  }


  public void registerEmployee(String employeeId, String name) {
    Employee employee = new Employee(employeeId, name);
    _employees.put(employeeId, employee);
  }


  public Habitats registerHabitat(String habitatId, String name, int area) {
    Habitats habitat = new Habitats(habitatId, name, area);
    _habitats.put(habitatId, habitat);
  }


  public void registerVaccine(String vaccineId, String name, String[] speciesIds) {
    Vaccine vaccine = new Vaccine(vaccineId, name);
    _vaccines.put(vaccineId, vaccine);
    // Adicionar lógica para associar espécies se necessário
  }

  public void createTree(String treeId, String name, String type, int age, int difficulty) {
    Arvore tree = new Arvore(treeId, name, age, type, difficulty, _currentSeason);
    _arvores.put(treeId, tree);
  }

  public void addResponsibility(String employeeId, String responsibility) {
    Employee employee = _employees.get(employeeId);
    if (employee != null) {
      employee.addResponsibility(responsibility);  // Implementar addResponsibility na classe Employee
    }
  }
}



