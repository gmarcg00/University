package vista;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JComboBox;
import javax.swing.SwingConstants;

import controlador.ControladorComprarRecambio;
import controlador.ControladorInicioCliente;
import modelo.logica.Factura;
import modelo.logica.Recambio;
import modelo.logica.Reparacion;
import modelo.logica.TipoRecambio;
import modelo.logica.TipoReparacion;
import modelo.logica.Vehiculo;

import javax.swing.JSpinner;

import java.awt.Image;

import javax.swing.DefaultComboBoxModel;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;

public class VentanaComprarRecambios extends JFrame{
	private DefaultComboBoxModel<String> tipoProblema;
	private JComboBox<String> listaRecambios;
	private JSpinner cantidadRecambio;
	private JButton seguirComprando;
	private JButton finalizar;
	private Recambio modeloRecambio;

	public VentanaComprarRecambios(VentanaInicioCliente vista) {
		setBounds(130, 130, 450, 314);
		setTitle("Comprar Recambio");
		getContentPane().setLayout(null);
		inicializar();
		setControlador(modeloRecambio,getTipoProblema(), getCantidadRecambio());
	}

	public VentanaComprarRecambios(VentanaComprarRecambios vista) {
		setBounds(130, 130, 450, 314);
		setTitle("Comprar Recambio");
		getContentPane().setLayout(null);
		inicializar();
		setControlador(modeloRecambio,getTipoProblema(), getCantidadRecambio());
	}
	
	public void setControlador(Recambio modeloRecambio, TipoRecambio trecambio , int cantidad) {
		modeloRecambio = new Recambio();
		ControladorComprarRecambio control=new ControladorComprarRecambio(VentanaComprarRecambios.this,modeloRecambio, trecambio,cantidad);
		control.setActionBoton();
	}

	private void inicializar() {
		JLabel lblLogo = new JLabel("");
		lblLogo.setBounds(23, 19, 165, 103);
		ImageIcon imagen=new ImageIcon("src/imagenes/logo.jpeg");
		Icon icono=new ImageIcon(imagen.getImage().getScaledInstance(lblLogo.getWidth(),lblLogo.getHeight(),Image.SCALE_DEFAULT));
		lblLogo.setIcon(icono);
		
		this.getContentPane().add(lblLogo);
		
		JLabel lblNewLabel = new JLabel("\u00BFQue pieza desea comprar?");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(168, 66, 258, 21);
		getContentPane().add(lblNewLabel);
		
		listaRecambios = new JComboBox<String>();
		listaRecambios.setBounds(186, 132, 201, 29);
		tipoProblema = new DefaultComboBoxModel<String>();
		tipoProblema.addElement("Aceite");
		tipoProblema.addElement("Limpiaparabrisas");
		tipoProblema.addElement("Bujia");
		tipoProblema.addElement("Neumaticos");
		tipoProblema.addElement("Radio");
		listaRecambios.setModel(tipoProblema);
		getContentPane().add(listaRecambios);
		
		JLabel lblNewLabel_1 = new JLabel("Pieza");
		lblNewLabel_1.setBounds(40, 140, 45, 13);
		getContentPane().add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("Cantidad");
		lblNewLabel_2.setBounds(40, 184, 64, 13);
		getContentPane().add(lblNewLabel_2);
		
		cantidadRecambio = new JSpinner();
		cantidadRecambio.setBounds(342, 181, 45, 20);
		getContentPane().add(cantidadRecambio);
		
		seguirComprando = new JButton("Seguir Comprando");
		seguirComprando.setBounds(40, 223, 127, 29);
		getContentPane().add(seguirComprando);
		
		finalizar = new JButton("Finalizar Compra");
		finalizar.setBounds(260, 223, 127, 29);
		getContentPane().add(finalizar);
	}

	public TipoRecambio getTipoProblema() {
		if(listaRecambios.getSelectedItem().toString()=="Aceite") {
			return TipoRecambio.ACEITE;
		}else if(listaRecambios.getSelectedItem().toString()=="Limpiaparabrisas") {
			return TipoRecambio.LIMPIAPARABRISAS;
		}else if(listaRecambios.getSelectedItem().toString()=="Bujia") {
			return TipoRecambio.BUJIA;
		}else if(listaRecambios.getSelectedItem().toString()=="Neumaticos") {
			return TipoRecambio.NEUMATICOS;
		}else{
			return TipoRecambio.RADIO;
		}
	}

	public void setTipoProblema(DefaultComboBoxModel<String> tipoProblema) {
		this.tipoProblema = tipoProblema;
	}

	public JButton getSeguirComprando() {
		return seguirComprando;
	}

	public void setSeguirComprando(JButton continuar) {
		seguirComprando = continuar;
	}

	public JButton getFinalizar() {
		return finalizar;
	}

	public void setFinalizar(JButton finalizar) {
		finalizar = finalizar;
	}

	public int getCantidadRecambio() {
		return (int) cantidadRecambio.getValue();
	}

	public void setCantidadRecambio(JSpinner cantidadRecambio) {
		this.cantidadRecambio = cantidadRecambio;
	}
	
	
}
