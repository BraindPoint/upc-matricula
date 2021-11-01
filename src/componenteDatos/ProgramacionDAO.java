package componenteDatos;

import componenteEntidad.Aula;
import componenteEntidad.Horario;
import componenteEntidad.Programacion;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class ProgramacionDAO {

    private Connection cnn = null;
    private ResultSet rs = null;

    private static ProgramacionDAO instancia;

    public static ProgramacionDAO getInstancia() {
        if (instancia == null) {
            instancia = new ProgramacionDAO();
        }
        return instancia;
    }

    public void insertar(Programacion programacion) throws SQLException {

        cnn = Conexion.getInstancia().miConexion();
        PreparedStatement ps = null;

        try {
            ps = cnn.prepareStatement("INSERT INTO programacion "
                    + "(codigo"
                    + ",codigo_horario"
                    + ",codigo_aula) "
                    + "VALUES(?,?,?)");
            ps.setString(1, programacion.getCodigo());
            ps.setString(2, programacion.getHorario().getCodigo());
            ps.setString(3, programacion.getAula().getCodigo());
            ps.executeUpdate();

        } catch (SQLException e) {
            System.out.println("Error insertar programacion: " + e.getMessage());
            e.printStackTrace();
        } finally {
            cnn.close();
            ps.close();
        }
    }

    public void actualizar(Programacion programacion) throws SQLException {
        cnn = Conexion.getInstancia().miConexion();
        PreparedStatement ps = null;

        try {
            ps = cnn.prepareStatement("UPDATE programacion "
                    + "SET codigo_horario = ?"
                    + ",codigo_aula = ?"
                    + "WHERE codigo = ?");
            ps.setString(1, programacion.getHorario().getCodigo());
            ps.setString(2, programacion.getAula().getCodigo());
            ps.setString(3, programacion.getCodigo());
            ps.executeUpdate();

        } catch (SQLException e) {
            System.out.println("Error actualizar programacion: " + e.getMessage());
        } finally {
            cnn.close();
            ps.close();
        }
    }

    public void eliminar(String idProgramacion) throws SQLException {
        cnn = Conexion.getInstancia().miConexion();
        PreparedStatement ps = null;
        try {
            ps = cnn.prepareStatement("DELETE FROM programacion WHERE codigo = ?");
            ps.setString(1, idProgramacion);
            ps.executeUpdate();

        } catch (SQLException e) {
            System.out.println("Error eliminar programacion" + e.getMessage());
        } finally {
            cnn.close();
            ps.close();
        }
    }

    public Programacion buscarProgramacion(String idProgramacion) throws SQLException {

        cnn = Conexion.getInstancia().miConexion();
        PreparedStatement ps = null;
        Programacion programacion = null;
        try {
            ps = cnn.prepareStatement("SELECT * FROM programacion WHERE codigo = ?");
            ps.setString(1, idProgramacion);
            rs = ps.executeQuery();

            if (rs.next()) {
                String idHorario = rs.getString("codigo_horario");
                Horario horario = HorarioDAO.getInstancia().buscarHorario(idHorario);
                String idAula = rs.getString("codigo_aula");
                Aula aula = AulaDAO.getInstancia().buscaAula(idAula);

                programacion = new Programacion(idProgramacion, horario, aula);
            }
        } catch (SQLException ex) {
            System.out.println("error buscar programacion:  " + ex.getMessage());
        } finally {
            cnn.close();
            ps.close();
        }
        return programacion;
    }

    public ArrayList<Programacion> mostrarProgramacion() throws SQLException {

        cnn = Conexion.getInstancia().miConexion();
        PreparedStatement ps = null;
        ArrayList<Programacion> lista = new ArrayList<Programacion>();
        ResultSet rs1 = null;
        try {
            ps = cnn.prepareStatement("SELECT * FROM programacion");
            rs1 = ps.executeQuery();

            while (rs1.next()) {
                String idProgramacion = rs1.getString("codigo");
                Programacion programacion = buscarProgramacion(idProgramacion);
                lista.add(programacion);
            }
        } catch (SQLException e) {
            System.out.println("Error mostrar programacion: " + e.getMessage());
        } finally {
            cnn.close();
            ps.close();
        }
        return lista;
    }

}
