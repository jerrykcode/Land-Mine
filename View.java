package mine;

import java.awt.Graphics;

import javax.swing.JPanel;

public class View extends JPanel implements MineConstants {

	public View(Field field) {
		// TODO Auto-generated constructor stub
		this.field = field;
	}

	@Override
	public void paint(Graphics g) {
		// TODO Auto-generated method stub
		for (int i = 0; i < field.getNRows(); i++)
			for (int j = 0; j < field.getNColumns(); j++) {
				Mine mine = field.getMineAt(i, j);
				mine.draw(g, i * MINE_SIZE, j * MINE_SIZE);
			}
	}
	
	/* Private instance variables */
	private Field field;
}
