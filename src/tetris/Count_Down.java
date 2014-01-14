package tetris;

/*
 * Finalized on January 8, 2012
 */

import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import static tetris.Pause_Menu.newpiece;

/*
 * This part of the program is used so that before the game starts, users are warned with the fact that user 
 * has to press the game window.
 */

public class Count_Down extends JFrame {
	//sets variable
	Board board;
	private JFrame frame;
	private JPanel p;
	private JButton click;
	private JLabel notice1;
	private JLabel notice2;
	private JLabel gamest;
	private JLabel spacing1;
	private JLabel spacing2;
	private JLabel spacing3;
			
	public Count_Down(Board b){
		board = b;								
		p = new JPanel();																			//creates a panel
		p.setBackground(Color.ORANGE);																//sets background color for the panel
		spacing1 = new JLabel("                                                           ");		//creates a label
		spacing2 = new JLabel("                                                           ");		//creates a label
		spacing3 = new JLabel("                                                           ");		//creates a label
		click = new JButton("BEGIN!!!");												//creates a button
		notice1 = new JLabel("(Click on Game Window");									//creates a label
		notice1.setFont(new Font("Century Gothic", Font.BOLD, 10));						//sets font for the label
		notice2 = new JLabel("when the Game Starts)");									//creates a label
		notice2.setFont(new Font("Century Gothic", Font.BOLD, 10));						//sets font for the label
		gamest = new JLabel("Click on the Button to begin");							//creates  label
		gamest.setFont(new Font("Century Gothic", Font.BOLD, 13));						//sets font for the label
		
		//adds the selected components
		p.add(spacing1);
		p.add(gamest);
		p.add(spacing2);
		p.add(click);
		p.add(spacing3);
		p.add(notice1);
		p.add(notice2);
				
		frame = new JFrame("Click");										//creates  frame
		frame.setSize(200,200);													//sets the size of the frame
		frame.setResizable(false);												//disables user from changing the frame size
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);					//allow exiting the program by pressing the x button
		frame.setVisible(true);													//make the frame visible
		frame.setLocation(600,350);												//sets the location for the frame
		frame.add(p);															//adds panel to the frame
		
		click.addActionListener(new ActionListener() {
	    	
	    	public void actionPerformed(ActionEvent e)
	    	{
	    		//Execute when button is pressed
	    		board.timer.start();															//game starts
	    		frame.setVisible(false);														//closes the frame
	    		if(newpiece == true){
	    			board.newPiece();
	    		}
	    	}
	    });
	}
			
}
