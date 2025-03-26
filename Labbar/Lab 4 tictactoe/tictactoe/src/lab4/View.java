package lab4;

import java.awt. *;
import java.awt.event. *;
import javax.swing.*;

public class View extends JFrame implements ActionListener{
	private static final long serialVersionUID = 1L;
	private JButton[][] boxes;
	private JButton reset;
	private JLabel messageLabel;
	private final int columns = 3;
	private final int rows = 3;
	private Controller _controller;
	
	public View() {
		boxes = new JButton[rows][columns];
		this.setSize(500, 300);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setLayout(new BorderLayout());
		JPanel board = new JPanel(new GridLayout(rows,columns)); 
		messageLabel = new JLabel();
		
		reset = new JButton();
		reset.setText("Reset");
		reset.setName("Reset");
		reset.addActionListener(this);
		
		for(int y = 0; y < columns; y++) {
			for(int x = 0; x < rows; x++) {
				boxes[x][y] = new JButton();
				boxes[x][y].setName(x+","+ y);
				boxes[x][y].addActionListener(this);
				board.add(boxes[x][y]);
			}
		}
		this.add(messageLabel, BorderLayout.NORTH);
		this.add(board, BorderLayout.CENTER);
		this.add(reset, BorderLayout.SOUTH);
		
		this.setTitle("          TicTacToe Game");
		this.setVisible(true);
	}
	
	public void addEventListener(Controller newController) {
		this._controller = newController;
	}
	
	public void sendClicks(String player, int xAxis, int yAxis) {
		boxes[xAxis][yAxis].setText(player);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		JButton clicked = (JButton) e.getSource();
		String buttonName = clicked.getName();		
		int player = _controller.getPlayer();
		_controller.updateClicks(buttonName, player);		
	}

	public void updateView(String message){
		messageLabel.setText(message);
	}	
}