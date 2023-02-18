package simulandoTrenecitos.ui.gui.views;

import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import java.awt.BorderLayout;
import java.awt.Color;

import javax.swing.ScrollPaneConstants;
import javax.swing.SwingConstants;

import simulandoTrenecitos.logic.Train;
import simulandoTrenecitos.logic.MovimientoTren;
import simulandoTrenecitos.logic.Trenecitos;
import simulandoTrenecitos.logic.model.BoxStatus;
import simulandoTrenecitos.ui.gui.MainWindow;
import simulandoTrenecitos.ui.gui.componentes.Casilla;

public class BoardPanel extends JPanel {

	private Casilla board[][];
	private MainWindow ventana;
	private JPanel panel;

	public BoardPanel(MainWindow frame, int row, int column) {
		this.ventana = frame;

		setLayout(new BorderLayout(0, 0));
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
		add(scrollPane,BorderLayout.CENTER);
		
		JPanel panel_1 = new JPanel();
		
		panel_1.setLayout(new GridLayout(1, column, 0, 0));
		
		for(int i=0;i<column;i++) {
			JLabel label=new JLabel(""+i,SwingConstants.CENTER);
			panel_1.add(label);
		}
		
		JPanel panel_2 = new JPanel();
		
		panel_2.setLayout(new GridLayout(row, 1, 0, 0));
		
		for(int j=row-1;j>=0;j--) {
			JLabel label=new JLabel(""+j);
			panel_2.add(label);
		}

		

		panel = new JPanel();
		//spanel.setLayout(new GridLayout(5, 5, 0, 0));
		panel.setLayout(new GridLayout(row, column, 0, 0));
		
		JPanel panel2=new JPanel();
		panel2.setLayout(new BorderLayout());
		
		panel2.add(panel_1, BorderLayout.NORTH);
		panel2.add(panel_2, BorderLayout.WEST);
		panel2.add(panel, BorderLayout.CENTER);
		scrollPane.setViewportView(panel2);
		
		
		ventana.getEdit().setEnabled(true);
		ventana.getSimulacion().setEnabled(true);

		initBoard(row, column);
		repaintBoard();

	}

	public void initBoard(int row, int column) {
		board = new Casilla[row][column];
		for (int i = board.length - 1; i >= 0; i--) {
			for (int j = 0; j < board[i].length; j++) {
				board[i][j] = new Casilla(i, j, ".");
				board[i][j].addActionListener(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent arg0) {
						Casilla box = (Casilla) arg0.getSource();
						int x = box.getBoardX();
						int y = box.getBoardY();
						if(ventana.juego.getHeadInfo(x,y)) {
							ventana.juego.unlockTrain(x, y);
							BoardPanel.this.repaint();
						}
						// ventana.mnMover.setEnabled(true);
						

					}

				});
				panel.add(board[i][j]);

			}
		}
	}


	@Override
	public void repaint() {
		super.repaint();
		repaintBoard();
	}

	public void repaintBoard() {
		if (board != null) {
			for (int i = 0; i < board.length; i++) {
				for (int j = 0; j < board[i].length; j++) {
					String str = " ";
					switch (ventana.juego.getCellStatus(i, j)) {
					case EMPTY:
						str = ".";
						break;
					case UP:
						str = "A";
						board[i][j].setBackground(Color.CYAN);
						break;
					case DOWN:
						str = "B";
						board[i][j].setBackground(Color.GRAY);
						break;
					case LEFT:
						str = "I";
						board[i][j].setBackground(Color.GREEN);
						break;
					case RIGHT:
						str = "D";
						board[i][j].setBackground(Color.PINK);
						break;
					case COLLISION:
						str = "X";
						break;
					}
					board[i][j].setText(str);
					if (ventana.juego.getHeadInfo(i, j)) {
						board[i][j].setBackground(Color.WHITE);
					}else {
						if(str.equals("A")) {
							board[i][j].setBackground(Color.cyan);
						}
						if(str.equals("B")) {
							board[i][j].setBackground(Color.green);
						}
						if(str.equals("I")) {
							board[i][j].setBackground(Color.ORANGE);
						}
						if(str.equals("D")) {
							board[i][j].setBackground(Color.PINK);
						}
						if(str.equals("X")) {
							board[i][j].setBackground(Color.black);
						}
						if(str.equals(".")) {
							board[i][j].setBackground(null);
						}
					}	
				}	
			}
		}
	}

}
