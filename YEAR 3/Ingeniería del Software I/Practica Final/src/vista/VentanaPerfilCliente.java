package vista;

import java.awt.Font;
import java.awt.Image;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

import controlador.ControladorPerfilCliente;
import modelo.vo.UsuarioVO;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.SwingConstants;

public class VentanaPerfilCliente extends JFrame {
	
	private UsuarioVO usuario;
	private JButton btnReparaciones;
	private JButton btnFacturas;
	

	public VentanaPerfilCliente(VentanaInicioCliente ventana, UsuarioVO usuario) {
		setBounds(130, 130, 450, 458);
		setTitle("Perfil Cliente");
		getContentPane().setLayout(null);
		this.usuario=usuario;
		inicializate();
		setControlador();
		
	}
	
	public void setControlador() {
		ControladorPerfilCliente control =new ControladorPerfilCliente(VentanaPerfilCliente.this,usuario);
		control.setActionBoton();
	}

	public void inicializate() {

		JLabel lblLogo = new JLabel("");
		lblLogo.setBounds(31, 27, 165, 103);
		ImageIcon imagen=new ImageIcon("src/imagenes/logo.jpeg");
		Icon icono=new ImageIcon(imagen.getImage().getScaledInstance(lblLogo.getWidth(),lblLogo.getHeight(),Image.SCALE_DEFAULT));
		lblLogo.setIcon(icono);
		
		this.getContentPane().add(lblLogo);
		
		JLabel lblPerfilCliente = new JLabel("Perfil "+usuario.getNombre());
		lblPerfilCliente.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblPerfilCliente.setBounds(268, 35, 140, 77);
		getContentPane().add(lblPerfilCliente);
		
		JLabel lblNombreCliente = new JLabel("¿Qué desea consultar " + usuario.getNombre() + "?");
		lblNombreCliente.setHorizontalAlignment(SwingConstants.CENTER);
		lblNombreCliente.setBounds(93, 178, 242, 47);
		getContentPane().add(lblNombreCliente);
		
		btnFacturas = new JButton("Historial de Facturas");
		btnFacturas.setBounds(93, 259, 242, 47);
		getContentPane().add(btnFacturas);
		
		btnReparaciones = new JButton("Historial de Reparaciones");
		btnReparaciones.setBounds(93, 330, 242, 47);
		getContentPane().add(btnReparaciones);
	
	}
	
	public JButton getBtnReparaciones() {
		return btnReparaciones;
	}

	public void setBtnReparaciones(JButton btnReparaciones) {
		this.btnReparaciones = btnReparaciones;
	}

	public JButton getBtnFacturas() {
		return btnFacturas;
	}

	public void setBtnFacturas(JButton btnFacturas) {
		this.btnFacturas = btnFacturas;
	}

}
