package modelo.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import conexion.Conexion;
import modelo.logica.EstadoReparacion;
import modelo.logica.TipoDePago;
import modelo.logica.TipoReparacion;
import modelo.vo.FacturaVO;
import modelo.vo.ReparacionVO;
import modelo.vo.UsuarioVO;

public class ReparacionDAO {
	public ReparacionVO setReparacion(ReparacionVO reparacion) {
		Conexion conexion = new Conexion();
		Connection conex = conexion.getConnection();
        PreparedStatement consulta1;

        try {
            consulta1 = conex.prepareStatement("INSERT INTO REPARACIONES(IDREPARACION,MATRICULAVEHICULO,THEIDFACTURA,PRECIO,PAGADA,ESTADO,NIFMECANICO,NIFCLIENTE,TIPO,DESCRIPCION) VALUES(?,?,?,?,?,?,?,?,?,?)");
            consulta1.setInt(1, reparacion.getIdReparacion());
            consulta1.setString(2, reparacion.getMatricula());
            consulta1.setInt(3, reparacion.getIdFactura());
            consulta1.setDouble(4, reparacion.getPrecio());
            consulta1.setBoolean(5, reparacion.isPagada());
            consulta1.setString(6, reparacion.getEstado().toString());
            consulta1.setString(7, reparacion.getNifMecanico());
            consulta1.setString(8, reparacion.getNifCliente());
            consulta1.setString(9, reparacion.getTipo().toString());
            consulta1.setString(10, reparacion.getDescripcion());
            consulta1.executeUpdate();
            
            return reparacion;
            
        } catch (SQLException e) {
        		System.out.println(e.getMessage());
            	System.err.println("Error");
        }
		return null;
	}
	
	public int getIdReparacion() {
		Conexion conexion = new Conexion();
		Connection conex = conexion.getConnection();
        PreparedStatement consulta1;
        
        try {
            consulta1 = conex.prepareStatement("SELECT IDREPARACION FROM REPARACIONES ORDER BY IDREPARACION DESC ");
            ResultSet res1 = consulta1.executeQuery();
            int n=-1;
            if(res1.next()) {
            	n=res1.getInt("IDREPARACION");
            }
            return n;
            
        } catch (SQLException e) {
        		System.out.println(e.getMessage());
            	System.err.println("Error");
        }
        
        return -1;
	}
	
	public boolean actualizarPago(ReparacionVO reparacion) {
		Conexion conexion = new Conexion();
		Connection conex = conexion.getConnection();
        PreparedStatement consulta1;
        
      
        try {
            consulta1 = conex.prepareStatement("UPDATE REPARACIONES SET PAGADA=? WHERE IDREPARACION=?");
            consulta1.setInt(1,1);
            consulta1.setInt(2, reparacion.getIdReparacion());
            
            int res1 = consulta1.executeUpdate();
            
            if(res1>0) {
            	reparacion.setPagada(true);
            	return true;
            }else {
            	return false;
            }
            
            //((Conexion) conex).closeConnection();
        } catch (SQLException e) {
        		System.out.println(e.getMessage());
            	System.err.println("Error");
        }
        
        return false;
	}
	
