
package hva.core;

import hva.core.exception.*;
import java.io.*;
import java.util.ArrayList;
import java.util.List;


/**
 * Class representing the manager of this application. It manages the current
 * zoo hotel.
 **/
public class HotelManager {
  private Hotel _hotel = new Hotel();
  private String _filename;
  private boolean _hasChanged = false; // Flag para indicar se houve mudanças
  private int _currentSeason; // 0 = Primavera, 1 = Verão, 2 = Outono, 3 = Inverno
  private List<Arvore> _allTrees; // Lista de todas as árvores
  private List<Employee> employees;
  private List<Animal> animals;


  // Construtor
    public HotelManager() {
        _currentSeason = 0; // Começa na Primavera
        _allTrees = new ArrayList<>(); // Inicializa a lista de árvores
        employees = new ArrayList<>();
        animals = new ArrayList<>();
    }
  
  /**
   * Creates a new hotel (resets the state of the application) and removes any
   * associated file.
   */
  public void createNewHotel() {
    _hotel = new Hotel();
    _filename = null;
    _hasChanged = true; // O estado mudou

  }

  /**
   * Saves the serialized application's state into the file associated to the
   * current network.
   *
   * @throws FileNotFoundException           if for some reason the file cannot be
   *                                         created or opened.
   * @throws MissingFileAssociationException if the current network does not have
   *                                         a file.
   * @throws IOException                     if there is some error while
   *                                         serializing the state of the network
   *                                         to disk.
   **/
  public void save() throws FileNotFoundException, MissingFileAssociationException, IOException {
    if (_filename == null) {
      throw new MissingFileAssociationException();
    }
    saveAs(_filename);
    _hasChanged = false; // Resetar a flag após salvar

  }

  /**
   * Saves the serialized application's state into the specified file. The current
   * network is
   * associated to this file.
   *
   * @param filename the name of the file.
   * @throws FileNotFoundException           if for some reason the file cannot be
   *                                         created or opened.
   * @throws MissingFileAssociationException if the current network does not have
   *                                         a file.
   * @throws IOException                     if there is some error while
   *                                         serializing the state of the network
   *                                         to disk.
   **/
  public void saveAs(String filename) throws FileNotFoundException, MissingFileAssociationException, IOException {
    try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(filename))) {
      oos.writeObject(_hotel);
      _filename = filename;
      _hasChanged = false; // Resetar a flag após salvar

    }
  }
 
  /**
   * @param filename name of the file containing the serialized application's
   *                 state
   *                 to load.
   * @throws UnavailableFileException if the specified file does not exist or
   *                                  there is
   *                                  an error while processing this file.
   **/
  public void load(String filename) throws UnavailableFileException {
    try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(filename))) {
      _hotel = (Hotel) ois.readObject();
      _filename = filename;
      _hasChanged = false; // Depois de carregar, o estado não foi alterado
    } catch (IOException | ClassNotFoundException e) {
      throw new UnavailableFileException("Erro a processar ficheiro");
    }
  }

  /**
   * Read text input file and initializes the current zoo hotel (which should be
   * empty)
   * with the domain entitiesi representeed in the import file.
   *
   * @param filename name of the text input file
   * @throws ImportFileException if some error happens during the processing of
   *                             the
   *                             import file.
   **/
  public void importFile(String filename) throws ImportFileException {
    try {
        _hotel.importFile(filename);
        _hasChanged = true; // O estado mudou ao importar o arquivo
    } catch (IOException e) {
        throw new ImportFileException("Erro a processar ficheiro: " + e.getMessage());
    } catch (UnrecognizedEntryException e) {
        System.out.println("Entrada não reconhecida: " + e.getMessage());
        throw new ImportFileException("Entrada não reconhecida no ficheiro: " + e.getMessage());
    }
}


  /**
   * Verifica se houve alterações no estado do hotel.
   * 
   * @return true se houve mudanças, false caso contrário.
   */
  public boolean hasChanged() {
    return _hasChanged;
  }

  /**
   * Returns the zoo hotel managed by this instance.
   *
   * @return the current zoo hotel
   **/
  public final Hotel getHotel() {
    return _hotel;
  }

  public String getFilename() {
    return _filename;
  }


  public int getCurrentSeason() {
    return _currentSeason; // Retorna a estação atual
  }

    public void advanceSeason() {
      _currentSeason = (_currentSeason + 1) % 4; // Avança para a próxima estação (0 a 3)
    }

    public List<Arvore> getAllTrees() {
      return new ArrayList<>(_allTrees); // Retorna uma cópia da lista de árvores
    }

    // Método para adicionar árvores à lista (para fins de exemplo)
    public void addTree(Arvore tree) {
      _allTrees.add(tree);
    }


    public List<Employee> getEmployees() {
        return employees;
    }

    public List<Animal> getAnimals() {
        return animals;
    }

}
