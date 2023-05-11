package window;

import java.awt.Color;
import java.awt.Font;
import java.awt.Point;
import java.awt.event.ActionEvent;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;

import game.IMinesweeper;
import game.Minesweeper;
import game.TileIcon;

/**
 * The game window containing the current Minesweeper game and its selected
 * difficulty.
 * 
 * @author Yuki
 *
 */
public class GameWindow extends Window {
	private final String winTitle = "Minesweeper";
	private final int tileSize = 30;
	private final int extraWinWidth = tileSize * 2;
	private final int extraWinHeight = tileSize * 10;
	protected static int flagCounter;
	private int winStreak;
	private boolean isStarted;
	private boolean isResults;
	private ImageIcon imgWinner = new ImageIcon(assetLoc + "youwon.png");
	private ImageIcon imgLoser = new ImageIcon(assetLoc + "youlose.png");
	private static ImageIcon imgFace1 = new ImageIcon(assetLoc + "thinking.png");
	private static ImageIcon imgFace2 = new ImageIcon(assetLoc + "thinking2.png");
	private JLabel lblBanner;
	protected static JLabel lblFlags = new JLabel();
	protected static JLabel lblFace1 = new JLabel(imgFace1);
	protected static JLabel lblFace2 = new JLabel(imgFace2);
	private JButton btnReset = new JButton("Reset");
	private JButton btnReturn = new JButton("Select Difficulty");
	private JButton[][] btnBoard;
	private JLabel lblWinStreak = new JLabel();
	protected static IMinesweeper board;
	protected static final String strWinStreak = "Win Streak: ";
	protected static final String strFlags = "Flags: ";
	private Point resetLoc;

	/**
	 * Constructor to setup the game window. Sets up the window settings and its
	 * GUI.
	 * 
	 * @param board is the Minesweeper game that will be shown on the window
	 */
	public GameWindow(IMinesweeper board) {
		GameWindow.board = board;
		lblFace1.setIcon(imgFace1);
		flagCounter = board.getDifficulty().getMines();
		int width = extraWinWidth + 16 + (tileSize * board.getDifficulty().getColumns());
		int height = extraWinHeight + (tileSize * board.getDifficulty().getRows());

		setWindowSettings(winTitle, width, height);
		setupGUI();
		frame.setVisible(true);
	}

	/**
	 * Calls all functions to setup the GUI.
	 */
	private void setupGUI() {
		addButtons();
		addLabels();
	}

	/**
	 * Sets the button settings and adds it to the frame.
	 */
	private void addButtons() {
		btnBoard = new JButton[board.getDifficulty().getRows()][board.getDifficulty().getColumns()];
		for (int i = 0; i < btnBoard.length; i++) {
			for (int j = 0; j < btnBoard[0].length; j++) {
				btnBoard[i][j] = new JButton();
				btnBoard[i][j].setLocation(tileSize + (tileSize * j), 185 + (tileSize * i));
				btnBoard[i][j].setSize(tileSize, tileSize);
				btnBoard[i][j].setIcon(TileIcon.Default.getIcon());
				btnBoard[i][j].addActionListener(e -> selectTile(e));
				btnBoard[i][j].addMouseListener(new TileMouseAdapter(btnBoard[i][j], i, j));
				frame.add(btnBoard[i][j]);
			}
		}

		btnReset.setSize(70, 30);
		btnReset.setLocation((int) (frame.getSize().getWidth() / 2) - 43,
				223 + board.getDifficulty().getRows() * tileSize);
		resetLoc = btnReset.getLocation();
		btnReset.addActionListener(e -> resetGame(e));
		frame.add(btnReset);

		btnReturn.setSize(125, 30);
		btnReturn.addActionListener(e -> returnDiffSelect(e));
		btnReturn.setVisible(false);
		frame.add(btnReturn); // Adds Return Button
	}

	/**
	 * Closes the current game and brings the user back to the difficulty select
	 * window.
	 * 
	 * @param e is the JButton responsible for calling this function
	 */
	private void returnDiffSelect(ActionEvent e) {
		frame.dispose();
		new DifficultySelectWindow();
	}

