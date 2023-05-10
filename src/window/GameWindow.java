package window;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;

import game.GameBoard;
import game.Tile;

public class GameWindow extends Window {

	private static final long serialVersionUID = -3793425913656695007L;
	private final String winTitle = "Minesweeper";
	private final int tileSize = 30;
	private final int extraWinWidth = tileSize * 2;
	private final int extraWinHeight = tileSize * 10;
	protected static int flagCounter;
	private int winStreak;
	private boolean isStarted;
	
    // All ICONS (Tile, Mine, Flag, Numbers)
    private ImageIcon minewin = new ImageIcon(assetLoc + "minewin.png");
    private ImageIcon minelose = new ImageIcon(assetLoc + "minelose.png");
    protected static ImageIcon flag = new ImageIcon(assetLoc + "flagtile.png");
    protected static ImageIcon tile = new ImageIcon(assetLoc + "tile.png");
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
    protected static JLabel lblFlags = new JLabel();
    protected static JLabel lblFace1 = new JLabel(new ImageIcon(assetLoc + "thinking.png"));
    protected static JLabel lblFace2 = new JLabel(new ImageIcon(assetLoc + "thinking2.png"));
    
    private JButton btnReset = new JButton();
    private JButton btnReturn = new JButton();
    private JButton[][] btnBoard;
    
    private JLabel lblWinner = new JLabel(new ImageIcon(assetLoc + "youwon.png"));
    private JLabel lblLoser = new JLabel(new ImageIcon(assetLoc + "youlose.png"));
    private JLabel lblWinStreak = new JLabel();
    
	private GameBoard board;
	
	public GameWindow(GameBoard board) {
		this.board = board;
		flagCounter = board.getDifficulty().getMines();
		int width = extraWinWidth + 16 + (tileSize * board.getDifficulty().getColumns());
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
				btnBoard[i][j].setLocation(tileSize + (tileSize * j), 185 + (tileSize * i));
				btnBoard[i][j].setSize(tileSize, tileSize);
				btnBoard[i][j].setIcon(tile);
				btnBoard[i][j].addActionListener(e -> viewTile(e));
				btnBoard[i][j].addMouseListener(new TileMouseAdapter(btnBoard[i][j]));
				add(btnBoard[i][j]);
			}
		}
		
		btnReset.setSize(70, 30);
		btnReset.setLocation((int)(getSize().getWidth() / 2) - 43, 223 + board.getDifficulty().getRows() * tileSize);
		btnReset.setText("Reset");
		add(btnReset);
	}
	
	private void addLabels() {
		lblBanner = new JLabel(new ImageIcon(assetLoc + board.getDifficulty().getBanner()));
        lblBanner.setSize(300, 80);
        lblBanner.setLocation((int)(getSize().width / 2) - 161, 20);
        add(lblBanner);
        
        lblFlags.setSize(65, 13);
        lblFlags.setFont(new Font ("Arial", Font.BOLD, 15));
        lblFlags.setForeground(Color.WHITE);
        lblFlags.setLocation(tileSize, 162);
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
        lblWinStreak.setLocation(tileSize, 193 + board.getDifficulty().getRows() * tileSize);
        add(lblWinStreak);
        
        lblAnimatedBG.setSize(1460, 821);
        add(lblAnimatedBG);
	}
	
	private void viewTile(ActionEvent e) {
		int[] index = getSelectedButton(e);
		if (!isStarted) {
			isStarted = true;
			board.initializeBoard(index[0], index[1]);
		}
		
		Tile tile = board.getTiles()[index[0]][index[1]];
		if (tile.isFlagged())
			return;
		else if (tile.isBomb()) {
			gameOver();
		}
		else if (tile.getClue() == 0) {
			revealTiles(index[0], index[1]);
		}
		else {
			btnBoard[index[0]][index[1]].setIcon(one);
		}
		
	}
	
	private void revealTiles(int row, int col) {
		
	}
	
	private void gameOver() {
		
	}
	
	private int[] getSelectedButton(ActionEvent e) {
		int[] index = new int[2];
		for (int i = 0; i < btnBoard.length; i++) {
			for (int j = 0; j < btnBoard[0].length; j++) {
				if (e.getSource() == btnBoard[i][j]) {
					index[0] = i;
					index[1] = j;
					return index;
				}
			}
		}
		
		return null;
	}
}
