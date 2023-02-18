package modelo.vo;

import modelo.logica.EstadoReparacion;
import modelo.logica.Mecanico;
import modelo.logica.TipoReparacion;

public class ReparacionVO {

	private int idReparacion;
	private double precio;
	private boolean pagada;
	private EstadoReparacion estado;
	private String nifMecanico;
	private int idFactura;
	private String nifCliente;
	private TipoReparacion tipo;
	private String matricula;
	private String descripcion;
	
	
	public ReparacionVO(int idReparacion, String matricula,int idFactura, double precio,boolean pagada,EstadoReparacion estado,String nifMecanico,String nifCliente,TipoReparacion tipo,String descripcion ) {
		setIdReparacion(idReparacion);
		setMatricula(matricula);
		setIdFactura(idFactura);
		setPrecio(precio);
		setPagada(pagada);
		setEstado(estado);
		setNifMecanico(nifMecanico);
		setNifCliente(nifCliente);
		setTipo(tipo);
		setDescripcion(descripcion);
	}

	public int getIdReparacion() {
		return idReparacion;
	}

	public void setIdReparacion(int idReparacion) {
		this.idReparacion = idReparacion;
	}

	public double getPrecio() {
		return precio;
	}

	public void setPrecio(double precio) {
		this.precio = precio;
	}

	public boolean isPagada() {
		return pagada;
	}

	public void setPagada(boolean pagada) {
		this.pagada = pagada;
	}

	public EstadoReparacion getEstado() {
		return estado;
	}

	public void setEstado(EstadoReparacion estado) {
		this.estado = estado;
	}


	public String getTipo() {
		return tipo.toString();
	}

	public void setTipo(TipoReparacion tipo) {
		this.tipo = tipo;
	}

	public String getNifMecanico() {
		return nifMecanico;
	}

	public void setNifMecanico(String nifMecanico) {
		this.nifMecanico = nifMecanico;
	}

	public String getMatricula() {
		return matricula;
	}

	public void setMatricula(String matricula) {
		this.matricula = matricula;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public int getIdFactura() {
		return idFactura;
	}

	public void setIdFactura(int idFactura2) {
		this.idFactura = idFactura2;
	}

	public String getNifCliente() {
		return nifCliente;
	}

	public void setNifCliente(String nifCliente) {
		this.nifCliente = nifCliente;
	}

}
