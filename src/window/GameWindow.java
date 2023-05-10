package window;

import java.awt.Color;
import java.awt.Font;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;

import game.GameBoard;
import game.Tile;

public class GameWindow extends Window {

	private static final long serialVersionUID = -3793425913656695007L;
	private final String winTitle = "Minesweeper";
	private final int tileSize = 30;
	private final int extraWinWidth = tileSize * 4;
	private final int extraWinHeight = tileSize * 10;
	private int flagCounter;
	private int winStreak;
	
    // All ICONS (Tile, Mine, Flag, Numbers)
    private ImageIcon minewin = new ImageIcon(assetLoc + "minewin.png");
    private ImageIcon minelose = new ImageIcon(assetLoc + "minelose.png");
    private ImageIcon flag = new ImageIcon(assetLoc + "flagtile.png");
    private ImageIcon tile = new ImageIcon(assetLoc + "tile.png");
    private ImageIcon zero = new ImageIcon(assetLoc + "zero.png");
    private ImageIcon one = new ImageIcon(assetLoc + "one.png");
    private ImageIcon two = new ImageIcon(assetLoc + "two.png");
    private ImageIcon three = new ImageIcon(assetLoc + "three.png");
    private ImageIcon four = new ImageIcon(assetLoc + "four.png");
    private ImageIcon five = new ImageIcon(assetLoc + "five.png");
    private ImageIcon six = new ImageIcon(assetLoc + "six.png");
    private ImageIcon seven = new ImageIcon(assetLoc + "seven.png");
    private ImageIcon eight = new ImageIcon(assetLoc + "eight.png");
    
    // Banner label for difficulty modes, and attaches the banner image to the label
    private JLabel lblBanner;
    private JLabel lblFlags = new JLabel();
    private JLabel lblFace1 = new JLabel(new ImageIcon(assetLoc + "thinking.png"));
    private JLabel lblFace2 = new JLabel(new ImageIcon(assetLoc + "thinking2.png"));
    
    private JButton btnReset = new JButton();
    private JButton btnReturn = new JButton();
    private JButton[][] btnBoard;
    
    JLabel lblWinner = new JLabel(new ImageIcon(assetLoc + "youwon.png"));
    JLabel lblLoser = new JLabel(new ImageIcon(assetLoc + "youlose.png"));
    JLabel lblWinStreak = new JLabel();
    
	private GameBoard board;
	
	public GameWindow(GameBoard board) {
		this.board = board;
		flagCounter = board.getDifficulty().getMines();
		int width = extraWinWidth + (tileSize * board.getDifficulty().getColumns());
		int height = extraWinHeight + (tileSize * board.getDifficulty().getRows());
		
    	setWindowSettings(winTitle, width, height);
    	setupGUI();
    	setVisible(true);
	}
	
	private void setupGUI() {
    	addButtons();
    	addLabels();
	}
	
	private void addButtons() {
		btnBoard = new JButton[board.getDifficulty().getRows()][board.getDifficulty().getColumns()];
		for (int i = 0; i < btnBoard.length; i++) {
			for (int j = 0; j < btnBoard[0].length; j++) {
				btnBoard[i][j] = new JButton();
				btnBoard[i][j].setLocation(tileSize * 2 + (tileSize * i), 185 + (tileSize * j));
				btnBoard[i][j].setSize(tileSize, tileSize);
				add(btnBoard[i][j]);
			}
		}
	}
	
	private void addLabels() {
		lblBanner = new JLabel(new ImageIcon(assetLoc + board.getDifficulty().getBanner()));
        lblBanner.setSize(300, 80);
        lblBanner.setLocation((int)(getSize().width / 2) - 161, 20);
        add(lblBanner);
        
        lblFlags.setSize(65, 15);
        lblFlags.setFont(new Font ("Arial", Font.BOLD, 15));
        lblFlags.setForeground(Color.WHITE);
        lblFlags.setLocation(tileSize * 2, 162);
        lblFlags.setText("Flags: " + flagCounter);
        add(lblFlags);
        
        lblFace1.setSize(60, 68);
        lblFace1.setLocation((int)(getSize().width / 2) - 40, 110);
        add(lblFace1);
        
        lblFace2.setSize(60, 68);
        lblFace2.setLocation((int)(getSize().width / 2) - 40, 110);
        lblFace2.setVisible(false);
        add(lblFace2);
        
        lblWinStreak.setSize(200, 14);
        lblWinStreak.setFont(new Font ("Arial", Font.BOLD, 14));
        lblWinStreak.setForeground(Color.WHITE);
        lblWinStreak.setText("Win Streak: " + winStreak);
        lblWinStreak.setLocation(tileSize * 2 + (tileSize * board.getDifficulty().getColumns()) - 80, 162);
        add(lblWinStreak);
        
        lblAnimatedBG.setSize(1460, 821);
        add(lblAnimatedBG);
	}
}
