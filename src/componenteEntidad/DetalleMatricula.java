package componenteEntidad;

import java.util.Objects;

public class DetalleMatricula {

    private Curso curso;

    public DetalleMatricula() {

    }

    public DetalleMatricula(Curso curso) {
        this.curso = curso;
    }

    public Curso getCurso() {
        return curso;
    }

    public void setCurso(Curso curso) {
        this.curso = curso;
    }

    @Override
    public int hashCode() {
        int hash = 3;
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
        final DetalleMatricula other = (DetalleMatricula) obj;
        if (!Objects.equals(this.curso, other.curso)) {
            return false;
        }
        return true;
    }

}
