/** SnakesAndLaddersNameController class
  * Class for the controller of the name input by user
  * @since Jan 22, 2023
  * @author Waleed Rashid
  */

import javax.swing.*;
import java.awt.event.*;

public class SnakesAndLaddersNameController implements ActionListener
{
  private SnakesAndLaddersMenuModel menuModel; // Menu model
  
  private JTextField name; // Name text field
  private JTextField round; // Round text field
  
  /** Default constructor for controller of name text field
    * @param menuModel Model of the menu
    * @param name Name text field
    * @param round Round text field */
  public SnakesAndLaddersNameController (SnakesAndLaddersMenuModel menuModel, JTextField name, JTextField round)
  {
    super ();
    
    // Connects model and text fields
    this.menuModel = menuModel;
    this.name = name;
    this.round = round;
  }
  
  /** Method which reads the button labels and performs their corresponding actions
    * @param e Indicates a button has been pressed */
  public void actionPerformed (ActionEvent e)
  {
    String playerName = this.name.getText ();
    
    this.menuModel.setPlayerName (playerName); // Sets user's inputted name
    this.name.setEnabled (false);
    this.round.setEnabled (true); // Enables round text field
  }
  
}