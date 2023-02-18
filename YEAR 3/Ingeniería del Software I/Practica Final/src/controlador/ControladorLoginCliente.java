package controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

import modelo.dao.UsuarioDAO;
import modelo.logica.Cliente;
import modelo.logica.Usuario;
import modelo.vo.ClienteVO;
import modelo.vo.UsuarioVO;
import vista.VentanaInicioAplicacion;
import vista.VentanaInicioCliente;
import vista.VentanaLoginCliente;
import vista.VentanaRegistroCliente;

public class ControladorLoginCliente implements ActionListener {
	private VentanaLoginCliente vista;
	private Usuario modeloUsuario;
	
	public ControladorLoginCliente(VentanaLoginCliente vista,Usuario modelo ) {
		this.vista=vista;
		this.modeloUsuario=modelo;
	}
	
	public void setActionBoton() {
		vista.getBtnIniciarSesion().addActionListener(this);
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		 UsuarioVO usuario = new UsuarioVO(vista.getNIF(), vista.getPassword(),null,null,null,null,null,null,"Cliente");
		 if(modeloUsuario.isUsuario(usuario)!=null && usuario.getTipo().equals("Cliente")) {
				 JOptionPane.showMessageDialog(this.vista, "Bienvenido de nuevo "+usuario.getNombre());
				 VentanaInicioCliente ventana = new VentanaInicioCliente(this.vista,usuario);
				 ventana.setVisible(true);
				 vista.setVisible(false);
		 }else {
			 JOptionPane.showMessageDialog(null, "Cliente no registrado en el sistema");
		 }
		
	}

}
