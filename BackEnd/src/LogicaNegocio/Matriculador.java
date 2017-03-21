
package LogicaNegocio;


public class Matriculador extends Persona {

    private Usuario usuario;
    private Estudiante estudiante;

    public Matriculador(Usuario usuario, Estudiante estudiante, String nombre, String cedula, String telefono, String email) {
        super(nombre, cedula, telefono, email);
        this.usuario = usuario;
        this.estudiante = estudiante;
    }

    public Matriculador() {
        super(null, null, null, null);
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public Estudiante getEstudiante() {
        return estudiante;
    }

    public void setEstudiante(Estudiante estudiante) {
        this.estudiante = estudiante;
    }
    
    
    
}
