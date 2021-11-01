package componenteEntidad;

import java.util.Objects;

public class Apoderado extends Persona {

    private String codigo;
    private String estadoCivil;

    public Apoderado() {

    }

    public Apoderado(String codigo, String nombres, String apellidos,
            String telefono, String direccion, String estadoCivil) {
        super(nombres, apellidos, telefono, direccion);
        this.codigo = codigo;
        this.estadoCivil = estadoCivil;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

  

    public String getEstadoCivil() {
        return estadoCivil;
    }

    public void setEstadoCivil(String estadoCivil) {
        this.estadoCivil = estadoCivil;
    }

    @Override
    public String toString() {
        return codigo + super.toString() + estadoCivil;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 71 * hash + Objects.hashCode(this.codigo);
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
        final Apoderado other = (Apoderado) obj;
        if (!Objects.equals(this.codigo, other.codigo)) {
            return false;
        }
        return true;
    }

}
