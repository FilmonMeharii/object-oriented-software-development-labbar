package lab4;


public class Board {
	
	private String[][] dimension;
	private int xAxis = 3;
	private int yAxis = 3;
	
	
	Board() {
		dimension = new String[xAxis][yAxis];
	}
	
	
	public String[][] getBoard() {
		return dimension;
	}
	
	public int getRows() {
		return xAxis;
	}
	
	public int getColumns () {
		return yAxis;
	}
}