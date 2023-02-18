package vista;

import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultListModel;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import controlador.ControladorNuevaReparacion;
import controlador.ControladorRegistroCliente;
import modelo.logica.Cliente;
import modelo.logica.Factura;
import modelo.logica.Reparacion;
import modelo.logica.TipoReparacion;
import modelo.logica.Vehiculo;
import modelo.vo.UsuarioVO;

import javax.swing.JButton;
import javax.swing.JList;
import javax.swing.JSpinner;
import javax.swing.JCheckBoxMenuItem;
import javax.swing.JScrollPane;
import javax.swing.JComboBox;
import java.awt.Font;
import javax.swing.SwingConstants;

public class VentanaNuevaReparacion extends JFrame{
	private JTextField textMarca;
	private JTextField textModelo;
	private JTextField textMatricula;
	private JTextField textProblema;
	private JButton btnContinuar;
	private Vehiculo modelo;
	private Reparacion modeloReparacion;
	private Factura modeloFactura;
	private UsuarioVO usuario;
	private DefaultComboBoxModel<String> tipoProblema;
	private JComboBox<String> listaProblemas;

	
	public VentanaNuevaReparacion(VentanaInicioCliente frame, UsuarioVO usuario) {
		setBounds(130, 130, 450, 540);
		setTitle("Nueva Reparacion");
		getContentPane().setLayout(null);
		initializate();
		setControlador(modelo,modeloReparacion,usuario,modeloFactura);
		this.usuario=usuario;
	}
	
	public void setControlador(Vehiculo modelo, Reparacion modeloReparacion, UsuarioVO usuario,Factura modeloFactura) {
		modelo=new Vehiculo();
		modeloFactura=new Factura();
		modeloReparacion=new Reparacion();
		ControladorNuevaReparacion control=new ControladorNuevaReparacion(VentanaNuevaReparacion.this,modelo,modeloReparacion,usuario,modeloFactura);
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
		
		JLabel lblVehiculo = new JLabel("Datos del vehiculo");
		lblVehiculo.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblVehiculo.setBounds(232, 27, 172, 80);
		getContentPane().add(lblVehiculo);
		
		JLabel lblMarca = new JLabel("Marca");
		lblMarca.setBounds(55, 140, 102, 24);
		getContentPane().add(lblMarca);
		
		JLabel lblModelo = new JLabel("Modelo");
		lblModelo.setBounds(55, 174, 102, 24);
		getContentPane().add(lblModelo);
		
		JLabel lblMatricula = new JLabel("Matricula");
		lblMatricula.setBounds(55, 208, 102, 24);
		getContentPane().add(lblMatricula);
		
		
		textMarca = new JTextField();
		textMarca.setBounds(208, 143, 175, 19);
		getContentPane().add(textMarca);
		textMarca.setColumns(10);
		
		textModelo = new JTextField();
		textModelo.setColumns(10);
		textModelo.setBounds(208, 177, 175, 19);
		getContentPane().add(textModelo);
		
		textMatricula = new JTextField();
		textMatricula.setColumns(10);
		textMatricula.setBounds(208, 211, 175, 19);
		getContentPane().add(textMatricula);
		
		
		btnContinuar = new JButton("Continuar");
		btnContinuar.setFont(new Font("Tahoma", Font.PLAIN, 10));
		btnContinuar.setBounds(147, 434, 136, 40);
		getContentPane().add(btnContinuar);
		
		JLabel lblInformacion = new JLabel("\u00BFQu\u00E9 le ocurre al veh\u00EDculo?");
		lblInformacion.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblInformacion.setBounds(142, 305, 263, 24);
		getContentPane().add(lblInformacion);
		
		textProblema = new JTextField();
		textProblema.setBounds(55, 339, 328, 80);
		getContentPane().add(textProblema);
		textProblema.setColumns(10);
		
		JLabel lblTipoReparacion = new JLabel("Problema");
		lblTipoReparacion.setBounds(55, 256, 65, 13);
		getContentPane().add(lblTipoReparacion);
		
		listaProblemas = new JComboBox<String>();
		listaProblemas.setBounds(208, 252, 175, 21);
		tipoProblema = new DefaultComboBoxModel<String>();
		tipoProblema.addElement("Pinchazo");
		tipoProblema.addElement("No funciona el aire acondicionado");
		tipoProblema.addElement("Luna rota");
		tipoProblema.addElement("Retrovisor roto");
		tipoProblema.addElement("Luces fundidas");
		tipoProblema.addElement("Otro");
		listaProblemas.setModel(tipoProblema);
		getContentPane().add(listaProblemas);

		
		this.repaint();
	}

	public String getTextMarca() {
		return textMarca.getText();
	}

	public void setTextMarca(JTextField textMarca) {
		this.textMarca = textMarca;
	}

	public String getTextModelo() {
		return textModelo.getText();
	}

	public void setTextModelo(JTextField textModelo) {
		this.textModelo = textModelo;
	}

	public String getTextMatricula() {
		return textMatricula.getText();
	}

	public void setTextMatricula(JTextField textMatricula) {
		this.textMatricula = textMatricula;
	}
	
	public String getTextProblema() {
		return textProblema.getText();
	}

	public void setTextProblema(JTextField textProblema) {
		this.textProblema = textProblema;
	}

	public JButton getBtnContinuar() {
		return btnContinuar;
	}

	public void setBtnIntroducir(JButton btnIntroducir) {
		this.btnContinuar = btnIntroducir;
	}

	public TipoReparacion getListaProblemas() {
		if(listaProblemas.getSelectedItem().toString()=="Pinchazo") {
			return TipoReparacion.RUEDA;
		}else if(listaProblemas.getSelectedItem().toString()=="No funciona el aire acondicionado") {
			return TipoReparacion.AIRE;
		}else if(listaProblemas.getSelectedItem().toString()=="Luna rota") {
			return TipoReparacion.LUNA;
		}else if(listaProblemas.getSelectedItem().toString()=="Retrovisor roto") {
			return TipoReparacion.RETROVISOR;
		}else if(listaProblemas.getSelectedItem().toString()=="Luces fundidas") {
			return TipoReparacion.LUCES;
		}else{
			return TipoReparacion.OTRO;
		}
	}

	public void setListaProblemas(JComboBox<String> listaProblemas) {
		this.listaProblemas = listaProblemas;
	}
	
	public double getPrecio() {
		if(listaProblemas.getSelectedItem().toString()=="Pinchazo") {
			return 70.0;
		}else if(listaProblemas.getSelectedItem().toString()=="No funciona el aire acondicionado") {
			return 200.0;
		}else if(listaProblemas.getSelectedItem().toString()=="Luna rota") {
			return 150.0;
		}else if(listaProblemas.getSelectedItem().toString()=="Retrovisor roto") {
			return 100.0;
		}else if(listaProblemas.getSelectedItem().toString()=="Luces fundidas") {
			return 80.0;
		}else{
			return 200.0;
		}
	}
}
