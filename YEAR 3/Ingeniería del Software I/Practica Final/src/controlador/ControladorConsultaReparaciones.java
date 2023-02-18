package controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import modelo.logica.Reparacion;
import modelo.vo.ReparacionVO;
import modelo.vo.UsuarioVO;
import vista.VentanaConsultarReparacionMecanico;

public class ControladorConsultaReparaciones implements ActionListener{

	private VentanaConsultarReparacionMecanico vista;
	private UsuarioVO usuario;
	private ArrayList<ReparacionVO> listaReparaciones;
	private String value;
	private Reparacion modeloReparacion;
	
	
	public ControladorConsultaReparaciones(VentanaConsultarReparacionMecanico vista,
			UsuarioVO usuario, ArrayList<ReparacionVO> listaReparaciones, String value) {
		
		this.vista = vista;
		this.usuario=usuario;
		this.listaReparaciones=listaReparaciones;
		this.value=value;
		this.modeloReparacion=new Reparacion();
	}


	public void setBoton() {
		vista.getBtnReparar().addActionListener(this);
		
	}


	@Override
	public void actionPerformed(ActionEvent arg0) {
		
		if(arg0.getSource().equals(vista.getBtnReparar())) {
			int i=Integer.parseInt(value);
			if(modeloReparacion.cambiarMecanico(listaReparaciones.get(i), usuario)) {
				JOptionPane.showMessageDialog(vista, "Vehiculo reparado");
				vista.setVisible(false);
			}
		}
		
	}

}
