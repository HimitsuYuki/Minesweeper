package window;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JButton;
import javax.swing.SwingUtilities;

import game.ITile;

/**
 * Handles the JButtons of the tiles in the current Minesweeper game.
 * 
 * @author Yuki
 *
 */
public class TileMouseAdapter extends MouseAdapter {
	private JButton button;
	private int row;
	private int col;

	/**
	 * Constructor for the adapter of the tile.
	 * 
	 * @param button is the JButton that was clicked by the user
	 * @param row    is the row of the JButton's position
	 * @param col    is the column of the JButton's position
	 */
	public TileMouseAdapter(JButton button, int row, int col) {
		this.button = button;
		this.row = row;
		this.col = col;
	}

	/**
	 * Handles adding flags to tiles when right clicked. Does not do anything if the
	 * tile was already revealed. Increments or decrements the flag counter and
	 * updates the text.
	 */
	public void mouseClicked(MouseEvent me) {
		ITile tile = GameWindow.board.getTiles()[row][col];
		if (SwingUtilities.isRightMouseButton(me) && !tile.isRevealed()) {
			if (!tile.isFlagged() && GameWindow.flagCounter != 0) {
				GameWindow.flagCounter--;
				tile.setFlagged(true);
			} else if (tile.isFlagged()) {
				GameWindow.flagCounter++;
				tile.setFlagged(false);
			}

			GameWindow.lblFlags.setText(GameWindow.strFlags + Integer.toString(GameWindow.flagCounter));
			button.setIcon(tile.getCurIcon().getIcon());
		}
	}

	/**
	 * Handles updating the emoji face at the top of the window if the user
	 * clicks/holds the left mouse button. Does nothing if the tile was already
	 * revealed or flagged.
	 */
	public void mousePressed(MouseEvent me) {
		ITile tile = GameWindow.board.getTiles()[row][col];
		if (SwingUtilities.isLeftMouseButton(me) && !tile.isRevealed() && !tile.isFlagged()) {
			GameWindow.lblFace1.setVisible(false);
			GameWindow.lblFace2.setVisible(true);
		}
	}

	/**
	 * Handles updating the emoji face at the top of the window if the left mouse
	 * button is released.
	 */
	public void mouseReleased(MouseEvent me) {
		if (SwingUtilities.isLeftMouseButton(me)) {
			GameWindow.lblFace1.setVisible(true);
			GameWindow.lblFace2.setVisible(false);
		}
	}
}
