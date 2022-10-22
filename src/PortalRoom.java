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

import java.util.Random;


public class PortalRoom extends Room {
  private Random randGen; // random number generator for location picking
  private static final String PORTAL_WARNING = "You feel a distortion in space nearby.\n";
  private static final String TELEPORT_MESSAGE =
      "The space distortion teleported you to another room!\n";
  private static processing.core.PImage portalImage; // image of a portal to be shown in all portal
                                                     // rooms

  public PortalRoom(int ID, String description, processing.core.PImage image) {
    super(ID, description, image);
  }

  /**
   * Getter for PORTAL_WARNING
   * 
   * @return the string for warning about a portal being nearby
   */
  public static String getPortalWarning() {
    return PORTAL_WARNING;
  }

  /**
   * Getter for TELEPORT_MESSAGE
   * 
   * @return the string for letting the player know they were teleported
   */
  public static String getTeleportMessage() {
    return TELEPORT_MESSAGE;
  }

  /**
   * Picks an adjacent room at random for the player to teleport to
   * 
   * @return the room that player should immediately be moved to
   */
  public Room getTeleportLocation() {
    int max = this.getAdjacentRooms().size();
    int min = 1;
    int randomNumber = randGen.nextInt(max + 1 - min) + min;
    return this.getAdjacentRooms().get(randomNumber);
  }

  /**
   * Draws this PortalRoom to the window by drawing the background image, a rectangle, some text,
   * and the portal
   */
  @Override
  public void draw() {
    super.draw();
    processing.image(portalImage, 325, 225);
  }

  /**
   * Sets the portal image for the PortalRoom class
   * 
   * @param portalImage the image to represent the portal
   */
  public static void setPortalImage(processing.core.PImage portalImage) {
    PortalRoom.portalImage = portalImage;
  }
}
