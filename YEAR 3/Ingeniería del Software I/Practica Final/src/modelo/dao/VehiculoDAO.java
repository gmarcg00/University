package modelo.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.swing.JOptionPane;

import conexion.Conexion;

import modelo.vo.VehiculoVO;

public class VehiculoDAO {
	
	public void setVehiculo(VehiculoVO vehiculo) {
		Conexion conexion = new Conexion();
		Connection conex = conexion.getConnection();
        PreparedStatement consulta1;

        try {
            consulta1 = conex.prepareStatement("INSERT INTO VEHICULOS(MATRICULA,THENIFCLIENTE,MARCA,MODELO) VALUES(?,?,?,?)");
            consulta1.setString(1, vehiculo.getMatricula());
            consulta1.setString(2, vehiculo.getNifCliente());
            consulta1.setString(3, vehiculo.getMarca());
            consulta1.setString(4, vehiculo.getModelo());

            int res1 = consulta1.executeUpdate();
            

        } catch (SQLException e) {
        		System.out.println(e.getMessage());
            	System.err.println("Error");
        }
	}
}
