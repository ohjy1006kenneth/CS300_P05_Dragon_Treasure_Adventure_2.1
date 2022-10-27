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

import java.util.ArrayList;

public class Character {

  private Room currentRoom; // current room the character is in
  private String label; // a label giving a basic description of the character


  /**
   * Constructor for a Character object. Initializes all instance fields.
   * 
   * @param currentRoom the room that the Character is located in
   * @param label       a descriptive label of this Character
   * @throws IllegalArgumentException with a descriptive message if currentRoom is null
   */
  public Character(Room currentRoom, String label) throws IllegalArgumentException {
    if (currentRoom == null) {
      throw new IllegalArgumentException("Character()-Error: currentRoom is null.");
    }
    this.currentRoom = currentRoom;
    this.label = label;
  }

  /**
   * Getter for the current room of this Character.
   * 
   * @return the room where the character is
   */
  public Room getCurrentRoom() {
    return this.currentRoom;
  }

  /**
   * Getter for the label of this Character.
   * 
   * @return this Character's descriptive label
   */
  public String getLabel() {
    return this.label;
  }

  /**
   * Gets the list of rooms adjacent to this Character.
   * 
   * @return an ArrayList of rooms adjacent to this character
   */
  public ArrayList<Room> getAdjacentRooms() {
    return this.currentRoom.getAdjacentRooms();
  }

  /**
   * Sets the current room to the one given.
   * 
   * @param newRoom the room that should become the current room
   */
  public void setCurrentRoom(Room newRoom) {
    this.currentRoom = newRoom;
  }
}
