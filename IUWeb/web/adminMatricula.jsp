<%-- 
    Document   : matricula
    Created on : Mar 27, 2017, 4:08:39 PM
    Author     : SheshoVega
--%>

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
        <%  Estudiante estudianteCurrent = (Estudiante) session.getAttribute("estudianteCurrent"); %>
        <div class="container">
            <div class="row">
                <div class="col-md-2">
                    <%@ include file="adminMenu.jspf" %>                    
                </div>
                <div class="col-md-10">
                    <div class="row">
                        <h2>Matrícula de <%= estudianteCurrent.getNombre() %></h2>
                        <div class="col-md-12" >
                            
                        </div>
                    </div>
                </div>
            </div>
        </div>                    
    </body>
</html>
