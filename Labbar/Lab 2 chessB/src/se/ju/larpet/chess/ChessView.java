package se.ju.larpet.chess;

import java.awt.Canvas;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.Font;
import java.awt.Color;
import java.awt.Graphics;
import javax.swing.JFrame;

public class ChessView extends JFrame{
	
	public ChessView(Chess chess){
		super("Chess");
		
		var chessCanvas = new ChessCanvas(chess, 400);
		
		add(chessCanvas);
		pack();
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
		
	}
	
	private static class ChessCanvas extends Canvas{
		
		private Chess chess;
		private Piece selectedPiece;
		
		public ChessCanvas(Chess chess, int size) {
			
			this.chess = chess;
			
			setSize(size, size);
			
			addMouseListener(new MouseAdapter(){
				public void mousePressed(MouseEvent event){
					
					var clickedBoardX = viewXToBoardX(event.getX());
					var clickedBoardY = viewYToBoardY(event.getY());
					
					if(selectedPiece == null){
						selectedPiece = chess.getPieceInSquare(
							clickedBoardX,
							clickedBoardY
						);
					}else if(selectedPiece.hasPosition(clickedBoardX, clickedBoardY)){
						selectedPiece = null;
					}else{
						var moveToPosition = new Position(
							clickedBoardX,
							clickedBoardY
						);
						var wasMoveValid = chess.movePieceTo(selectedPiece, moveToPosition);
						selectedPiece = null;
						
						if(!wasMoveValid){
							// TODO: Display error message to user.
							System.out.println("That move is invalid.");
						}
						
					}
					
					repaint();
					
				}
			});
			
		}
		
		private int getSquareSize(){
			return Math.min(
				getWidth(),
				getHeight()
			) / 8;
		}
		
		private int boardXToViewX(int boardX){
			return boardX * getSquareSize();
		}
		
		private int boardYToViewY(int boardY){
			return (7 - boardY) * getSquareSize();
		}
		
		private int viewXToBoardX(int viewX){
			return (int) Math.floor(
				viewX / getSquareSize()
			);
		}
		
		private int viewYToBoardY(int viewY){
			return (int) Math.floor(
				(getHeight() - viewY) / getSquareSize()
			);
		}
		
		public void paint(Graphics graphics) {
			
			var squareSize = getSquareSize();
			
			// Draw the squares.
			for(var x=0; x<8; x++){
				for(var y=0; y<8; y++){
					
					if(selectedPiece != null && selectedPiece.hasPosition(x, y)){
						graphics.setColor(Color.GREEN);
					}else if(x % 2 == y % 2){
						graphics.setColor(Color.WHITE);
					}else{
						graphics.setColor(Color.BLACK);
					}
					
					graphics.fillRect(
						boardXToViewX(x),
						boardYToViewY(y),
						squareSize,
						squareSize
					);
					
				}
			}
			
			// Draw all pieces.
			graphics.setFont(new Font(
				"TimesRoman",
				Font.PLAIN,
				(int) Math.floor(squareSize*0.8)
			));
			
			for(var piece: chess.pieces){
				
				graphics.setColor(
					(piece.team == Team.WHITE) ? Color.ORANGE : Color.GRAY
				);
				
				graphics.drawString(
					String.valueOf(piece.getLetter()),
					(int) (boardXToViewX(piece.xPosition) + 12),
					(int) (boardYToViewY(piece.yPosition - 1) - 12)
				);
			}
			
		}
		
	}
	
}
