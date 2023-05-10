package game;

public enum Difficulty {
	Easy(9, 9, 10),
	Normal(16, 16, 40),
	Hard(16, 30, 99),
	Custom(0, 0, 0);
	
	private int length;
	private int width;
	private int bombs;
	
	Difficulty(int length, int width, int bombs) {
		this.length = length;
		this.width = width;
		this.bombs = bombs;
	}

	/**
	 * @return the length
	 */
	public int getLength() {
		return length;
	}

	/**
	 * @param length the length to set
	 */
	public void setLength(int length) {
		this.length = length;
	}

	/**
	 * @return the width
	 */
	public int getWidth() {
		return width;
	}

	/**
	 * @param width the width to set
	 */
	public void setWidth(int width) {
		this.width = width;
	}

	/**
	 * @return the bombs
	 */
	public int getBombs() {
		return bombs;
	}

	/**
	 * @param bombs the bombs to set
	 */
	public void setBombs(int bombs) {
		this.bombs = bombs;
	}
	
	
}
