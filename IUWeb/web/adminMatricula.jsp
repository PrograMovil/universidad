<%-- 
    Document   : matricula
    Created on : Mar 27, 2017, 4:08:39 PM
    Author     : SheshoVega
--%>

<%@page import="LogicaNegocio.Grupo"%>
<%@page import="java.util.ArrayList"%>
<%@page import="LogicaNegocio.Curso"%>
<%@page import="LogicaNegocio.Carrera"%>
<%@page import="LogicaNegocio.Estudiante"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Matrícula</title>
        <%@ include file="imports.jspf" %> 
        <meta name="viewport" content="width=device-width, initial-scale=1.0"/> 
    </head>
    <body>
        <%@ include file="header.jspf" %>
        <%  Estudiante estudianteCurrent = (Estudiante) session.getAttribute("estudianteCurrent"); 
          Carrera carreraEstudianteCurrent = (Carrera) session.getAttribute("carreraEstudianteCurrent"); 
          ArrayList< ArrayList<Grupo> > listaGrupos = (ArrayList< ArrayList<Grupo> >) session.getAttribute("listaGrupos"); %>
        <div class="container">
            <div class="row">
                <div class="col-md-2">
                    <%@ include file="adminMenu.jspf" %>                    
                </div>
                <div class="col-md-10">
                    <div class="row">
                        <h2>Matrícula de <%= estudianteCurrent.getNombre() %> de  </h2>
                        <div class="col-md-12" >
                            <table class="table" style="text-align: center">
                                <tr>
                                    <td>Curso</td>
                                    <td># Grupo</td>
                                    <td>Horario</td>
                                    <td>Profesor</td>
                                    <td>Ciclo</td>
                                    <td></td>
                                </tr>                            
                            <%  for( int i=0; i<listaGrupos.size(); i++ ){ 
                                    ArrayList<Grupo> gruposList = listaGrupos.get(i);
                                    for( int j=0; j<gruposList.size(); j++ ){ 
                                        Grupo grupo = gruposList.get(j);
                            %>
                                <tr>
                                    <td><%= grupo.getCurso().getNombre() %></td>
                                    <td><%= grupo.getNumero() %></td>
                                    <td><%= grupo.getHorario().toString() %></td>
                                    <td><%= grupo.getProfesor().getNombre() %></td>
                                    <td><%= grupo.getCiclo().getNumero() %></td>
                                    <td><a href="#editarModal" data-toggle="modal" class="btn btn-default" id="" onclick="cargarDataModal(this)">Matricular</a></td>
                                </tr>
                            <%  } 
                             } %>
                            </table>
                        </div>
                    </div>
                </div>
            </div>
        </div>                    
    </body>
</html>
