package game;

import javax.swing.ImageIcon;

import window.Window;

public enum TileIcon {
	Zero("zero"),
	One("one"),
	Two("two"),
	Three("three"),
	Four("four"),
	Five("five"),
	Six("six"),
	Seven("seven"),
	Eight("eight"),
	Flag("flagtile"),
	MineLose("minelose"),
	MineWin("minewin"),
	Default("tile");
	
	private ImageIcon icon;
	TileIcon(String clue) {
		icon = new ImageIcon(Window.assetLoc + clue + ".png");
	}
	
	/**
	 * @return the icon
	 */
	public ImageIcon getIcon() {
		return icon;
	}
	/**
	 * @param icon the icon to set
	 */
	public void setIcon(ImageIcon icon) {
		this.icon = icon;
	}
	
	
}
