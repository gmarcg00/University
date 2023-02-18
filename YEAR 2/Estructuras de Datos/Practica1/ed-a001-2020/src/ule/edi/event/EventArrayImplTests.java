package ule.edi.event;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import org.junit.*;

import ule.edi.model.*;
import ule.edi.model.Configuration.Type;

public class EventArrayImplTests {

	private DateFormat dformat = null;
	private EventArrayImpl e,b,c,d;
	
	private Date parseLocalDate(String spec) throws ParseException {
        return dformat.parse(spec);
	}

	public EventArrayImplTests() {
		
		dformat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
	}
	
	@Before
	public void testBefore() throws Exception{
	    e = new EventArrayImpl("The Fabulous Five", parseLocalDate("24/02/2018 17:00:00"), 110);
	    b=new EventArrayImpl("Alicia en el pais de las maravillas",parseLocalDate("24/02/2018 17:00:00"),110,50.0,(byte) 4);
	    c=new EventArrayImpl("Alicia",parseLocalDate("24/02/2018 17:00:00"),110,50.0,(byte) 4);
	    d=new EventArrayImpl("dfsdfds",parseLocalDate("24/02/2018 17:00:00"),3,50.0,(byte) 4);
	    
	    
	    
	    Person prueba= new Person("Guille","7171443w",20);
	    Person prueba2= new Person("Alex","7171434t",80);
	    
	    
	    
	    b.sellSeat(2, prueba, true);
	    b.sellSeat(4, prueba2, false);
	    e.sellSeat(2, prueba, false);
	    e.sellSeat(4, prueba2, true);
	   

	}
	
	@Test
	public void testgetName() {
		Assert.assertEquals(e.getName(),"The Fabulous Five");
	}
	
	@Test
	public void testgetDateEvent() throws ParseException {
		Assert.assertEquals(e.getDateEvent(),parseLocalDate("24/02/2018 17:00:00"));
	}
	
	@Test
	public void testgetPrice() {
		Assert.assertEquals(50,b.getPrice(),0);
	}
	
	@Test
	public void testgetDiscountAdvanceSale() {
		Assert.assertEquals(4, b.getDiscountAdvanceSale(),0);
	}
	
	@Test
	public void testgetNumberOfSoldSeats() {
		Assert.assertEquals(2, e.getNumberOfSoldSeats());
	}
	
	@Test
	public void testgetNumberOfNormalSaleSeats() {
		Assert.assertEquals(1, e.getNumberOfNormalSaleSeats());
	}
	
	@Test
	public void testgetNumberOfAdvanceSaleSeats() {
		Assert.assertEquals(1, b.getNumberOfAdvanceSaleSeats(),0);
	}
	
	@Test 
	public void testgetNumberOfSeats() {
		Assert.assertEquals(110,e.getNumberOfSeats());
	}
	
	@Test
	public void testgetSeat() {
		Person prueba= new Person("Guille","7171443w",20);
		Seat seat1=new Seat(e,2,Type.NORMAL,prueba);
		Assert.assertNull(e.getSeat(6));
		Assert.assertTrue(e.getSeat(2).getHolder().equals(prueba));
		
	}
	
	@Test 
	public void testRefundSeat() {
		Assert.assertNull(e.refundSeat(8));
		Person prueba= new Person("Guille","7171443w",20);
		Assert.assertTrue(e.refundSeat(2).equals(prueba));
		
	}
	
	@Test
	public void testSellSeat() {
		Person prueba= new Person("Guille","7171443w",20);
		Assert.assertTrue(b.sellSeat(12,prueba,true));
		Assert.assertFalse(b.sellSeat(2, prueba, false));
		Assert.assertFalse(b.sellSeat(12,prueba,true));
		 
	}
	
	@Test
	public void testgetNumberOfAttendingChildren() {
		Assert.assertEquals(0,e.getNumberOfAttendingChildren());
		Person prueba3= new Person("Alex","7171434t",10);
		 e.sellSeat(8,prueba3,false);
		 Assert.assertEquals(1,e.getNumberOfAttendingChildren());
		
		
		
	}
	
	@Test
	public void testgetNumberOfAttendingAdults() {
		Assert.assertEquals(1,e.getNumberOfAttendingAdults());
		
	}
	
