
package LogicaNegocio;
import java.util.Date;

public class Estudiante extends Persona {
    private Date fechaNac;
    private Carrera carrera;

    public Estudiante(String nombre, String cedula, String telefono, String email, Date fechaNac, Carrera carrera) {
        super(nombre, cedula, telefono, email);
        this.fechaNac = fechaNac;
        this.carrera = carrera;
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
        
}
