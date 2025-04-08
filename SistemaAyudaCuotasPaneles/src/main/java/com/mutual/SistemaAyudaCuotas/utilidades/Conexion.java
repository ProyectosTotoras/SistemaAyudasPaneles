package com.mutual.SistemaAyudaCuotas.utilidades;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;
import javax.swing.JOptionPane;

public class Conexion {
	
	private Connection conn;

    public Conexion() {

        try {
            
            Properties propiedades = new Properties();
            
            InputStream archivoProp = null;
            archivoProp = getClass().getClassLoader().getResourceAsStream("application.properties");
            
            propiedades.load(archivoProp);
            
            String datasourceURL = propiedades.getProperty("spring.datasource.url");
            String usuarioBBDD = propiedades.getProperty("spring.datasource.username");
            String contraseniaBBDD = propiedades.getProperty("spring.datasource.password");
            
            Class.forName("com.mysql.cj.jdbc.Driver");

            String db = datasourceURL;

            conn = DriverManager.getConnection(db, usuarioBBDD, contraseniaBBDD);
            
        } catch (Exception ex) {
        	
            JOptionPane.showMessageDialog(null, "No se pudo conectar a la BD, intente nuevamente");
            System.exit(0);
            
        }
        
    }

    public Connection getConexion() {
    	
        return conn;
        
    }

    public Connection cerrarConexion() throws SQLException {
    	
        conn.close();
        conn = null;
        
        return conn;
        
    }

}
