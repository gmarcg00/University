package controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JOptionPane;

import modelo.logica.Factura;
import modelo.logica.Reparacion;
import modelo.vo.FacturaVO;
import modelo.vo.ReparacionVO;
import vista.VentanaFacturaReparacion;

public class ControladorFacturaReparacionCliente implements ActionListener {
	
	private Factura modeloFactura;
	private Reparacion modeloReparacion;
	private VentanaFacturaReparacion vista;
	private FacturaVO factura;
	private ReparacionVO reparacion;
	
	public ControladorFacturaReparacionCliente(VentanaFacturaReparacion ventanaFacturaReparacion, FacturaVO factura, Factura modeloFactura, ReparacionVO reparacion) {
		this.vista=ventanaFacturaReparacion;
		this.modeloFactura=modeloFactura;
		this.factura=factura;
		this.reparacion=reparacion;
		this.modeloReparacion=new Reparacion();
		
	}
	
	public void setActionBoton() {
		vista.getBotonPagar().addActionListener(this);
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		if(arg0.getSource().equals(vista.getBotonPagar())) {
			if(modeloReparacion.actualizarPago(reparacion)) {
				//Pagado actualizado
				factura.setTipoPago(vista.getTipoPago());
				if(modeloFactura.cambiarPago(factura)) {
					//Tipo de pago cambiado
					JOptionPane.showMessageDialog(vista, "Se ha completado el pago.");
					vista.setVisible(false);
				}
			}
		}
		
	}

}
