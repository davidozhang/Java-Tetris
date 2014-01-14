package tetris;

/*
 * Finalized on January 8, 2012
 */

import java.io.*; 
import java.util.Scanner;
import java.util.StringTokenizer;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.Timer;

import tetris.Shape.Tetrominoes;

import static tetris.Shape.*;
import static tetris.Tetris.*;

/*
 * This part controls the main aspects of the gameplay. It is a collection of important methods, such as graphics,
 * reading from keyboard input, loading data from progress file, pausing and resuming, determining when to
 * drop a new piece, when to remove full lines, and moving the pieces above down, etc. 
 */

public class Board extends JPanel implements ActionListener {
	
	String accountName="gameProgress_"+globalAccount+".txt";
	String accountName2="userAccount_"+globalAccount+".txt";
	public static boolean holdExists=false; 	//Determines if a hold piece exists or not
	public static boolean loadGame=false; 		//Determines if there is a saved game to launch or not 
	public static int holdMemory; 	//The hold piece number that is stored until next swap of pieces
	public static int holdPiece;	//Temporary variable
	public static String displayHoldPiece=""; // Displays the letter of the piece on hold
	public static String [] textFileArray=new String [1000]; 	//Array used to read from progress file
	public static String [] shapeName= new String [1000];		//Array that stores the shape name from progress file
	public static int [] x_coord=new int [1000];		//Array that stores the x-coordinates
	public static int [] y_coord=new int [1000];		//Array that stores the y-coordinates
	
	public static boolean isPaused = false;	//Determines if game is paused or not
	public static boolean restart = false;	//Determines if the game needs to be restarted or not 
    final int BoardWidth = 10;	//Width of board
    final int BoardHeight = 22;	//Height of board
    String []strLine=new String[1000];	//Used to store some variable
    int i=0; //Counter purpose

    Timer timer;	//Timer initiated
    boolean isFallingFinished = false;	//Determines if the pieces have finished falling or not
    boolean isStarted = false;			//Determines if the game has started or not
    int numLinesRemoved=0;			//Number of lines removed
    int curX = 0;			//Currrent X-coordinate of the piece dropped down
    int curY = 0;			//Current Y-coordinate of the piece dropped down 
    JLabel statusbar;		//statusbar set up 
    Shape curPiece;			//Current piece
    Tetrominoes[] board;  	//Array that stores the piece shape for each box on the board
    
    
    public Board(Tetris parent) { //Set up timer and board array 
       setFocusable(true);
       curPiece = new Shape();
       timer = new Timer(globalLevel, this);
       Count_Down cd = new Count_Down(this);
       statusbar = parent.getStatusBar();
       board = new Tetrominoes[BoardWidth * BoardHeight];
       addKeyListener(new TAdapter());
       clearBoard();  //clears the board 
    }

    public void actionPerformed(ActionEvent e) { //ZetCode
        if (isFallingFinished) {
            isFallingFinished = false;
            newPiece();
            
        } else {
            oneLineDown(); //Initiate move everything one line down variable
        }
    }


    int squareWidth() // Initialize the width of each square, Zetcode
    { 
    	return (int) getSize().getWidth() / BoardWidth; 
    }
    
    int squareHeight() { // Initialize the height of each square, Zetcode
    	return (int) getSize().getHeight() / BoardHeight;   
    }
    
    Tetrominoes shapeAt(int x, int y) // Determines what shape is at a specific coordinate; returns a piece shape enum value
    { 
    	return board[(y * BoardWidth) + x]; 
    }
    
    public void start() //ZetCode
    {
        if (isPaused)
            return;
        
        isStarted = true;
        isFallingFinished = false;
        clearBoard();
        statusbar.repaint();
        if (loadGame==true) {	//Loads from file if user wants to continue game
        	loadFromFile();	
        }
    }
    
