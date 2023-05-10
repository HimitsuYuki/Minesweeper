package game;

public class Tile {
	private boolean isBomb;
	private boolean isFlagged;
	private int clue; // 0 - no bomb in scope unless isBomb is true
	
	public Tile(boolean isBomb) {
		this.isBomb = isBomb;
	}
	
	public Tile(int clue) {
		this.clue = clue;
	}

	public Tile() {}

	/**
	 * @return the isBomb
	 */
	public boolean isBomb() {
		return isBomb;
	}
	
	/**
	 * @param isBomb the isBomb to set
	 */
	public void setBomb(boolean isBomb) {
		this.isBomb = isBomb;
	}

	/**
	 * @return the clue
	 */
	public int getClue() {
		return clue;
	}
	
	/**
	 * @param clue the clue to set
	 */
	public void setClue(int clue) {
		this.clue = clue;
	}

	/**
	 * @return the isFlagged
	 */
	public boolean isFlagged() {
		return isFlagged;
	}

	/**
	 * @param isFlagged the isFlagged to set
	 */
	public void setFlagged(boolean isFlagged) {
		this.isFlagged = isFlagged;
	}
}
