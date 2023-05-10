package window;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

import game.GameBoard;

public class GameWindow extends Window {

	private static final long serialVersionUID = -3793425913656695007L;
	
    // All ICONS (Tile, Mine, Flag, Numbers)
    private ImageIcon minewin = new ImageIcon("minewin.png");
    private ImageIcon minelose = new ImageIcon("minelose.png");
    private ImageIcon flag = new ImageIcon("flagtile.png");
    private ImageIcon tile = new ImageIcon("tile.png");
    private ImageIcon zero = new ImageIcon("zero.png");
    private ImageIcon one = new ImageIcon("one.png");
    private ImageIcon two = new ImageIcon("two.png");
    private ImageIcon three = new ImageIcon("three.png");
    private ImageIcon four = new ImageIcon("four.png");
    private ImageIcon five = new ImageIcon("five.png");
    private ImageIcon six = new ImageIcon("six.png");
    private ImageIcon seven = new ImageIcon("seven.png");
    private ImageIcon eight = new ImageIcon("eight.png");
    
    // Banner label for difficulty modes, and attaches the banner image to the label
    private JLabel lblBannerEasy = new JLabel(new ImageIcon("bannereasy.png"));
    private JLabel lblBannerNormal = new JLabel(new ImageIcon("bannernormal.png"));
    private JLabel lblBannerHard = new JLabel(new ImageIcon("bannerhard.png"));
    
    
	private GameBoard board;
	
	public GameWindow(GameBoard board) {
		this.board = board;
	}
}
