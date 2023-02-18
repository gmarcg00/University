package modelo.vo;

public class EmpleadoVO extends UsuarioVO{
	private double sueldo;
	
	public EmpleadoVO(String nif,String password,String cifEmpresa,String nombre, String apellidos, String direccion, String telefono, String email,double sueldo, String tipo) {
		super(nif,password,cifEmpresa,nombre,apellidos,direccion,telefono,email,tipo);
		setSueldo(sueldo);
	}

	public double getSueldo() {
		return sueldo;
	}

	public void setSueldo(double sueldo) {
		this.sueldo = sueldo;
	}
}
