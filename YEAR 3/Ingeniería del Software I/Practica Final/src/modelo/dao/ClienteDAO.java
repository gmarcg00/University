package modelo.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JOptionPane;

import conexion.Conexion;
import modelo.vo.ClienteVO;
import modelo.vo.UsuarioVO;

public class ClienteDAO {
	
	public ClienteVO setCliente(ClienteVO cliente) {
		Conexion conexion = new Conexion();
		Connection conex = conexion.getConnection();
        PreparedStatement consulta1;

        try {
            consulta1 = conex.prepareStatement("INSERT INTO CLIENTES(NIFCLIENTE) VALUES(?)");
            consulta1.setString(1, cliente.getNif());
            int res1 = consulta1.executeUpdate();
            
            
            
            return cliente;
            //((Conexion) conex).closeConnection();
        } catch (SQLException e) {
        		System.out.println(e.getMessage());
            	System.err.println("Error");
        }
        return null;
	}
	
}
