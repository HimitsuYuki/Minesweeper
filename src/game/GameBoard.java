package game;

public class GameBoard implements IGame {
	private Difficulty difficulty;
	private Tile[][] tiles;

	public GameBoard(Difficulty difficulty) {
		this.difficulty = difficulty;
	}

	@Override
	public void initializeBoard() {
		tiles = new Tile[difficulty.getRows()][difficulty.getColumns()];
	}

	@Override
	public void placeMines() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void calculateClues(int row, int col) {
		// TODO Auto-generated method stub
		
	}
	
	/**
	 * @return the difficulty
	 */
	public Difficulty getDifficulty() {
		return difficulty;
	}

}
