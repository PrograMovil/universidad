<%-- 
    Document   : matriculador
    Created on : Mar 23, 2017, 8:10:19 PM
    Author     : SheshoVega
--%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="LogicaNegocio.Carrera"%>
<%@page import="LogicaNegocio.Estudiante"%>
<%@page import="java.util.ArrayList"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Matricular</title>
        <%@ include file="imports.jspf" %> 
        <meta name="viewport" content="width=device-width, initial-scale=1.0"/>        

    </head>
    <body>
        <%@ include file="header.jspf" %>
        <%  ArrayList<Estudiante> estudiantes = (ArrayList<Estudiante>) session.getAttribute("estudiantes"); %>
        <%  ArrayList<Carrera> allCarreras = (ArrayList<Carrera>) session.getAttribute("allCarreras"); %>
        <div class="container">
            <div class="row">
                <div class="col-md-2">
                    <%@ include file="matriculadorMenu.jspf" %>                    
                </div>
                <div class="col-md-10">
                    <div class="label label-danger col-md-12">${errores}</div>
                    <div class="row">
                        <h2>Lista de Estudiantes</h2>
                        <div class="col-md-10" >
                            <form action="Servlet" method="get" class="form-inline">
                                <div class="form-group">
                                    <input type="text" name="cedula" class="form-control" id="cedulaSearch" placeholder="Cédula">
                                </div>
                                <div class="form-group">
                                    <input type="text" name="nombre" class="form-control" id="nombreSearch" placeholder="Nombre">
                                </div>                                
                                <select class="form-control" name="idCarrera" >
                                    <option value="" >Seleccione la carrera</option>
                                <% for (Carrera c : allCarreras) { %>
                                    <option value="<%= c.getCodigo() %>"><%= c.getNombre() %></option>
                                <% } %>
                                </select>
                                <button type="submit" class="btn btn-default" name="action" value="BuscarEstudianteEnMatriculador">Buscar Estudiante</button>
                            </form>                                
                        </div>
                        <div class="col-md-2" >
                            
                        </div>                                               
                    </div>
                    <br>
                    <div class="row">
                        <div class="col-md-12">
                            <table class="table" style="text-align: center">
                                <tr>
                                    <td>Cédula</td>
                                    <td>Nombre</td>
                                    <td>Teléfono</td>
                                    <td>e-mail</td>                                    
                                    <td>Fecha de Nacimiento</td>
                                    <td>Carrera</td>
                                    <td></td>
                                </tr>
                                <% for( Estudiante es : estudiantes ){ 
                                    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
                                %>
                                <tr>
                                    <td><%= es.getCedula() %></td>
                                    <td><%= es.getNombre() %></td>
                                    <td><%= es.getTelefono()%></td>
                                    <td><%= es.getEmail() %></td>
                                    <td hidden="hidden"><%= es.getUsuario().getClave() %></td>
                                    <td><%= sdf.format(es.getFechaNac().getTime()) %></td>
                                    <td hidden="hidden"><%= es.getCarrera().getCodigo() %></td>
                                    <td><%= es.getCarrera().getNombre() %></td>
                                <form action="Servlet" method="POST">
                                    <input type="text" name="idEstudiante" value="<%= es.getCedula() %>" hidden="" />
                                    <td><button type="submit" class="btn btn-default" data-toggle="tooltip" title="Ver o Realizar Matrícula" id="matriculaBtn" name="router" value="matriculadorMatricula">Matrícula</button></td>
                                </form>
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
