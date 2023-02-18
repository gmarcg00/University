import java.util.Scanner;

public class maximoMinimo {
	
	public static void main (String[] args) {
		int [] array=read();
		int min=getMin(array,Integer.MAX_VALUE,0,array.length-1);
		int max=getMax(array,Integer.MIN_VALUE,0,array.length-1);
		int minNotFound=notFoundMin(array,min+1,0);
		int maxNotFound=notFoundMax(array,max-1,array.length-1);
		
		if(sameNumber(array,0))
			System.out.println("Secuencia Completa");
		else {
			if(minNotFound==max && maxNotFound==min)
				System.out.println("Secuencia Completa");
			else
				System.out.println(minNotFound+" "+maxNotFound);
		}
		
	
	}
	
	public static boolean sameNumber(int [] array, int count) {
		if(count+1==array.length-1) {
			if(array[count]==array[count+1]) {
				return true;
			}else {
				return false;
			}	
		}
		if(array[count]!=array[count+1]) {
			return false;
		}else {
			return sameNumber(array,++count);
		}
	}
	
	public static int [] read() {
		Scanner input=new Scanner(System.in);
		int tam=input.nextInt();
		int i=0;
		int [] array=new int [tam];
		array=readInput(input,array,i);
		
		return array;
		
	}
	
	public static int[] readInput(Scanner input, int [] vector, int count) {
		if(count<vector.length) {
			vector[count]=input.nextInt();
			 vector=readInput(input,vector,++count);
		}
		return vector;
	}
	
	public static int getMin(int [] array,int minValue, int begin, int end) {
		if(begin==end) {
			return array[begin];
		}
		
		int middleLeft=(begin+end)/2;
		int middleRight=middleLeft+1;
		
		int minimumLeft=getMin(array,minValue,begin,middleLeft);
		int minimumRight=getMin(array,minValue,middleRight,end);
		
		if(minValue<minimumLeft) {
			if(minValue<minimumRight) {
				return minValue;
			}else {
				return minimumRight;
			}
		}else {
			if(minimumLeft<minimumRight) {
				return minimumLeft;
			}else {
				return minimumRight;
			}
		}
	}
	
	public static int getMax(int [] array,int maxValue, int begin, int end) {
		if(begin==end) {
			return array[begin];
		}
		
		int middleLeft=(begin+end)/2;
		int middleRight=middleLeft+1;
		
		int maxLeft=getMax(array,maxValue,begin,middleLeft);
		int maxRight=getMax(array,maxValue,middleRight,end);
		
		if(maxValue>maxLeft) {
			if(maxValue>maxRight) {
				return maxValue;
			}else {
				return maxRight;
			}
		}else {
			if(maxLeft>maxRight) {
				return maxLeft;
			}else {
				return maxRight;
			}
		}
	}
	
	public static int notFoundMin(int [] array, int min, int index) {
		if(index==array.length-1) {
			if(array[index]==min) {
				if(array[index]==getMax(array,Integer.MIN_VALUE,0,array.length-1))
					return min;
				else
					return notFoundMin(array,min+1,0);
			}
			return min;
		}
		if(array[index]==min) {
			if(array[index+1]==min)
				return notFoundMin(array,min,++index);
			else {
				if(min==getMax(array,Integer.MIN_VALUE,0,array.length-1))
					return min;
				else
					return notFoundMin(array,min+1,0);
			}	
		}else {
			return notFoundMin(array,min,++index);
		}
		
	}
	public static int notFoundMax(int [] array, int max, int index) {
		if(index==0) {
			if(array[index]==max) {
				if(array[index]==getMin(array,Integer.MAX_VALUE,0,array.length-1))
					return max;
				else
					return notFoundMax(array,max-1,array.length-1);
			}
			return max;
		}
		if(array[index]==max) {
			if(array[index-1]==max)
				return notFoundMax(array,max,--index);
			else {
				if(max==getMin(array,Integer.MAX_VALUE,0,array.length-1)) {
					return max;
				}else {
					return notFoundMax(array,max-1,array.length-1);
				}
			}	
		}else {
			return notFoundMax(array,max,--index);
		}
		
	}
	
}
