package tetris;

/*
 * Finalized on January 8, 2012
 */

import java.awt.*;

import javax.swing.*;

import java.awt.event.*;

import static tetris.Board.*;
import static tetris.Tetris.*;
import static tetris.Tetris.curScore;
import static tetris.Tetris.globalLevel;
import static tetris.Tetris.highScore;
import static tetris.Tetris.localLevel;
import static tetris.Shape.pieceLetter;
import static tetris.Pause_Menu.newpiece;

/*
 * This part of the program asks the user if he or she wants to play again or quit after
 * losing in the previous gameplay. The program presents a new window with two active buttons
 * that will execute the way the user wants.
 */

public class Game_Over extends JFrame {
	//sets variables
	Board board; 							
	private JFrame frame;								
	private JPanel p;
	private JLabel GameOver;
	private JLabel Notice; 
	private JButton Again;
	private JButton Quit;
	private JLabel Spacing1;
	private JLabel Spacing2;
	private JLabel Spacing3;
	private JLabel Spacing4;
	private JLabel Spacing5;
	private JLabel Spacing6;
	private JLabel Spacing7;
	private JLabel level;
	private JButton level_1;
	private JButton level_2;
	private JButton level_3;
	private JButton level_4;
	private JButton level_5;
	private JButton level_6;
	private JButton level_7;
	private JButton level_8;
	private JButton level_9;
	private JButton level_10;
	private JLabel hscore;
	private JLabel cscore;
	
	public Game_Over(Board b){
		
		board = b;
		p = new JPanel();							//creates a panel to the frame
		p.setBackground(Color.CYAN);				//sets a background colour of the panel
		
		GameOver = new JLabel("GAME OVER"); //creates a label
		GameOver.setFont(new Font("Century Gothic", Font.BOLD, 20));					//changes the font of the label
		Notice=new JLabel("");
		Notice.setFont(new Font("Century Gothic", Font.BOLD, 10));
		Again = new JButton("Play Again");												//creates a button
		Quit = new JButton("Quit");													 	//creates a button
		Spacing1 = new JLabel("                                                  ");	//creates a label
		Spacing2 = new JLabel("                                                  ");	//creates a label
		Spacing3 = new JLabel("     ");													//creates a label
		Spacing4 = new JLabel("                                                             ");	//creates a label
		Spacing5 = new JLabel("                                                                                           ");	//creates a label
		Spacing6 = new JLabel("                                                                    ");	//creates a label
		Spacing7 = new JLabel("                                                                    ");	//creates a label
		level = new JLabel("Choose your level:");												//creates a label
		level_1 = new JButton("1");																//creates a button		
		level_2 = new JButton("2");																//creates a button
		level_3 = new JButton("3");																//creates a button
		level_4 = new JButton("4");																//creates a button
		level_5 = new JButton("5");																//creates a button
		level_6 = new JButton("6");																//creates a button
		level_7 = new JButton("7");																//creates a button
		level_8 = new JButton("8");																//creates a button
		level_9 = new JButton("9");																//creates a button
		level_10 = new JButton("10");															//creates a button
		cscore = new JLabel("Your Final Score:   "+curScore);									    //creates a label
		hscore = new JLabel("Your High Score:   "+highScore);                                         //creates a label
		
		p.add(Spacing1);																//adds the selected components to the panel
		p.add(GameOver);
		p.add(cscore);
		p.add(hscore);
		p.add(Notice);
		p.add(Spacing2);
		p.add(Again);
		p.add(Spacing3);
		p.add(Quit);
		p.add(Spacing4);
		p.add(level);
		p.add(Spacing5);
		p.add(level_1);
		p.add(level_2);
		p.add(level_3);
		p.add(level_4);
		p.add(Spacing6);
		p.add(level_5);
		p.add(level_6);
		p.add(level_7);
		p.add(level_8);
		p.add(Spacing7);
		p.add(level_9);
		p.add(level_10);
		
		Spacing4.setVisible(false);
		Spacing5.setVisible(false);
		Spacing6.setVisible(false);
		Spacing7.setVisible(false);
		level.setVisible(false);
    	level_1.setVisible(false);
    	level_2.setVisible(false);
    	level_3.setVisible(false);
    	level_4.setVisible(false);
    	level_5.setVisible(false);
    	level_6.setVisible(false);
    	level_7.setVisible(false);
    	level_8.setVisible(false);
    	level_9.setVisible(false);
    	level_10.setVisible(false);
    	
    	curScore=0; 
		board.saveAndQuit();
		board.clearSavedFile();
    	
		frame = new JFrame("Game Over");								//creates a frame called Game Over
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);			//action when close button is pressed
	    frame.setResizable(false);										//sets the frame so that the user cannot resize it
	    frame.add(p);                                                   //adds panel to the frame
	    frame.setSize(255, 255);										//sets the size of the frame
	    frame.setVisible(true);											//makes the window visible
	    frame.setLocation(830,400);	    								//sets the frame at specified area
	    
