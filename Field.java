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
		mines = new Mine[nRows][nColumns];
	}

	/** Returns the number of rows of the field */
	public int getNRows(){
		return nRows;
	}
	
	/** Returns the number of columns of the field */
	public int getNColumns(){
		return nColumns;
	}
	
	/** Places a mine at the position (x, y) in the field */
	public void place(int x, int y, Mine mine) {
		mines[x][y] = mine;
	}

	/** Returns the mine at position(x, y) in the field */
	public Mine getMineAt(int x, int y) {
		return mines[x][y];
	}

	/**
	 * Returns the mines around a specific mine at position(x, y) in the field
	 */
	public Mine[] getMinesAround(int x, int y) {
		ArrayList<Mine> minesAround = new ArrayList<Mine>();
		for (int i = -1; i < 2; i++) {
			if (x + i < 0 || x + i > nColumns - 1)
				continue;
			for (int j = -1; j < 2; j++) {
				if (i == 0 && j == 0) // (x+i, y+j) is (x, y) itself
					continue;
				if (y + j < 0 || y + j > nRows - 1)
					continue;
				minesAround.add(mines[x + i][y + j]);
			}
		}
		return (Mine[]) minesAround.toArray();
	}

	/* Private instance variables */
	int nRows; // The number of rows in the field
	int nColumns; // The number of columns in the field
	Mine mines[][]; // The mines in the field
}
