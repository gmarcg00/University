package es.unileon.prg1.candyClean;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;


public class BoardTest {

	Board sample;
	@Before
	public void setUp() throws Exception {
		sample = new Board(5, 3);
		int[] colors = {1, 1, 1, 1, 1, 4, 4, 1, 1, 1, 4, 4, 4, 4, 4, 2, 2, 4, 4, 4, 4, 4, 2, 2, 2};
		int cont = 0;
		for (int i = 0; i < 5; i++) {
			for (int j = 0; j < 5; j++) {
				sample.getBoxes()[i][j]= new Box(BackgroundColor.values()[colors[cont]]);
				cont++;
			}
		}
	}

	@Test
	public void testBoard() throws CandyCleanException {
		Board board = new Board(5, 3);
		Box[][] boxes = board.getBoxes();
		int numColors=0;
		int[] colors = {0, 0, 0, 0, 0, 0, 0, 0};
		assertEquals(5, boxes.length);
		assertEquals(5, boxes[0].length);
			
		for (int i = 0; i < boxes.length; i++) {
			for (int j = 0; j < boxes.length; j++) {
				for (int k = 0; k < 8; k++) {
					Box box = new Box(BackgroundColor.values()[k]);
					if(box.getColor().equals(boxes[i][j].getColor())) {
						colors[k]++;
					}
				}
			}
		}
		
		for (int i = 0; i < colors.length; i++) {
			if(colors[i]!=0) {
				numColors++;
			}
		}
		
		assertEquals(numColors, 3);
	}

	@Test
	public void testNewBoard() throws CandyCleanException {
		Board board = new Board(5, 3);
		Box[][] boxes = new Box[5][5];
		Box[][] boxes2;
		boolean different=false;
		for (int i = 0; i < boxes.length; i++) {
			for (int j = 0; j < boxes.length; j++) {
				boxes[i][j] = board.getBoxes()[i][j];
			}
		}
		board.newBoard();
		boxes2=board.getBoxes();
		
		for (int i = 0; i < boxes2.length; i++) {
			for (int j = 0; j < boxes2.length; j++) {
				if(!boxes[i][j].getColor().equals(boxes2[i][j].getColor())) {
					different=true;
				}
			}
		}
		assertTrue(different);
	}

	@Test
	public void testRemoveRow() {
		boolean valid=true;
		assertTrue(sample.removeBox(2, 0));
		Box box = new Box(BackgroundColor.values()[0]);
		for (int i = 0; i < 5; i++) {
			if(!box.getColor().equals(sample.getBoxes()[2][i].getColor())) {
				valid = false;
			}
		}
		assertTrue(valid);
		assertTrue(sample.removeBox(3, 0));
		for (int i = 0; i < 2; i++) {
			if(!box.getColor().equals(sample.getBoxes()[3][i].getColor())) {
				valid = false;
			}
		}
		assertTrue(sample.removeBox(0, 4));
		for (int i = 0; i < 5; i++) {
			if(!box.getColor().equals(sample.getBoxes()[0][i].getColor())) {
				valid = false;
			}
		}
		assertTrue(valid);
		if(!box.getColor().equals(sample.getBoxes()[3][3].getColor())) {
			valid = false;
		}
		assertFalse(valid);
	}
	
	@Test
	public void testRemoveRowExcesoFilas() {
		assertFalse(sample.removeBox(8, 0));
	}
	
	@Test
	public void testRemoveRowExcesoColumnas() {
		assertFalse(sample.removeBox(2, 8));
	}
	
	@Test
	public void testRemoveRowDefectoFilas() {
		assertFalse(sample.removeBox(-8, 0));
	}
	
	@Test
	public void testRemoveRowDefectoColumnas() {
		assertFalse(sample.removeBox(2, -8));
	}

	@Test
	public void testCompact() {
		boolean valid=true;
		assertTrue(sample.removeBox(2, 0));
		Box box = new Box(BackgroundColor.values()[0]);
		sample.compact();
		for (int i = 0; i < 5; i++) {
			if(!box.getColor().equals(sample.getBoxes()[0][i].getColor())) {
				valid = false;
			}
		}
		assertTrue(valid);
		assertTrue(sample.removeBox(3, 0));
		sample.compact();
		for (int i = 0; i < 2; i++) {
			if(!box.getColor().equals(sample.getBoxes()[1][i].getColor())) {
				valid = false;
			}
		}
	}

	@Test
	public void testPrintBoard() {
		String tablero=" |0|";
		int[] colors = {1, 1, 1, 1, 1, 4, 4, 1, 1, 1, 4, 4, 4, 4, 4, 2, 2, 4, 4, 4, 4, 4, 2, 2, 2};
		int cont = 0;
		
		for (int k = 1; k < 5; k++) {
			tablero = tablero + String.valueOf(k) + "|";
		}
		tablero = tablero + "\n";
		for (int i = 0; i < 5; i++) {
			tablero = tablero + String.valueOf(i);
			for (int j = 0; j < 5; j++) {
				tablero = tablero + new Color(BackgroundColor.values()[colors[cont]]);
				cont++;
			}
			tablero = tablero + "\n";
		}
		
		assertEquals(tablero, sample.printBoard().toString());
		
	}

	@Test
	public void testGetBoxes() {
		Box[][] boxes = sample.getBoxes();
		assertEquals(5, boxes.length);
		assertEquals(5, boxes[0].length);
	}

	@Test
	public void testToString() {
		
			String tablero="  0|";
			int[] colors = {1, 1, 1, 1, 1, 4, 4, 1, 1, 1, 4, 4, 4, 4, 4, 2, 2, 4, 4, 4, 4, 4, 2, 2, 2};
			int cont = 0;
			
			for (int k = 1; k < 5; k++) {
				tablero = tablero + String.valueOf(k) + "|";
			}
			tablero = tablero + "\n";
			for (int i = 0; i < 5; i++) {
				tablero = tablero + String.valueOf(i);
				for (int j = 0; j < 5; j++) {
					tablero = tablero + new Color(BackgroundColor.values()[colors[cont]]);
					cont++;
				}
				tablero = tablero + "\n";
			}
			
			assertEquals(tablero, sample.toString());
			
		}

}
