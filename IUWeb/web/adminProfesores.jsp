<%-- 
    Document   : adminProfesores
    Created on : Mar 24, 2017, 1:45:34 PM
    Author     : SheshoVega
--%>

<%@page import="LogicaNegocio.Profesor"%>
<%@page import="java.util.ArrayList"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Profesores</title>
        <%@ include file="imports.jspf" %> 
        <meta name="viewport" content="width=device-width, initial-scale=1.0"/> 
    </head>
    <body>
        <%@ include file="header.jspf" %>
        <% //ArrayList<Profesor> profesores = (ArrayList<Profesor>) session.getAttribute("profesores"); %>
        <div class="container">
            <div class="row">
                <div class="col-md-2">
                    <%@ include file="adminMenu.jspf" %>                    
                </div>
                <div class="col-md-10">
                    <div class="row">
                        <form action="Servlet" method="POST" class="form-inline">
                            <div class="form-group">
                                <input type="text" name="cedula" class="form-control" id="cedulaSearch" placeholder="Cédula">
                            </div>
                            <div class="form-group">
                                <input type="text" name="nombre" class="form-control" id="nombreSearch" placeholder="Nombre">
                            </div>
                            <button type="submit" class="btn btn-default" name="action" value="BuscarProfesor">Buscar Profesor</button>
                        </form>
                        <h2>Lista de Profesores</h2>                        
                    </div>
                </div>
            </div>            
        </div>
    </body>
    <script>
        $(document).ready(function () {
//            $('#codigoForm').tooltip({'trigger':'focus', 'title': 'Código'});
//            $('#nombreForm').tooltip({'trigger':'focus', 'title': 'Nombre'});
//            $('#tituloForm').tooltip({'trigger':'focus', 'title': 'Título'});
            $('#cedulaSearch').tooltip({'trigger':'focus', 'title': 'Cédula'});
            $('#nombreSearch').tooltip({'trigger':'focus', 'title': 'Nombre'});
        });
    </script>
</html>
