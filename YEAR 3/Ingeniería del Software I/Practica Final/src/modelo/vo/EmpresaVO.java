package modelo.vo;

public class EmpresaVO {
	private String cif;
	private String nombre;
	private String direccion;
	private String ciudad;
	private String pais;
	private String telefono;
	
	public EmpresaVO(String cif, String nombre, String direccion, String ciudad, String pais,String telefono) {
		this.cif=cif;
		this.nombre=nombre;
		this.direccion=direccion;
		this.ciudad=ciudad;
		this.pais=pais;
		this.telefono=telefono;
	}
	
	public String getCif() {
		return cif;
	}
	public void setCif(String cif) {
		this.cif = cif;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getDireccion() {
		return direccion;
	}
	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}
	public String getCiudad() {
		return ciudad;
	}
	public void setCiudad(String ciudad) {
		this.ciudad = ciudad;
	}
	public String getPais() {
		return pais;
	}
	public void setPais(String pais) {
		this.pais = pais;
	}

	public String getTelefono() {
		return telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}
	
}
