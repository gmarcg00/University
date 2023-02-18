package modelo.logica;

import modelo.dao.VehiculoDAO;
import modelo.vo.VehiculoVO;

public class Vehiculo {
	private VehiculoDAO vehiculo;
	
	public void setVehiculo(VehiculoVO vehiculo) {
		this.vehiculo=new VehiculoDAO();
		this.vehiculo.setVehiculo(vehiculo);
	}
}
