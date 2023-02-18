package es.unileon.prg1.candyClean;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class ColorTest {

	private Color color, color1, color2, color3;
	
	@Before
	public void setup() {
		
		color = new Color(BackgroundColor.BLACK);
		color2 = new Color(3);
		color3 = new Color(color);
		
	}
	
	@Test
	public void testGetColorName() {
		
		assertEquals(this.color.getColorName(), "\u001B[40m");
	
	}
	
	@Test
	public void testColor() {
		
		assertEquals(this.color2.getColorName(), "[43m");
	}
	
	@Test
	public void testAnotherColor() {
		
		assertEquals(this.color3.getColorName(), "\u001B[40m");
		
	}
	
	@Test
	public void testEquals() {
		
		color1 = new Color(BackgroundColor.BLACK);
		assertTrue(this.color.equals(color1));
		
	}
	
	@Test
	public void testNotEquals() {
		
		color1 = new Color(BackgroundColor.RED);
		assertFalse(this.color.equals(color1));
		
	}
	
	@Test (expected=Exception.class)
	public void testWrongColor() {
		color1 = new Color(33);
		
	}
	
	@Test
	public void testToString0() {
		
		assertEquals(this.color.toString("Test"), "\u001B[40mTest\u001B[0m" );
	
	}
	
	@Test
	public void testToString() {
		
		assertEquals(this.color.toString(), "\u001B[40m  \u001B[0m" );
	
	}

}
