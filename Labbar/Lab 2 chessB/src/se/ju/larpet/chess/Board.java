package se.ju.larpet.chess;

// The board is represented like this:
// Y
// 7 R N B Q K B N R        BLACK UPP HERE
// 6 P P P P P P P P
// 5
// 4
// 3
// 2
// 1 P P P P P P P P
// 0 R N B Q K B N R        WHITE DOWN HERE
//   0 1 2 3 4 5 6 7 --> X
public class Board {
	
	protected int xSize;
	protected int ySize;
	
	// xSize = width, ySize = height (these names make the code more readable).
	public Board(int xSize, int ySize){
		this.xSize = xSize;
		this.ySize = ySize;
	}
	
	public int getXSize(){
		return xSize;
	}
	
	public int getYSize(){
		return ySize;
	}
	
	public boolean containsSquare(int x, int y){
		return (
			(0 <= x && x < xSize) &&
			(0 <= y && y < ySize)
		);
	}
	
}
