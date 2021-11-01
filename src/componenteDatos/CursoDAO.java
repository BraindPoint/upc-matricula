package componenteDatos;

import componenteEntidad.Curso;
import componenteEntidad.Profesor;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class CursoDAO {

    private Connection cnn = null;
    private ResultSet rs = null;

    private static CursoDAO instancia;

    public static CursoDAO getInstancia() {
        if (instancia == null) {
            instancia = new CursoDAO();
        }
        return instancia;
    }

    public void insertar(Curso curso) throws SQLException {

        cnn = Conexion.getInstancia().miConexion();
        PreparedStatement ps = null;

        try {
            ps = cnn.prepareStatement("INSERT INTO curso "
                    + "(codigo"
                    + ",codigo_profesor"
                    + ",nombre) "
                    + "VALUES(?,?,?)");
            ps.setString(1, curso.getCodigo());
            ps.setString(2, curso.getProfesor().getCodigo());
            ps.setString(3, curso.getNombre());
            ps.executeUpdate();

        } catch (SQLException e) {
            System.out.println("Error insertar curso: " + e.getMessage());
            e.printStackTrace();
        } finally {
            cnn.close();
            ps.close();
        }
    }

    public int numeroCursos() throws SQLException {

        cnn = Conexion.getInstancia().miConexion();
        PreparedStatement ps = null;
        try {
            ps = cnn.prepareStatement("SELECT COUNT(*) AS num FROM curso");
            rs = ps.executeQuery();
            if (rs.next()) {
                return rs.getInt("num");
            } else {
                return 0;
            }
        } catch (SQLException e) {
            System.out.println("Error numero de cursos: " + e.getMessage());
            return 0;
        } finally {
            cnn.close();
            ps.close();
        }
    }

    public void actualizar(Curso curso) throws SQLException {
        cnn = Conexion.getInstancia().miConexion();
        PreparedStatement ps = null;

        try {
            ps = cnn.prepareStatement("UPDATE curso "
                    + "SET codigo_profesor = ?"
                    + ",nombre = ?"
                    + "WHERE codigo = ?");
            ps.setString(1, curso.getProfesor().getCodigo());
            ps.setString(2, curso.getNombre());
            ps.setString(3, curso.getCodigo());
            ps.executeUpdate();

        } catch (SQLException e) {
            System.out.println("Error actualizar curso: " + e.getMessage());
        } finally {
            cnn.close();
            ps.close();
        }
    }

    public void eliminar(String idCurso) throws SQLException {
        cnn = Conexion.getInstancia().miConexion();
        PreparedStatement ps = null;
        try {
            ps = cnn.prepareStatement("DELETE FROM curso WHERE codigo = ?");
            ps.setString(1, idCurso);
            ps.executeUpdate();

        } catch (SQLException e) {
            System.out.println("Error eliminar curso" + e.getMessage());
        } finally {
            cnn.close();
            ps.close();
        }
    }

    public Curso buscarCurso(String idCurso) throws SQLException {

        cnn = Conexion.getInstancia().miConexion();
        PreparedStatement ps = null;
        Curso curso = null;
        try {
            ps = cnn.prepareStatement("SELECT * FROM curso WHERE codigo = ?");
            ps.setString(1, idCurso);
            rs = ps.executeQuery();

            if (rs.next()) {
                String idProfesor = rs.getString("profesor_idprofesor");
                Profesor profesor = ProfesorDAO.getInstancia().buscarProfesor(idProfesor);
                String nombre = rs.getString("nombre");
                curso = new Curso(idCurso, profesor, nombre);
            }
        } catch (SQLException ex) {
            System.out.println("error buscar curso:  " + ex.getMessage());
        } finally {
            cnn.close();
            ps.close();
        }
        return curso;
    }

    public ArrayList<Curso> mostrarCursosProfesor(String idProfesor) throws SQLException {
        cnn = Conexion.getInstancia().miConexion();
        PreparedStatement ps = null;
        ResultSet rs1 = null;
        ArrayList<Curso> lista = new ArrayList<Curso>();
        try {
            ps = cnn.prepareStatement("SELECT * FROM curso where codigo_profesor = ?");
            ps.setString(1, idProfesor);
            rs1 = ps.executeQuery();
            while (rs1.next()) {
                String idCurso = rs1.getString("codigo");
                Curso curso = buscarCurso(idCurso);
                lista.add(curso);
            }
        } catch (SQLException ex) {
            System.out.println("error mostrar cursos de profesores: " + ex.getMessage());
        } finally {
            cnn.close();
            ps.close();
        }
        return lista;
    }

    public ArrayList<Curso> mostrarCursos() throws SQLException {

        cnn = Conexion.getInstancia().miConexion();
        PreparedStatement ps = null;
        ArrayList<Curso> lista = new ArrayList<Curso>();
        ResultSet rs1 = null;
        try {
            ps = cnn.prepareStatement("SELECT * FROM curso");
            rs1 = ps.executeQuery();

            while (rs1.next()) {
                String idCurso = rs1.getString("codigo");
                Curso curso = buscarCurso(idCurso);
                lista.add(curso);
            }
        } catch (SQLException e) {
            System.out.println("Error mostrar curso: " + e.getMessage());
        } finally {
            cnn.close();
            ps.close();
        }
        return lista;
    }

    public ArrayList<Curso> buscarPorNombre(String nom) throws SQLException {
        cnn = Conexion.getInstancia().miConexion();
        PreparedStatement ps = null;
        ArrayList<Curso> lista = new ArrayList<Curso>();
        try {
            ps = cnn.prepareStatement("SELECT * FROM curso where nombre like ?");
            ps.setString(1, nom + "%");
            rs = ps.executeQuery();
            while (rs.next()) {
                String idCurso = rs.getString("codigo");
                String idProfesor = rs.getString("codigo_profesor");
                Profesor profesor = ProfesorDAO.getInstancia().buscarProfesor(idProfesor);
                String nombre = rs.getString("nombre");
                Curso curso = new Curso(idCurso, profesor, nombre);
                lista.add(curso);
            }
        } catch (SQLException ex) {
            System.out.println("Error buscar por nombre: " + ex.getMessage());
        } finally {
            cnn.close();
            ps.close();
        }
        return lista;
    }
}
