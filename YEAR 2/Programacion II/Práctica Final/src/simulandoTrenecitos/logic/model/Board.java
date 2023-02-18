package simulandoTrenecitos.logic.model;

import java.util.ArrayList;
import java.util.List;

import simulandoTrenecitos.logic.MovimientoTren;
import simulandoTrenecitos.logic.Train;

public class Board {

	private Box[][] boxes;
	private List<Integer> list = new ArrayList<Integer>();

	public Board(int size) {
		this(size, size);
	}

	public Board(int row, int column) {
		boxes = new Box[row][column];
		newBoard();
	}

	Board(Board board) {
		this.boxes = new Box[board.boxes.length][board.boxes[0].length];
		for (int i = boxes.length - 1; i >= 0; i--) {
			for (int j = 0; j < boxes[i].length; j++) {
				this.boxes[i][j] = new Box(board.boxes[i][j]);
			}
		}
		this.list = new ArrayList<Integer>(board.list);
	}

	public void printBoard() {
		System.out.println("   0 0 0 0 0 0 0 0 0 0 1 1 1 1 1 1 1 1 1 1 2 2 2 2 2 2 2 2 2 2");
		System.out.println("   0 1 2 3 4 5 6 7 8 9 0 1 2 3 4 5 6 7 8 9 0 1 2 3 4 5 6 7 8 9");

		for (int i = boxes.length - 1; i >= 0; i--) {
			if (i < 10) {
				System.out.print("0" + i);

			} else {
				System.out.print(i);
			}
			for (int j = 0; j < boxes.length; j++) {
				System.out.print(boxes[i][j].toString());
			}
			System.out.println();
		}

		System.out.println();
	}

	private void newBoard() {
		for (int i = boxes.length - 1; i >= 0; i--) {
			for (int j = 0; j < boxes[i].length; j++) {
				boxes[i][j] = new Box(".", false, false);
			}
		}
	}

	public void putSolution() {
		int j = 1;
		for (int i = 0; i < list.size(); i = i + 2) {
			boxes[list.get(i)][list.get(j)].setDireccion("X");
			j += 2;
		}
	}

	public void printHeads() {
		for (int i = boxes.length - 1; i >= 0; i--) {
			for (int j = 0; j < boxes.length; j++) {
				if (boxes[i][j].getHead())
					System.out.print("H");
				else
					System.out.print("o");
			}
			System.out.println();
		}
	}

	public boolean insertUp(int length, int x, int y) {
		boolean incorrect = true;
		if (checkInput((x+length), y, length)) {
			boxes[y][x].setHead(true);
			while (incorrect && length > 0) {
				if (checkInput(x, (y+length), length)) {
					if (!checkCollision(x, y)) {
						boxes[y][x].setDireccion(toString(0));
						length--;
						y++;
					} else {
						System.out.println("Conjunto de trenes incorrecto.");
						incorrect = false;
					}
				} else {
					System.out.println("Conjunto de trenes incorrecto.");
					incorrect = false;
				}
			}
		} else {
			System.out.println("Conjunto de trenes incorrecto.");
			incorrect = false;
		}
		return incorrect;
	}

	public boolean insertDown(int length, int x, int y) {
		boolean incorrect = true;
		if (checkInput((x-length), y, length)) {
			boxes[y][x].setHead(true);
			while (incorrect && length > 0) {
				if (checkInput(x, y, length)) {
					if (!checkCollision(x, y)) {
						boxes[y][x].setDireccion(toString(1));
						length--;
						y--;
					} else {
						System.out.println("Conjunto de trenes incorrecto.");
						incorrect = false;
					}
				} else {
					System.out.println("Conjunto de trenes incorrecto.");
					incorrect = false;
				}
			}
		} else {
			System.out.println("Conjunto de trenes incorrecto.");
			incorrect = false;
		}
		return incorrect;
	}

