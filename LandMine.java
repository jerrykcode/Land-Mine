package mine;

import java.awt.event.MouseEvent;

import java.awt.event.MouseListener;

import java.util.Random;

import javax.swing.JFrame;


public class LandMine implements MineConstants , MouseListener {

	public LandMine() {
		view.addMouseListener(this);
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		init();
		draw();
		new LandMine();
	}

	/* Initialize parts of the instance variables to the constants. */
	private static void init() {
		// TODO Auto-generated method stub
		viewHeight = VIEW_HEIGHT;
		viewWidth = VIEW_WIDTH;
		fwidth = FRAME_WIDTH;
		fheight = FRAME_HEIGHT;
		nMines = N_MINES;
		cellSize = MINE_SIZE;
		nRows = N_MINE_ROW;
		nColumns = N_MINE_COLUMN;
	}

	/* Draws the initial mines. */
	private static void draw() {
		initField();
		view = new View(field);
		view.setSize(viewWidth, viewHeight);
		frame = new JFrame();
		frame.setSize(fwidth, fheight);
		frame.setTitle("Land Mine");
		frame. setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 
		frame.add(view);
		frame.setVisible(true);
	}

	/*
	 * Initialize the field. Sets some of the cells to be cells with a mine in
	 * it (flag equals -1) randomly, and calculates the other cells' flag by
	 * counting the number of cells with mine around them.
	 */
	private static void initField() {
		field = new Field(nRows, nColumns);
		for (int i = 0; i < field.getNRows(); i++)
			for (int j = 0; j < field.getNColumns(); j++) {
				field.place(i, j, new Cell(cellSize, 0));
			}
		/* Sets some of the cells to be mine randomly */
		for (int i = 0; i < nMines; i++) {
			int x = rand.nextInt(nRows);
			int y = rand.nextInt(nColumns);
			if (field.getCellAt(x, y).getFlag() != -1) // If the cell at (x,
														// y)is not a mine
				field.getCellAt(x, y).setFlag(-1);// Sets it to be a mine
			else
				i--;
		}
		/* Calculates the flag of other cells */
		for (int i = 0; i < nRows; i++)
			for (int j = 0; j < nColumns; j++) {
				if (field.getCellAt(i, j).getFlag() == -1) // If this cell is a
															// mine
					continue;
				Cell[] cellsAround = field.getMinesAround(i, j);
				int count = 0;
				for (Cell cell : cellsAround) // cells around
					if (cell.getFlag() == -1)
						count++;
				field.getCellAt(i, j).setFlag(count);
			}
	}

	/* Response to mouse click */
	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		int column = getColumn(e.getX());
		int row = getRow(e.getY());
		Cell cell = field.getCellAt(row, column); // The cell the mouse clicked is at
											// (x, y)
		if (cell.getVisiblity() == true) //The cell is visible
			return;
		if (e.isMetaDown()) { // If it is clicked by the right button
			if (cell.hasSign())
				cell.removeSign();
			else
				cell.addSign();
		}
		else { //Clicked by the left button
			if (cell.hasSign())
				return;
			cell.setVisibility();
			
		}
		frame.repaint();
	}

	/* Returns the column of the cell the mouse clicked at */
	private int getColumn(int x) {
		// TODO Auto-generated method stub
		int w = view.getWidth() / nColumns;
		return x/w;
	}

	/* Returns the row of the cell the mouse clicked at */
	private int getRow(int y) {
		// TODO Auto-generated method stub
		int h = view.getHeight() / nRows;
		return y/h;
	}
	
	
	@Override
	public void mouseEntered(MouseEvent e) { }

	@Override
	public void mouseExited(MouseEvent e) { }

	@Override
	public void mousePressed(MouseEvent e) { }

	@Override
	public void mouseReleased(MouseEvent e) { }
	

	/* Private instance variables */

	/*
	 * These variables will be initialized as the constants defined in
	 * MineConstants.java
	 */
	private static int viewWidth;
	private static int viewHeight;
	private static int fwidth; // The width of the frame
	private static int fheight; // The height of the frame
	private static int nMines; // The number of mines
	private static int cellSize; // The size(width&height) of the cell(a
									// rectangle)
	private static int nRows; // The number of rows of cells in the field
	private static int nColumns; // The number of columns in the field

	/* private instance variables */
	private static Field field; // The field
	private static View view;
	private static JFrame frame;
	private static Random rand = new Random();
	
}
