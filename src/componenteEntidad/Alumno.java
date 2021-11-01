package componenteEntidad;

import java.util.Date;
import java.util.Objects;

public class Alumno extends Persona {

    private String codigo;
    private Apoderado apoderado;
    private String sexo;
    private Date fechaNacimiento;

    public Alumno() {

    }

    public Alumno(String codigo, Apoderado apoderado, String sexo, String nombres,
            String apellidos, String telefono, String direccion, Date fechaNacimiento) {
        super(nombres, apellidos, telefono, direccion);
        this.codigo = codigo;
        this.apoderado = apoderado;
        this.sexo = sexo;
        this.fechaNacimiento = fechaNacimiento;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public Apoderado getApoderado() {
        return apoderado;
    }

    public void setApoderado(Apoderado apoderado) {
        this.apoderado = apoderado;
    }

    public String getSexo() {
        return sexo;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo;
    }

    public Date getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(Date fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    @Override
    public String toString() {
        return codigo + apoderado + sexo + super.toString() + fechaNacimiento;
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
        final Alumno other = (Alumno) obj;
        if (!Objects.equals(this.codigo, other.codigo)) {
            return false;
        }
        return true;
    }

}
