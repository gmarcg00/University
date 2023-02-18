package modelo.vo;

public class VehiculoVO {
	private String matricula;
	private String marca;
	private String modelo;
	private String nifCliente;
	
	public VehiculoVO(String matricula,String nifCliente,String marca, String modelo) {
		this.matricula=matricula;
		this.marca=marca;
		this.modelo=modelo;
		this.nifCliente=nifCliente;
	}
	
	public String getMatricula() {
		return matricula;
	}
	public void setMatricula(String matricula) {
		this.matricula = matricula;
	}
	public String getMarca() {
		return marca;
	}
	public void setMarca(String marca) {
		this.marca = marca;
	}
	public String getModelo() {
		return modelo;
	}
	public void setModelo(String modelo) {
		this.modelo = modelo;
	}

	public String getNifCliente() {
		return nifCliente;
	}

	public void setNifCliente(String nifCliente) {
		this.nifCliente = nifCliente;
	}
}
