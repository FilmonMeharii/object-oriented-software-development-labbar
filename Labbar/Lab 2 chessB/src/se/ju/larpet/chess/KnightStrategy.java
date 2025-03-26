package se.ju.larpet.chess;

import java.util.ArrayList;
import java.util.List;

public class KnightStrategy extends PieceStrategy {
	
	private static Move[] moves = {
		new Move(1, 2),
		new Move(1, -2),
		new Move(2, 1),
		new Move(2, -1),
		new Move(-1, 2),
		new Move(-1, -2),
		new Move(-2, 1),
		new Move(-2, -1)
	};
	
	public KnightStrategy(Chess chess, Piece piece) {
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
			
		}
		
		return validMoveToPositionsIgnoringCheck;
		
	}
	
	@Override
	public char getLetter(){
		return 'N';
	}
	
}
