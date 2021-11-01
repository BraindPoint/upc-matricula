package componenteDatos;

import componenteEntidad.Alumno;
import componenteEntidad.Apoderado;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;

public class AlumnoDAO {

    private Connection cnn = null;
    private ResultSet rs = null;

    private static AlumnoDAO instancia;

    public static AlumnoDAO getInstancia() {
        if (instancia == null) {
            instancia = new AlumnoDAO();
        }
        return instancia;
    }

    public void insertar(Alumno alumno) throws SQLException {

        cnn = Conexion.getInstancia().miConexion();
        PreparedStatement ps = null;

        try {
            ps = cnn.prepareStatement("INSERT INTO alumno "
                    + "(codigo"
                    + ",codigo_apoderado"
                    + ",nombres"
                    + ",apellidos"
                    + ",sexo"
                    + ",telefono"
                    + ",direccion"
                    + ",fechaNacimiento) "
                    + "VALUES(?,?,?,?,?,?,?,?)");
            ps.setString(1, alumno.getCodigo());
            ps.setString(2, alumno.getApoderado().getCodigo());
            ps.setString(3, alumno.getNombres());
            ps.setString(4, alumno.getApellidos());
            ps.setString(5, alumno.getSexo());
            ps.setString(6, alumno.getTelefono());
            ps.setString(7, alumno.getDireccion());
            ps.setDate(8, Convertir.convertJavaDateTOSQLDate(alumno.getFechaNacimiento()));
            ps.executeUpdate();

        } catch (SQLException e) {
            System.out.println("Error insertar alumno: " + e.getMessage());
            e.printStackTrace();
        } finally {
            cnn.close();
            ps.close();
        }
    }

    public int numeroAlumnos() throws SQLException {

        cnn = Conexion.getInstancia().miConexion();
        PreparedStatement ps = null;
        try {
            ps = cnn.prepareStatement("SELECT COUNT(*) AS num FROM alumno");
            rs = ps.executeQuery();
            if (rs.next()) {
                return rs.getInt("num");
            } else {
                return 0;
            }
        } catch (SQLException e) {
            System.out.println("Error numero de alumnos: " + e.getMessage());
            return 0;
        } finally {
            cnn.close();
            ps.close();
        }
    }

    public void actualizar(Alumno alumno) throws SQLException {
        cnn = Conexion.getInstancia().miConexion();
        PreparedStatement ps = null;

        try {
            ps = cnn.prepareStatement("UPDATE alumno "
                    + "SET codigo_apoderado = ?"
                    + ",nombres = ?"
                    + ",apellidos = ?"
                    + ",sexo = ?"
                    + ",telefono = ?"
                    + ",direccion = ?"
                    + ",fechaNacimiento = ?"
                    + "WHERE codigo = ?");
            ps.setString(1, alumno.getApoderado().getCodigo());
            ps.setString(2, alumno.getNombres());
            ps.setString(3, alumno.getApellidos());
            ps.setString(4, alumno.getSexo());
            ps.setString(5, alumno.getTelefono());
            ps.setString(6, alumno.getDireccion());
            ps.setDate(7, Convertir.convertJavaDateTOSQLDate(alumno.getFechaNacimiento()));
            ps.setString(8, alumno.getCodigo());
            ps.executeUpdate();

        } catch (SQLException e) {
            System.out.println("Error actualizar alumno: " + e.getMessage());
        } finally {
            cnn.close();
            ps.close();
        }
    }

    public void eliminar(String codigo) throws SQLException {
        cnn = Conexion.getInstancia().miConexion();
        PreparedStatement ps = null;
        try {
            ps = cnn.prepareStatement("DELETE FROM alumno WHERE codigo = ?");
            ps.setString(1, codigo);
            ps.executeUpdate();

        } catch (SQLException e) {
            System.out.println("Error eliminar alumno" + e.getMessage());
        } finally {
            cnn.close();
            ps.close();
        }
    }

    public Alumno buscarAlumno(String codigo) throws SQLException {

        cnn = Conexion.getInstancia().miConexion();
        PreparedStatement ps = null;
        Alumno alumno = null;
        try {
            ps = cnn.prepareStatement("SELECT * FROM alumno WHERE codigo = ?");
            ps.setString(1, codigo);
            rs = ps.executeQuery();

            if (rs.next()) {
                String idApoderado = rs.getString("codigo_apoderado");
                Apoderado apoderado = ApoderadoDAO.getInstancia().buscarApoderado(idApoderado);
                String nombres = rs.getString("nombres");
                String apellidos = rs.getString("apellidos");
                String sexo = rs.getString("sexo");
                String telefono = rs.getString("telefono");
                String direccion = rs.getString("direccion");
                Date fechaNacimiento = rs.getDate("fechaNacimiento");
                alumno = new Alumno(codigo, apoderado, sexo, nombres, apellidos, telefono, direccion, fechaNacimiento);
            }
        } catch (SQLException ex) {
            System.out.println("error buscar alumno:  " + ex.getMessage());
        } finally {
            cnn.close();
            ps.close();
        }
        return alumno;
    }

    public ArrayList<Alumno> mostrarAlumnos() throws SQLException {

        cnn = Conexion.getInstancia().miConexion();
        PreparedStatement ps = null;
        ArrayList<Alumno> lista = new ArrayList<Alumno>();
        ResultSet rs1 = null;
        try {
            ps = cnn.prepareStatement("SELECT * FROM alumno");
            rs1 = ps.executeQuery();

            while (rs1.next()) {
                String codigo = rs1.getString("codigo");
                Alumno alumno = buscarAlumno(codigo);
                lista.add(alumno);
            }
        } catch (SQLException e) {
            System.out.println("Error mostrar alumno: " + e.getMessage());
        } finally {
            cnn.close();
            ps.close();
        }
        return lista;
    }

    public ArrayList<Alumno> buscarPorNombre(String nom) throws SQLException {
        cnn = Conexion.getInstancia().miConexion();
        PreparedStatement ps = null;
        ArrayList<Alumno> lista = new ArrayList<Alumno>();
        try {
            ps = cnn.prepareStatement("SELECT * FROM alumno where nombres like ?");
            ps.setString(1, nom + "%");
            rs = ps.executeQuery();
            while (rs.next()) {
                String codigo = rs.getString("codigo");
                String idApoderado = rs.getString("codigo_apoderado");
                Apoderado apoderado = ApoderadoDAO.getInstancia().buscarApoderado(idApoderado);
                String nombres = rs.getString("nombres");
                String apellidos = rs.getString("apellidos");
                String telefono = rs.getString("telefono");
                String sexo = rs.getString("sexo");
                String direccion = rs.getString("direccion");
                Date fechaNacimiento = rs.getDate("fechaNacimiento");
                Alumno alumno = new Alumno(codigo, apoderado, sexo, nombres, apellidos, telefono, direccion, fechaNacimiento);
                lista.add(alumno);
            }
        } catch (SQLException ex) {
            System.out.println("Error al buscar por nombre: " + ex.getMessage());
        } finally {
            cnn.close();
            ps.close();
        }
        return lista;
    }
}
