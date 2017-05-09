<%-- 
    Document   : estudianteDash
    Created on : Mar 23, 2017, 8:22:04 PM
    Author     : SheshoVega
--%>
<%@page import="Control.Control"%>
<%@page import="LogicaNegocio.Grupo"%>
<%@page import="java.util.ArrayList"%>
<%@page import="LogicaNegocio.Estudiante"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Expediente del Estudiante</title>
        <%@ include file="imports.jspf" %> 
        <meta name="viewport" content="width=device-width, initial-scale=1.0"/>
    </head>
    <body>
        <%@ include file="header.jspf" %>
        <%  
            Estudiante estudianteCurrent = (Estudiante) session.getAttribute("estudianteCurrent"); 
            ArrayList<Grupo> listaGruposEstudiante = (ArrayList<Grupo>) session.getAttribute("gruposDelEstudiante");
            Control ctrl = new Control();
        %>
        <div class="container">
            <div class="row">
                <div class="col-md-2">
                    <%@ include file="adminMenu.jspf" %>                    
                </div>
                <div class="col-md-10">
                    <div class="label label-danger col-md-12">${errores}</div>
                    <div class="row">
                        <h1>Expediente de <%= estudianteCurrent.getNombre() %>:</h1>
                        <div class="col-md-12" >
                            <table class="table" style="text-align: center">
                                <tr>
                                    <td>Código</td>
                                    <td>Curso</td>
                                    <td>Creditos</td>
                                    <td>Nivel</td>
                                    <td>Nota</td>
                                </tr>                            
                            <%  for( Grupo grupo : listaGruposEstudiante ){ %>
                                <tr>
                                    <td><%= grupo.getCurso().getCodigo() %></td>
                                    <td><%= grupo.getCurso().getNombre() %></td>
                                    <td><%= grupo.getCurso().getCreditos() %></td>
                                    <td><%= grupo.getCurso().getNivel() %></td>
                                    <% if(ctrl.getNota(estudianteCurrent.getCedula(), grupo.getCurso().getId()) != null){ %>
                                    <td><%= ctrl.getNota(estudianteCurrent.getCedula(), grupo.getCurso().getId()).getCalificacion() %></td>
                                    <% } else { %>
                                    <td>Sin asignar</td>
                                    <% } %>                                    
                                </tr>
                            <%  } %>
                            </table>
                        </div>
                    </div>
                </div>
            </div>
        </div>        
    </body>
</html>
