package modelo.logica;

import javax.swing.JOptionPane;

import modelo.dao.EmpleadoDAO;
import modelo.dao.EncargadoDAO;
import modelo.dao.MecanicoDAO;
import modelo.dao.UsuarioDAO;
import modelo.vo.EmpleadoVO;
import modelo.vo.MecanicoVO;
import modelo.vo.UsuarioVO;

public class Mecanico {
	private MecanicoDAO mecanico;
	private UsuarioDAO usuario;
	private EmpleadoDAO empleado;
	
	public Mecanico() {
		this.mecanico=new MecanicoDAO();
		this.usuario=new UsuarioDAO();
		this.empleado=new EmpleadoDAO();
		
	}
	
	public MecanicoVO nuevoMecanico(MecanicoVO mecanico) {
		this.usuario.setUsuario(mecanico);
		this.empleado.setEmpleado(mecanico);
		return this.mecanico.setMecanico(mecanico);
		
	}
	
	
	public int despedirMecanico(MecanicoVO mecanico) {
		this.mecanico.removeMecanico(mecanico);
		this.mecanico.removeEmpleado(mecanico);
		return this.mecanico.removeUsuario(mecanico);
	}
	
	
}
