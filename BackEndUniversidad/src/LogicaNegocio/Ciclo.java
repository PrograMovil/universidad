
package LogicaNegocio;

import java.util.Date;


public class Ciclo {
    private int anio;
    private int numero;
    private Date fechaInicio;
    private Date fechaFinalizacion;

    public Ciclo(int anio, int numero, Date fechaInicio, Date fechaFinalizacion) {
        this.anio = anio;
        this.numero = numero;
        this.fechaInicio = fechaInicio;
        this.fechaFinalizacion = fechaFinalizacion;
    }

    public Ciclo() {
        this.anio = 0;
        this.numero = 0;
        this.fechaInicio = new Date(2000, 10, 11);
        this.fechaFinalizacion = new Date(2000, 10, 12);
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

    public Date getFechaInicio() {
        return fechaInicio;
    }

    public void setFechaInicio(Date fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public Date getFechaFinalizacion() {
        return fechaFinalizacion;
    }

    public void setFechaFinalizacion(Date fechaFinalizacion) {
        this.fechaFinalizacion = fechaFinalizacion;
    }

    
    
    
}
