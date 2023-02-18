package vista;

import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.Image;
import java.util.ArrayList;

import javax.swing.JPanel;
import javax.swing.SwingConstants;

import modelo.logica.Factura;
import modelo.logica.TipoRecambio;

import javax.swing.JList;
import javax.swing.DefaultListModel;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;

public class VentanaFacturaRecambiosCliente extends JFrame{
	private JLabel lblLogo;
	private JLabel lblPago;
	private JLabel lblRecambios;
	private JLabel lblPrecio;
	private JLabel lblTotal;
	private JLabel lblTipoPago;
	private JPanel recambio;
	private JPanel precio;
	private JPanel total;
	private DefaultListModel<String> tipoPago;
	private JButton btnFinalizar;
	private JComboBox comboBox;
	private ArrayList<Integer> cantidad;
	private ArrayList<TipoRecambio> trecambio;

	
	public VentanaFacturaRecambiosCliente(VentanaComprarRecambios vista, ArrayList<TipoRecambio> arrayTipoRecambio , ArrayList<Integer> arrayCantidad) {
		this.trecambio=arrayTipoRecambio;
		this.cantidad=arrayCantidad;
		
		setBounds(130, 130, 482, 444);
		setTitle("Factura");
		getContentPane().setLayout(null);
		inicializar();
	}
	

	private void inicializar() {
		lblLogo = new JLabel("");
		lblLogo.setBounds(10, 10, 68, 54);
		ImageIcon imagen=new ImageIcon("src/imagenes/logo.jpeg");
		Icon icono=new ImageIcon(imagen.getImage().getScaledInstance(lblLogo.getWidth(),lblLogo.getHeight(),Image.SCALE_DEFAULT));
		lblLogo.setIcon(icono);
		
		this.getContentPane().add(lblLogo);
		getContentPane().add(lblLogo);
		
		lblPago = new JLabel("Pago");
		lblPago.setHorizontalAlignment(SwingConstants.CENTER);
		lblPago.setFont(new Font("Arial", Font.PLAIN, 15));
		lblPago.setBounds(211, 10, 58, 28);
		getContentPane().add(lblPago);
		
		lblRecambios = new JLabel("Recambios");
		lblRecambios.setHorizontalAlignment(SwingConstants.CENTER);
		lblRecambios.setFont(new Font("Arial", Font.PLAIN, 15));
		lblRecambios.setBounds(38, 74, 128, 28);
		getContentPane().add(lblRecambios);
		
		lblPrecio = new JLabel("Precio");
		lblPrecio.setHorizontalAlignment(SwingConstants.CENTER);
		lblPrecio.setFont(new Font("Arial", Font.PLAIN, 15));
		lblPrecio.setBounds(300, 74, 128, 28);
		getContentPane().add(lblPrecio);
		
		recambio = new JPanel();
		recambio.setBounds(38, 116, 128, 149);
		getContentPane().add(recambio);
		
		precio = new JPanel();
		precio.setBounds(300, 116, 128, 149);
		getContentPane().add(precio);
		
		lblTotal = new JLabel("Total");
		lblTotal.setHorizontalAlignment(SwingConstants.CENTER);
		lblTotal.setFont(new Font("Arial", Font.PLAIN, 15));
		lblTotal.setBounds(201, 291, 85, 22);
		getContentPane().add(lblTotal);
		
		total = new JPanel();
		total.setBounds(300, 291, 128, 22);
		getContentPane().add(total);
		
		lblTipoPago = new JLabel("Tipo de pago");
		lblTipoPago.setHorizontalAlignment(SwingConstants.CENTER);
		lblTipoPago.setFont(new Font("Arial", Font.PLAIN, 15));
		lblTipoPago.setBounds(38, 350, 128, 28);
		getContentPane().add(lblTipoPago);
		tipoPago = new DefaultListModel<String>();
		tipoPago.addElement("Al contado");
		tipoPago.addElement("A plazos");
		
		btnFinalizar = new JButton("Finalizar");
		btnFinalizar.setBounds(300, 354, 128, 23);
		getContentPane().add(btnFinalizar);
		
		comboBox = new JComboBox();
		comboBox.setBounds(188, 355, 81, 21);
		getContentPane().add(comboBox);		
	}


	public JLabel getLblLogo() {
		return lblLogo;
	}


	public void setLblLogo(JLabel lblLogo) {
		this.lblLogo = lblLogo;
	}


	public JLabel getLblPago() {
		return lblPago;
	}


	public void setLblPago(JLabel lblPago) {
		this.lblPago = lblPago;
	}


	public JLabel getLblRecambios() {
		return lblRecambios;
	}


	public void setLblRecambios(JLabel lblRecambios) {
		this.lblRecambios = lblRecambios;
	}

	public JLabel getLblPrecio() {
		return lblPrecio;
	}


	public void setLblPrecio(JLabel lblPrecio) {
		this.lblPrecio = lblPrecio;
	}


	public JLabel getLblTotal() {
		return lblTotal;
	}


	public void setLblTotal(JLabel lblTotal) {
		this.lblTotal = lblTotal;
	}


	public JLabel getLblTipoPago() {
		return lblTipoPago;
	}


	public void setLblTipoPago(JLabel lblTipoPago) {
		this.lblTipoPago = lblTipoPago;
	}


	public JPanel getRecambio() {
		return recambio;
	}


	public void setRecambio(JPanel recambio) {
		this.recambio = recambio;
	}

	public JPanel getPrecio() {
		return precio;
	}


	public void setPrecio(JPanel precio) {
		this.precio = precio;
	}


	public JPanel getTotal() {
		return total;
	}


	public void setTotal(JPanel total) {
		this.total = total;
	}

	public DefaultListModel<String> getTipoPago() {
		return tipoPago;
	}


	public void setTipoPago(DefaultListModel<String> tipoPago) {
		this.tipoPago = tipoPago;
	}


	public JButton getBtnFinalizar() {
		return btnFinalizar;
	}


	public void setBtnFinalizar(JButton btnFinalizar) {
		this.btnFinalizar = btnFinalizar;
	}
}
