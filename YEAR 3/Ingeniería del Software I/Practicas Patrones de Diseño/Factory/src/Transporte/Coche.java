package Transporte;

public class Coche implements Transporte {

	@Override
	public void arrancar() {
		System.out.println("Estoy arrancando el coche...");
		
	}

	@Override
	public void frenar() {
		System.out.println("Estoy frenando el coche...");
		
	}

	@Override
	public void aparcar() {
		System.out.println("Estoy aparcando el coche...");
		
	}
	
}
