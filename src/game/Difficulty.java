package game;

/**
 * Enum serves as the list of difficulties for Minesweeper, containing the rows,
 * columns and mines.
 * 
 * @author Yuki
 *
 */
public enum Difficulty {
	Easy(9, 9, 10), Normal(16, 16, 40), Hard(16, 30, 99);

	private int rows;
	private int columns;
	private int mines;
	private String banner; // difficulty banner that shows at the top of the game

	/**
	 * Constructor for the enum
	 * 
	 * @param row     is the amount of rows in the difficulty
	 * @param columns is the amount of columns in the difficulty
	 * @param bombs   is the amount of bombs in the difficulty
	 */
	Difficulty(int row, int columns, int bombs) {
		this.rows = row;
		this.columns = columns;
		this.mines = bombs;
		banner = "banner" + this.toString().toLowerCase() + ".png";
	}

	/**
	 * @return the rows
	 */
	public int getRows() {
		return rows;
	}

	/**
	 * @param rows the amount of rows to set
	 */
	public void setRows(int rows) {
		this.rows = rows;
	}

	/**
	 * @return the columns
	 */
	public int getColumns() {
		return columns;
	}

	/**
	 * @param columns the amount of columns to set
	 */
	public void setColumns(int columns) {
		this.columns = columns;
	}

	/**
	 * @return the mines
	 */
	public int getMines() {
		return mines;
	}

	/**
	 * @param mines the amount of mines to set
	 */
	public void setMines(int mines) {
		this.mines = mines;
	}

	/**
	 * @return the banner
	 */
	public String getBanner() {
		return banner;
	}

}
