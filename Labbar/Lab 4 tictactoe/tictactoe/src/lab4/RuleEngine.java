package lab4;

public class RuleEngine {
	
	private Board myBoard;
	private String[][] board;
	protected String winner;
	protected int playCount;
	private boolean gameOver;
	private View view1;
	private View view2;
	
	private int columns;
	private int rows;


	public RuleEngine() {  
		myBoard = new Board();
		board = myBoard.getBoard();
		columns = myBoard.getColumns();
		rows = myBoard.getRows();
	}
	
	public void updateBoard(View newView, View anotherView) {
		this.view1 = newView;
		this.view2 = anotherView;
		resetBoard();
	}
	
	public String handsMove(String currentPlayer, int xAxis, int yAxis)  {
		
		if(gameOver) {
			return "       Game Over! Press reset to play again.";
		}
		if(board[xAxis][yAxis].equals("")) {
			board[xAxis][yAxis] = currentPlayer;
			playCount++;
			checkForWinner(currentPlayer);
			view1.sendClicks(currentPlayer, xAxis, yAxis);
			view2.sendClicks(currentPlayer, xAxis, yAxis);
			return "model updated";
		}else if(board[xAxis][yAxis].equals("model updated")) {
			return "       Already occupied";
		}
		else {
			return "       This box has already been played. Please try another one!";
		}
	}

	public void resetBoard() {
		gameOver = false;
		playCount = 0;
		for(int y= 0; y<columns; y++) {
			for(int x= 0; x<rows; x++) {
				board[x][y] = "";
				view1.sendClicks("", x, y);
				view2.sendClicks("", x, y);
			}
		}
		winner = "No";         
	}
	
	public String getWinner() {
		return winner;
	}

	
	private void checkForWinner(String gotWinner) {
		boolean playerWon = checkAcross(gotWinner) || checkDown(gotWinner) ||
		checkDiagonal(gotWinner) || checkAntiDiagonal(gotWinner);
		if(playerWon) {
			this.winner = gotWinner;
			gameOver = true;
		}
		else if(playCount == 9) {
			this.winner = "Tied";
		}
	}

	private boolean checkAcross(String playerWon) {
		int symbolCount = 0;
		
		for(int y = 0; y < columns; y++) { //Kollar varje kolumn
			symbolCount = 0;
			for(int x = 0; x < rows; x++) {
				if(board[x][y].equals(playerWon)) {
					symbolCount++;
				}
			}
			if(symbolCount == 3) {
				return true;
			}		
		}
		return false;
	}


	private boolean checkDown(String playerWon) {
		int symbolCount = 0;
		for(int x = 0; x < rows; x++) {				//Kollar varje rad
			symbolCount = 0;
			for(int y = 0; y < columns; y++) {
				if(board[x][y].equals(playerWon)) {
					symbolCount++;
				}
			}
			if(symbolCount == 3) {
				return true;
			}		
		}
		return false;
	}


	private boolean checkDiagonal(String playing) {
		int symbolCount = 0;
		for(int i = 0; i < columns; i++) {
			if(board[i][i].equals(playing)) {
				symbolCount++;
			}			
		}
		if(symbolCount == 3) {
			return true;
		}	
		return false;
	}

	private boolean checkAntiDiagonal(String playing) {
		int symbolCount = 0;
		int y = 0;	
		for(int x = 0; x < rows; x++) {
			y = columns - 1 - x;
			if(board[x][y].equals(playing)) {
				symbolCount++;
			}			
		}
		if(symbolCount == 3) {
			return true;
		}	
		return false;
	}
}

