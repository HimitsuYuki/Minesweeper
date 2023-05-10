package game;

public class GameBoard implements IGame {
	private Difficulty difficulty;
	private Tile[][] tiles;

	public GameBoard(Difficulty difficulty) {
		this.difficulty = difficulty;
	}

	@Override
	public void initializeBoard(int row, int col) {
		tiles = new Tile[difficulty.getRows()][difficulty.getColumns()];
		for (int i = 0; i < tiles.length; i++) {
			for (int j = 0; j < tiles[0].length; j++) {
				tiles[i][j] = new Tile();
			}
		}
		generateMines(row, col);
		generateClues();
	}

	@Override
	public void generateMines(int row, int col) {
		int placedMines = 0;
		do {
			int randomRow = (int) (Math.random() * difficulty.getRows());
			int randomCol = (int) (Math.random() * difficulty.getColumns());
			if (randomRow != row && randomCol != col) {
				placedMines++;
				tiles[randomRow][randomCol].setBomb(true);;
			}
		} while (placedMines != difficulty.getMines());
	}

	@Override
	public void generateClues() {
        for(int i = 0; i < difficulty.getRows(); i++){
            for(int j = 0; j < difficulty.getColumns(); j++){
                if(tiles[i][j] == null){
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
                    if(i-1 >= 0 && j-1 >= 0 && tiles[i-1][j-1].isBomb()) clue++;
                    // Top middle
                    if(i-1 >= 0 && tiles[i-1][j].isBomb()) clue++;
                    // Top right
                    if(i-1 >= 0 && j+1 < difficulty.getColumns() && tiles[i-1][j+1].isBomb()) clue++;
                    // Left
                    if(j-1 >= 0 && tiles[i][j-1].isBomb()) clue++;
                    // Right
                    if(j+1 < difficulty.getColumns() && tiles[i][j+1].isBomb()) clue++;
                    // Bottom left
                    if(i+1 < difficulty.getRows() && j-1 >= 0 && tiles[i+1][j-1].isBomb()) clue++;
                    // Bottom middle
                    if(i+1 < difficulty.getRows() && tiles[i+1][j].isBomb()) clue++;
                    // Bottom right
                    if(i+1 < difficulty.getRows() && j+1 < difficulty.getColumns() && tiles[i+1][j+1].isBomb()) clue++;
                    
                    tiles[i][j].setClue(clue);
                }
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

}
