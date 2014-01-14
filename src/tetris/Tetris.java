package tetris;

/*
 * Finalized on January 8, 2012
 */

/* Game Program: TETRIS 
 * Producers: David Zhang & Jeff Jun 
 * Class: ICS3U1-01
 * Teacher: Ms. Wun 
 * Year: 2011-2012 
 * Due Date: January 9, 2012 
 * 
 * A Couple of Notices: 
 * 
 * (1) Certain Parts of the Code Belong Rightfully to ZetCode, 
 * as certain graphics and algorithm were referenced through their site. 
 * The parts are marked by 'ZetCode' within comment blocks. 
 * We have added a lot more original features that are not part of the original game from ZetCode. 
 * We believe that they should be rightfully credited, and want to thank them for making our game possible. 
 * To access their original Tetris code, Visit: 
 * 
 * http://zetcode.com/tutorials/javagamestutorial/tetris/
 * 
 * (2) Playing of the game requires the existence of a critical text file 'UserAccountNames.txt', which should
 * come along with the game file . If this file be missing, the game would not run properly. To fix the problem, 
 * go to your Java Workspace, and find the work file that contains the game codes; Go into the file, and right-click 
 * "New Text Document", and name it 'UserAccountNames.txt'.
 * 
 * (3) This game comes with Auto-Save feature, meaning you do not have to manually save the game. 
 * 
 */

import static tetris.Shape.*;


import java.util.Scanner; 
import java.io.*; 

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Font;


import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JLabel;

import static tetris.Board.*;

/*
 * This is the main part of the entire game. 
 */

public class Tetris extends JFrame {

    JLabel statusbar; //Statusbar displays the score, high score, level, next 3 pieces and hold piece 
    Color c= new Color(0,0,255);
    Container con=getContentPane();
    // All of the following are saved as global variable that can be used by all the programs 
    public static int curScore; // Current Score
    public static int highScore; // High Score
    public static String pwd; // Password
    public static int localLevel; // The Level Number the User uses during gameplay
    public static int globalLevel; // The Number that sets the Timer for how fast the pieces fall
    public static String globalAccount; // The account name the user uses to log in 
    Board board;
    
    public static void localToGlobal() { // This method transforms the user's level number into a number that is used for 
    									// the timer in the program 
    	switch (localLevel) {
		case 1: globalLevel=1000; break; 
		case 2: globalLevel=900; break;
		case 3: globalLevel=800; break;
		case 4: globalLevel=700; break;
		case 5: globalLevel=600; break;
		case 6: globalLevel=500; break;
		case 7: globalLevel=400; break;
		case 8: globalLevel=300; break;
		case 9: globalLevel=200; break;
		case 10: globalLevel=100; break;
		default: globalLevel=0; break; 
		}	
    }
    
    public static void loadGame() { // This method loads the main game 
    	Tetris game = new Tetris();
	    game.setLocationRelativeTo(null);
	    game.setVisible(true);
    }
     
    public static void countdown() { // This method runs a loop that counts down the 
    								//number of seconds before the game loads
    	for (int counter=5; counter>0; counter--){
    		
    		if (counter>1) {
    			System.out.println(counter+ " seconds");
    			try {
    				Thread.sleep(800L);
    			}
    			catch (Exception e) {}
    		}
    		
    		else if (counter==1) {
    			System.out.println(counter+" second");
        		try {
    				Thread.sleep(800L);
    			}
    			catch (Exception e) {}
    		}
    	}
    }

    public Tetris() {
    	Shape shape=new Shape(); 
    	shape.setRandomShape();
        statusbar = new JLabel("<html>Score <br>"+curScore+"<br> Level <br>"+localLevel+"<br> HiScore <br>"+highScore+"<br> Next 3 <br>"
				+pieceLetter[0]+"<br>"+pieceLetter[1]+"<br>"+pieceLetter[2]+"<br> Hold <br>"+displayHoldPiece+"</html>");
        statusbar.setFont(new Font("Century Gothic", Font.BOLD, 16));		// The Statusbar is created and set up here,         																	
        statusbar.setForeground(Color.white);								// including its content, font, size, colour and border 
        statusbar.setBorder(BorderFactory.createLineBorder(Color.blue));
        add(statusbar, BorderLayout.EAST);
        Board board = new Board(this);										// An instance of the game board is created 
        																	// The game is initiated
        add(board);															// Its size, title, visibility, and whether it is resizable is set up here
        board.start();
        con.setBackground(c);
        setSize(300, 510);
        setTitle("Tetris - "+globalAccount);								
        setVisible(true);
        setResizable(false);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        board.timer.stop();
   }

