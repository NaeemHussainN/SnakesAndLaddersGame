/**
 * 
 SnakesAndLaddersModel class is responsible for handling the game logic of the Snakes and Ladders game.
 It includes methods for rolling dice, moving the player and computer, checking for ladders, snakes, special spaces, knockouts, and wins.
 It also includes methods for displaying and updating game statistics and for saving the game results to a file.
 @author Naeem Hussain / Waleed Rashid
 @since Jan 22, 2023
 */
import java.io.PrintWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*; 
import java.util.Scanner;
import javax.swing.*;

public class SnakesAndLaddersMainModel {
  //Instance Variables
  private static final int BOARD_SIZE = 100; // The size of the board
  private static final int[] SNAKES = {45, 63, 95}; // The position at which the snakes are at
  private static final int[] SNAKE_ENDS = {25, 38, 32}; // The position at which the snakes end
  private static final int[] LADDERS = {18, 29, 68}; // The space at which the ladders begin 
  private static final int[] LADDERS_ENDS = {42, 77, 90}; // The space where the ladders end
  private static final int[] SPECIAL_SPACES = new int [4]; // An array to hold randomized values for special squares on the board
  private static final int[] SPECIAL_MOVES = new int [4]; // An array that holds randomized values to move forward on the board
  private JFrame mainFrame;
  SnakesAndLaddersMainView mainView;
  SnakesAndLaddersMenuModel menuModel;
  private int playerPosition; // Stores the current Player Position
  private int computerPosition;  // Stores the current Computer Position
  private int currentRound;  // Stores the current round
  private int totalRounds; // Stores the total amount of rounds
  private int playerWins;  // Stores the total Player Wins
  private int computerWins; // Stores the total Computer Wins
  private int playerTotalKnockouts; // Stores the total Player Knockouts
  private int computerTotalKnockouts; // Stores the total Computer Knockouts
  private int playerRoundKnockouts; // Stores the amount of time Player knocked out the Computer
  private int computerRoundKnockouts; // Stores the amount of time Computer knocked out the Player
  private String playerLogText; // Displays the current and previous moves / actions of the player
  private String computerLogText; // Displays the current and previous moves / actions of the computer
  private String computerMessage; // Displays a message from computer 
  private String winner; // Holds the name of the winner
  private String [] colours = new String [] {"RED", "BLUE", "GREEN", "YELLOW", "PURPLE", "ORANGE"}; // An Array to hold the colours of the pieces
  private boolean gameEnded; // Declares whether game has ended
  private boolean playerTurn; // Declares whether its player turn 
  private boolean onLastRound; // Declares if the game is in its last round
  private boolean isWinner; // Declares if someone has won the game yet
  private int dice1Num; // Stores the value of the first dice number
  private int dice2Num; // Stores the value of the second dice number
  private int total; // Stores the value of the total dice roll
  private int yourRoll; // Stores the player roll   
  private int computerRoll; // Stores the computer roll
  private int playerLogLines; // Stores the current number of lines in the player log
  private int computerLogLines; // Stores the current number of lines in the computer log
  private int spacesTo100; // Stores the number of spaces needed to get to 100 from current position
  private int bounceBackPosition; // Stores the position new value after the bounce back feature
  //Stores the stats of the player and computer
  private String playerStatsText; 
  private String computerStatsText; 
  private int playerNumSnakesUsed; 
  private int computerNumSnakesUsed;
  private int playerNumLaddersUsed;
  private int computerNumLaddersUsed;
  private int playerNumSpecialSpacesUsed;
  private int computerNumSpecialSpacesUsed;
  /**
   * 
   Default constructor for the main playing area GUI
   @param menuModel Model of the menu's model
   @param mainFrame Frame for main GUI
   */
  public SnakesAndLaddersMainModel(SnakesAndLaddersMenuModel menuModel, JFrame mainFrame) {
    super();
    //Connects the model to the menuModel and the GUI
    this.menuModel = menuModel; 
    this.mainFrame = mainFrame;
    //Initializes the Instance Variables
    playerPosition = 1;
    computerPosition = 1;
    currentRound = 1;
    totalRounds = 0;
    playerWins = 0;
    computerWins = 0;
    playerTotalKnockouts = 0;
    computerTotalKnockouts = 0;
    playerRoundKnockouts = 0;
    computerRoundKnockouts = 0;
    playerLogText = this.menuModel.getPlayerName () + " Log";
    computerLogText = "Burns Log";
    computerMessage = this.computerRandomIntro ();
    this.dice1Num = 1;
    this.dice2Num = 1;
    this.total = 0;
    this.playerLogLines = 1;
    this.computerLogLines = 1;
    spacesTo100 = 0;
    isWinner = false;
    winner = "";
    playerStatsText = "";
    computerStatsText = "";
  }
  /** Method which sets the GUI of the Model
    * @param currentGUI - connects the GUI to the Model  */
  public void setGUI(SnakesAndLaddersMainView currentGUI) {
    this.mainView = currentGUI;
  }
  /** Method which sets the Computer Position
    * @param computerPosition - the computer's current position */
  public void setComputerPosition(int computerPosition) {
    this.computerPosition = computerPosition;
  }
  /**
   * 
   Returns the current player position
   @return int value of the player position
   */
  public int getPlayerPosition() {
    return playerPosition;
  }
  /**
   * 
   Returns the current computer position
   @return int value of the computer position
   */
  public int getComputerPosition() {
    return computerPosition;
  }
  /**
   * 
   Returns the current round number
   @return int value of the current round
   */
  public int getCurrentRound() {
    return currentRound;
  }
  /**
   * 
   Returns if the game has ended
   @return boolean value indicating if the game has ended
   */
  public boolean getGameEnded ()
  {
    return this.gameEnded;
  }
  /**
   * 
   Returns the value of the first dice roll
   @return int value of the first dice roll
   */
  public int getDice1Num ()
  {
    return this.dice1Num;
  }
  /**
   * 
   Returns the value of the second dice roll
   @return int value of the second dice roll
   */
  public int getDice2Num ()
  {
    return this.dice2Num;
  }
  /**
   * Returns the value of the total dice roll
   @return int value of the total dice roll
   */
  public int getTotal ()
  {
    return this.total;
  }
  /**
   * 
   Returns if the game is on its last round
   @return boolean value indicating if the game is on its last round
   */
  public boolean getOnLastRound ()
  {
    return this.onLastRound;
  }
  /**Method to reset the game for the next round */
  public void playAgain ()
  {
    if (this.currentRound < this.menuModel.getTotalRounds ())
    {
      this.dice1Num = 1;
      this.dice2Num = 1;
      this.total = 0;
      this.yourRoll = 0;
      this.computerRoll = 0;
      this.playerRoundKnockouts = 0;
      this.computerRoundKnockouts = 0;
      this.playerLogText = this.menuModel.getPlayerName () + " Log";
      this.computerLogText = "Burns Log";
      this.playerLogLines = 1;
      this.computerLogLines = 1;
      playerPosition = 1;
      computerPosition = 1;
      this.gameEnded = true;
      this.isWinner = false;
      this.currentRound++;
    }
  }
  /**
   * 
   Returns the log text for the player
   @return String value of the player log text
   */
  public String getPlayerLogText ()
  {
    return this.playerLogText;
  }/**
   * 
   Returns the log text for the computer
   @return String value of the computer log text
   */
  public String getComputerLogText ()
  {
    return this.computerLogText;
  }
  /**
   * 
   Returns the message for the computer
   @return String value of the computer message
   */
  public String getComputerMessage ()
  {
    return this.computerMessage;
  }
  /** Method which sets the Player Position
    * @param playerPosition - the player's current position */
  public void setPlayerPosition(int playerPosition) {
    this.playerPosition = playerPosition;
  }
  /**
   * 
   Returns the current player turn
   @return boolean value indicating if it is the player's turn
   */
  public boolean getPlayerTurn ()
  {
    return this.playerTurn;
  }
  /**
   * 
   Returns if someone has won the game
   @return boolean value indicating if someone has won the game
   */
  public boolean getIsWinner ()
  {
    return this.isWinner;
  }
  /**
   * 
   Returns the name of the winner
   @return String value of the winner's name
   */
  public String getWinner ()
  {
    return this.winner;
  }
  /**
   * 
   Returns the player statistics text
   @return String value of the player statistics text
   */
  public String getPlayerStats ()
  {
    return this.playerStatsText;
  }
  /**
   * 
   Returns the computer statistics text
   @return String value of the computer statistics text
   */
  public String getComputerStats ()
  {
    return this.computerStatsText;
  }
  public void getAndSortSpecialSpaces ()
  {
    //Input 5 random numbers into the array
    for(int x = 0; x < SPECIAL_SPACES.length; x++)
    {
      SPECIAL_SPACES [x] = (int)(Math.random() * 97 + 3);
    }
    for (int i = 0; i < SPECIAL_SPACES.length - 1; i++) 
    {
      int minIndex = i;
      for (int j = i + 1; j < SPECIAL_SPACES.length; j++) 
      {
        if (SPECIAL_SPACES[j] < SPECIAL_SPACES[minIndex]) 
        {
          minIndex = j;
        }
      }
      int temp = SPECIAL_SPACES[i];
      SPECIAL_SPACES[i] = SPECIAL_SPACES[minIndex];
      SPECIAL_SPACES[minIndex] = temp;
    }
  }
  /**
   Rolls the dice for the player
   */
  public int rollDice ()
  {
    this.dice1Num = (int)(Math.random () * 6 + 1);
    this.dice2Num = (int)(Math.random () * 6 + 1);
    
    this.total = this.dice1Num + this.dice2Num;
    return this.total;
  }
  /**
   Method that handles the logic for bouncing a player back to the start when they exceed the board size.
   The player's current position and the number of spaces to the end of the board are checked.
   If the total of the roll and the current position is greater than the number of spaces to the end, the player's position is set to the end of the board minus the overshoot.
   The player's log text is also updated to reflect the bounce back.
   */
  public void bounceBack() {
    if (playerTurn == true) {
      spacesTo100 = BOARD_SIZE - playerPosition;
      
      if(this.total > spacesTo100){
        playerPosition = BOARD_SIZE - (this.total - spacesTo100);
        bounceBackPosition = playerPosition;
        setPlayerPosition (playerPosition);
        playerLogText = playerLogText + "\nBounced Back";
      }
    }
    else if (playerTurn == false) {
      spacesTo100 = BOARD_SIZE - computerPosition;
      
      if(this.total > spacesTo100){
        computerPosition = BOARD_SIZE - (this.total - spacesTo100);
        bounceBackPosition = computerPosition;
        setComputerPosition (computerPosition);
        computerLogText = computerLogText + "\nBounced Back";
      }
    }
  }
  /**
   * 
   Method for moving the player's position on the board.
   The player's roll and current position are passed as parameters.
   The new position is calculated as the current position plus the roll.
   */
  public void movePlayer(int playerPosition, int playerRoll)
  {
    playerRoll = this.total;
    yourRoll = playerRoll;
    int newPosition = this.playerPosition + playerRoll;
    // If the new position exceeds the board size, the bounceBack method is called and the player's log text is updated to reflect the move.
    if (newPosition > BOARD_SIZE)
    {
      bounceBack ();
      this.playerLogText = this.playerLogText + "\n" + playerPosition + " -> " + bounceBackPosition;
    }
    // Else, the player's position is set to the new position and the player's log text is updated to reflect the move.
    else
    {
      setPlayerPosition(newPosition);
      this.playerLogText = this.playerLogText + "\n" + playerPosition + " -> " + newPosition;
    }
    //The player's log lines are also incremented and checked.
    playerLogLines++;
    checkPlayerLogLines ();
  }
  /**
   Method for moving the computer's position on the board.
   The computer's roll and current position are passed as parameters.
   The new position is calculated as the current position plus the roll.
   
   
   */
  public void moveComputer(int computerPosition, int computerRoll)
  {
    computerRoll = this.total;
    this.computerRoll = computerRoll;
    int newPosition = computerPosition + computerRoll;
    //If the new position exceeds the board size, the bounceBack method is called and the computer's log text is updated to reflect the move.
    if (newPosition > BOARD_SIZE)
    {
      bounceBack ();
      this.computerLogText = this.computerLogText + "\n" + computerPosition + " -> " + bounceBackPosition;
    }
    // Else, the computer's position is set to the new position and the computer's log text is updated to reflect the move.
    else
    {
      setComputerPosition(newPosition);
      this.computerLogText = this.computerLogText + "\n" + computerPosition + " -> " + newPosition;
    }
    // The computer's log lines are also incremented and checked.
    computerLogLines++;
    checkComputerLogLines ();
  }
  /**
   * 
   Returns the roll of the player
   @return int value of the player roll
   */
  public int getYourRoll ()
  {
    return this.yourRoll;
  }
  /**
   * 
   Returns the roll of the computer
   @return int value of the computer roll
   */
  public int getComputerRoll ()
  {
    return this.computerRoll;
  }
  /**
   * 
   Check if a player knocked out the opponent
   @param currentPosition current position of the player
   @param opponentPosition current position of the opponent
   */
  public void checkKnockOut(int currentPosition, int opponentPosition) {
    if (currentPosition == opponentPosition) {
      if (playerTurn== true)
      {
        setComputerPosition(1);
        computerLogText = computerLogText + "\nKnocked out";
        computerRandomKnockOutReply ();
        playerTotalKnockouts++;
        playerRoundKnockouts++;
      }
      else if(playerTurn == false)
      {
        setPlayerPosition(1);
        playerLogText = playerLogText + "\nKnocked out";
        computerRandomKnockedOutReply ();
        computerTotalKnockouts++;
        computerRoundKnockouts++;
      }
    }
  }
  /**
   * 
   Plays the game
   */
  public void playGame() {
    this.computerMessage = "";
    if (this.currentRound == this.menuModel.getTotalRounds ())
    {
      this.onLastRound = true;
    }
    computerTurn ();
    playerTurn ();
    this.gameEnded = false;
  }
  /**
   * 
   Player's turn
   */
  public void playerTurn ()
  {
    int playerRoll = 0;
    playerTurn = true;
    playerRoll = rollDice();
    movePlayer(playerPosition, playerRoll);
    checkLadders(playerPosition);
    checkSnakes(playerPosition);
    checkKnockOut(playerPosition, computerPosition);
    checkSpecialSpaces(playerPosition);
    
    checkWin();
  }
  /**
   * 
   Computer's turn
   */
  public void computerTurn()
  {
    int computerRoll = 0;
    playerTurn = false;
    computerRoll = rollDice();
    moveComputer(computerPosition, computerRoll);
    checkLadders(computerPosition);
    checkSnakes(computerPosition);
    checkKnockOut(computerPosition, playerPosition);
    checkSpecialSpaces(computerPosition);
  }
  /**
   * 
   Check if the number of lines in the player log has reached the maximum and resets it
   */
  public void checkPlayerLogLines ()
  {
    if (playerLogLines == 10)
    {
      playerLogLines = 1;
      playerLogText = this.menuModel.getPlayerName () + " Log";
    }
  }
  /**
   * 
   Check if the number of lines in the computer log has reached the maximum and resets it
   */
  public void checkComputerLogLines ()
  {
    if (computerLogLines == 10)
    {
      computerLogLines = 1;
      computerLogText = "Burns Log";
    }
  }
  /**
   * 
   Generates a random introductory message from the computer
   
   @return a string value of the computer's introductory message
   */
  public String computerRandomIntro ()
  {
    int random = (int)(Math.random () * 100 + 1);
    
    if (random >= 0 && random <= 33)
      computerMessage = "Ready to lose?";
    
    else if (random >= 34 && random <= 67)
      computerMessage = "Didn't I beat you last time?";
    
    else if (random >= 68 && random <= 100)
      computerMessage = "Let's play!";
    
    return computerMessage;
  }
  /**
   * 
   Generates a random message from the computer when the player lands on a snake
   */
  public void computerRandomSnakeReply ()
  {
    int random = (int)(Math.random () * 100 + 1);
    
    if (random >= 0 && random <= 33)
      computerMessage = "You're garbage!";
    
    else if (random >= 34 && random <= 67)
      computerMessage = "You stink!";
    
    else if (random >= 68 && random <= 100)
      computerMessage = "Down the snake you go!";
  }
  /**
   * 
   Generates a random message from the computer when the player lands on a ladder
   */
  public void computerRandomLadderReply ()
  {
    int random = (int)(Math.random () * 100 + 1);
    
    if (random >= 0 && random <= 33)
      computerMessage = "Only losers use\nladders.";
    
    else if (random >= 34 && random <= 67)
      computerMessage = "You really need ladders\nto win?";
    
    else if (random >= 68 && random <= 100)
      computerMessage = "That ladder won't stop\nme from beating you!";
  }
  /**
   * 
   Generates a random message from the computer when the player wins the game
   */
  public void computerRandomWinReply ()
  {
    int random = (int)(Math.random () * 100 + 1);
    if (random >= 0 && random <= 33)
      computerMessage = "Beginner's luck.";
    else if (random >= 34 && random <= 67)
      computerMessage = "I'll beat you next time\njust watch!";
    else if (random >= 68 && random <= 100)
      computerMessage = "I went easy on you\ndon't get excited.";
  }
  /**
   * 
   Generates a random message from the computer when the computer wins the game
   */
  public void computerRandomLossReply ()
  {
    int random = (int)(Math.random () * 100 + 1);
    if (random >= 0 && random <= 33)
      computerMessage = "Better luck next\ntime!";
    else if (random >= 34 && random <= 67)
      computerMessage = "I'm failing you for\nplaying so bad!";
    else if (random >= 68 && random <= 100)
      computerMessage = "I can beat you\nin my sleep.";
  }
  /**
   * 
   Generates a random message from the computer when the computer is knocked out
   */
  public void computerRandomKnockOutReply ()
  {
    int random = (int)(Math.random () * 100 + 1);
    if (random >= 0 && random <= 33)
      computerMessage = "Get back here!";
    else if (random >= 34 && random <= 67)
      computerMessage = "I'll catch up!";
    else if (random >= 68 && random <= 100)
      computerMessage = "Wow that was brutal!";
  }
  /**
   * 
   Generates a random message from the computer when the player is knocked out
   */
  public void computerRandomKnockedOutReply ()
  {
    int random = (int)(Math.random () * 100 + 1);
    if (random >= 0 && random <= 33)
      computerMessage = "Haha get lost!";
    else if (random >= 34 && random <= 67)
      computerMessage = "One way ticket back to\nthe start!";
    else if (random >= 68 && random <= 100)
      computerMessage = "Dang you're taking up\nthis entire space, get\noutta here!";
  }
  /**
   * 
   Returns the value of a specific index in the SPECIAL_SPACES array
   @param index - Index of the value to be returned
   @return int value of the element at the given index
   */
  public int getSpecialSpaces (int index)
  {
    return this.SPECIAL_SPACES [index];
  }
  /**
   * 
   Returns the value of a specific index in the SPECIAL_MOVES array
   @param index - Index of the value to be returned
   @return int value of the element at the given index
   */
  public int getSpecialMoves (int index)
  {
    return this.SPECIAL_MOVES [index];
  }
  /**
   * 
   Generates and sorts 5 random values and assigns them to the array SPECIAL_MOVES
   */
  public void getAndSortSpecialMoves() {
    // Input 5 random numbers into the array
    for (int x = 0; x < SPECIAL_MOVES.length; x++) {
      SPECIAL_MOVES[x] = (int) (Math.random() * 10 + 1);
    }
    // Sort those numbers in descending order (Selection)
    for (int i = 0; i < SPECIAL_MOVES.length - 1; i++) {
      int maxIndex = i;
      for (int j = i + 1; j < SPECIAL_MOVES.length; j++) {
        if (SPECIAL_MOVES[j] > SPECIAL_MOVES[maxIndex]) {
          maxIndex = j;
        }
      }
      int temp = SPECIAL_MOVES[i];
      SPECIAL_MOVES[i] = SPECIAL_MOVES[maxIndex];
      SPECIAL_MOVES[maxIndex] = temp;
    }
  }
  /**
   * 
   Checks if the current player's position is on top of a snake on the board, and updates the player's position if so.
   If the current position is present in the SNAKES/LADDERS/SPECIAL SPACES array, the binarySearch method will return the index of the element,
   otherwise, it will return a negative value.
   Also updates the log text and calls a random snake reply from the computer.
   @param newPosition - The current player's position on the board
   */
  public void checkSnakes(int newPosition) {
    int snakeIndex = Arrays.binarySearch(SNAKES, newPosition);
    if (snakeIndex >= 0) {
      newPosition = SNAKE_ENDS[snakeIndex];
      if (playerTurn== true)
      {
        setPlayerPosition(newPosition);
        this.playerLogText = this.playerLogText + "\nSnake";
        computerRandomSnakeReply ();
        playerNumSnakesUsed++;
        playerLogLines++;
        checkPlayerLogLines ();
      }
      else if(playerTurn == false)
      {
        setComputerPosition(newPosition);
        this.computerLogText = this.computerLogText + "\nSnake";
        computerNumSnakesUsed++;
        computerLogLines++;
        checkComputerLogLines ();
      }
    }
  }
  /**
   * 
   Checks if the current player's position is on top of a ladder on the board, and updates the player's position if so.
   Also updates the log text and calls a random ladder reply from the computer.
   @param newPosition - The current player's position on the board
   */
  public void checkLadders(int newPosition) {
    int ladderIndex  = Arrays.binarySearch(LADDERS, newPosition);
    if (ladderIndex >= 0) {
      newPosition = LADDERS_ENDS[ladderIndex];
      if (playerTurn== true)
      {
        setPlayerPosition(newPosition);
        this.playerLogText = this.playerLogText + "\nLadder";
        computerRandomLadderReply ();
        playerNumLaddersUsed++;
        playerLogLines++;
        checkPlayerLogLines ();
      }
      else if(playerTurn == false)
      {
        setComputerPosition(newPosition);
        this.computerLogText = this.computerLogText + "\nLadder";
        computerNumLaddersUsed++;
        computerLogLines++;
        checkComputerLogLines ();
      }
    }
  }
  /**
   * 
   Check if the current player's position is on top of a special space on the board, and increments the player's position by the special move amount.
   Also updates the log text and keeps track of the number of special spaces used by the player.
   @param newPosition - The current player's position on the board
   */
  public void checkSpecialSpaces(int newPosition) {
    int specialIndex = Arrays.binarySearch(SPECIAL_SPACES, newPosition);
    if (specialIndex >= 0) {
      newPosition += SPECIAL_MOVES[specialIndex];
      if (playerTurn== true)
      {
        setPlayerPosition(newPosition);
        playerLogText = playerLogText + "\nSpecial Space";
        playerNumSpecialSpacesUsed++;
      }
      else if(playerTurn == false)
      {
        setComputerPosition(newPosition);
        computerLogText = computerLogText + "\nSpecial Space";
        computerNumSpecialSpacesUsed++;
      }
    }
  }
  /**
   * 
   Method that resets the player and computer positions, as well as the wins for each player,
   to their initial values of 0.
   */
  public void endGame() {
    playerPosition = 0;
    computerPosition = 0;
    playerWins = 0;
    computerWins = 0;
  }
  /**
   * 
   Method that creates a new menu frame, model, and view, and sets the main game frame to invisible
   when the back to main menu button is pressed.
   */
  public void backToMainMenu ()
  {
    // Menu Frame
    JFrame menuFrame = new JFrame ();
    SnakesAndLaddersMenuModel menuModel = new SnakesAndLaddersMenuModel (menuFrame);
    SnakesAndLaddersMenuView menuView = new SnakesAndLaddersMenuView (menuModel);
    
    menuFrame.setContentPane (menuView);
    menuFrame.setLocation (750,250);
    menuFrame.setSize (400,400);
    menuFrame.setTitle ("Snakes And Ladders");
    menuFrame.setDefaultCloseOperation (JFrame.EXIT_ON_CLOSE);
    menuFrame.setVisible (true);
    this.mainFrame.setVisible (false);
  }
  /**
   * 
   Method that checks if either the player or the computer have reached the last space on the board,
   and if so, increments the wins for that player and calls the declareWinner method. Also updates the
   player and computer statistics text.
   */
  public void checkWin() {
    if (playerPosition == BOARD_SIZE) {
      playerWins++;
      this.playerLogText = this.playerLogText + "\nWINNER!";
      declareWinner(this.menuModel.getPlayerName ());
      playerPosition = 100;
      computerRandomWinReply ();
    } else if (computerPosition == BOARD_SIZE) {
      computerWins++;
      this.computerLogText = this.computerLogText + "\nWINNER!";
      declareWinner("Burns");
      computerPosition = 100;
      computerRandomLossReply ();
    }
    this.playerStatsText = "\nSnakes Used: " + this.playerNumSnakesUsed + "\nLadders Used: " + this.playerNumLaddersUsed + "\nSpecial Spaces Used: " + this.playerNumSpecialSpacesUsed + "\nKnockouts: " + this.playerRoundKnockouts + "\nWins: " + this.playerWins;
    this.computerStatsText = "\nSnakes Used: " + this.computerNumSnakesUsed + "\nLadders Used: " + this.computerNumLaddersUsed + "\nSpecial Spaces Used: " + this.computerNumSpecialSpacesUsed + "\nKnockouts: " + this.computerRoundKnockouts + "\nWins: " + this.computerWins;
  }
  /**
   * 
   Method that sets the winner variable to the name of the winner and sets the isWinner variable to true,
   and calls the gameEnded method in the view.
   @param winner a string that contains the name of the winner
   */
  public void declareWinner(String winner) {
    this.winner = winner; 
    this.isWinner = true;
    this.mainView.gameEnded ();
  }
  /**
   * 
   Saves the game results to a user-specified file. A file chooser dialog is displayed
   allowing the user to choose the file name and location to save the results. The file
   is saved in text format and includes information such as rounds played, player and
   computer knockouts, and player and computer wins.
   */
  public void saveGame() {
    // Create a file chooser
    JFileChooser fileChooser = new JFileChooser();
    fileChooser.setDialogTitle("Save Game Results");
    // Set the default file name and extension
    fileChooser.setSelectedFile(new File("SnakesAndLadders_Game_Results.txt"));
    int userSelection = fileChooser.showSaveDialog(null);
    if (userSelection == JFileChooser.APPROVE_OPTION) {
      File file = fileChooser.getSelectedFile();
      try {
        PrintWriter writer = new PrintWriter(file);
        writer.println("Rounds Played: " + this.currentRound + " / " + this.menuModel.getTotalRounds ());
        writer.println ();
        writer.println (this.menuModel.getPlayerName() + " Colour: " + this.colours[this.menuModel.getColourNum()]);
        writer.println (this.menuModel.getPlayerName() + " Knockouts: " + playerTotalKnockouts);
        writer.println(this.menuModel.getPlayerName() + " Wins: " + playerWins);
        writer.println ();
        writer.println ("Burns Colour: " + this.colours[this.menuModel.getComputerColour()]);
        writer.println ("Burns Knockouts: " + computerTotalKnockouts);
        writer.println("Burns Wins: " + computerWins);
        writer.close();
      } catch (IOException e) {
        e.printStackTrace();
      }
    }
  }
}