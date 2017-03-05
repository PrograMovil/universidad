
package LogicaNegocio;

import AccesoDatos.Cursos;


public class Carrera {
    
    private String codigo;
    private String nombre;
    private String titulo;
    private Cursos cursos;

    public Carrera(String codigo, String nombre, String titulo, Cursos cursos) {
        this.codigo = codigo;
        this.nombre = nombre;
        this.titulo = titulo;
        this.cursos = cursos;
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

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }
    
    
}
