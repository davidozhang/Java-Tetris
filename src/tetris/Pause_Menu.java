package tetris; 

/*
 * Finalized on January 8, 2012
 */

import java.awt.*;
import javax.swing.*;

import java.awt.event.*;

import static tetris.Board.*;
import static tetris.Shape.pieceLetter;
import static tetris.Tetris.curScore;
import static tetris.Tetris.highScore;
import static tetris.Tetris.localLevel;
import static tetris.Board.holdExists;
import static tetris.Tetris.globalLevel;
import static tetris.Tetris.localToGlobal;

/*
 * This part of the program is activated when the user paused the game. 
 * They can either resume, restart, change level, save and quit, and take a look 
 * at the game manual which includes the controls and objective of the game.
 */

public class Pause_Menu extends JFrame {
	//sets variables
	Board board; 
	private JFrame frame;
	private JPanel p;
	private JButton ResumeButton;
	private JButton HelpButton;
	private JButton RestartButton; 
	private JButton ChangeLevelButton; 
	private JButton Quit;
	private JButton Yes; 
	private JButton No; 
	private JLabel Confirm; 
	private JLabel PauseTitle;
	private JLabel Spacing1;
	private JLabel Spacing2;
	private JLabel Spacing3;
	private JLabel Spacing4;
	private JLabel Spacing5; 
	private JLabel Spacing6; 
	private JLabel Spacing7; 
	
	private JLabel spacing1;
	private JLabel spacing2;
	private JLabel spacing3;
	private JLabel spacing4;
	private JLabel spacing5;
	private JLabel spacing6;
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
	private JButton back;
	public static boolean newpiece;
	
	public Pause_Menu(Board b) { 
		board = b;
		p = new JPanel();																		//creates a panel
		p.setBackground(Color.ORANGE);															//gives the panel a background colour
				
		PauseTitle = new JLabel("Why Not Take A Break?");										//creates a label
		PauseTitle.setFont(new Font("Century Gothic", Font.BOLD, 16));							//gives a label a font
		ResumeButton = new JButton				(" Resume ");									//creates a button
		RestartButton=new JButton 				(" Restart");									//creates a button	
		HelpButton = new JButton  				(" Help   ");									//creates a button
		ChangeLevelButton = new JButton			(" Change Level");								//creates a button
		Quit = new JButton("Quit");																//creates a button
		Yes = new JButton("Yes");																//creates a button
		No = new JButton("No");																	//creates a button
		Confirm=new JLabel("Confirm Restart?");													//creates a label
		Confirm.setFont(new Font("Century Gothic", Font.BOLD, 16));								//gives the label a new font
		Spacing1 = new JLabel("                                                  ");			//creates a label
		Spacing2 = new JLabel("                                                  ");			//creates a label
		Spacing3 = new JLabel("      (Click on Game Window After Resuming)       ");			//creates a label
		Spacing3.setFont(new Font("Century Gothic", Font.BOLD, 9));								//gives the label a new font
		Spacing4 = new JLabel("                                                  ");			//creates a label
		Spacing5 = new JLabel("                                                  ");			//creates a label
		Spacing6 = new JLabel("                                                  ");			//creates a label
		Spacing7 = new JLabel("                                                  ");			//creates a label
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
		spacing1 = new JLabel("                                                   ");																//creates a label
		spacing2 = new JLabel("                                                                                               ");					//creates a label
		spacing3 = new JLabel("                                                                                               ");					//creates a label
		spacing4 = new JLabel("                                                                                               ");					//creates a label
		spacing5 = new JLabel("                                                                                               ");					//creates a label
		spacing6 = new JLabel("                                                                                               ");					//creates a label
		back = new JButton("Back");																													//creates a button
		newpiece = false;															//adds the selected components to the panel
		
		p.add(Spacing1);
		p.add(PauseTitle);
		p.add(Spacing2);
		p.add(ResumeButton);
	    p.add(Spacing3);
	    p.add(RestartButton);
	    p.add(Spacing4);
	    p.add(HelpButton);
	    p.add(Spacing5);
	    p.add(ChangeLevelButton);
	    p.add(Spacing6);
	    p.add(Quit);
	    p.add(Confirm);
    	p.add(Spacing7);
		p.add(Yes);
		p.add(No);
		p.add(spacing1);
		p.add(level);
		p.add(spacing2);
		p.add(level_1);
		p.add(level_2);
		p.add(level_3);
		p.add(spacing3);
		p.add(level_4);
		p.add(level_5);
		p.add(level_6);
		p.add(spacing4);
		p.add(level_7);
		p.add(level_8);
		p.add(level_9);
		p.add(spacing5);
		p.add(level_10);
		p.add(spacing6);
		p.add(back);
																	//sets some of the added components to become invisible
		Spacing7.setVisible(false);
    	Confirm.setVisible(false);
    	Yes.setVisible(false);
    	No.setVisible(false);
    	spacing1.setVisible(false);
    	level.setVisible(false);
    	spacing2.setVisible(false);
    	level_1.setVisible(false);
    	level_2.setVisible(false);
    	level_3.setVisible(false);
    	spacing3.setVisible(false);
    	level_4.setVisible(false);
    	level_5.setVisible(false);
    	level_6.setVisible(false);
    	spacing4.setVisible(false);
    	level_7.setVisible(false);
    	level_8.setVisible(false);
    	level_9.setVisible(false);
    	spacing5.setVisible(false);
    	level_10.setVisible(false);
    	spacing6.setVisible(false);
    	back.setVisible(false);
	    	    
	    frame = new JFrame("Pause Menu");												//creates a frame
	    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);							//program closes when close button is pressed
	    frame.setResizable(false);														//disables user from changing the frame size
	    frame.add(p);																	//adds panel to the frame
	    frame.setSize(220, 355);														//sets the frame size
	    frame.setVisible(true);															//makes the frame visible
	    frame.setLocation(850,350);														//sets its location
	    
