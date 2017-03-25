<%-- 
    Document   : adminCursos
    Created on : Mar 24, 2017, 2:10:49 PM
    Author     : SheshoVega
--%>
<%@page import="LogicaNegocio.Curso"%>
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
        <%  ArrayList<Curso> cursos = (ArrayList<Curso>) session.getAttribute("cursos"); %>
        <%  ArrayList<Carrera> allCarreras = (ArrayList<Carrera>) session.getAttribute("allCarreras"); %>
        <div class="container">
            <div class="row">
                <div class="col-md-2">
                    <%@ include file="adminMenu.jspf" %>                    
                </div>
                <div class="col-md-10">
                    <div class="row">
                        <h2>Lista de Cursos</h2>
                        <div class="col-md-10" >
                            <form action="Servlet" method="POST" class="form-inline">
                                <div class="form-group">
                                    <input type="text" name="codigo" class="form-control" id="codigoSearch" placeholder="Código">
                                </div>
                                <div class="form-group">
                                    <input type="text" name="nombre" class="form-control" id="nombreSearch" placeholder="Nombre">
                                </div>
                                <select class="form-control">
                                    <option value="default" >Seleccione la carrera</option>
                                <% for (Carrera c : allCarreras) { %>
                                    <option value="<%= c.getCodigo() %>"><%= c.getNombre() %></option>
                                <% } %>
                                </select>
                                <button type="submit" class="btn btn-default" name="action" value="BuscarCurso">Buscar Curso</button>
                            </form>
                        </div>
                        <div class="col-md-2" >
                            <a href="#agregarModal" data-toggle="modal" class="btn btn-primary pull-right" >Agregar Curso</a>
                        </div>                                                                        
                    </div>
                    <br>
                    <div class="row">
                        <div class="col-md-12">
                            <table class="table" style="text-align: center">
                                <tr>
                                    <td>Código</td>
                                    <td>Nombre</td>
                                    <td>Créditos</td>
                                    <td>Horas Semana</td>
                                    <td>Carrera</td>
                                    <td>Nivel</td>
                                    <td></td>
                                </tr>
                                <% for( Curso cur : cursos ){ %>
                                <tr>
                                    <td><%= cur.getCodigo() %></td>
                                    <td><%= cur.getNombre()%></td>
                                    <td><%= cur.getCreditos()%></td>
                                    <td><%= cur.getHorasSemanales() %></td>
                                    <td><%= cur.getCarrera().getNombre() %></td>
                                    <td><%= cur.getNivel() %></td>
                                    <td><a href="#editarModal" data-toggle="modal" class="btn btn-default" id="<%= cur.getCodigo() %>" onclick="">Editar</a></td>
                                </tr>
                                <%}%>                           
                            </table>
                        </div>                        
                    </div>
                            
                </div>
            </div>            
        </div>
    </body>
</html>
