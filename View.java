package mine;

import java.awt.Graphics;

import javax.swing.JPanel;

public class View extends JPanel{

	public View(Field field) {
		// TODO Auto-generated constructor stub
		this.field = field;
	}

	@Override
	public void paint(Graphics g) {
		// TODO Auto-generated method stub
		int w = this.getWidth()/field.nColumns;
		int h = this.getHeight()/field.nRows;
		for (int i = 0; i < field.nRows; i ++) {
			int y = i*h + (h - field.getCellAt(0, 0).getSize())/2;
			for (int j = 0; j < field.nColumns; j ++) {
				int x = j*w + (w - field.getCellAt(0, 0).getSize())/2;
				field.getCellAt(i, j).draw(g, x, y);
			}
		}
	}
	
	/* Private instance variables */
	private Field field;
}
