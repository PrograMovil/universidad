
package Control;

import AccesoDatos.*;
import LogicaNegocio.*;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Control {
    Carreras carreras;
    Ciclos ciclos;
    Cursos cursos;
    Estudiantes estudiantes;
    Grupos grupos;
    Horarios horarios;
    Matriculadores matriculadores;
    Notas notas;
    Profesores profesores;
    Usuarios usuarios;

    

    public Control() {
        carreras=new Carreras();
        ciclos=new Ciclos();
        cursos=new Cursos();
        estudiantes=new Estudiantes();
        grupos=new Grupos();
        horarios=new Horarios();
        matriculadores=new Matriculadores();
        notas=new Notas();
        profesores=new Profesores();
        usuarios=new Usuarios();
    }
    
    
    
    public int verificaUsuario(String username, String clave){
        try {
            return usuarios.verificaUsuario(username, clave);
        } catch (Exception ex) {
            Logger.getLogger(Control.class.getName()).log(Level.SEVERE, null, ex);
        }
        return 0;
        
    }
    
    
    public ArrayList<Grupo> gruposDelProfesor(String cedula){
        try {
            grupos.gruposPorProfesor(cedula);
        } catch (Exception ex) {
            Logger.getLogger(Control.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    
    
    
    
    //<editor-fold defaultstate="collapsed" desc="Metodos agregar, Eliminar, modificar entities">
    public int addCarrera(Carrera ca){
        return this.carreras.agregar(ca);
    }
    
    public int deleteCarrera(Carrera ca){
        return this.carreras.eliminar(ca);
    }
    
    public int updateCarrera(Carrera ca){
        try {
            return this.carreras.actualizar(ca);
        } catch (SQLException ex) {
            System.err.println("Error al actualizar carrera");
        }
        return 0;
    }
    
    public Carrera getCarrera(String id) throws Exception{
        return this.carreras.obtener(id);
    }
    
    
    
    
    
    
    public int addCiclo(Ciclo ca){
        return this.ciclos.agregar(ca);
    }
    
    public int deleteCiclo(Ciclo ca){
        return this.ciclos.eliminar(ca);
    }
    
    public int updateCiclo(Ciclo ca){
        return this.ciclos.actualizar(ca);
    }
    
    public Ciclo getCiclo(int anio, int numero) throws Exception{
        return this.ciclos.obtener(anio,numero);
    }
    
    
    
    public int addCurso(Curso ca){
        return this.cursos.agregar(ca);
    }
    
    public int deleteCurso(Curso ca){
        return this.cursos.eliminar(ca);
    }
    
    public int updateCurso(Curso ca){
        try {
            return this.cursos.actualizar(ca);
        } catch (SQLException ex) {
            System.err.println("Error al actualizar curso");
        }
        return 0;
    }
    
    public Curso getCurso(String codigo) throws Exception{
        return this.cursos.obtener(codigo);
    }
    
    
    
    public int addEstudiante(Estudiante ca){
        return this.estudiantes.agregar(ca);
    }
    
    public int deleteEstudiante(Estudiante ca){
        return this.estudiantes.eliminar(ca);
    }
    
    public int updateEstudiante(Estudiante ca){
        return this.estudiantes.actualizar(ca);
    }
    
    public Estudiante getEstudiante(String codigo) throws Exception{
        return this.estudiantes.obtener(codigo);
    }
    
    
    
    public int addGrupo(Grupo ca){
        try {
            return this.grupos.agregar(ca);
        } catch (SQLException ex) {
            System.err.println("Error al agregar grupo");
        }
        return 0;
    }
    
    public int deleteGrupo(Grupo ca){
        try {
            return this.grupos.eliminar(ca);
        } catch (SQLException ex) {
            System.err.println("Error al eliminar grupo");
        }
        return 0;
    }
    
    public int updateGrupo(Grupo ca){
        try {
            return this.grupos.actualizar(ca);
        } catch (SQLException ex) {
            System.err.println("Error al actualizar grupo");
        }
        return 0;
    }
    
    public Grupo getGrupo(String codigo) throws Exception{
        return this.grupos.obtener(codigo);
    }
    
    
    
    
    public int addHorario(Horario ca){
        return this.horarios.agregar(ca);
    }
    
    public int deleteHorario(Horario ca){
        try {
            return this.horarios.eliminar(ca);
        } catch (SQLException ex) {
            System.err.println("Error al eliminar horario");
        }
        return 0;
    }
    
    public int updateHorario(Horario ca){
        try {
            return this.horarios.actualizar(ca);
        } catch (SQLException ex) {
            System.err.println("Error al actualizar horario");
        }
        return 0;
    }
    
    public Horario getHorario(String dias, Date horaInicial, Date horaFinal) throws Exception{
        return this.horarios.obtener(dias,horaInicial,horaFinal);
    }
    
    
    
    
    
    public int addMatriculador(Matriculador ca){
        return this.matriculadores.agregar(ca);
    }
    
    public int deleteMatriculador(Matriculador ca){
        return this.matriculadores.eliminar(ca);
    }
    
    public int updateMatriculador(Matriculador ca){
        return this.matriculadores.actualizar(ca);
    }
    
    public Matriculador getMatriculador(String codigo){
        try {
            return this.matriculadores.obtener(codigo);
        } catch (Exception ex) {
            System.err.println("Error al obtener Matriculador");
        }
        return null;
    }
    
    
    
    public int addNota(Nota ca){
        try {
            return this.notas.agregar(ca);
        } catch (SQLException ex) {
            System.err.println("Error al agregar Nota");
        }
        return 0;
    }
    
    public int deleteNota(Nota ca){
        try {
            return this.notas.eliminar(ca);
        } catch (SQLException ex) {
            System.err.println("Error al borrar nota");
        }
        return 0;
    }
    
    public int updateNota(Nota ca){
        try {
            return this.notas.actualizar(ca);
        } catch (SQLException ex) {
            System.err.println("Error al actualizar nota");
        }
        return 0;
    }
    
    public Nota getNota(float calificacion, String Estudiante_cedula, String Codigo_Curso) throws Exception{
        return this.notas.obtener(calificacion,Estudiante_cedula,Codigo_Curso);
    }
    
    
    public int addProfesor(Profesor ca){
        return this.profesores.agregar(ca);
    }
    
    public int deleteProfesor(Profesor ca){
        return this.profesores.eliminar(ca);
    }
    
    public int updateProfesor(Profesor ca){
        return this.profesores.actualizar(ca);
    }
    
    public Profesor getProfesor(String codigo){
        try {
            return this.profesores.obtener(codigo);
        } catch (Exception ex) {
            System.err.println("Error al obtener profesor");
        }
        return null;
    }
    
    
    
    public int addUsuario(Usuario ca){
        return this.usuarios.agregar(ca);
    }
    
    public int deleteUsuario(Usuario ca){
        return this.usuarios.eliminar(ca);
    }
    
    public int updateUsuario(Usuario ca){
        return this.usuarios.actualizar(ca);
    }
    
    public Usuario getUsuario(String codigo){
        try {
            return this.usuarios.obtener(codigo);
        } catch (Exception ex) {
            System.err.println("Error al obtener usuario");
        }
        return null;
    }
//</editor-fold>
    
    
    
    
}