	public boolean insertLeft(int length, int x, int y) {
		boolean incorrect = true;
		if (checkInput((x-length), y, length)) {
			boxes[y][x].setHead(true);
			while (incorrect && length > 0) {
				if (checkInput(x, (y-length), length)) {
					if (!checkCollision(x, y)) {
						boxes[y][x].setDireccion(toString(3));
						length--;
						x--;
					} else {
						System.out.println("Conjunto de trenes incorrecto.");
						incorrect = false;
					}
				} else {
					System.out.println("Conjunto de trenes incorrecto.");
					incorrect = false;
				}
			}
		} else {
			System.out.println("Conjunto de trenes incorrecto.");
			incorrect = false;
		}
		return incorrect;
	}

	public void changeLockedBoard(boolean locked) {
		for (int i = boxes.length - 1; i >= 0; i--) {
			for (int j = 0; j < boxes[i].length; j++) {
				boxes[i][j].setLocked(locked);
			}
		}
	}

	public boolean insertRight(int length, int x, int y) {
		boolean incorrect = true;
		if (checkInput(x,(y+length), length)) {
			boxes[y][x].setHead(true);
			while (incorrect && length > 0) {
				if (checkInput(x, y, length)) {
					if (!checkCollision(x, y)) {
						boxes[y][x].setDireccion(toString(2));
						length--;
						x++;
					} else {
						System.out.println("Conjunto de trenes incorrecto.");
						incorrect = false;
					}
				} else {
					System.out.println("Conjunto de trenes incorrecto.");
					incorrect = false;
				}
			}
		} else {
			System.out.println("Conjunto de trenes incorrecto.");
			incorrect = false;
		}
		return incorrect;
	}

	public void moveLeft() {
		for (int i = boxes.length - 1; i >= 0; i--) {
			for (int j = 0; j < boxes.length; j++) {
				int k = i;
				if (!boxes[i][j].getLocked()) {
					if (boxes[i][j].getHead() && boxes[i][j].getDirection().equals(toString(2))) {
						if (checkInput(i, j - 1, boxes.length)) {
							if (!checkCollision(j - 1, i)) {
								boxes[i][j - 1].setDireccion(toString(2));
								boxes[i][j - 1].setHead(true);
								boxes[i][j].setDireccion(".");
								boxes[i][j].setHead(false);
							} else {
								// Colision
								list.add(i);
								list.add(j - 1);
								boxes[i][j].setDireccion(".");
								boxes[i][j].setHead(false);
								String dir = boxes[i][j - 1].getDirection();
								// Pongo el punto en la colision
								if (checkInput(i, j - 1, boxes.length)) {
									boxes[i][j - 1].setDireccion(".");
								}
								// Pongo la nueva cabeza del tren que choca
								if (checkInput(i, j + 1, boxes.length)) {
									boxes[i][j + 1].setHead(true);
								}

								boolean newHead = false;

								// Caso tren hacia arriba
								if (dir.equals(toString(1))) {
									// Cabeza con cabeza
									if (boxes[i][j - 1].getHead()) {
										boxes[i][j - 1].setHead(false);
									}
									if (boxes[i + 1][j - 1].getDirection() != ".") { // arriba
										boxes[i - 1][j - 1].setHead(true);
										while (!newHead) {
											if (boxes[i + 1][j - 1].getDirection() != ".") {
												i++;
											} else {
												boxes[i][j - 1].setHead(true);
												newHead = true;
											}
										}
									} else {
										boxes[i - 1][j - 1].setHead(true);
									}
								}
								newHead = false;

								// Caso tren hacia abajo
								if (dir.equals(toString(0))) {
									// Cabeza con cabeza
									if (boxes[i][j - 1].getHead()) {
										boxes[i][j - 1].setHead(false);
									}
									if (boxes[i - 1][j - 1].getDirection().equals(toString(0))) {
										boxes[i + 1][j - 1].setHead(true);
										while (!newHead) {
											if (boxes[i - 1][j - 1].getDirection().equals(toString(0))) {
												i--;
											} else {
												boxes[i][j - 1].setHead(true);
												newHead = true;
											}
										}
									} else {
										boxes[i + 1][j - 1].setHead(true);
									}
								}
							}
						} else {
							boxes[i][j].setHead(false);
							boxes[i][j].setDireccion(".");
							if (boxes[i][j + 1].getDirection().equals(toString(2))) {
								boxes[i][j + 1].setHead(true);
							}
						}

					}
					i = k;
					if (!boxes[i][j].getHead() && boxes[i][j].getDirection().equals(toString(2))) {
						if (!checkCollision(j - 1, i)) {
							boxes[i][j - 1].setDireccion(toString(2));
							boxes[i][j].setDireccion(".");
						}
					}
				}
				
			}
		}
	}

