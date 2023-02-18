package Transporte;

public class Factory {
	
	public static Transporte construir(TipoVehiculo vehiculo) {
		switch(vehiculo) {
			case COCHE:
				return new Coche();
			case MOTO:
				return new Moto();
			default:
				return null;
		}
	}
}
