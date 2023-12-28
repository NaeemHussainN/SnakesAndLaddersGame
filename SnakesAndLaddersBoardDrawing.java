/** SnakesAndLadderBoardDrawing class
  * Class that draws the board and pieces in their new positions
  * @since Jan 22, 2023
  * @author Waleed Rashid
  */

import javax.swing.*;
import java.awt.*;
import java.util.*;

public class SnakesAndLaddersBoardDrawing extends JComponent
{
  JFrame frame; // Frame of main GUI
  SnakesAndLaddersMenuModel menuModel = new SnakesAndLaddersMenuModel (frame); // Menu model
  SnakesAndLaddersMainModel mainModel = new SnakesAndLaddersMainModel (menuModel, frame); // Main model
  
  // Images for snakes and ladders
  private ImageIcon [] snakes = new ImageIcon [] {new ImageIcon ("snake1.png"), new ImageIcon ("snake2.png"), new ImageIcon ("snake3.png")};
  private ImageIcon [] ladders = new ImageIcon [] {new ImageIcon ("ladder1.png"), new ImageIcon ("ladder2.png"), new ImageIcon ("ladder3.png")};
  
  private int [] [] boardNums = new int [10] [10]; // Labeled spaces on the board
  
  final private int SPACING = 49; // Even spacing between each space
  
  // Colour choices of pieces to assign to player
  private ImageIcon [] pieceColours = new ImageIcon [] {new ImageIcon ("red.png"), new ImageIcon ("blue.png"), new ImageIcon ("green.png"), new ImageIcon ("yellow.png"), new ImageIcon ("purple.png"), new ImageIcon ("orange.png")};
  
  // Checkerboard colours
  final private Color offwhite = new Color (252,239,233);
  final private Color lightRed = new Color (247,62,28);
  
  private Map <Integer, String> board = new HashMap <Integer, String> (); // Spaces on the board
  
  // Player piece
  private ImageIcon playerImage;
  private Image player;
  
  // Computer piece
  private ImageIcon computerImage;
  private Image computer;
  
  /** Default constructor for the board
    * @param mainModel Model of the main view
    * @param playerColour Colour of the player's piece
    * @param computerColour Colour of the computer's piece */
  public SnakesAndLaddersBoardDrawing (SnakesAndLaddersMainModel mainModel, int playerColour, int computerColour)
  {
    super ();
    
    this.mainModel = mainModel; // Connects main model
    this.setPreferredSize (new Dimension (500,500));
    
    this.playerImage = this.pieceColours [playerColour];
    this.player = this.playerImage.getImage ();
    
    this.computerImage = this.pieceColours [computerColour];
    this.computer = this.computerImage.getImage ();
  }
  
