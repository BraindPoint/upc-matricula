package componenteEntidad;

import java.util.Date;
import java.util.Objects;

public class Horario {

    private String codigo;
    private String grado;
    private String turno;
    private Date fechaInicio;
    private String horaInicio;
    private String horaSalida;

    public Horario() {

    }

    public Horario(String codigo, String grado, String turno, Date fechaInicio,
            String horaInicio, String horaSalida) {
        this.codigo = codigo;
        this.grado = grado;
        this.turno = turno;
        this.fechaInicio = fechaInicio;
        this.horaInicio = horaInicio;
        this.horaSalida = horaSalida;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getGrado() {
        return grado;
    }

    public void setGrado(String grado) {
        this.grado = grado;
    }

    public String getTurno() {
        return turno;
    }

    public void setTurno(String turno) {
        this.turno = turno;
    }

    public Date getFecha() {
        return fechaInicio;
    }

    public void setFecha(Date fecha) {
        this.fechaInicio = fecha;
    }

    public String getHoraInicio() {
        return horaInicio;
    }

    public void setHoraInicio(String horaInicio) {
        this.horaInicio = horaInicio;
    }

    public String getHoraSalida() {
        return horaSalida;
    }

    public void setHoraSalida(String horaSalida) {
        this.horaSalida = horaSalida;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 41 * hash + Objects.hashCode(this.codigo);
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
        final Horario other = (Horario) obj;
        if (!Objects.equals(this.codigo, other.codigo)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return codigo + turno + fechaInicio + horaInicio;
    }

}
