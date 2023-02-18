package es.unileon.prg1.candyClean;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class BoxTest {

	private Box box;
	private CandyClean candyClean;
	private Color color;
	
	
	@Before
	public void setUp() throws CandyCleanException{
		box=new Box(BackgroundColor.BLUE);
		candyClean=new CandyClean(5,3);
		color=new Color(BackgroundColor.BLUE);
		
	}
	
	@Test
	public void testBox() {
		box=new Box(BackgroundColor.GREEN);
	}
		
	
	@Test
	public void testSetColor() {
		box.setColor(new Color(0));
	}
	
	@Test
	public void testGetColor() {
		box.getColor();	
	}
	@Test
	public void testIsSame() {
		assertTrue(box.isSame(box));
		box=new Box (BackgroundColor.CYAN);
		assertFalse(box.isSame(box));
	}
	
	@Test
	public void testRemoveBox() {
		box.removeBox();
	}
	@Test
	public void testToString() {
		box.toString();
		
	}

}
