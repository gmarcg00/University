package modelo.logica;

import javax.swing.JOptionPane;


import modelo.dao.EmpleadoDAO;
import modelo.dao.UsuarioDAO;
import modelo.vo.EmpleadoVO;
import modelo.vo.UsuarioVO;

public class Empleado {
	private EmpleadoDAO empleado;
	private UsuarioDAO usuario;
	
	public void nuevoEmpleado(EmpleadoVO empleado) {
		this.empleado=new EmpleadoDAO();
		this.usuario=new UsuarioDAO();
		JOptionPane.showMessageDialog(null, "He llegado a la logica");
		this.usuario.setUsuario(empleado);
		this.empleado.setEmpleado(empleado);
		
	}
}
