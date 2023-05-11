package game;

public class Tile implements ITile {
	private boolean isRevealed;
	private boolean isMine;
	private boolean isFlagged;
	private int clue; // 0 - no mine in scope, 9 = bomb (win), 10 = bomb (lose), 11 = flag
	private TileIcon curIcon;
	private TileIcon[] icons = { TileIcon.Zero, TileIcon.One, TileIcon.Two, TileIcon.Three, TileIcon.Four,
			TileIcon.Five, TileIcon.Six, TileIcon.Seven, TileIcon.Eight, TileIcon.MineWin, TileIcon.MineLose };

	public Tile() {
		curIcon = TileIcon.Default;
	}

	public Tile(boolean isMine) {
		super();
		this.isMine = isMine;
		clue = 9;
	}

	public Tile(int clue) {
		super();
		this.clue = clue;
	}

	/**
	 * @return the isBomb
	 */
	public boolean isMine() {
		return isMine;
	}

	/**
	 * @param isMine the isMine to set
	 */
	public void setMine(boolean isMine) {
		this.isMine = isMine;
		clue = 9;
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
		if (isRevealed)
			return;

		this.isFlagged = isFlagged;
		if (isFlagged)
			curIcon = TileIcon.Flag;
		else
			curIcon = TileIcon.Default;
	}

	/**
	 * @return the curIcon
	 */
	public TileIcon getCurIcon() {
		return curIcon;
	}

	/**
	 * @return the isRevealed
	 */
	public boolean isRevealed() {
		return isRevealed;
	}

	/**
	 * @param isRevealed the isRevealed to set
	 */
	public void setRevealed(boolean isRevealed) {
		this.isRevealed = isRevealed;

		if (isRevealed)
			curIcon = icons[clue];
		else
			curIcon = TileIcon.Default;
	}
}
