
public class Motor implements Observador {

	public Motor() {
		
	}
	
	@Override
	public void update() {
		//Acci�n a realizar tras haber sido notificado
		System.out.println("Aumenta la velocidad");
	}

}
