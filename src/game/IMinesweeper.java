package game;

/**
 * Interface for the methods the Minesweeper game must have
 * 
 * @author Yuki
 *
 */
public interface IMinesweeper {
	/**
	 * Initializes the board with respect to where the user first clicked, so they
	 * cannot lose on the first click.
	 * 
	 * @param row is the row of the JButton clicked
	 * @param col is the column of the JButton clicked
	 */
	public void initializeBoard(int row, int col);

	/**
	 * The mines that will randomly generate on the tiles with respect to where the
	 * user first clicked, so they cannot lose on the first click.
	 * 
	 * @param row is the row of the JButton clicked
	 * @param col is the column of the JButton clicked
	 */
	public void generateMines(int row, int col);

	/**
	 * The clues (numbers) on the tiles containing the amount of bombs adjacent to
	 * that tile.
	 */
	public void generateClues();

	/**
	 * Reveals the tile that the user clicked. Does not do anything if the tile was
	 * flagged or already revealed. If the user clicks a tile that has no bombs
	 * nearby, it should recursively reveal the tiles around it until it reaches a
	 * tile with a clue. If the user clicks a tile with a bomb, then it should game
	 * over.
	 * 
	 * @param row is the row of the JButton clicked
	 * @param col is the column of the JButton clicked
	 */
	public void revealTile(int row, int col);

	/**
	 * Handles the game over logic of Minesweeper
	 */
	public void gameOver();

	/**
	 * Gets the difficulty of the current Minesweeper game.
	 * 
	 * @return the difficulty of the current Minesweeper game
	 */
	public Difficulty getDifficulty();

	/**
	 * Gets all the tiles of the current Minesweeper game.
	 * 
	 * @return the tiles of the current Minesweeper game
	 */
	public ITile[][] getTiles();

	/**
	 * Checks if the game has finished.
	 * 
	 * @return the state of the game if it is over
	 */
	public boolean isGameOver();

	/**
	 * Checks if the user has won.
	 * 
	 * @return the current Minesweeper game being won or not
	 */
	public boolean isWin();
}
