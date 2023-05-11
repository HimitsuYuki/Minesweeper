package game;

public class GameBoard implements IGame {
	private Difficulty difficulty;
	private Tile[][] tiles;
	private Tile[] mines;
	private boolean gameOver;
	private boolean isWin;

	public GameBoard(Difficulty difficulty) {
		this.difficulty = difficulty;
		mines = new Tile[difficulty.getMines()];
		tiles = new Tile[difficulty.getRows()][difficulty.getColumns()];
		for (int i = 0; i < tiles.length; i++) {
			for (int j = 0; j < tiles[0].length; j++) {
				tiles[i][j] = new Tile();
			}
		}
	}

	@Override
	public void initializeBoard(int row, int col) {
		generateMines(row, col);
		generateClues();
	}

	@Override
	public void generateMines(int row, int col) {
		int placedMines = 0;
		do {
			int randomRow = (int) (Math.random() * difficulty.getRows());
			int randomCol = (int) (Math.random() * difficulty.getColumns());
			if (randomRow != row && randomCol != col && !tiles[randomRow][randomCol].isMine()) {
				mines[placedMines] = tiles[randomRow][randomCol];
				tiles[randomRow][randomCol].setMine(true);
				placedMines++;
			}
		} while (placedMines != difficulty.getMines());
	}

	@Override
	public void generateClues() {
        for(int i = 0; i < difficulty.getRows(); i++){
            for(int j = 0; j < difficulty.getColumns(); j++) {
            	if(tiles[i][j].isMine())
            		continue;
            	/**
                 * Clues calculated based on a square formation.
                 * All checks make sure that the square is a bomb, and that it is not checking an out of bounds index.
                 * If there is a bomb, it increments the clue counter by 1.
                 * 0 0 0
                 * 0 b 0
                 * 0 0 0
                 */
            	int clue = 0;
                // Top left
                if(i-1 >= 0 && j-1 >= 0 && tiles[i-1][j-1].isMine()) clue++;
                // Top middle
                if(i-1 >= 0 && tiles[i-1][j].isMine()) clue++;
                // Top right
                if(i-1 >= 0 && j+1 < difficulty.getColumns() && tiles[i-1][j+1].isMine()) clue++;
                // Left
                if(j-1 >= 0 && tiles[i][j-1].isMine()) clue++;
                // Right
                if(j+1 < difficulty.getColumns() && tiles[i][j+1].isMine()) clue++;
                // Bottom left
                if(i+1 < difficulty.getRows() && j-1 >= 0 && tiles[i+1][j-1].isMine()) clue++;
                // Bottom middle
                if(i+1 < difficulty.getRows() && tiles[i+1][j].isMine()) clue++;
                // Bottom right
                if(i+1 < difficulty.getRows() && j+1 < difficulty.getColumns() && tiles[i+1][j+1].isMine()) clue++;
                
			    tiles[i][j].setClue(clue);
            }
        }
	}
	
	public void revealTile(int row, int col) {
		if (tiles[row][col].isFlagged())
			return;
		
		if (tiles[row][col].isMine()) {
			gameOver();
			return;
		}
		
		recursiveTileReveal(row, col);
		
		if(isGameWon())
			gameOver();
	}
	
	private void recursiveTileReveal(int row, int col) {
		if(row < 0 || row == tiles.length || col < 0 || col == tiles[0].length)
			return;
		
		Tile tile = tiles[row][col];
		if (tile.isRevealed() || tile.isFlagged())
			return;
		
		tile.setRevealed(true);
		
		if(tile.getClue() != 0) // we want it to stop when it reaches a clue with a number
			return;
		
		// recursive traverse if it is a tile with 0 clues
		recursiveTileReveal(row - 1, col - 1); // top left
		recursiveTileReveal(row - 1, col); // top middle
		recursiveTileReveal(row - 1, col + 1); // top right
		recursiveTileReveal(row, col - 1); // left
		recursiveTileReveal(row, col + 1); // right
		recursiveTileReveal(row + 1, col - 1); // bottom left
		recursiveTileReveal(row + 1, col); // bottom middle
		recursiveTileReveal(row + 1, col + 1); // bottom right
	}
	
	public boolean isGameWon() {
        for(int i = 0; i < difficulty.getRows(); i++){
            for(int j = 0; j < difficulty.getColumns(); j++) {
            	if(!tiles[i][j].isRevealed() && !tiles[i][j].isMine())
            		return false;
            }
        }
        
        isWin = true;
        return true;
	}
	
	public void gameOver() {
		gameOver = true;
		
		if (!isWin) {
	        for(int i = 0; i < difficulty.getRows(); i++){
	            for(int j = 0; j < difficulty.getColumns(); j++) {
	            	if(tiles[i][j].isMine())
	            		tiles[i][j].setClue(10);
	            		
	            	tiles[i][j].setRevealed(true);
	            }
	        }
		}
		else {
			for (int i = 0; i < mines.length; i++) {
				if (mines[i].isFlagged())
					continue;
				mines[i].setRevealed(true);
			}
		}
	}
	
	/**
	 * @return the difficulty
	 */
	public Difficulty getDifficulty() {
		return difficulty;
	}

	/**
	 * @return the tiles
	 */
	public Tile[][] getTiles() {
		return tiles;
	}

	/**
	 * @return the gameOver
	 */
	public boolean isGameOver() {
		return gameOver;
	}

	/**
	 * @return the isWin
	 */
	public boolean isWin() {
		return isWin;
	}

}
