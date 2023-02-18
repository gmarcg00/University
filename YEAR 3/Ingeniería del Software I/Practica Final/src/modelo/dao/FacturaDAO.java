package modelo.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import conexion.Conexion;
import modelo.logica.TipoDePago;
import modelo.vo.FacturaVO;
import modelo.vo.UsuarioVO;

public class FacturaDAO {
	public FacturaVO setFactura(FacturaVO factura) {
		Conexion conexion = new Conexion();
		Connection conex = conexion.getConnection();
        PreparedStatement consulta1;

        try {
            consulta1 = conex.prepareStatement("INSERT INTO FACTURAS(IDFACTURA,CIFEMPRESA,NOMBRE_EMPRESA,NIFCLIENTEFACTURA,PRECIOTOTAL,TIPOPAGO) VALUES(?,?,?,?,?,?)");
            consulta1.setInt(1, factura.getIdFactura());
            consulta1.setString(2, "B67657189");
            consulta1.setString(3, factura.getNombreEmpresa());
            consulta1.setString(4, factura.getNifCliente());
            consulta1.setDouble(5, factura.getPrecioTotal());
            consulta1.setString(6, factura.getTipoPago());
            //consulta1.setString(7, factura.getFecha());
            consulta1.executeUpdate();
            
            
            return factura;
            //((Conexion) conex).closeConnection();
        } catch (SQLException e) {
        		System.out.println(e.getMessage());
            	System.err.println("Error");
        }
        
        return null;
	}
	
	public int getIdFactura() {
		Conexion conexion = new Conexion();
		Connection conex = conexion.getConnection();
        PreparedStatement consulta1;
        
        try {
            consulta1 = conex.prepareStatement("SELECT IDFACTURA FROM FACTURAS ORDER BY IDFACTURA DESC ");
            ResultSet res1 = consulta1.executeQuery();
            int n=-1;
            if(res1.next()) {
            	n=res1.getInt("IDFACTURA");
            }
            return n;
            //((Conexion) conex).closeConnection();
        } catch (SQLException e) {
        		System.out.println(e.getMessage());
            	System.err.println("Error");
        }
        
        return -1;
        
	}
	
	public boolean cambiarPago(FacturaVO factura) {
		Conexion conexion = new Conexion();
		Connection conex = conexion.getConnection();
        PreparedStatement consulta1;
        
      
        try {
            consulta1 = conex.prepareStatement("UPDATE FACTURAS SET TIPOPAGO=? WHERE IDFACTURA=?");
            consulta1.setString(1,factura.getTipoPago());
            consulta1.setInt(2,factura.getIdFactura());
            
            int res1 = consulta1.executeUpdate();
            
            if(res1>0) {
            	return true;
            }else {
            	return false;
            }
            
        } catch (SQLException e) {
        		System.out.println(e.getMessage());
            	System.err.println("Error");
        }
        
        return false;
	}
	
	public ArrayList<FacturaVO> getFactura(UsuarioVO usuario,ArrayList<FacturaVO> listaFacturas){
		Conexion conexion = new Conexion();
		Connection conex = conexion.getConnection();
        PreparedStatement consulta1;
       
        
        try {
            consulta1 = conex.prepareStatement("SELECT * FROM FACTURAS WHERE NIFCLIENTEFACTURA=?");
            consulta1.setString(1,usuario.getNif());
            ResultSet res1 = consulta1.executeQuery();
            
            while(res1.next()) {
            	 FacturaVO factura=new FacturaVO(1,"B67657189","AUTOKING SL",usuario.getNif(), 0.0, TipoDePago.ALCONTADO, null);
            	factura.setIdFactura(res1.getInt("IDFACTURA"));
            	factura.setCifEmpresa(res1.getString("CIFEMPRESA"));
            	factura.setNombreEmpresa(res1.getString("NOMBRE_EMPRESA"));
            	factura.setNifCliente(res1.getString("NIFCLIENTEFACTURA"));
            	factura.setPrecioTotal(res1.getDouble("PRECIOTOTAL"));
            	//listaFacturas.get(i).setTipoPago(res1.getString("TIPOPAGO"));
            	
            	listaFacturas.add(factura);
    
            }
            return listaFacturas;
            //((Conexion) conex).closeConnection();
        } catch (SQLException e) {
        		System.out.println(e.getMessage());
            	System.err.println("Error");
        }
        
        return null;
		
	}
}
