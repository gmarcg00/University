package modelo.logica;

import javax.swing.JOptionPane;

import modelo.dao.ClienteDAO;
import modelo.dao.UsuarioDAO;
import modelo.vo.ClienteVO;


public class Cliente extends Usuario{
	private ClienteDAO cliente;
	private UsuarioDAO usuario;
	
	public ClienteVO nuevoCliente(ClienteVO cliente) {
		this.cliente=new ClienteDAO();
		this.usuario=new UsuarioDAO();
		JOptionPane.showMessageDialog(null, "He llegado a la logica");
		this.usuario.setUsuario(cliente);
		
		return this.cliente.setCliente(cliente);
	}
}
