package componenteDatos;

import componenteEntidad.Aula;
import java.sql.*;
import java.util.ArrayList;

public class AulaDAO {

    private Connection cnn = null;
    private ResultSet rs = null;

    private static AulaDAO instancia;

    public static AulaDAO getInstancia() {
        if (instancia == null) {
            instancia = new AulaDAO();
        }
        return instancia;
    }

    public void insertar(String codigo, int numeroAula, int capacidad) throws SQLException {

        cnn = Conexion.getInstancia().miConexion();
        PreparedStatement ps = null;

        try {
            ps = cnn.prepareStatement("INSERT INTO aula"
                    + "(codigo"
                    + ",numeroAula"
                    + ",capacidad) "
                    + "VALUES(?,?,?)");
            ps.setString(1, codigo);
            ps.setInt(2, numeroAula);
            ps.setInt(3, capacidad);
            ps.executeUpdate();

        } catch (SQLException e) {
            System.out.println("Error insertar aula: " + e.getMessage());
        } finally {
            cnn.close();
            ps.close();
        }
    }

    public Aula buscaAula(String codigo) throws SQLException {

        cnn = Conexion.getInstancia().miConexion();
        PreparedStatement ps = null;
        Aula aula = null;

        try {
            ps = cnn.prepareStatement("SELECT * FROM aula WHERE codigo = ?");
            ps.setString(1, codigo);
            rs = ps.executeQuery();
            if (rs.next()) {
                int numeroAula = rs.getInt("numeroAula");
                int cantidad = rs.getInt("capacidad");

                aula = new Aula(codigo, numeroAula, cantidad);
            }
        } catch (SQLException e) {
            System.out.println("Error buscar aula: " + e.getMessage());
        } finally {
            cnn.close();
            ps.close();
        }
        return aula;
    }

    public void actualizar(String codigo, int numeroAula, int capacidad) throws SQLException {

        cnn = Conexion.getInstancia().miConexion();
        PreparedStatement ps = null;

        try {
            ps = cnn.prepareStatement("UPDATE aula "
                    + "SET numeroAula = ?"
                    + ",capacidad = ?"
                    + "WHERE codigo = ?");
            ps.setInt(1, numeroAula);
            ps.setInt(2, capacidad);
            ps.setString(3, codigo);
            ps.executeUpdate();

        } catch (SQLException e) {
            System.out.println("Error actualizar aula: " + e.getMessage());
        } finally {
            cnn.close();
            ps.close();
        }
    }

    public void eliminar(String codigo) throws SQLException {
        cnn = Conexion.getInstancia().miConexion();
        PreparedStatement ps = null;
        try {
            ps = cnn.prepareStatement("DELETE FROM aula WHERE codigo = ?");
            ps.setString(1, codigo);
            ps.executeUpdate();

        } catch (SQLException e) {
            System.out.println("Error eliminar aula" + e.getMessage());
        } finally {
            cnn.close();
            ps.close();
        }
    }

    public ArrayList<Aula> mostrarAulas() throws SQLException {

        cnn = Conexion.getInstancia().miConexion();
        PreparedStatement ps = null;
        ArrayList<Aula> lista = new ArrayList<Aula>();

        try {
            ps = cnn.prepareStatement("SELECT * FROM aula");
            rs = ps.executeQuery();

            while (rs.next()) {
                String codigo = rs.getString("codigo");
                int numeroAula = rs.getInt("numeroAula");
                int capacidad = rs.getInt("capacidad");

                Aula aula = new Aula(codigo, numeroAula, capacidad);

                lista.add(aula);
            }
        } catch (SQLException e) {
            System.out.println("Error mostrar aulas: " + e.getMessage());
        } finally {
            cnn.close();
            ps.close();
        }
        return lista;
    }

    public ArrayList<Aula> buscarPorNumero(int num) throws SQLException {
        cnn = Conexion.getInstancia().miConexion();
        PreparedStatement ps = null;
        ArrayList<Aula> lista = new ArrayList<Aula>();
        try {
            ps = cnn.prepareStatement("SELECT * FROM aula where numeroAula = ?");
            ps.setInt(1, Integer.valueOf(num));
            rs = ps.executeQuery();
            while (rs.next()) {
                String codigo = rs.getString("codigo");
                int numeroAula = rs.getInt("numeroAula");
                int capacidad = rs.getInt("capacidad");
                Aula aula = new Aula(codigo, numeroAula, capacidad);
                lista.add(aula);
            }
        } catch (SQLException ex) {
            System.out.println("Error buscar por numero de aula: " + ex.getMessage());
        } finally {
            cnn.close();
            ps.close();
        }
        return lista;
    }
}
