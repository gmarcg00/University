
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;



public class palillos {

	int num;
	boolean used;
	static ArrayList<Integer> soluciones = new ArrayList<Integer>();

	public palillos(int num, boolean used) {
		this.num = num;
		this.used = used;
		
	}

	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);
		 while (input.hasNext()) {
			List<Integer> list = readInput(input);
			
			if(list==null)
				break;

            int size = list.size();
            int addition=0;
            addition=addition(list,addition);
            palillos array[] = new palillos[size];
			newArray(list, size, array);
			int maxNum = getMax(list, size);
			ArrayList<Integer> dividers=new ArrayList<Integer>();
			dividers=calculateDividers(addition,maxNum,dividers);
			
			//System.out.println(dividers);

			int result = 0;
			soluciones=new ArrayList<Integer>();
			for(int i=0;i<dividers.size();i++) {
				//System.out.println("Probando solucion "+ i);
				int res=getSolRec(array, result, 2, 2, dividers.get(i));
				if(res!=-1) {
					System.out.println(res);
					break;
				}
			}
		 }
		input.close();
	}
	
	public static int addition (List<Integer> list,int addition) {
		for(int i=0;i<list.size();i++) {
			addition+=list.get(i);
		}
		return addition;
	}
	
	public static ArrayList <Integer> calculateDividers(int addition,int maxNum,ArrayList <Integer> dividers) {
		for(int i=(maxNum+1);i<addition;i++) {
			if(addition%i==0) {
				dividers.add(i);
			}
		}
		return dividers;
	}

	public static List<Integer> readInput(Scanner input) {
		List<Integer> list = new ArrayList<Integer>();
		int size = input.nextInt();
		if (size != 0) {
			for (int i = 0; i < size; i++) {
				list.add(input.nextInt());
			}
		}else {
			return null;
		}
		return list;
	}
	

	public static int getSolRec(palillos array[], int suma, int count, int initialCount, int maxNum) {
		int ret=-1;
		
		if(maxNum>=50) {
			return ret;
		}
		if (count >= array.length-1) {
			return ret;
		}
		if (count ==0 && suma==maxNum && checkSolution(array) ) {
			//System.out.println("Solucion: [" + String.join(",", Arrays.toString(soluciones.toArray())) + "]");
			return maxNum;
		}
		if(!checkSolution(array)) {
			if (count == 0) {
				if (suma == maxNum) {
					
					ret=getSolRec(array, 0, initialCount, initialCount, maxNum);
				}
			} else {
				for (int i = 0; i < array.length; i++) {
					if (!array[i].used) {
						if (suma + array[i].num <= maxNum) {
							suma += array[i].num;
							array[i].used = true;
							soluciones.add(array[i].num);							
							ret=getSolRec(array, suma, count - 1, initialCount, maxNum);
							if(ret!=-1)
								return ret;
							array[i].used = false;
							suma -= array[i].num;
							soluciones.remove(soluciones.size() - 1);
							if (!checkSolution(array)) {
								ret=getSolRec(array, 0, initialCount+1, initialCount + 1, maxNum);
								if(ret!=-1)
									return ret;
							}	
						}
					}
				}
			}
		}
		
		return ret;
	}

	public static palillos[] newArray(List<Integer> list, int size, palillos array[]) {
		for (int i = 0; i < size; i++) {
			array[i] = new palillos(list.get(i), false);
		}
		return array;
	}

	public static int getMax(List<Integer> list, int size) {
		int max = Integer.MIN_VALUE;
		for (int i = 0; i < size; i++) {
			if (list.get(i) > max)
				max = list.get(i);
		}
		return max;
	}

	public static boolean checkSolution(palillos array[]) {
		for (int k = 0; k < array.length; k++) {
			if (!array[k].used)
				return false;
		}
		return true;
	}

}
