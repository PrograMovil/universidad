
package LogicaNegocio;


public class Profesor extends Persona {
    
    private Usuario usuario;

    public Profesor() {
        super(null, null, null, null);
    }

    public Profesor(Usuario usuario, String nombre, String cedula, String telefono, String email) {
        super(nombre, cedula, telefono, email);
        this.usuario = usuario;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }
        
}
