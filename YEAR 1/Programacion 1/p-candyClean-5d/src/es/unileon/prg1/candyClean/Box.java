package es.unileon.prg1.candyClean;

import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;


/**
 * 
 * Esta clase representa las casillas del juego
 * @version 1.0
 * @author Juan Alvaro
 * 
 *
 */
public class Box {
	
	//Declaracion del logger
	private static final Logger logger=LogManager.getLogger(Box.class);

	private Color color;
	/**
	 * 
	 * @param aleatorio
	 */
	public Box(BackgroundColor aleatorio) {
		logger.info("Crea un nuevo Color");

		this.color = new Color(aleatorio);
		

	}
	/**
	 * Marca el color de la casilla
	 * @param color
	 */
	public void setColor(Color color) {
		logger.debug("Establece el color");
		this.color = color;

	}
 
	/**
	 * Este metodo devuelve el color de la casilla
	 * @return Color
	 */
	public Color getColor() {
		logger.debug("Devuelve el color");

		return color;
		

	}

	/**
	 * Borra la casilla poniendo el color negro
	 */
	public void removeBox() {
		logger.info("Establece a negro el color de la casilla");
		this.color = new Color(BackgroundColor.BLACK);

	}

	/**
	 * 
	 * Este metodo compara el color de las casillas
	 * @param Box
	 * @return boolean
	 */
	public boolean isSame(Box another) {
		logger.info("Comprueba si dos colores son iguales");

		if (this.color.equals(another.getColor())) {
			logger.trace("Entrando en un if");
			return true;
		}else
			return false;
	}

	@Override
	public String toString() {
		return color.toString();
	}

}
