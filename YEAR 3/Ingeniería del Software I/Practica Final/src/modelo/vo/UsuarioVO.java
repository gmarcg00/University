package modelo.vo;

public class UsuarioVO {
	
	private String nif;
	private String cifEmpresa;
	private String password;
	private String nombre;
	private String apellidos;
	private String direccion;
	private String telefono;
	private String email;
	private String tipo;
	
	
	public UsuarioVO(String nif,String password,String cifEmpresa,String nombre, String apellidos, String direccion, String telefono, String email, String tipo) {
		this.setNif(nif);
		this.setPassword(password);
		this.setCifEmpresa(cifEmpresa);
		this.setNombre(nombre);
		this.setApellidos(apellidos);
		this.setDireccion(direccion);
		this.setTelefono(telefono);
		this.setEmail(email);
		this.setTipo(tipo);
	}
	
	public UsuarioVO() {
		
	}
	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getApellidos() {
		return apellidos;
	}

	public void setApellidos(String apellidos) {
		this.apellidos = apellidos;
	}
	public String getNif() {
		return nif;
	}
	public void setNif(String nif) {
		this.nif = nif;
	}
	public String getDireccion() {
		return direccion;
	}
	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getTelefono() {
		return telefono;
	}
	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getCifEmpresa() {
		return cifEmpresa;
	}
	public void setCifEmpresa(String cifEmpresa) {
		this.cifEmpresa = cifEmpresa;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

}
