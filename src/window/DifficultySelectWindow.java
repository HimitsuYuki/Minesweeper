package window;

import java.awt.Color;
import java.awt.Font;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;

import game.Difficulty;
import game.GameBoard;

public class DifficultySelectWindow extends Window {
	
	// Window Settings
	private static final long serialVersionUID = -2103625142321853803L;
	private final int winWidth = 350;
	private final int winHeight = 350;
	private final String winTitle = "Minesweeper: Select Difficulty";
	
    // ALL LABELS
    private JLabel lblMainMenu = new JLabel(new ImageIcon(assetLoc + "bannerdiff.png"));
    private JLabel lblDifficulty = new JLabel();
    
    // ALL BUTTONS
    private JButton btnEasy = new JButton("Easy");
    private JButton btnNormal = new JButton("Normal");
    private JButton btnHard = new JButton("Hard");
    
    public DifficultySelectWindow() {
    	setWindowSettings(winTitle, winWidth, winHeight);
    	setupGUI();
    	setVisible(true);
    }
    
    private void setupGUI () {
    	addButtons();
    	addLabels();
    }
    
    private void addLabels() {
        lblMainMenu.setLocation(17, 20);
        lblMainMenu.setSize(300, 80);
        add(lblMainMenu);
        
        lblDifficulty.setLocation(100, 120);
        lblDifficulty.setFont(new Font ("Arial", Font.BOLD, 17));
        lblDifficulty.setForeground(Color.WHITE);
        lblDifficulty.setSize(140, 17);
        lblDifficulty.setText("Select a difficulty");
        add(lblDifficulty);
        
        lblAnimatedBG.setSize(1460, 821);
        add(lblAnimatedBG);
    }
    
    private void addButtons() {
        btnEasy.setLocation(117, 160);
        btnEasy.setSize(100, 30);
        btnEasy.addActionListener(e -> startGame(Difficulty.Easy));
        add(btnEasy);
        
        btnNormal.setLocation(117, 210);
        btnNormal.setSize(100, 30);

        btnNormal.addActionListener(e -> startGame(Difficulty.Normal));
        add(btnNormal);
        
        btnHard.setLocation(117, 260);
        btnHard.setSize(100, 30);
        btnHard.addActionListener(e -> startGame(Difficulty.Hard));
        add(btnHard);
    }
    
    private void startGame(Difficulty difficulty) {
    	dispose();
    	new GameWindow(new GameBoard(difficulty));
    }
}
