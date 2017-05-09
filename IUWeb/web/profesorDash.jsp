<%-- 
    Document   : matricula
    Created on : Mar 27, 2017, 4:08:39 PM
    Author     : SheshoVega
--%>

<%@page import="LogicaNegocio.Profesor"%>
<%@page import="LogicaNegocio.Ciclo"%>
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
        <title>Registro de Notas</title>
        <%@ include file="imports.jspf" %> 
        <meta name="viewport" content="width=device-width, initial-scale=1.0"/> 
    </head>
    <body>
        <%@ include file="header.jspf" %>
        <%  Profesor profesorCurrent = (Profesor) session.getAttribute("profeCurrent"); 
          ArrayList<Grupo> listaGruposProfesor = (ArrayList<Grupo>) session.getAttribute("gruposDelProfe"); %>
        <div class="container">
            <div class="row">
                <div class="col-md-2">
                    <%@ include file="profeMenu.jspf" %>                    
                </div>
                <div class="col-md-10">
                    <div class="label label-danger col-md-12">${errores}</div>
                    <div class="row">
                        <h2>Lista de grupos del profesor <%= profesorCurrent.getNombre() %>  </h2>
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
                            <%  for( Grupo grupo : listaGruposProfesor ){ %>
                                <tr>
                                    <td><%= grupo.getCurso().getNombre() %></td>
                                    <td><%= grupo.getNumero() %></td>
                                    <td><%= grupo.getHorario().toString() %></td>
                                    <td><%= grupo.getProfesor().getNombre() %></td>
                                    <td><%= grupo.getCiclo().getNumero() %></td>
                                <form action="Servlet" method="POST">
                                    <input type="text" name="codigoCurso" value="<%= grupo.getCurso().getCodigo() %>" hidden="" />
                                    <input type="text" name="idGrupo" value="<%= grupo.getId() %>" hidden="" />
                                    <td><button type="submit" class="btn btn-default" data-toggle="tooltip" title="Notas" id="notasBtn" name="router" value="notas">Notas</button></td>
                                </form>
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