    public void saveAndQuit() { //Writes game progress into gameProgress_ACCOUNTNAME.txt
    	try{
    		FileWriter fwriter = new FileWriter (accountName); 
			BufferedWriter out = new BufferedWriter (fwriter);
        	for (int i = BoardHeight - 1; i >= 0; --i) {
                for (int j = 0; j < BoardWidth; ++j) {
                		out.write(""+shapeAt(j,i)+" "+j+" "+i); //Records shape and its coordinates into the file
                		out.newLine(); 
                }
            } 
			out.close(); 
			
			FileWriter fwriter2=new FileWriter(accountName2);	// Updates the user's score, high score, local level and password
			BufferedWriter out2=new BufferedWriter(fwriter2);	//in file containing user profile 
			out2.write(""+curScore);
			out2.newLine(); 
			out2.write(""+localLevel);
			out2.newLine(); 
			out2.write(""+highScore);
			out2.newLine(); 
			out2.write(""+pwd); 
			out2.close(); 
    	}
    	catch (Exception e) {
    		System.err.println("Error: "+e.getMessage());
    	}
    	
    }
    
   public void clearSavedFile() {
	   try {
		   FileWriter fw=new FileWriter(accountName); 
		   BufferedWriter out = new BufferedWriter (fw);
		   for (int i = BoardHeight - 1; i >= 0; --i) {
               for (int j = 0; j < BoardWidth; ++j) {
               		out.write(""+"NoShape"+" "+j+" "+i); //Overwrites entire file with NoShape
               		out.newLine(); 
               }
           } 
			out.close(); 		   
	   }
	   catch (Exception e) {}
   }
    
   public void loadFromFile() { //Reads from gameProgress_ACCOUNTNAME.txt	
    	try {
    		FileReader fr= new FileReader(accountName);
			Scanner src= new Scanner(fr);
			for (int i=0; i<660; i++) {
				strLine[i]=src.next();  
				StringTokenizer st=new StringTokenizer(strLine[i], ", ;");	//Tokenizes all items, categorizing them
				textFileArray[i]=st.nextToken();
			}			
    	}
    	catch (Exception e) {} 
    	int z=0; 
		for (int k=0; k<660; k++) {
			
			shapeName[z]= textFileArray[k];	//all Shape Names stored
			k++; 
			x_coord[z]= Integer.parseInt(textFileArray[k]);	//all X-Coordinates stored
			k++;
			y_coord[z]=Integer.parseInt(textFileArray[k]);	//all Y-Coordinates stored
			
			/*
			 * The if..else if.. system matches the shape's characteristic and coordinate
			 * to a specific position on the board; That way, the game could immediately paint
			 * out what piece is in which square on the board
			 */
			if (shapeName[z].equals("NoShape")) {
				board[(y_coord[z]*BoardWidth+x_coord[z])]=Tetrominoes.NoShape; 
			}
			else if (shapeName[z].equals("ZShape")) {
				board[(y_coord[z]*BoardWidth+x_coord[z])]=Tetrominoes.ZShape;
			}
			else if (shapeName[z].equals("SShape")) {
				board[(y_coord[z]*BoardWidth+x_coord[z])]=Tetrominoes.SShape;
			}
			else if (shapeName[z].equals("LineShape")) {
				board[(y_coord[z]*BoardWidth+x_coord[z])]=Tetrominoes.LineShape;
			}
			else if (shapeName[z].equals("TShape")) {
				board[(y_coord[z]*BoardWidth+x_coord[z])]=Tetrominoes.TShape;
			}
			else if (shapeName[z].equals("SquareShape")) {
				board[(y_coord[z]*BoardWidth+x_coord[z])]=Tetrominoes.SquareShape;
			}
			else if (shapeName[z].equals("LShape")) {
				board[(y_coord[z]*BoardWidth+x_coord[z])]=Tetrominoes.LShape;
			}
			else if (shapeName[z].equals("MirroredLShape")) {
				board[(y_coord[z]*BoardWidth+x_coord[z])]=Tetrominoes.MirroredLShape;
			}
 			z++; 
		}
    }
    
   public void paint(Graphics g) //Paints all the pieces, ZetCode
   { 
       super.paint(g);

       Dimension size = getSize();
       int boardTop = (int) size.getHeight() - BoardHeight * squareHeight();

       for (int i = 0; i < BoardHeight; ++i) {
           for (int j = 0; j < BoardWidth; ++j) {
               Tetrominoes shape = shapeAt(j, BoardHeight - i - 1);
               if (shape != Tetrominoes.NoShape)
                   drawSquare(g, 0 + j * squareWidth(),
                              boardTop + i * squareHeight(), shape);
           }
       }

       if (curPiece.getShape() != Tetrominoes.NoShape) {
           for (int i = 0; i < 4; ++i) {
               int x = curX + curPiece.x(i);
               int y = curY - curPiece.y(i);
               drawSquare(g, 0 + x * squareWidth(),
               boardTop + (BoardHeight - y - 1) * squareHeight(),
               curPiece.getShape());
           }
       }
   }


