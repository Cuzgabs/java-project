package hva.core;

import hva.app.exception.UnknownHabitatKeyException;
import hva.core.exception.*;
import java.io.*;
import java.util.HashMap;
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
  }


 // Método para retornar o nome do arquivo associado
    public String getFilename() {
        return _filename;
    }

    // Método para definir o nome do arquivo associado
    public void setFilename(String filename) {
        this._filename = filename;
    }




// Método para adicionar uma árvore ao habitat e também ao mapa global de árvores
  public void addTreeToHabitat(String habitatId, Arvore tree) throws UnknownHabitatKeyException {
    Habitats habitat = _habitats.get(habitatId);
    if (habitat == null) {
      throw new UnknownHabitatKeyException(habitatId); // Lança exceção se o habitat não existir
    }
    
    // Adiciona a árvore ao habitat
    habitat.addTree(tree);
    
    // Adiciona a árvore ao mapa global de árvores
    _arvores.put(tree.getArvoreId(), tree);
  }



  public Species getSpecies(String speciesId) {
    return _species.get(speciesId); // Retorna a espécie pelo ID ou null se não existir
  }

  public void DoRegisterAnimal(Animal animal){
    _animals.put(animal.getId(),animal);
  }

  public void DoRegisterSpecies(Species specie){
    _species.put(specie.getId(),specie);
  }

  // Registra um funcionário no hotel
  public void registerEmployee(Employee employee) {
    _employees.put(employee.getEmployeeId(), employee);
  }

  public void registerHabitat(Habitats habitat) {
    _habitats.put(habitat.getHabitatId(), habitat);
  }

  // Registra uma vacina no hotel
  public void registerVaccine(Vaccine vaccine) {
    _vaccines.put(vaccine.getVaccineId(), vaccine);
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
