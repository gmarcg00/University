package modelo.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JOptionPane;

import aplicacion.AutoKingException;
import conexion.Conexion;
import modelo.vo.ClienteVO;
import modelo.vo.UsuarioVO;

public class UsuarioDAO {
	public UsuarioVO setUsuario(UsuarioVO usuario) {
		Conexion conexion = new Conexion();
		Connection conex = conexion.getConnection();
        PreparedStatement consulta1;

        try {
            consulta1 = conex.prepareStatement("INSERT INTO USUARIOS(NIF,THECIFEMPRESA,PASSWORD,NOMBRE,APELLIDOS,DIRECCION,TELEFNO,EMAIL,TIPO) VALUES(?,?,?,?,?,?,?,?,?)");
            consulta1.setString(1, usuario.getNif());
            consulta1.setString(2, "B67657189");
            consulta1.setString(3, usuario.getPassword());
            consulta1.setString(4, usuario.getNombre());
            consulta1.setString(5, usuario.getApellidos());
            consulta1.setString(6, usuario.getDireccion());
            consulta1.setString(7, usuario.getTelefono());
            consulta1.setString(8, usuario.getEmail());
            consulta1.setString(9, usuario.getTipo());
            int res1 = consulta1.executeUpdate();
            
            
            return usuario;
            //((Conexion) conex).closeConnection();
        } catch (SQLException e) {
        		System.out.println(e.getMessage());
            	System.err.println("Error");
        }
        
        return null;
	}
	
	
	/*
	 * Busca un usuario por su nif y contraseña
	 */
	public UsuarioVO getUsuario(UsuarioVO usuario) {
		Conexion conexion = new Conexion();
		Connection conex = conexion.getConnection();
        PreparedStatement consulta1;

        try {
            consulta1 = conex.prepareStatement("SELECT * FROM USUARIOS WHERE NIF=? AND PASSWORD=?");
            consulta1.setString(1, usuario.getNif());
            consulta1.setString(2, usuario.getPassword());
            ResultSet res1 = consulta1.executeQuery();
            if(res1.next()) {
            	usuario.setNombre(res1.getString("NOMBRE"));
            	usuario.setNif(res1.getString("NIF"));
            	usuario.setApellidos(res1.getString("APELLIDOS"));
            	usuario.setCifEmpresa(res1.getString("THECIFEMPRESA"));
            	usuario.setPassword(res1.getString("PASSWORD"));
            	usuario.setDireccion(res1.getString("DIRECCION"));
            	usuario.setEmail(res1.getString("EMAIL"));
            	usuario.setTelefono(res1.getString("TELEFNO"));
            	usuario.setTipo(res1.getString("TIPO"));
            	return usuario;
            }	
            
            //((Conexion) conex).closeConnection();
        } catch (SQLException e) {
        		System.out.println(e.getMessage());
            	System.err.println("Error");
        }
        
        return null;
	}
	/*
	 * Busca un usuario por su nif
	 */
	public UsuarioVO existsUsuario(UsuarioVO usuario) {
		Conexion conexion = new Conexion();
		Connection conex = conexion.getConnection();
        PreparedStatement consulta1;

        try {
            consulta1 = conex.prepareStatement("SELECT * FROM USUARIOS WHERE NIF=?");
            consulta1.setString(1, usuario.getNif());
            ResultSet res1 = consulta1.executeQuery();
            if(res1.next()) {
            	usuario.setNombre(res1.getString("NOMBRE"));
            	usuario.setNif(res1.getString("NIF"));
            	usuario.setApellidos(res1.getString("APELLIDOS"));
            	usuario.setCifEmpresa(res1.getString("THECIFEMPRESA"));
            	usuario.setPassword(res1.getString("PASSWORD"));
            	usuario.setDireccion(res1.getString("DIRECCION"));
            	usuario.setEmail(res1.getString("EMAIL"));
            	usuario.setTelefono(res1.getString("TELEFNO"));
            	usuario.setTipo(res1.getString("TIPO"));
            	return usuario;
            }	
            
            //((Conexion) conex).closeConnection();
        } catch (SQLException e) {
        		System.out.println(e.getMessage());
            	System.err.println("Error");
        }
        
        return null;
	}
	
	
	public int removeUsuario(UsuarioVO usuario) {
		Conexion conexion = new Conexion();
		Connection conex = conexion.getConnection();
        PreparedStatement consulta1;
        
        int res1=0;

        try {
            consulta1 = conex.prepareStatement("DELETE FROM USUARIOS WHERE NIF=?");
            consulta1.setString(1, usuario.getNif());
            res1 = consulta1.executeUpdate();
            
            
            return res1;
            //((Conexion) conex).closeConnection();
        } catch (SQLException e) {
        		System.out.println(e.getMessage());
            	System.err.println("Error");
        }
        
        return res1;
	}
}
