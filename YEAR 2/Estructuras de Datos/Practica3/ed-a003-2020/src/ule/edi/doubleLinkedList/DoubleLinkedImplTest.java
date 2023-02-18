package ule.edi.doubleLinkedList;

import static org.junit.Assert.*;

import java.util.Iterator;
import java.util.NoSuchElementException;

import org.junit.*;

import ule.edi.exceptions.EmptyCollectionException;

public class DoubleLinkedImplTest {
	DoubleLinkedListImpl<String> lv;
	DoubleLinkedListImpl<String> listaConElems;
	DoubleLinkedListImpl<String> ls;
	
	@Before
	public void antesDe() {
		lv=new DoubleLinkedListImpl<String>();
		listaConElems=new DoubleLinkedListImpl<String>();
		ls=new DoubleLinkedListImpl<String>();
		listaConElems.insertFirst("D");
		listaConElems.insertFirst("B");
		listaConElems.insertFirst("A");
		listaConElems.insertFirst("C");
		listaConElems.insertFirst("B");
		listaConElems.insertFirst("A");
		
	}

	
	@Test
	public void isEmptyTest() {
		Assert.assertTrue(lv.isEmpty());
		Assert.assertTrue(lv.size()==0);
		Assert.assertFalse(listaConElems.isEmpty());
		Assert.assertTrue(listaConElems.size()==6);
		
	}
	
	@Test
	public void clearTest() {
		lv.clear();
		Assert.assertTrue(lv.isEmpty());
		Assert.assertTrue(lv.size()==0);
		
		listaConElems.clear();
		Assert.assertTrue(listaConElems.isEmpty());
		Assert.assertTrue(listaConElems.size()==0);
		Assert.assertEquals(listaConElems.toString(),listaConElems.toStringReverse());
		
	}
	
	@Test
	public void testInsertFirst() {
		Assert.assertEquals("(A B C A B D )",listaConElems.toString());
		lv.insertFirst("G");
		Assert.assertEquals(1,lv.size());
	}
	
	@Test(expected=NullPointerException.class)
	public void testInsertFirstNull() {
		lv.insertFirst(null);
	}
	
	@Test
	public void testInsertLast() {
		listaConElems.insertLast("G");
		Assert.assertEquals("(A B C A B D G )",listaConElems.toString());
		Assert.assertEquals(7, listaConElems.size());
		lv.insertLast("G");
		Assert.assertEquals("(G )", lv.toString());
	}
	
	@Test(expected=NullPointerException.class)
	public void testInsertLastNull() {
		lv.insertLast(null);
	}
	
	@Test
	public void testRemoveFirst() throws EmptyCollectionException {
		Assert.assertEquals("A", listaConElems.removeFirst());
		Assert.assertEquals("(B C A B D )",listaConElems.toString());
	}
	
	
	@Test(expected=EmptyCollectionException.class)
	public void testRemoveFirstEmpty() throws EmptyCollectionException {
		lv.removeFirst();
	}
	
	@Test
	public void testRemoveLast() throws EmptyCollectionException {
		Assert.assertEquals("D", listaConElems.removeLast());
		Assert.assertEquals("(A B C A B )",listaConElems.toString());

	}
	
	@Test(expected=EmptyCollectionException.class)
	public void testRemoveLastEmpty() throws EmptyCollectionException {
		lv.removeLast();
	}
	
	@Test
	public void testInsertPos() {
		Assert.assertEquals("(A B C A B D )",listaConElems.toString());
		listaConElems.insertPos("Z", 4);
		Assert.assertEquals("(A B C Z A B D )",listaConElems.toString());
		listaConElems.insertPos("G", 10);
		Assert.assertEquals("(A B C Z A B D G )",listaConElems.toString());
		listaConElems.insertPos("G", 1);
		Assert.assertEquals("(G A B C Z A B D G )",listaConElems.toString());
	}
	