	public void moveRight() {
		for (int i = boxes.length - 1; i >= 0; i--) {
			for (int j = boxes.length - 1; j >= 0; j--) {
				int k = i;
				if (!boxes[i][j].getLocked()) {
					if (boxes[i][j].getHead() && boxes[i][j].getDirection().equals(toString(3))) {
						if (checkInput(i, j + 1, boxes.length)) {
							if (!checkCollision(j + 1, i)) {
								boxes[i][j + 1].setDireccion(toString(3));
								boxes[i][j + 1].setHead(true);
								boxes[i][j].setDireccion(".");
								boxes[i][j].setHead(false);
							} else {
								// Colision
								list.add(i);
								list.add(j + 1);
								boxes[i][j].setDireccion(".");
								boxes[i][j].setHead(false);
								String dir = boxes[i][j + 1].getDirection();
								if (checkInput(i, j - 1, boxes.length)) {
									boxes[i][j - 1].setHead(true);
								}

								if (checkInput(i, j + 1, boxes.length)) {
									boxes[i][j + 1].setDireccion(".");
								}

								boolean newHead = false;

								// Caso tren hacia abajo
								if (dir.equals(toString(0))) {
									// Cabeza con cabeza
									if (boxes[i][j + 1].getHead()) {
										boxes[i][j + 1].setHead(false);
									}
									if (boxes[i - 1][j + 1].getDirection().equals(toString(0))) {
										boxes[i + 1][j + 1].setHead(true);
										while (!newHead) {
											if (boxes[i - 1][j + 1].getDirection() != ".") {
												i--;
											} else {
												boxes[i][j + 1].setHead(true);
											}
										}
									} else {
										boxes[i + 1][j + 1].setHead(true);
									}
								}

								newHead = false;

								// Caso tren hacia arriba
								if (dir.equals(toString(1))) {
									// Cabeza con cabeza
									if (boxes[i][j + 1].getHead()) {
										boxes[i][j + 1].setHead(false);
									}

									if (boxes[i + 1][j + 1].getDirection().equals(toString(1))) {
										boxes[i - 1][j + 1].setHead(true);
										while (!newHead) {
											if (boxes[i + 1][j + 1].getDirection() != ".") {
												i++;
											} else {
												boxes[i][j + 1].setHead(true);
												newHead = true;
											}
										}
									} else {
										boxes[i - 1][j + 1].setHead(true);
									}
								}
							}
						} else {
							boxes[i][j].setHead(false);
							boxes[i][j].setDireccion(".");
							if (boxes[i][j - 1].getDirection().equals(toString(3))) {
								boxes[i][j - 1].setHead(true);
							}
						}
					}
					i = k;
					if (!boxes[i][j].getHead() && boxes[i][j].getDirection().equals(toString(3))) {
						if (!checkCollision(j + 1, i)) {
							boxes[i][j + 1].setDireccion(toString(3));
							boxes[i][j].setDireccion(".");
						}
					}
				}
			}
		}
	}