    private void holdPiece() 	//Holds a piece if user wants to
    {
    	if (holdExists==false) { 
			holdExists=true; 	//First time initiation
	    	holdPiece=curPieceNumber; //Transfer the hold piece to current piece
	    	holdMemory=holdPiece;
	    	isFallingFinished=true;
	    	switch (curPieceNumber) {	//Displays hold piece
			case 1: displayHoldPiece="Z"; break; 
			case 2: displayHoldPiece="S"; break;
			case 3: displayHoldPiece="I"; break;
			case 4: displayHoldPiece="T"; break;
			case 5: displayHoldPiece="O"; break;
			case 6: displayHoldPiece="L"; break;
			case 7: displayHoldPiece="J"; break;
			default: displayHoldPiece=""; break; 
			}	
    	}
    	else {
    		holdPiece=holdMemory; //After the first time, the game would take the piece that is on hold
    		pieceNumber[0]=holdMemory; //and swap with the next piece
    		holdMemory=curPieceNumber; 
    		isFallingFinished=true; 
    		switch (curPieceNumber) {
    		case 1: displayHoldPiece="Z"; break; //Display hold piece
			case 2: displayHoldPiece="S"; break;
			case 3: displayHoldPiece="I"; break;
			case 4: displayHoldPiece="T"; break;
			case 5: displayHoldPiece="O"; break;
			case 6: displayHoldPiece="L"; break;
			case 7: displayHoldPiece="J"; break;
    		}    		
    	}
    }
    
    private void pause()	//Displays a pause menu 
    {	
        if (!isStarted)
            return;

        isPaused = !isPaused;
        if (isPaused) {
            
        	Pause_Menu pm = new Pause_Menu(this);          //Create an instance of the pause menu 
        	timer.stop();
            if (curScore<=highScore) {
            statusbar.setText("<html>Score <br>"+curScore+"<br> Level <br>"+localLevel+"<br> HiScore <br>"+highScore+"<br> Next 3 <br>"
    				+pieceLetter[0]+"<br>"+pieceLetter[1]+"<br>"+pieceLetter[2]+"<br> Hold <br>"+displayHoldPiece+"</html>");
            }
            else {
            	highScore=curScore; //Updates the high score 
            	statusbar.setText("<html>Score <br>"+curScore+"<br> Level <br>"+localLevel+"<br> HiScore <br>"+highScore+"<br> Next 3 <br>"
        				+pieceLetter[0]+"<br>"+pieceLetter[1]+"<br>"+pieceLetter[2]+"<br> Hold <br>"+displayHoldPiece+"</html>");
            }
        } else {
            timer.start(); //When resumed, timer starts
            if (restart) {
            	curScore=0;
            	if (curScore<=highScore) {
                    statusbar.setText("<html>Score <br>"+curScore+"<br> Level <br>"+localLevel+"<br> HiScore <br>"+highScore+"<br> Next 3 <br>"
            				+pieceLetter[0]+"<br>"+pieceLetter[1]+"<br>"+pieceLetter[2]+"<br> Hold <br>"+displayHoldPiece+"</html>");
                    }
                    else {
                    	highScore=curScore; //Update the high score
                    	statusbar.setText("<html>Score <br>"+curScore+"<br> Level <br>"+localLevel+"<br> HiScore <br>"+highScore+"<br> Next 3 <br>"
                				+pieceLetter[0]+"<br>"+pieceLetter[1]+"<br>"+pieceLetter[2]+"<br> Hold <br>"+displayHoldPiece+"</html>");
                    }
            }
            else {
            	if (curScore<=highScore) {
                    statusbar.setText("<html>Score <br>"+curScore+"<br> Level <br>"+localLevel+"<br> HiScore <br>"+highScore+"<br> Next 3 <br>"
            				+pieceLetter[0]+"<br>"+pieceLetter[1]+"<br>"+pieceLetter[2]+"<br> Hold <br>"+displayHoldPiece+"</html>");
                    }
                    else {
                    	highScore=curScore; //Updates the high score
                    	statusbar.setText("<html>Score <br>"+curScore+"<br> Level <br>"+localLevel+"<br> HiScore <br>"+highScore+"<br> Next 3 <br>"
                				+pieceLetter[0]+"<br>"+pieceLetter[1]+"<br>"+pieceLetter[2]+"<br> Hold <br>"+displayHoldPiece+"</html>");
                    }
            }
        }
        repaint();
    }
    
