package tetris;

/*
 * Finalized on January 8, 2012
 */

import java.util.Random;
import java.lang.Math;

/*
 * This is the part of the program that creates random shapes, and assigns the name of 
 * the shapes to them. It also calculates the coordinates of the shapes when they rotate 
 * left or right; Lastly, it transfers the shape into a visible letter that is seen by 
 * the user when the game is played, so they could know the upcoming pieces.
 */

public class Shape {
		
		public static int [] pieceNumber= new int [4]; // An array that stores an instance of three random shapes formed
		public static String [] pieceLetter= new String [3]; // Corresponding array that stores the letters representing the
		public static int j=0; 	//Counter purposes			// three random shapes
		public static int curPieceNumber; // The number of the shape that is being dropped
		public static enum Tetrominoes { NoShape, ZShape, SShape, LineShape, // An enum array of all 7 shapes, used in all the programs
            TShape, SquareShape, LShape, MirroredLShape };
            
		public static void pieceNumbertoLetter() { // Transfers the number of the random shape 
			switch (pieceNumber[1]) {				// into a letter that represents the shape.
			case 1: pieceLetter[0]="Z"; break; 		// The purpose is for the user to visualize
			case 2: pieceLetter[0]="S"; break;		// the upcoming pieces through the statusbar
			case 3: pieceLetter[0]="I"; break;
			case 4: pieceLetter[0]="T"; break;
			case 5: pieceLetter[0]="O"; break;
			case 6: pieceLetter[0]="L"; break;
			case 7: pieceLetter[0]="J"; break;
			default: pieceLetter[0]=""; break; 
			}
			switch (pieceNumber[2]){
			case 1: pieceLetter[1]="Z"; break; 
			case 2: pieceLetter[1]="S"; break;
			case 3: pieceLetter[1]="I"; break;
			case 4: pieceLetter[1]="T"; break;
			case 5: pieceLetter[1]="O"; break;
			case 6: pieceLetter[1]="L"; break;
			case 7: pieceLetter[1]="J"; break;
			default: pieceLetter[1]=""; break; 
			}
			switch (pieceNumber[3]){
			case 1: pieceLetter[2]="Z"; break; 
			case 2: pieceLetter[2]="S"; break;
			case 3: pieceLetter[2]="I"; break;
			case 4: pieceLetter[2]="T"; break;
			case 5: pieceLetter[2]="O"; break;
			case 6: pieceLetter[2]="L"; break;
			case 7: pieceLetter[2]="J"; break;
			default: pieceLetter[2]=""; break; 
			}
		}

	    private Tetrominoes pieceShape; // The random shape formed is matched with an enum 
	    private int coords[][]; // Setting up a coordinate table to create the pieces 
	    private int[][][] coordsTable;


	    public Shape() { //ZetCode

	        coords = new int[4][2];
	        setShape(Tetrominoes.NoShape); //Initialize everything on the board as Nothing

	    }

	    public void setShape(Tetrominoes shape) { //ZetCode 

	         coordsTable = new int[][][] { //Setting the coordinates for each shape: 
	            { { 0, 0 },   { 0, 0 },   { 0, 0 },   { 0, 0 } },	//No Shape
	            { { 0, -1 },  { 0, 0 },   { -1, 0 },  { -1, 1 } },	//Z Shape
	            { { 0, -1 },  { 0, 0 },   { 1, 0 },   { 1, 1 } },	//S Shape
	            { { 0, -1 },  { 0, 0 },   { 0, 1 },   { 0, 2 } },	//Line Shape
	            { { -1, 0 },  { 0, 0 },   { 1, 0 },   { 0, 1 } },	//T Shape
	            { { 0, 0 },   { 1, 0 },   { 0, 1 },   { 1, 1 } },	//O Shape
	            { { -1, -1 }, { 0, -1 },  { 0, 0 },   { 0, 1 } },	//L Shape
	            { { 1, -1 },  { 0, -1 },  { 0, 0 },   { 0, 1 } }	//J Shape
	        };

	        for (int i = 0; i < 4 ; i++) {
	            for (int j = 0; j < 2; ++j) {
	                coords[i][j] = coordsTable[shape.ordinal()][i][j];
	            }
	        }
	        pieceShape = shape; //Creates an instance of the shape formed

	    }

	    private void setX(int index, int x) { coords[index][0] = x; } //x-value for the coordinate
	    private void setY(int index, int y) { coords[index][1] = y; } //y-value for the coordinate 
	    public int x(int index) { return coords[index][0]; }			// Setting up Coordinates 
	    public int y(int index) { return coords[index][1]; }
	    public Tetrominoes getShape()  { return pieceShape; }			//Returns an instance of the shape created

	    public void setRandomShape()
	    { 
	    	j++;
	    	Tetrominoes[] values = Tetrominoes.values(); //The value of enum that determines which shape will be formed
	    	if (j==1) {
	    		for (int i=0; i<4; i++) {
	    			Random r = new Random(); //A random three-decimal number between 0 to 1 is generated
	    			pieceNumber[i] = Math.abs(r.nextInt()) % 7 + 1;	   // Using this calculation to round the decimal into a valid integer
	    		} 
	    		pieceNumbertoLetter();	//Converts the number to a letter
	    		setShape(values[pieceNumber[0]]);	//Sets the shape that is falling down
	    		curPieceNumber=pieceNumber[0];
	    		pieceNumber[0]=pieceNumber[1];		// The next piece becomes the piece that will fall down
	    		pieceNumber[1]=pieceNumber[2];		// The rest follow along
	    		pieceNumber[2]=pieceNumber[3];
	    		pieceNumber[3]=0;
	    	}
	    	
	    	else if(j>1) {
	    		Random r=new Random(); 	    		
	    		pieceNumber[3]= Math.abs(r.nextInt())%7+1;
	    		pieceNumbertoLetter();
	    		setShape(values[pieceNumber[0]]);
	    		curPieceNumber=pieceNumber[0]; 		// The next piece becomes the piece that will fall down
	    		pieceNumber[0]=pieceNumber[1];		// The rest follow along
	    		pieceNumber[1]=pieceNumber[2];
	    		pieceNumber[2]=pieceNumber[3];
	    		pieceNumber[3]=0; 
	    	}
	    }

	    public int minX()	//ZetCode
	    {	//Calculates the minimum x-value for the pieces to fall down
	      int m = coords[0][0];
	      for (int i=0; i < 4; i++) {
	          m = Math.min(m, coords[i][0]);
	      }
	      return m;
	    }


	    public int minY() 	//ZetCode
	    { 	//Calculates the minimum y-value for the pieces to fall down 
	      int m = coords[0][1];
	      for (int i=0; i < 4; i++) {
	          m = Math.min(m, coords[i][1]);
	      }
	      return m;
	    }

	    public Shape rotateLeft() //ZetCode
	    {	//Calculates coordinates that result from continuous rotating left
	        if (pieceShape == Tetrominoes.SquareShape)
	            return this;

	        Shape result = new Shape();
	        result.pieceShape = pieceShape;

	        for (int i = 0; i < 4; ++i) {
	            result.setX(i, y(i));
	            result.setY(i, -x(i));
	        }
	        return result;
	    }

	    public Shape rotateRight()	//ZetCode
	    {	//Calculates coordinates that result from continuous rotating right 
	        if (pieceShape == Tetrominoes.SquareShape)
	            return this;

	        Shape result = new Shape();
	        result.pieceShape = pieceShape;

	        for (int i = 0; i < 4; ++i) {
	            result.setX(i, -y(i));
	            result.setY(i, x(i));
	        }
	        return result;
	    }
	}