	@Test
	public void testgetNumberOfAttendingElderlyPeople() {
		Person prueba2= new Person("Guille","7171443w",80);
		
		Assert.assertEquals(1,e.getNumberOfAttendingElderlyPeople());;
	}
	
	@Test
	public void testgetAvailableSeatsList() {
		//List<Integer> asientosLibres=new ArrayList<>(Arrays.asList(3,3,2));
		List<Integer> asientosLibres2=new ArrayList<Integer>();
		Person prueba2= new Person("Guille","7171443w",80);
		d.sellSeat(0, prueba2, false);
		for(int i=1;i<=d.getNumberOfAvailableSeats();i++) {
			asientosLibres2.add(i);
		}
		Assert.assertEquals(d.getAvailableSeatsList(),asientosLibres2);
	}
	
	@Test
	public void testgetAdvanceSaleSeatsList() {
		List<Integer> asientosLibres2=new ArrayList<Integer>();
		Person prueba2= new Person("Guille","7171443w",80);
		d.sellSeat(0, prueba2, true);
		d.sellSeat(1, prueba2, false);
		for(int i=1;i<=d.getNumberOfAdvanceSaleSeats();i++) {
			asientosLibres2.add(i);
		}
		Assert.assertEquals(d.getAdvanceSaleSeatsList(),asientosLibres2);
		
	}
	
	@Test
	public void testgetMaxNumberConsecutiveSeats() {
		Person prueba2= new Person("Guille","7171443w",80);
		d.sellSeat(1, prueba2, true);
		d.sellSeat(2, prueba2, true);
		Assert.assertEquals(1, d.getMaxNumberConsecutiveSeats());
		
		
	}
	
	@Test
	public void testgetPrice2() {
		Person prueba2= new Person("Guille","7171443w",80);
		Seat seat=new Seat(d,2,Type.ADVANCE_SALE,prueba2);
		Seat seat3=new Seat(b,2,Type.NORMAL,prueba2);
		Assert.assertEquals(46, d.getPrice(seat),0);
		Assert.assertEquals(50,d.getPrice(seat3),0);
		
	}
	
	@Test
	public void testgetPosPerson() {
		Person prueba= new Person("Guille","7171443w",20);
		Person prueba2= new Person("Alex","7171434t",80);
		d.sellSeat(0, prueba2, true);
		Assert.assertEquals(1,d.getPosPerson(prueba2));
		Assert.assertEquals(-1,d.getPosPerson(prueba));
	}
	
	@Test
	public void testisAdvanceSale() {
		 Person prueba= new Person("Guille","7171443w",20);
		Person prueba2= new Person("Alex","7171434t",80);
		d.sellSeat(0, prueba, true);
		d.sellSeat(1, prueba2, false);
		Assert.assertTrue(d.isAdvanceSale(prueba));
		Assert.assertFalse(d.isAdvanceSale(prueba2));
		
	}
	
	@Test
	public void testEventoVacio() throws Exception {
		
		Assert.assertEquals(e.getNumberOfAvailableSeats(), 108);
	    Assert.assertTrue(e.getNumberOfAvailableSeats()==108);
	    Assert.assertEquals(e.getNumberOfAttendingAdults(), 1);
	}
	
	
	@Test
	public void testSellSeat1Adult() throws Exception{
		
			
	    Assert.assertEquals(e.getNumberOfAttendingAdults(), 1);
		Assert.assertTrue(e.sellSeat(1, new Person("10203040A","Alice", 34),false));	//venta normal
	    Assert.assertEquals(e.getNumberOfAttendingAdults(), 2);  
	    Assert.assertEquals(e.getNumberOfNormalSaleSeats(), 2);
	  
	}
	

	
	@Test
	public void testgetCollection() throws Exception{
		Event  ep = new EventArrayImpl("The Fabulous Five", parseLocalDate("24/02/2018 17:00:00"), 4);
		Assert.assertEquals(ep.sellSeat(1, new Person("1010", "AA", 10), true),true);
		Assert.assertEquals(75,ep.getCollectionEvent(),0);
		Assert.assertTrue(ep.getCollectionEvent()==75);	
		Assert.assertEquals(96,b.getCollectionEvent(),0);
	}
	
	@Test
	public void testEquals() {
		Person prueba= new Person("Guille","7171443w",20);
		Seat seat3=new Seat(b,2,Type.NORMAL,prueba);
		Assert.assertFalse(prueba.equals(seat3));
	}
	
}
