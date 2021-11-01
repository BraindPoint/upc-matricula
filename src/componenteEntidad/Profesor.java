package componenteEntidad;

import java.util.Objects;

public class Profesor extends Persona {

    private String codigo;
    private String dni;

    public Profesor() {

    }

    public Profesor(String codigo, String dni, String apellidos, String nombres,
            String telefono, String direccion) {
        super(nombres, apellidos, telefono, direccion);
        this.codigo = codigo;
        this.dni = dni;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 17 * hash + Objects.hashCode(this.codigo);
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
        final Profesor other = (Profesor) obj;
        if (!Objects.equals(this.codigo, other.codigo)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return codigo + dni + super.toString();
    }

}
