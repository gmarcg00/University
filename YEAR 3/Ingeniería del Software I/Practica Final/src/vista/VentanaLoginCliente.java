package vista;


import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import controlador.ControladorInicioCliente;
import controlador.ControladorLoginCliente;
import modelo.logica.Recambio;
import modelo.logica.Reparacion;
import modelo.logica.Usuario;

import javax.swing.JButton;
import java.awt.Font;

public class VentanaLoginCliente extends JFrame{
	private JTextField textFieldNIF;
	private JPasswordField textField_Contraseña;
	private JFrame ventana;
	private JLabel lblNIF;
	private JLabel lblContraseña;
	private JButton btnIniciarSesion;
	private Usuario modeloUsuario;
	private JLabel lblLoginCliente;
	
	public VentanaLoginCliente(VentanaAccesoClientes frame) {
		setBounds(130, 130, 450, 300);
		setTitle("Iniciar Sesión");
		getContentPane().setLayout(null);
		initializate();
		setControlador(modeloUsuario);
	}
	
	public void setControlador(Usuario modeloUsuario) {
		modeloUsuario=new Usuario();
		ControladorLoginCliente control=new ControladorLoginCliente(VentanaLoginCliente.this, modeloUsuario);
		control.setActionBoton();
	}
	
	
	public void initializate() {
		//Colocamos el logo
		JLabel lblLogo = new JLabel("");
		lblLogo.setBounds(31, 27, 165, 103);
		ImageIcon imagen=new ImageIcon("src/imagenes/logo.jpeg");
		Icon icono=new ImageIcon(imagen.getImage().getScaledInstance(lblLogo.getWidth(),lblLogo.getHeight(),Image.SCALE_DEFAULT));
		lblLogo.setIcon(icono);
		
		this.getContentPane().add(lblLogo);
		this.repaint();
		
		textFieldNIF = new JTextField();
		textFieldNIF.setBounds(116, 153, 107, 19);
		getContentPane().add(textFieldNIF);
		textFieldNIF.setColumns(10);
		
		textField_Contraseña = new JPasswordField();
		textField_Contraseña.setBounds(116, 204, 107, 19);
		getContentPane().add(textField_Contraseña);
		textField_Contraseña.setColumns(10);
		
		lblNIF = new JLabel("NIF");
		lblNIF.setBounds(31, 153, 97, 19);
		getContentPane().add(lblNIF);
		
		lblContraseña = new JLabel("Contrase\u00F1a");
		lblContraseña.setBounds(31, 204, 97, 19);
		getContentPane().add(lblContraseña);
		
		btnIniciarSesion = new JButton("Iniciar Sesion");
		btnIniciarSesion.setBounds(273, 170, 121, 27);
		getContentPane().add(btnIniciarSesion);
		
		lblLoginCliente = new JLabel("Login Cliente");
		lblLoginCliente.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblLoginCliente.setBounds(273, 36, 121, 49);
		getContentPane().add(lblLoginCliente);
		
		
	}


	public JButton getBtnIniciarSesion() {
		return btnIniciarSesion;
	}
	
	public String getNIF() {
		return this.textFieldNIF.getText();
	}
	
	public String getPassword() {
		return this.textField_Contraseña.getText();
	}


	public void setBtnIniciarSesion(JButton btnIniciarSesion) {
		this.btnIniciarSesion = btnIniciarSesion;
	}
	
	
}
