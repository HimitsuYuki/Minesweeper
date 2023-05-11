package window;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;

public abstract class Window {
	protected JFrame frame;
	public final static String assetLoc = "Assets/";
	protected JLabel lblAnimatedBG = new JLabel(new ImageIcon(assetLoc + "background.gif")); 

	public Window() {
		frame = new JFrame();
		frame.setResizable(false);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
	}
	
	public void setWindowSettings(String title, int windowWidth, int windowHeight) {
		frame.setTitle(title);
		frame.setSize(windowWidth, windowHeight);
		frame.setLocationRelativeTo(null);
	}
}
