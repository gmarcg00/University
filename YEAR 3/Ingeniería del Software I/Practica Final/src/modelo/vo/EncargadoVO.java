package modelo.vo;

public class EncargadoVO extends EmpleadoVO{
	public EncargadoVO(String nif,String password,String cifEmpresa,String nombre, String apellidos, String direccion, String telefono, String email,double sueldo, String tipo) {
		super(nif,password,cifEmpresa,nombre, apellidos, direccion, telefono, email,sueldo,tipo);
	}
}
