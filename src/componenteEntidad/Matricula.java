package componenteEntidad;

import java.util.Date;
import java.util.Objects;

public class Matricula {

    private String codigo;
    private Date fecha;
    private Programacion programacion;
    private Alumno alumno;
    private ListaDetalleMatricula LM;

    public Matricula() {
        LM = new ListaDetalleMatricula();
    }

    public Matricula(String codigo, Date fecha, Programacion programacion, Alumno alumno) {
        this.codigo = codigo;
        this.fecha = fecha;
        this.programacion = programacion;
        this.alumno = alumno;
        LM = new ListaDetalleMatricula();
    }

    public ListaDetalleMatricula getLM() {
        return LM;
    }

    public void setLM(ListaDetalleMatricula LM) {
        this.LM = LM;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public Programacion getProgramacion() {
        return programacion;
    }

    public void setProgramacion(Programacion programacion) {
        this.programacion = programacion;
    }

    public Alumno getAlumno() {
        return alumno;
    }

    public void setAlumno(Alumno alumno) {
        this.alumno = alumno;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 37 * hash + Objects.hashCode(this.codigo);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Matricula other = (Matricula) obj;
        if (!Objects.equals(this.codigo, other.codigo)) {
            return false;
        }
        return true;
    }

    public void registrarDetalleMatricula(Curso curso) {
        DetalleMatricula detalleMatricula = new DetalleMatricula(curso);
        LM.agregar(detalleMatricula);
    }

    public Object[][] devuelveDetalleMatricula() {
        return LM.devuelvedatos();
    }

    @Override
    public String toString() {
        return codigo + fecha + programacion + alumno.getNombres();
    }

}
