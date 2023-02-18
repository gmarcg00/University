package es.unileon.prg1.candyClean;

/**
 * @version 1.0
 * @author Guillermo Marcos Garcia
 *
 */

public class MainCandyClean {
	public static void main(String args[]) {
		int size, numColors;
		CandyClean candyclean;
		if (args.length != 2) {// Si el numero de argumentos es incorrecto
			System.out.println("Sintaxis del programa:\n" + "MainCandyClean dimension numColors");
		} else {// Si el numero de argumentos es correcto
			try {
				size = Integer.parseInt(args[0]);
				numColors = Integer.parseInt(args[1]); 
				candyclean = new CandyClean(size, numColors);
				TextUI textUI = new TextUI(candyclean);
				textUI.start();

			} catch (Exception e) {
				System.out.println(e.getMessage());
			}
		}
	}

}