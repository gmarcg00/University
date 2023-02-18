package vista;

import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import controlador.ControladorRegistroCliente;
import modelo.logica.Cliente;

import javax.swing.JButton;

public class VentanaRegistroCliente extends JFrame{
	private JTextField textNIF;
	private JTextField textNombre;
	private JTextField textApellidos;
	private JTextField textTelefono;
	private JTextField textDireccion;
	private JTextField textEmail;
	private JTextField textContrasena;
	private JButton btnInformacion;
	private JButton btnRegistrarse;
	private Cliente modelo;
	
	public VentanaRegistroCliente(VentanaAccesoClientes ventanaAccesoClientes) {
		setBounds(130, 130, 450, 550);
		setTitle("Datos del cliente");
		getContentPane().setLayout(null);
		initializate();
		setControlador(modelo);
	}
	
	public void setControlador(Cliente modelo) {
		modelo=new Cliente();
		ControladorRegistroCliente control=new ControladorRegistroCliente(VentanaRegistroCliente.this,modelo);
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
		
		JLabel lblRegistro = new JLabel("Datos Cliente");
		lblRegistro.setBounds(247, 27, 136, 80);
		getContentPane().add(lblRegistro);
		
		JLabel lblNIF = new JLabel("NIF");
		lblNIF.setBounds(55, 140, 102, 24);
		getContentPane().add(lblNIF);
		
		JLabel lblNombre = new JLabel("Nombre");
		lblNombre.setBounds(55, 174, 102, 24);
		getContentPane().add(lblNombre);
		
		JLabel lblApellidos = new JLabel("Apellidos");
		lblApellidos.setBounds(55, 208, 102, 24);
		getContentPane().add(lblApellidos);
		
		JLabel lblTelefono = new JLabel("Telefono");
		lblTelefono.setBounds(55, 247, 102, 24);
		getContentPane().add(lblTelefono);
		
		JLabel lblDireccin = new JLabel("Direcci\u00F3n");
		lblDireccin.setBounds(55, 281, 102, 24);
		getContentPane().add(lblDireccin);
		
		JLabel lblEmail = new JLabel("Email");
		lblEmail.setBounds(55, 315, 102, 24);
		getContentPane().add(lblEmail);
		
		JLabel lblContrasena = new JLabel("Establecer contrase\u00F1a");
		lblContrasena.setBounds(55, 349, 141, 24);
		getContentPane().add(lblContrasena);
		
		textNIF = new JTextField();
		textNIF.setBounds(208, 143, 151, 19);
		getContentPane().add(textNIF);
		textNIF.setColumns(10);
		
		textNombre = new JTextField();
		textNombre.setColumns(10);
		textNombre.setBounds(208, 177, 151, 19);
		getContentPane().add(textNombre);
		
		textApellidos = new JTextField();
		textApellidos.setColumns(10);
		textApellidos.setBounds(208, 211, 151, 19);
		getContentPane().add(textApellidos);
		
		textTelefono = new JTextField();
		textTelefono.setColumns(10);
		textTelefono.setBounds(208, 250, 151, 19);
		getContentPane().add(textTelefono);
		
		textDireccion = new JTextField();
		textDireccion.setColumns(10);
		textDireccion.setBounds(208, 284, 151, 19);
		getContentPane().add(textDireccion);
		
		textEmail = new JTextField();
		textEmail.setColumns(10);
		textEmail.setBounds(208, 318, 151, 19);
		getContentPane().add(textEmail);
		
		textContrasena = new JTextField();
		textContrasena.setColumns(10);
		textContrasena.setBounds(208, 352, 151, 19);
		getContentPane().add(textContrasena);
		
		btnInformacion = new JButton("Informaci\u00F3n");
		btnInformacion.setBounds(67, 434, 112, 40);
		getContentPane().add(btnInformacion);
		
		btnInformacion.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				JOptionPane.showMessageDialog(null, "Para acceder al sistema necesitará \n "
						+ "         su NIF y contraseña.");
				
			}
			
		});
		
		btnRegistrarse = new JButton("Registrarse");
		btnRegistrarse.setBounds(220, 434, 112, 40);
		getContentPane().add(btnRegistrarse);
		this.repaint();
	}
	
	public JButton getRegistrarse() {
		return this.btnRegistrarse;
	}
	
	public String getNIF() {
		return this.textNIF.getText();
	}
	public String getNombre() {
		return this.textNombre.getText();
	}
	public String getApellidos() {
		return this.textApellidos.getText();
	}
	public String getTelefono() {
		return this.textTelefono.getText();
	}
	public String getDireccion() {
		return this.textDireccion.getText();
	}
	public String getEmail() {
		return this.textEmail.getText();
	}
	public String getPassword() {
		return this.textContrasena.getText();
	}
	
	
}
