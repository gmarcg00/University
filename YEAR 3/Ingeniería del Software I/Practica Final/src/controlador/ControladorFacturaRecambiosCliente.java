package controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import modelo.logica.Factura;
import modelo.logica.Recambio;
import vista.VentanaFacturaRecambiosCliente;

public class ControladorFacturaRecambiosCliente implements ActionListener{
	
	private VentanaFacturaRecambiosCliente vista;
	private Factura modelo;
	private Recambio modelo1;
	
	public ControladorFacturaRecambiosCliente(VentanaFacturaRecambiosCliente vista, Factura modelo) {
		this.vista=vista;
		this.modelo=modelo;
	}
	
	public void setActionBoton() {
		vista.getBtnFinalizar().addActionListener(this);
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		
	}
	
}
