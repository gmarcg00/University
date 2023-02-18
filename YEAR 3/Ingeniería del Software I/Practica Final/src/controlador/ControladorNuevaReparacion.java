package controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

import modelo.logica.Cliente;
import modelo.logica.EstadoReparacion;
import modelo.logica.Factura;
import modelo.logica.Reparacion;
import modelo.logica.TipoDePago;
import modelo.logica.TipoReparacion;
import modelo.logica.Vehiculo;
import modelo.vo.ClienteVO;
import modelo.vo.FacturaVO;
import modelo.vo.ReparacionVO;
import modelo.vo.UsuarioVO;
import modelo.vo.VehiculoVO;
import vista.VentanaFacturaReparacion;
import vista.VentanaInicioCliente;
import vista.VentanaNuevaReparacion;
import vista.VentanaRegistroCliente;

public class ControladorNuevaReparacion implements ActionListener {

	private VentanaNuevaReparacion vista;
	private Vehiculo modeloVehiculo;
	private Factura modeloFactura;
	private Reparacion modeloReparacion;
	private UsuarioVO usuario;

	public ControladorNuevaReparacion(VentanaNuevaReparacion vista,Vehiculo modelo, Reparacion modeloReparacion, UsuarioVO usuario, Factura modeloFactura) {
		this.vista=vista;
		this.modeloVehiculo=modelo;
		this.usuario=usuario;
		this.modeloFactura=modeloFactura;
		this.modeloReparacion=modeloReparacion;
		
	}

	public void setActionBoton() {
		vista.getBtnContinuar().addActionListener(this);
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		
		int idFactura=modeloFactura.getId();
		if(idFactura!=-1) {
			FacturaVO factura=new FacturaVO(idFactura+1,"B67657189","AUTOKING SL",usuario.getNif(), 0.0, TipoDePago.ALCONTADO, null);
			modeloFactura.nuevaFactura(factura);
			int idReparacion=modeloReparacion.getId();
			VehiculoVO vehiculo = new VehiculoVO(vista.getTextMatricula(),usuario.getNif(),vista.getTextMarca(), vista.getTextModelo());
			modeloVehiculo.setVehiculo(vehiculo);
			if(idReparacion!=-1) {
				ReparacionVO reparacion = new ReparacionVO(idReparacion+1,vista.getTextMatricula(),idFactura+1, vista.getPrecio(), false, EstadoReparacion.ENPROCESO, "6666666B",usuario.getNif(), vista.getListaProblemas(),vista.getTextProblema());
				if(modeloReparacion.nuevaReparacion(reparacion)!=null) {
					JOptionPane.showMessageDialog(vista, "Se ha registrado la reparación correctamente");
					VentanaFacturaReparacion ventana =new VentanaFacturaReparacion(vista,vehiculo,reparacion,factura);
					ventana.setVisible(true);
					vista.setVisible(false);
				}
			}else {
				JOptionPane.showMessageDialog(vista, "Ha ocurrido un error en el registro de la reparación");
			}
		}else {
			JOptionPane.showMessageDialog(vista, "Ha ocurrido un error en el registro de la factura");
		}
		
		

	}
}
