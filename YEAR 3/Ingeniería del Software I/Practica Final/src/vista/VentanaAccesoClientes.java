package vista;

import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JButton;

public class VentanaAccesoClientes extends JFrame{
	
	public VentanaAccesoClientes(VentanaInicioAplicacion frame) {
		setBounds(130, 130, 524, 319);
		setTitle("Iniciar Sesión");
		getContentPane().setLayout(null);
		initializate();
		
		
	}

	private void initializate() {
		JLabel lblLogo = new JLabel("");
		lblLogo.setBounds(31, 27, 165, 103);
		ImageIcon imagen=new ImageIcon("src/imagenes/logo.jpeg");
		Icon icono=new ImageIcon(imagen.getImage().getScaledInstance(lblLogo.getWidth(),lblLogo.getHeight(),Image.SCALE_DEFAULT));
		lblLogo.setIcon(icono);
		
		this.getContentPane().add(lblLogo);
		
		JLabel lblBienvenida = new JLabel("\u00A1Bienvenido a Autoking!");
		lblBienvenida.setFont(new Font("Tahoma", Font.ITALIC, 18));
		lblBienvenida.setBounds(245, 53, 265, 47);
		getContentPane().add(lblBienvenida);
		
		JButton btnIniciarSesion = new JButton("Iniciar Sesi\u00F3n");
		btnIniciarSesion.setBounds(60, 181, 136, 47);
		getContentPane().add(btnIniciarSesion);
		
		JButton btnRegistrarse = new JButton("Registrarse");
		btnRegistrarse.setBounds(290, 181, 136, 47);
		getContentPane().add(btnRegistrarse);
		this.repaint();
		
		btnIniciarSesion.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				VentanaLoginCliente ventana = new VentanaLoginCliente(VentanaAccesoClientes.this);
				ventana.setVisible(true);
			}

		});

		btnRegistrarse.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				VentanaRegistroCliente ventana = new VentanaRegistroCliente(VentanaAccesoClientes.this);
				ventana.setVisible(true);

			}

		});
	}
}
