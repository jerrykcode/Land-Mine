package mine;

import javax.swing.JFrame;

public class Test {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Field f = new Field(10, 10);
		for (int i = 0; i < 10; i ++)
			for (int j = 0; j < 10; j ++) {
				f.place(i, j, new Mine(30, 0));
				f.getMineAt(i, j).setVisibility();
			}
		View v = new View(f);
		JFrame fr = new JFrame();
		fr.add(v);
		fr.setVisible(true);
	}

}