	public void moveUp() {
		for (int j = boxes.length - 1; j >= 0; j--) {
			for (int i = boxes.length - 1; i >= 0; i--) {
				int k = j;
				if (!boxes[i][j].getLocked()) {
					if (boxes[i][j].getHead() && boxes[i][j].getDirection().equals(toString(1))) {
						if (checkInput(i + 1, j, boxes.length)) {
							if (!checkCollision(j, i + 1)) {
								boxes[i + 1][j].setDireccion(toString(1));
								boxes[i + 1][j].setHead(true);
								boxes[i][j].setDireccion(".");
								boxes[i][j].setHead(false);
							} else {
								// Colision
								list.add(i + 1);
								list.add(j);
								boxes[i][j].setDireccion(".");
								boxes[i][j].setHead(false);
								String dir = boxes[i + 1][j].getDirection();
								if (checkInput(i - 1, j, boxes.length)) {
									// Pongo la nueva cabeza del tren que choca
									if (boxes[i - 1][j].getDirection().equals(toString(1)))
										boxes[i - 1][j].setHead(true);
								}

								if (checkInput(i + 1, j, boxes.length)) {
									// Pongo el punto en la colision
									boxes[i + 1][j].setDireccion(".");
								}

								boolean newHead = false;

								// Caso tren hacia la izquierda
								if (dir.equals(toString(2))) {
									// Cabeza con cabeza
									if (boxes[i + 1][j].getHead())
										boxes[i + 1][j].setHead(false);

									if (boxes[i + 1][j - 1].getDirection().equals(toString(2))) {
										// Cabeza subtren derecha
										boxes[i + 1][j + 1].setHead(true);

										// Cabeza subTren izquierda
										while (!newHead) {
											if (boxes[i + 1][j - 1].getDirection().equals(toString(2))) {
												j--;
											} else {
												boxes[i + 1][j].setHead(true);
												newHead = true;
											}
										}
									} else {
										boxes[i + 1][j + 1].setHead(true);
									}
								}
								newHead = false;

								// Caso tren hacia la derecha
								if (dir.equals(toString(3))) {
									// Cabeza con cabeza
									if (boxes[i + 1][j].getHead())
										boxes[i + 1][j].setHead(false);

									if (boxes[i + 1][j + 1].getDirection().equals(toString(3))) {
										// Cabeza subTren izquierda
										boxes[i + 1][j - 1].setHead(true);

										// Cabeza subTren derecha
										while (!newHead) {
											if (boxes[i + 1][j + 1].getDirection().equals(toString(3))) {
												j++;
											} else {
												boxes[i + 1][j].setHead(true);
												newHead = true;
											}
										}
									} else {
										// Cabeza subTren izquierda
										boxes[i + 1][j - 1].setHead(true);
									}
								}
								j = k;
							}
						} else {
							boxes[i][j].setHead(false);
							boxes[i][j].setDireccion(".");
							if (boxes[i - 1][j].getDirection().equals(toString(1))) {
								boxes[i - 1][j].setHead(true);
							}
						}

					}
					if (!boxes[i][j].getHead() && boxes[i][j].getDirection().equals(toString(1))) {
						if (!checkCollision(j, i + 1)) {
							boxes[i + 1][j].setDireccion(toString(1));
							boxes[i][j].setDireccion(".");
						}
					}
				}
				
			}
		}
	}

	public void moveDown() {
		printBoard();
		for (int j = 0; j < boxes.length; j++) {
			for (int i = 0; i < boxes.length; i++) {
				int k = j;
				if (!boxes[i][j].getLocked()) {
					if (boxes[i][j].getHead() && boxes[i][j].getDirection().equals(toString(0))) {
						if (checkInput(i - 1, j, boxes.length)) {
							if (!checkCollision(j, i - 1)) {
								boxes[i - 1][j].setDireccion(toString(0));
								boxes[i - 1][j].setHead(true);
								boxes[i][j].setDireccion(".");
								boxes[i][j].setHead(false);
							} else {
								// Colision
								list.add(i - 1);
								list.add(j);
								boxes[i][j].setDireccion(".");
								boxes[i][j].setHead(false);

								String dir = boxes[i - 1][j].getDirection();
								// Nueva cabeza
								if (checkInput(i + 1, j, boxes.length)) {
									boxes[i + 1][j].setHead(true);
								}
								// Punto en la colision
								if (checkInput(i - 1, j, boxes.length)) {
									boxes[i - 1][j].setDireccion(".");
								}

								boolean newHead = false;

								// Cabeza con cabeza
								if (boxes[i - 1][j].getHead()) {
									boxes[i - 1][j].setHead(false);
								}

								// Choque tren hacia la izquierda
								if (dir.equals(toString(2))) {
									if (boxes[i - 1][j - 1].getDirection().equals(toString(2))) {
										// Cabeza subTren derecha
										boxes[i - 1][j + 1].setHead(true);

										// Cabeza subTren izquierda
										while (!newHead) {
											if (boxes[i - 1][j - 1].getDirection().equals(toString(3))) {
												j--;
											} else {
												boxes[i + 1][j].setHead(true);
												newHead = true;
											}
										}
									} else {
										// Cabeza subTren izquierda
										boxes[i - 1][j + 1].setHead(true);
									}

								}
								// Choque tren hacia la derecha
								if (dir.equals(toString(3))) {
									if (boxes[i - 1][j + 1].getDirection().equals(toString(2))) {
										// Cabeza subTren derecha
										boxes[i - 1][j - 1].setHead(true);

										// Cabeza subTren izquierda
										while (!newHead) {
											if (boxes[i - 1][j + 1].getDirection().equals(toString(3))) {
												j++;
											} else {
												boxes[i + 1][j].setHead(true);
												newHead = true;
											}
										}
									} else {
										// Cabeza subTren izquierda
										boxes[i - 1][j - 1].setHead(true);
									}
								}
							}
						} else {
							boxes[i][j].setHead(false);
							boxes[i][j].setDireccion(".");
							if (boxes[i + 1][j].getDirection().equals(toString(0))) {
								boxes[i + 1][j].setHead(true);
							}
						}

					}
					j = k;
					if (!boxes[i][j].getHead() && boxes[i][j].getDirection().equals(toString(0))) {
						if (!checkCollision(j, i - 1)) {
							if (!boxes[i][j].getLocked()) {
								boxes[i - 1][j].setDireccion(toString(0));
								boxes[i][j].setDireccion(".");
							}

						}
					}
				}
			}
		}
	}

