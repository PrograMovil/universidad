
package Control;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import Control.Control;
import LogicaNegocio.Carrera;
import LogicaNegocio.Curso;
import LogicaNegocio.Profesor;
import LogicaNegocio.Usuario;
import java.util.ArrayList;
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
        ArrayList<Curso> cursos = null;

        Control ctrl = new Control();
        HttpSession session = request.getSession();
        
        carreras = ctrl.obtenerTodasCarreras();
        profesores = ctrl.obtenerTodosLosProfesores();
        
//        Router y rutas
        if(router !=null){
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
//                    cursos = 
//                    session.setAttribute("cursos", cursos);
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
                    response.sendRedirect("adminEstudiantes.jsp");
                }
                break;
            }
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
                                    session.setAttribute("carreras", carreras);
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
//                        session.removeAttribute("cursos");
                        session.removeAttribute("profesores");
//                        session.removeAttribute("estudiantes");
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
                            response.sendRedirect("adminDash.jsp");
                        }else{
                            this.printHTML("ERROR: Carrera NO Agregada!", response);
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
                                Carrera c = new Carrera("","","");
                                carreras.add(c);
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
                            response.sendRedirect("adminDash.jsp");
                        }else{
                            this.printHTML("ERROR: Carrera NO Actualizada!", response);
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
                            response.sendRedirect("adminProfesores.jsp");
                        }else{
                            this.printHTML("ERROR: Profesor NO Agregado!", response);
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
                                Profesor p = new Profesor(null,"","","","");
                                profesores.add(p);
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
                            response.sendRedirect("adminProfesores.jsp");
                        }else{
                            this.printHTML("ERROR: Profesor NO Actualizado!", response);
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
        out.println("<title>Servlet Servlet</title>");            
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
