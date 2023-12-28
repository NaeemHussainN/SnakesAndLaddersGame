/** SnakesAndLadderMainController class
  * Class for the controllers of the buttons in the main frame
  * @since Jan 22, 2023
  * @author Naeem Hussain
  */

import javax.swing.*;
import java.awt.event.*;
import java.awt.*;

public class SnakesAndLaddersMainController implements ActionListener
{
  SnakesAndLaddersMainModel mainModel; // Main model
  SnakesAndLaddersMainView mainView; // Main view
  
  private Graphics g; // Graphics object to draw
  
  public SnakesAndLaddersMainController (SnakesAndLaddersMainModel mainModel, SnakesAndLaddersMainView mainView)
  {
    super ();
    
    // Connects main model and view
    this.mainModel = mainModel;
    this.mainView = mainView;
    
   // this.board = new SnakesAndLaddersBoardDrawing(mainModel);
  }
  
  /** Method which reads the button labels and performs their corresponding actions
    * @param e - indicates a button has been pressed */
  public void actionPerformed (ActionEvent e)
  {
    try
    {
      String buttonText = e.getActionCommand ();
      
      if (buttonText.equals ("Roll Dice"))
      {
        this.mainModel.playGame ();
      }
      
      if (buttonText.equals ("Play Again"))
      {
        this.mainModel.playAgain ();
      }

      if (buttonText.equals ("Quit"))
      {
        System.exit (0);
      }
      
      if (buttonText.equals ("Return Back"))
      {
        this.mainModel.backToMainMenu ();
      }
      
      if (buttonText.equals ("Save Results"))
      {
        this.mainModel.saveGame ();
      }
      
      this.mainView.update (g);
      
    } catch (NumberFormatException ex)
    {
      System.out.println ("error");
    }
    
  }
  
}