    private void dropDown()	//Drops a piece down, ZetCode
    {
        int newY = curY;
        while (newY > 0) {
            if (!tryMove(curPiece, curX, newY - 1))
                break;
            --newY;
        }
        pieceDropped();
    }

    private void oneLineDown() 	//Moves every line one line down, ZetCode
    {
        if (!tryMove(curPiece, curX, curY - 1))
            pieceDropped();
    }


    public void clearBoard()	//Makes the entire board empty, or No Shape. ZetCode
    {
        for (int i = 0; i < BoardHeight * BoardWidth; ++i)
            board[i] = Tetrominoes.NoShape; 
    }

    private void pieceDropped()	//Determines if piece has dropped down; if it has, remove any full lines 
    {							//ZetCode
        for (int i = 0; i < 4; ++i) {
            int x = curX + curPiece.x(i);
            int y = curY - curPiece.y(i);
            board[(y * BoardWidth) + x] = curPiece.getShape();
        }

        removeFullLines();
        if (!isFallingFinished)
        	saveAndQuit();
            newPiece();
    }

    public void newPiece()	//Releases a new random shape 
    {
        curPiece.setRandomShape();
        curX = BoardWidth / 2 + 1;
        curY = BoardHeight - 1 + curPiece.minY();
        if (curScore<=highScore) {	
            statusbar.setText("<html>Score <br>"+curScore+"<br> Level <br>"+localLevel+"<br> HiScore <br>"+highScore+"<br> Next 3 <br>"
    				+pieceLetter[0]+"<br>"+pieceLetter[1]+"<br>"+pieceLetter[2]+"<br> Hold <br>"+displayHoldPiece+"</html>");
            }
            else {
            	highScore=curScore; 
            	statusbar.setText("<html>Score <br>"+curScore+"<br> Level <br>"+localLevel+"<br> HiScore <br>"+highScore+"<br> Next 3 <br>"
        				+pieceLetter[0]+"<br>"+pieceLetter[1]+"<br>"+pieceLetter[2]+"<br> Hold <br>"+displayHoldPiece+"</html>");
            }
        
        if (!tryMove(curPiece, curX, curY)) {	//If the new piece cannot move down, Game is over
        	statusbar.setText("<html>Game <br>"+"Over <br>"+"<br>"+"Your <br>"+"Final <br>"+"Score: <br>"+curScore+"</html>");     
            curPiece.setShape(Tetrominoes.NoShape);
            timer.stop();
            Game_Over go = new Game_Over(this); //When the Game is over, it prompts user if player wants to play again or not
            isStarted = false;       
        }
    }

    private boolean tryMove(Shape newPiece, int newX, int newY)		//Tries to move the piece; then releases new piece, ZetCode
    {
        for (int i = 0; i < 4; ++i) {
            int x = newX + newPiece.x(i);
            int y = newY - newPiece.y(i);
            if (x < 0 || x >= BoardWidth || y < 0 || y >= BoardHeight)
                return false;
            if (shapeAt(x, y) != Tetrominoes.NoShape)
                return false;
        }

        curPiece = newPiece;
        curX = newX;
        curY = newY;
        repaint();
        return true;
    }