  /** Method which draws the board and pieces
    * @param g - Graphics object to draw */
  public void paintComponent (Graphics g)
  {
    super.paintComponent (g);
    
    Image [] snake = new Image [snakes.length];
    Image [] ladder = new Image [ladders.length];
    
    // Assigns each snake and ladder to an image
    for (int x = 0; x < 3; x++)
    {
      snake [x] = snakes [x].getImage ();
      ladder [x] = ladders [x].getImage ();
    }
    
    
    // Colours the board in a checkerboard pattern
    for (int i = 0; i < boardNums.length * SPACING; i += (SPACING * 2))
    {
      for (int x = 0; x < boardNums.length * SPACING; x+=SPACING)
      {
        g.setColor (offwhite);
        g.fillRect (x,i,SPACING,SPACING);
        
        g.setColor (lightRed);
        g.fillRect (x+=SPACING,i,SPACING,SPACING);
      }
    }
    
    for (int i = 49; i < boardNums.length * SPACING; i += (SPACING * 2))
    {
      for (int x = 0; x < boardNums.length * SPACING; x+=SPACING)
      {
        g.setColor (lightRed);
        g.fillRect (x,i,SPACING,SPACING);
        
        g.setColor (offwhite);
        g.fillRect (x+=SPACING,i,SPACING,SPACING);
      }
    }
    
    
    g.setColor (Color.BLACK);
    
    // Draw the squares
    for (int i = 0; i < 10; i++) {
      for (int j = 0; j < 10; j++) {
        g.drawRect(i * 49, j * 49, 49, 49);
      }
    }
    
    
    // Sets numbers of each space
    int num = 100;
    
    for (int i = 0; i < 10; i++)
    {
      if (i % 2 == 0)
      {
        for (int j = 0; j < 10; j++)
        {
          boardNums [i][j] = num--;
        }
      }
      
      else
      {
        for (int j = 10 - 1; j >= 0; j--)
        {
          boardNums[i][j] = num--;
        }
      }
    }
    
    
    // Labels the spaces with corresponding numbers
    g.setFont(new Font("Arial", Font.PLAIN, 20));
    for (int i = 0; i < boardNums.length; i++)
    {
      for (int j = 0; j < boardNums[i].length; j++)
      {
        g.drawString (Integer.toString (boardNums [j] [i]), i * 49 + 11, j * 49 + 29);
      }
    }
    
      
    // Draw snakes on board
    g.drawImage (snake [0],SPACING * 4,SPACING * 5,150,200,null); 
    g.drawImage (snake [1],SPACING * 1,SPACING * 3,100,200,null);
    g.drawImage (snake [2],SPACING * 4,SPACING * 0,325,375,null);
    
    // Draw ladders on board
    g.drawImage (ladder [0],SPACING * 1,SPACING * 5,100,200,null);
    g.drawImage (ladder [1],SPACING * 3,SPACING * 2,340,350,null);
    g.drawImage (ladder [2],SPACING * 7,SPACING * 1,150,150,null); 
    
    
    
    // Assigns each space to an x and y position, spaced out by 49 pixels
    board.put (1, "0,441");
    board.put (2, "49,441");
    board.put (3, "98,441");
    board.put (4, "147,441");
    board.put (5, "196,441");
    board.put (6, "245,441");
    board.put (7, "294,441");
    board.put (8, "343,441");
    board.put (9, "392,441");
    board.put (10, "441,441");
    
    board.put (11, "441,392");
    board.put (12, "392,392");
    board.put (13, "343,392");
    board.put (14, "294,392");
    board.put (15, "245,392");
    board.put (16, "196,392");
    board.put (17, "147,392");
    board.put (18, "98,392");
    board.put (19, "49,392");
    board.put (20, "0,392");
    
    board.put (21, "0,343");
    board.put (22, "49,343");
    board.put (23, "98,343");
    board.put (24, "147,343");
    board.put (25, "196,343");
    board.put (26, "245,343");
    board.put (27, "294,343");
    board.put (28, "343,343");
    board.put (29, "392,343");
    board.put (30, "441,343");
    
    board.put (31, "441,294");
    board.put (32, "392,294");
    board.put (33, "343,294");
    board.put (34, "294,294");
    board.put (35, "245,294");
    board.put (36, "196,294");
    board.put (37, "147,294");
    board.put (38, "98,294");
    board.put (39, "49,294");
    board.put (40, "0,294");
    
    board.put (41, "0,245");
    board.put (42, "49,245");
    board.put (43, "98,245");
    board.put (44, "147,245");
    board.put (45, "196,245");
    board.put (46, "245,245");
    board.put (47, "294,245");
    board.put (48, "343,245");
    board.put (49, "392,245");
    board.put (50, "441,245");
    
    board.put (51, "441,196");
    board.put (52, "392,196");
    board.put (53, "343,196");
    board.put (54, "294,196");
    board.put (55, "245,196");
    board.put (56, "196,196");
    board.put (57, "147,196");
    board.put (58, "98,196");
    board.put (59, "49,196");
    board.put (60, "0,196");
    
    board.put (61, "0,147");
    board.put (62, "49,147");
    board.put (63, "98,147");
    board.put (64, "147,147");
    board.put (65, "196,147");
    board.put (66, "245,147");
    board.put (67, "294,147");
    board.put (68, "343,147");
    board.put (69, "392,147");
    board.put (70, "441,147");
    
    board.put (71, "441,98");
    board.put (72, "392,98");
    board.put (73, "343,98");
    board.put (74, "294,98");
    board.put (75, "245,98");
    board.put (76, "196,98");
    board.put (77, "147,98");
    board.put (78, "98,98");
    board.put (79, "49,98");
    board.put (80, "0,98");
    
    board.put (81, "0,49");
    board.put (82, "49,49");
    board.put (83, "98,49");
    board.put (84, "147,49");
    board.put (85, "196,49");
    board.put (86, "245,49");
    board.put (87, "294,49");
    board.put (88, "343,49");
    board.put (89, "392,49");
    board.put (90, "441,49");
    
    board.put (91, "441,0");
    board.put (92, "392,0");
    board.put (93, "343,0");
    board.put (94, "294,0");
    board.put (95, "245,0");
    board.put (96, "196,0");
    board.put (97, "147,0");
    board.put (98, "98,0");
    board.put (99, "49,0");
    board.put (100, "0,0");
    
    board.put (101, "0,-49");
    board.put (102, "49,-49");
    board.put (103, "98,-49");
    board.put (104, "147,-49");
    board.put (105, "196,-49");
    board.put (106, "245,-49");
    board.put (107, "294,-49");
    board.put (108, "343,-49");
    board.put (109, "343,-49");
    board.put (110, "392,-49");
    board.put (111, "392,-98");
    
    
    ImageIcon specialSpaceImageIcon = new ImageIcon ("specialSpaceLogo.png");
    Image specialSpace = specialSpaceImageIcon.getImage ();
    
    // Displays logo for special spaces on corresponding spaces
    for (int x = 0; x < 4; x++)
    {
      String [] spaceXY = board.get (this.mainModel.getSpecialSpaces (x)).split(",");
      int spaceX = Integer.parseInt(spaceXY[0]);
      int spaceY = Integer.parseInt(spaceXY[1]);
      
      g.drawImage (specialSpace, spaceX, spaceY, 49, 49, null);
    }
    
    // Assigns x and y positions for player
    String [] xy = board.get (this.mainModel.getPlayerPosition ()).split(",");
    int spaceX = Integer.parseInt(xy[0]);
    int spaceY = Integer.parseInt(xy[1]);
    
    g.drawImage (this.player, spaceX, spaceY, SPACING, SPACING, null); // Draws player piece
    
    
    // Assigns x and y posiitons for computer
    xy = board.get (this.mainModel.getComputerPosition ()).split(",");
    spaceX = Integer.parseInt(xy[0]);
    spaceY = Integer.parseInt(xy[1]);
    
    g.drawImage (this.computer, spaceX, spaceY, SPACING, SPACING, null); // Draws computer piece
  }
  
}