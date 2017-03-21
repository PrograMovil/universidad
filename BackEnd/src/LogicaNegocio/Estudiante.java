
package LogicaNegocio;
import java.util.Date;

public class Estudiante extends Persona {
    private Date fechaNac;
    private Carrera carrera;
    private Usuario usuario;

    public Estudiante(String nombre, String cedula, String telefono, String email, Date fechaNac,Usuario usuario, Carrera carrera) {
        super(nombre, cedula, telefono, email);
        this.fechaNac = fechaNac;
        this.carrera = carrera;
        this.usuario=usuario;
    }

    public Estudiante() {
        super(null, null, null, null);
    }
    
    public Date getFechaNac() {
        return fechaNac;
    }

    public void setFechaNac(Date fechaNac) {
        this.fechaNac = fechaNac;
    }

    public Carrera getCarrera() {
        return carrera;
    }

    public void setCarrera(Carrera carrera) {
        this.carrera = carrera;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }
    
}