	public ArrayList<ReparacionVO> getReparacion(UsuarioVO usuario,ArrayList<ReparacionVO> listaReparaciones){
		Conexion conexion = new Conexion();
		Connection conex = conexion.getConnection();
        PreparedStatement consulta1;
       
        
        try {
            consulta1 = conex.prepareStatement("SELECT * FROM REPARACIONES WHERE NIFCLIENTE=?");
            consulta1.setString(1,usuario.getNif());
            ResultSet res1 = consulta1.executeQuery();
            
            while(res1.next()) {
            	ReparacionVO reparacion = new ReparacionVO(1,null,1, 0.0, false, EstadoReparacion.ENPROCESO, "6666666B",usuario.getNif(), null,null);
            	reparacion.setIdReparacion(Integer.parseInt(res1.getString("IDREPARACION")));
            	reparacion.setMatricula(res1.getString("MATRICULAVEHICULO"));
            	reparacion.setIdFactura(Integer.parseInt(res1.getString("THEIDFACTURA")));
            	reparacion.setPrecio(Double.parseDouble(res1.getString("PRECIO")));
            	reparacion.setPagada(true);
            	if(res1.getString("ESTADO").equals("ENPROCESO")) {
            		reparacion.setEstado(EstadoReparacion.ENPROCESO);
            	}else {
            		reparacion.setEstado(EstadoReparacion.FINALIZADA);
            	}
            	reparacion.setNifMecanico(res1.getString("NIFMECANICO"));
            	reparacion.setNifCliente(res1.getString("NIFCLIENTE"));
            	if(res1.getString("TIPO").equals("RUEDA")){
            		reparacion.setTipo(TipoReparacion.RUEDA);
            	}
            	if(res1.getString("TIPO").equals("LUNA")){
            		reparacion.setTipo(TipoReparacion.LUNA);
            	}
            	if(res1.getString("TIPO").equals("AIRE")){
            		reparacion.setTipo(TipoReparacion.AIRE);
            	}
            	if(res1.getString("TIPO").equals("LUCES")){
            		reparacion.setTipo(TipoReparacion.LUCES);
            	}
            	if(res1.getString("TIPO").equals("RETROVISOR")){
            		reparacion.setTipo(TipoReparacion.RETROVISOR);
            	}
            	if(res1.getString("TIPO").equals("OTRO")){
            		reparacion.setTipo(TipoReparacion.OTRO);
            	}
            	
            	reparacion.setDescripcion(res1.getString("DESCRIPCION"));
            	
            	
            	listaReparaciones.add(reparacion);
    
            }
            return listaReparaciones;
            //((Conexion) conex).closeConnection();
        } catch (SQLException e) {
        		System.out.println(e.getMessage());
            	System.err.println("Error");
        }
        
        return null;
		
	}

	public ArrayList<ReparacionVO> getReparacionDisponible(ArrayList<ReparacionVO> listaReparaciones) {
		
		Conexion conexion = new Conexion();
		Connection conex = conexion.getConnection();
        PreparedStatement consulta1;
		
        try {
            consulta1 = conex.prepareStatement("SELECT * FROM REPARACIONES WHERE ESTADO=?");
            consulta1.setString(1, "ENPROCESO");
            ResultSet res1 = consulta1.executeQuery();
            while(res1.next()) {
            	ReparacionVO reparacion = new ReparacionVO(1,null,1, 0.0, false, EstadoReparacion.ENPROCESO, "6666666B", null, null,null);
            	reparacion.setIdReparacion(Integer.parseInt(res1.getString("IDREPARACION")));
            	reparacion.setMatricula(res1.getString("MATRICULAVEHICULO"));
            	reparacion.setIdFactura(Integer.parseInt(res1.getString("THEIDFACTURA")));
            	reparacion.setPrecio(Double.parseDouble(res1.getString("PRECIO")));
            	reparacion.setPagada(true);
            	if(res1.getString("ESTADO").equals("ENPROCESO")) {
            		reparacion.setEstado(EstadoReparacion.ENPROCESO);
            	}else {
            		reparacion.setEstado(EstadoReparacion.FINALIZADA);
            	}
            	reparacion.setNifMecanico(res1.getString("NIFMECANICO"));
            	reparacion.setNifCliente(res1.getString("NIFCLIENTE"));
            	if(res1.getString("TIPO").equals("RUEDA")){
            		reparacion.setTipo(TipoReparacion.RUEDA);
            	}
            	if(res1.getString("TIPO").equals("LUNA")){
            		reparacion.setTipo(TipoReparacion.LUNA);
            	}
            	if(res1.getString("TIPO").equals("AIRE")){
            		reparacion.setTipo(TipoReparacion.AIRE);
            	}
            	if(res1.getString("TIPO").equals("LUCES")){
            		reparacion.setTipo(TipoReparacion.LUCES);
            	}
            	if(res1.getString("TIPO").equals("RETROVISOR")){
            		reparacion.setTipo(TipoReparacion.RETROVISOR);
            	}
            	if(res1.getString("TIPO").equals("OTRO")){
            		reparacion.setTipo(TipoReparacion.OTRO);
            	}
            	
            	reparacion.setDescripcion(res1.getString("DESCRIPCION"));
            	
            	
            	listaReparaciones.add(reparacion);
    
            }
            
            return listaReparaciones;
            
        } catch (SQLException e) {
        		System.out.println(e.getMessage());
            	System.err.println("Error");
        }
        
        return null;
	}

