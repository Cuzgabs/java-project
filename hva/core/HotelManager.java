package hva.core;

import hva.core.exception.*;
import java.io.*;

/**
 * Class representing the manager of this application. It manages the current
 * zoo hotel.
 **/
public class HotelManager {
  private Hotel _hotel = new Hotel();
  private String _filename;

  /**
   * Creates a new hotel (resets the state of the application) and removes any
   * associated file.
   */
  public void createNewHotel() {
    _hotel = new Hotel();
    _filename = null;
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
    } catch (IOException | ClassNotFoundException e) {
      throw new UnavailableFileException(filename);
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
    } catch (IOException | UnrecognizedEntryException e) {
      throw new ImportFileException(filename, e);
    }
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
}