	    ResumeButton.addActionListener(new ActionListener() {
 
            public void actionPerformed(ActionEvent e)
            {
                //Execute when button is pressed
               frame.setVisible(false);                                //resumes the program 
               isPaused=false; 
               Count_Down cd = new Count_Down(board);
                  	
            }
        });  
	    
	    RestartButton.addActionListener(new ActionListener() {
	    	public void actionPerformed(ActionEvent e)
	    	{
	    		//Execute when button is pressed
	    		PauseTitle.setVisible(false);							//Changes the component's visibility, creating a transition from one slide to another
                ResumeButton.setVisible(false);
                ChangeLevelButton.setVisible(false);
                Quit.setVisible(false);
            	HelpButton.setVisible(false);
            	Spacing2.setVisible(false);
            	Spacing3.setVisible(false);
            	Spacing4.setVisible(false);
            	RestartButton.setVisible(false);
            	
            	Spacing7.setVisible(true);								
            	Confirm.setVisible(true);
            	Yes.setVisible(true);
            	No.setVisible(true);
	    		   		
	    	}
	    });
	    
	    Yes.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//Execute when button is pressed
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
	        }
		});	
	    
	    No.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//Execute when button is pressed
				RestartButton.setVisible(true);							//Goes back to the pause menu
				PauseTitle.setVisible(true);
                ResumeButton.setVisible(true);
                ChangeLevelButton.setVisible(true);
                Quit.setVisible(true);
            	HelpButton.setVisible(true);
            	Spacing2.setVisible(true);
            	Spacing3.setVisible(true);
            	Spacing4.setVisible(true);
            	Spacing5.setVisible(true);
            	Spacing6.setVisible(true);
            	Spacing7.setVisible(false);
            	Confirm.setVisible(false);
            	Yes.setVisible(false);
            	No.setVisible(false);
			}
		});
	    
	    ChangeLevelButton.addActionListener(new ActionListener() {
	    	public void actionPerformed(ActionEvent e) 
	    	{
	    		//Executes when the button is pressed
	    		PauseTitle.setVisible(false);							//Shows the user the level change menu
                ResumeButton.setVisible(false);
                ChangeLevelButton.setVisible(false);
                Quit.setVisible(false);
            	HelpButton.setVisible(false);
            	Spacing1.setVisible(false);
            	Spacing2.setVisible(false);
            	Spacing3.setVisible(false);
            	Spacing4.setVisible(false);
            	Spacing5.setVisible(false);
            	Spacing6.setVisible(false);
            	RestartButton.setVisible(false);
            	
            	spacing1.setVisible(true);
            	level.setVisible(true);
            	spacing2.setVisible(true);
            	level_1.setVisible(true);
            	level_2.setVisible(true);
            	level_3.setVisible(true);
            	spacing3.setVisible(true);
            	level_4.setVisible(true);
            	level_5.setVisible(true);
            	level_6.setVisible(true);
            	spacing4.setVisible(true);
            	level_7.setVisible(true);
            	level_8.setVisible(true);
            	level_9.setVisible(true);
            	spacing5.setVisible(true);
            	level_10.setVisible(true);
            	spacing6.setVisible(true);
            	back.setVisible(true);
	    	}
	    });
	    
	    	    
	    HelpButton.addActionListener(new ActionListener() {
	    	 
            public void actionPerformed(ActionEvent e)
            {
                //Execute when button is pressed
            	Help_Tutorial ht = new Help_Tutorial();					//launches the Help_Tutorial program
            	
                
            }
        });     
	    
	    Quit.addActionListener(new ActionListener() {
	    	
	    	public void actionPerformed(ActionEvent e)
	    	{
	    		//Execute when button is pressed
	    		System.exit(0);											//closes the program
	    	}
	    });
	    
	    level_1.addActionListener(new ActionListener() {
	    	
	    	public void actionPerformed(ActionEvent e)
	    	{
	    		//Execute when button is pressed
	    		localLevel=1;											//changes the game level 
	    		localToGlobal();
	    		board.saveAndQuit();
	    		frame.setVisible(false);
	    		board.timer.setDelay((int)(globalLevel));	
	    		Count_Down cd = new Count_Down(board);
	        	isPaused=false;
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
	    		board.saveAndQuit();
	    		frame.setVisible(false);
	    		board.timer.setDelay((int)(globalLevel));	
	    		Count_Down cd = new Count_Down(board);
	    		isPaused=false;
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
	    		board.saveAndQuit();
	    		frame.setVisible(false);
	    		board.timer.setDelay((int)(globalLevel));	
	    		Count_Down cd = new Count_Down(board);
	    		isPaused=false;
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
	    		board.saveAndQuit();
	    		frame.setVisible(false);
	    		board.timer.setDelay((int)(globalLevel));	
	    		Count_Down cd = new Count_Down(board);
	    		isPaused=false;
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
	    		board.saveAndQuit();
	    		frame.setVisible(false);
	    		board.timer.setDelay((int)(globalLevel));	
	    		Count_Down cd = new Count_Down(board);
	    		isPaused=false;
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
	    		board.saveAndQuit();
	    		frame.setVisible(false);
	    		board.timer.setDelay((int)(globalLevel));	
	    		Count_Down cd = new Count_Down(board);
	    		isPaused=false;
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
	    		board.saveAndQuit();
	    		frame.setVisible(false);
	    		board.timer.setDelay((int)(globalLevel));	
	    		Count_Down cd = new Count_Down(board);
	    		isPaused=false;
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
	    		board.saveAndQuit();
	    		frame.setVisible(false);
	    		board.timer.setDelay((int)(globalLevel));	
	    		Count_Down cd = new Count_Down(board);
	    		isPaused=false;
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
	    		board.saveAndQuit(); 
	    		frame.setVisible(false);
	    		board.timer.setDelay((int)(globalLevel));	
	    		Count_Down cd = new Count_Down(board);
	    		isPaused=false;
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
	    		board.saveAndQuit();
	    		frame.setVisible(false);
	    		board.timer.setDelay((int)(globalLevel));	
	    		Count_Down cd = new Count_Down(board);
	    		isPaused=false;
	    		board.statusbar.setText("<html>Score <br>"+curScore+"<br> Level <br>"+localLevel+"<br> HiScore <br>"+highScore+"<br> Next 3 <br>"
	    				+pieceLetter[0]+"<br>"+pieceLetter[1]+"<br>"+pieceLetter[2]+"<br> Hold <br>"+displayHoldPiece+"</html>");
	    		
	    	}
	    });
	    
	    back.addActionListener(new ActionListener() {
	    	
	    	public void actionPerformed(ActionEvent e)
	    	{
	    		//Execute when button is pressed
	    		PauseTitle.setVisible(true);							//goes back to the pause menu
                ResumeButton.setVisible(true);
                ChangeLevelButton.setVisible(true);
                Quit.setVisible(true);
            	HelpButton.setVisible(true);
            	Spacing1.setVisible(true);
            	Spacing2.setVisible(true);
            	Spacing3.setVisible(true);
            	Spacing4.setVisible(true);
            	Spacing5.setVisible(true);
            	Spacing6.setVisible(true);
            	RestartButton.setVisible(true);
            	
	    		spacing1.setVisible(false);
	        	level.setVisible(false);
	        	spacing2.setVisible(false);
	        	level_1.setVisible(false);
	        	level_2.setVisible(false);
	        	level_3.setVisible(false);
	        	spacing3.setVisible(false);
	        	level_4.setVisible(false);
	        	level_5.setVisible(false);
	        	level_6.setVisible(false);
	        	spacing4.setVisible(false);
	        	level_7.setVisible(false);
	        	level_8.setVisible(false);
	        	level_9.setVisible(false);
	        	spacing5.setVisible(false);
	        	level_10.setVisible(false);
	        	spacing6.setVisible(false);
	        	back.setVisible(false);
	    	}
	    });
	    
	}
			
}