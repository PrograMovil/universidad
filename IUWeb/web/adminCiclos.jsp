<%-- 
    Document   : adminCiclo
    Created on : Mar 27, 2017, 4:14:37 PM
    Author     : SheshoVega
--%>

<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.util.ArrayList"%>
<%@page import="LogicaNegocio.Ciclo"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Ciclo Lectivo</title>
        <%@ include file="imports.jspf" %> 
        <meta name="viewport" content="width=device-width, initial-scale=1.0"/> 
    </head>
    <body>
        <%@ include file="header.jspf" %>
        <%ArrayList<Ciclo> ciclos = (ArrayList<Ciclo>) session.getAttribute("ciclos"); %>
        <div class="container">
            <div class="row">
                <div class="col-md-2">
                    <%@ include file="adminMenu.jspf" %>                    
                </div>
                <div class="col-md-10">
                    <div class="row">
                        <h2>Lista de Ciclos</h2>
                        <div class="col-md-12" >
                            <table class="table" style="text-align: center">
                                <tr>
                                    <td>Año</td>
                                    <td>Número</td>
                                    <td>Fecha de Inicio</td>
                                    <td>Fecha de Finalización</td>  
                                    <td></td>
                                </tr>
                                <% for( Ciclo ci : ciclos ){ %>
                                <tr>
                                    <td><%= ci.getAnio() %></td>
                                    <td><%= ci.getNumero() %></td>
                                    <td><%= ci.getFechaInicio() %></td>
                                    <td><%= ci.getFechaFinalizacion() %></td>
                                <form action="Servlet" method="POST">
                                    <input type="text" name="anio" value="<%= ci.getAnio() %>" hidden="" />
                                    <input type="text" name="numero" value="<%= ci.getNumero() %>" hidden="" />
                                    <td><button type="submit" class="btn btn-default" data-toggle="tooltip" title="" id="cicloDefaultBtn" name="action" value="CicloDefault">Seleccionar como Default</button></td>
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