	public boolean cambiarMecanico(ReparacionVO reparacion, UsuarioVO usuario) {

		Conexion conexion = new Conexion();
		Connection conex = conexion.getConnection();
        PreparedStatement consulta1;
        
        try {
            consulta1 = conex.prepareStatement("UPDATE REPARACIONES SET NIFMECANICO=?, ESTADO=? WHERE IDREPARACION=?");
            consulta1.setString(1,usuario.getNif());
            consulta1.setString(2, "FINALIZADO");
            consulta1.setInt(3, reparacion.getIdReparacion());
            
            int res1 = consulta1.executeUpdate();
            
            if(res1>0) {
            	return true;
            }else {
            	return false;
            }
            
            //((Conexion) conex).closeConnection();
        } catch (SQLException e) {
        		System.out.println(e.getMessage());
            	System.err.println("Error");
        }
        
        return false;
        
	}

	public ArrayList<ReparacionVO> getReparacionFinalizada(UsuarioVO usuario,
			ArrayList<ReparacionVO> listaReparaciones) {
		
		Conexion conexion = new Conexion();
		Connection conex = conexion.getConnection();
        PreparedStatement consulta1;
        
        try {
            consulta1 = conex.prepareStatement("SELECT * FROM REPARACIONES WHERE NIFMECANICO=?");
            consulta1.setString(1, usuario.getNif());
            ResultSet res1 = consulta1.executeQuery();
            while(res1.next()) {
            	ReparacionVO reparacion = new ReparacionVO(1,null,1, 0.0, false, EstadoReparacion.ENPROCESO, "6666666B", null, null,null);
            	reparacion.setIdReparacion(Integer.parseInt(res1.getString("IDREPARACION")));
            	reparacion.setMatricula(res1.getString("MATRICULAVEHICULO"));
            	reparacion.setIdFactura(Integer.parseInt(res1.getString("THEIDFACTURA")));
            	reparacion.setPrecio(Double.parseDouble(res1.getString("PRECIO")));
            	reparacion.setPagada(true);
            	if(res1.getString("ESTADO").equals("ENPROCESO")) {
            		reparacion.setEstado(EstadoReparacion.ENPROCESO);
            	}else {
            		reparacion.setEstado(EstadoReparacion.FINALIZADA);
            	}
            	reparacion.setNifMecanico(res1.getString("NIFMECANICO"));
            	reparacion.setNifCliente(res1.getString("NIFCLIENTE"));
            	if(res1.getString("TIPO").equals("RUEDA")){
            		reparacion.setTipo(TipoReparacion.RUEDA);
            	}
            	if(res1.getString("TIPO").equals("LUNA")){
            		reparacion.setTipo(TipoReparacion.LUNA);
            	}
            	if(res1.getString("TIPO").equals("AIRE")){
            		reparacion.setTipo(TipoReparacion.AIRE);
            	}
            	if(res1.getString("TIPO").equals("LUCES")){
            		reparacion.setTipo(TipoReparacion.LUCES);
            	}
            	if(res1.getString("TIPO").equals("RETROVISOR")){
            		reparacion.setTipo(TipoReparacion.RETROVISOR);
            	}
            	if(res1.getString("TIPO").equals("OTRO")){
            		reparacion.setTipo(TipoReparacion.OTRO);
            	}
            	
            	reparacion.setDescripcion(res1.getString("DESCRIPCION"));
            	
            	
            	listaReparaciones.add(reparacion);
    
            }
            
            return listaReparaciones;
            
        } catch (SQLException e) {
        		System.out.println(e.getMessage());
            	System.err.println("Error");
        }
        
        return null;
	}
}