	    Again.addActionListener(new ActionListener() {					
	    	
	    	public void actionPerformed(ActionEvent e)
	    	{
	    		//Execute when button is pressed
	    		GameOver.setVisible(false);						
	    		Notice.setVisible(false);
	    		hscore.setVisible(false);
	    		cscore.setVisible(false);
	    		Spacing1.setVisible(false);
	    		Spacing2.setVisible(false);
	    		Again.setVisible(false);
	    		Spacing3.setVisible(false);
	    		Quit.setVisible(false);			
	    		
	    		Spacing4.setVisible(true);
	    		Spacing5.setVisible(true);
	    		Spacing6.setVisible(true);
	    		Spacing7.setVisible(true);
	    		level.setVisible(true);
	        	level_1.setVisible(true);
	        	level_2.setVisible(true);
	        	level_3.setVisible(true);
	        	level_4.setVisible(true);
	        	level_5.setVisible(true);
	        	level_6.setVisible(true);
	        	level_7.setVisible(true);
	        	level_8.setVisible(true);
	        	level_9.setVisible(true);
	        	level_10.setVisible(true);
	    		
	    	}
	    });
	    
	    Quit.addActionListener(new ActionListener() {
	    	
	    	public void actionPerformed(ActionEvent e)
	    	{
	    		//Execute when button is pressed
	    		curScore=0; 
	    		board.saveAndQuit();
	    		board.clearSavedFile(); 
	    		System.exit(0);											//program shuts down and saves the latest score
	      	}
	    });
	    level_1.addActionListener(new ActionListener() {
	    	
	    	public void actionPerformed(ActionEvent e)
	    	{
	    		//Execute when button is pressed
	    		localLevel=1;											//changes the game level 
	    		localToGlobal();
	    		board.timer.setDelay((int)(globalLevel));	
	    		frame.setVisible(false);								//Restarts the round 						
	    		isPaused=false;
	    		board.isStarted = true;
	    		restart=true; 
	    		holdExists = false;
	    		holdMemory = 0;
	    		displayHoldPiece = "";
	    		curScore = 0;
	    		board.saveAndQuit();
	    		board.clearSavedFile();
	    		board.clearBoard(); 
	    		newpiece = true;
	    		Count_Down cd = new Count_Down(board);
	        	board.statusbar.setText("<html>Score <br>"+curScore+"<br> Level <br>"+localLevel+"<br> HiScore <br>"+highScore+"<br> Next 3 <br>"
	    				+pieceLetter[0]+"<br>"+pieceLetter[1]+"<br>"+pieceLetter[2]+"<br> Hold <br>"+displayHoldPiece+"</html>");
	    	}
	    });

	    level_2.addActionListener(new ActionListener() {
	    	
	    	public void actionPerformed(ActionEvent e)
	    	{
	    		//Execute when button is pressed
	    		localLevel=2;											//changes the game level 
	    		localToGlobal();
	    		board.timer.setDelay((int)(globalLevel));	
	    		frame.setVisible(false);								//Restarts the round 						
	    		isPaused=false;
	    		board.isStarted = true;
	    		restart=true; 
	    		holdExists = false;
	    		holdMemory = 0;
	    		displayHoldPiece = "";
	    		curScore = 0;
	    		board.saveAndQuit();
	    		board.clearSavedFile();
	    		board.clearBoard(); 
	    		newpiece = true;
	    		Count_Down cd = new Count_Down(board);
	        	board.statusbar.setText("<html>Score <br>"+curScore+"<br> Level <br>"+localLevel+"<br> HiScore <br>"+highScore+"<br> Next 3 <br>"
	    				+pieceLetter[0]+"<br>"+pieceLetter[1]+"<br>"+pieceLetter[2]+"<br> Hold <br>"+displayHoldPiece+"</html>");
	    	}
	    });
	    
