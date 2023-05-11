package game;

/**
 * The logic for the Minesweeper game
 * 
 * @author Yuki
 *
 */
public class Minesweeper implements IMinesweeper {
	private Difficulty difficulty;
	private ITile[][] tiles;
	private ITile[] mines;
	private boolean gameOver;
	private boolean isWin;

	/**
	 * Constructor for creating the game. Instantiates the mines and tiles to a
	 * default state.
	 * 
	 * @param difficulty is the difficulty that will be used to set up the game.
	 */
	public Minesweeper(Difficulty difficulty) {
		this.difficulty = difficulty;
		mines = new Tile[difficulty.getMines()];
		tiles = new Tile[difficulty.getRows()][difficulty.getColumns()];
		for (int i = 0; i < tiles.length; i++) {
			for (int j = 0; j < tiles[0].length; j++) {
				tiles[i][j] = new Tile();
			}
		}
	}

	@Override
	public void initializeBoard(int row, int col) {
		generateMines(row, col);
		generateClues();
	}

	@Override
	public void generateMines(int row, int col) {
		int placedMines = 0;
		do {
			int randomRow = (int) (Math.random() * difficulty.getRows());
			int randomCol = (int) (Math.random() * difficulty.getColumns());
			if (randomRow != row && randomCol != col && !tiles[randomRow][randomCol].isMine()) {
				mines[placedMines] = tiles[randomRow][randomCol];
				tiles[randomRow][randomCol].setMine(true);
				placedMines++;
			}
		} while (placedMines != difficulty.getMines());
	}

	@Override
	public void generateClues() {
		for (int i = 0; i < difficulty.getRows(); i++) {
			for (int j = 0; j < difficulty.getColumns(); j++) {
				if (tiles[i][j].isMine())
					continue;

				tiles[i][j].setClue(countMines(i, j));
			}
		}
	}

	/**
	 * Counts the mines adjacent to a tile.
	 * 
	 * @param row is the row of the tile that will have its adjacent mines counted
	 * @param col is the column of the tile that will have its adjacent mines
	 *            counted
	 * 
	 * @return the amount of mines adjacent to the tile
	 */
	private int countMines(int row, int col) {
		int clue = 0;
		for (int i = row - 1; i <= row + 1; i++) {
			for (int j = col - 1; j <= col + 1; j++) {
				if (isValidPosition(i, j) && tiles[i][j].isMine()) {
					clue++;
				}
			}
		}
		return clue;
	}

	/**
	 * Used to check if the tile position exists on the board
	 * 
	 * @param row is the row of the tile
	 * @param col is the column of the tile
	 * @return if the tile position exists or not
	 */
	private boolean isValidPosition(int row, int col) {
		return row >= 0 && row < difficulty.getRows() && col >= 0 && col < difficulty.getColumns();
	}

	public void revealTile(int row, int col) {
		if (tiles[row][col].isFlagged())
			return;

		if (tiles[row][col].isMine()) {
			gameOver();
			return;
		}

		recursiveTileReveal(row, col);

		if (isGameWon())
			gameOver();
	}

	/**
	 * Recursively checks all the tiles around the tile that was clicked if it has
	 * no mine adjacent. It should return if; the tile is already revealed, the tile
	 * is already flagged or if the tile has a mine adjacent.
	 * 
	 * @param row is the row of the tile to reveal
	 * @param col is the column of the tile to reveal
	 */
	private void recursiveTileReveal(int row, int col) {
		if (!isValidPosition(row, col))
			return;

		ITile tile = tiles[row][col];
		if (tile.isRevealed() || tile.isFlagged())
			return;

		tile.setRevealed(true);

		if (tile.getClue() != 0) // we want it to stop when it reaches a clue with a number
			return;

		// recursive traverse if it is a tile with 0 clues
		recursiveTileReveal(row - 1, col - 1); // top left
		recursiveTileReveal(row - 1, col); // top middle
		recursiveTileReveal(row - 1, col + 1); // top right
		recursiveTileReveal(row, col - 1); // left
		recursiveTileReveal(row, col + 1); // right
		recursiveTileReveal(row + 1, col - 1); // bottom left
		recursiveTileReveal(row + 1, col); // bottom middle
		recursiveTileReveal(row + 1, col + 1); // bottom right
	}

	/**
	 * Checks if the user has won the game.
	 * 
	 * @return true if the user won the game, false if not
	 */
	private boolean isGameWon() {
		for (int i = 0; i < difficulty.getRows(); i++) {
			for (int j = 0; j < difficulty.getColumns(); j++) {
				if (!tiles[i][j].isRevealed() && !tiles[i][j].isMine())
					return false;
			}
		}

		isWin = true;
		return true;
	}

	public void gameOver() {
		gameOver = true;

		if (!isWin) {
			for (int i = 0; i < difficulty.getRows(); i++) {
				for (int j = 0; j < difficulty.getColumns(); j++) {
					if (tiles[i][j].isMine())
						tiles[i][j].setClue(10);

					tiles[i][j].setRevealed(true);
				}
			}
		} else {
			for (int i = 0; i < mines.length; i++) {
				if (mines[i].isFlagged())
					continue;
				mines[i].setRevealed(true);
			}
		}
	}

	public Difficulty getDifficulty() {
		return difficulty;
	}

	public ITile[][] getTiles() {
		return tiles;
	}

	public boolean isGameOver() {
		return gameOver;
	}

	public boolean isWin() {
		return isWin;
	}

}
