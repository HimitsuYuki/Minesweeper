package window;

import javax.swing.JFrame;

public abstract class Window extends JFrame {
	
	private static final long serialVersionUID = -3206820048194603158L;

	public Window() {
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		getContentPane().setLayout(null);
	}
}
