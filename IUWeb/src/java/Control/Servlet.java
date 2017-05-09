
package Control;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import Control.Control;
import LogicaNegocio.Administrador;
import LogicaNegocio.Carrera;
import LogicaNegocio.Ciclo;
import LogicaNegocio.Curso;
import LogicaNegocio.Estudiante;
import LogicaNegocio.Grupo;
import LogicaNegocio.Horario;
import LogicaNegocio.Matriculador;
import LogicaNegocio.Nota;
import LogicaNegocio.Profesor;
import LogicaNegocio.Usuario;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.http.HttpSession;

public class Servlet extends HttpServlet {
        
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        
//        Define cual es la accion que se va a realizar
        String accion = request.getParameter("action");
        String router = request.getParameter("router");
                
//        Listas de Objetos
        ArrayList<Carrera> carreras = null;
        ArrayList<Carrera> allCarreras = null;
        ArrayList<Profesor> profesores = null;
        ArrayList<Matriculador> matriculadores = null;
        ArrayList<Administrador> administradores = null;
        ArrayList<Estudiante> estudiantes = null;
        ArrayList<Estudiante> listaEstudiantesDelGrupo = null;
        ArrayList<Curso> cursos = null;
        Curso cursoCurrent = null;
        ArrayList<Grupo> grupos = null;
        ArrayList<Profesor> allProfesores = null;
        ArrayList<Ciclo> ciclos = null;
        Estudiante estudianteCurrent = null;
        Carrera carreraEstudianteCurrent = null;
        ArrayList<Curso> cursosCarrera = null;
        ArrayList<Grupo> gruposCurso = null;
        ArrayList<Grupo> gruposMatriculados = null;
        ArrayList<Grupo> gruposDelProfe = null;
        ArrayList<Grupo> gruposDelEstudiante = null;
        Ciclo cicloDefault = null;;
        Profesor profeCurrent = null;
//        Grupo grupoCurrent = null;
        
        Control ctrl = new Control();
        HttpSession session = request.getSession();
        
