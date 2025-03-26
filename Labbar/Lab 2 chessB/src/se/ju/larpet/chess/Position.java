package se.ju.larpet.chess;

public class Position {
	
	private int x;
	private int y;
	
	public Position(int x, int y){
		this.x = x;
		this.y = y;
	}
	
	public int getX(){
		return x;
	}
	
	public int getY(){
		return y;
	}
	
	@Override
	public boolean equals(Object object){
		
		if(object == null){
			return false;
		}
		
		if(getClass() != object.getClass()){
			return false;
		}
		
		Position other = (Position) object;
		
		return (
			this.x == other.x &&
			this.y == other.y
		);
		
	}
	
}
