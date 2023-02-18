package conexion;



import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import javax.swing.JOptionPane;

//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;


public class Conexion {
    //static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
	
	//static final String DB_URL = "jdbc:mysql://192.168.31.233:3306/Autoking";
	static final String DB_URL = "jdbc:mysql://johnny.heliohost.org:3306/gmarcg00_AutoKing";
    static final String USER = "gmarcg00";
    static final String PASS = "Autoking00";
    //Logger logger = LoggerFactory.getLogger(this.getClass());

    
    Connection con = null;

    public Conexion() {
        try {
        	Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection(DB_URL, USER, PASS);
        } catch (SQLException | ClassNotFoundException se) {
            //logger.error(se.getMess
        	JOptionPane.showMessageDialog(null, "Error en la conexion");
        	System.out.println(se.getMessage());
        } 
    }
    
    public Connection getConnection() {
        if (con == null) {
        	return (Connection) new Conexion();
        }else {
        	return con;
        }
    }

    public void closeConnection() {
        try {
            con.close();
        } catch (SQLException e) {
           // logger.error("~ Error closing connection!");
        }
    }
}


 