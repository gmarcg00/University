package modelo.logica;

import java.util.ArrayList;

import modelo.dao.ReparacionDAO;
import modelo.vo.FacturaVO;
import modelo.vo.ReparacionVO;
import modelo.vo.UsuarioVO;

public class Reparacion {
	private ReparacionDAO reparacion;
	
	public Reparacion() {
		this.reparacion = new ReparacionDAO();
	}
	
	public ReparacionVO nuevaReparacion( ReparacionVO reparacion) {
		return this.reparacion.setReparacion(reparacion);
	}
	
	public int getId() {
		return this.reparacion.getIdReparacion();
	}
	
	public boolean actualizarPago(ReparacionVO reparacion) {
		return this.reparacion.actualizarPago(reparacion);
	}
	
	public ArrayList<ReparacionVO> getReparaciones(UsuarioVO usuario,ArrayList<ReparacionVO> listaReparaciones){
		return reparacion.getReparacion(usuario,listaReparaciones);
	}
	
	public ArrayList<ReparacionVO> getReparacionesDisponibles(ArrayList<ReparacionVO> listaReparaciones){
		return reparacion.getReparacionDisponible(listaReparaciones);
	}
	
	public boolean cambiarMecanico(ReparacionVO reparacion, UsuarioVO usuario) {
		return this.reparacion.cambiarMecanico(reparacion, usuario);
	}
	
	public ArrayList<ReparacionVO> getReparacionesFinalizadas(UsuarioVO usuario,ArrayList<ReparacionVO> listaReparaciones){
		return reparacion.getReparacionFinalizada(usuario,listaReparaciones);
	}
	
}
