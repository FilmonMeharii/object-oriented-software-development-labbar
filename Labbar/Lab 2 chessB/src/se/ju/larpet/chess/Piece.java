package se.ju.larpet.chess;

import java.util.List;

public class Piece {
	
	protected Chess chess;
	protected Team team;
	protected int xPosition;
	protected int yPosition;
	protected PieceStrategy pieceStrategy;
	
	public Piece(Chess chess, Team team, int xPosition, int yPosition, PieceType type){
		this.chess = chess;
		this.team = team;
		this.xPosition = xPosition;
		this.yPosition = yPosition;
		this.turnInto(type);
	}
	
	public void turnInto(PieceType type){
		switch(type){
			case PAWN:
				pieceStrategy = new PawnStrategy(chess, this);
				break;
			case ROCK:
				pieceStrategy = new RockStrategy(chess, this);
				break;
			case KNIGHT:
				pieceStrategy = new KnightStrategy(chess, this);
				break;
			case BISHOP:
				pieceStrategy = new BishopStrategy(chess, this);
				break;
			case QUEEN:
				pieceStrategy = new QueenStrategy(chess, this);
				break;
			case KING:
				pieceStrategy = new KingStrategy(chess, this);
		};
	}
	
	public boolean hasPosition(int x, int y){
		return (
			xPosition == x &&
			yPosition == y
		);
	}
	
	public List<Position> getValidMoveToPositions(){
		
		var validMoveToPosition = pieceStrategy.getValidMoveToPositionsIgnoringCheck();
		
		// TODO: Filter out the moves that causes the team's own King to be checked
		// (too complicated to implement... ^^).
		
		return validMoveToPosition;
		
	}
	
	public boolean shouldTurnIntoOtherType(){
		return pieceStrategy.shouldTurnIntoOtherType();
	}
	
	public char getLetter(){
		return pieceStrategy.getLetter();
	}
	
}
