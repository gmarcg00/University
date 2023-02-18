package controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import modelo.logica.Mecanico;
import modelo.logica.Reparacion;
import modelo.logica.Usuario;
import modelo.vo.MecanicoVO;
import modelo.vo.ReparacionVO;
import modelo.vo.UsuarioVO;
import vista.VentanaConsultarFactura;
import vista.VentanaConsultarReparacionMecanico;
import vista.VentanaContratarMecanico;
import vista.VentanaInicioEncargado;
import vista.VentanaInicioMecanico;

public class ControladorInicioMecanico implements ActionListener {

	private UsuarioVO usuario;
	private VentanaInicioMecanico vista;
	private Reparacion modeloReparacion;

	public ControladorInicioMecanico(VentanaInicioMecanico vista, UsuarioVO usuario) {
		this.vista = vista;
		this.usuario = usuario;
		this.modeloReparacion=new Reparacion();
	}

	public void setBoton() {
		vista.getBtnBotonReparacionesDisponibles().addActionListener(this);
		vista.getBtnHistorialReparaciones().addActionListener(this);

	}

	@Override
	public void actionPerformed(ActionEvent arg0) {

		if (arg0.getSource().equals(vista.getBtnBotonReparacionesDisponibles())) {

			ArrayList<ReparacionVO> listaReparaciones = new ArrayList<ReparacionVO>();
			listaReparaciones = modeloReparacion.getReparacionesDisponibles(listaReparaciones);
			if (listaReparaciones != null) {
				String value = null;
				value = JOptionPane.showInputDialog(vista,
						"Hay " + listaReparaciones.size() + " reparaciones disponibles, \n ¿Cuál desea atender?");
				if (value != null) {
					if (isNumeric(value)) {
						if (Integer.parseInt(value) > 1 || Integer.parseInt(value) < listaReparaciones.size()) {
							VentanaConsultarReparacionMecanico ventana = new VentanaConsultarReparacionMecanico(listaReparaciones, value, usuario);
							ventana.setVisible(true);
						} else {
							JOptionPane.showMessageDialog(vista, "No existe ese numero de reparacion");
						}
					} else {
						JOptionPane.showMessageDialog(vista, "Los datos introducidos son erroneos");
					}
				}
			}
		}
		if (arg0.getSource().equals(vista.getBtnHistorialReparaciones())) {

			ArrayList<ReparacionVO> listaReparaciones = new ArrayList<ReparacionVO>();
			listaReparaciones = modeloReparacion.getReparacionesFinalizadas(usuario, listaReparaciones);
			if (listaReparaciones != null) {
				String value = null;
				value = JOptionPane.showInputDialog(vista,
						"Ha realizado " + listaReparaciones.size() + " reparaciones, \n ¿Cuál desea consultar?");
				if (value != null) {
					if (isNumeric(value)) {
						if (Integer.parseInt(value) > 1 || Integer.parseInt(value) < listaReparaciones.size()) {
							VentanaConsultarReparacionMecanico ventana = new VentanaConsultarReparacionMecanico(listaReparaciones, value, usuario);
							ventana.setVisible(true);
							ventana.getBtnReparar().setVisible(false);
						} else {
							JOptionPane.showMessageDialog(vista, "No existe ese numero de reparacion");
						}
					} else {
						JOptionPane.showMessageDialog(vista, "Los datos introducidos son erroneos");
					}
				}
			}
		}
	}

	public boolean isNumeric(String str) {
		if (str == null) {
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
