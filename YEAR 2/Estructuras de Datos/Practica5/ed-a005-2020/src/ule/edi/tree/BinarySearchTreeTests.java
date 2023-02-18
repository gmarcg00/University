package ule.edi.tree;


import java.util.Collection;
import java.util.LinkedList;
import java.util.List;
import java.util.NoSuchElementException;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;





public class BinarySearchTreeTests {

   
	/*
	* 10
	* |  5
	* |  |  2
	* |  |  |  ∅
	* |  |  |  ∅
	* |  |  ∅
	* |  20
	* |  |  15
	* |  |  |  ∅
	* |  |  |  ∅
	* |  |  30
	* |  |  |  ∅
	* |  |  |  ∅
    */	
	private BinarySearchTreeImpl<Integer> ejemplo = null;
	
	
	/*
	* 10
	* |  5
	* |  |  2
	* |  |  |  ∅
	* |  |  |  ∅
	* |  |  ∅
	* |  20
	* |  |  15
	* |  |  |  12
	* |  |  |  |  ∅
	* |  |  |  |  ∅
	* |  |  ∅
  */
	private BinarySearchTreeImpl<Integer> other=null;
	private BinarySearchTreeImpl<Integer> ejemplo2=null;
	private BinarySearchTreeImpl<Integer> ejemplo3=null;
	
	@Before
	public void setupBSTs() {
		
			
		ejemplo = new BinarySearchTreeImpl<Integer>();
		ejemplo.insert(10, 20, 5, 2, 15, 30);
		Assert.assertEquals(ejemplo.toString(), "{10, {5, {2, ∅, ∅}, ∅}, {20, {15, ∅, ∅}, {30, ∅, ∅}}}");
		
		
		other =new BinarySearchTreeImpl<Integer>();
		other.insert(10, 20, 5, 2, 15, 12);
		Assert.assertEquals(other.toString(), "{10, {5, {2, ∅, ∅}, ∅}, {20, {15, {12, ∅, ∅}, ∅}, ∅}}");
		
		ejemplo2= new BinarySearchTreeImpl<Integer>();
		ejemplo3= new BinarySearchTreeImpl<Integer>();
		
	    	}
	
	@Test
	public void testRemoveHoja() {
		ejemplo.remove(30);
		Assert.assertEquals("{10, {5, {2, ∅, ∅}, ∅}, {20, {15, ∅, ∅}, ∅}}",ejemplo.toString());
	}
	
	@Test
	public void testRemove1Hijo() {
		ejemplo.remove(5);
		Assert.assertEquals("{10, {2, ∅, ∅}, {20, {15, ∅, ∅}, {30, ∅, ∅}}}",ejemplo.toString());
		ejemplo2.insert(10,20,5,7,15,30);
		ejemplo2.remove(5);
		Assert.assertEquals("{10, {7, ∅, ∅}, {20, {15, ∅, ∅}, {30, ∅, ∅}}}",ejemplo2.toString());
		ejemplo2.remove(15,30);
		Assert.assertEquals("{10, {7, ∅, ∅}, {20, ∅, ∅}}",ejemplo2.toString());
		ejemplo3.insert(10,7,15,4,2,3);
		ejemplo3.remove(7);
		ejemplo3.insert(30,25,32);
		ejemplo3.remove(15);
		
	}
	
	@Test(expected=NoSuchElementException.class)
	public void testRemoveNoSuch() {
		ejemplo.remove(40);
	}
	
	@Test(expected=NoSuchElementException.class)
	public void testRemoveNoSuch2() {
		ejemplo.remove(40,50);
	}
	
	
	@Test
	public void testRemove2Hijos() {
		ejemplo.remove(10);
		Assert.assertEquals("{15, {5, {2, ∅, ∅}, ∅}, {20, ∅, {30, ∅, ∅}}}",ejemplo.toString());
	}
	
		@Test
		public void testTagDecendentsEjemplo() {
			ejemplo.tagDecendents();
			ejemplo.filterTags("decendents");
			Assert.assertEquals("{10 [(decendents, 5)], {5 [(decendents, 1)], {2 [(decendents, 0)], ∅, ∅}, ∅}, {20 [(decendents, 2)], {15 [(decendents, 0)], ∅, ∅}, {30 [(decendents, 0)], ∅, ∅}}}",ejemplo.toString());
			ejemplo.insert(17);
			ejemplo.tagDecendents();
			ejemplo2.tagDecendents();
		}
		
		@Test
		public void testTagHeightEjemplo() {
			other.tagHeight();
			other.filterTags("height");
			Assert.assertEquals("{10 [(height, 1)], {5 [(height, 2)], {2 [(height, 3)], ∅, ∅}, ∅}, {20 [(height, 2)], {15 [(height, 3)], {12 [(height, 4)], ∅, ∅}, ∅}, ∅}}",other.toString());
			ejemplo2.tagHeight();
			ejemplo2.insert(10,20);
			ejemplo2.tagHeight();
			ejemplo2.filterTags("height");
			Assert.assertEquals("{10 [(height, 1)], ∅, {20 [(height, 2)], ∅, ∅}}", ejemplo2.toString());
			
		}
		
		
		/*@Test
		public void testTagOnlySonEjemplo() {
		
		Assert.assertEquals(other.toString(), "{10, {5, {2, ∅, ∅}, ∅}, {20, {15, {12, ∅, ∅}, ∅}, ∅}}");
		Assert.assertEquals(3,other.tagOnlySonInorder());
		other.filterTags("onlySon");
		Assert.assertEquals("{10, {5, {2 [(onlySon, 1)], ∅, ∅}, ∅}, {20, {15 [(onlySon, 3)], {12 [(onlySon, 2)], ∅, ∅}, ∅}, ∅}}",other.toString());

		}*/
		
		@Test
		public void testContains() {
			Assert.assertTrue(ejemplo.contains(10));
			Assert.assertTrue(ejemplo.contains(15));
			Assert.assertTrue(ejemplo.contains(30));
			Assert.assertFalse(ejemplo.contains(31));
		}
		
		@Test(expected=IllegalArgumentException.class)
		public void containsTestNull() {
			ejemplo.contains(null);
		}
		
		@Test
		public void insertTest() {
			Assert.assertFalse(ejemplo.insert(10));
			Assert.assertEquals(ejemplo.toString(), "{10, {5, {2, ∅, ∅}, ∅}, {20, {15, ∅, ∅}, {30, ∅, ∅}}}");
			Assert.assertEquals(0, ejemplo.insert(2,null));
		}
		
		@Test(expected=IllegalArgumentException.class)
		public void insertIllegalTest() {
			ejemplo.insert((Integer)null);
		}
		
		@Test
		public void insertCollectionTest() {
			List<Integer> prueba=new LinkedList<Integer>();
			prueba.add(5);
			prueba.add(15);
			prueba.add(10);
			ejemplo2.insert(prueba);
		}
		

		@Test
		public void insertCollectionTestNull() {
			List<Integer> prueba=new LinkedList<Integer>();
			prueba.add(5);
			prueba.add(15);
			prueba.add(10);
			prueba.add(null);
			Assert.assertEquals(0,ejemplo2.insert(prueba));
		}
		

		
		

	
	}


