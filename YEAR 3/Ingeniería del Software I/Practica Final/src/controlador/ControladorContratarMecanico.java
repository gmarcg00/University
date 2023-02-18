package controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

import modelo.logica.Mecanico;
import modelo.vo.ClienteVO;
import modelo.vo.MecanicoVO;
import vista.VentanaContratarMecanico;

public class ControladorContratarMecanico implements ActionListener{
	
	private VentanaContratarMecanico vista;
	private Mecanico modelo;
	
	public ControladorContratarMecanico(VentanaContratarMecanico ventanaContratarMecanico, Mecanico modelo) {
		this.vista=ventanaContratarMecanico;
		this.modelo=modelo;
	}
	
	public void setBoton() {
		vista.getbtnContratar().addActionListener(this);
		vista.getbtnInformacion().addActionListener(this);
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		if(arg0.getSource().equals(vista.getbtnInformacion())) {
			JOptionPane.showMessageDialog(null, "Para acceder al sistema necesitará \n "
					+ "         su NIF y contraseña.");
		}
		
		if(arg0.getSource().equals(vista.getbtnContratar())) {
			MecanicoVO cliente=new MecanicoVO(vista.getNIF(),vista.getPassword(),"B67657189",vista.getNombre(),vista.getApellidos(),vista.getDireccion(),vista.getTelefono(),vista.getEmail(),1550.0, "Mecanico");
			if(modelo.nuevoMecanico(cliente)!=null) {
				JOptionPane.showMessageDialog(vista, cliente.getNombre()+ " "+cliente.getApellidos()+ " ha sido contratado");
				vista.setVisible(false);
			}else {
				JOptionPane.showMessageDialog(vista,"Error en el registro");
			}
		}
		
		
	}
}
