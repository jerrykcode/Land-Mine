package mine;

import javax.swing.JFrame;

public class Test {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Field f = new Field(9, 9);
		for (int i = 0; i < 9; i ++)
			for (int j = 0; j < 9; j ++) {
				f.place(i, j, new Cell(30, 8));
				f.getCellAt(i, j).setVisibility();
			}
		View v = new View(f);
		JFrame fr = new JFrame();
		fr.setSize(400, 400);
		fr.add(v);
		fr.setVisible(true);
	}

}
