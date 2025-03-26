package se.ju.larpet.chess;

public class Move {
	
	protected int dx;
	protected int dy;
	
	public Move(int dx, int dy){
		this.dx = dx;
		this.dy = dy;
	}
	
	public int getDx(){
		return dx;
	}
	
	public int getDy(){
		return dy;
	}
	
	public Position getDestination(Position movingFrom){
		return new Position(
			movingFrom.getX() + dx,
			movingFrom.getY() + dy
		);
	}
	
}
