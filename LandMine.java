package mine;

import java.awt.Graphics2D;

import java.util.Random;

public class LandMine implements MineConstants {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		init();
		draw();
	}

	/* Initialize parts of the instance variables to the constants. */
	private static void init() {
		// TODO Auto-generated method stub
		fwidth = FRAME_WIDTH;
		fheight = FRAME_HEIGHT;
		mineSize = MINE_SIZE;
		nRows = N_MINE_ROW;
		nColumns = N_MINE_COLUMN;
	}
	
	/* Draws the initial mines. */
	private static void draw(){
		initField();
	}
	
	/*
	 *  Initialize the field. Sets some of the mine rectangles to be an actual mine
	 * (flag equals -1) randomly, and calculates the other mine rectangles' flag
	 * by counting the number of actual mines around them.
	 */
	private static void initField() {
		field = new Field(nRows, nColumns);
		for (int i = 0; i < field.getNRows(); i ++)
			for (int j = 0; j < field.getNColumns(); j ++) {
				field.place(i, j, new Cell(mineSize, 0));
			}
		
	}

	/* Private instance variables */
	
	/* These variables will be initialized as the constants defined in MineConstants.java */
	private static int fwidth; //The width of the frame
	private static int fheight; //The height of the frame
	private static int nMines; //The number of mines
	private static int mineSize; //The size(width&height) of the mine(a rectangle)
	private static int nRows; //The number of rows of mines in the field
	private static int nColumns; //The number of columns in the field
	
	/* private instance variables */
	private static Field field; //The field
	private static View view;
	private static Random rand = new Random();
	
}
