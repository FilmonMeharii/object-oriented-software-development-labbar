package se.ju.larpet.chess;

import java.util.ArrayList;
import java.util.List;

public class BishopStrategy extends PieceStrategy {
	
	private static Move[] moveDirections = {
		new Move(1, 1),
		new Move(1, -1),
		new Move(-1, 1),
		new Move(-1, -1)
	};
	
	public BishopStrategy(Chess chess, Piece piece) {
		super(chess, piece);
	}
	
	@Override
	public List<Position> getValidMoveToPositionsIgnoringCheck() {
		
		var validMoveToPositionsIgnoringCheck = new ArrayList<Position>();
		
		for(var moveDirection : moveDirections){
			
			var x = piece.xPosition + moveDirection.getDx();
			var y = piece.yPosition + moveDirection.getDy();
			var pieceInSquare = chess.getPieceInSquare(x, y);
			
			// Move until we hit the end of board or a piece.
			while(chess.board.containsSquare(x, y) && pieceInSquare == null){
				validMoveToPositionsIgnoringCheck.add(new Position(x, y));
				x += moveDirection.getDx();
				y += moveDirection.getDy();
				pieceInSquare = chess.getPieceInSquare(x, y);
			}
			
			// Check if we hit an enemy piece, then we can move there too.
			if(pieceInSquare != null && pieceInSquare.team != piece.team){
				validMoveToPositionsIgnoringCheck.add(new Position(x, y));
			}
		}
		return validMoveToPositionsIgnoringCheck;
		
	}
	
	@Override
	public char getLetter(){
		return 'B';
	}
	
}
