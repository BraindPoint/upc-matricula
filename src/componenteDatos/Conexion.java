package componenteDatos;

import util.Constantes;
import java.sql.*;

public class Conexion {

    private static Conexion instancia;
    
    public static Conexion getInstancia() {
        if (instancia == null) {
            instancia = new Conexion();
        }
        return instancia;
    }

    private Conexion() {

    }

    public Connection miConexion() {
        Connection cn = null;

        try {
            Class.forName(Constantes.DB_DRIVER);
        } catch (ClassNotFoundException e) {
            System.out.println("Error falta cargar el driver" + e.getMessage());
        }

        try {
            cn = DriverManager.getConnection(Constantes.DB_URI, Constantes.DB_USER, Constantes.DB_PASSWORD);
        } catch (SQLException e) {
            System.out.println("Error no se puede establecer la conexion" + e.getMessage());
        }

        return cn;
    }
}
