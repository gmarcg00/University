package modelo.vo;

public class MecanicoVO extends EmpleadoVO{
	public MecanicoVO(String nif,String password,String cifEmpresa,String nombre, String apellidos, String direccion, String telefono, String email,double sueldo, String tipo) {
		super(nif,password,cifEmpresa,nombre, apellidos,direccion, telefono, email, sueldo, tipo);
	}
}
