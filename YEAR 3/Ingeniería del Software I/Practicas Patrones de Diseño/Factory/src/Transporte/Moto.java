package Transporte;

public class Moto implements Transporte {

	@Override
	public void arrancar() {
		System.out.println("Estoy arrancando la moto...");
		
	}

	@Override
	public void frenar() {
		System.out.println("Estoy frenando la moto...");
		
	}

	@Override
	public void aparcar() {
		System.out.println("Estoy aparcando la moto...");
		
	}
}
