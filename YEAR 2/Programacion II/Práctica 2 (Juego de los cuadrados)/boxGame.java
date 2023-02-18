package juegoCuadrados;

import java.util.ArrayList;
import java.util.Scanner;

public class boxGame {
	
	private boolean up;
	private boolean down;
	private boolean left;
	private boolean right;
	
	public boxGame(boolean up, boolean down ,boolean left, boolean right) {
		this.up=up;
		this.down=down;
		this.left=left;
		this.right=right;
		
	}
	
	public static void main (String[] args) {
		Scanner input =new Scanner(System.in);
		int count=1;
		
		while(input.hasNext()) {
			newList(input,count);
			count++;
			if(input.hasNext()) {
				System.out.println();
				System.out.println("******************************");
				System.out.println();				
			}		
		}
	}
	
	public static void newList(Scanner input,int count) {
		
		int size=Integer.parseInt(input.nextLine());
		int lines=Integer.parseInt(input.nextLine());
		int length=size-1;
		
		ArrayList<String> list =new ArrayList<String>();
		
		boxGame [][] board=new boxGame[size][size];
		newBoard(board);
			
		
		input(list,lines,input);
		board=insertLines(list,lines,board);
		int [] results=new int[length];
		
		for(int i=length;i>0;i--) {
			results=findBoxes(board,i,results);
		}
		
		printOutput(results,count);
	}
	
	public static void printOutput(int[] results, int count) {
		int empty=0;
		System.out.println("Problema nº "+count);
		System.out.println();
		for(int i=0;i<results.length-1;i++) {
			if(results[i]!=0) {
				empty++;
				System.out.println(results[i]+" cuadrado(s) de tamaño "+(i+1));
			}
		}
		
		if(empty==0) {
			System.out.println("No se ha encontrado ningún cuadrado completo.");
		}
		
		
	}
	
	public static void input(ArrayList<String> lista, int lines, Scanner input) {
		for(int i=0;i<lines;i++) {
			lista.add(input.nextLine());
		}
	}
	
	public static void newBoard (boxGame [][] board) {
		for(int i=0;i<board.length;i++) {
			for(int j=0;j<board[i].length;j++) {
				board[i][j]=new boxGame(false,false,false,false);
			}
		}
	}
	
	
	public static int[] findBoxes(boxGame [][] board,int size, int [] result) {
		int count=0;
		for(int i=0;i<board.length;i++) {
			for(int j=0;j<board[i].length;j++) {
				int k=i;
				int l=j;
				count=0;
				
				while(count<size && board[i][j].right) {
					count++;
					j++;
				}
				
				if(count==size) {
					count=0;
					while(count<size && board[i][j].down) {
						count++;
						i++;
					}
					if(count==size) {
						count=0;
						while(count<size && board[i][j].left) {
							count++;
							j--;
						}
							if(count==size) {
								count=0;
								while(count<size && board[i][j].up) {
									count++;
									i--;
								}
								if(count==size) {
									//HAY CUADRADO DEL TAMAÑO N-1
									++result[size-1];
								}
								else {
									i=k;
									j=l;
								}
							}else {
								i=k;
								j=l;
							}
					}else {
						i=k;
						j=l;
					}
				}else {
					i=k;
					j=l;
				}
			}
		}
		return result;
	}
	
	public static boxGame[][] insertLines(ArrayList<String> list, int size, boxGame [][] board) {
		for(int i=0;i<size;i++) {
				if(list.get(i).contains("H")){
					int x=Integer.parseInt(String.valueOf(list.get(i).charAt(2)));
					int y=Integer.parseInt(String.valueOf(list.get(i).charAt(4)));
					
					if(y==board.length) {
						board[x-1][y-2].right=true;//Linea hacia la derecha
						board[x-1][y-1].left=true;//Linea hacia la izquierda
					}else {
						board[x-1][y-1].right=true;//Linea hacia la derecha
						board[x-1][y].left=true;//Linea hacia la izquierda
					}
					
				}else {
					int x=Integer.parseInt(String.valueOf(list.get(i).charAt(2)));
					int y=Integer.parseInt(String.valueOf(list.get(i).charAt(4)));
					
					if(y==board.length) {
						board[y-2][x-1].down=true;//Linea hacia abajo
						board[y-1][x-1].up=true;//Linea hacia arriba
					}else {
						board[y-1][x-1].down=true;//Linea hacia abajo
						board[y][x-1].up=true;//Linea hacia arriba
					}
				}
		}
		return board;
	}
	
	
}