	public void unlockDown(int row, int column) {
		while (checkInput(row,column,boxes.length) && boxes[row][column].getStatus().equals(BoxStatus.DOWN)) {
			boxes[row][column].setLocked(false);
			row++;
		}
	}

	public void unlockUp(int row, int column) {
		while (checkInput(row,column,boxes.length) && boxes[row][column].getStatus().equals(BoxStatus.UP)) {
			boxes[row][column].setLocked(false);
			row--;
		}
	}

	public void unlockRight(int row, int column) {
		while (checkInput(row,column,boxes.length) && boxes[row][column].getStatus().equals(BoxStatus.RIGHT)) {
			boxes[row][column].setLocked(false);
			column--;
		}
	}

	public void unlockLeft(int row, int column) {
		while (checkInput(row,column,boxes.length) && boxes[row][column].getStatus().equals(BoxStatus.LEFT)) {
			boxes[row][column].setLocked(false);
			column++;
		}
	}
	
	//Elimina los trenes que van hacia abajo
	public void removeDown(int row, int column) {
		while (checkInput(row,column,boxes.length) && boxes[row][column].getStatus().equals(BoxStatus.DOWN)) {
			if(boxes[row][column].getHead()) {
				boxes[row][column].setHead(false);
			}
			boxes[row][column].setDireccion(".");
			row++;
		}
	}
	//Elimina los trenes que van hacia arriba
	public void removeUp(int row, int column) {
		while (checkInput(row,column,boxes.length) && boxes[row][column].getStatus().equals(BoxStatus.UP)) {
			if(boxes[row][column].getHead()) {
				boxes[row][column].setHead(false);
			}
			boxes[row][column].setDireccion(".");
			row--;
		}
	}
	//Elimina los trenes que van hacia la izquierda
	public void removeLeft(int row, int column) {
		while (checkInput(row,column,boxes.length) && boxes[row][column].getStatus().equals(BoxStatus.LEFT)) {
			if(boxes[row][column].getHead()) {
				boxes[row][column].setHead(false);
			}
			boxes[row][column].setDireccion(".");
			column++;
		}
	}
	//Elimina los trenes que van hacia la derecha
	public void removeRight(int row, int column) {
		while (checkInput(row,column,boxes.length) && boxes[row][column].getStatus().equals(BoxStatus.RIGHT)) {
			if(boxes[row][column].getHead()) {
				boxes[row][column].setHead(false);
			}
			boxes[row][column].setDireccion(".");
			column--;
		}
	}

