package tetris;

/*
 * Finalized on January 8, 2012
 */

import java.awt.*;

import javax.swing.*;

import java.awt.event.*;

/*
 * This part of the program shows the user the game tutorial, as well as the controls.
 * Furthermore, user can go back to the pause menu when done with the tutorial.
 */

public class Help_Tutorial extends JFrame {
	
	private JFrame frame;
	private JPanel p;
	private JButton Back;
	private JLabel Help;
	private JTextArea Tutorial;
	private JScrollPane Scroll;
	
	public Help_Tutorial(){
		
		p = new JPanel();												//creates a panel
		p.setBackground(Color.GREEN);									//sets the panel green
		
		String str = " In Tetris, Your Goal is to CLEAR LINES.\n"+" A line is defined as filled squares from the very left side of\n"+								//sets the variable that includes the tutorial
		 			 " the game field to the very right side.\n"+" A Tetrimino is a shape consisting of 4 squares.\n"+
		 			 " It could come in 7 different shapes: L, J, O, S, Z, T or I.\n"+" The object is to manipulate these pieces by moving them\n"+
		 			 " left to right, rotating them by 90 degrees units with the aim of creating\n" + " a horizontal line of blocks without gaps.\n"+
		 			 " When a horizontal line is filled, it disappears, \n"+" and the blocks above would fall down.\n"+ " Your score is based on how many lines you clear before the\n"+
		 			 " stacks of Tetriminoes reach the top and no new pieces could fall \n"+" down. Double the score is awarded for clearing 4 lines (A 'Tetris')\n"+
		 			 " simultaneously. While playing, press 'H' to temporarily swap your\n"+" current piece with the next piece. After the first swap, you could \n"+
		 			 " swap your falling piece with the shape that is shown under 'Hold'.\n"+" THE GAME IS OVER WHEN NO NEW PIECE COULD FALL DOWN.\n"+
		 			 "\n"+" -Controls-\n"+" W/Up Arrow - Rotate Right\n"+" A/Left Arrow - Move Left\n"+" D/Right Arrow - Move Right\n"+" Down Arrow - Rotate Left\n"+
		 			 " S - Move Down Faster\n"+" SPACEBAR - Drop Piece to Bottom Instantly\n"+" H - Hold Piece\n"+" P - Pause Game/Access Pause Menu\n"+"\n"+
		 			 "                   !!!!!! YOU ARE NOW READY TO PLAY TETRIS !!!!!!";				
		
		Help = new JLabel("TETRIS Game Rules");													//creates a label
		Help.setFont(new Font("Century Gothic", Font.BOLD, 16));								//sets a font for the label
		Tutorial = new JTextArea(str,20,35);													//creates a text area
				
		Scroll = new JScrollPane(Tutorial);														//creates a scroll
		Scroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);				//creates the scroll so that it only works vertically
		Tutorial.setEditable(false);															//sets the text area not editable
		Back = new JButton("Back");																//creates a button
		
		p.add(Help);																//adds the tutorial to the panel
		p.add(Scroll);																//adds the scroll function
		p.add(Back);																//adds the button
		
		Back.addActionListener(new ActionListener() {
	    	
	    	public void actionPerformed(ActionEvent e)
	    	{
	    		//Execute when button is pressed
	    		frame.setVisible(false);														//closes the frame
	    	}
	    });
		
		frame = new JFrame("Pause Menu");														//creates a frame
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);									//sets the frame so that it closes when close button is pressed
	    frame.setResizable(false);																//disables the resizing frame function
	    frame.add(p);																			//adds panel to the frame
	    frame.setSize(450, 430);																//sets the frame size
	    frame.setVisible(true);																	
	    frame.setLocation(850,350);																//sets the location
	}
}
