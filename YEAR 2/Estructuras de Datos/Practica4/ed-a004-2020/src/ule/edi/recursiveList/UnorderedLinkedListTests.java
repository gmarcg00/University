package ule.edi.recursiveList;



import java.util.Iterator;
import java.util.NoSuchElementException;


import org.junit.*;

import ule.edi.exceptions.EmptyCollectionException;



public class UnorderedLinkedListTests {

	

	private UnorderedLinkedListImpl<String> lS;
	private UnorderedLinkedListImpl<String> lSABC;
	

	@Before
	public void setUp() {
		this.lS = new UnorderedLinkedListImpl<String>();
		
		
		this.lSABC = new UnorderedLinkedListImpl<String>("A", "B", "C");
	}
	
	
	
	@Test
	public void isEmptyTest() {
	   Assert.assertTrue(lS.isEmpty());
	   lS.addFirst("A");
	   Assert.assertFalse(lS.isEmpty());
	}
	
	@Test
	public void containsTest() {
		Assert.assertTrue(lSABC.contains("A"));
		Assert.assertTrue(lSABC.contains("B"));
		Assert.assertFalse(lSABC.contains("D"));
		Assert.assertTrue(lSABC.contains("C"));
	}
	
	@Test(expected=NullPointerException.class)
	public void containsNullTest() {
		lS.contains(null);
	}
	
	@Test
	public void countTest() {
		Assert.assertEquals(lSABC.count("B"), 1);
	}
	
	@Test
	public void getFirstTest() throws EmptyCollectionException {
		Assert.assertEquals(lSABC.getFirst(),"A");
	}
	
	@Test(expected=EmptyCollectionException.class)
	public void getFirstEmptyTest() throws EmptyCollectionException {
		lS.getFirst();
	}
	
