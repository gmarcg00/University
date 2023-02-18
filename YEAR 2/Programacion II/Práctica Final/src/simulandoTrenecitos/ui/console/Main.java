package simulandoTrenecitos.ui.console;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import simulandoTrenecitos.logic.Train;
import simulandoTrenecitos.logic.MovimientoTren;
import simulandoTrenecitos.logic.Trenecitos;


public class Main {

	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);
		int size=30;
		while(input.hasNext()) {
			Trenecitos juego=new Trenecitos(size);
			if(readInput(input,juego)) {
				juego.play();
				System.out.println(juego.getInfoGrid());
			}else {
				System.out.println("Conjunto de trenes incorrecto.");
				System.exit(0);
			}

		}	
	}
	
	public static boolean readInput(Scanner input,Trenecitos juego) {
		ArrayList<String> list = new ArrayList<String>();
		ArrayList<Integer> numbers = new ArrayList<Integer>();
		int numberTrains = Integer.parseInt(input.nextLine());
		int suma=0;
		if (numberTrains > Trenecitos.MAX_NUMBERS_TRAINS) {
			// Numero de trenes incorrecto
			return false;
		}
		
		numbers = input(list, numberTrains, input);
		
		if(Trenecitos.isMaxUnits(suma,numbers)) {
			int j=0;
			List<Train> trenes=new ArrayList<Train>();
			for(String str :list) {
				MovimientoTren tmp = null;
				switch(str.split(" ")[0]) {
					case "D":
						tmp=MovimientoTren.DERECHA;
						break;
					case "I":
						tmp=MovimientoTren.IZQUIERDA;
						break;
					case "A":
						tmp=MovimientoTren.ARRIBA;
						break;
					case "B":
						tmp=MovimientoTren.ABAJO;
						break;
					
				}
				int length = numbers.get(j++);
				int x = numbers.get(j++);
				int y = numbers.get(j++);
				trenes.add(new Train(tmp,length,x,y));
				
			}
			
			return juego.insertTrains(trenes);
		}
		
		return false;
	
	}
	
	public static ArrayList<Integer> input(ArrayList<String> list, int size, Scanner input) {
		for (int i = 0; i < size; i++) {
			list.add(input.nextLine());
		}

		ArrayList<Integer> enteros = new ArrayList<Integer>();

		for (String str : list) {
			String[] chars = str.split(" ");
			for(int i=1;i<chars.length;i++) {
				enteros.add(Integer.parseInt(chars[i]));
			}
		}

		return enteros;
	}

}
