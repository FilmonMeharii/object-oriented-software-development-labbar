package se.ju.larpet.chess;

import java.util.List;
import java.util.ArrayList;

public class Chess {
	
	protected Board board;
	protected Team nextTeamToMove;
	
	// Here a hash map might be better, or 2 dimensional array (faster lookups).
	protected ArrayList<Piece> pieces;
	
	public Chess(){
		
		board = new Board(8, 8);
		
		nextTeamToMove = Team.WHITE;
		
		pieces = new ArrayList<>(List.of(
			// Black team at the top.
			new Piece(this, Team.BLACK, 0, 7, PieceType.ROCK),
			new Piece(this, Team.BLACK, 1, 7, PieceType.KNIGHT),
			new Piece(this, Team.BLACK, 2, 7, PieceType.BISHOP),
			new Piece(this, Team.BLACK, 3, 7, PieceType.QUEEN),
			new Piece(this, Team.BLACK, 4, 7, PieceType.KING),
			new Piece(this, Team.BLACK, 5, 7, PieceType.BISHOP),
			new Piece(this, Team.BLACK, 6, 7, PieceType.KNIGHT),
			new Piece(this, Team.BLACK, 7, 7, PieceType.ROCK),
			
			new Piece(this, Team.BLACK, 0, 6, PieceType.PAWN),
			new Piece(this, Team.BLACK, 1, 6, PieceType.PAWN),
			new Piece(this, Team.BLACK, 2, 6, PieceType.PAWN),
			new Piece(this, Team.BLACK, 3, 6, PieceType.PAWN),
			new Piece(this, Team.BLACK, 4, 6, PieceType.PAWN),
			new Piece(this, Team.BLACK, 5, 6, PieceType.PAWN),
			new Piece(this, Team.BLACK, 6, 6, PieceType.PAWN),
			new Piece(this, Team.BLACK, 7, 6, PieceType.PAWN),
			
			// White team at the bottom.
			new Piece(this, Team.WHITE, 0, 1, PieceType.PAWN),
			new Piece(this, Team.WHITE, 1, 1, PieceType.PAWN),
			new Piece(this, Team.WHITE, 2, 1, PieceType.PAWN),
			new Piece(this, Team.WHITE, 3, 1, PieceType.PAWN),
			new Piece(this, Team.WHITE, 4, 1, PieceType.PAWN),
			new Piece(this, Team.WHITE, 5, 1, PieceType.PAWN),
			new Piece(this, Team.WHITE, 6, 1, PieceType.PAWN),
			new Piece(this, Team.WHITE, 7, 1, PieceType.PAWN),
			
			new Piece(this, Team.WHITE, 0, 0, PieceType.ROCK),
			new Piece(this, Team.WHITE, 1, 0, PieceType.KNIGHT),
			new Piece(this, Team.WHITE, 2, 0, PieceType.BISHOP),
			new Piece(this, Team.WHITE, 3, 0, PieceType.QUEEN),
			new Piece(this, Team.WHITE, 4, 0, PieceType.KING),
			new Piece(this, Team.WHITE, 5, 0, PieceType.BISHOP),
			new Piece(this, Team.WHITE, 6, 0, PieceType.KNIGHT),
			new Piece(this, Team.WHITE, 7, 0, PieceType.ROCK)
		));
		
	}
	
	public Team getNextTeamToMove(){
		return nextTeamToMove;
	}
	
	public boolean movePieceTo(Piece piece, Position moveToPosition){
		
		// Don't accept moving a piece in the wrong team.
		if(nextTeamToMove != piece.team){
			return false;
		}
		
		// Don't accept moving a piece to a square it can't move to.
		var validMoveToPositions = piece.getValidMoveToPositions();
		
		if(!validMoveToPositions.contains(moveToPosition)){
			return false;
		}
		
		var pieceAtDestination = getPieceInSquare(moveToPosition.getX(), moveToPosition.getY());
		if(pieceAtDestination != null){
			pieces.remove(pieceAtDestination);
		}
		
		piece.xPosition = moveToPosition.getX();
		piece.yPosition = moveToPosition.getY();
		
		if(piece.shouldTurnIntoOtherType()){
			piece.turnInto(PieceType.QUEEN); // TODO: Don't hardcode queen, ask user.
		}
		
		changeTurn();
		
		return true;
		
	}
	
	public void changeTurn(){
		if(this.nextTeamToMove == Team.WHITE){
			this.nextTeamToMove = Team.BLACK;
		}else{
			this.nextTeamToMove = Team.WHITE;
		}
	}
	
	public Piece getPieceInSquare(int x, int y){
		
		for(var piece : pieces){
			if(piece.xPosition == x && piece.yPosition == y){
				return piece;
			}
		}
		
		return null;
	}
	
	public boolean isSquareFree(int x, int y){
		return getPieceInSquare(x, y) == null;
	}
	
}
