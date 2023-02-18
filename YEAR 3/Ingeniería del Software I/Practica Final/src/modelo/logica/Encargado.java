package modelo.logica;

import javax.swing.JOptionPane;

import modelo.dao.EmpleadoDAO;
import modelo.dao.EncargadoDAO;
import modelo.dao.UsuarioDAO;
import modelo.vo.EmpleadoVO;

public class Encargado {
	private EncargadoDAO encargado;
	private UsuarioDAO usuario;
	private EmpleadoDAO empleado;
	
	public void nuevoEncargado(EmpleadoVO encargado) {
		this.encargado=new EncargadoDAO();
		this.usuario=new UsuarioDAO();
		this.empleado=new EmpleadoDAO();
		
		JOptionPane.showMessageDialog(null, "He llegado a la logica");
		this.usuario.setUsuario(encargado);
		this.empleado.setEmpleado(encargado);
		this.encargado.setEncargado(encargado);
		
	}
	

}
