package game;

/**
 * Interface for the Tiles of the board for Minesweeper that it must have
 * 
 * @author Yuki
 *
 */
public interface ITile {
	/**
	 * Checks if the tile is a mine or not
	 * 
	 * @return true if the tile is a mine, false if not
	 */
	public boolean isMine();

	/**
	 * Sets the tile to be a mine
	 * 
	 * @param isMine whether the tile is a mine or not
	 */
	public void setMine(boolean isMine);

	/**
	 * Gets the clue (adjacent mines) of the tile.
	 * 
	 * @return the amount of adjacent mines of the tile
	 */
	public int getClue();

	/**
	 * Sets the amount of clues (adjacent mines) of the tile.
	 * 
	 * @param clue the amount of adjacent mines of the tile.
	 */
	public void setClue(int clue);

	/**
	 * Checks if the tile was flagged by the user.
	 * 
	 * @return true if the tile was flagged, false if not
	 */
	public boolean isFlagged();

	/**
	 * Sets the tile to be flagged by the user. Does nothing if the tile was already
	 * revealed. Changes the current icon of the tile as well to default or a flag.
	 * 
	 * @param isFlagged true if the tile was flagged by the user, false if not
	 */
	public void setFlagged(boolean isFlagged);

	/**
	 * Checks if the tile has already been clicked by the user (revealed).
	 * 
	 * @return true if the tile was revealed, false if not
	 */
	public boolean isRevealed();

	/**
	 * Sets the tile to be revealed to the user. Changes the current icon of the
	 * tile if it is revealed.
	 * 
	 * @param isRevealed true if the tile will be revealed, false if not
	 */
	public void setRevealed(boolean isRevealed);

	/**
	 * Gets the current icon of the tile for the GUI.
	 * @return the current icon of the tile
	 */
	public TileIcon getCurIcon();
}
