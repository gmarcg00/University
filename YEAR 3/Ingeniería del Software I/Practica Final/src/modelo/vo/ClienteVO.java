package modelo.vo;

import modelo.vo.UsuarioVO;

public class ClienteVO extends UsuarioVO{
	public ClienteVO(String nif,String password,String cifEmpresa,String nombre, String apellidos, String direccion, String telefono, String email, String tipo) {
		super(nif,password,cifEmpresa,nombre, apellidos, direccion, telefono, email, tipo);
		// TODO Auto-generated constructor stub
	}
}
