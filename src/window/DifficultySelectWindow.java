package window;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;

public class DifficultySelectWindow extends Window {

	private static final long serialVersionUID = -2103625142321853803L;
	
    // ALL LABELS
    private JLabel lblMainMenu = new JLabel(new ImageIcon("bannerdiff.png"));
    private JLabel lblDifficulty = new JLabel();
    
    // ALL BUTTONS
    private JButton btnEasy = new JButton();
    private JButton btnNormal = new JButton();
    private JButton btnHard = new JButton();
    private JButton btnReset = new JButton();
    private JButton btnReturn = new JButton();

}
