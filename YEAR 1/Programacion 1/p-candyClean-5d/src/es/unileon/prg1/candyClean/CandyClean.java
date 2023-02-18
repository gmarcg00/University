package es.unileon.prg1.candyClean;

import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;


/**
 * @version1.0
 * @author Javier Fernandez Tejerina
 */

public class CandyClean {
	
	private static final Logger logger=LogManager.getLogger(CandyClean.class);

	private Board board;
	/**
	 * 
	 * @param size
	 * @param numcolors
	 * @throws CandyCleanException
	 */
	public CandyClean(int size, int numcolors) throws CandyCleanException{
		logger.info("Crea un objeto de tipo tablero");
		board = new Board(size, numcolors); // crea el tablero
	}

	/**
	 * 
	 * @param row
	 * @param column
	 */
	public void selectBox(int row, int column) {
		logger.debug("Elimina una casilla");
		board.removeBox(row, column); // borra fila
	}
	

	public boolean checkWin() { // comprueba si el jugador a ganado
		logger.debug("Comprueba si se ha ganado la partida");
		Box[][] boxes = board.getBoxes(); // llamada a tablero para obtener el tablero
		Color negro = new Color(0);
		for (int i = 0; i < boxes.length; i++) {
			logger.trace("entrando en un for");
			for (int j = 0; j < boxes.length; j++) {
				logger.trace("entrando en un for");
				if (!boxes[i][j].getColor().equals(negro)) { // comprueba si el tablero esta en negro
					logger.trace("entrando en un if");
					return false; // no lo esta: sigue jugando
				}
			}
		}
		return true; // esta negro: a ganado
	}

	public boolean checkLoose() { // comprueba si el jugador a perdido
		logger.debug("Comprueba si se ha perdido la partida");
		Box[][] boxes = board.getBoxes();
		Box blackBox = new Box(BackgroundColor.BLACK);
		for (int i = 0; i < boxes.length; i++) {
			logger.trace("entrando en un for");
			for (int j = 0; j < boxes.length - 1; j++) {// el tablero no esta en negro pero puede seguir jugando (aun
				logger.trace("entrando en un for");			// hay casillas iguales juntos)
				if (!boxes[i][j].isSame(blackBox) && boxes[i][j].isSame(boxes[i][j + 1])) {
					logger.trace("entrando en un if");
					return false;
				}
			}
		}
		return true; // el tablero no esta en negro pero no hay mas casillas iguales juntas: a
						// perdido
	}
	
	public Board getBoard() {
		return this.board;
	}
	
	public void setBoard(Board board) {
		this.board =board;
		
		
	}

	
// to string de candyClean --> to string de tablero
	@Override
	public String toString() {
		
		return board.toString();
		
	}

}
