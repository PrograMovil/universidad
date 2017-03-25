<%-- 
    Document   : adminCursos
    Created on : Mar 24, 2017, 2:10:49 PM
    Author     : SheshoVega
--%>
<%@page import="LogicaNegocio.Carrera"%>
<%@page import="java.util.ArrayList"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Cursos</title>
        <%@ include file="imports.jspf" %> 
        <meta name="viewport" content="width=device-width, initial-scale=1.0"/>
    </head>
    <body>
        <%@ include file="header.jspf" %>
        <% //ArrayList<Curso> cursos = (ArrayList<Curso>) session.getAttribute("cursos"); %>
        <%  ArrayList<Carrera> allCarreras = (ArrayList<Carrera>) session.getAttribute("allCarreras"); %>
        <div class="container">
            <div class="row">
                <div class="col-md-2">
                    <a href="adminDash.jsp" class="btn btn-default btn-block">Carreras</a>
                    <a href="adminCursos.jsp" class="btn btn-default btn-block active">Cursos</a>
                    <a href="adminProfesores.jsp" class="btn btn-default btn-block">Profesores</a>
                    <a href="adminEstudiantes.jsp" class="btn btn-default btn-block">Estudiantes</a>                    
                </div>
                <div class="col-md-10">
                    <div class="row">
                        <form action="Servlet" method="POST" class="form-inline">
                            <div class="form-group">
                                <input type="text" name="codigo" class="form-control" id="codigoSearch" placeholder="Código">
                            </div>
                            <div class="form-group">
                                <input type="text" name="nombre" class="form-control" id="nombreSearch" placeholder="Nombre">
                            </div>
                            <select class="form-control">
                                <% for (Carrera c : allCarreras) { %>
                                    <option value="<%= c.getCodigo() %>"><%= c.getNombre() %></option>
                                <% } %>
                            </select>
                            <button type="submit" class="btn btn-default" name="action" value="BuscarCurso">Buscar Curso</button>
                        </form>
                        <h2>Lista de Cursos</h2>                        
                    </div>
                </div>
            </div>            
        </div>
    </body>
</html>
