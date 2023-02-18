package vista;

import java.awt.Image;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;

import modelo.logica.Factura;
import modelo.logica.TipoDePago;
import modelo.vo.FacturaVO;
import modelo.vo.ReparacionVO;
import modelo.vo.VehiculoVO;
import java.awt.Choice;
import java.awt.Label;
import javax.swing.JButton;
import javax.swing.JTextField;

import controlador.ControladorFacturaReparacionCliente;
import java.awt.Font;

public class VentanaFacturaReparacion extends JFrame{
	private VehiculoVO vehiculo;
	private ReparacionVO reparacion;
	private FacturaVO factura;
	private Factura modeloFactura;
	private JTextField textEmpresa;
	private JTextField textPrecio;
	private JTextField textReparacion;
	private JTextField textMatricula;
	private JButton btnPagar;
	private Choice tipoPago;
	
	public VentanaFacturaReparacion(VentanaNuevaReparacion vista, VehiculoVO vehiculo, ReparacionVO reparacion, FacturaVO factura) {
		// TODO Auto-generated constructor stub
		this.vehiculo=vehiculo;
		this.reparacion=reparacion;
		this.factura=factura;
		setBounds(130, 130, 691, 319);
		setTitle("Factura Reparación");
		getContentPane().setLayout(null);
		initializate();
		setControlador();
	}
	
	public void setControlador() {
		this.modeloFactura=new Factura();
		ControladorFacturaReparacionCliente control = new ControladorFacturaReparacionCliente(VentanaFacturaReparacion.this,this.factura,modeloFactura,reparacion);
		control.setActionBoton();
	}
	
	public void initializate() {
		JLabel lblLogo = new JLabel("");
		lblLogo.setBounds(31, 27, 159, 80);
		ImageIcon imagen=new ImageIcon("src/imagenes/logo.jpeg");
		Icon icono=new ImageIcon(imagen.getImage().getScaledInstance(lblLogo.getWidth(),lblLogo.getHeight(),Image.SCALE_DEFAULT));
		lblLogo.setIcon(icono);
		
		this.getContentPane().add(lblLogo);
		
		JLabel lblRegistro = new JLabel("Factura Reparación");
		lblRegistro.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblRegistro.setBounds(394, 27, 293, 80);
		getContentPane().add(lblRegistro);
		
		JLabel lblReparacion = new JLabel("Reparacion");
		lblReparacion.setBounds(151, 103, 77, 40);
		getContentPane().add(lblReparacion);
		
		JLabel lblMatricula = new JLabel("Matricula");
		lblMatricula.setBounds(279, 103, 77, 40);
		getContentPane().add(lblMatricula);
		
		JLabel lblPrecio = new JLabel("Precio");
		lblPrecio.setBounds(419, 103, 77, 40);
		getContentPane().add(lblPrecio);
		
		JLabel lblEmpresa = new JLabel("Empresa");
		lblEmpresa.setBounds(31, 103, 77, 40);
		getContentPane().add(lblEmpresa);
		
		tipoPago = new Choice();
		tipoPago.setBounds(549, 160, 104, 18);
		getContentPane().add(tipoPago);
		tipoPago.add("Al contado");
		tipoPago.add("A plazos");
		
		JLabel lblTipoDePago = new JLabel("Tipo de pago");
		lblTipoDePago.setBounds(549, 103, 77, 40);
		getContentPane().add(lblTipoDePago);
		
		btnPagar = new JButton("Pagar");
		btnPagar.setBounds(289, 226, 85, 21);
		getContentPane().add(btnPagar);
		
		textEmpresa = new JTextField();
		textEmpresa.setEditable(false);
		textEmpresa.setBounds(31, 158, 96, 19);
		getContentPane().add(textEmpresa);
		textEmpresa.setText(factura.getNombreEmpresa());
		textEmpresa.setColumns(10);
		
		textPrecio = new JTextField();
		textPrecio.setEditable(false);
		textPrecio.setColumns(10);
		textPrecio.setBounds(419, 159, 90, 19);
		textPrecio.setText(String.valueOf(reparacion.getPrecio()));
		getContentPane().add(textPrecio);
		
		textReparacion = new JTextField();
		textReparacion.setEditable(false);
		textReparacion.setColumns(10);
		textReparacion.setBounds(151, 159, 104, 19);
		textReparacion.setText(String.valueOf(reparacion.getTipo()));
		getContentPane().add(textReparacion);
		
		textMatricula = new JTextField();
		textMatricula.setEditable(false);
		textMatricula.setColumns(10);
		textMatricula.setBounds(279, 159, 104, 19);
		textMatricula.setText(vehiculo.getMatricula());
		getContentPane().add(textMatricula);
	}
	
	public JButton getBotonPagar() {
		return this.btnPagar;
	}
	
	public TipoDePago getTipoPago() {
		if(tipoPago.getSelectedItem().equals("A plazos")) {
			return TipoDePago.APLAZOS;
		}else {
			return TipoDePago.ALCONTADO;
		}
	}
}
