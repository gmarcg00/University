package controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

import modelo.logica.Cliente;
import modelo.vo.ClienteVO;
import vista.VentanaInicioCliente;
import vista.VentanaPerfilCliente;
import vista.VentanaRegistroCliente;

public class ControladorRegistroCliente implements ActionListener{
	private VentanaRegistroCliente vista;
	private Cliente modelo;
	
	public ControladorRegistroCliente(VentanaRegistroCliente vista,Cliente modelo ) {
		this.vista=vista;
		this.modelo=modelo;
		
	}
	
	public void setActionBoton() {
		vista.getRegistrarse().addActionListener(this);
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		ClienteVO cliente=new ClienteVO(vista.getNIF(),vista.getPassword(),"B67657189",vista.getNombre(),vista.getApellidos(),vista.getDireccion(),vista.getTelefono(),vista.getEmail(), "Cliente");
		if(modelo.nuevoCliente(cliente)!=null) {
			JOptionPane.showMessageDialog(vista, "Se ha registrado correctamente");
			VentanaInicioCliente ventana= new VentanaInicioCliente(vista,cliente);
			ventana.setVisible(true);
		}else {
			JOptionPane.showMessageDialog(vista, "Ha ocurrido un error en el registro");
		}
		vista.setVisible(false);
		
	}
}
