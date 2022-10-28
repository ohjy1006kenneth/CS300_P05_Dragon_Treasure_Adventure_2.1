import java.util.Random;

public class Dragon extends Character implements Moveable {
  private Random randGen; // random num generator used for moving
  private static final String DRAGON_WARNING = "You hear a fire breathing nearby!\n";
  private static final String DRAGON_ENCOUNTER = "Oh no! You ran into the fire breathing dragon!\n";

  /**
   * Constructor for a Dragon object. Initializes all instance fields. The label should be "DRAGON"
   * by default.
   * 
   * @param currentRoom the room that the Dragon starts in
   * @throws IllegalArgumentException with a descriptive message if currentRoom is not a
   *                                  TreasureRoom
   */
  public Dragon(Room currentRoom) throws IllegalArgumentException {
    super(currentRoom, "DRAGON");
    randGen = new Random();
    if (currentRoom instanceof TreasureRoom == false) {
      throw new IllegalArgumentException("Dragon()-Error: currentRoom is not a TreasureRoom.");
    }
  }

  /**
   * Moves the Dragon to the destination room.
   * 
   * @param destination the Room to change it to
   * @return true if the change was successful, false otherwise
   */
  public boolean changeRoom(Room destination) {
    if (this.getAdjacentRooms().contains(destination)) {
      this.setCurrentRoom(destination);
    }
    return this.getCurrentRoom() == destination;
  }

  /**
   * Checks if the dragon can move to the given destination. A valid move is the destination not a
   * PortalRoom.
   * 
   * @param destination the room to check if the dragon can move towards
   * @return true if they can, false otherwise
   */
  public boolean canMoveTo(Room destination) {
    if (destination instanceof PortalRoom) {
      return false;
    }
    return true;
  }

  /**
   * Picks randomly ONCE an adjacent room to move into.
   * 
   * @return the room that this Dragon should try to move into
   */
  public Room pickRoom() {
    int max = this.getAdjacentRooms().size();
    int min = 0;
    int randomNumber;

    while (true) {
      randomNumber = randGen.nextInt(max) + min;
      if (canMoveTo(this.getCurrentRoom().getAdjacentRooms().get(randomNumber))) {
        break;
      }
    }
    return this.getAdjacentRooms().get(randomNumber);
  }

  /**
   * Getter for DRAGON_WARNING.
   * 
   * @return the string for warning about a dragon being nearby
   */
  public static String getDragonWarning() {
    return DRAGON_WARNING;
  }

  /**
   * Getter for DRAGON_ENCOUNTER.
   * 
   * @return the string for letting the player know they ran into the dragon
   */
  public static String getDragonEncounter() {
    return DRAGON_ENCOUNTER;
  }
}
