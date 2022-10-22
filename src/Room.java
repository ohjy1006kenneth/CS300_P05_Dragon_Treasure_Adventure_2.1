import java.util.ArrayList;

/**
 * Class to represent each room of the dungeon as an object
 * 
 * @author Kenneth Oh
 */
public class Room {
  private String description; // verbal description of the room
  private ArrayList<Room> adjRooms; // list of all rooms directly connect
  private final int ID; // a "unique" identifier for each room
  protected static processing.core.PApplet processing; // PApplet object which the rooms will use to
  // draw stuff to the GUI
  private processing.core.PImage image; // stores the image that corresponds to the background of a
                                        // room

  /**
   * Constructor for a Room object. Initializes all instance data fields.
   * 
   * @param ID          the ID that this Room should have
   * @param description the verbal description this Room should have
   * @param image       the image that should be used as background when drawing this Room
   */
  public Room(int ID, String description, processing.core.PImage image) {
    this.ID = ID;
    this.description = description;
    this.image = image;
  }

  /**
   * Getter for ID
   * 
   * @return the ID of this Room
   */
  public int getID() {
    return this.ID;
  }

  /**
   * Getter for description
   * 
   * @return the verbal description of this Room
   */
  public String getDescription() {
    return this.description;
  }

  /**
   * Getter for the list of adjacentRooms
   * 
   * @return the list of adjacent rooms
   */
  public ArrayList<Room> getAdjacentRooms() {
    return this.adjRooms;
  }

  /**
   * Sets the processing for the class
   * 
   * @param processing
   */
  public static void setProcessing(processing.core.PApplet processing) {

  }

}
