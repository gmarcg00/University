package controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

import modelo.logica.Mecanico;
import modelo.logica.Recambio;
import modelo.logica.Reparacion;
import modelo.logica.Usuario;
import modelo.logica.Vehiculo;
import modelo.vo.MecanicoVO;
import modelo.vo.UsuarioVO;
import vista.VentanaContratarMecanico;
import vista.VentanaInicioEncargado;
import vista.VentanaInicioEncargado;

public class ControladorInicioEncargado implements ActionListener{

	private UsuarioVO usuario;
	private VentanaInicioEncargado vista;

	public ControladorInicioEncargado(VentanaInicioEncargado vista, UsuarioVO usuario) {
		this.vista = vista;
		this.usuario=usuario;
		
	}
	
	public void setBoton() {
		vista.getBtnContratar().addActionListener(this);
		vista.getBtnDespedir().addActionListener(this);
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		if(arg0.getSource().equals(vista.getBtnContratar())) {
			VentanaContratarMecanico ventana=new VentanaContratarMecanico();
				ventana.setVisible(true);
		}
		
		if(arg0.getSource().equals(vista.getBtnDespedir())) {
			String value=null;
			value = JOptionPane.showInputDialog(vista, "Introduce el NIF del mecánico", "Despedir Mecánico",
					JOptionPane.DEFAULT_OPTION);
			if(value!=null) {
				UsuarioVO usuario=new UsuarioVO(value,null,null,null,null,null,null,null,null);
				Usuario user=new Usuario();
				usuario=user.isUsuario(usuario);
				if(usuario!=null) {
					MecanicoVO mecanico =new MecanicoVO(usuario.getNif(),usuario.getPassword(),usuario.getCifEmpresa(),usuario.getNombre(),usuario.getApellidos(),usuario.getDireccion(),usuario.getTelefono(),usuario.getEmail(),0.0,"Mecanico");
					Mecanico mec =new Mecanico();
					if(mec.despedirMecanico(mecanico)>0) {
						JOptionPane.showInputDialog(vista,usuario.getNombre()+" "+usuario.getApellidos()+ " ha sido despedido");
					}else {
						JOptionPane.showInputDialog(vista,"Error al eliminar mecánico");
					}
				}else {
					JOptionPane.showMessageDialog(vista,"No existe ningún usuario registrado en el sistema con ese NIF");
				}
			}
		
		}
	}
	
}