	@Test(expected=NullPointerException.class)
	public void insertPosNullTest() {
		listaConElems.insertPos(null, 4);
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void insertPosIllegalTest() {
		listaConElems.insertPos("A", 0);
	}
	
	@Test
	public void insertBeforeTest() {
		listaConElems.insertBefore("K","C");
		Assert.assertEquals("(A B K C A B D )", listaConElems.toString());
	}
	
	@Test
	public void getElemPosTest() {
		Assert.assertEquals("C", listaConElems.getElemPos(3));
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void getElemPosIllegalTest() {
		listaConElems.getElemPos(0);
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void getElemPosIllegalTest2() {
		listaConElems.getElemPos(20);
	}
	
	@Test
	public void testGetPosFirst() {
		Assert.assertEquals(2, listaConElems.getPosFirst("B"));
	}
	
	@Test(expected=NullPointerException.class)
	public void GetPosFirstNull() {
		listaConElems.getPosFirst(null);
	}
	
	@Test(expected=NoSuchElementException.class)
	public void testGetPosFirstNoSuch() {
		listaConElems.getPosFirst("Z");
	}
	
	@Test
	public void testGetPosLast() {
		Assert.assertEquals(5,listaConElems.getPosLast("B"));
	}
	
	@Test(expected=NullPointerException.class)
	public void GetPosFirstLast() {
		listaConElems.getPosLast(null);
	}

	@Test(expected=NoSuchElementException.class)
	public void testGetPosLastNoSuch() {
		listaConElems.getPosLast("Z");
	}
	
	@Test
	public void removePosTest() {
		Assert.assertEquals("C", listaConElems.removePos(3));
		Assert.assertEquals("(A B A B D )", listaConElems.toString());
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void removePosIllegalTest() {
		listaConElems.removePos(0);
		
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void removePosIllegal2Test() {
		listaConElems.removePos(20);
	}
	
	
	@Test
	public void removeAllTest() throws EmptyCollectionException {
		Assert.assertEquals(2, listaConElems.removeAll("A"));
		Assert.assertEquals(listaConElems.toString(),"(B C B D )");

		listaConElems.removeAll("B");

		Assert.assertFalse(listaConElems.contains("A"));
		Assert.assertFalse(listaConElems.contains("B"));
		Assert.assertEquals(listaConElems.toString(),"(C D )");
		listaConElems.removeAll("C");

		Assert.assertTrue(listaConElems.contains("D"));
		Assert.assertFalse(listaConElems.contains("C"));
		listaConElems.removeAll("D");

		Assert.assertFalse(listaConElems.contains("D"));
		Assert.assertTrue(listaConElems.isEmpty());
		Assert.assertTrue(listaConElems.size()==0);
		Assert.assertEquals(listaConElems.toString(),listaConElems.toStringReverse());
		
		lv.clear();
		lv.insertFirst("A");
		Assert.assertEquals(1, lv.removeAll("A"));

	}
	
	@Test(expected=NullPointerException.class)
	public void removeAllNullTest() {
		listaConElems.removeAll(null);
	}
	
	@Test(expected=NoSuchElementException.class)
	public void removeAllNoSuchTest() {
		listaConElems.removeAll("P");
	}
	
	
	@Test
	public void containsTest() {
		Assert.assertFalse(lv.contains("A"));
		Assert.assertTrue(listaConElems.contains("A"));
		Assert.assertTrue(listaConElems.contains("B"));
		Assert.assertTrue(listaConElems.contains("B"));
		Assert.assertTrue(listaConElems.contains("D"));
		Assert.assertFalse(listaConElems.contains("Z"));
		
	}
	
	@Test
	public void testsize() {
		Assert.assertEquals(6, listaConElems.size());
		Assert.assertEquals(0,lv.size());
	}
	
	@Test
	public void testToStringReverse() {
		Assert.assertEquals("(D B A C B A )",listaConElems.toStringReverse());
	}
	
	@Test
	public void reverseTest() {
		Assert.assertEquals("(D B A C B A )",listaConElems.reverse().toString());
	}
	
	@Test
	public void maxRepeatedTest() throws EmptyCollectionException {
		listaConElems.insertPos("A", 6);
		Assert.assertEquals(listaConElems.toString(),"(A B C A B A D )");
		Assert.assertEquals(3, listaConElems.maxRepeated());
		listaConElems.insertFirst("C");
		Assert.assertEquals(listaConElems.toString(),"(C A B C A B A D )");
		Assert.assertEquals(3, listaConElems.maxRepeated());
		listaConElems.insertPos("A", 8);
		Assert.assertEquals(4, listaConElems.maxRepeated());
	}
	
	@Test
	public void isEqualsTest() {
		Assert.assertTrue(lv.isEquals(ls));
		lv.insertFirst("D");
		Assert.assertFalse(listaConElems.isEquals(lv));
		lv.insertFirst("B");
		lv.insertFirst("A");
		lv.insertFirst("C");
		lv.insertFirst("B");
		lv.insertFirst("A");
		Assert.assertTrue(listaConElems.isEquals(lv));
		ls.insertFirst("A");
		ls.insertFirst("B");
		ls.insertFirst("A");
		ls.insertFirst("A");
		ls.insertFirst("A");
		ls.insertFirst("A");
		Assert.assertFalse(listaConElems.isEquals(ls));
		ls.clear();
		Assert.assertFalse(lv.isEquals(ls));
		Assert.assertFalse(ls.isEquals(lv));
		ls.insertFirst("A");
		ls.clear();
		lv.clear();
		Assert.assertTrue(lv.isEquals(ls));
	}
	
	@Test(expected=NullPointerException.class)
	public void isEqualsNullTest() {
		lv.isEquals(null);
	}
	
	@Test
	public void containsAllTest() {
		Assert.assertTrue(listaConElems.containsAll(ls));
		ls.insertFirst("A");
		ls.insertFirst("B");
		Assert.assertTrue(listaConElems.containsAll(ls));
		ls.insertFirst("K");
		Assert.assertFalse(listaConElems.containsAll(ls));
	}
	
	@Test(expected=NullPointerException.class)
	public void containsAllNullTest() {
		listaConElems.containsAll(null);
	}

	
	
	@Test
	public void isSubListTest() throws EmptyCollectionException {
			Assert.assertTrue(listaConElems.isSubList(listaConElems));
			Assert.assertFalse(lv.isSubList(listaConElems));
		 	Assert.assertTrue(listaConElems.isSubList(lv));
	    	Assert.assertTrue(listaConElems.isSubList(new DoubleLinkedListImpl<String>("A", "B", "C")));
	      	Assert.assertEquals(listaConElems.toString(),"(A B C A B D )");
	      	Assert.assertEquals(new DoubleLinkedListImpl<String>("A", "C").toString(),"(A C )");   
	     	Assert.assertFalse(listaConElems.isSubList(new DoubleLinkedListImpl<String>("A", "C")));
	     	Assert.assertEquals(listaConElems.maxRepeated(),2);
	     	listaConElems.insertBefore("A", "D");
	     	
	     	Assert.assertEquals(listaConElems.toString(),"(A B C A B A D )");
	    	Assert.assertTrue(listaConElems.maxRepeated()==3);
	}
	
	@Test(expected=NullPointerException.class)
	public void isSubListNullTest() {
		listaConElems.isSubList(null);
	}
	
	
	@Test
	public void toStringFromUntilTest() {
		Assert.assertEquals("(A B C )",listaConElems.toStringFromUntil(1, 3));
		Assert.assertEquals("(B C A B D )",listaConElems.toStringFromUntil(2, 6));
	}
	
	@Test
	public void testToString() {
		lv.insertFirst("G");
		String prueba="(G )";
		Assert.assertEquals(prueba,lv.toString());
	}
	
	@Test(expected=NoSuchElementException.class)
	public void testIterator() {
		lv.insertFirst("B");
		lv.insertFirst("A");
		lv.insertFirst("C");
		lv.insertFirst("B");
		lv.insertFirst("A");
		Iterator<String> normal = lv.iterator();
		StringBuffer salida = new StringBuffer("(");
		while(normal.hasNext()) {
			salida.append(normal.next() + " ");
		}
		salida.append(")");
		Assert.assertEquals(salida.toString(), lv.toString());
		Assert.assertFalse(normal.hasNext());
		normal.next();
	}
	
	@Test(expected=NoSuchElementException.class)
	public void testProgressIterator() {
		lv = new DoubleLinkedListImpl<String>("1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19");
		Iterator<String> normal = lv.progressIterator();
		StringBuffer salida = new StringBuffer("(");
		while(normal.hasNext()) {
			salida.append(normal.next() + " ");
		}
		salida.append(")");
		Assert.assertEquals("(1 2 4 7 11 16 )", salida.toString());
		Assert.assertFalse(normal.hasNext());
		normal.next();
	}
	
	@Test(expected=NoSuchElementException.class)
	public void testReverseIterator() {
		lv.insertFirst("B");
		lv.insertFirst("A");
		lv.insertFirst("C");
		lv.insertFirst("B");
		lv.insertFirst("A");
		Iterator<String> normal = lv.reverseIterator();
		StringBuffer salida = new StringBuffer("(");
		while(normal.hasNext()) {
			salida.append(normal.next() + " ");
		}
		salida.append(")");
		Assert.assertEquals(salida.toString(), lv.toStringReverse());
		Assert.assertFalse(normal.hasNext());
		normal.next();
	}
	
	@Test(expected=NoSuchElementException.class)
	public void testEvenPositionsIterator() {
		lv = new DoubleLinkedListImpl<String>("A");
		Iterator<String> normal = lv.evenPositionsIterator();
		Assert.assertFalse(normal.hasNext());
		
		lv = new DoubleLinkedListImpl<String>("A", "B", "C", "D", "E");
		normal = lv.evenPositionsIterator();
		StringBuffer salida = new StringBuffer("(");
		while(normal.hasNext()) {
			salida.append(normal.next() + " ");
		}
		salida.append(")");
		Assert.assertEquals("(B D )", salida.toString());
		
		lv = new DoubleLinkedListImpl<String>("A", "B", "C", "D", "E", "F");
		normal = lv.evenPositionsIterator();
		salida = new StringBuffer("(");
		while(normal.hasNext()) {
			salida.append(normal.next() + " ");
		}
		salida.append(")");
		Assert.assertEquals("(B D F )", salida.toString());
		Assert.assertFalse(normal.hasNext());
		normal.next();
	}
	

}
