/** SnakesAndsLaddersStartup class
  * Class for the menu GUI startup
  * @since Jan 22, 2023
  * @author Naeem Hussain
  */

import javax.swing.*;
import java.awt.*;

public class SnakesAndsLaddersStartup
{
  public static void main (String [] args)
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
  }
}
