package game;

/**
 * The tiles on the Minesweeper game's board
 * 
 * @author Yuki
 *
 */
public class Tile implements ITile {
	private boolean isRevealed;
	private boolean isMine;
	private boolean isFlagged;
	private int clue; // 0 - no mine in scope, 9 = bomb (win), 10 = bomb (lose), 11 = flag
	private TileIcon curIcon;
	private TileIcon[] icons = { TileIcon.Zero, TileIcon.One, TileIcon.Two, TileIcon.Three, TileIcon.Four,
			TileIcon.Five, TileIcon.Six, TileIcon.Seven, TileIcon.Eight, TileIcon.MineWin, TileIcon.MineLose };

	/**
	 * Constructor for setting the tiles to their default state.
	 */
	public Tile() {
		curIcon = TileIcon.Default;
	}

	/**
	 * Constructor that allows to instantiate a tile to be a mine or not. Sets the
	 * clue to 9 as it is a bomb.
	 * 
	 * @param isMine true if the tile is a mine, false if not
	 */
	public Tile(boolean isMine) {
		super();
		this.isMine = isMine;
		clue = 9;
	}

	/**
	 * Constructor that allows to instantiate a tile to include a clue or not.
	 * 
	 * @param clue the amount of adjacent bombs to that tile
	 */
	public Tile(int clue) {
		super();
		this.clue = clue;
	}

	public boolean isMine() {
		return isMine;
	}

	public void setMine(boolean isMine) {
		this.isMine = isMine;
		clue = 9;
	}

	public int getClue() {
		return clue;
	}

	public void setClue(int clue) {
		this.clue = clue;
	}

	public boolean isFlagged() {
		return isFlagged;
	}

	public void setFlagged(boolean isFlagged) {
		if (isRevealed)
			return;

		this.isFlagged = isFlagged;
		if (isFlagged)
			curIcon = TileIcon.Flag;
		else
			curIcon = TileIcon.Default;
	}

	public TileIcon getCurIcon() {
		return curIcon;
	}

	public boolean isRevealed() {
		return isRevealed;
	}

	public void setRevealed(boolean isRevealed) {
		this.isRevealed = isRevealed;

		if (isRevealed)
			curIcon = icons[clue];
		else
			curIcon = TileIcon.Default;
	}
}
