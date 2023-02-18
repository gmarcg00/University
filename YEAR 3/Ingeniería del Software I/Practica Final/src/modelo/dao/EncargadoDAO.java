package modelo.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.swing.JOptionPane;

import conexion.Conexion;
import modelo.vo.EmpleadoVO;

public class EncargadoDAO extends EmpleadoDAO{
	public EmpleadoVO setEncargado(EmpleadoVO encargado) {
		Conexion conexion = new Conexion();
		Connection conex = conexion.getConnection();
        PreparedStatement consulta1;

        try {
            consulta1 = conex.prepareStatement("INSERT INTO ENCARGADOS(NIFENCARGADO) VALUES(?)");
            consulta1.setString(1, encargado.getNif());
            int res1 = consulta1.executeUpdate();
            
            return encargado;
            
            //((Conexion) conex).closeConnection();
        } catch (SQLException e) {
        		System.out.println(e.getMessage());
            	System.err.println("Error");
        }
        
        return null;
	}
}
