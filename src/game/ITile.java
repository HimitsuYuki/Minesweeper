package game;


public interface ITile {
	public boolean isMine();
	public void setMine(boolean isMine);
	public int getClue();
	public void setClue(int clue);
	public boolean isFlagged();
	public void setFlagged(boolean isFlagged);
	public boolean isRevealed();
	public void setRevealed(boolean isRevealed);
	public TileIcon getCurIcon();
}
