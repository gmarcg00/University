package controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

import modelo.logica.Usuario;
import modelo.vo.UsuarioVO;
import vista.VentanaInicioEncargado;
import vista.VentanaInicioMecanico;
import vista.VentanaLoginTrabajador;

public class ControladorLoginTrabajador implements ActionListener{
	private VentanaLoginTrabajador vista;
	private Usuario modeloUsuario;
	
	public ControladorLoginTrabajador(VentanaLoginTrabajador vista,Usuario modelo ) {
		this.vista=vista;
		this.modeloUsuario=modelo;
	}
	
	public void setActionBoton() {
		vista.getBtnIniciarSesion().addActionListener(this);
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		 UsuarioVO usuario = new UsuarioVO(vista.getTextFieldNIF(), vista.getTextField_Contraseña(),null,null,null,null,null,null,null);
		 Usuario trabajador=new Usuario();
		 if(trabajador.isUsuario(usuario)!=null) {
			 JOptionPane.showMessageDialog(null, "Bienvenido de nuevo "+usuario.getNombre());
			 if(usuario.getTipo().equals("Mecanico")) {
				 VentanaInicioMecanico ventana = new VentanaInicioMecanico(vista,usuario);
				 ventana.setVisible(true);
			 }else if(usuario.getTipo().equals("Encargados")) {
				 VentanaInicioEncargado ventana = new VentanaInicioEncargado(vista,usuario);
				 ventana.setVisible(true);
			 }
			 vista.setVisible(false);
		 }else {
			 JOptionPane.showMessageDialog(null, "Usuario no registrado en el sistema");
		 }
		
	}

}
