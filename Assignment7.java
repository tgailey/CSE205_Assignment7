// Assignment #: 7
// Arizona State University - CSE205
//         Name: Trenton Gailey
//    StudentID: 1211386693
//      Lecture: Monday Wednesday Friday 9:40 - 10:30
//  Description: The Assignment 7 class creates a panel that will allow us to edit
//				 the shape. It also sets the size of the panel

import javax.swing.*;

public class Assignment7 extends JApplet
{

 public void init()
  {
    // create a WholePanel object and add it to the applet
    WholePanel wholePanel = new WholePanel();
    getContentPane().add(wholePanel);

    //set applet size to 500 X 400
    setSize (500, 400);
  }

}