	    level_3.addActionListener(new ActionListener() {
	    	
	    	public void actionPerformed(ActionEvent e)
	    	{
	    		//Execute when button is pressed
	    		localLevel=3;											//changes the game level 
	    		localToGlobal();
	    		frame.setVisible(false);
	    		board.timer.setDelay((int)(globalLevel));	
	    		isPaused=false;
	    		board.isStarted = true;
	    		restart=true; 
	    		holdExists = false;
	    		holdMemory = 0;
	    		displayHoldPiece = "";
	    		curScore = 0;
	    		board.saveAndQuit();
	    		board.clearSavedFile();
	    		board.clearBoard(); 
	    		board.newPiece();
	    		Count_Down cd = new Count_Down(board);
	        	board.statusbar.setText("<html>Score <br>"+curScore+"<br> Level <br>"+localLevel+"<br> HiScore <br>"+highScore+"<br> Next 3 <br>"
	    				+pieceLetter[0]+"<br>"+pieceLetter[1]+"<br>"+pieceLetter[2]+"<br> Hold <br>"+displayHoldPiece+"</html>");
	    	}
	    });
	    
	    level_4.addActionListener(new ActionListener() {
	    	
	    	public void actionPerformed(ActionEvent e)
	    	{
	    		//Execute when button is pressed
	    		localLevel=4;											//changes the game level 
	    		localToGlobal();
	    		board.timer.setDelay((int)(globalLevel));	
	    		frame.setVisible(false);								//Restarts the round 						
	    		isPaused=false;
	    		board.isStarted = true;
	    		restart=true; 
	    		holdExists = false;
	    		holdMemory = 0;
	    		displayHoldPiece = "";
	    		curScore = 0;
	    		board.saveAndQuit();
	    		board.clearSavedFile();
	    		board.clearBoard(); 
	    		newpiece = true;
	    		Count_Down cd = new Count_Down(board);
	        	board.statusbar.setText("<html>Score <br>"+curScore+"<br> Level <br>"+localLevel+"<br> HiScore <br>"+highScore+"<br> Next 3 <br>"
	    				+pieceLetter[0]+"<br>"+pieceLetter[1]+"<br>"+pieceLetter[2]+"<br> Hold <br>"+displayHoldPiece+"</html>");
	    	}
	    });
	    
	    level_5.addActionListener(new ActionListener() {
	    	
	    	public void actionPerformed(ActionEvent e)
	    	{
	    		//Execute when button is pressed
	    		localLevel=5;											//changes the game level 
	    		localToGlobal();
	    		board.timer.setDelay((int)(globalLevel));	
	    		frame.setVisible(false);								//Restarts the round 						
	    		isPaused=false;
	    		board.isStarted = true;
	    		restart=true; 
	    		holdExists = false;
	    		holdMemory = 0;
	    		displayHoldPiece = "";
	    		curScore = 0;
	    		board.saveAndQuit();
	    		board.clearSavedFile();
	    		board.clearBoard(); 
	    		newpiece = true;
	    		Count_Down cd = new Count_Down(board);
	        	board.statusbar.setText("<html>Score <br>"+curScore+"<br> Level <br>"+localLevel+"<br> HiScore <br>"+highScore+"<br> Next 3 <br>"
	    				+pieceLetter[0]+"<br>"+pieceLetter[1]+"<br>"+pieceLetter[2]+"<br> Hold <br>"+displayHoldPiece+"</html>");
	    	}
	    });
	    
	    level_6.addActionListener(new ActionListener() {
	    	
	    	public void actionPerformed(ActionEvent e)
	    	{
	    		//Execute when button is pressed
	    		localLevel=6;											//changes the game level 
	    		localToGlobal();
	    		board.timer.setDelay((int)(globalLevel));	
	    		frame.setVisible(false);								//Restarts the round 						
	    		isPaused=false;
	    		board.isStarted = true;
	    		restart=true; 
	    		holdExists = false;
	    		holdMemory = 0;
	    		displayHoldPiece = "";
	    		curScore = 0;
	    		board.saveAndQuit();
	    		board.clearSavedFile();
	    		board.clearBoard(); 
	    		newpiece = true;
	    		Count_Down cd = new Count_Down(board);
	        	board.statusbar.setText("<html>Score <br>"+curScore+"<br> Level <br>"+localLevel+"<br> HiScore <br>"+highScore+"<br> Next 3 <br>"
	    				+pieceLetter[0]+"<br>"+pieceLetter[1]+"<br>"+pieceLetter[2]+"<br> Hold <br>"+displayHoldPiece+"</html>");
	    	}
	    });
	    
