package vista;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JTextField;

import aplicacion.AutoKingException;
import conexion.Conexion;
import modelo.dao.ClienteDAO;
import modelo.logica.Empresa;
import modelo.logica.Encargado;
import modelo.logica.Mecanico;
import modelo.logica.Proveedor;
import modelo.vo.ClienteVO;
import modelo.vo.EmpresaVO;
import modelo.vo.EncargadoVO;
import modelo.vo.MecanicoVO;
import modelo.vo.ProveedorVO;

import java.sql.Connection;

import javax.swing.JButton;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JTextPane;
import java.awt.Font;
import com.jgoodies.forms.factories.DefaultComponentFactory;

import javax.swing.SwingConstants;

public class VentanaInicioAplicacion extends JFrame {

	private JFrame frame;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					VentanaInicioAplicacion window = new VentanaInicioAplicacion();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public VentanaInicioAplicacion() {
		/*
		ProveedorVO proveedor= new ProveedorVO("353426935Y","Renault","Alemania","678349143","renault.@gmail");
		Proveedor proveedor1=new Proveedor();
		proveedor1.nuevoProveedor(proveedor);
		*/
		
		/*
		MecanicoVO mecanico =new MecanicoVO("6666666B","xxxx","B67657189","Andres","González Arias","Gutierrez Mellado N10 4A","659126781","cesar.unileon",550.0,"Mecanicos");
		Mecanico mecanico1=new Mecanico();
		mecanico1.nuevoMecanico(mecanico);
		*/
		
		/*
		EncargadoVO mecanico =new EncargadoVO("5555555P","xxxx","B67657189","Sergio","Piqué Bernabeu","Las Ramblas","659126781","pique.barcelona@gmail",550.0,"Encargados");
		Encargado mecanico1=new Encargado();
		mecanico1.nuevoEncargado(mecanico);
		*/
		
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		frame.setTitle("AutoKing");

		// Colocamos el logo
		JLabel lblLogo = new JLabel("");
		lblLogo.setBounds(31, 27, 165, 103);
		ImageIcon imagen = new ImageIcon("src/imagenes/logo.jpeg");
		Icon icono = new ImageIcon(
				imagen.getImage().getScaledInstance(lblLogo.getWidth(), lblLogo.getHeight(), Image.SCALE_DEFAULT));
		lblLogo.setIcon(icono);

		frame.getContentPane().add(lblLogo);
		frame.repaint();

		// Etiqueta bienvenidos
		JLabel lblBienvenidos = DefaultComponentFactory.getInstance().createLabel("    Bienvenidos a Autoking");
		lblBienvenidos.setBounds(224, 20, 165, 91);
		frame.getContentPane().add(lblBienvenidos);

		// Botones
		JButton btnClientes = new JButton("Acceso Clientes");
		btnClientes.setBounds(31, 140, 372, 38);
		frame.getContentPane().add(btnClientes);

		JButton btnTrabajadores = new JButton("Acceso Trabajadores");
		btnTrabajadores.setBounds(31, 197, 372, 38);
		frame.getContentPane().add(btnTrabajadores);

		btnClientes.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				VentanaAccesoClientes ventana = new VentanaAccesoClientes(VentanaInicioAplicacion.this);
				ventana.setVisible(true);
			}

		});

		btnTrabajadores.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub

				// Ventana Registro Trabajador
				VentanaLoginTrabajador ventana = new VentanaLoginTrabajador(VentanaInicioAplicacion.this);
				ventana.setVisible(true);

			}

		});

	}

}
