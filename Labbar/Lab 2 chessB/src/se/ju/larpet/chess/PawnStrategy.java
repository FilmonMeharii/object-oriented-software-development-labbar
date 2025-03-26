package se.ju.larpet.chess;

import java.util.ArrayList;
import java.util.List;

public class PawnStrategy extends PieceStrategy {
	
	int yMoveDirection;
	
	public PawnStrategy(Chess chess, Piece piece) {
		super(chess, piece);
		
		if(piece.team == Team.WHITE){
			yMoveDirection = 1;
		}else{
			yMoveDirection = -1;
		}
		
	}
	
	public boolean isPawnOnStartPosition(){
		return (
			(piece.team == Team.WHITE && piece.yPosition == 1) ||
			(piece.team == Team.BLACK && piece.yPosition == 6)
		);
	}
	
	@Override
	public List<Position> getValidMoveToPositionsIgnoringCheck() {
		
		var validMoveToPositionsIgnoringCheck = new ArrayList<Position>();
		
		// Can move one step forward?
		{
			var moveToX = piece.xPosition;
			var moveToY = piece.yPosition + yMoveDirection;
			if(chess.isSquareFree(moveToX, moveToY)){
				validMoveToPositionsIgnoringCheck.add(new Position(moveToX, moveToY));
			}
		}
		
		// Can take opponent forward to left/right?
		{
			var moveToX = piece.xPosition - 1;
			var moveToY = piece.yPosition + yMoveDirection;
			Piece pieceInSquare = chess.getPieceInSquare(
				moveToX,
				moveToY
			);
			if(pieceInSquare != null && pieceInSquare.team != piece.team){
				validMoveToPositionsIgnoringCheck.add(new Position(moveToX, moveToY));
			}
		}
		
		// Can take opponent forward to right/left?
		{
			var moveToX = piece.xPosition + 1;
			var moveToY = piece.yPosition + yMoveDirection;
			Piece pieceInSquare = chess.getPieceInSquare(
				moveToX,
				moveToY
			);
			if(pieceInSquare != null && pieceInSquare.team != piece.team){
				validMoveToPositionsIgnoringCheck.add(new Position(moveToX, moveToY));
			}
		}
		
		// On start position, can move two steps forwards?
		{
			var moveToX = piece.xPosition;
			var moveToY = piece.yPosition + yMoveDirection*2;
			if(
				isPawnOnStartPosition() &&
				chess.isSquareFree(piece.xPosition, piece.yPosition + yMoveDirection) &&
				chess.isSquareFree(moveToX, moveToY)
			){
				validMoveToPositionsIgnoringCheck.add(new Position(moveToX, moveToY));
			}
		}
		
		// TODO: Check if opponent pawn moved two steps in last move, then this pawn
		// might be able to take it (too complicated to implement... ^^).
		
		return validMoveToPositionsIgnoringCheck;
		
	}
	
	@Override
	public boolean shouldTurnIntoOtherType() {
		return (
			(piece.team == Team.WHITE && piece.yPosition == 7) ||
			(piece.team == Team.BLACK && piece.yPosition == 0)
		);
	}
	
	@Override
	public char getLetter(){
		return 'P';
	}
	
}
