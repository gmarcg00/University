package vista;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JFrame;

import modelo.vo.UsuarioVO;
import javax.swing.JLabel;

import controlador.ControladorInicioEncargado;
import controlador.ControladorInicioMecanico;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.Image;
import javax.swing.JButton;

public class VentanaInicioMecanico extends JFrame{
	
	private JButton btnBotonReparacionesDisponibles;
	private JButton btnHistorialReparaciones;
	
	public VentanaInicioMecanico(VentanaLoginTrabajador vista, UsuarioVO usuario) {
		setBounds(130, 130, 464, 296);
		setTitle("Inicio Mecánico");
		getContentPane().setLayout(null);
		inicializa();
		setControlador(usuario);
	}
	
	public void setControlador(UsuarioVO usuario) {
		ControladorInicioMecanico control =new ControladorInicioMecanico(VentanaInicioMecanico.this,usuario);
		control.setBoton();
	}
	
	private void inicializa() {
		JLabel lblLogo = new JLabel("");
		lblLogo.setBounds(31, 31, 165, 103);
		ImageIcon imagen = new ImageIcon("src/imagenes/logo.jpeg");
		Icon icono = new ImageIcon(
		imagen.getImage().getScaledInstance(lblLogo.getWidth(), lblLogo.getHeight(), Image.SCALE_DEFAULT));
		lblLogo.setIcon(icono);
		this.getContentPane().add(lblLogo);
		
		btnBotonReparacionesDisponibles = new JButton("Reparaciones Disponibles");
		btnBotonReparacionesDisponibles.setBounds(223, 90, 187, 53);
		getContentPane().add(btnBotonReparacionesDisponibles);
		
		btnHistorialReparaciones = new JButton("Historial Reparaciones");
		btnHistorialReparaciones.setBounds(223, 175, 187, 53);
		getContentPane().add(btnHistorialReparaciones);
		
		JLabel lblInicioMecanico = new JLabel("Inicio Mecanico");
		lblInicioMecanico.setFont(new Font("Tahoma", Font.PLAIN, 17));
		lblInicioMecanico.setBounds(258, 31, 163, 34);
		getContentPane().add(lblInicioMecanico);
		
	}

	public JButton getBtnBotonReparacionesDisponibles() {
		return btnBotonReparacionesDisponibles;
	}

	public void setBtnBotonReparacionesDisponibles(JButton btnBotonReparacionesDisponibles) {
		this.btnBotonReparacionesDisponibles = btnBotonReparacionesDisponibles;
	}

	public JButton getBtnHistorialReparaciones() {
		return btnHistorialReparaciones;
	}

	public void setBtnHistorialReparaciones(JButton btnHistorialReparaciones) {
		this.btnHistorialReparaciones = btnHistorialReparaciones;
	}
	
	
}