        carreras = ctrl.obtenerTodasCarreras();
        profesores = ctrl.obtenerTodosLosProfesores();
        matriculadores = ctrl.obtenerTodosLosMatriculadores();
        administradores = ctrl.obtenerTodosLosAdministradores();
        estudiantes = ctrl.obtenerTodosLosEstudiantes();
        cursos = ctrl.obtenerTodosLosCursos();
        cicloDefault = ctrl.obtenerCicloActivo();
//        Router y rutas
        try {
            if(router != null){
                switch(router){
                    case "adminDash": {
                        carreras = ctrl.obtenerTodasCarreras();
                        session.setAttribute("allCarreras", carreras);
                        response.sendRedirect("adminDash.jsp");
                    }
                    break;
                    case "profesorDash": {
//                        gruposDelProfe = ctrl.gruposDelProfesor(profeCurrent.getCedula());
//                        session.setAttribute("gruposDelProfe", gruposDelProfe);
                        response.sendRedirect("profesorDash.jsp");
                    }
                    break;
                    case "estudianteDash": {
                        response.sendRedirect("estudianteDash.jsp");
                    }
                    break;
                    case "matriculadorDash": {
                        response.sendRedirect("matriculadorDash.jsp");
                    }
                    break;
                    case "adminCursos": {
                        allCarreras = ctrl.obtenerTodasCarreras();
                        session.setAttribute("allCarreras", allCarreras);
                        cursos = ctrl.obtenerTodosLosCursos();
                        session.setAttribute("cursos", cursos);
                        response.sendRedirect("adminCursos.jsp");
                    }
                    break;
                    case "adminProfesores": {
                        profesores = ctrl.obtenerTodosLosProfesores();
                        session.setAttribute("profesores", profesores);
                        response.sendRedirect("adminProfesores.jsp");
                    }
                    break;
                    case "adminMatriculadores": {
                        matriculadores = ctrl.obtenerTodosLosMatriculadores();
                        session.setAttribute("matriculadores", matriculadores);
                        response.sendRedirect("adminMatriculadores.jsp");
                    }
                    break;
                    case "adminAdministradores": {
                        administradores = ctrl.obtenerTodosLosAdministradores();
                        session.setAttribute("administradores", administradores);
                        response.sendRedirect("adminAdministradores.jsp");
                    }
                    break;
                    case "adminEstudiantes": {
                        allCarreras = ctrl.obtenerTodasCarreras();
                        session.setAttribute("allCarreras", allCarreras);
                        estudiantes = ctrl.obtenerTodosLosEstudiantes();
                        session.setAttribute("estudiantes", estudiantes);
                        response.sendRedirect("adminEstudiantes.jsp");
                    }
                    break;
                    case "adminGrupos": {
                        String codigo = request.getParameter("idCurso");
                        cursoCurrent = ctrl.getCurso(codigo);
                        session.setAttribute("cursoCurrent", cursoCurrent);
                        allProfesores = ctrl.obtenerTodosLosProfesores();
                        session.setAttribute("allProfesores", allProfesores);
                        grupos = ctrl.gruposPorCurso(cursoCurrent);
                        session.setAttribute("grupos", grupos);
                        response.sendRedirect("adminGrupos.jsp");
                    }
                    break;
                    case "adminMatricula": {
                        String idEstudiante = request.getParameter("idEstudiante");
                        estudianteCurrent = ctrl.getEstudiante(idEstudiante);
                        session.setAttribute("estudianteCurrent", estudianteCurrent);
                        carreraEstudianteCurrent = estudianteCurrent.getCarrera();
                        session.setAttribute("carreraEstudianteCurrent", carreraEstudianteCurrent);
                        cursosCarrera = ctrl.getCursoPorCarrera(carreraEstudianteCurrent);
                        session.setAttribute("cursosCarrera", cursosCarrera);
                        ArrayList<ArrayList<Grupo>> listaGrupos = new ArrayList();
                        for(Curso curso : cursosCarrera ){
                            gruposCurso = ctrl.gruposPorCurso(curso);
                            listaGrupos.add(gruposCurso);
                        }
                        session.setAttribute("listaGrupos", listaGrupos);
                        cicloDefault = ctrl.obtenerCicloActivo();
                        session.setAttribute("cicloDefault", cicloDefault);
                        gruposMatriculados = ctrl.obtenerGruposDeEstudiante(estudianteCurrent);
                        session.setAttribute("gruposMatriculados", gruposMatriculados);                            
                        response.sendRedirect("adminMatricula.jsp");
                    }
                    break;
                    case "matriculadorMatricula": {
                        String idEstudiante = request.getParameter("idEstudiante");
                        estudianteCurrent = ctrl.getEstudiante(idEstudiante);
                        session.setAttribute("estudianteCurrent", estudianteCurrent);
                        carreraEstudianteCurrent = estudianteCurrent.getCarrera();
                        session.setAttribute("carreraEstudianteCurrent", carreraEstudianteCurrent);
                        cursosCarrera = ctrl.getCursoPorCarrera(carreraEstudianteCurrent);
                        session.setAttribute("cursosCarrera", cursosCarrera);
                        ArrayList<ArrayList<Grupo>> listaGrupos = new ArrayList();
                        for(Curso curso : cursosCarrera ){
                            gruposCurso = ctrl.gruposPorCurso(curso);
                            listaGrupos.add(gruposCurso);
                        }
                        session.setAttribute("listaGrupos", listaGrupos);
                        cicloDefault = ctrl.obtenerCicloActivo();
                        session.setAttribute("cicloDefault", cicloDefault);
                        gruposMatriculados = ctrl.obtenerGruposDeEstudiante(estudianteCurrent);
                        session.setAttribute("gruposMatriculados", gruposMatriculados);                            
                        response.sendRedirect("matriculadorMatricula.jsp");
                    }
                    break;
                    case "cicloLectivo": {
                        session.setAttribute("exito", "");
                        session.setAttribute("errores", "");
                        ciclos = ctrl.obtenerTodosLosCiclos();
                        session.setAttribute("ciclos", ciclos);
                        response.sendRedirect("adminCiclos.jsp");
                    }
                    break;
                    case "notas": {
                        String idGrupo= request.getParameter("idGrupo");
                        String codigoCurso = request.getParameter("codigoCurso");
                        this.printHTML("codigo curso: "+codigoCurso+", id grupo: "+idGrupo, response);
                        Grupo gru = ctrl.getGrupo(Integer.parseInt(idGrupo));
                        listaEstudiantesDelGrupo = ctrl.obtenerEstudiantesDeGrupo(gru);
                        session.setAttribute("listaEstudiantesDelGrupo", listaEstudiantesDelGrupo);
                        session.setAttribute("grupoCurrent", gru);
                        response.sendRedirect("profeNotasEstudiantes.jsp");
                    }
                    break;
                    case "expediente": {
                        String cedula= request.getParameter("cedulaEstudiante");
                        Estudiante est = ctrl.getEstudiante(cedula);
                        gruposDelEstudiante = ctrl.obtenerGruposDeEstudiante(est);
                        session.setAttribute("gruposDelEstudiante", gruposDelEstudiante);                        
                        session.setAttribute("estudianteCurrent", est);
                        response.sendRedirect("adminExpediente.jsp");
                    }
                    break;
                }
            }
        } catch (Exception ex) {
                    Logger.getLogger(Servlet.class.getName()).log(Level.SEVERE, null, ex);
                }
//        Acciones
        if(accion != null){
            try{
                switch (accion) {

                    case "Ingresar": {
                        String id = request.getParameter("id");
                        String pass = request.getParameter("password");
                        int tipoUsuario = ctrl.verificaUsuario(id, pass);
                        if(tipoUsuario != 0){
                            session.setAttribute("userId", id);
                            switch(tipoUsuario){ 
                                case 1: //ADMINISTRADOR                                
                                    System.out.println("Es administrador");                                
                                    session.setAttribute("carreras", carreras); //al inicio debe pedir los datos del primer dash
                                    session.setAttribute("tipoUsuario", tipoUsuario);
                                    response.sendRedirect("adminDash.jsp");
                                    break;
                                case 2: //MATRICULADOR
                                    System.out.println("Es matriculador");
                                    allCarreras = ctrl.obtenerTodasCarreras();
                                    session.setAttribute("allCarreras", allCarreras);
                                    estudiantes = ctrl.obtenerTodosLosEstudiantes();
                                    session.setAttribute("estudiantes", estudiantes);
                                    session.setAttribute("tipoUsuario", tipoUsuario);
                                    response.sendRedirect("matriculadorDash.jsp");
                                    break;
                                case 3: //PROFESOR
                                    System.out.println("Es profesor");
                                    profeCurrent = ctrl.getProfesor(id);
                                    gruposDelProfe = ctrl.gruposDelProfesor(id);
                                    session.setAttribute("gruposDelProfe", gruposDelProfe);
                                    session.setAttribute("profeCurrent", profeCurrent);
                                    session.setAttribute("tipoUsuario", tipoUsuario);
                                    response.sendRedirect("profesorDash.jsp");
                                    break;
                                case 4: //ESTUDIANTE
                                    System.out.println("Es estudiante");
                                    Estudiante est = ctrl.getEstudiante(id);
                                    gruposDelEstudiante = ctrl.obtenerGruposDeEstudiante(est);
                                    session.setAttribute("gruposDelEstudiante", gruposDelEstudiante);                        
                                    session.setAttribute("tipoUsuario", tipoUsuario);
                                    session.setAttribute("estudianteCurrent", est);
                                    response.sendRedirect("estudianteDash.jsp");
                                    break;
                            }
                        }else{
                            String errores = "Usuario o Contraseña incorrectos!";
                            session.setAttribute("errores", errores);
                            response.sendRedirect("login.jsp");
                        }
                    }
                    break;
                    case "Salir": {
//                        Eliminar datos de la sesion
                        session.removeAttribute("errores");
                        session.removeAttribute("userId");
                        session.removeAttribute("carreras");
                        session.removeAttribute("allCarreras");
                        session.removeAttribute("cursos");
                        session.removeAttribute("profesores");
                        session.removeAttribute("matriculadores");
                        session.removeAttribute("estudiantes");
                        session.removeAttribute("cursoCurrent");
                        session.removeAttribute("grupos");
                        session.removeAttribute("gruposMatriculados");
                        session.removeAttribute("ciclos");
                        session.removeAttribute("estudianteCurrent");
                        session.removeAttribute("carreraEstudianteCurrent");
                        session.removeAttribute("listaGrupos");
                        session.removeAttribute("cicloDefault");
                        session.removeAttribute("tipoUsuario");
                        session.removeAttribute("gruposDelEstudiante");
                        session.removeAttribute("estudianteCurrent");
                        session.removeAttribute("administradores");
                        session.invalidate();
                        response.sendRedirect("login.jsp");
                    }
                    break;
                    case "AgregarCarrera": {
                        String codigo = request.getParameter("codigo");
                        String nombre = request.getParameter("nombre");
                        String titulo = request.getParameter("titulo");
                        Carrera ca = new Carrera(codigo,nombre,titulo);
                        if(ctrl.addCarrera(ca) == 1){
                            carreras = ctrl.obtenerTodasCarreras();
                            session.setAttribute("carreras", carreras);
                            session.setAttribute("errores", "");
                            response.sendRedirect("adminDash.jsp");
                        }else{
                            String errores = "ERROR: Carrera NO Agregada!";
                            session.setAttribute("errores", errores);
                            response.sendRedirect("adminDash.jsp");
                        }
                    }
                    break;
                    case "BuscarCarrera": {
                        String codigo = request.getParameter("codigo");
                        String nombre = request.getParameter("nombre");
                        if(codigo != "" && nombre == ""){
                            Carrera ca;
                            if((ca = ctrl.getCarrera(codigo)) == null){
                                carreras.clear();
                                session.setAttribute("carreras", carreras);
                                response.sendRedirect("adminDash.jsp");
                            }else{
                                carreras.clear();
                                carreras.add(ca);                        
                                session.setAttribute("carreras", carreras);
                                response.sendRedirect("adminDash.jsp");
                            }

                        }else if(nombre != "" && codigo == ""){
                            carreras.clear();
                            carreras = ctrl.obtenerCarreraPorNombre(nombre);
                            session.setAttribute("carreras", carreras);
                            response.sendRedirect("adminDash.jsp");  
                        }else if(nombre == "" && codigo == ""){
                            carreras.clear();
                            carreras = ctrl.obtenerTodasCarreras();
                            session.setAttribute("carreras", carreras);
                            response.sendRedirect("adminDash.jsp");
                        }
                    }
                    break;
                    case "EditarCarrera":{
                        String codigo = request.getParameter("codigo");
                        String nombre = request.getParameter("nombre");
                        String titulo = request.getParameter("titulo");
                        Carrera ca = new Carrera(codigo,nombre,titulo);
                        System.out.println(ca.toString());
                        if(ctrl.updateCarrera(ca) == 1){
                            carreras.clear();
                            carreras = ctrl.obtenerTodasCarreras();
                            session.setAttribute("carreras", carreras);
                            session.setAttribute("errores", "");
                            response.sendRedirect("adminDash.jsp");
                        }else{
                            String errores = "ERROR: Carrera NO Actualizada!";
                            session.setAttribute("errores", errores);
                            response.sendRedirect("adminDash.jsp");
                        }
                    }
                    break;
                    case "AgregarProfesor": {
                        String cedula = request.getParameter("cedula");
                        String nombre = request.getParameter("nombre");
                        String telefono = request.getParameter("telefono");
                        String email = request.getParameter("email");
                        String password = request.getParameter("password");
                        Usuario user = new Usuario(cedula,password,3);
                        Profesor profe = new Profesor(user,nombre,cedula,telefono,email);
                        if(ctrl.addProfesor(profe) == 1){
                            profesores = ctrl.obtenerTodosLosProfesores();
                            session.setAttribute("profesores", profesores);
                            session.setAttribute("errores", "");
                            response.sendRedirect("adminProfesores.jsp");
                        }else{
                            String errores = "ERROR: Profesor NO Agregado!";
                            session.setAttribute("errores", errores);
                            response.sendRedirect("adminProfesores.jsp");
                        }
                    }
                    break;
                    case "BuscarProfesor": {
                        String cedula = request.getParameter("cedula");
                        String nombre = request.getParameter("nombre");
                        if(cedula != "" && nombre == ""){
                            Profesor pro;
                            if((pro = ctrl.getProfesor(cedula)) == null){
                                profesores.clear();
                                session.setAttribute("profesores", profesores);
                                response.sendRedirect("adminProfesores.jsp");
                            }else{
                                profesores.clear();
                                profesores.add(pro);                        
                                session.setAttribute("profesores", profesores);
                                response.sendRedirect("adminProfesores.jsp");
                            }

                        }else if(nombre != "" && cedula == ""){
                            profesores.clear();
                            profesores = ctrl.obtenerProfesoresPorNombre(nombre);
                            session.setAttribute("profesores", profesores);
                            response.sendRedirect("adminProfesores.jsp");  
                        }else if(nombre == "" && cedula == ""){
                            profesores.clear();
                            profesores = ctrl.obtenerTodosLosProfesores();
                            session.setAttribute("profesores", profesores);
                            response.sendRedirect("adminProfesores.jsp");
                        }
                    }
                    break;
                    case "EditarProfesor":{
                        String cedula = request.getParameter("cedula");
                        String nombre = request.getParameter("nombre");
                        String telefono = request.getParameter("telefono");
                        String email = request.getParameter("email");
                        String password = request.getParameter("password");
                        Usuario user = new Usuario(cedula,password,3);
                        Profesor profe = new Profesor(user,nombre,cedula,telefono,email);
                        System.out.println(profe.toString());
                        if(ctrl.updateProfesor(profe) == 1){
                            profesores.clear();
                            profesores = ctrl.obtenerTodosLosProfesores();
                            session.setAttribute("profesores", profesores);
                            session.setAttribute("errores", "");
                            response.sendRedirect("adminProfesores.jsp");
                        }else{
                            String errores = "ERROR: Profesor NO Actualizado!";
                            session.setAttribute("errores", errores);
                            response.sendRedirect("adminProfesores.jsp");
                        }
                    }
                    break;
                    case "AgregarMatriculador": {
                        String cedula = request.getParameter("cedula");
                        String nombre = request.getParameter("nombre");
                        String telefono = request.getParameter("telefono");
                        String email = request.getParameter("email");
                        String password = request.getParameter("password");
                        Usuario user = new Usuario(cedula,password,3);
                        Matriculador ma = new Matriculador(user,nombre,cedula,telefono,email);
                        if(ctrl.addMatriculador(ma) == 1){
                            matriculadores = ctrl.obtenerTodosLosMatriculadores();
                            session.setAttribute("matriculadores", matriculadores);
                            session.setAttribute("errores", "");
                            response.sendRedirect("adminMatriculadores.jsp");
                        }else{
                            String errores = "ERROR: Matriculador NO Agregado!";
                            session.setAttribute("errores", errores);
                            response.sendRedirect("adminMatriculadores.jsp");
                        }
                    }
                    break;
                    case "BuscarMatriculador": {
                        String cedula = request.getParameter("cedula");
                        String nombre = request.getParameter("nombre");
                        if(cedula != "" && nombre == ""){
                            Matriculador ma;
                            if((ma = ctrl.getMatriculador(cedula)) == null){
                                matriculadores.clear();
                                session.setAttribute("matriculadores", matriculadores);
                                response.sendRedirect("adminMatriculadores.jsp");
                            }else{
                                matriculadores.clear();
                                matriculadores.add(ma);                        
                                session.setAttribute("matriculadores", matriculadores);
                                response.sendRedirect("adminMatriculadores.jsp");
                            }

                        }else if(nombre != "" && cedula == ""){
                            matriculadores.clear();
                            matriculadores = ctrl.obtenerMatriculadoresPorNombre(nombre);
                            session.setAttribute("matriculadores", matriculadores);
                            response.sendRedirect("adminMatriculadores.jsp");  
                        }else if(nombre == "" && cedula == ""){
                            matriculadores.clear();
                            matriculadores = ctrl.obtenerTodosLosMatriculadores();
                            session.setAttribute("matriculadores", matriculadores);
                            response.sendRedirect("adminMatriculadores.jsp");
                        }
                    }
                    break;
                    case "EditarMatriculador":{
                        String cedula = request.getParameter("cedula");
                        String nombre = request.getParameter("nombre");
                        String telefono = request.getParameter("telefono");
                        String email = request.getParameter("email");
                        String password = request.getParameter("password");
                        Usuario user = new Usuario(cedula,password,3);
                        Matriculador ma = new Matriculador(user,nombre,cedula,telefono,email);
                        System.out.println(ma.toString());
                        if(ctrl.updateMatriculador(ma) == 1){
                            matriculadores.clear();
                            matriculadores = ctrl.obtenerTodosLosMatriculadores();
                            session.setAttribute("matriculadores", matriculadores);
                            session.setAttribute("errores", "");
                            response.sendRedirect("adminMatriculadores.jsp");
                        }else{
                            String errores = "ERROR: Matriculador NO Actualizado!";
                            session.setAttribute("errores", errores);
                            response.sendRedirect("adminMatriculadores.jsp");
                        }
                    }
                    break;
                    case "AgregarAdministrador": {
                        String cedula = request.getParameter("cedula");
                        String nombre = request.getParameter("nombre");
                        String telefono = request.getParameter("telefono");
                        String email = request.getParameter("email");
                        String password = request.getParameter("password");
                        Usuario user = new Usuario(cedula,password,3);
                        Administrador ad = new Administrador(user,nombre,cedula,telefono,email);
                        if(ctrl.addAdministrador(ad) == 1){
                            administradores = ctrl.obtenerTodosLosAdministradores();
                            session.setAttribute("administradores", administradores);
                            session.setAttribute("errores", "");
                            response.sendRedirect("adminAdministradores.jsp");
                        }else{
                            String errores = "ERROR: Administrador NO Agregado!";
                            session.setAttribute("errores", errores);
                            response.sendRedirect("adminAdministradores.jsp");
                        }
                    }
                    break;
                    case "BuscarAdministrador": {
                        String cedula = request.getParameter("cedula");
                        String nombre = request.getParameter("nombre");
                        if(cedula != "" && nombre == ""){
                            Administrador ad;
                            if((ad = ctrl.getAdministrador(cedula)) == null){
                                administradores.clear();
                                session.setAttribute("administradores", administradores);
                                response.sendRedirect("adminAdministradores.jsp");
                            }else{
                                administradores.clear();
                                administradores.add(ad);                        
                                session.setAttribute("administradores", administradores);
                                response.sendRedirect("adminAdministradores.jsp");
                            }

                        }else if(nombre != "" && cedula == ""){
                            administradores.clear();
                            administradores = ctrl.obtenerAdministradoresPorNombre(nombre);
                            session.setAttribute("administradores", administradores);
                            response.sendRedirect("adminAdministradores.jsp");  
                        }else if(nombre == "" && cedula == ""){
                            administradores.clear();
                            administradores = ctrl.obtenerTodosLosAdministradores();
                            session.setAttribute("administradores", administradores);
                            response.sendRedirect("adminAdministradores.jsp");
                        }
                    }
                    break;
                    case "EditarAdministrador":{
                        String cedula = request.getParameter("cedula");
                        String nombre = request.getParameter("nombre");
                        String telefono = request.getParameter("telefono");
                        String email = request.getParameter("email");
                        String password = request.getParameter("password");
                        Usuario user = new Usuario(cedula,password,3);
                        Administrador ad = new Administrador(user,nombre,cedula,telefono,email);
                        System.out.println(ad.toString());
                        if(ctrl.updateAdministrador(ad) == 1){
                            administradores.clear();
                            administradores = ctrl.obtenerTodosLosAdministradores();
                            session.setAttribute("administradores", administradores);
                            session.setAttribute("errores", "");
                            response.sendRedirect("adminAdministradores.jsp");
                        }else{
                            String errores = "ERROR: Administrador NO Actualizado!";
                            session.setAttribute("errores", errores);
                            response.sendRedirect("adminAdministradores.jsp");
                        }
                    }
                    break;
                    case "AgregarEstudiante": {
                        String cedula = request.getParameter("cedula");
                        String fechaNacString = request.getParameter("fechaNac");
                        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
                        Calendar cal = Calendar.getInstance();
                        cal.setTime(sdf.parse(fechaNacString));
                        String nombre = request.getParameter("nombre");
                        String idCarrera = request.getParameter("idCarrera");
                        String telefono = request.getParameter("telefono");
                        String email = request.getParameter("email");
                        String password = request.getParameter("password");
                        Usuario user = new Usuario(cedula,password,4);
                        Carrera carrera = ctrl.getCarrera(idCarrera);
                        Estudiante es = new Estudiante(cal,carrera,user,nombre,cedula,telefono,email);
                        if(ctrl.addEstudiante(es) == 1){
                            estudiantes = ctrl.obtenerTodosLosEstudiantes();
                            session.setAttribute("estudiantes", estudiantes);
                            session.setAttribute("errores", "");
                            response.sendRedirect("adminEstudiantes.jsp");
                        }else{
                            String errores = "ERROR: Estudiante NO Agregado!";
                            session.setAttribute("errores", errores);
                            response.sendRedirect("adminEstudiantes.jsp");
                        }
                    }
                    break;
                    case "EditarEstudiante": {
                        String cedula = request.getParameter("cedula");
                        String fechaNacString = request.getParameter("fechaNac");
                        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
                        Calendar cal = Calendar.getInstance();
                        cal.setTime(sdf.parse(fechaNacString));
                        String nombre = request.getParameter("nombre");
                        String idCarrera = request.getParameter("idCarrera");
                        String telefono = request.getParameter("telefono");
                        String email = request.getParameter("email");
                        String password = request.getParameter("password");
                        Usuario user = new Usuario(cedula,password,4);
                        Carrera carrera = ctrl.getCarrera(idCarrera);
                        Estudiante es = new Estudiante(cal,carrera,user,nombre,cedula,telefono,email);
                        if(ctrl.updateEstudiante(es) == 1){
                            estudiantes = ctrl.obtenerTodosLosEstudiantes();
                            session.setAttribute("estudiantes", estudiantes);
                            session.setAttribute("errores", "");
                            response.sendRedirect("adminEstudiantes.jsp");
                        }else{
                            String errores = "ERROR: Estudiante NO Actualizado!";
                            session.setAttribute("errores", errores);
                            response.sendRedirect("adminEstudiantes.jsp");
                        }
                    }
                    break;
                    case "BuscarEstudiante": {
                        String cedula = request.getParameter("cedula");
                        String nombre = request.getParameter("nombre");
                        String idCarrera = request.getParameter("idCarrera");
                        if(cedula != "" && nombre == "" && idCarrera == ""){
                            Estudiante es;
                            if((es = ctrl.getEstudiante(cedula)) == null){
                                estudiantes.clear();
                                session.setAttribute("estudiantes", estudiantes);
                                response.sendRedirect("adminEstudiantes.jsp");
                            }else{
                                estudiantes.clear();
                                estudiantes.add(es);                        
                                session.setAttribute("estudiantes", estudiantes);
                                response.sendRedirect("adminEstudiantes.jsp");
                            }
                        }else if(nombre != "" && cedula == "" && idCarrera == ""){
                            estudiantes.clear();
                            estudiantes = ctrl.obtenerEstudiantePorNombre(nombre);
                            session.setAttribute("estudiantes", estudiantes);
                            response.sendRedirect("adminEstudiantes.jsp");  
                        }else if(idCarrera != "" && nombre == "" && cedula == ""){
                            estudiantes.clear();
                            Carrera ca = ctrl.getCarrera(idCarrera);
                            estudiantes = ctrl.obtenerEstudiantesPorCarrera(ca);
                            session.setAttribute("estudiantes", estudiantes);
                            response.sendRedirect("adminEstudiantes.jsp");  
                        }else if(nombre == "" && cedula == "" && idCarrera == ""){
                            estudiantes.clear();
                            estudiantes = ctrl.obtenerTodosLosEstudiantes();
                            session.setAttribute("estudiantes", estudiantes);
                            response.sendRedirect("adminEstudiantes.jsp");
                        }
                    }
                    break;
                    case "BuscarEstudianteEnMatriculador": {
                        String cedula = request.getParameter("cedula");
                        String nombre = request.getParameter("nombre");
                        String idCarrera = request.getParameter("idCarrera");
                        if(cedula != "" && nombre == "" && idCarrera == ""){
                            Estudiante es;
                            if((es = ctrl.getEstudiante(cedula)) == null){
                                estudiantes.clear();
                                session.setAttribute("estudiantes", estudiantes);
                                response.sendRedirect("matriculadorDash.jsp");
                            }else{
                                estudiantes.clear();
                                estudiantes.add(es);                        
                                session.setAttribute("estudiantes", estudiantes);
                                response.sendRedirect("matriculadorDash.jsp");
                            }
                        }else if(nombre != "" && cedula == "" && idCarrera == ""){
                            estudiantes.clear();
                            estudiantes = ctrl.obtenerEstudiantePorNombre(nombre);
                            session.setAttribute("estudiantes", estudiantes);
                            response.sendRedirect("matriculadorDash.jsp");  
                        }else if(idCarrera != "" && nombre == "" && cedula == ""){
                            estudiantes.clear();
                            Carrera ca = ctrl.getCarrera(idCarrera);
                            estudiantes = ctrl.obtenerEstudiantesPorCarrera(ca);
                            session.setAttribute("estudiantes", estudiantes);
                            response.sendRedirect("matriculadorDash.jsp");  
                        }else if(nombre == "" && cedula == "" && idCarrera == ""){
                            estudiantes.clear();
                            estudiantes = ctrl.obtenerTodosLosEstudiantes();
                            session.setAttribute("estudiantes", estudiantes);
                            response.sendRedirect("matriculadorDash.jsp");
                        }
                    }
                    break;
                    case "AgregarCurso": {
                        String codigo = request.getParameter("codigo");
                        String nombre = request.getParameter("nombre");
                        String creditos = request.getParameter("creditos");
                        String horasSemanales = request.getParameter("horasSemanales");
                        String idCarrera = request.getParameter("idCarrera");
                        String nivel = request.getParameter("nivel");
                        String ciclo = request.getParameter("ciclo");
                        Carrera ca = ctrl.getCarrera(idCarrera);
                        Curso cu = new Curso(codigo,nombre,Integer.parseInt(creditos),Integer.parseInt(horasSemanales),ca,nivel);
                        cu.setCiclo(ciclo);
                        if(ctrl.addCurso(cu) == 1){
                            cursos = ctrl.obtenerTodosLosCursos();
                            session.setAttribute("cursos", cursos);
                            session.setAttribute("errores", "");
                            response.sendRedirect("adminCursos.jsp");
                        }else{
                            String errores = "ERROR: Curso NO Agregado!";
                            session.setAttribute("errores", errores);
                            response.sendRedirect("adminCursos.jsp");
                        }
                    }
                    break;
                    case "EditarCurso": {
                        String codigo = request.getParameter("codigo");
                        String nombre = request.getParameter("nombre");
                        String creditos = request.getParameter("creditos");
                        String horasSemanales = request.getParameter("horasSemanales");
                        String idCarrera = request.getParameter("idCarrera");
                        String nivel = request.getParameter("nivel");
                        String ciclo = request.getParameter("ciclo");
                        Carrera ca = ctrl.getCarrera(idCarrera);
                        Curso cu = new Curso(codigo,nombre,Integer.parseInt(creditos),Integer.parseInt(horasSemanales),ca,nivel);
                        cu.setCiclo(ciclo);
                        if(ctrl.updateCurso(cu) == 1){
                            cursos = ctrl.obtenerTodosLosCursos();
                            session.setAttribute("cursos", cursos);
                            session.setAttribute("errores", "");
                            response.sendRedirect("adminCursos.jsp");
                        }else{
                            String errores = "ERROR: Curso NO Actualizado!";
                            session.setAttribute("errores", errores);
                            response.sendRedirect("adminCursos.jsp");
                        }
                    }
                    break;
                    case "BuscarCurso": {
                        String codigo = request.getParameter("codigo");
                        String nombre = request.getParameter("nombre");
                        String idCarrera = request.getParameter("idCarrera");
                        if(codigo != "" && nombre == "" && idCarrera == ""){
                            Curso cu;
                            if((cu = ctrl.getCurso(codigo)) == null){
                                cursos.clear();
                                session.setAttribute("cursos", cursos);
                                response.sendRedirect("adminCursos.jsp");
                            }else{
                                cursos.clear();
                                cursos.add(cu);                        
                                session.setAttribute("cursos", cursos);
                                response.sendRedirect("adminCursos.jsp");
                            }
                        }else if(nombre != "" && codigo == "" && idCarrera == ""){
                            cursos.clear();
                            cursos = ctrl.getCursoPorNombre(nombre);
                            session.setAttribute("cursos", cursos);
                            response.sendRedirect("adminCursos.jsp");  
                        }else if(idCarrera != "" && nombre == "" && codigo == ""){
                            cursos.clear();
                            Carrera ca = ctrl.getCarrera(idCarrera);
                            cursos = ctrl.getCursoPorCarrera(ca);
                            session.setAttribute("cursos", cursos);
                            response.sendRedirect("adminCursos.jsp");  
                        }else if(nombre == "" && codigo == "" && idCarrera == ""){
                            cursos.clear();
                            cursos = ctrl.obtenerTodosLosCursos();
                            session.setAttribute("cursos", cursos);
                            response.sendRedirect("adminCursos.jsp");
                        }
                    }
                    break;
                    case "AgregarGrupo": {
                        String idCurso = request.getParameter("idCurso");
                        String numeroCiclo = request.getParameter("numeroCiclo");
                        String numero = request.getParameter("numero");
                        String[] dias = request.getParameterValues("dias");
                        String horaInicio = request.getParameter("horaInicio");
                        String horaFinal = request.getParameter("horaFinal");
                        String idProfesor = request.getParameter("idProfesor");
                        String anioCiclo = request.getParameter("anioCiclo");
                        String diasStr = "";
                        for(String s : dias){
                            diasStr = diasStr +" "+ s;
                        }
                        Horario hora = new Horario(diasStr,horaInicio,horaFinal);
                        Profesor profe = ctrl.getProfesor(idProfesor);
                        Curso cur = ctrl.getCurso(idCurso);
                        Ciclo ci = new Ciclo(Integer.parseInt(anioCiclo),numeroCiclo);
                        Grupo gru = new Grupo(Integer.parseInt(numero),hora,profe,cur,ci);
//                        this.printHTML(gru.toString(), response);
                        if(ctrl.addGrupo(gru) == 1){
                            grupos = ctrl.gruposPorCurso(cur);
                            session.setAttribute("grupos", grupos);
                            session.setAttribute("errores", "");
                            response.sendRedirect("adminGrupos.jsp");
                        }else{
                            String errores = "ERROR: Grupo NO Agregado!";
                            session.setAttribute("errores", errores);
                            response.sendRedirect("adminGrupos.jsp");
                        }
                    }
                    break;
                    case "EditarGrupo": {
                        String idGrupo= request.getParameter("idGrupo");
                        String idCurso = request.getParameter("idCurso");
                        String numeroCiclo = request.getParameter("numeroCiclo");
                        String numero = request.getParameter("numero");
                        String[] dias = request.getParameterValues("dias");
                        String horaInicio = request.getParameter("horaInicio");
                        String horaFinal = request.getParameter("horaFinal");
                        String idProfesor = request.getParameter("idProfesor");
                        String anioCiclo = request.getParameter("anioCiclo");
                        String diasStr = "";
                        for(String s : dias){
                            diasStr = diasStr +" "+ s;
                        }
                        Horario hora = new Horario(diasStr,horaInicio,horaFinal);
                        Profesor profe = ctrl.getProfesor(idProfesor);
                        Curso cur = ctrl.getCurso(idCurso);
                        Ciclo ci = new Ciclo(Integer.parseInt(anioCiclo),numeroCiclo);
                        Grupo gru = new Grupo(Integer.parseInt(idGrupo),Integer.parseInt(numero),hora,profe,cur,ci);
                        if(ctrl.updateGrupo(gru) == 1){
                            grupos = ctrl.gruposPorCurso(cur);
                            session.setAttribute("grupos", grupos);
                            session.setAttribute("errores", "");
                            response.sendRedirect("adminGrupos.jsp");
                        }else{
                            String errores = "ERROR: Grupo NO Actualizado!";
                            session.setAttribute("errores", errores);
                            response.sendRedirect("adminGrupos.jsp");
                        }
                    }
                    break;
                    case "Matricular": {
                        cicloDefault = ctrl.obtenerCicloActivo();
                        session.setAttribute("cicloDefault", cicloDefault);
                        String idEstudiante= request.getParameter("idEstudiante");
                        String idGrupo= request.getParameter("idGrupo");
                        System.out.println("id del grupo: "+idGrupo);
                        Grupo gru = ctrl.getGrupo(Integer.parseInt(idGrupo));
                        Curso cur = gru.getCurso();
                        Estudiante est = ctrl.getEstudiante(idEstudiante);
                        if((ctrl.addEstudianteAGrupo(est,gru)) == 1 ){ // && (ctrl.addEstudianteACurso(est, cur) == 1)){
                            gruposMatriculados = ctrl.obtenerGruposDeEstudiante(est);
                            session.setAttribute("gruposMatriculados", gruposMatriculados);
                            response.sendRedirect("adminMatricula.jsp");
                        }else {
                            this.printHTML("NO Matriculado", response);
                        }                        
                    }
                    break;
                    case "MatricularEnMatriculador": {
                        cicloDefault = ctrl.obtenerCicloActivo();
                        session.setAttribute("cicloDefault", cicloDefault);
                        String idEstudiante= request.getParameter("idEstudiante");
                        String idGrupo= request.getParameter("idGrupo");
                        System.out.println("id del grupo: "+idGrupo);
                        Grupo gru = ctrl.getGrupo(Integer.parseInt(idGrupo));
                        Curso cur = gru.getCurso();
                        Estudiante est = ctrl.getEstudiante(idEstudiante);
                        if((ctrl.addEstudianteAGrupo(est,gru)) == 1 ){ //&& (ctrl.addEstudianteACurso(est, cur) == 1)){
                            gruposMatriculados = ctrl.obtenerGruposDeEstudiante(est);
                            session.setAttribute("gruposMatriculados", gruposMatriculados);
                            response.sendRedirect("matriculadorMatricula.jsp");
                        }else {
                            this.printHTML("NO Matriculado", response);
                        }                        
                    }
                    break;
                    case "Retirar": {
                        String idEstudiante= request.getParameter("idEstudiante");
                        String idGrupo= request.getParameter("idGrupo");
                        System.out.println("id del grupo: "+idGrupo);
                        Grupo gru = ctrl.getGrupo(Integer.parseInt(idGrupo));
                        Estudiante est = ctrl.getEstudiante(idEstudiante);
                        if((ctrl.deleteEstudianteDeGrupo(est,gru)) == 1 ){
                            gruposMatriculados = ctrl.obtenerGruposDeEstudiante(est);
                            session.setAttribute("gruposMatriculados", gruposMatriculados);
                            response.sendRedirect("adminMatricula.jsp");
                        }else {
                            this.printHTML("NO Retirado", response);
                        }                        
                    }
                    break;
                    case "RetirarEnMatriculador": {
                        String idEstudiante= request.getParameter("idEstudiante");
                        String idGrupo= request.getParameter("idGrupo");
                        System.out.println("id del grupo: "+idGrupo);
                        Grupo gru = ctrl.getGrupo(Integer.parseInt(idGrupo));
                        Estudiante est = ctrl.getEstudiante(idEstudiante);
                        if((ctrl.deleteEstudianteDeGrupo(est,gru)) == 1 ){
                            gruposMatriculados = ctrl.obtenerGruposDeEstudiante(est);
                            session.setAttribute("gruposMatriculados", gruposMatriculados);
                            response.sendRedirect("matriculadorMatricula.jsp");
                        }else {
                            this.printHTML("NO Retirado", response);
                        }                        
                    }
                    break;                    
                    case "CicloDefault": {
                        String anio= request.getParameter("anio");
                        String numero= request.getParameter("numero");
                        this.printHTML("Matriculado "+anio+" en: "+numero, response);
                        if((ctrl.cambiarCicloActivo(Integer.parseInt(anio), numero)) == 1){
                            session.setAttribute("errores", "");
                            session.setAttribute("exito", "Ciclo Cambiado Correctamente");
                            response.sendRedirect("adminCiclos.jsp");
                        }else{
                            session.setAttribute("errores", "");
                            session.setAttribute("exito", "");
                            response.sendRedirect("adminCiclos.jsp");
                        }
                    }
                    break;
                    case "RegistrarNota": {
                        String idEstudiante= request.getParameter("idEstudiante");
                        String idCurso = request.getParameter("idCurso");
                        String nota = request.getParameter("notaEstudiante");
                        Nota no =  new Nota(Float.parseFloat(nota),ctrl.getEstudiante(idEstudiante),ctrl.getCurso(idCurso));
                        if(ctrl.addNota(no) == 1){
                            response.sendRedirect("profeNotasEstudiantes.jsp");
                        }else{
                            this.printHTML("Notas NO Registrada", response);
                        }
                    }
                    break;
                }
            }catch (Exception ex) {
                Logger.getLogger(Servlet.class.getName()).log(Level.SEVERE, null, ex);
            }
        }   
        
        
    }
    
    public void printHTML(String msg, HttpServletResponse response) throws IOException{
        PrintWriter out = response.getWriter();
        out.println("<!DOCTYPE html>");
        out.println("<html>");
        out.println("<head>");
        out.println("<title>Mensaje</title>");            
        out.println("</head>");
        out.println("<body>");
        out.println("<h1>"+msg+"</h1>");
        out.println("</body>");
        out.println("</html>");
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
