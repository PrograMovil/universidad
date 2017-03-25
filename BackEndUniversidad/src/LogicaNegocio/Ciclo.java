
package LogicaNegocio;

import java.util.Calendar;
import java.util.GregorianCalendar;


public class Ciclo {
    private int anio;
    private int numero;
    private Calendar fechaInicio;
    private Calendar fechaFinalizacion;

    public Ciclo(int anio, int numero, Calendar fechaInicio, Calendar fechaFinalizacion) {
        this.anio = anio;
        this.numero = numero;
        this.fechaInicio = fechaInicio;
        this.fechaFinalizacion = fechaFinalizacion;
    }
    
    public Ciclo() {
        this.anio = 0;
        this.numero = 0;
        this.fechaInicio = new GregorianCalendar(2000, 10, 11);
        this.fechaFinalizacion = new GregorianCalendar(2000, 10, 12);
    }
    

    public int getAnio() {
        return anio;
    }

    public void setAnio(int anio) {
        this.anio = anio;
    }

    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public Calendar getFechaInicio() {
        return fechaInicio;
    }

    public void setFechaInicio(Calendar fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public Calendar getFechaFinalizacion() {
        return fechaFinalizacion;
    }

    public void setFechaFinalizacion(Calendar fechaFinalizacion) {
        this.fechaFinalizacion = fechaFinalizacion;
    }

    
    
}
