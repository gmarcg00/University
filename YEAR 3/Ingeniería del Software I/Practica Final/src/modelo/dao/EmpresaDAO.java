package modelo.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.swing.JOptionPane;

import conexion.Conexion;
import modelo.vo.ClienteVO;
import modelo.vo.EmpresaVO;

public class EmpresaDAO {
	public void setEmpresa(EmpresaVO empresa) {
		Conexion conexion = new Conexion();
		Connection conex = conexion.getConnection();
        PreparedStatement consulta1;

        try {
            consulta1 = conex.prepareStatement("INSERT INTO EMPRESAS(CIF,NOMBRE,DIRECCION,CIUDAD,PAIS,TELEFONO) VALUES(?,?,?,?,?,?)");
            consulta1.setString(1, empresa.getCif());
            consulta1.setString(2, empresa.getNombre());
            consulta1.setString(3, empresa.getDireccion());
            consulta1.setString(4, empresa.getCiudad());
            consulta1.setString(5, empresa.getPais());
            consulta1.setString(6, empresa.getTelefono());
            int res1 = consulta1.executeUpdate();
            
            //((Conexion) conex).closeConnection();
        } catch (SQLException e) {
        		System.out.println(e.getMessage());
            	System.err.println("Error");
        }
	}
}
