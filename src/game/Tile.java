package game;

public class Tile {
	private boolean isBomb;
	private int clue;
	
	public Tile(boolean isBomb, int clue) {
		this.isBomb = isBomb;
		this.clue = clue;
	}

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
}
