package mine;

import java.awt.event.MouseEvent;

import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Random;

import javax.swing.JFrame;

public class LandMine implements MineConstants, MouseListener {

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
		gameOver = win = lost = false;
	}

	/* Draws the initial mines. */
	private static void draw() {
		initField();
		view = new View(field);
		view.setSize(viewWidth, viewHeight);
		frame = new JFrame();
		frame.setSize(fwidth, fheight);
		frame.setTitle("Land Mine");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
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
				field.place(i, j, new Cell(i, j, cellSize, 0));
			}
		/* Sets some of the cells to be mine randomly */
		for (int i = 0; i < nMines; i++) {
			int x = rand.nextInt(nRows);
			int y = rand.nextInt(nColumns);
			if (!field.getCellAt(x, y).hasMine()) // If the cell at (x,
													// y)is not a mine
				field.getCellAt(x, y).setToHasMine();// Sets it to be a mine
			else
				i--;
		}
		/* Calculates the flag of other cells */
		for (int i = 0; i < nRows; i++)
			for (int j = 0; j < nColumns; j++) {
				if (field.getCellAt(i, j).hasMine()) // If this cell has a
														// mine
					continue;
				Cell[] cellsAround = field.getCellsAround(i, j);
				int count = 0;
				for (Cell cell : cellsAround) // cells around
					if (cell.hasMine())
						count++;
				field.getCellAt(i, j).setFlag(count);
			}
	}

	/* Response to mouse click */
	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		if (gameOver) // Nothing will happens if the game had already over
			return;
		int column = getColumn(e.getX());
		int row = getRow(e.getY());
		Cell cell = field.getCellAt(row, column); // The cell the mouse clicked
													// is at
		// (x, y)
		if (cell.getVisiblity() == true) // The cell is visible
			return;
		if (e.isMetaDown()) { // If it is clicked by the right button
			if (cell.hasSign())
				cell.removeSign();
			else
				cell.addSign();
		} else { // Clicked by the left button
			if (cell.hasSign())
				return;
			cell.setVisibility();
			if (cell.hasMine()) {
				gameOver = true; // Lost
				lost = true;
			}
			else if (cell.getFlag() == 0) {
				bfsVisible(cell);
			}
		}
		frame.repaint();
		checkWin(); //Check if the player is win
		
	}

	/* Returns the column of the cell the mouse clicked at */
	private int getColumn(int x) {
		// TODO Auto-generated method stub
		int w = view.getWidth() / nColumns;
		return x / w;
	}

	/* Returns the row of the cell the mouse clicked at */
	private int getRow(int y) {
		// TODO Auto-generated method stub
		int h = view.getHeight() / nRows;
		return y / h;
	}

	/*
	 * If the cell the mouse clicked at has no mine in it and its flag equals 0
	 * (no cell around it has a mine) then all the cells with flag equals 0
	 * around it will be visible, and even more, the cells with flags equals 0
	 * around these cells will be visible, too. And the cells with flag equals 0
	 * around them will be visible. This circle continued until all the cells
	 * around these visible cells have flags greater than 0. Finally, these
	 * cells with flags greater than 0 will also be visible but at this time no
	 * more cells(no matter what their flags are)will be visible. The process is
	 * like the Breadth-first search in graph
	 */
	public static void bfsVisible(Cell cell) {
		ArrayList<Cell> collects = new ArrayList<Cell>(); // The cells had been
															// collected
		Queue<Cell> queue = new LinkedList<Cell>();
		if (cell.getFlag() == 0) { // the flag of this cell equals 0
			queue.offer(cell);
			collects.add(cell); // Collects this cell
		} else
			return;
		while (!queue.isEmpty()) {
			Cell c = queue.poll();
			for (Cell cellAround : field.getCellsAround(c)) { // the cells
																// around c
				if (!collects.contains(cellAround)) { // Not collect yet
					cellAround.setVisibility();
					if (cellAround.getFlag() == 0)
						queue.offer(cellAround);
					collects.add(cellAround);
				}
			}
		}
	}

	@Override
	public void mouseEntered(MouseEvent e) {
	}

	@Override
	public void mouseExited(MouseEvent e) {
	}

	@Override
	public void mousePressed(MouseEvent e) {
	}

	@Override
	public void mouseReleased(MouseEvent e) {
	}

	/* Returns true if the player win the game */
	private static boolean checkWin() {
		for (int i = 0; i < nRows; i++)
			for (int j = 0; j < nColumns; j++) {
				Cell cell = field.getCellAt(i, j);
				if (cell.hasMine() && !cell.hasSign())	 // if the cell has a mine
															// but has no flag
					return false;
				if (cell.getFlag() >= 0 && !cell.getVisiblity()) // if the cell has no
																	// mine in it but
																	//  it is not visible
					return false;
			}
		win = gameOver = true;
		return true;
	}

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
	private static boolean gameOver;
	private static boolean win;
	private static boolean lost;
}
