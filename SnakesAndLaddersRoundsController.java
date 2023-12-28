/** SnakesAndLaddersRoundsController class
  * Class for the controller of the total rounds input by user
  * @since Jan 22, 2023
  * @author Naeem Hussain
  */

import javax.swing.*;
import java.awt.event.*;

public class SnakesAndLaddersRoundsController implements ActionListener
{
  SnakesAndLaddersMenuModel menuModel; // Menu model
  
  private JTextField rounds;
  private JButton next;
  
  public SnakesAndLaddersRoundsController (SnakesAndLaddersMenuModel menuModel, JTextField rounds, JButton next)
  {
    super ();
    
    // Connects menu model, round text field, and next button
    this.menuModel = menuModel;
    this.rounds = rounds;
    this.next = next;
  }
  
  /** Default constructor for controller of total rounds text field
    * @param menuModel Model of the menu
    * @param name Name text field
    * @param round Round text field */
  public void actionPerformed (ActionEvent e)
  {
    int numRounds;
    
    try 
    {
      numRounds = Integer.parseInt (this.rounds.getText ());
      this.menuModel.setTotalRounds (numRounds);
      
      // If the user's inputted number is outside the range of 1-7
      if (numRounds > 7 || numRounds < 1)
      {
        System.out.println ("Please enter a number between 1-7");
        this.rounds.selectAll ();
      }
      
      else
      {
        this.rounds.setEnabled (false);
        this.next.setEnabled (true); // Enables the next button in the menu GUI
      }
    }
    
    // If the user inputs a non-integer
    catch (NumberFormatException ex)
    {
      this.rounds.selectAll ();
      System.out.println ("Please enter a valid number");
    }
    
  }
  
}
