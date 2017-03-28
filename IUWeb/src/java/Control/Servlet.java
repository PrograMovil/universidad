
package Control;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import Control.Control;
import LogicaNegocio.Carrera;
import LogicaNegocio.Ciclo;
import LogicaNegocio.Curso;
import LogicaNegocio.Estudiante;
import LogicaNegocio.Grupo;
import LogicaNegocio.Horario;
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
        ArrayList<Estudiante> estudiantes = null;
        ArrayList<Curso> cursos = null;
        Curso cursoCurrent = null;
        ArrayList<Grupo> grupos = null;
        ArrayList<Profesor> allProfesores = null;
        ArrayList<Ciclo> ciclos = null;
        Estudiante estudianteCurrent = null;
        Carrera carreraEstudianteCurrent = null;
        ArrayList<Curso> cursosCarrera = null;
        ArrayList<Grupo> gruposCurso = null;
        Ciclo cicloDefault = null;;
        
        Control ctrl = new Control();
        HttpSession session = request.getSession();
        
        carreras = ctrl.obtenerTodasCarreras();
        profesores = ctrl.obtenerTodosLosProfesores();
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
                        response.sendRedirect("adminMatricula.jsp");
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
                                    response.sendRedirect("adminDash.jsp");
                                    break;
                                case 2: //MATRICULADOR
                                    System.out.println("Es matriculador");
                                    response.sendRedirect("matriculadorDash.jsp");
                                    break;
                                case 3: //PROFESOR
                                    System.out.println("Es profesor");
                                    response.sendRedirect("profesorDash.jsp");
                                    break;
                                case 4: //ESTUDIANTE
                                    System.out.println("Es estudiante");
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
                        session.removeAttribute("estudiantes");
                        session.removeAttribute("cursoCurrent");
                        session.removeAttribute("grupos");
                        session.removeAttribute("ciclos");
                        session.removeAttribute("estudianteCurrent");
                        session.removeAttribute("carreraEstudianteCurrent");
                        session.removeAttribute("listaGrupos");
                        session.removeAttribute("cicloDefault");
//                        session.removeAttribute("administradores");
//                        session.removeAttribute("matriculadores");
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
                        System.out.println(cicloDefault);
                        session.setAttribute("cicloDefault", cicloDefault);
                        String idEstudiante= request.getParameter("idEstudiante");
                        String idGrupo= request.getParameter("idGrupo");
                        this.printHTML("Matriculado "+idEstudiante+" en: "+idGrupo, response);
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
