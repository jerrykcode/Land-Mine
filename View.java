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
		int mineWidth = getWidth()/field.getNColumns(); 
		int mineHeight = getHeight()/field.getNRows();
		for (int i = 0; i < field.getNRows(); i++)
			for (int j = 0; j < field.getNColumns(); j++) {
				Cell mine = field.getMineAt(i, j);
				mine.draw(g, i * mineWidth, j * mineHeight);
			}
	}
	
	/* Private instance variables */
	private Field field;
}
