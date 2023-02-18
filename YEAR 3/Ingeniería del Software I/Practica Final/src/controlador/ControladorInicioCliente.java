package controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

import modelo.logica.Cliente;
import modelo.logica.EstadoReparacion;
import modelo.logica.Recambio;
import modelo.logica.Reparacion;
import modelo.logica.TipoReparacion;
import modelo.logica.Vehiculo;
import modelo.vo.ClienteVO;
import modelo.vo.RecambioVO;
import modelo.vo.ReparacionVO;
import modelo.vo.UsuarioVO;
import modelo.vo.VehiculoVO;
import vista.VentanaComprarRecambios;
import vista.VentanaFacturaRecambiosCliente;
import vista.VentanaFacturaReparacion;
import vista.VentanaInicioAplicacion;
import vista.VentanaInicioCliente;
import vista.VentanaLoginCliente;
import vista.VentanaNuevaReparacion;
import vista.VentanaPerfilCliente;
import vista.VentanaRegistroCliente;

public class ControladorInicioCliente implements ActionListener {
	private VentanaInicioCliente vista;
	private Vehiculo modeloVehiculo;
	private Reparacion modeloReparacion;
	private Recambio modeloRecambio;
	private UsuarioVO usuario;

	public ControladorInicioCliente(VentanaInicioCliente vista, Reparacion modeloReparacion, Recambio modeloRecambio,
			Vehiculo modeloVehiculo, UsuarioVO usuario) {
		this.vista = vista;
		this.modeloReparacion = modeloReparacion;
		this.modeloRecambio = modeloRecambio;
		this.modeloVehiculo = modeloVehiculo;
		this.usuario=usuario;
	}

	public void setActionBoton() {
		vista.getBtnNuevaReparacion().addActionListener(this);
		vista.getBtnComprarRecambios().addActionListener(this);
		vista.getBtnVerPerfil().addActionListener(this);
		vista.getBtnSalir().addActionListener(this);
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		
		if (arg0.getSource().equals(vista.getBtnNuevaReparacion())) {
			VentanaNuevaReparacion ventana = new VentanaNuevaReparacion(vista,usuario);
			ventana.setVisible(true);
		}

		if (arg0.getSource().equals(vista.getBtnComprarRecambios())){
			VentanaComprarRecambios ventana = new VentanaComprarRecambios(vista);
			ventana.setVisible(true);
			
		}

		if (arg0.getSource().equals(vista.getBtnVerPerfil())) {
			VentanaPerfilCliente ventana = new VentanaPerfilCliente(vista,usuario);
			ventana.setVisible(true);
		}
		
		if (arg0.getSource().equals(vista.getBtnSalir())) {
			vista.setVisible(false);
		}

	}
}
