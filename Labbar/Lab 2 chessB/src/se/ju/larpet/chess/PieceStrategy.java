package se.ju.larpet.chess;

import java.util.List;

public abstract class PieceStrategy {
	
	protected Chess chess;
	protected Piece piece;
	
	public PieceStrategy(Chess chess, Piece piece){
		this.chess = chess;
		this.piece = piece;
	}
	
	public abstract List<Position> getValidMoveToPositionsIgnoringCheck();
	
	public abstract char getLetter();
	
	// Most pieces aren't able to transform, so use this implementation as default.
	public boolean shouldTurnIntoOtherType(){
		return false;
	}
	
}
