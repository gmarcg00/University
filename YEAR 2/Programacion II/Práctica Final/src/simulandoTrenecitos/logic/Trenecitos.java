package simulandoTrenecitos.logic;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import simulandoTrenecitos.logic.model.Board;
import simulandoTrenecitos.logic.model.BoxStatus;

public class Trenecitos {

	private Board board;
	public final static int MAX_NUMBERS_TRAINS = 10;
	private final static int MAX_NUMBER_UNITS=100;

	public Trenecitos(int size) {
		board = new Board(size);
	}
	
	public Trenecitos(int row, int column) {
		board = new Board(row,column);
	}
	
	 
	 public BoxStatus getCellStatus (int row, int column) {
		 return board.getBoxStatus(row,column);
	 }
	 
	 public boolean getHeadInfo(int row, int column) {
		 return board.getHeadStatus(row, column);
	 }
	 
	 public boolean getInfoLocked(int row, int column) {
		 return board.getInfoLocked(row, column);
	 }
	 
	 public void moveTrainDown() {
		  board.moveDown();
	 }
	
	
	public static boolean isMaxUnits(int suma,ArrayList<Integer> numbers) {
		for(int i=0;i<numbers.size();i=i+3) {
			suma+=numbers.get(i);
		}
		if(suma>MAX_NUMBER_UNITS)
			return false;
		else
			return true;
	}
	
	public boolean insertTrains(List<Train> list) {
		for (int i = 0; i < list.size(); i++) {
			Train train=list.get(i);
			if (train.getMove().equals(MovimientoTren.DERECHA)) {
				if(!board.insertLeft(train.getLength(), train.getX(), train.getY())) {
					return false;
				}
			}else if (train.getMove().equals(MovimientoTren.IZQUIERDA)) {
				if(!board.insertRight(train.getLength(), train.getX(), train.getY())) {
					return false;
				}
			}else if (train.getMove().equals(MovimientoTren.ARRIBA)) {
				if(!board.insertDown(train.getLength(), train.getX(), train.getY())) {
					return false;
				}
			}else if (train.getMove().equals(MovimientoTren.ABAJO)) {
				if (!board.insertUp(train.getLength(), train.getX(), train.getY())) {
					return false;
				}
		
			}else {
				System.out.println("Conjunto de trenes incorrecto");
				return false;
			}

		}
		return true;
	}
	

	public String getInfoGrid() {
		return board.getInfoBoard();
	}
	
	public void play() {
		while(!board.endGame()) {
			iteration();
		}
		board.putSolution();
	}
	
	private void iteration() {
		board.moveDown();
		board.moveUp();
		board.moveLeft();
		board.moveRight();
	}

	public String toString() {
		return board.toString();
	}

	public void unlockTrain(int i, int j) {
		board.changeLockedBoard(true);
		if(getCellStatus(i,j).equals(BoxStatus.DOWN)) {
			board.unlockDown(i, j);
			
		}
		if(getCellStatus(i,j).equals(BoxStatus.UP)) {
			board.unlockUp(i, j);
	
		}
		if(getCellStatus(i,j).equals(BoxStatus.LEFT)) {
			board.unlockLeft(i, j);
			
		}
		if(getCellStatus(i,j).equals(BoxStatus.RIGHT)) {
			board.unlockRight(i, j);
		
		}
		iteration();
		board.changeLockedBoard(false);
		if(board.endGame()) {
			board.putSolution();
		}
	}
	
	public boolean removeTrains(int i, int j) {
		//board.changeLockedBoard(true);
		if(board.checkInput(i, j, board.getBoardLength())) {
			if(getCellStatus(i,j).equals(BoxStatus.DOWN)) {
				board.removeDown(i, j);
				return true;
				
			}
		}
		if(board.checkInput(i, j, board.getBoardLength())) {
			if(getCellStatus(i,j).equals(BoxStatus.UP)) {
				board.removeUp(i, j);
				return true;
		}
		
	
		}
		if(board.checkInput(i, j, board.getBoardLength())) {
			if(getCellStatus(i,j).equals(BoxStatus.LEFT)) {
				board.removeLeft(i, j);
				return true;
				
			}
		}
		
		if(board.checkInput(i, j, board.getBoardLength())) {
			if(getCellStatus(i,j).equals(BoxStatus.RIGHT)) {
				board.removeRight(i, j);
				return true;
			
			}
		}
		
		
		return false;
	}
	
	
	
	
	
	public String getOutput() {
		return board.getOutputTrains();
	}

	public boolean isEndedGame() {
		if(board.endGame()) {
			board.putSolution();
		}
		
		return board.endGame();
	}

	public void nextRound() {
		iteration();
	}

}
