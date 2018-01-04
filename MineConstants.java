package mine;

public interface MineConstants {
	
	/** The number of mines */
	
	public static final int N_MINES = 20;
	
	/** The size (width and height) of the rectangle indicates a mine */
	
	public static final int MINE_SIZE = 24;
	
	/** The number of mine rectangles in one row */
	
	public static final int N_MINE_ROW = 19;
	
	/** The number of mine rectangles in one column */
	
	public static final int N_MINE_COLUMN = 19;
	
	/** The width of the view */
	
	public static final int VIEW_WIDTH = MINE_SIZE*N_MINE_COLUMN;
	
	/** The height of the view */
	
	public static final int VIEW_HEIGHT = MINE_SIZE*N_MINE_ROW;
	
	/** The width of the frame */
	
	public static final int FRAME_WIDTH = VIEW_WIDTH + 80;
	
	/** The height of the frame */
	
	public static final int FRAME_HEIGHT = VIEW_HEIGHT + 100;
	

}
