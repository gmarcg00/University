import Transporte.Factory;
import Transporte.Transporte;
import Transporte.TipoVehiculo;

public class Application {

	public static void main(String[] args) {
		Transporte t1=Factory.construir(TipoVehiculo.MOTO);
		
		t1.arrancar();
		
		t1.frenar();
		
		t1.aparcar();
	}

}
