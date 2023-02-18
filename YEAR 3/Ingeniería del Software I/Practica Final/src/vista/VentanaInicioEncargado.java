package vista;

import javax.swing.JFrame;

import modelo.vo.UsuarioVO;
import javax.swing.JLabel;

import controlador.ControladorInicioCliente;
import controlador.ControladorInicioEncargado;

import java.awt.Font;
import java.awt.Image;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;

public class VentanaInicioEncargado extends JFrame{

	private JButton btnContratarMec;
	private JButton btnEliminarMec;
	private UsuarioVO user;
	private VentanaInicioEncargado vista;
	
	public VentanaInicioEncargado(VentanaLoginTrabajador vista, UsuarioVO usuario) {
		setBounds(130, 130, 450, 300);
		setTitle("Inicio Encargado");
		getContentPane().setLayout(null);
		this.user=usuario;
		initializate();
		setControlador(user);
	
	}
	
	public void setControlador(UsuarioVO usuario) {
		ControladorInicioEncargado control =new ControladorInicioEncargado(VentanaInicioEncargado.this,usuario);
		control.setBoton();
	}
	
	public void initializate() {
		JLabel lblLogo = new JLabel("");
		lblLogo.setBounds(31, 27, 165, 103);
		ImageIcon imagen=new ImageIcon("src/imagenes/logo.jpeg");
		Icon icono=new ImageIcon(imagen.getImage().getScaledInstance(lblLogo.getWidth(),lblLogo.getHeight(),Image.SCALE_DEFAULT));
		lblLogo.setIcon(icono);
		this.getContentPane().add(lblLogo);
		
		JLabel lblEncargado = new JLabel("Inicio encargado");
		lblEncargado.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblEncargado.setBounds(239, 55, 160, 40);
		getContentPane().add(lblEncargado);
		
		btnContratarMec = new JButton("Contratar Mecanico");
		btnContratarMec.setBounds(34, 157, 153, 50);
		getContentPane().add(btnContratarMec);
		
		btnEliminarMec = new JButton("Despedir Mec\u00E1nico");
		btnEliminarMec.setBounds(239, 157, 159, 50);
		getContentPane().add(btnEliminarMec);
	}

	public JButton getBtnContratar() {
		return btnContratarMec;
	}

	public void setBtnContratarMec(JButton btnContratarMec) {
		this.btnContratarMec = btnContratarMec;
	}

	public JButton getBtnDespedir() {
		return btnEliminarMec;
	}

	public void setBtnEliminarMec(JButton btnEliminarMec) {
		this.btnEliminarMec = btnEliminarMec;
	}
	
	
}
