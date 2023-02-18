package ule.edi.recursiveList;


import org.junit.*;

import ule.edi.exceptions.EmptyCollectionException;
import ule.edi.model.Person;

public class OrderedLinkedListTests {

	private OrderedLinkedListImpl<String> lA4B2;
	private OrderedLinkedListImpl<String> listaConElems;
	private OrderedLinkedListImpl<String> l2;
	
	private Person person1, person2, person3, person4;
	


	@Before
	public void setupFixture() {
		lA4B2 = new OrderedLinkedListImpl<String>("A", "B", "A", "A", "B", "A");
		listaConElems=new OrderedLinkedListImpl<String>("C", "X", "A", "D", "B", "Z");
		l2=new OrderedLinkedListImpl<>();
		person1=new Person("10203040A", "Ana", 20);
		person2=new Person("20304050A", "Pedro", 18);
		person3=new Person("01020304A", "Sara", 16);
		person4=new Person("30405060A", "Pablo", 30);

	}
	
	
	@Test
	public void testConstructsEmpty() {
		Assert.assertTrue(new OrderedLinkedListImpl<>().isEmpty());
	}
	
	@Test
	public void addTest() {
		l2.add("B");
		Assert.assertEquals(l2.toString(),"(B )");
		l2.add("A");
		Assert.assertEquals(l2.toString(),"(A B )");
		l2.add("Z");
		Assert.assertEquals(l2.toString(),"(A B Z )");
		
		
		
	}
	
	@Test(expected=NullPointerException.class)
	public void addTestNull() {
		l2.add(null);
	}
	
	

	// tests isOrdered
	// todas las listas OrderedLinkedListImpl deben ser ordenadas
	@Test
	public void testIsOrdered() {
		// todas las orderedLists deben estar ordenadas
		Assert.assertEquals(lA4B2.toString(),"(A A A A B B )");
		Assert.assertTrue(lA4B2.isOrdered());
		Assert.assertTrue((new OrderedLinkedListImpl<String>("A","B")).isOrdered());
		Assert.assertTrue((new OrderedLinkedListImpl<String>()).isOrdered());		
	}

	// Tests removeDuplicates en ordenada
	@Test
	public void testRemoveDuplicates() throws EmptyCollectionException {
		Assert.assertEquals(lA4B2.removeDuplicates(),4); // 3 A + 1B repetidas
		Assert.assertEquals(lA4B2.removeDuplicates(),0); // 0 repetids
		Assert.assertEquals(lA4B2.toString(), "(A B )");	
	}
	
	@Test(expected=EmptyCollectionException.class)
	public void testRemoveDuplicatesEmpty() throws EmptyCollectionException {
		l2.removeDuplicates();
	}

	// Tests con personas
	@Test
	public void testInsertPersons() {
		OrderedLinkedListImpl<Person> lista=new OrderedLinkedListImpl<Person>(person1, person2, person3, person4);
		Assert.assertEquals(lista.toString(),"({01020304A, Sara, 16} {20304050A, Pedro, 18} {10203040A, Ana, 20} {30405060A, Pablo, 30} )");
		Assert.assertFalse(lista.isOrdered());
	}
	
	@Test
	public void testPerson() {
		Assert.assertFalse(person1.equals(person2));
		Assert.assertTrue(person1.equals(person1));
		Assert.assertEquals(person1.getName(),"Ana");
		person1.setName("Silvia");
		Assert.assertEquals(person1.getNif(),"10203040A");
		person1.setNif("10203040D");
		person1.setAge(17);
		Assert.assertFalse(person1.equals("hola"));
		
	}
	
	
	
	// AÑADIR MAS TESTS para el resto de casos especiales 
	
	
}
