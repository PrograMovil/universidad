<%-- 
    Document   : adminDash
    Created on : Mar 23, 2017, 1:27:10 PM
    Author     : SheshoVega
--%>


<%@page import="LogicaNegocio.Carrera"%>
<%@page import="java.util.ArrayList"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Administrador</title>
        <%@ include file="imports.jspf" %> 
        <meta name="viewport" content="width=device-width, initial-scale=1.0"/>
    </head>
    <body>
        <%@ include file="header.jspf" %>
        <% ArrayList<Carrera> carreras = (ArrayList<Carrera>) request.getAttribute("carreras"); %>
        <div class="container">
            <div class="row">
                <div class="col-md-2">
                    <a  class="btn btn-default btn-block active">Carreras</a>
                    <a  class="btn btn-default btn-block">Cursos</a>
                    <a  class="btn btn-default btn-block">Profesores</a>
                    <a  class="btn btn-default btn-block">Estudiantes</a>                    
                </div>
                <div class="col-md-10">
                    <div class="row">
                        <form action="Servlet" method="GET" class="form-inline">
                            <div class="form-group">
                                <input type="text" name="codigo" class="form-control" id="codigoForm" placeholder="Código">
                            </div>
                            <div class="form-group">
                                <input type="text" name="nombre" class="form-control" id="nombreForm" placeholder="Nombre">
                            </div>
                            <div class="form-group">
                                <input type="text" name="titulo" class="form-control" id="tituloForm" placeholder="Título">
                            </div>
                            <button type="submit" class="btn btn-default" name="action" value="AgregarCarrera">Agregar Carrera</button>
                        </form>
                    </div>
                    <div class="row">
                        <h2>Lista de Carreras</h2>
                        <form action="Servlet" method="GET" class="form-inline">
                            <div class="form-group">
                                <input type="text" name="codigo" class="form-control" id="codigoSearch" placeholder="Código">
                            </div>
                            <div class="form-group">
                                <input type="text" name="nombre" class="form-control" id="nombreSearch" placeholder="Nombre">
                            </div>
                            <button type="submit" class="btn btn-default" name="action" value="BuscarCarrera">Buscar Carrera</button>
                        </form>
                        <table class="table">
                            <th>
                                <td>Código</td>
                                <td>Nombre</td>
                                <td>Título</td>
                            </th>
                            <% for( Carrera ca : carreras ){ 
                                out.print("<tr>");
                                out.print("<td>");
                                out.print(ca.getCodigo());
                                out.print("</td>");
                                out.print("<td>");
                                out.print(ca.getNombre());
                                out.print("</td>");
                                out.print("<td>");
                                out.print(ca.getTitulo());
                                out.print("</td>");
                                out.print("</tr>");
                            }
                            %>                           
                        </table>
                    </div>
                    
                    
                </div>
                <!--<div class="col-md-2">-->
                    <!--side bar-->
                <!--</div>-->
            </div>
        </div><!--container-->
    </body>
    <script>
        $(document).ready(function () {
            $('#codigoForm').tooltip({'trigger':'focus', 'title': 'Código'});
            $('#nombreForm').tooltip({'trigger':'focus', 'title': 'Nombre'});
            $('#tituloForm').tooltip({'trigger':'focus', 'title': 'Título'});
            $('#codigoSearch').tooltip({'trigger':'focus', 'title': 'Código'});
            $('#nombreSearch').tooltip({'trigger':'focus', 'title': 'Nombre'});
        });
        
    </script>
</html>
