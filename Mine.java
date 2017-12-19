package mine;
import java.awt.*;
/*
 * Min.java
 * --------
 * This class defines a rectangle indicates a mine 
 */
public class Mine {

	public Mine(int flag) {
		// TODO Auto-generated constructor stub
		this.flag = flag;
		visible = false;
	}
	
	public void draw(Graphics g, int x, int y){
		if (visible){ //If the rectangle is visible
			if (flag == -1){ //flag == -1 indicates that a land mine insides this rectangle
				
			}
		}
	}

	/* Private instance variables */
	private int flag; //-1 indicates that a land mine insides this rectangle
						//if flag >= 0 && <= 8, flag is the number of land mines around
						//this rectangle
	private boolean visible;
}
