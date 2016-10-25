// Assignment #: 7
// Arizona State University - CSE205
//         Name: Trenton Gailey
//    StudentID: 1211386693
//      Lecture: Monday Wednesday Friday 9:40 - 10:30
//  Description: The Whole Panel class creates two jpanels, one for the user to
//				 interact with, and one for the user to see a circle. The user
//				 will be able to change to background color, the color of the circle,
//				 and whether or not it is filled or an outline. With the keyboard, 
//				 the user will be able to make the circle bigger or smaller, 
//				 and move it around
import java.awt.*;
import javax.swing.*;
import java.awt.event.*;  // to use listener interfaces

public class WholePanel extends JPanel
{
   //Variables to be changed and edited in the editor
   private Color foregroundColor, backgroundColor;
   private int currentDiameter, x1, y1;
   
   //Canvas to be drawn on
   private CanvasPanel canvas;
   
   //Top panel with all the interaction
   private JPanel buttonPanel;

   //Labels for the three editible components
   private JLabel foregroundLabel, backgroundLabel, fillingLabel;
   //Radio Buttons for the three editible components
   private JRadioButton fColorRed, fColorGreen, fColorBlue, bColorCyan, bColorYellow, bColorPink, filled, unfilled;
   //Three button groups for the radio buttons
   private ButtonGroup foreGroup, backGroup, fillGroup;
   //Panels for the three editible components
   private JPanel foregroundPanel, backgroundPanel, fillingPanel;
   
   public WholePanel()
   {
	  //Set default background/foreground colors
      backgroundColor = Color.CYAN;
      foregroundColor = Color.RED;

      //Set default diameter and x/y values
      currentDiameter = 100;
      x1 = 200; y1 = 100;

      //Create a top panel with all the buttons
      buttonPanel = new JPanel(new GridLayout(3,1));
      
      //Foreground Color Components
      foregroundLabel = new JLabel("Foreground Color");
      //Foreground Color Radio Buttons
      foreGroup = new ButtonGroup();
      fColorRed = new JRadioButton("red" , true);
      fColorRed.addActionListener(new ColorListener());
      fColorGreen = new JRadioButton("green", false);
      fColorGreen.addActionListener(new ColorListener());
      fColorBlue = new JRadioButton("blue", false);
      fColorBlue.addActionListener(new ColorListener());
      foreGroup.add(fColorRed);
      foreGroup.add(fColorGreen);
      foreGroup.add(fColorBlue);
      //Add all Foreground color components to foreground panel
      foregroundPanel = new JPanel();
      foregroundPanel.add(foregroundLabel);
      foregroundPanel.add(fColorRed);
      foregroundPanel.add(fColorGreen);
      foregroundPanel.add(fColorBlue);
      
      //Background color components
      backgroundLabel = new JLabel ("Background Color");
      //Background color radio buttons
      backGroup = new ButtonGroup();
      bColorCyan = new JRadioButton("cyan", true);
      bColorCyan.addActionListener(new ColorListener());
      bColorYellow = new JRadioButton("yellow", false);
      bColorYellow.addActionListener(new ColorListener());
      bColorPink = new JRadioButton("pink", false);
      bColorPink.addActionListener(new ColorListener());
      backGroup.add(bColorCyan);
      backGroup.add(bColorYellow);
      backGroup.add(bColorPink);
      //Add background color components to background panel
      backgroundPanel = new JPanel();
      backgroundPanel.add(backgroundLabel);
      backgroundPanel.add(bColorCyan);
      backgroundPanel.add(bColorYellow);
      backgroundPanel.add(bColorPink);
      
      //Filling Components
      fillingLabel = new JLabel("Circle Filling");
      //Filling Radio Buttons
      fillGroup = new ButtonGroup();
      unfilled = new JRadioButton("Unfilled", true);
      unfilled.addActionListener(new FillListener());
      filled = new JRadioButton("Filled", false);
      filled.addActionListener(new FillListener());
      fillGroup.add(unfilled);
      fillGroup.add(filled);
      //Add Filling Components to filling panel
      fillingPanel = new JPanel();
      fillingPanel.add(fillingLabel);
      fillingPanel.add(unfilled);
      fillingPanel.add(filled);
      
      //Add all three panels to top panel
      buttonPanel.add(foregroundPanel);
      buttonPanel.add(backgroundPanel);
      buttonPanel.add(fillingPanel);
      
      //Declare canvas to be painted upon
      canvas = new CanvasPanel();
      canvas.requestFocus();
      
      //Declare Split Pane to split top button panel and canvas to two parts
      JSplitPane sPane = new JSplitPane(JSplitPane.VERTICAL_SPLIT, buttonPanel, canvas);

      //Place split panel in the center of the layout
      setLayout(new BorderLayout());
      add(sPane, BorderLayout.CENTER);
    }

