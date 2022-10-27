import java.util.ArrayList;

public class Player extends Character implements Moveable {
  private boolean hasKey;

  /**
   * Constructor for player object. The label should be "PLAYER" and not have a key by default.
   * 
   * @param currentRoom the room that the player should start in
   * @throws IllegalArgumentException if the currentRoom is not a StartRoom
   */
  public Player(Room currentRoom) throws IllegalArgumentException {
    super(currentRoom, "PLAYER");
    this.hasKey = false;
  }

  /**
   * Determines if the player has the key.
   * 
   * @return true if the player has the key, false otherwise
   */
  public boolean hasKey() {
    return this.hasKey;
  }

  /**
   * Gives player the key.
   */
  public void obtainKey() {
    this.hasKey = true;
  }

  /**
   * Moves the Player to the destination room.
   * 
   * @param destination the Room to change it to
   * @return true if the change was successful, false otherwise
   */
  public boolean changeRoom(Room destination) {
    this.setCurrentRoom(destination);
    return this.getCurrentRoom() == destination;
  }

  /**
   * Checks if the player can move to the given destination. A valid move is the destination is a
   * room adjacent to the player.
   * 
   * @param destination the room to check if the player can move towards
   * @return true if they can, false otherwise
   */
  public boolean canMoveTo(Room destination) {
    ArrayList<Room> adjRooms = this.getCurrentRoom().getAdjacentRooms();
    return adjRooms.contains(destination);
  }

  /**
   * Checks if the player needs to teleport and move them if needed.
   * 
   * @return true if a teleport occurred, false otherwise
   */
  public boolean teleport() {
    if (this.getCurrentRoom() instanceof PortalRoom) {
      this.setCurrentRoom(((PortalRoom) this.getCurrentRoom()).getTeleportLocation());
      return true;
    }
    return false;
  }

  /**
   * Determines whether or not a portal room is nearby. A portal room is considered nearby if it is
   * one of the adjacent rooms.
   * 
   * @return true if a portal room is nearby, false otherwise
   */
  public boolean isPortalNearby() {
    ArrayList<Room> adjRooms = this.getCurrentRoom().getAdjacentRooms();
    for (int i = 0; i < adjRooms.size(); i++) {
      if (adjRooms.get(i) instanceof PortalRoom) {
        return true;
      }
    }
    return false;
  }

  /**
   * Determines whether or not the treasure room is nearby. The treasure room is considered nearby
   * if it is one of the adjacent rooms.
   * 
   * @return true if the treasure room is nearby, false otherwise
   */
  public boolean isTreasureNearby() {
    ArrayList<Room> adjRooms = this.getCurrentRoom().getAdjacentRooms();
    for (int i = 0; i < adjRooms.size(); i++) {
      if (adjRooms.get(i) instanceof TreasureRoom) {
        return true;
      }
    }
    return false;
  }
}
