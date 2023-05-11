package window;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JButton;
import javax.swing.SwingUtilities;

import game.ITile;

public class TileMouseAdapter extends MouseAdapter {
	private JButton button;
	private int row;
	private int col;
	
	public TileMouseAdapter(JButton button, int row, int col) {
		this.button = button;
		this.row = row;
		this.col = col;
	}
	
	public void mouseClicked(MouseEvent me) {
		ITile tile = GameWindow.board.getTiles()[row][col];
		if (SwingUtilities.isRightMouseButton(me) && !tile.isRevealed()) {
			if(!tile.isFlagged() && GameWindow.flagCounter != 0) {
				GameWindow.flagCounter--;
				tile.setFlagged(true);
			}
			else if (tile.isFlagged()){
				GameWindow.flagCounter++;
				tile.setFlagged(false);
			}
			
			GameWindow.lblFlags.setText(GameWindow.strFlags + Integer.toString(GameWindow.flagCounter));
			button.setIcon(tile.getCurIcon().getIcon());
		}
	}
	
	public void mousePressed(MouseEvent me) {
		ITile tile = GameWindow.board.getTiles()[row][col];
		if (SwingUtilities.isLeftMouseButton(me) && !tile.isRevealed() && !tile.isFlagged()) {
			GameWindow.lblFace1.setVisible(false);
			GameWindow.lblFace2.setVisible(true);
		}
	}
	
	public void mouseReleased(MouseEvent me) {
		if (SwingUtilities.isLeftMouseButton(me)) {
			GameWindow.lblFace1.setVisible(true);
			GameWindow.lblFace2.setVisible(false);
		}
	}
}
