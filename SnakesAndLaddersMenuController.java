/** SnakesAndLaddersMenuController class
  * Class for the controllers of the buttons in the menu frame
  * @since Jan 22, 2023
  * @author Waleed Rashid
  */

import javax.swing.*;
import java.awt.event.*;

public class SnakesAndLaddersMenuController implements ActionListener
{
  SnakesAndLaddersMenuModel menuModel; // Menu model
  SnakesAndLaddersMenuView menuView; // Menu view
  
  /** Default constructor for the controller for menu buttons
    * @param menuModel Model of the menu
    * @param menuView Menu's GUI */
  public SnakesAndLaddersMenuController (SnakesAndLaddersMenuModel menuModel, SnakesAndLaddersMenuView menuView)
  {
    super ();
    
    // Connects menu's model and view
    this.menuModel = menuModel;
    this.menuView = menuView;
  }
  
  /** Method which reads the button labels and performs their corresponding actions
    * @param e Indicates a button has been pressed */
  public void actionPerformed (ActionEvent e)
  {
    try
    {
      String buttonText = e.getActionCommand (); // Reads button label
      
      // Runs corresponding action after reading button
      if (buttonText.equals ("Play"))
      {
        this.menuView.playPressed ();
      }
      
      if (buttonText.equals ("Help"))
      {
        this.menuView.helpPressed ();
      }
      
      if (buttonText.equals ("Back"))
      {
        this.menuModel.back ();
      }
      
      if (buttonText.equals ("Quit"))
      {
        System.exit (0);
      }
      
      if (buttonText.equals ("Start"))
      {
        this.menuModel.openGame ();
      }
      
      if (buttonText.equals ("Next"))
      {
        this.menuView.nextPressed ();
      }
      
      if (buttonText.equals ("Red"))
      {
        this.menuModel.setColourNum (0);
        this.menuModel.setIsColourChosen (true);
      }
      
      if (buttonText.equals ("Blue"))
      {
        this.menuModel.setColourNum (1);
        this.menuModel.setIsColourChosen (true);
      }
      
      if (buttonText.equals ("Green"))
      {
        this.menuModel.setColourNum (2);
        this.menuModel.setIsColourChosen (true);
      }
      
      if (buttonText.equals ("Yellow"))
      {
        this.menuModel.setColourNum (3);
        this.menuModel.setIsColourChosen (true);
      }
      
      if (buttonText.equals ("Purple"))
      {
        this.menuModel.setColourNum (4);
        this.menuModel.setIsColourChosen (true);
      }
      
      if (buttonText.equals ("Orange"))
      {
        this.menuModel.setColourNum (5);
        this.menuModel.setIsColourChosen (true);
      }
      
      this.menuView.menuUpdate ();
      
    } catch (NumberFormatException ex)
    {
    } 
  }
  
}