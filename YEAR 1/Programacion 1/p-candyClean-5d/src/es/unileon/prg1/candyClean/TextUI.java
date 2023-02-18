package es.unileon.prg1.candyClean;

import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;

/**
 * @version1.0
 * @author Guillermo Marcos Garcia, Javier Fernandez Tejerina
 */

public class TextUI {
	
	private static final Logger logger=LogManager.getLogger(TextUI.class);

	private CandyClean candyclean;

	public TextUI(CandyClean candyclean) {
		logger.debug("Crea un objeto CandyClean");
		this.candyclean = candyclean;
	}

	public void start() {
		int option = -1;
		boolean exit = false;
		do {
			logger.trace("Entrando en un  do while");
			do {
				logger.trace("Entrando en un  do while");
				System.out.println("-----------------------------");
				System.out.println("----------CandyClean----------");
				System.out.println("-----------------------------");
				System.out.println("Select an option:");
				System.out.println("1 - Play");
				System.out.println("2 - Exit");
				option = Teclado.readInteger();
			} while (option != 1 && option != 2);
			
			try {
				switch (option) {
				case 1:
					option1();
					exit = true;
					// El usuario ha seleccionado jugar
					break;
				case 2:
					exit = true;
					System.out.println("-----------BYE BYE-----------");
					break;
				}
			} catch (CandyCleanException e) {
				
				System.out.println(e.getMessage());
				
			}

		} while (exit != true);
		
	}

	public void option1() throws CandyCleanException {
		logger.debug("Ejecuta la opcion de jugar");
		while (candyclean.checkWin() == false && candyclean.checkLoose() == false) {
			System.out.println(candyclean.toString());
			System.out.println("Introduce la fila");
			int row = Teclado.readInteger();
			System.out.println("Introduce la columna");
			int column = Teclado.readInteger();
			if (candyclean.getBoard().removeBox(row, column)) {
				logger.trace("Entrando en un if");
				candyclean.getBoard().compact();
			} else {
				System.out.println("That movement doesn't work");
			}
			System.out.println();
		}
		if (candyclean.checkWin() == true) {
			logger.trace("Entrando en un if");
			System.out.println(candyclean.toString());
			System.out.println("You won!");
		}
		if (candyclean.checkLoose() == true) {
			logger.trace("Entrando en un if");
			System.out.println(candyclean.toString());
			System.out.println("You lost!");
		}

	}

}