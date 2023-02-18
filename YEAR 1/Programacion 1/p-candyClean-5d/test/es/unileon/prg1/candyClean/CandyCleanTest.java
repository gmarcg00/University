package es.unileon.prg1.candyClean;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class CandyCleanTest {

	private CandyClean candyClean;

	@Before
	public void inicializar() throws CandyCleanException {

		candyClean = new CandyClean(5, 3);
		candyClean.setBoard(new Board(5, 5));
	}

	@Test
	public void testGetBoard() throws CandyCleanException {

		assertEquals(candyClean.getBoard(), candyClean.getBoard());
	}

	@Test
	public void testBoardEmpty() {
		for (int i = 0; i < candyClean.getBoard().getBoxes().length; i++) {
			for (int j = 0; j < candyClean.getBoard().getBoxes()[0].length; j++) {

				assertNotSame(candyClean.getBoard().getBoxes()[i][j].getColor().getColorName(), BackgroundColor.BLACK.toString());
			}
		}
	}

	@Test
	public void testSelectBox() throws CandyCleanException {

		candyClean.getBoard().getBoxes()[0][0].setColor(new Color(1));
		candyClean.getBoard().getBoxes()[0][1].setColor(new Color(1));
		candyClean.getBoard().getBoxes()[0][2].setColor(new Color(2));

		candyClean.selectBox(0, 0);
		
		assertEquals(candyClean.getBoard().getBoxes()[0][0].getColor().getColorName(), "\u001B[40m");
		assertEquals(candyClean.getBoard().getBoxes()[0][1].getColor().getColorName(), "\u001B[40m");
		assertEquals(candyClean.getBoard().getBoxes()[0][2].getColor().getColorName(), "\u001B[42m");
		
		assertNotSame(candyClean.getBoard().getBoxes()[1][0].getColor().getColorName(), "\u001B[40m");
		assertNotSame(candyClean.getBoard().getBoxes()[1][1].getColor().getColorName(), "\u001B[40m");
		assertNotSame(candyClean.getBoard().getBoxes()[1][2].getColor().getColorName(), "\u001B[40m");
		
	}
	@Test
	public void testSelectBoxlb() {//lastbox
		
		candyClean.getBoard().getBoxes()[4][4].setColor(new Color(1));
		candyClean.getBoard().getBoxes()[4][3].setColor(new Color(1));
		candyClean.getBoard().getBoxes()[4][2].setColor(new Color(1));
		candyClean.getBoard().getBoxes()[4][1].setColor(new Color(1));
		candyClean.getBoard().getBoxes()[4][0].setColor(new Color(4));
		
		candyClean.selectBox(4, 3);
		
		assertEquals(candyClean.getBoard().getBoxes()[4][4].getColor().getColorName(), "\u001B[40m");
		assertEquals(candyClean.getBoard().getBoxes()[4][3].getColor().getColorName(), "\u001B[40m");
		assertEquals(candyClean.getBoard().getBoxes()[4][2].getColor().getColorName(), "\u001B[40m");
		assertEquals(candyClean.getBoard().getBoxes()[4][1].getColor().getColorName(), "\u001B[40m");
		assertEquals(candyClean.getBoard().getBoxes()[4][0].getColor().getColorName(), "\u001B[44m");
		
		
		assertNotSame(candyClean.getBoard().getBoxes()[3][0].getColor().getColorName(), "\u001B[40m");
		assertNotSame(candyClean.getBoard().getBoxes()[3][1].getColor().getColorName(), "\u001B[40m");
		assertNotSame(candyClean.getBoard().getBoxes()[3][2].getColor().getColorName(), "\u001B[40m");
		assertNotSame(candyClean.getBoard().getBoxes()[3][3].getColor().getColorName(), "\u001B[40m");
	}

	@Test
	public void testCheckWin() {

		for (int i = 0; i < candyClean.getBoard().getBoxes().length; i++) {
			for (int j = 0; j < candyClean.getBoard().getBoxes()[0].length; j++) {

				candyClean.getBoard().getBoxes()[i][j].setColor(new Color(0));
			}
		}
		assertTrue(candyClean.checkWin());
		
		candyClean.getBoard().getBoxes()[4][4].setColor(new Color(3));
		candyClean.getBoard().getBoxes()[4][3].setColor(new Color(3));
		assertFalse(candyClean.checkWin());
		
		candyClean.selectBox(4, 4);

		assertTrue(candyClean.checkWin());
	}

	@Test
	public void testCheckLoose() {

		for (int i = 0; i < candyClean.getBoard().getBoxes().length; i++) {
			for (int j = 0; j < candyClean.getBoard().getBoxes()[0].length; j++) {

				candyClean.getBoard().getBoxes()[i][j].setColor(new Color(0));
			}
		}
		assertTrue(candyClean.checkWin());
		
		candyClean.getBoard().getBoxes()[4][4].setColor(new Color(3));
		candyClean.getBoard().getBoxes()[4][3].setColor(new Color(3));
		assertFalse(candyClean.checkLoose());
		candyClean.getBoard().getBoxes()[4][3].setColor(new Color(0));
		assertTrue(candyClean.checkLoose());
	}
}