	/**
	 * Sets the label settings and adds it to the frame.
	 */
	private void addLabels() {
		lblBanner = new JLabel(new ImageIcon(assetLoc + board.getDifficulty().getBanner()));
		lblBanner.setSize(300, 80);
		lblBanner.setLocation((int) (frame.getSize().width / 2) - 161, 20);
		frame.add(lblBanner);

		lblFlags.setSize(65, 13);
		lblFlags.setFont(new Font("Arial", Font.BOLD, 15));
		lblFlags.setForeground(Color.WHITE);
		lblFlags.setLocation(tileSize, 162);
		lblFlags.setText(strFlags + flagCounter);
		frame.add(lblFlags);

		lblFace1.setSize(60, 68);
		lblFace1.setLocation((int) (frame.getSize().width / 2) - 40, 110);
		frame.add(lblFace1);

		lblFace2.setSize(60, 68);
		lblFace2.setLocation((int) (frame.getSize().width / 2) - 40, 110);
		lblFace2.setVisible(false);
		frame.add(lblFace2);

		lblWinStreak.setSize(200, 14);
		lblWinStreak.setFont(new Font("Arial", Font.BOLD, 14));
		lblWinStreak.setForeground(Color.WHITE);
		lblWinStreak.setText(strWinStreak + winStreak);
		lblWinStreak.setLocation(tileSize, 193 + board.getDifficulty().getRows() * tileSize);
		frame.add(lblWinStreak);

		lblAnimatedBG.setSize(1460, 821);
		frame.add(lblAnimatedBG);
	}

	/**
	 * The action of the tile that was clicked on the Minesweeper board. Does
	 * nothing if the results screen was shown. Initializes the board if the user
	 * did their first click, reveals the tile(s) on the board and updates the
	 * window.
	 * 
	 * @param e is the JButton responsible for calling this function
	 */
	private void selectTile(ActionEvent e) {
		if (isResults)
			return;

		int[] buttonIndex = getSelectedButton(e);
		if (!isStarted) {
			isStarted = true;
			board.initializeBoard(buttonIndex[0], buttonIndex[1]);
		}

		board.revealTile(buttonIndex[0], buttonIndex[1]);
		update();
	}

	/**
	 * Updates the GUI elements on the window and checks if the game has ended.
	 */
	private void update() {
		updateBoard();
		isGameEnd();
	}

	/**
	 * Updates the Minesweeper board GUI buttons.
	 */
	private void updateBoard() {
		for (int i = 0; i < btnBoard.length; i++) {
			for (int j = 0; j < btnBoard[0].length; j++) {
				btnBoard[i][j].setIcon(board.getTiles()[i][j].getCurIcon().getIcon());
			}
		}
	}

	/**
	 * Handles if the game has ended. Does nothing if the game wasn't over and if
	 * the result screen wasn't already showing. Sets the emoji icon at the top of
	 * the window, the win streak and shows the select difficulty button.
	 */
	private void isGameEnd() {
		if (!board.isGameOver() && !isResults)
			return;

		lblFace1.setVisible(false);
		if (board.isWin()) {
			lblFace1.setIcon(imgWinner);
			winStreak++;
		} else {
			lblFace1.setIcon(imgLoser);
			winStreak = 0;
		}

		btnReset.setLocation(btnReset.getLocation().x - 70, btnReset.getLocation().y);
		btnReturn.setVisible(true);
		btnReturn.setLocation(btnReset.getLocation().x + 80, btnReset.getLocation().y);
		lblWinStreak.setText(strWinStreak + winStreak);
		isResults = true;
	}

	/**
	 * Returns an array containing the index of the JButton that was clicked.
	 * 
	 * @param e is the JButton responsible for calling this function
	 * @return the index of the JButton that was clicked
	 */
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

	/**
	 * Resets the game if the user clicks the reset button. Calls update() after
	 * resetting the window settings to its default state.
	 * 
	 * @param e is the JButton responsible for calling this function
	 */
	private void resetGame(ActionEvent e) {
		isStarted = false;
		isResults = false;
		lblFace1.setIcon(imgFace1);
		flagCounter = board.getDifficulty().getMines();
		lblFlags.setText(GameWindow.strFlags + Integer.toString(GameWindow.flagCounter));
		btnReset.setLocation(resetLoc);
		btnReturn.setVisible(false);

		board = new Minesweeper(board.getDifficulty());
		update();
	}
}
