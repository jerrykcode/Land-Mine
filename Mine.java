package mine;

import java.awt.*;

/*
 * Min.java
 * --------
 * This class defines a rectangle indicates a mine 
 */
public class Mine implements MineConstants {

	public Mine(int size, int flag) {
		// TODO Auto-generated constructor stub
		this.size = size;
		this.flag = flag;
		visible = false;
		hasSign = false;
	}

	/** Returns the flag of this rectangle */
	public int getFlag() {
		return flag;
	}

	/** Returns the visibility of the rectangle */
	public boolean getVisiblity() {
		return visible;
	}

	/** Sets the visibility of the rectangle to be true */
	public void setVisibility() {
		visible = true;
	}

	/** Adds a sign on a rectangle with a visibility equals false */
	public void addSign() {
		if (!visible)
			hasSign = true;
	}

	public void draw(Graphics g, int x, int y) {
		if (visible)
			g.setColor(Color.gray);
		else
			g.setColor(Color.green);
		g.fillRect(x, y, size, size);
		if (visible) { // If the rectangle is visible
			if (flag == -1) { // flag == -1 indicates that a land mine insides
								// this rectangle
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
	private int flag; // -1 indicates that a land mine insides this rectangle
						// if flag >= 0 && <= 8, flag is the number of land
						// mines around
						// this rectangle
	private int size; //The size (width & height) of the rectangle
	private boolean visible;// true if the rectangle is visible
	private boolean hasSign;// true if there is a sign on the rectangle
}
