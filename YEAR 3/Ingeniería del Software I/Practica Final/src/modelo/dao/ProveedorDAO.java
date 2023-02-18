package modelo.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.swing.JOptionPane;

import conexion.Conexion;
import modelo.vo.EmpresaVO;
import modelo.vo.ProveedorVO;

public class ProveedorDAO {
	public ProveedorVO setProveedor(ProveedorVO proveedor) {
		Conexion conexion = new Conexion();
		Connection conex = conexion.getConnection();
        PreparedStatement consulta1;

        try {
            consulta1 = conex.prepareStatement("INSERT INTO PROVEEDORES(CIF,NOMBRE,DIRECCION,TELEFONO,EMAIL) VALUES(?,?,?,?,?)");
            consulta1.setString(1, proveedor.getCif());
            consulta1.setString(2, proveedor.getNombre());
            consulta1.setString(3, proveedor.getDireccion());
            consulta1.setString(4, proveedor.getTelefono());
            consulta1.setString(5, proveedor.getEmail());
            int res1 = consulta1.executeUpdate();
            
            
            return proveedor;
            
            //((Conexion) conex).closeConnection();
        } catch (SQLException e) {
        		System.out.println(e.getMessage());
            	System.err.println("Error");
        }
        return null;
	}
}
