
package LogicaNegocio;

import java.io.Serializable;

public class Curso implements Serializable{
    private int id; //es lo que se manda en la query para pedir el grupo en la BD y se setea en la base
    private String codigo;
    private String nombre;
    private int creditos;
    private int horasSemanales;
    private Carrera carrera;
    private String nivel;
    private String ciclo;
    

    public Curso(int id, String codigo, String nombre, int creditos, int horasSemanales, Carrera carrera, String nivel) {
        this.id = id;
        this.codigo = codigo;
        this.nombre = nombre;
        this.creditos = creditos;
        this.horasSemanales = horasSemanales;
        this.carrera=carrera;
        this.nivel = nivel;
    }

    public Curso() {
        this.id = -1;
        this.codigo = "";
        this.nombre = "";
        this.creditos = 0;
        this.horasSemanales = 0;
        this.carrera=new Carrera();
        this.nivel = "";
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getCreditos() {
        return creditos;
    }

    public void setCreditos(int creditos) {
        this.creditos = creditos;
    }

    public int getHorasSemanales() {
        return horasSemanales;
    }

    public void setHorasSemanales(int horasSemanales) {
        this.horasSemanales = horasSemanales;
    }

    public Carrera getCarrera() {
        return carrera;
    }

    public void setCarrera(Carrera carrera) {
        this.carrera = carrera;
    }

    public String getNivel() {
        return nivel;
    }

    public void setNivel(String nivel) {
        this.nivel = nivel;
    }

    public String getCiclo() {
        return ciclo;
    }

    public void setCiclo(String ciclo) {
        this.ciclo = ciclo;
    }
    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    
}
