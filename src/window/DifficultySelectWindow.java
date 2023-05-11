package window;

import java.awt.Color;
import java.awt.Font;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;

import game.Difficulty;
import game.Minesweeper;

/**
 * The initial window of the application, allows the user to select a set of
 * difficulties.
 * 
 * @author Yuki
 *
 */
public class DifficultySelectWindow extends Window {

	private final int winWidth = 350;
	private final int winHeight = 350;
	private final String winTitle = "Minesweeper: Select Difficulty";
	private JLabel lblMainMenu = new JLabel(new ImageIcon(assetLoc + "bannerdiff.png"));
	private JLabel lblDifficulty = new JLabel("Select a difficulty");
	private JButton btnEasy = new JButton("Easy");
	private JButton btnNormal = new JButton("Normal");
	private JButton btnHard = new JButton("Hard");

	/**
	 * Constructor to setup the difficulty select window. Sets up the window
	 * settings and its GUI.
	 */
	public DifficultySelectWindow() {
		setWindowSettings(winTitle, winWidth, winHeight);
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
	 * Sets the label settings and adds it to the frame.
	 */
	private void addLabels() {
		lblMainMenu.setLocation(17, 20);
		lblMainMenu.setSize(300, 80);
		frame.add(lblMainMenu);

		lblDifficulty.setLocation(100, 120);
		lblDifficulty.setFont(new Font("Arial", Font.BOLD, 17));
		lblDifficulty.setForeground(Color.WHITE);
		lblDifficulty.setSize(140, 17);
		frame.add(lblDifficulty);

		lblAnimatedBG.setSize(1460, 821);
		frame.add(lblAnimatedBG);
	}

	/**
	 * Sets the button settings and adds it to the frame.
	 */
	private void addButtons() {
		btnEasy.setLocation(117, 160);
		btnEasy.setSize(100, 30);
		btnEasy.addActionListener(e -> startGame(Difficulty.Easy));
		frame.add(btnEasy);

		btnNormal.setLocation(117, 210);
		btnNormal.setSize(100, 30);

		btnNormal.addActionListener(e -> startGame(Difficulty.Normal));
		frame.add(btnNormal);

		btnHard.setLocation(117, 260);
		btnHard.setSize(100, 30);
		btnHard.addActionListener(e -> startGame(Difficulty.Hard));
		frame.add(btnHard);
	}

	/**
	 * Starts the Minesweeper game, called if a difficulty was selected.
	 * 
	 * @param difficulty is the difficulty to instantiate the Minesweeper game with.
	 */
	private void startGame(Difficulty difficulty) {
		frame.dispose();
		new GameWindow(new Minesweeper(difficulty));
	}
}
