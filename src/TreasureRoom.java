//////////////// FILE HEADER (INCLUDE IN EVERY FILE) //////////////////////////
//
// Title: Dragon Treasure Adventure 2.0
// Course: CS 300 Fall 2022
//
// Author: Kenneth Oh
// Email: oh87@wisc.edu
// Lecturer: Jeff Nyhoff
//
//////////////////// PAIR PROGRAMMERS COMPLETE THIS SECTION ///////////////////
//
// Partner Name: NONE
// Partner Email: NONE
// Partner Lecturer's Name: NONE
//
// VERIFY THE FOLLOWING BY PLACING AN X NEXT TO EACH TRUE STATEMENT:
// _X__ Write-up states that pair programming is allowed for this assignment.
// __X_ We have both read and understand the course Pair Programming Policy.
// __X_ We have registered our team prior to the team registration deadline.
//
///////////////////////// ALWAYS CREDIT OUTSIDE HELP //////////////////////////
//
// Persons: NONE
// Online Sources: NONE
//
///////////////////////////////////////////////////////////////////////////////

public class TreasureRoom extends Room {
  private static final String TREASURE_WARNING = "You sense that there is treasure nearby.\n";
  private static processing.core.PImage treasureBackground; // the images ALWAYS used for treasure
                                                            // rooms

  /**
   * Constructor for a TreasureRoom object and have a description of "In the back of this room, you
   * spot a treasure chest. It is locked...". Initializes all instance data fields.
   * 
   * @param ID the ID to give to this object
   */
  public TreasureRoom(int ID) {
    super(ID, TREASURE_WARNING, treasureBackground);
  }

  /**
   * Getter for TREASURE_WARNING
   * 
   * @return the string for warning about treasure being nearby
   */
  public static String getTreasureWarning() {
    return TREASURE_WARNING;
  }

  /**
   * Determines whether or not the player can open the treasure chest in the room
   * 
   * @param p the Player to check if they can open the chest
   * @return true if the player has the key and is in this TreasureRoom, false otherwise
   */
  /*
   * public boolean playerCanGrabTreasure(Player p) {
   * 
   * }
   */

  /**
   * Sets the background image for the TreasureRoom class
   * 
   * @param treasureBackground the image to be the background
   */
  public static void setTreasureBackground(processing.core.PImage treasureBackground) {
    TreasureRoom.treasureBackground = treasureBackground;
  }

}
