package mine;

import java.util.ArrayList;

/*
 * Field.java
 * ----------
 * This class defines a field containing nRows*nColumns mines(rectangles, defined in
 * Mine.java).
 */
public class Field {

	public Field(int nRows, int nColumns) {
		// TODO Auto-generated constructor stub
		this.nRows = nRows;
		this.nColumns = nColumns;
		cells = new Cell[nRows][nColumns];
	}

	/** Returns the number of rows of the field */
	public int getNRows(){
		return nRows;
	}
	
	/** Returns the number of columns of the field */
	public int getNColumns(){
		return nColumns;
	}
	
	/** Places a cell at the position (r, c) in the field */
	public void place(int row, int column, Cell mine) {
		cells[row][column] = mine;
	}

	/** Returns the cell at position(r, c) in the field */
	public Cell getCellAt(int row, int column) {
		return cells[row][column];
	}

	/**
	 * Returns the cells around a specific cell at position(r, c) in the field
	 */
	public Cell[] getCellsAround(int row, int column) {
		ArrayList<Cell> cellsAround = new ArrayList<Cell>();
		for (int i = -1; i < 2; i++) {
			if (row + i < 0 || row + i > nColumns - 1)
				continue;
			for (int j = -1; j < 2; j++) {
				if (i == 0 && j == 0) // (x+i, y+j) is (x, y) itself
					continue;
				if (column + j < 0 || column + j > nRows - 1)
					continue;
				cellsAround.add(cells[row + i][column + j]);
			}
		}
		return (Cell[]) cellsAround.toArray(new Cell[0]);
	}
	
	/** Returns the cells around a specific cell passed in as parameter */
	public Cell[] getCellsAround(Cell cell) {
		return getCellsAround(cell.getRow(), cell.getColumn());
	}

	/* Private instance variables */
	int nRows; // The number of rows in the field
	int nColumns; // The number of columns in the field
	Cell cells[][]; // The cells in the field
}
