package se.ju.larpet.chess;

// by Peter Larsson-Green, 2021
//
// The Controller in MVC pattern.
public class Main{
	
	public static void main(String args[]){
		
		var chess = new Chess();
		
		var view = new ChessView(chess);
		
	}
	
}