    private void removeFullLines()	//Removes any full lines, Parts belong to Zetcode
    { 
        int numFullLines = 0;

        for (int i = BoardHeight - 1; i >= 0; --i) {
            boolean lineIsFull = true;
            
            for (int j = 0; j < BoardWidth; ++j) {
                if (shapeAt(j, i) == Tetrominoes.NoShape) {
                    lineIsFull = false;
                    break;
                }
            }

            if (lineIsFull) {
                ++numFullLines;
                for (int k = i; k < BoardHeight - 1; ++k) {
                    for (int j = 0; j < BoardWidth; ++j)
                         board[(k * BoardWidth) + j] = shapeAt(j, k + 1);
                }
            }
        }

        if (numFullLines > 0) {
        	if (numFullLines<4) {	//Less than 4 lines, add normal score
        		numLinesRemoved += 100*numFullLines;
        		curScore+=numLinesRemoved;
        		numLinesRemoved=0;
        	}
        	else if (numFullLines==4) {		//4 lines cleared simultaneously, add double score 
        		numLinesRemoved+= 200*numFullLines; 
        		curScore+=numLinesRemoved; 
        		numLinesRemoved=0; 
            }
        	if (curScore<=highScore) {	//Update score and high score 
                statusbar.setText("<html>Score <br>"+curScore+"<br> Level <br>"+localLevel+"<br> HiScore <br>"+highScore+"<br> Next 3 <br>"
        				+pieceLetter[0]+"<br>"+pieceLetter[1]+"<br>"+pieceLetter[2]+"<br> Hold <br>"+displayHoldPiece+"</html>");
                saveAndQuit();
                }
                else {	//Update score and high score
                	highScore=curScore; 
                	statusbar.setText("<html>Score <br>"+curScore+"<br> Level <br>"+localLevel+"<br> HiScore <br>"+highScore+"<br> Next 3 <br>"
            				+pieceLetter[0]+"<br>"+pieceLetter[1]+"<br>"+pieceLetter[2]+"<br> Hold <br>"+displayHoldPiece+"</html>");
                	saveAndQuit();
                }
        	
            statusbar.repaint();
            curPiece.setShape(Tetrominoes.NoShape);
            repaint();
        }
     }

    private void drawSquare(Graphics g, int x, int y, Tetrominoes shape)	//Draws each square for each shape
    {
        Color colors[] = { new Color(0, 0, 0), new Color(204, 102, 102), //Sets up colour array for different colours of different shapes
            new Color(102, 204, 102), new Color(102, 102, 204), 
            new Color(204, 204, 102), new Color(204, 102, 204), 
            new Color(102, 204, 204), new Color(218, 170, 0)
        };


        Color color = colors[shape.ordinal()];

        g.setColor(color);
        g.fillRect(x + 1, y + 1, squareWidth() - 2, squareHeight() - 2);

        g.setColor(color.brighter());
        g.drawLine(x, y + squareHeight() - 1, x, y);
        g.drawLine(x, y, x + squareWidth() - 1, y);

        g.setColor(color.darker());
        g.drawLine(x + 1, y + squareHeight() - 1,
                         x + squareWidth() - 1, y + squareHeight() - 1);
        g.drawLine(x + squareWidth() - 1, y + squareHeight() - 1,
                         x + squareWidth() - 1, y + 1);

    }

    class TAdapter extends KeyAdapter {	//This part reads the keyboard input
         public void keyPressed(KeyEvent e) {

             if (!isStarted || curPiece.getShape() == Tetrominoes.NoShape) {  
                 return;
             }

             int keycode = e.getKeyCode();

             if (keycode == 'p' || keycode == 'P') {
                 pause();
                 return;
             }

             if (isPaused)
                 return;

             switch (keycode) {
             
             case 'H':
            	 holdPiece();
            	 break;
             case 'W':
            	 tryMove(curPiece.rotateLeft(), curX, curY);
                 break;
             case 'w': 
            	 tryMove(curPiece.rotateLeft(), curX, curY);
                 break;
             case 'A': 
            	 tryMove(curPiece, curX - 1, curY);
                 break;
             case 'a': 
            	 tryMove(curPiece, curX - 1, curY);
                 break;
             case 'S': 
                 oneLineDown();
                 break;
             case 's': 
                 oneLineDown();
                 break;
             case 'D':
            	 tryMove(curPiece, curX + 1, curY);
                 break;
             case 'd':
            	 tryMove(curPiece, curX + 1, curY);
                 break;
             case KeyEvent.VK_LEFT:
                 tryMove(curPiece, curX - 1, curY);
                 break;
             case KeyEvent.VK_RIGHT:
                 tryMove(curPiece, curX + 1, curY);
                 break;
             case KeyEvent.VK_DOWN:
                 tryMove(curPiece.rotateRight(), curX, curY);
                 break;
             case KeyEvent.VK_UP:
                 tryMove(curPiece.rotateLeft(), curX, curY);
                 break;
             case KeyEvent.VK_SPACE:
                 dropDown();
                 break;
             }

         }
     }
}


