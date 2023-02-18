package ule.edi.queuewithrep;


import static org.junit.Assert.*;

import java.util.Iterator;
import java.util.NoSuchElementException;

import org.junit.*;

import ule.edi.exceptions.EmptyCollectionException;

public abstract class AbstractQueueWithRefTests {

	protected abstract <T> QueueWithRep<T> createQueueWithRep();
	

	private QueueWithRep<String> S1;

	private QueueWithRep<String> S2;
	
	@Before
	public void setupQueueWithReps() {

		this.S1 = createQueueWithRep();
		
		this.S2 = createQueueWithRep();
		
		S2.add("ABC", 5);
		S2.add("123", 5);
		S2.add("XYZ", 10);
	}

	@Test
	public void testConstructionIsEmpty() {
		assertTrue(S1.isEmpty());
		assertFalse(S2.isEmpty());
	}
	
	@Test
	//Las nuevas instancias del TAD tienen tamaño cero: 
	public void testConstructionCardinality() {
		assertEquals(S1.size(), 0);
	}

	@Test
	public void testToStringInEmpty() {
		assertTrue(S1.isEmpty());
		assertEquals(S1.toString(), "()");
	}
	
	@Test
	public void testToString1elem() {
		assertTrue(S1.isEmpty());
		S1.add("A",3);
		assertEquals(S1.toString(), "(A A A )");
	}
	
	@Test
	//Añadir elementos con una multiplicidad incrementa su contador y el tamaño de la cola: ")
	public void testAddWithCount() {
		S1.add("ABC", 5);
		assertEquals(S1.count("ABC"), 5);
		assertEquals(S1.size(), 5);
		S1.add("ABC", 5);
		assertEquals(S1.count("ABC"), 10);
		assertEquals(S1.size(), 10);
		S1.add("123", 5);		
		assertEquals(S1.count("123"), 5);
		assertEquals(S1.count("ABC"), 10);
		assertEquals(S1.size(), 15);
	}
	
	@Test(expected=NullPointerException.class)
	public void testAddTinmesNull() {
		S1.add(null,8);
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testAddNegativeTimes() {
		S1.add("A",-2);
	}
	
	@Test(expected=NullPointerException.class)
	public void testAddNull() {
		S1.add(null);
	}
	
	@Test
	public void testRemoveTimes() {
		S1.add("A",4);
		S1.add("B",4);
		S1.remove("A",2);
		Assert.assertEquals("(A A B B B B )",S1.toString());
		S1.remove("A",1);
		Assert.assertEquals("(A B B B B )",S1.toString());
		S1.remove("B",3);
		Assert.assertEquals("(A B )",S1.toString());
		
	}
	

	@Test(expected=NullPointerException.class)
	public void testRemoveNull() {
		S1.remove(null, 1);
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testRemoveNegativeTimes() {
		S1.add("A",4);
		S1.remove("A", -1);
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testRemoveNegativeTimes2() {
		S1.add("A",4);
		S1.remove("A",5);
	}
	
	@Test(expected=EmptyCollectionException.class)
	public void testRemoveEmpty() throws EmptyCollectionException {
		S1.remove();
	}
	
	@Test(expected=NoSuchElementException.class)
	public void testRemoveNoElemento() {
		S1.add("A",4);
		S1.remove("C", 1);
	}
	
	@Test
	public void testRemoveElementoCero() {
		S1.add("A",4);
		S1.remove("A", 0);
	}
	
	@Test
	public void testRemove() throws EmptyCollectionException {
		S1.add("A",2);
		S1.add("B",2);
		S1.add("A",2);
		S1.add("B",2);
		S1.add("C",1);
		
		
		Assert.assertEquals(4,S1.remove());
		
		Assert.assertEquals("(B B B B C )", S1.toString());
		
	}
	
	@Test
	public void testclear() {
		S1.add("A",2);
		S1.add("B",2);
		S1.clear();
		Assert.assertEquals("()", S1.toString());
	}
	
	@Test
	public void testContains() {
		S1.add("A",2);
		Assert.assertTrue(S1.contains("A"));
		Assert.assertFalse(S1.contains("B"));
	}
	
	@Test(expected=NullPointerException.class)
	public void testContainsNull() {
		S1.add("A",2);
		Assert.assertTrue(S1.contains(null));
	}
	
	@Test
	public void testEmpty() {
		Assert.assertTrue(S1.isEmpty());
		S1.add("A");
		Assert.assertFalse(S1.isEmpty());
	}
	
	@Test
	public void testSize() {
		S1.add("A", 2);
		Assert.assertEquals(2, S1.size());
	}
	
	
	@Test
	public void testCount() {
		S1.add("A",2);
		Assert.assertEquals(0,S1.count("V"));
	}
	
	
	@Test(expected=NullPointerException.class)
	public void testCountNull() {
		S1.count(null);
	}
	
	@Test(expected=NoSuchElementException.class)
	public void testIterator() {
		S1.add("A",2);
		S1.add("B");
		S1.add("C");
		
		Iterator<String> i = S1.iterator();
		Assert.assertTrue(i.hasNext());
		Assert.assertEquals("A", i.next());
		Assert.assertTrue(i.hasNext());
		Assert.assertEquals("A", i.next());
		Assert.assertTrue(i.hasNext());
		Assert.assertEquals("B", i.next());
		Assert.assertTrue(i.hasNext());
		Assert.assertEquals("C", i.next());
		Assert.assertFalse(i.hasNext());
		Assert.assertEquals("A", i.next());
	}
	
	@Test
	public void testToString() {
		ArrayQueueWithRepImpl<String> aq = new ArrayQueueWithRepImpl<String>(2);
		S1.add("A",2);
		String prueba="(A A )";
		Assert.assertEquals(prueba, S1.toString());
	
	}
	
	
	@Test
	//Se pueden eliminar cero instancias de un elemento con remove(x, 0): ")
	public void testRemoveZeroInstances() {
		S1.remove("ABC", 0);
	}
	
	// TODO AÑADIR MAS TESTS
	
	
	

}
