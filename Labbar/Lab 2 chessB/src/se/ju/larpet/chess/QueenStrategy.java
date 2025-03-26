package se.ju.larpet.chess;

import java.util.List;

public class QueenStrategy extends PieceStrategy {
	
	RockStrategy rockStrategy;
	BishopStrategy bishopStrategy;
	
	public QueenStrategy(Chess chess, Piece piece) {
		super(chess, piece);
		rockStrategy = new RockStrategy(chess, piece);
		bishopStrategy = new BishopStrategy(chess, piece);
	}
	
	@Override
	public List<Position> getValidMoveToPositionsIgnoringCheck() {
		
		// This is probably not a good way of doing it, because changing the rock's
		// or bishop's strategy in the future will unexpectedly change the queen's
		// strategy too! But kept as this to demonstrate a bad example ^^'
		var validMoveToPositionsIgnoringCheck = rockStrategy.getValidMoveToPositionsIgnoringCheck();
		validMoveToPositionsIgnoringCheck.addAll(bishopStrategy.getValidMoveToPositionsIgnoringCheck());
		
		return validMoveToPositionsIgnoringCheck;
		
	}
	
	@Override
	public char getLetter(){
		return 'Q';
	}
	
}
