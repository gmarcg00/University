package modelo.logica;

import modelo.dao.ProveedorDAO;
import modelo.vo.ProveedorVO;

public class Proveedor {
	private ProveedorDAO proveedor;
	
	
	public  ProveedorVO nuevoProveedor(ProveedorVO proveedor) {
		this.proveedor=new ProveedorDAO();
		return this.proveedor.setProveedor(proveedor);
	}
}