	public boolean endGame() {
		for (int i = 0; i < boxes.length; i++) {
			for (int j = 0; j < boxes.length; j++) {
				if (boxes[i][j].getDirection().equals(toString(0)))
					return false;
				if (boxes[i][j].getDirection().equals(toString(1)))
					return false;
				if (boxes[i][j].getDirection().equals(toString(2)))
					return false;
				if (boxes[i][j].getDirection().equals(toString(3)))
					return false;
			}
		}
		return true;
	}

	public boolean checkCollision(int x, int y) {
		if (boxes[y][x].getDirection() != ".") {
			return true;
		}
		return false;
	}

	public boolean checkInput(int x, int y, int length) {
		if (length < 1 || length > boxes.length)
			return false;
		if (x < 0 || x > boxes.length - 1)
			return false;
		if (y < 0 || y > boxes[0].length - 1)
			return false;

		return true;

	}

	public String toString(int numero) {
		String num = "";
		num += numero;

		return num;

	}
	
	public boolean existTrain() {
		for (int i = 0; i < boxes.length; i++) {
			for (int j = 0; j < boxes.length; j++) {
				if(boxes[i][j].getHead()) {
					return true;
				}
			}
		}
		return false;
	}

	public List<Train> getTrains(){
		List<Train> trains = new ArrayList<Train>();
		for(int i = 0; i < boxes.length; i++) {
			for(int j = 0; j < boxes[i].length; j++) {
				if (boxes[i][j].getHead()) {
					int length = 0;
					MovimientoTren tmp = null;
					switch (boxes[i][j].getStatus()) {
						case UP:
							tmp = MovimientoTren.ARRIBA;
							for(int k = i; boxes[i][j].getStatus() == boxes[k][j].getStatus();k--) {
								length++;
							}
							break;
						case DOWN:
							tmp = MovimientoTren.ABAJO;
							for(int k = i; boxes[i][j].getStatus() == boxes[k][j].getStatus();k++) {
								length++;
							}
							break;
						case LEFT:
							tmp = MovimientoTren.IZQUIERDA;
							for(int k = j; boxes[i][j].getStatus() == boxes[i][k].getStatus();k++) {
								length++;
							}
							break;
						case RIGHT:
							tmp = MovimientoTren.DERECHA;
							for(int k = j; boxes[i][j].getStatus() == boxes[i][k].getStatus();k--) {
								length++;
							}
							break;
					}
					trains.add(new Train(tmp, length, i, j));
				}
			}
		}
		return trains;
	}
	
	public String getOutputTrains() {
		List<Train> trains = getTrains();
		StringBuilder output = new StringBuilder();
		output.append(trains.size()).append("\n");
		for(Train train:trains) {
			String dir="";
			switch(train.getMove()) {
			case ABAJO:
				dir="B";
				break;
			case ARRIBA:
				dir="A";
				break;
			case IZQUIERDA:
				dir="I";
				break;
			case DERECHA:
				dir="D";
				break;
			}
			output
			.append(dir)
			.append(" ")
			.append(train.getLength())
			.append(" ")
			.append(train.getY())
			.append(" ")
			.append(train.getX())
			.append("\n");
		}
		return output.toString();
	}

	public String getInfoBoard() {
		StringBuilder output = new StringBuilder();
		output.append("   0 0 0 0 0 0 0 0 0 0 1 1 1 1 1 1 1 1 1 1 2 2 2 2 2 2 2 2 2 2").append("\n");
		output.append("   0 1 2 3 4 5 6 7 8 9 0 1 2 3 4 5 6 7 8 9 0 1 2 3 4 5 6 7 8 9").append("\n");

		for (int i = boxes.length - 1; i >= 0; i--) {
			if (i < 10) {
				output.append("0" + i);

			} else {
				output.append(i);
			}
			for (int j = 0; j < boxes.length; j++) {
				output.append(boxes[i][j].toString());
			}
			output.append("\n");
		}
		output.append("\n");

		return output.toString();
	}
	
	public int getBoardLength() {
		return boxes.length;
	}

	public BoxStatus getBoxStatus(int row, int column) {
		return boxes[row][column].getStatus();

	}

	public boolean getHeadStatus(int row, int column) {
		return boxes[row][column].getHead();
	}

	public boolean getInfoLocked(int row, int column) {
		return boxes[row][column].getLocked();
	}

}
