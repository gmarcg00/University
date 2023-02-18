package controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import modelo.logica.EstadoReparacion;
import modelo.logica.Recambio;
import modelo.logica.Reparacion;
import modelo.logica.TipoRecambio;
import modelo.logica.Vehiculo;
import modelo.vo.ReparacionVO;
import modelo.vo.VehiculoVO;
import vista.VentanaComprarRecambios;
import vista.VentanaFacturaRecambiosCliente;
import vista.VentanaNuevaReparacion;

public class ControladorComprarRecambio implements ActionListener{
	private VentanaComprarRecambios vista;
	private Recambio modeloRecambio;
	private int cantidad;
	private TipoRecambio recambio;
	private ArrayList<Recambio> arrayModeloRecambio;
	private ArrayList<TipoRecambio> arrayTipoRecambio;
	private ArrayList<Integer> arrayCantidad;

	public ControladorComprarRecambio(VentanaComprarRecambios vista,Recambio modelo, TipoRecambio recambio , int cantidad) {
		this.vista=vista;
		this.modeloRecambio=modelo;
		this.recambio=recambio;
		this.cantidad=cantidad;	
	}

	public void setActionBoton() {
		vista.getSeguirComprando().addActionListener(this);
		vista.getFinalizar().addActionListener(this);
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		arrayModeloRecambio = new ArrayList<Recambio>();
		arrayTipoRecambio = new ArrayList<TipoRecambio>();
		arrayCantidad = new ArrayList<Integer>();
		if(arg0.getSource().equals(vista.getSeguirComprando())) {
			VentanaComprarRecambios ventana = new VentanaComprarRecambios(vista);
			ventana.setVisible(true);
			//guardar el recambio y la cantidad
			arrayModeloRecambio.add(modeloRecambio);
			arrayTipoRecambio.add(recambio);
			arrayCantidad.add(cantidad);
		}
		if(arg0.getSource().equals(vista.getFinalizar())){
			VentanaFacturaRecambiosCliente ventana1 = new VentanaFacturaRecambiosCliente(vista,arrayTipoRecambio,arrayCantidad);
			ventana1.setVisible(true);
		}

	}
	
}
