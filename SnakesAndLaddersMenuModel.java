/** SnakesAndLaddersMenuModel class
  * Class for the controllers of the buttons in the menu frame
  * @since Jan 22, 2023
  * @author Naeem Hussain
  */

import javax.swing.*;
import java.awt.*;

public class SnakesAndLaddersMenuModel extends Object
{
  // Frames for main and menu GUI's
  private JFrame mainFrame;
  private JFrame menuFrame;
  
  SnakesAndLaddersMenuView menuView; 
  SnakesAndLaddersMainModel mainModel = new SnakesAndLaddersMainModel (this, this.mainFrame);
  
  private String playerName; // User's input of their name
  private int totalRounds; // User's input of the total rounds they wish to play
  
  private int chosenColour; // Corresponding number to colours
  private int randomComputerColour; // Randomly generated colour of the computer piece
  
  private boolean isColourChosen; // Indicates if a colour is currently chosen or not
  
  /** Default constructor for the menu model
    * @param menuFrame Frame of the menu */
  public SnakesAndLaddersMenuModel (JFrame menuFrame)
  {
    super ();
    
    this.menuFrame = menuFrame; // Connects frame
    
    chosenColour = 6;
    isColourChosen = false;
  }
  
  /** Method that sets the menu GUI
    * @param currentGUI menu GUI */
  public void setMenuGUI (SnakesAndLaddersMenuView currentGUI)
  {
    this.menuView = currentGUI;
  }
  
  /** Accessor method for colourChosen
    * @returns chosenColour The corresponding number of the user's colour choice */
  public int getColourNum ()
  {
    return this.chosenColour;
  }
  
  /** Method that sets the chosen colour corresponding number
    * @param colourNum number that matches with the 1-6 colours */
  public void setColourNum (int colourNum)
  {
    this.chosenColour = colourNum;
  }
  
  /** Accessor method for isColourChosen
    * @returns isColourChosen If the user has chosen a colour currently */
  public boolean getIsColourChosen ()
  {
    return this.isColourChosen;
  }
  
  /** Method that sets isColourChosen
    * @param isColourChosen indicates if a colour is currently selected */
  public void setIsColourChosen (boolean isColourChosen)
  {
    this.isColourChosen = isColourChosen;
  }
  
  /** Accessor method for AI's corresponding number colour
    * @returns randomComputerColour The computer's auto-generated number */
  public int getComputerColour ()
  {
    return this.randomComputerColour;
  }
  
  /** Method that sets the user's name
    * @param name the user's inputted name */
  public void setPlayerName (String name)
  {
    this.playerName = name;
  }
  
  /** Accessor method for the player's name
    * @returns playerName the user's name */
  public String getPlayerName ()
  {
    return this.playerName;
  }
  
  /** Method that sets the total rounds as the user's input
    * @param totalRounds the user's inputted choice of the number of rounds */
  public void setTotalRounds (int totalRounds)
  {
    this.totalRounds = totalRounds;
  }
  
  /** Accessor method for the total number of rounds
    * @returns totalRounds the total rounds */
  public int getTotalRounds ()
  {
    return this.totalRounds;
  }
  
  public void back ()
  {
    this.menuView.backPressed ();
    this.chosenColour = 6;
    this.isColourChosen = false;
  }
  
  /** Method that randomizes a number between 1-6 to choose a random colour for the computer */
  public void setComputerColour ()
  {
    this.randomComputerColour = (int)(Math.random () * 6 + 0);
    
    if (this.randomComputerColour == this.chosenColour)
    {
      while (this.randomComputerColour == this.chosenColour)
      {
        this.randomComputerColour = (int)(Math.random () * 6 + 0);
      }
    }
  }
  
  /** Method that opens the main game frame and closes the menu */
  public void openGame ()
  {
    this.setComputerColour (); // Sets the random generated computer colour
    
    // Randomizes special spaces on the board and the move values of each
    this.mainModel.getAndSortSpecialSpaces ();
    this.mainModel.getAndSortSpecialMoves ();
    
    // Creates main frame
    JFrame mainFrame = new JFrame ();
    SnakesAndLaddersMainModel mainModel = new SnakesAndLaddersMainModel (this, mainFrame);
    SnakesAndLaddersMainView mainView = new SnakesAndLaddersMainView (mainModel, this);
    
    mainFrame.setContentPane (mainView);
    mainFrame.setLocation (300,150);
    mainFrame.setSize (850,600);
    mainFrame.setTitle ("Snakes And Ladders");
    mainFrame.setDefaultCloseOperation (JFrame.EXIT_ON_CLOSE);
    mainFrame.pack ();
    mainFrame.setVisible (true);
    
    this.menuFrame.setVisible (false);
  }
}
