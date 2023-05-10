package game;

public interface IGame {
	public void initializeBoard(int row, int col);
	public void generateMines(int row, int col);
	public void generateClues();
	
}
