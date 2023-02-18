package modelo.vo;

import modelo.logica.Cliente;
import modelo.logica.TipoDePago;

public class FacturaVO {
	private int idFactura;
	private String cifEmpresa;
	private String nombreEmpresa;
	private String nifCliente;
	private double precioTotal;
	private TipoDePago tipoPago;
	private String fecha;
	
	public FacturaVO(int idFactura,String cifEmpresa,String nombreEmpresa ,String nifCliente,double precioTotal, TipoDePago tipoPago,String fecha) {
		setIdFactura(idFactura);
		setCifEmpresa(cifEmpresa);
		setNifCliente(nifCliente);
		setPrecioTotal(precioTotal);
		setTipoPago(tipoPago);
		setFecha(fecha);
		setNombreEmpresa(nombreEmpresa);
	}

	public int getIdFactura() {
		return idFactura;
	}

	public void setIdFactura(int idFactura) {
		this.idFactura = idFactura;
	}

	public double getPrecioTotal() {
		return precioTotal;
	}

	public void setPrecioTotal(double precioTotal) {
		this.precioTotal = precioTotal;
	}

	
	public String getTipoPago() {
		return tipoPago.toString();
	}

	public void setTipoPago(TipoDePago tipoPago) {
		this.tipoPago = tipoPago;
	}

	public String getNifCliente() {
		return nifCliente;
	}

	public void setNifCliente(String nifCliente) {
		this.nifCliente = nifCliente;
	}

	public String getFecha() {
		return fecha;
	}

	public void setFecha(String fecha) {
		this.fecha = fecha;
	}

	public String getCifEmpresa() {
		return cifEmpresa;
	}

	public void setCifEmpresa(String cifEmpresa) {
		this.cifEmpresa = cifEmpresa;
	}

	public String getNombreEmpresa() {
		return nombreEmpresa;
	}

	public void setNombreEmpresa(String nombreEmpresa) {
		this.nombreEmpresa = nombreEmpresa;
	}
}
