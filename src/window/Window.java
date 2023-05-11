package window;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;

public abstract class Window extends JFrame {
	
	private static final long serialVersionUID = -3206820048194603158L;
	public final static String assetLoc = "Assets/";
	protected JLabel lblAnimatedBG = new JLabel(new ImageIcon(assetLoc + "background.gif")); 

	public Window() {
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		getContentPane().setLayout(null);
	}
	
	public void setWindowSettings(String title, int windowWidth, int windowHeight) {
		setTitle(title);
		setSize(windowWidth, windowHeight);
		setLocationRelativeTo(null);
	}
}