	    level_7.addActionListener(new ActionListener() {
	    	
	    	public void actionPerformed(ActionEvent e)
	    	{
	    		//Execute when button is pressed
	    		localLevel=7;											//changes the game level 
	    		localToGlobal();
	    		board.timer.setDelay((int)(globalLevel));	
	    		frame.setVisible(false);								//Restarts the round 						
	    		isPaused=false;
	    		board.isStarted = true;
	    		restart=true; 
	    		holdExists = false;
	    		holdMemory = 0;
	    		displayHoldPiece = "";
	    		curScore = 0;
	    		board.saveAndQuit();
	    		board.clearSavedFile();
	    		board.clearBoard(); 
	    		newpiece = true;
	    		Count_Down cd = new Count_Down(board);
	        	board.statusbar.setText("<html>Score <br>"+curScore+"<br> Level <br>"+localLevel+"<br> HiScore <br>"+highScore+"<br> Next 3 <br>"
	    				+pieceLetter[0]+"<br>"+pieceLetter[1]+"<br>"+pieceLetter[2]+"<br> Hold <br>"+displayHoldPiece+"</html>");
	    	}
	    });
	    
	    level_8.addActionListener(new ActionListener() {
	    	
	    	public void actionPerformed(ActionEvent e)
	    	{
	    		//Execute when button is pressed
	    		localLevel=8;											//changes the game level 
	    		localToGlobal();
	    		board.timer.setDelay((int)(globalLevel));	
	    		frame.setVisible(false);								//Restarts the round 						
	    		isPaused=false;
	    		board.isStarted = true;
	    		restart=true; 
	    		holdExists = false;
	    		holdMemory = 0;
	    		displayHoldPiece = "";
	    		curScore = 0;
	    		board.saveAndQuit();
	    		board.clearSavedFile();
	    		board.clearBoard(); 
	    		newpiece = true;
	    		Count_Down cd = new Count_Down(board);
	        	board.statusbar.setText("<html>Score <br>"+curScore+"<br> Level <br>"+localLevel+"<br> HiScore <br>"+highScore+"<br> Next 3 <br>"
	    				+pieceLetter[0]+"<br>"+pieceLetter[1]+"<br>"+pieceLetter[2]+"<br> Hold <br>"+displayHoldPiece+"</html>");
	    	}
	    });
	    
	    level_9.addActionListener(new ActionListener() {
	    	
	    	public void actionPerformed(ActionEvent e)
	    	{
	    		//Execute when button is pressed
	    		localLevel=9;											//changes the game level 
	    		localToGlobal();
	    		board.timer.setDelay((int)(globalLevel));	
	    		frame.setVisible(false);								//Restarts the round 						
	    		isPaused=false;
	    		board.isStarted = true;
	    		restart=true; 
	    		holdExists = false;
	    		holdMemory = 0;
	    		displayHoldPiece = "";
	    		curScore = 0;
	    		board.saveAndQuit();
	    		board.clearSavedFile();
	    		board.clearBoard(); 
	    		newpiece = true;
	    		Count_Down cd = new Count_Down(board);
	        	board.statusbar.setText("<html>Score <br>"+curScore+"<br> Level <br>"+localLevel+"<br> HiScore <br>"+highScore+"<br> Next 3 <br>"
	    				+pieceLetter[0]+"<br>"+pieceLetter[1]+"<br>"+pieceLetter[2]+"<br> Hold <br>"+displayHoldPiece+"</html>");
	    	}
	    });
	    
	    level_10.addActionListener(new ActionListener() {
	    	
	    	public void actionPerformed(ActionEvent e)
	    	{
	    		//Execute when button is pressed
	    		localLevel=10;											//changes the game level 
	    		localToGlobal();
	    		board.timer.setDelay((int)(globalLevel));	
	    		frame.setVisible(false);								//Restarts the round 						
	    		isPaused=false;
	    		board.isStarted = true;
	    		restart=true; 
	    		holdExists = false;
	    		holdMemory = 0;
	    		displayHoldPiece = "";
	    		curScore = 0;
	    		board.saveAndQuit();
	    		board.clearSavedFile();
	    		board.clearBoard(); 
	    		newpiece = true;
	    		Count_Down cd = new Count_Down(board);
	        	board.statusbar.setText("<html>Score <br>"+curScore+"<br> Level <br>"+localLevel+"<br> HiScore <br>"+highScore+"<br> Next 3 <br>"
	    				+pieceLetter[0]+"<br>"+pieceLetter[1]+"<br>"+pieceLetter[2]+"<br> Hold <br>"+displayHoldPiece+"</html>");
	    		
	    	}
	    });
	    
	    Quit.addActionListener(new ActionListener() {
	    	
	    	public void actionPerformed(ActionEvent e)
	    	{
	    		//Execute when button is pressed
	    		curScore=0; 
	    		board.saveAndQuit();
	    		board.clearSavedFile(); 
	    		System.exit(0);											//program shuts down and saves the latest score
	      	}
	    });
	    
	}
			
}
