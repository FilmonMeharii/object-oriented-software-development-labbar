package se.ju.larpet.chess;

import java.util.ArrayList;
import java.util.List;

public class KingStrategy extends PieceStrategy {
	
	private static Move[] moves = {
		new Move(-1, -1),
		new Move(-1, 0),
		new Move(-1, 1),
		new Move(0, -1),
		new Move(0, 1),
		new Move(1, -1),
		new Move(1, 0),
		new Move(1, 1)
	};
	
	public KingStrategy(Chess chess, Piece piece) {
		super(chess, piece);
	}
	
	@Override
	public List<Position> getValidMoveToPositionsIgnoringCheck() {
		
		var validMoveToPositionsIgnoringCheck = new ArrayList<Position>();
		
		for(var move : moves){
			
			var x = piece.xPosition + move.getDx();
			var y = piece.yPosition + move.getDy();
			var pieceInSquare = chess.getPieceInSquare(x, y);
			
			// Must land inside the board on empty or opponent square.
			if(
				chess.board.containsSquare(x, y) &&
				(pieceInSquare == null || pieceInSquare.team != piece.team)
			){
				validMoveToPositionsIgnoringCheck.add(new Position(x, y));
			}
			
			// TODO: Also check so not moving too close to enemy king
			// (too complicated to implement... ^^).
			
			// TODO: Also add support for castling
			// (too complicated to implement... ^^).
			
		}
		
		return validMoveToPositionsIgnoringCheck;
		
	}
	
	@Override
	public char getLetter(){
		return 'K';
	}
	
}