   private class ColorListener implements ActionListener
    {
     public void actionPerformed(ActionEvent event)
      {
    	 //Called when color is selected from menu
    	 
    	 //If foreground color red is selected, set foreground color to red
          if (fColorRed.isSelected()) {
        	  foregroundColor = Color.red;
          }
     	 //else if foreground color green is selected, set foreground color to green
          else if (fColorGreen.isSelected()) {
        	foregroundColor = Color.green;  
          }
      	 //else if foreground color blue is selected, set foreground color to blue
          else if (fColorBlue.isSelected()) {
        	  foregroundColor = Color.blue;
          }
      	 //if background color cyan is selected, set background color to cyan
          if (bColorCyan.isSelected()) {
        	  backgroundColor = Color.cyan;
          }
       	 //else if background color yellow is selected, set background color to yellow
          else if (bColorYellow.isSelected()) {
        	  backgroundColor = Color.yellow;
          }
         //else if background color pink is selected, set background color to pink
          else if (bColorPink.isSelected()) {
        	  backgroundColor = Color.pink;
          }
          
          //Repaint the canvas with new foreground/background colors
          canvas.repaint();
          canvas.requestFocus();
      }
    } // end of ColorListener

   private class FillListener implements ActionListener
    {
     public void actionPerformed(ActionEvent event)
      {
    	 //When unfilled or filled is pressed, simply repaint the canvas
           canvas.repaint();
           canvas.requestFocus();
      }
    }
   //CanvasPanel is the panel where a circle is drawn
   private class CanvasPanel extends JPanel
    {
     //Constructor to initialize the canvas panel
     public CanvasPanel( )
      {
        // make this canvas panel listen to keys
        addKeyListener (new DirectionListener());
        // make this canvas panel listen to mouse
        addMouseListener(new PointListener());

        //Set background and foreground colors to default selected colros
        setBackground(backgroundColor);
        setForeground(foregroundColor);
        
        //This method needs to be called for this panel to listen to keys
        //When panel listens to other things, and go back to listen
        //to keys, this method needs to be called again.
        requestFocus();
      }


     //this method draws all characters pressed by a user so far
     public void paintComponent(Graphics page)
      {
          super.paintComponent(page);
          //Set the background to the selected color
          setBackground(backgroundColor);
          //Set the foreground to the selected color
          setForeground(foregroundColor);
          //IF unfilled is selected, draw oval
          if (unfilled.isSelected()) {
        	  page.drawOval(x1, y1, currentDiameter, currentDiameter);
          }
          //else if filled is selected, fill oval
          else {
        	  page.fillOval(x1, y1, currentDiameter, currentDiameter);
          }
      }

     /** This method is overriden to enable keyboard focus */
     public boolean isFocusable()
      {
        return true;
      }

     // listener class to listen to keyboard keys
     private class DirectionListener implements KeyListener 
       {
         public void keyReleased(KeyEvent e) {}

         public void keyTyped(KeyEvent e) {}

         // in case that a key is pressed, the following will be executed.
         public void keyPressed(KeyEvent e)
          {
        	//if up arrow is pressed and there is room, move the y coordinate up
            if (e.getKeyCode() == KeyEvent.VK_UP && y1 >= 5) {
            	y1 -= 5;
            }
        	//if down arrow is pressed and there is room, move the y coordinate down
            else if (e.getKeyCode() == KeyEvent.VK_DOWN && y1 <= canvas.getHeight() - currentDiameter - 5) {
            	y1 += 5;
            }
        	//if left arrow is pressed and there is room, move the x coordinate left
            else if (e.getKeyCode() == KeyEvent.VK_LEFT && x1 >= 5) {
            	x1 -= 5;
            }
        	//if right arrow is pressed and there is room, move the x coordinate right
            else if (e.getKeyCode() == KeyEvent.VK_RIGHT && x1 <= canvas.getWidth() - currentDiameter - 5) {
            	x1 += 5;
            }
        	//if s is pressed and subtracting six won't decrease diameter less than 10, subtract 6 from diameter
            else if (e.getKeyCode() == KeyEvent.VK_S && currentDiameter >= 16) {
            	currentDiameter -= 6;
            }
        	//if b is pressed, add 6 to diameter
            else if (e.getKeyCode() == KeyEvent.VK_B) {
            	currentDiameter += 6;
            }
            //repaint the canvas with new stats on the circle
            canvas.repaint();
          }
       } // end of DirectionListener


     // listener class that listens to the mouse
     // This class is already completed. No adjustment is needed.
     public class PointListener implements MouseListener
       {
         //in case that a user presses using a mouse,
         //it gains the focus of the keyboard keys
         public void mousePressed (MouseEvent event)
          {
            canvas.requestFocus();
          }

         public void mouseClicked (MouseEvent event) {}
         public void mouseReleased (MouseEvent event) {}
         public void mouseEntered (MouseEvent event) {}
         public void mouseExited (MouseEvent event) {}

       } // end of PointListener

    } // end of Canvas Panel Class

} // end of Whole Panel Class