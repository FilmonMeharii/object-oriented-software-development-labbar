package lab4;

public class Controller {
	
	private View view1;
	private View view2;
	private RuleEngine _ruleEngine;
	protected int player;
	private final int playerO = 0;
	private final int playerX = 1;

	public Controller() {
		_ruleEngine =new RuleEngine();
		view1 = new View();
		view2 = new View();
		
		_ruleEngine.updateBoard(view1, view2);
		view1.addEventListener(this);
		view2.addEventListener(this);
		player = 0;
	}
	
	public void setPlayer(int nextPlayer) {
		player = nextPlayer;
	}
	public int getPlayer() {
		return player;
	}
	
	public void updateClicks(String name, int currentPlayer) {
		
		if(name.equals("Reset")) {
			_ruleEngine.resetBoard();
			view1.updateView("");
			view2.updateView("");
		}
		else {
			if(currentPlayer==playerO) {
				String[]playerPosition = name.split(",");
				
				int x= Integer.valueOf(playerPosition[0]);
				int y= Integer.valueOf(playerPosition[1]);
				
				String returnMessage = _ruleEngine.handsMove("O", x, y);
				if(!returnMessage.equals("model updated")) {
					view1.updateView(returnMessage);
					view2.updateView(returnMessage);
					return;
				}
				else {
					view1.updateView("");
					view2.updateView("");
				}
				if(ifWinner()) {
					return;
				}
				ifWinner();
				setPlayer(playerX);
				player = getPlayer();
			}
			else {
				String[] playerPosition = name.split(",");
				
				int x = Integer.valueOf(playerPosition[0]);
				int y = Integer.valueOf(playerPosition[1]);
				
				String returnMessage = _ruleEngine.handsMove("X", x, y);
				if(!returnMessage.equals("model updated")) {
					view1.updateView(returnMessage);
					view2.updateView(returnMessage);
					return;
				}
				else {
					view1.updateView("");
					view2.updateView("");
				}
				if(ifWinner()) {
					return;
				}
				ifWinner();
				setPlayer(playerO);
				player = getPlayer();
			}
		}
	}
	
	private boolean ifWinner() {
		String ifWinner = _ruleEngine.getWinner();
		if(ifWinner.equals("No")) {
			return false;
		}
		
		switch (ifWinner) {		
		case "X":
			view1.updateView(" Congrats! Player X won, click Reset to play more!");
			view2.updateView(" Congrats! Player X won, click Reset to play more!");
			break;			
		case "O":
			view1.updateView(" Congrats! Player O won, click Reset to play more!");
			view2.updateView(" Congrats! Player O won, click Reset to play more!");
			break;
		case "Tied":
			view1.updateView(" Tied:No body won, click Reset to play more!");
			view2.updateView(" Tied:No body won, click Reset to play more!");
			break;		
		}
		return true;
		}
}
















