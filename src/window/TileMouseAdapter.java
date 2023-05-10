package window;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JButton;
import javax.swing.SwingUtilities;

public class TileMouseAdapter extends MouseAdapter {
	private JButton button;
	
	public TileMouseAdapter(JButton button) {
		this.button = button;
	}
	
	public void mouseClicked(MouseEvent me) {
		if (SwingUtilities.isRightMouseButton(me)) {
			if(button.getIcon() != GameWindow.flag && button.getIcon() == GameWindow.tile) {
				GameWindow.flagCounter--;
				button.setIcon(GameWindow.flag);
			}
			else if (button.getIcon() == GameWindow.flag){
				GameWindow.flagCounter++;
				button.setIcon(GameWindow.tile);
			}
		}
	}
	
	public void mousePressed(MouseEvent me) {
		if (SwingUtilities.isLeftMouseButton(me) && button.getIcon() == GameWindow.tile) {
			GameWindow.lblFace1.setVisible(false);
			GameWindow.lblFace2.setVisible(true);
		}
	}
	
	public void mouseReleased(MouseEvent me) {
		if (SwingUtilities.isLeftMouseButton(me) && button.getIcon() == GameWindow.tile) {
			GameWindow.lblFace1.setVisible(true);
			GameWindow.lblFace2.setVisible(false);
		}
	}
}
