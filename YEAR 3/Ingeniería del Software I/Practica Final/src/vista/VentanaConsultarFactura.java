package vista;

import java.awt.Image;
import java.util.ArrayList;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

import modelo.vo.FacturaVO;
import modelo.vo.UsuarioVO;
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.CardLayout;
import java.awt.Choice;

public class VentanaConsultarFactura extends JFrame{
	
	private UsuarioVO user;
	private String value;
	private ArrayList<FacturaVO> listaFacturas;
	private JTextField textIdFactura;
	private JTextField textPrecio;
	private JTextField textReparacion;
	private JTextField textMatricula;
	private JTextField textCifEmpresa;
	private JTextField textNombreEmpresa;
	private JTextField textNifCliente;
	private JTextField textPrecioTotal;
	private JTextField textTipoPago;
	
	public VentanaConsultarFactura(ArrayList<FacturaVO> listaFacturas, String value) {
		this.value=value;
		this.listaFacturas=listaFacturas;
		setBounds(130, 130, 691, 319);
		setTitle("Factura "+ listaFacturas.get(Integer.parseInt(value)).getIdFactura());
		getContentPane().setLayout(null);
		initializate();
	}
	
	public void initializate() {
		JLabel lblLogo = new JLabel("");
		lblLogo.setBounds(31, 27, 159, 80);
		ImageIcon imagen=new ImageIcon("src/imagenes/logo.jpeg");
		Icon icono=new ImageIcon(imagen.getImage().getScaledInstance(lblLogo.getWidth(),lblLogo.getHeight(),Image.SCALE_DEFAULT));
		lblLogo.setIcon(icono);
		
		this.getContentPane().add(lblLogo);
		
		JLabel lblRegistro = new JLabel("Factura Reparación");
		lblRegistro.setBounds(360, 27, 136, 80);
		getContentPane().add(lblRegistro);
		
		
		JLabel lblidFactura = new JLabel("idFactura");
		lblidFactura.setBounds(31, 103, 77, 40);
		getContentPane().add(lblidFactura);
		
		
		JLabel lblTipoDePago = new JLabel("Tipo de pago");
		lblTipoDePago.setBounds(520, 103, 77, 40);
		getContentPane().add(lblTipoDePago);
		
	
		JLabel lblCifempresa = new JLabel("cifEmpresa");
		lblCifempresa.setBounds(128, 103, 96, 40);
		getContentPane().add(lblCifempresa);
		
		
		JLabel lblidFactura_1_1 = new JLabel("nombreEmpresa");
		lblidFactura_1_1.setBounds(233, 103, 77, 40);
		getContentPane().add(lblidFactura_1_1);
		
		JLabel lblidFactura_1_2 = new JLabel("nifCliente");
		lblidFactura_1_2.setBounds(332, 103, 99, 40);
		getContentPane().add(lblidFactura_1_2);
		
		JLabel lblidFactura_1_3 = new JLabel("Precio");
		lblidFactura_1_3.setBounds(441, 103, 77, 40);
		getContentPane().add(lblidFactura_1_3);
		
		textCifEmpresa = new JTextField();
		textCifEmpresa.setEditable(false);
		textCifEmpresa.setText("0");
		textCifEmpresa.setColumns(10);
		textCifEmpresa.setBounds(128, 153, 75, 19);
		textCifEmpresa.setText(listaFacturas.get(Integer.parseInt(value)).getCifEmpresa());
		getContentPane().add(textCifEmpresa);
		
		textIdFactura = new JTextField();
		textIdFactura.setEditable(false);
		textIdFactura.setBounds(31, 153, 64, 19);
		getContentPane().add(textIdFactura);
		textIdFactura.setText(String.valueOf(listaFacturas.get(Integer.parseInt(value)).getIdFactura()));
		textIdFactura.setColumns(10);
		
		textNombreEmpresa = new JTextField();
		textNombreEmpresa.setEditable(false);
		textNombreEmpresa.setText("0");
		textNombreEmpresa.setColumns(10);
		textNombreEmpresa.setBounds(233, 153, 77, 19);
		textNombreEmpresa.setText(listaFacturas.get(Integer.parseInt(value)).getNombreEmpresa());
		getContentPane().add(textNombreEmpresa);
		
		textNifCliente = new JTextField();
		textNifCliente.setEditable(false);
		textNifCliente.setText("0");
		textNifCliente.setColumns(10);
		textNifCliente.setBounds(332, 153, 68, 19);
		textNifCliente.setText(listaFacturas.get(Integer.parseInt(value)).getNifCliente());
		getContentPane().add(textNifCliente);
		
		textPrecioTotal = new JTextField();
		textPrecioTotal.setEditable(false);
		textPrecioTotal.setText("0");
		textPrecioTotal.setColumns(10);
		textPrecioTotal.setBounds(441, 153, 56, 19);
		textPrecioTotal.setText(String.valueOf(listaFacturas.get(Integer.parseInt(value)).getPrecioTotal()));
		getContentPane().add(textPrecioTotal);
		
		textTipoPago = new JTextField();
		textTipoPago.setEditable(false);
		textTipoPago.setText("0");
		textTipoPago.setColumns(10);
		textTipoPago.setBounds(520, 153, 77, 19);
		textTipoPago.setText(listaFacturas.get(Integer.parseInt(value)).getTipoPago());
		getContentPane().add(textTipoPago);
		
		
		
	
	}
}
