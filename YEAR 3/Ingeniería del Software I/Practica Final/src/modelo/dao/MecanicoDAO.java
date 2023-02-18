package modelo.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.swing.JOptionPane;

import conexion.Conexion;
import modelo.vo.EmpleadoVO;
import modelo.vo.MecanicoVO;

public class MecanicoDAO extends EmpleadoDAO{
	public MecanicoVO setMecanico(MecanicoVO mecanico) {
		Conexion conexion = new Conexion();
		Connection conex = conexion.getConnection();
        PreparedStatement consulta1;

        try {
            consulta1 = conex.prepareStatement("INSERT INTO MECANICOS(NIFMECANICO) VALUES(?)");
            consulta1.setString(1, mecanico.getNif());
            int res1 = consulta1.executeUpdate();
            
            return mecanico;
            //((Conexion) conex).closeConnection();
        } catch (SQLException e) {
        		System.out.println(e.getMessage());
            	System.err.println("Error");
        }
        
        return null;
	}
	
	public int removeMecanico(MecanicoVO mecanico) {
		Conexion conexion = new Conexion();
		Connection conex = conexion.getConnection();
        PreparedStatement consulta1;
        int res1=0;

        try {
            consulta1 = conex.prepareStatement("DELETE FROM MECANICOS WHERE NIFMECANICO=?");
            consulta1.setString(1, mecanico.getNif());
            res1=consulta1.executeUpdate();
            
            
            return res1;
            //((Conexion) conex).closeConnection();
        } catch (SQLException e) {
        		System.out.println(e.getMessage());
            	System.err.println("Error");
        }
        
        return res1;
	}
}
