/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Control;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import Control.Control;
import LogicaNegocio.Carrera;
import java.util.logging.Level;
import java.util.logging.Logger;
/**
 *
 * @author SheshoVega
 */
public class Servlet extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        
//        Define cual es la accion que se va a realizar
        String accion = request.getParameter("action");
        
        Control ctrl = new Control();
        try{
            switch (accion) {
                case "Ingresar": {
                    String id = request.getParameter("id");
                    String pass = request.getParameter("password");
                    int tipoUsuario = ctrl.verificaUsuario(id, pass);
                    if(tipoUsuario != 0){
                        switch(tipoUsuario){ 
                            case 1: //ADMINISTRADOR
                                System.out.println("Es administrador");
                                response.sendRedirect("adminDash.jsp");
                                break;
                            case 2: //MATRICULADOR
                                System.out.println("Es matriculador");
                                response.sendRedirect("adminDash.jsp");
                                break;
                            case 3: //PROFESOR
                                System.out.println("Es profesor");
                                response.sendRedirect("adminDash.jsp");
                                break;
                            case 4: //ESTUDIANTE
                                System.out.println("Es estudiante");
                                response.sendRedirect("adminDash.jsp");
                                break;
                        }
                    }else{
                        response.sendRedirect("login.jsp");
                    }

                }
            }
        }catch (Exception ex) {
            Logger.getLogger(Servlet.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    
        
//        try (PrintWriter out = response.getWriter()) {
//            Carrera car = ctrl.getCarrera(codigo);
            /* TODO output your page here. You may use following sample code. */
//            out.println("<!DOCTYPE html>");
//            out.println("<html>");
//            out.println("<head>");
//            out.println("<title>Servlet Servlet</title>");            
//            out.println("</head>");
//            out.println("<body>");
//            if(ctrl.addCarreraServlet(codigo, nombre, titulo) == 1){
//                out.println("<h1>Carrera: AGREGADA</h1>");
//            }else{
//                out.println("<h1>Carrera: ERROR!</h1>");
//            }            
//            out.println("<p>Lista de carreras<p>");
//            out.println("<h1>Carrera: " + codigo + "</h1>");
//            out.println("<h1>Carrera: " + car.getNombre() + "</h1>");
//            out.println("</body>");
//            out.println("</html>");
//        }catch (Exception ex) {
//            Logger.getLogger(Servlet.class.getName()).log(Level.SEVERE, null, ex);
//        }
        
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
