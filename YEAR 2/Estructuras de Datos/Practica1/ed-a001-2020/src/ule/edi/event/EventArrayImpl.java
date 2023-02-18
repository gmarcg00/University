package ule.edi.event;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;

import ule.edi.model.*;
import ule.edi.model.Configuration.Type;


public class EventArrayImpl implements Event {
	
	private String name;
	private Date eventDate;
	private int nSeats;
	
	private Double price;    // precio de entradas 
	private Byte discountAdvanceSale;   // descuento en venta anticipada (0..100)
   	
	private Seat[] seats;
		
	
	
   public EventArrayImpl(String name, Date date, int nSeats){
	   //TODO 
	   // utiliza los precios por defecto: DEFAULT_PRICE y DEFAULT_DISCOUNT definidos en Configuration.java
	   this.name=name;
	   this.eventDate=date;
	   this.nSeats=nSeats;
	   
	   price=Configuration.DEFAULT_PRICE;
	   discountAdvanceSale=Configuration.DEFAULT_DISCOUNT;
	   
	  
	   // Debe crear los arrays de butacas gold y silver

	   seats=new Seat[nSeats];
	   
   }
   
   
   public EventArrayImpl(String name, Date date, int nSeats, Double price, Byte discount){
	   //TODO 
	   // Debe crear los arrays de butacas gold y silver
	   this.name=name;
	   this.eventDate=date;
	   this.nSeats=nSeats;
	   
	   this.price= price;
	   this.discountAdvanceSale=discount;   
	 
	   this.seats=new Seat[nSeats];   
   }


@Override
public String getName() {
	return this.name;
}


@Override
public Date getDateEvent() {
	return this.eventDate;
}


@Override
public Double getPrice() {
	return this.price;
}


@Override
public Byte getDiscountAdvanceSale() {
	return this.discountAdvanceSale;
}


@Override
public int getNumberOfSoldSeats() {
	int butacas=0;
	for(int i=0;i<seats.length;i++) {
		if(seats[i]!=null) {
			butacas++;
		}
	}
	return butacas;
}


@Override
public int getNumberOfNormalSaleSeats() {
	int resultado=0;
	for(int i=0;i<seats.length;i++) {
		if(seats[i]!=null) {
			if(seats[i].getType().equals(Type.NORMAL)) {
				resultado++;
			}
		}
	}
	return resultado;
}


@Override
public int getNumberOfAdvanceSaleSeats() {
	int resultado=0;
	for(int i=0;i<seats.length;i++) {
		if(seats[i]!=null) {
			if(seats[i].getType().equals(Type.ADVANCE_SALE)) {
				resultado++;
			}
		}
	}
	return resultado;
}


@Override
public int getNumberOfSeats() {
	return this.nSeats;
}


@Override
public int getNumberOfAvailableSeats() {
	int libres=0;
	for(int i=0;i<seats.length;i++) {
		if(seats[i]==null)
			libres++;
	}
	return libres;
}


@Override
public Seat getSeat(int pos) {
	if(seats[pos]==null)
		return null;
	else {
		return seats[pos];
	}
}


@Override
public Person refundSeat(int pos) {
	if(seats[pos]==null) {
		return null;
	}else {
		 Person person=seats[pos].getHolder();
		 seats[pos]=null;
		 return person;
	}
}


@Override
public boolean sellSeat(int pos, Person p, boolean advanceSale) {
	if(advanceSale) {
			if(seats[pos]==null) {
				seats[pos]=new Seat(this,pos+1,Type.ADVANCE_SALE,p);
				return true;
			}else {
				return false;
			}
	}else {
			if(seats[pos]==null) {
				seats[pos]=new Seat(this,pos+1,Type.NORMAL,p);
				return true;
			}else {
				return false;
		}
	}
}


@Override
public int getNumberOfAttendingChildren() {
	int menores=0,i;
	for(i=0;i<seats.length;i++) {
		if(seats[i]!=null) {
			if(seats[i].getHolder().getAge()<Configuration.CHILDREN_EXMAX_AGE) {
				menores++;
			}
		}
	}
	return menores;
}


@Override
public int getNumberOfAttendingAdults() {
	int mayores=0;
	mayores=getNumberOfSoldSeats()-getNumberOfAttendingChildren()-getNumberOfAttendingElderlyPeople();
	return mayores;
}


@Override
public int getNumberOfAttendingElderlyPeople() {
	int ancianos=0,i;
	for(i=0;i<seats.length;i++) {
		if(seats[i]!=null) {
			if(seats[i].getHolder().getAge()>=Configuration.ELDERLY_PERSON_INMIN_AGE) {
				ancianos++;
			}
		}
	}
	return ancianos;
}


@Override
public List<Integer> getAvailableSeatsList() {
	List<Integer> asientosLibres=new ArrayList<Integer>();
	for(int i=0;i<seats.length;i++) {
		if(seats[i]==null) {
			asientosLibres.add(i);
		}
	}
	return asientosLibres;
}


@Override
public List<Integer> getAdvanceSaleSeatsList() {
	List<Integer> asientosPremium=new ArrayList<Integer>();
	for(int i=0;i<seats.length;i++) {
		if(seats[i]!=null) {
			if(seats[i].getType().equals(Configuration.Type.ADVANCE_SALE))
				asientosPremium.add(i+1);
		}
			
	}
	return asientosPremium;
}


@Override
public int getMaxNumberConsecutiveSeats() {
	int consecutivas=0,max=0,i;
	for(i=0;i<seats.length;i++) {
		if(seats[i]==null)
			consecutivas++;
		else {
			if(consecutivas>max)
				max=consecutivas;
			consecutivas=0;
		}
	}
	return max;
}


@Override
public Double getPrice(Seat seat) {
	double precio=seat.getEvent().getPrice();
	if(seat.getType().equals(Configuration.Type.ADVANCE_SALE)){
			precio=precio-this.discountAdvanceSale;
	}else {
			precio=this.price;
	}
	return precio;
}


@Override
public Double getCollectionEvent() {
	double importe=0;
	for(int i=0;i<seats.length;i++) {
		if(seats[i]!=null) 
			importe=importe+seats[i].getEvent().getPrice(seats[i]);
	}
	return importe;
}


@Override
public int getPosPerson(Person p) {
	for(int i=0;i<seats.length;i++) {
		if(seats[i]!=null) {
			if(seats[i].getHolder().equals(p))
				return i+1;
		}
	}
	return -1;
}


@Override
public boolean isAdvanceSale(Person p) {
	for(int i=0;i<seats.length;i++) {
		if(seats[i]!=null) {
			if(seats[i].getHolder().equals(p)) {
				if(seats[i].getType().equals(Configuration.Type.ADVANCE_SALE))
					return true;	
			}	
		}
	}
	return false; 
}
  
}	