package game;

import javax.swing.ImageIcon;

import window.Window;

/**
 * Enum serves as the icons for the tiles.
 * 
 * @author Yuki
 *
 */
public enum TileIcon {
	Zero("zero"), One("one"), Two("two"), Three("three"), Four("four"), Five("five"), Six("six"), Seven("seven"),
	Eight("eight"), Flag("flagtile"), MineLose("minelose"), MineWin("minewin"), Default("tile");

	private ImageIcon icon;

	/**
	 * Constructor for the icon of the tiles. Sets the image icon to the path of the
	 * icon.
	 * 
	 * @param clue is the file name of the icon
	 */
	TileIcon(String clue) {
		icon = new ImageIcon(Window.assetLoc + clue + ".png");
	}

	/**
	 * Gets the icon of the tile.
	 * 
	 * @return the icon of the tile
	 */
	public ImageIcon getIcon() {
		return icon;
	}

	/**
	 * Sets the icon of the tile.
	 * 
	 * @param icon the icon to set
	 */
	public void setIcon(ImageIcon icon) {
		this.icon = icon;
	}

}
