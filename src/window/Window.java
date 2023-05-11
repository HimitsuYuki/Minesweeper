package window;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;

/**
 * Abstract class for all windows in the application.
 * 
 * @author Yuki
 *
 */
public abstract class Window {
	protected JFrame frame;
	public final static String assetLoc = "Assets/";
	protected JLabel lblAnimatedBG = new JLabel(new ImageIcon(assetLoc + "background.gif"));

	/**
	 * Constructor for all windows, makes sure it is unresizable, disposes on close
	 * and sets layout to null.
	 */
	public Window() {
		frame = new JFrame();
		frame.setResizable(false);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
	}

	/**
	 * Sets the window settings.
	 * 
	 * @param title        is the title to set the window to
	 * @param windowWidth  is the width of the window
	 * @param windowHeight is the height of the window
	 */
	public void setWindowSettings(String title, int windowWidth, int windowHeight) {
		frame.setTitle(title);
		frame.setSize(windowWidth, windowHeight);
		frame.setLocationRelativeTo(null);
	}
}