   public JLabel getStatusBar() { // This method constantly refreshes the statusbar
	   statusbar.repaint(); 
       return statusbar;
   }
   
   	/* This main section establishes the user account system, 
	* which takes care of signing in, signing up for a new 
	* account, and reading from text files that contain important 
	* information such as the user account's password, saved score, 
	* high score, recent level number, and the location of saved pieces
	*/
   
   public static void main(String[] args) { 
	   	String [] userInput= new String [10]; // Stores user's input through many occasions throughout the log-in/sign up process
		String [] accountNames=new String[10000]; // Leaves 10000 available spaces for different account names 
		int i=0; 							// Used as a temporary counter for some occasions 
		
		boolean  	signUpforNewAccount=false,  //Determines if the user needs to sign up for an account
					nameExists=true, 			//Determines whether the account name entered exists or not 
					nameDoesntExist=false,		//Determines if the name does not exist (Sign Up Purpose) 
					passwordIncorrect=true,		//Determines if the password is correct
					forceBreak=false; 			// Program Loop Control 
					boolean []invalidInput=new boolean[5];
					invalidInput[0]=false; invalidInput[1]=false; invalidInput[2]=false; invalidInput[3]=false; invalidInput[4]=false;
		
		Scanner input=new Scanner (System.in);
		
		try{	//This Try..Catch section reads from UserAccountNames.txt, and retrieves all the account names registered 
				// using a loop and storing all the names into the array accountNames[]
			FileInputStream fstream = new FileInputStream("UserAccountNames.txt");
			DataInputStream in = new DataInputStream(fstream);
			BufferedReader br = new BufferedReader(new InputStreamReader(in));
			String readAccountNames;
			while ((readAccountNames = br.readLine()) != null )   { //Loop: Reads and Stores all account names 
			    accountNames[i]=readAccountNames;
			    i++;
			}
		}
		catch (Exception e){
			System.err.println("Error: " + e.getMessage());    
		} 	

		System.out.println("Welcome to TETRIS(R)! Original Game Designed by Alexey Pajitnov.");
		System.out.println("This version of TETRIS is brought to you exclusively by David & Jeff (DJ) Productions, 2011-2012.");
		System.out.println("");
		System.out.println("An account is required to play. For newcomers, you could sign up for an account absolutely free of charge!");
		System.out.println("");
		
		while (forceBreak==false && nameExists==true) {
			
			System.out.print("User Name (Enter 'Sign Up' in any case to Register for an Account): "); //Prompts the user to enter a user name
																										// or to sign up for an account 
			userInput[0]=input.nextLine(); 
			String account_name="userAccount_"+userInput[0]+".txt";	
			
			for (int j=0; j<10000; j++) {
 
					if (userInput[0].equals(accountNames[j])){ //Matching account name with stored records
						globalAccount=userInput[0]; 
						while (passwordIncorrect==true) {
							System.out.print("Password: "); //Continues to prompt for password 
							userInput[3]=input.nextLine();
							try {
								FileInputStream fstream2=new FileInputStream(account_name); // Reads from the text file that contains the user profile 
								DataInputStream in2=new DataInputStream(fstream2);
								BufferedReader br2=new BufferedReader(new InputStreamReader(in2));
								String []strLine;
								strLine=new String [5];
								for (int l=0; l<5; l++) {					
									while((strLine[l]=br2.readLine())!=null) { 
										Scanner s=new Scanner(strLine[l]).useDelimiter("\\s*;\\s*");
										if (userInput[3].equals(s.next())) {
											passwordIncorrect=false;
											curScore=Integer.parseInt(strLine[0]);	//Retrieves the Current Score
											localLevel=Integer.parseInt(strLine[1]);// Retrieves Level Number
											highScore=Integer.parseInt(strLine[2]);	// Retrieves high score
											pwd=strLine[3];
											localToGlobal();	//Converts local score to global score used in timer 
											System.out.println("");
											System.out.println ("Welcome back, "+userInput[0].toUpperCase()+"! Here is your Game Profile: ");
											System.out.println("Current Score: "+curScore);		//Displays all the information to the user 
											System.out.println("High Score: "+highScore);
											System.out.println("Recent Level: "+localLevel);
											while (invalidInput[0]==false) {
												System.out.println("Enter 'Y' or 'y' to continue your game at LEVEL "+localLevel+", or enter 'N' or 'n' to start all over:");
												userInput[5]=input.next();	//Prompts the user to confirm starting a new game, or continuing their previous game 
											
												if (userInput[5].equalsIgnoreCase("y")) { // If yes, loads from saved file 
													for (int m=0; m<20;m++) {
														System.out.println("");
													}
													System.out.println("Game will automatically load in...");
													countdown(); //Countdown
													System.out.println("ENJOY THE GAME! If gamescreen does not show, click on ICON on toolbar below.");
													System.out.println("");
													System.out.println("FYI: This Game is Equipped with Auto-Save Feature. You could just quit the game by");
													System.out.println("pressing the 'X' button in the top right-hand corner, and you could continue your");
													System.out.println("game the next time you log in.");
													loadGame=true; 
													loadGame(); //Loads data from game progress file 
													invalidInput[0]=true;
												}	
											
												else if (userInput[5].equalsIgnoreCase("n")) { //Starts all over
													curScore=0; 
													while(invalidInput[1]==false) {
														System.out.print("Which level would you like to play? (1-Very Easy, 10-Very Hard): "); //Lets the user pick a level to play
														userInput[8]=input.next(); 
														if (userInput[8].equals("10")|| userInput[8].equals("9")|| userInput[8].equals("8")|| userInput[8].equals("7")
																|| userInput[8].equals("6") || userInput[8].equals("5")|| userInput[8].equals("4") || userInput[8].equals("3")
																|| userInput[8].equals("2")|| userInput[8].equals("1")) {
														localLevel=Integer.parseInt(userInput[8]);
														invalidInput[1]=true; 
														}
														else {
															System.out.println("Sorry, Please Enter a Number Between 1-10.");
															System.out.println("");
														}
													}
													if (invalidInput[1]==true) {
															localToGlobal(); 	//Converts local score to global score used in timer 
															for (int z=0; z<20;z++) {
																System.out.println("");
															}
														System.out.println("Game will automatically load in...");
														countdown();
														System.out.println("ENJOY THE GAME! If gamescreen does not show, click on ICON on toolbar below.");
														System.out.println("");
														System.out.println("FYI: This Game is Equipped with Auto-Save Feature. You could just quit the game by");
														System.out.println("pressing the 'X' button in the top right-hand corner, and you could continue your");
														System.out.println("game the next time you log in.");
														loadGame();
														invalidInput[0]=true; 
													}
												}
												else if (!userInput[5].equalsIgnoreCase("Y")|| !userInput[5].equalsIgnoreCase("N")) {
													System.out.println("Sorry, Please enter either 'Y/y' or 'N/n'.");
													System.out.println("");
													invalidInput[0]=false; 
												}
											}
										}
										else if (l==3){
											System.out.println("Sorry, Incorrect Password.");
										}
										l++;									
									}
								}
							}
							catch (Exception e) {
								System.err.println("Error: "+e.getMessage());
							}
							if (passwordIncorrect==false) {
								break;
							}
						}
						
						nameExists=true; 
						signUpforNewAccount=false;
						forceBreak=true; 
						break;				
					}
					
					else if (j==99) {
						System.out.println("Sorry, '"+userInput[0]+"' does not exist."); //Notifies the user if the account name he/she attempts
						System.out.println("");												// to sign in with does not exist within records
					}
						
					if (userInput[0].equalsIgnoreCase("sign up")) { 
						// The user could enter any case of 'Sign Up' to sign up for an account.
						signUpforNewAccount=true; 
						nameExists=false; 
						break; 
					}
				}
			}
			
			while (nameExists==false && signUpforNewAccount==true) {
		
				System.out.println("");
				System.out.print("REGISTRATION - Enter your desired user name: "); // Prompts the user to sign up for a desired account name 
				userInput[3]=input.nextLine();
				
				for (int j=0; j<100; j++) { // Checks if the name has duplicates by checking every registered account name 
										// The user will be notified if the account name has duplicates and prompt for a different user name
					if (userInput[3].equals(accountNames[j])){
						nameDoesntExist=false;
						System.out.println ("Sorry, the user name has been taken. Please try another one."); 
						break;
					}
					
					else nameDoesntExist=true;
					globalAccount=userInput[3];
				}
			
				if (nameDoesntExist==true) {
					System.out.print("Enter your desired password: (Recommended 5-10 digits/numbers): "); //Prompts the user for a password
					userInput[4]=input.nextLine(); 
					pwd=userInput[4];
					String account_name2="userAccount_"+userInput[3]+".txt"; 
					System.out.println("");
					System.out.println("Your account will be created instantly after selecting your level later on. Thank you!");
					System.out.println("Press any key to go through game tutorial, otherwise enter 'skip' in any case to skip tutorial: "); 
					userInput[7]=input.next(); 
			
					if (userInput[7].equalsIgnoreCase("skip")) {
						System.out.println("");
						while(invalidInput[2]==false) {
							System.out.print("Which level would you like to play? (1-Very Easy, 10-Very Hard): "); //Lets the user pick a level to play
							userInput[8]=input.next(); 
							if (userInput[8].equals("10")|| userInput[8].equals("9")|| userInput[8].equals("8")|| userInput[8].equals("7")
									|| userInput[8].equals("6") || userInput[8].equals("5")|| userInput[8].equals("4") || userInput[8].equals("3")
									|| userInput[8].equals("2")|| userInput[8].equals("1")) {
							localLevel=Integer.parseInt(userInput[8]);
							invalidInput[2]=true; 
							}
							else {
								System.out.println("Sorry, Please Enter a Number Between 1-10.");
								System.out.println("");
							}
						}
						if (invalidInput[2]==true) {
							localToGlobal(); 	
							try {																	
								FileWriter fwriter = new FileWriter ("UserAccountNames.txt",true); 	// Writes the user name that the user has signed up for 					 																									
								BufferedWriter out = new BufferedWriter (fwriter);					// within 'UserAccountNames.txt'
								out.newLine(); out.write(userInput[3]); out.close();  				
							FileWriter fwriter2 = new FileWriter (account_name2,true); 			
							BufferedWriter out2 = new BufferedWriter (fwriter2); 
							out2.write("0"); 													// Writes the user's password into a text file called 
							out2.newLine(); 													// 'userAccount_USERNAME.txt', where USERNAME is the registered
							out2.write(userInput[8]); 											// account name; The file also initiates the user's score, high score,
							out2.newLine();														// and the level number he/she wishes to play.
							out2.write("0");
							out2.newLine(); 
							out2.write(""+pwd);out2.close(); 		    
						} //try
						catch (IOException e) {
							System.err.println("Error: " + e.getMessage());
						} //catch
						for (int z=0; z<20;z++) {
							System.out.println("");
						}
						System.out.println("Game will automatically load in..."); // Countdown
						countdown();
						System.out.println("ENJOY THE GAME! If gamescreen does not show, click on ICON on toolbar below.");
						System.out.println("");
						System.out.println("FYI: This Game is Equipped with Auto-Save Feature. You could just quit the game through");
						System.out.println("pressing the 'X' button in the top right-hand corner, and you could continue your");
						System.out.println("game the next time you log in.");
						loadGame();
					}
					break; 
				}
				else {	// Goes through the game tutorial with the user in an easy-to-follow manner
					System.out.println("");
					System.out.println("");
					System.out.println("");
					System.out.println("-TETRIS Game Rules-");
					System.out.println("In Tetris, Your Goal is to CLEAR LINES.");
					System.out.println("A line is defined as filled squares from the very left side of the game field to the very right side.");
					System.out.println("");
					System.out.println("Acknowledge by entering any key: "); // Prompts for user acknowledgement after reading a small paragraph
					userInput[9]=input.next(); 
					System.out.println("");
					System.out.println("A Tetrimino is a shape consisting of 4 squares. It could come in 7 different shapes: L, J, O, S, Z, T or I.");
					System.out.println("The object is to manipulate these pieces by moving them left to right, rotating them by 90 degrees units");
					System.out.println("with the aim of creating a horizontal line of blocks without gaps.");
					System.out.println("");
					System.out.println("Acknowledge by entering any key: "); // Prompts for user acknowledgement after reading a small paragraph
					userInput[9]=input.next(); 
					System.out.println("");
					System.out.println("When a horizontal line is filled, it disappears, and the blocks above would fall down."); 
					System.out.println("Your score is based on how many lines you clear before the stacks of Tetriminoes reach the top and no new pieces could fall down.");
					System.out.println("Double the score is awarded for clearing 4 lines (A 'Tetris') simultaneously. While playing, press 'H' to temporarily swap your");
					System.out.println("current piece with the next piece. After the first swap, you could swap your falling piece with the shape that is shown under 'Hold'.");
					System.out.println("THE GAME IS OVER WHEN NO NEW PIECE COULD FALL DOWN.");
					System.out.println("");
					System.out.println("Acknowledge by entering any key: "); // Prompts for user acknowledgement after reading a small paragraph
					userInput[9]=input.next(); 
					System.out.println("");
					System.out.println("-Game Controls-");
					System.out.println("W/Up Arrow - Rotate Right 		A/Left Arrow - Move Left");
					System.out.println("D/Right Arrow - Move Right 		Down Arrow - Rotate Left");
					System.out.println("S - Move Down Faster			SPACEBAR - Drop Piece to Bottom Instantly");
					System.out.println("H - Hold Piece					P - Pause Game/Access Pause Menu"); 
					System.out.println("");
					System.out.println("Acknowledge by entering any key: "); // Prompts for user acknowledgement after reading a small paragraph
					userInput[9]=input.next(); 
					System.out.println("");
					System.out.println("You are now ready to play TETRIS!");
					while(invalidInput[4]==false) {
						System.out.print("Which level would you like to play? (1-Very Easy, 10-Very Hard): "); //Lets the user pick a level to play
						userInput[8]=input.next(); 
						if (userInput[8].equals("10")|| userInput[8].equals("9")|| userInput[8].equals("8")|| userInput[8].equals("7")
								|| userInput[8].equals("6") || userInput[8].equals("5")|| userInput[8].equals("4") || userInput[8].equals("3")
								|| userInput[8].equals("2")|| userInput[8].equals("1")) {
						localLevel=Integer.parseInt(userInput[8]);
						invalidInput[4]=true; 
						}
						else {
							System.out.println("Sorry, Please Enter a Number Between 1-10.");
							System.out.println("");
						}
					}
					if (invalidInput[4]==true) {
						localToGlobal(); 
						try {
							FileWriter fwriter = new FileWriter ("UserAccountNames.txt",true); // Writes the user name that the user has signed up for 
							BufferedWriter out = new BufferedWriter (fwriter);					// within 'UserAccountNames.txt'
							out.newLine(); out.write(userInput[3]); out.close();  
							FileWriter fwriter2 = new FileWriter (account_name2,true); // Writes the user's password into a text file called 						
							BufferedWriter out2 = new BufferedWriter (fwriter2); 		// 'userAccount_USERNAME.txt', where USERNAME is the registered 						
							out2.write("0"); 											// account name; The file also initiates the user's score, high score, 						
							out2.newLine(); 											// and the level number he/she wishes to play.
							out2.write(userInput[8]); 
							out2.newLine();
							out2.write("0");
							out2.newLine(); 
							out2.write(pwd);out2.close(); 		    
						} //try
						catch (IOException e) {
							System.err.println("Error: " + e.getMessage());
						} //catch
						System.out.println("");
						System.out.println("Game will automatically load in..."); // Countdown
						countdown();
						System.out.println("ENJOY THE GAME! If gamescreen does not show, click on ICON on toolbar below.");
						System.out.println("");
						System.out.println("FYI: This Game is Equipped with Auto-Save Feature. You could just quit the game by");
						System.out.println("pressing the 'X' button in the top right-hand corner, and you could continue your");
						System.out.println("game the next time you log in.");
						loadGame();
					}
				}
			}
		} 
	}
}
