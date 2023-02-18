package modelo.logica;

import modelo.dao.UsuarioDAO;
import modelo.vo.UsuarioVO;

public class Usuario {
	private UsuarioDAO usuario;
	
	public UsuarioVO isUsuario(UsuarioVO usuario) {
		this.usuario=new UsuarioDAO();
		return this.usuario.getUsuario(usuario);	
	}
}
