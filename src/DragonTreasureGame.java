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

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.Random;
import processing.core.PApplet;
import processing.core.PImage;

/**
 * Class that sets up the Dragon Treasure Adventure Game and handles control flow and game logic
 * 
 * @author Kenneth Oh
 */
public class DragonTreasureGame extends PApplet {

  private ArrayList<Room> roomList;
  private File roomInfo;
  private File mapInfo;
  private ArrayList<Character> characters;
  private boolean isDragonTurn = false;
  private int gameState = 0;

  @Override
  public void settings() {
    size(800, 600);
  }

  @Override
  public void setup() {
    this.getSurface().setTitle("Dragon Treasure Adventure"); // sets the title of the window
    this.imageMode(PApplet.CORNER); // Images are drawn using the x,y-coordinate
    // as the top-left corner
    this.rectMode(PApplet.CORNERS); // When drawing rectangles interprets args
    // as top-left corner and bottom-right corner respectively
    this.focused = true; // window will be active upon running program
    this.textAlign(CENTER); // sets the text alignment to center
    this.textSize(20); // sets the font size for the text

    roomInfo = new File("roominfo.txt");
    mapInfo = new File("map.txt");

    roomList = new ArrayList<Room>();
    characters = new ArrayList<Character>();

    loadRoomInfo();
    loadMap();
    loadCharacters();

    Room.setProcessing(this);
    TreasureRoom.setTreasureBackground(this.loadImage("images" + File.separator + "treasure.jpg"));
    PortalRoom.setPortalImage(this.loadImage("images" + File.separator + "portal.png"));
  }

  public void draw() {
    characters.get(1).getCurrentRoom().draw();

    // Check for Dragon Warning
    if (((Player) characters.get(1)).isDragonNearby((Dragon) characters.get(2))) {
      System.out.println(Dragon.getDragonWarning());
    }

    // Check for Portal Warning
    if (((Player) characters.get(1)).isPortalNearby()) {
      System.out.println(PortalRoom.getPortalWarning());
    }

    // Check for Treasure Warning
    if (((Player) characters.get(1)).isTreasureNearby()) {
      System.out.println(TreasureRoom.getTreasureWarning());
    }

    // Check if the player can grab the key
    if (characters.get(1).getCurrentRoom().equals(characters.get(0).getCurrentRoom())) {
      ((Player) characters.get(1)).obtainKey();
      System.out.println("KEY OBTAINED");
    }

    // Check if the player needs to teleport
    if (((Player) characters.get(1)).teleport()) {
      System.out.println("The player has teleported");
    }

    // Check if it is the dragon's turn to move
    if (isDragonTurn) {
      if (((Dragon) characters.get(2)).changeRoom(((Dragon) characters.get(2)).pickRoom())) {
        isDragonTurn = false;
      }
    }

    // Check if the player won
    if (((TreasureRoom) roomList.get(8)).playerCanGrabTreasure((Player) characters.get(1))) {
      gameState = 1;
      System.out.println("You won");
    }

    // Check if the player loss
    if (characters.get(1).getCurrentRoom().equals(characters.get(2).getCurrentRoom())) {
      gameState = 2;
      System.out.println(Dragon.getDragonEncounter());
      System.out.println("You Lost.");
    }
  }

  public static void main(String[] args) {
    PApplet.main("DragonTreasureGame");
  }

  /**
   * Loads in room info using the file stored in roomInfo
   * 
   * @author Michelle
   */
  private void loadRoomInfo() {
    System.out.println("Loading rooms...");
    Scanner fileReader = null;
    try {

      // scanner to read from file
      fileReader = new Scanner(roomInfo);

      // read line by line until none left
      while (fileReader.hasNext()) {
        String nextLine = fileReader.nextLine();

        // parse info and create new room
        String[] parts = nextLine.split(" \\| ");
        int ID = Integer.parseInt(parts[1].trim()); // get the room id
        String imageName = null;
        String description = null;
        PImage image = null;
        Room newRoom = null;

        if (parts.length >= 3) {
          imageName = parts[2].trim();
          image = this.loadImage("images" + File.separator + imageName);

        }

        if (parts.length == 4) {
          description = parts[3].trim(); // get the room description
        }

        switch (parts[0].trim()) {
          case "S":
            newRoom = new StartRoom(ID, image);
            break;
          case "R":
            newRoom = new Room(ID, description, image);
            break;
          case "P":
            newRoom = new PortalRoom(ID, description, image);
            break;
          case "T":
            newRoom = new TreasureRoom(ID);
            break;
          default:
            break;
        }

        if (newRoom != null) {
          roomList.add(newRoom);
        }
      }
    } catch (IOException e) { // handle checked exception
      e.printStackTrace();
    } finally {
      if (fileReader != null)
        fileReader.close(); // close scanner regardless of what happened for security reasons :)
    }
  }

  /**
   * Loads in room connections using the file stored in mapInfo
   * 
   * @author Michelle
   */
  private void loadMap() {
    System.out.println("Loading map...");
    Scanner fileReader = null;
    try {
      // scanner to read from file
      fileReader = new Scanner(mapInfo);

      // read line by line until none left
      while (fileReader.hasNext()) {

        // parse info
        String nextLine = fileReader.nextLine();
        String parts[] = nextLine.split(" ");
        int id = Integer.parseInt(parts[0]);

        Room toEdit = getRoomByID(id); // get the room we need to update info for adjacent rooms

        // add all the rooms to the adj room list of toEdit
        for (int i = 1; i < parts.length; i++) {
          Room toAdjAdd = getRoomByID(Integer.parseInt(parts[i]));
          toEdit.addToAdjacentRooms(toAdjAdd);
        }
      }
    } catch (IOException e) { // handle checked exception
      e.printStackTrace();
    } finally { // close scanner regardless of what happened for security reasons :)
      if (fileReader != null)
        fileReader.close();
    }
  }

  /**
   * Get the room objected associated with the given ID.
   * 
   * @param id the ID of the room to retrieve
   * @return the Room that corresponds to that id
   * @author Michelle
   */
  private Room getRoomByID(int id) {
    int indexToEdit = roomList.indexOf(new Room(id, "dummy", null));
    Room toEdit = roomList.get(indexToEdit);
    return toEdit;
  }

  private void loadCharacters() {
    System.out.println("Adding characters...");
    characters.add(new Character(getRoomByID(5), "KEYHOLDER"));
    characters.add(new Player(getRoomByID(1)));
    characters.add(new Dragon(getRoomByID(9)));
  }

  @Override
  public void keyPressed() {
    
  }
}
