package game;

public interface IGame {
	public void initializeBoard();
	public void placeMines();
	public void calculateClues(int row, int col);
	
}
