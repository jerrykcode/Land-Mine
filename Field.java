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
	
	/** Places a cell at the position (x, y) in the field */
	public void place(int x, int y, Cell mine) {
		cells[x][y] = mine;
	}

	/** Returns the cell at position(x, y) in the field */
	public Cell getCellAt(int x, int y) {
		return cells[x][y];
	}

	/**
	 * Returns the cells around a specific cell at position(x, y) in the field
	 */
	public Cell[] getMinesAround(int x, int y) {
		ArrayList<Cell> cellsAround = new ArrayList<Cell>();
		for (int i = -1; i < 2; i++) {
			if (x + i < 0 || x + i > nColumns - 1)
				continue;
			for (int j = -1; j < 2; j++) {
				if (i == 0 && j == 0) // (x+i, y+j) is (x, y) itself
					continue;
				if (y + j < 0 || y + j > nRows - 1)
					continue;
				cellsAround.add(cells[x + i][y + j]);
			}
		}
		return (Cell[]) cellsAround.toArray(new Cell[0]);
	}

	/* Private instance variables */
	int nRows; // The number of rows in the field
	int nColumns; // The number of columns in the field
	Cell cells[][]; // The cells in the field
}