	@Test
	public void toStringFromUntilTest() {
		Assert.assertEquals(lSABC.toStringFromUntil(4, 8), "()");
		Assert.assertEquals(lS.toStringFromUntil(4, 8), "()");
		Assert.assertEquals(lSABC.toStringFromUntil(1,5), "(A B C )");
		Assert.assertEquals(lSABC.toStringFromUntil(1,2), "(A B )");
		Assert.assertEquals(lSABC.toStringFromUntil(1,3), "(A B C )");
		Assert.assertEquals(lSABC.toStringFromUntil(2,3), "(B C )");
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void toStringFromUntilTest1() {
		lSABC.toStringFromUntil(-2, 4);
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void toStringFromUntilTest2() {
		lSABC.toStringFromUntil(2,-3);
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void toStringFromUntilTest3() {
		lSABC.toStringFromUntil(3, 1);
	}
	
	@Test
	public void getLastTest() throws EmptyCollectionException {
		Assert.assertEquals(lSABC.getLast(),"C");
	}
	
	@Test(expected=EmptyCollectionException.class)
	public void getLast2Test() throws EmptyCollectionException {
		lS.getLast();
	}
	
	@Test
	public void isOrderedTest() {
		Assert.assertTrue(lSABC.isOrdered());
		lSABC.addLast("B");
		Assert.assertFalse(lSABC.isOrdered());
		Assert.assertTrue(lS.isOrdered());
		lS=new UnorderedLinkedListImpl<String>("B", "A");
		Assert.assertFalse(lS.isOrdered());
	}
	
	
	
	@Test
	public void sizeTest() {
		Assert.assertEquals(lSABC.size(),3);
	}
	
	
	@Test
	public void toStringTest() {
		Assert.assertEquals(lSABC.toString(), "(A B C )");
		Assert.assertEquals(lS.toString(),"()");
	}

	
   @Test
   public void constructorElemens(){
	   lS=new UnorderedLinkedListImpl<String>("A", "B", "C", "D");
	   Assert.assertEquals("(A B C D )", lS.toString());
   }

// TESTS DE addFirst
   @Test
   public void addFirstTest(){
	   
	   lS.addFirst("D");
	   Assert.assertEquals("(D )", lS.toString());
	   lS.addFirst("C");
	   Assert.assertEquals("(C D )", lS.toString());
	   lS.addFirst("B");
	   Assert.assertEquals("(B C D )", lS.toString());
	   lS.addFirst("A");
	   Assert.assertEquals("(A B C D )", lS.toString());
   }
   
   @Test
	public void addLastTest() {
		lS.addLast("A");
		Assert.assertEquals(lS.size(),1);
		lS.addLast("B");
		Assert.assertEquals(lS.size(),2);
	}
   
   @Test(expected=NullPointerException.class)
	public void addLastNullTest() {
		lS.addLast(null);
	}
   
   // TESTS DE addBefore
   
   @Test
   public void addBeforeTest(){
	   lS.addFirst("D");
	   Assert.assertEquals("(D )", lS.toString());
	   lS.addBefore("C", "D");
	   Assert.assertEquals("(C D )", lS.toString());
	   lS.addBefore("A","C");
	   Assert.assertEquals("(A C D )", lS.toString());
	   lS.addBefore("B", "C");
	   Assert.assertEquals("(A B C D )", lS.toString());
	   lS.addBefore("K", "C");
	   Assert.assertEquals("(A B K C D )", lS.toString());
	   lS.addBefore("Q", "D");
	   Assert.assertEquals("(A B K C Q D )", lS.toString());
   }
   
   @Test(expected=NullPointerException.class)
   public void addBeforeNullTest() {
	   lS.addBefore("A", null);
   }
   
   @Test(expected=NullPointerException.class)
   public void addBeforeNull2Test() {
	   lS.addBefore(null,"A");
   }
   
   @Test(expected=NoSuchElementException.class)
   public void addBeforeNoSuch() {
	   lSABC.addBefore("K", "D");
   }
   
   @Test
   public void removeTest() throws EmptyCollectionException {
	   lSABC.remove("B");
	   Assert.assertEquals("(A C )",lSABC.toString() );
	   lSABC.remove("A");
	   Assert.assertEquals("(C )",lSABC.toString() );
	   lSABC.addLast("K");
	   lSABC.addLast("F");
	   lSABC.addLast("G");
	   lSABC.remove("F");
	   Assert.assertEquals("(C K G )",lSABC.toString() );
	   lSABC.addLast("E");
	   lSABC.remove("E");
	   Assert.assertEquals("(C K G )",lSABC.toString() );
   }
   
   @Test(expected=NullPointerException.class)
   public void removeNull() throws EmptyCollectionException {
	   lSABC.remove(null);
   }
   
   @Test(expected=EmptyCollectionException.class)
   public void removeEmpty() throws EmptyCollectionException {
	   lS.remove("A");
   }
   
   @Test(expected=NoSuchElementException.class)
   public void removeNoSuch() throws EmptyCollectionException {
	  lSABC.remove("S");
   }
   
   @Test
   public void removeLast() throws EmptyCollectionException {
	  lSABC.addLast("B");
	   lSABC.removeLast("B");
	   Assert.assertEquals(lSABC.toString(),"(A B C )");
	   lSABC.addLast("B");
	   lSABC.addLast("A");
	   lSABC.removeLast("B");
	   Assert.assertEquals(lSABC.toString(),"(A B C A )");
	   lSABC.removeLast("B");
	   Assert.assertEquals(lSABC.toString(),"(A C A )");
	   lS=new UnorderedLinkedListImpl<String>("A", "B", "C", "D");
	   Assert.assertEquals( lS.removeLast("A"),"A");
	   Assert.assertEquals(lS.toString(),"(B C D )");
   }
   
   @Test(expected=NoSuchElementException.class)
   public void removeLastNoSuch() throws EmptyCollectionException {
	   lSABC.removeLast("Q");
   }
   
   @Test(expected=NullPointerException.class)
   public void removeLastNull() throws EmptyCollectionException {
	   lSABC.removeLast(null);
   }
   
   @Test(expected=EmptyCollectionException.class)
   public void removeLastEmpty() throws EmptyCollectionException {
	   lS.removeLast("A");
   }
   
   //Tests toStringReverse 
 
   @Test
   public void toStringReverse(){
	   lS=new UnorderedLinkedListImpl<String>("A", "B", "C", "D");
	   Assert.assertEquals("(A B C D )", lS.toString());
	   Assert.assertEquals("(D C B A )", lS.toStringReverse());
		  
   }
// Tests eliminar duplicados
	
   @Test
	public void testRemoveDuplicates() throws EmptyCollectionException {
	    UnorderedLinkedListImpl<String> lista=new UnorderedLinkedListImpl<String>("A", "A", "B", "C", "B", "A", "C"); 
		Assert.assertEquals(lista.removeDuplicates(),4); 
		Assert.assertEquals(lista.toString(), "(A B C )");
		Assert.assertEquals(lSABC.removeDuplicates(),0); // 0 repetids
		Assert.assertEquals(lSABC.toString(), "(A B C )");	
		lista.addLast("C");
		lista.addLast("C");
		Assert.assertEquals(lista.removeDuplicates(),2);
		Assert.assertEquals(lista.toString(), "(A B C )");	
	
	}
   
   @Test(expected=EmptyCollectionException.class)
   public void testRemoveDuplicatesEmpty() throws EmptyCollectionException {
	   lS.removeDuplicates();
   }
   
   @Test(expected=NoSuchElementException.class)
   public void iteratorTest() {
	   lS.addFirst("A");
	   lS.addFirst("B");
	   lS.addFirst("D");
	   lS.addFirst("H");
	   lS.addFirst("I");
	   Iterator<String> normal=lS.iterator();
	   StringBuffer salida=new StringBuffer("(");
	   
	   while(normal.hasNext()) {
			salida.append(normal.next() + " ");
		}
		salida.append(")");
		Assert.assertEquals(salida.toString(), lS.toString());
		Assert.assertFalse(normal.hasNext());
		normal.next();
	
	   
   }
   
   @Test(expected=UnsupportedOperationException.class)
   public void iteratorRemoveTest() {
	   Iterator<String> normal=lS.iterator();
	   normal.remove();
   }
   
  
  
   
   
// AÑADIR MAS TESTS para el resto de casos especiales y para el resto de métodos
 // de las clases AbstractLinkedListImpl y UnorderedLinkedListImpl
   

}
