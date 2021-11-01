package componenteNegocio;

import componenteDatos.AulaDAO;
import componenteEntidad.Aula;
import java.sql.SQLException;
import java.util.ArrayList;

public class AulaCN {

    private static AulaCN instancia;

    public static AulaCN getInstancia() {
        if (instancia == null) {
            instancia = new AulaCN();
        }
        return instancia;
    }

    public void insertar(String codigo, int numeroAula, int capacidad) throws SQLException {
        AulaDAO.getInstancia().insertar(codigo, numeroAula, capacidad);
    }

    public Aula buscaAula(String codigo) throws SQLException {
        return AulaDAO.getInstancia().buscaAula(codigo);
    }

    public void actualizar(String codigo, int numeroAula, int capacidad) throws SQLException {
        AulaDAO.getInstancia().actualizar(codigo, numeroAula, capacidad);
    }

    public void eliminar(String codigo) throws SQLException {
        AulaDAO.getInstancia().eliminar(codigo);
    }

    public ArrayList<Aula> mostrarAulas() throws SQLException {
        return AulaDAO.getInstancia().mostrarAulas();
    }

    public ArrayList<Aula> buscarPorNumerodeAula(int numero) throws SQLException {
        return AulaDAO.getInstancia().buscarPorNumero(numero);
    }
}
