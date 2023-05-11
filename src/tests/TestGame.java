package tests;

import static org.junit.Assert.*;

import org.junit.Test;

import game.Difficulty;
import game.IMinesweeper;
import game.ITile;
import game.Minesweeper;
import game.Tile;
import game.TileIcon;

public class TestGame {

	@Test
	public void MinesweeperDefaultEasy() {
		IMinesweeper game = new Minesweeper(Difficulty.Easy);
		assertEquals(game.getDifficulty(), Difficulty.Easy);
		assertEquals(game.getTiles().length, 9);
		assertEquals(game.getTiles()[0].length, 9);
		assertEquals(game.getDifficulty().getMines(), 10);
		assertEquals(game.isGameOver(), false);
		assertEquals(game.isWin(), false);
	}

	@Test
	public void MinesweeperDefaultNormal() {
		IMinesweeper game = new Minesweeper(Difficulty.Normal);
		assertEquals(game.getDifficulty(), Difficulty.Normal);
		assertEquals(game.getTiles().length, 16);
		assertEquals(game.getTiles()[0].length, 16);
		assertEquals(game.getDifficulty().getMines(), 40);
		assertFalse(game.isGameOver());
		assertFalse(game.isWin());
	}

	@Test
	public void MinesweeperDefaultHard() {
		IMinesweeper game = new Minesweeper(Difficulty.Hard);
		assertEquals(game.getDifficulty(), Difficulty.Hard);
		assertEquals(game.getTiles().length, 16);
		assertEquals(game.getTiles()[0].length, 30);
		assertEquals(game.getDifficulty().getMines(), 99);
		assertFalse(game.isGameOver());
		assertFalse(game.isWin());
	}

	@Test
	public void MinesweeperBoardNotNull() {
		IMinesweeper game = new Minesweeper(Difficulty.Hard);

		for (int i = 0; i < Difficulty.Hard.getRows(); i++) {
			for (int j = 0; j < Difficulty.Hard.getColumns(); j++) {
				assertNotNull(game.getTiles()[i][j]);
			}
		}
	}

	@Test
	public void MinesweeperBoardSelectTile() {
		IMinesweeper game = new Minesweeper(Difficulty.Easy);
		ITile tile = game.getTiles()[0][0];
		tile.setRevealed(true);
		assertTrue(game.getTiles()[0][0].isRevealed());
	}

	@Test
	public void MinesweeperRecursiveEmpty() {
		IMinesweeper game = new Minesweeper(Difficulty.Easy);
		ITile[][] tiles = game.getTiles();

		for (int i = 6; i < Difficulty.Easy.getRows(); i++) {
			for (int j = 0; j < Difficulty.Easy.getColumns(); j++) {
				tiles[i][j].setClue(1);
			}
		}

		game.revealTile(0, 0);

		for (int i = 0; i < 6; i++) {
			for (int j = 0; j < Difficulty.Easy.getColumns(); j++) {
				assertTrue(tiles[i][j].isRevealed());
				assertEquals(tiles[i][j].getCurIcon(), TileIcon.Zero);
			}
		}

		for (int i = 6; i < 7; i++) {
			for (int j = 0; j < Difficulty.Easy.getColumns(); j++) {
				assertTrue(tiles[i][j].isRevealed());
				assertEquals(tiles[i][j].getCurIcon(), TileIcon.One);
			}
		}

		for (int i = 7; i < Difficulty.Easy.getRows(); i++) {
			for (int j = 0; j < Difficulty.Easy.getColumns(); j++) {
				assertFalse(tiles[i][j].isRevealed());
				assertEquals(tiles[i][j].getCurIcon(), TileIcon.Default);
			}
		}
	}

	@Test
	public void MinesweeperWin() {
		IMinesweeper game = new Minesweeper(Difficulty.Normal);
		game.generateMines(0, 0);
		game.revealTile(0, 0);

		assertTrue(game.isGameOver());
		assertTrue(game.isWin());

		for (int i = 0; i < Difficulty.Normal.getRows(); i++) {
			for (int j = 0; j < Difficulty.Normal.getColumns(); j++) {
				assertTrue(game.getTiles()[i][j].isRevealed());

				if (game.getTiles()[i][j].isMine()) {
					ITile mine = game.getTiles()[i][j];
					assertEquals(mine.getCurIcon(), TileIcon.MineWin);
				}

			}
		}
	}

	@Test
	public void MinesweeperLose() {
		IMinesweeper game = new Minesweeper(Difficulty.Normal);
		ITile[][] tiles = game.getTiles();
		tiles[0][0].setMine(true);

		game.revealTile(0, 0);
		assertTrue(game.isGameOver());
		assertFalse(game.isWin());

		for (int i = 0; i < Difficulty.Normal.getRows(); i++) {
			for (int j = 0; j < Difficulty.Normal.getColumns(); j++) {
				assertTrue(game.getTiles()[i][j].isRevealed());
			}
		}

		assertEquals(tiles[0][0].getCurIcon(), TileIcon.MineLose);
	}

	@Test
	public void TileDefault() {
		ITile tile = new Tile();
		assertEquals(tile.getClue(), 0);
		assertEquals(tile.getCurIcon(), TileIcon.Default);
		assertFalse(tile.isFlagged());
		assertFalse(tile.isMine());
		assertFalse(tile.isRevealed());
	}

	@Test
	public void TileFlagged() {
		ITile tile = new Tile();
		tile.setFlagged(true);
		assertTrue(tile.isFlagged());
		assertEquals(tile.getCurIcon(), TileIcon.Flag);

		tile.setFlagged(false);
		assertFalse(tile.isFlagged());
		assertEquals(tile.getCurIcon(), TileIcon.Default);
	}

	@Test
	public void TileMine() {
		ITile tile = new Tile();
		tile.setMine(true);
		assertTrue(tile.isMine());
		tile.setMine(false);
		assertFalse(tile.isMine());
	}

	@Test
	public void TileSetClue() {
		ITile tile = new Tile();
		tile.setClue(5);
		assertEquals(tile.getClue(), 5);
	}

	@Test
	public void TileReveal() {
		ITile tile = new Tile();
		tile.setClue(4);
		tile.setRevealed(true);
		assertTrue(tile.isRevealed());
		assertEquals(tile.getCurIcon(), TileIcon.Four);

		tile.setRevealed(false);
		assertFalse(tile.isRevealed());
		assertEquals(tile.getCurIcon(), TileIcon.Default);
	}
	
	@Test
	public void TileRevealFlag() {
		Tile tile = new Tile();
		tile.setRevealed(true);
		tile.setFlagged(true);
		assertFalse(tile.isFlagged());
	}

}
