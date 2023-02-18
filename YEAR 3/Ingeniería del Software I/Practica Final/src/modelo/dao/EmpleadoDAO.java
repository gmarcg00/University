package modelo.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.swing.JOptionPane;

import conexion.Conexion;
import modelo.vo.EmpleadoVO;

public class EmpleadoDAO extends UsuarioDAO{
	public EmpleadoVO setEmpleado(EmpleadoVO empleado) {
		Conexion conexion = new Conexion();
		Connection conex = conexion.getConnection();
        PreparedStatement consulta1;

        try {
            consulta1 = conex.prepareStatement("INSERT INTO EMPLEADOS(NIFEMPLEADO,SUELDO) VALUES(?,?)");
            consulta1.setString(1, empleado.getNif());
            consulta1.setDouble(2,1550.0);
            consulta1.executeUpdate();
            
            return empleado;
            //((Conexion) conex).closeConnection();
        } catch (SQLException e) {
        		System.out.println(e.getMessage());
            	System.err.println("Error");
        }
        
        return null;
	}
	
	public int removeEmpleado(EmpleadoVO empleado) {
		Conexion conexion = new Conexion();
		Connection conex = conexion.getConnection();
        PreparedStatement consulta1;
        
        int res1=0;

        try {
        	consulta1 = conex.prepareStatement("DELETE FROM EMPLEADOS WHERE NIFEMPLEADO=?");
            consulta1.setString(1, empleado.getNif());
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
