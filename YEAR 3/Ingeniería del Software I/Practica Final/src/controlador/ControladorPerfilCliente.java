package controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

import modelo.logica.Factura;
import modelo.logica.Reparacion;
import modelo.vo.FacturaVO;
import modelo.vo.ReparacionVO;
import modelo.vo.UsuarioVO;
import vista.VentanaConsultarFactura;
import vista.VentanaConsultarReparacion;
import vista.VentanaPerfilCliente;

public class ControladorPerfilCliente implements ActionListener{
	
	private VentanaPerfilCliente vista;
	private UsuarioVO user;
	private Factura modeloFactura;
	private Reparacion modeloReparacion;
	
	public ControladorPerfilCliente(VentanaPerfilCliente ventana,UsuarioVO usuario) {
		this.vista=ventana;
		this.user=usuario;
		modeloFactura=new Factura();
		modeloReparacion=new Reparacion();
	}
	
	public void setActionBoton() {
		vista.getBtnFacturas().addActionListener(this);
		vista.getBtnReparaciones().addActionListener(this);
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		if(arg0.getSource().equals(vista.getBtnFacturas())) {
			ArrayList<FacturaVO> listaFacturas=new ArrayList<FacturaVO>();
			listaFacturas=modeloFactura.getFacturas(user,listaFacturas);
			String value=null;
			value=JOptionPane.showInputDialog(vista,"Tiene "+listaFacturas.size()+" facturas, \n ¿Cuál desea consultar?");
			if(value!=null) {
				if(isNumeric(value)) {
					if(Integer.parseInt(value)>1 || Integer.parseInt(value)<listaFacturas.size()) {
						VentanaConsultarFactura ventana=new VentanaConsultarFactura(listaFacturas,value);
						ventana.setVisible(true);
					}else {
						JOptionPane.showMessageDialog(vista, "No existe ese numero de factura");
					}
				}else {
					JOptionPane.showMessageDialog(vista, "Los datos introducidos son erroneos");
				}
			}
		}

		
		if(arg0.getSource().equals(vista.getBtnReparaciones())) {
			ArrayList<ReparacionVO> listaReparaciones=new ArrayList<ReparacionVO>();
			listaReparaciones=modeloReparacion.getReparaciones(user, listaReparaciones);
			String value=null;
			value=JOptionPane.showInputDialog(vista,"Tiene "+listaReparaciones.size()+" reparaciones, \n ¿Cuál desea consultar?");
			if(value!=null) {
				if(isNumeric(value)) {
					if(Integer.parseInt(value)>1 || Integer.parseInt(value)<listaReparaciones.size()) {
						VentanaConsultarReparacion ventana=new VentanaConsultarReparacion(listaReparaciones,value);
						ventana.setVisible(true);
					}else {
						JOptionPane.showMessageDialog(vista, "No existe ese numero de factura");
					}
				}else {
					JOptionPane.showMessageDialog(vista, "Los datos introducidos son erroneos");
				}
			}
		}
		
	}
	
	public boolean isNumeric(String str) {
		if(str==null) {
			return true;
		}
		try {
			Double.parseDouble(str);
			return true;
		} catch (NumberFormatException e) {
			return false;
		}
	}

}
