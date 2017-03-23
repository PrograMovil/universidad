
package LogicaNegocio;

import java.util.ArrayList;

public class Grupo {
    
    private int numero;
    private Horario horario;
    private Profesor profesor;
    private ArrayList<Estudiante> estudiantes;
    private Curso curso;

    public Grupo() {
    }

    public Grupo(int numero, Horario horario, Profesor profesor, ArrayList<Estudiante> estudiantes, Curso curso) {
        this.numero = numero;
        this.horario = horario;
        this.profesor = profesor;
        this.estudiantes = estudiantes;
        this.curso = curso;
    }

    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public Horario getHorario() {
        return horario;
    }

    public void setHorario(Horario horario) {
        this.horario = horario;
    }

    public Profesor getProfesor() {
        return profesor;
    }

    public void setProfesor(Profesor profesor) {
        this.profesor = profesor;
    }

    public ArrayList<Estudiante> getEstudiantes() {
        return estudiantes;
    }

    public void setEstudiantes(ArrayList<Estudiante> estudiantes) {
        this.estudiantes = estudiantes;
    }

    public Curso getCurso() {
        return curso;
    }

    public void setCurso(Curso curso) {
        this.curso = curso;
    }
    
    
}
