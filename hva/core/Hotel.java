package hva.core;

import hva.core.exception.*;
import java.io.*;
import java.util.HashMap;
// FIXME import classes

public class Hotel implements Serializable {

  @Serial
  private static final long serialVersionUID = 202407081733L;
  private HashMap<String,Animal> _animals;
  private HashMap<String,Species> _species;
  private HashMap<String, Employee> _employees;
  private HashMap<String, Habitats> _habitats;
  private HashMap<String, Vaccine> _vaccines;


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
  }

  
  // Método para retornar o mapa de animais
  public HashMap<String, Animal> getAnimals() {
    return _animals;
  }


  // Retorna o mapa de funcionários
  public HashMap<String, Employee> getEmployees() {
    return _employees;
  }


  // Métodos para obter e registrar habitats
  public HashMap<String, Habitats> getHabitats() {
    return _habitats;
  }


  // Retorna o mapa de vacinas
  public HashMap<String, Vaccine> getVaccines() {
    return _vaccines;
  }

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
}
