package vista;

import java.awt.Image;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import controlador.ControladorInicioCliente;
import controlador.ControladorLoginTrabajador;
import modelo.logica.Recambio;
import modelo.logica.Reparacion;
import modelo.logica.Usuario;
import modelo.logica.Vehiculo;
import modelo.vo.UsuarioVO;

public class VentanaLoginTrabajador extends JFrame{

	private JTextField textFieldNIF;
	private JPasswordField textField_Contrase�a;
	private JFrame ventana;
	private JLabel lblNIF;
	private JLabel lblContrase�a;
	private JButton btnIniciarSesion;
	private Usuario usuario;
	
	public VentanaLoginTrabajador(VentanaInicioAplicacion frame) {
		setBounds(130, 130, 450, 300);
		setTitle("Iniciar Sesi�n");
		getContentPane().setLayout(null);
		initializate();
		setControlador(usuario);
		
		
	}
	
	public void setControlador(Usuario usuario) {
		usuario = new Usuario();
		ControladorLoginTrabajador control=new ControladorLoginTrabajador(VentanaLoginTrabajador.this,usuario);
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
		
		textField_Contrase�a = new JPasswordField();
		textField_Contrase�a.setBounds(116, 204, 107, 19);
		getContentPane().add(textField_Contrase�a);
		textField_Contrase�a.setColumns(10);
		
		lblNIF = new JLabel("NIF");
		lblNIF.setBounds(31, 153, 97, 19);
		getContentPane().add(lblNIF);
		
		lblContrase�a = new JLabel("Contrase\u00F1a");
		lblContrase�a.setBounds(31, 204, 97, 19);
		getContentPane().add(lblContrase�a);
		
		btnIniciarSesion = new JButton("Iniciar Sesion");
		btnIniciarSesion.setBounds(273, 170, 121, 27);
		getContentPane().add(btnIniciarSesion);
	}

	public JButton getBtnIniciarSesion() {
		return btnIniciarSesion;
	}

	public void setBtnIniciarSesion(JButton btnIniciarSesion) {
		this.btnIniciarSesion = btnIniciarSesion;
	}

	public String getTextFieldNIF() {
		return textFieldNIF.getText();
	}

	public void setTextFieldNIF(JTextField textFieldNIF) {
		this.textFieldNIF = textFieldNIF;
	}

	public String getTextField_Contrase�a() {
		return textField_Contrase�a.getText();
	}

	public void setTextField_Contrase�a(JPasswordField textField_Contrase�a) {
		this.textField_Contrase�a = textField_Contrase�a;
	}
	
	
	
}
