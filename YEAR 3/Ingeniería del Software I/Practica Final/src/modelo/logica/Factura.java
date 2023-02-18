package modelo.logica;

import java.util.ArrayList;

import modelo.dao.FacturaDAO;
import modelo.vo.FacturaVO;
import modelo.vo.UsuarioVO;

public class Factura {
	private FacturaDAO factura;
	
	
	public Factura() {
		this.factura=new FacturaDAO();
	}
	public FacturaVO nuevaFactura(FacturaVO factura) {
		return this.factura.setFactura(factura);
	}
	
	public int getId() {
		return this.factura.getIdFactura();
	}
	
	public boolean cambiarPago(FacturaVO factura) {
		return this.factura.cambiarPago(factura);
		
	}
	
	public ArrayList<FacturaVO> getFacturas(UsuarioVO usuario,ArrayList<FacturaVO> listaFacturas){
		return factura.getFactura(usuario,listaFacturas);
	}
	
	
}
