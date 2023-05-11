package game;

public interface IMinesweeper {
	public void initializeBoard(int row, int col);
	public void generateMines(int row, int col);
	public void generateClues();
	public void revealTile(int row, int col);
	public void gameOver();
	public Difficulty getDifficulty();
	public ITile[][] getTiles();
	public boolean isGameOver();
	public boolean isWin();
}
