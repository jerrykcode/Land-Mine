package mine;

import java.awt.*;

/*
 * Min.java
 * --------
 * This class defines a cell with a flag indicates that a mine exists in the cell
 * if the flag equals -1 or means the number of mines around this cell
 */
public class Cell {

	public Cell(int row, int column, int size, int flag) {
		// TODO Auto-generated constructor stub
		this.row = row;
		this.column = column;
		this.size = size;
		this.flag = flag;
		visible = false;
		hasSign = false;
	}
	
	/** Returns the row of the cell */
	public int getRow() {
		return row;
	}

	/** Returns the column of the cell */
	public int getColumn() {
		return column;
	}
	
	/** Returns the size of the cell */
	public int getSize() {
		return size;
	}
	
	/** Returns the flag of this cell */
	public int getFlag() {
		return flag;
	}
	
	/** Sets the flag of the cell */
	public void setFlag(int flag) {
		this.flag = flag;
	}

	/** Returns true if there is a mine in the cell */
	public boolean hasMine() {
		return flag == -1;
	}
	
	/** Sets the cell to the type which there is a mine in it */
	public void setToHasMine() {
		setFlag(-1);
	}
	
	/** Returns the visibility of the cell */
	public boolean getVisiblity() {
		return visible;
	}

	/** Sets the visibility of the cell to be true */
	public void setVisibility() {
		visible = true;
	}

	/** Adds a sign on a cell */
	public void addSign() {
		hasSign = true;
	}

	/** Returns true if there is a sign on the cell */
	public boolean hasSign() {
		return hasSign;
	}
	
	/** Removes the sign */
	public void removeSign() {
		hasSign = false;
	}
	
	public void draw(Graphics g, int x, int y) {
		if (visible)
			g.setColor(Color.gray);
		else
			g.setColor(Color.green);
		g.fillRect(x, y, size, size); // Fill the rectangle indicates the cell
		g.setColor(Color.white);
		Graphics2D g2 = (Graphics2D)g;
		g2.setStroke(new BasicStroke(3.0f));
		g2.drawRect(x, y, size, size); //Draw the outline
		if (visible) { // If the cell is visible
			if (flag == -1) { // flag == -1 indicates that a land mine insides
								// this cell
				g.setColor(Color.red);
				g.fillOval(x + size / 6, y + size / 6, size * 2 / 3, size * 2 / 3);
			} else if (flag != 0) {
				g.setColor(Color.cyan);
				g.drawString(" " + flag, x + size / 3, y + size * 2 / 3);
			}
		} else if (hasSign) {// Not visible and there is a sign on it
			// Draw the sign
			g.setColor(Color.red);
			int xPoints[] = { x + size / 3, x + size / 3, x + size * 2 / 3 };
			int yPoints[] = { y + size / 3, y + size * 2 / 3, y + size / 2 };
			int nPoints = xPoints.length;
			g.fillPolygon(xPoints, yPoints, nPoints);
		}
	}

	/* Private instance variables */
	private int row;
	private int column;
	private int flag; // -1 indicates that a land mine insides this cell
						// if flag >= 0 && <= 8, flag is the number of land
						// mines around
						// this cell
	private int size; //The size (width & height) of the cell
	private boolean visible;// true if the cell is visible
	private boolean hasSign;// true if there is a sign on the cell
}
