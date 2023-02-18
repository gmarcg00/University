package es.unileon.prg1.candyClean;

import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;

/**
 * @version 1.0
 * @author Guillermo, Javier 
 */

public class Board {
	

	//Declaracion del logger
	private static final Logger logger=LogManager.getLogger(Board.class);

	private Box[][] boxes;// Atributo

	private int numColors;
	/**
	 * 
	 * @param size
	 * @param numColors
	 * @throws CandyCleanException
	 */

	public Board(int size, int numColors) throws CandyCleanException{// Crea el tablero
		logger.info("Crea el tablero");
		StringBuilder salida=new StringBuilder();
		if(size<5) {
			salida.append("El tamano es demasiado pequeno\n");
		}
		if(numColors<3 || numColors>7) {
			salida.append("El numero de colores es incorrecto");
		}
		if(salida.length()>0) {
			throw new CandyCleanException(salida.toString());
		}
		
		boxes = new Box[size][size];
		this.numColors = numColors;
		newBoard();
	}
	
	public void newBoard() {// Inicializa el tablero
		logger.debug("Inicializa el tablero");
		BackgroundColor[] colores = BackgroundColor.values();// Guardo en colores la lista de colores del enum
		int numColoresValido = Math.min(numColors, colores.length);
		for (int i = 0; i < boxes.length; i++) {
			logger.trace("Entrando en un for");
			for (int j = 0; j < boxes[i].length; j++) {
				logger.trace("Entrando en un for");
				int aleatorio = (int) (Math.random() * numColors) + 1;
				boxes[i][j] = new Box(BackgroundColor.values()[aleatorio]);
			}
		}
	}
	/**
	 * 
	 * @param row
	 * @param column
	 * @return boolean
	 */
	public boolean removeBox(int row, int column) {// elimina las filas que sean iguales
		logger.debug("Establece a negro las casillas contiguas del mismo color");
		boolean reference = false;
		boolean seguidas = true;
		if (row >= 0 && row < boxes.length && column >= 0 && column < boxes[row].length) {// Compruebo que la casilla
			logger.trace("Entrando en un if");												// que me pasan esta dentro
																							// del tablero

			for (int j = column - 1; j >= 0 && seguidas == true; j--) {// Compruebo las casillas de la izquierda
				logger.trace("Entrando en un for");
				if (boxes[row][j].getColor().equals(boxes[row][column].getColor())) {
					logger.trace("Entrando en un if");
					boxes[row][j].setColor(new Color(BackgroundColor.BLACK));
					reference = true;
				} else {
					seguidas = false;
				}
			}
			seguidas = true;
			for (int j = column + 1; j < boxes[row].length && seguidas == true; j++) {// Compruebo las casillas de la
				logger.trace("Entrando en un for");									// derecha
				if (boxes[row][j].getColor().equals(boxes[row][column].getColor())) {
					logger.trace("Entrando en un if");
					boxes[row][j].setColor(new Color(BackgroundColor.BLACK));
					reference = true;
				} else {
					seguidas = false;
				}
			}
			if (reference) {
				logger.trace("Entrando en un if");
				boxes[row][column].setColor(new Color(BackgroundColor.BLACK));
			}

		}
		return reference;

	}
	

	public void compact() {
		logger.debug("Compacta el tablero");
		Box blackBox = new Box(BackgroundColor.BLACK);
		for (int i = boxes.length - 1; i > 0; i--) {
			logger.trace("Entrando en un for");
			for (int j = 0; j < boxes[i].length; j++) {
				logger.trace("Entrando en un for");
				if (boxes[i][j].isSame(blackBox)) {
					logger.trace("Entrando en un if");
					boxes[i][j].setColor(boxes[i - 1][j].getColor());
					boxes[i - 1][j].removeBox();
				}
			}
		}
	}
	


	public StringBuffer printBoard() {// Imprime el tablero
		StringBuffer buffer= new StringBuffer (" |0|");
		for (int k = 1; k < boxes.length; k++) {
			 buffer.append( String.valueOf(k) + "|");
		}
		buffer.append("\n");
		for (int i = 0; i < boxes.length; i++) {
			buffer.append(String.valueOf(i));
			for (int j = 0; j < boxes.length; j++) {
				buffer.append(boxes[i][j].toString());
			}
			buffer.append("\n");
		}
		return buffer;
	}

	/**
	 * 
	 * @return
	 */
	public Box[][] getBoxes() {
		logger.debug("Devuelve el tablero");
		return boxes;
	}
	
	/**
	 * @param
	 * @return
	 */
	@Override // Sirve para sobreescribir encima del toString que hereda de la clase objeto
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("  ");
		for (int k=0;k<boxes.length;k++){
			sb.append(String.valueOf(k)+"|");
		}
		sb.append("\n");
		for(int i = 0; i < boxes.length; i++) {
			sb.append(String.valueOf(i));
			for (int j = 0; j < boxes[i].length; j++) {
				sb.append(boxes[i][j]);
			}
			sb.append("\n");
		}

		return sb.toString();
	}
}
