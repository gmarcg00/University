package vista;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import java.awt.BorderLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JLabel;

import controlador.ControladorInicioCliente;
import controlador.ControladorRegistroCliente;
import modelo.logica.Cliente;
import modelo.logica.Recambio;
import modelo.logica.Reparacion;
import modelo.logica.Vehiculo;
import modelo.vo.UsuarioVO;

import java.awt.Font;
import javax.swing.JButton;

public class VentanaInicioCliente extends JFrame{

	private Reparacion modeloReparacion;
	private Recambio modeloRecambio;
	private Vehiculo modeloVehiculo;
	private JButton btnNuevaReparacion;
	private JButton btnComprarRecambios;
	private JButton btnVerPerfil;
	private JButton btnSalir;

	
	public VentanaInicioCliente(VentanaRegistroCliente vista,UsuarioVO usuario) {
		setBounds(130, 130, 450, 700);
		setTitle("Inicio Cliente");
		getContentPane().setLayout(null);
		inicializate();
		setControlador(modeloReparacion, modeloRecambio,modeloVehiculo,usuario);
		
	}
	
	/**
	 * @wbp.parser.constructor
	 */
	public VentanaInicioCliente(VentanaLoginCliente ventanaRegistroCliente,UsuarioVO usuario ) {
		setBounds(130, 130, 450, 700);
		setTitle("Inicio Cliente");
		getContentPane().setLayout(null);
		inicializate();
		setControlador(modeloReparacion, modeloRecambio,modeloVehiculo,usuario);
	}
	
	public void setControlador(Reparacion modeloReparacion, Recambio modeloRecambio,Vehiculo modeloVehiculo, UsuarioVO usuario) {
		modeloReparacion = new Reparacion();
		modeloRecambio = new Recambio();
		modeloVehiculo = new Vehiculo();
		ControladorInicioCliente control=new ControladorInicioCliente(VentanaInicioCliente.this,modeloReparacion, modeloRecambio,modeloVehiculo,usuario);
		control.setActionBoton();
	}

	public void inicializate() {
		
		JLabel lblLogo = new JLabel("");
		lblLogo.setBounds(31, 27, 165, 103);
		ImageIcon imagen=new ImageIcon("src/imagenes/logo.jpeg");
		Icon icono=new ImageIcon(imagen.getImage().getScaledInstance(lblLogo.getWidth(),lblLogo.getHeight(),Image.SCALE_DEFAULT));
		lblLogo.setIcon(icono);
		
		this.getContentPane().add(lblLogo);
		
		JLabel lblInicioCliente = new JLabel("Inicio Cliente");
		lblInicioCliente.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblInicioCliente.setBounds(260, 47, 127, 58);
		getContentPane().add(lblInicioCliente);
		
		JLabel lblAyudar = new JLabel("\u00BFEn que podemos ayudarle?");
		lblAyudar.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblAyudar.setBounds(106, 154, 246, 29);
		getContentPane().add(lblAyudar);
		
		btnNuevaReparacion = new JButton("Nueva Reparacion");
		btnNuevaReparacion.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnNuevaReparacion.setBounds(106, 229, 223, 65);
		getContentPane().add(btnNuevaReparacion);
		
		btnComprarRecambios = new JButton("Comprar Recambios");
		btnComprarRecambios.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnComprarRecambios.setBounds(106, 325, 223, 65);
		getContentPane().add(btnComprarRecambios);
		
		btnVerPerfil = new JButton("Ver Perfil");
		btnVerPerfil.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnVerPerfil.setBounds(106, 422, 223, 65);
		getContentPane().add(btnVerPerfil);
		
		btnSalir = new JButton("Salir");
		btnSalir.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnSalir.setBounds(106, 517, 223, 65);
		getContentPane().add(btnSalir);
		this.repaint();
		
		
		
		
	}

	public JButton getBtnNuevaReparacion() {
		return btnNuevaReparacion;
	}

	public void setBtnNuevaReparacion(JButton btnNuevaReparacion) {
		this.btnNuevaReparacion = btnNuevaReparacion;
	}
	
	public JButton getBtnComprarRecambios() {
		return btnComprarRecambios;
	}

	public void setBtnComprarRecambios(JButton btnComprarRecambios) {
		this.btnComprarRecambios = btnComprarRecambios;
	}

	public JButton getBtnVerPerfil() {
		return btnVerPerfil;
	}

	public void setBtnVerPerfil(JButton btnVerPerfil) {
		this.btnVerPerfil = btnVerPerfil;
	}

	public JButton getBtnSalir() {
		return btnSalir;
	}

	public void setBtnSalir(JButton btnSalir) {
		this.btnSalir = btnSalir;
	}
	
	
	
	
}
