<%-- 
    Document   : login
    Created on : Mar 22, 2017, 10:18:21 PM
    Author     : SheshoVega
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Gestión Académica</title>
        <%@ include file="imports.jspf" %> 
        <meta name="viewport" content="width=device-width, initial-scale=1.0"/>
    </head>
    <body>
        <div class="container">
            <div class="row">
                <div class="col-md-4 col-md-offset-4">
                    <form class="form-signin" action="Servlet" method="POST">
                        <h2 style="text-align: center">Gestión Académica</h2>
                        <label for="id" class="sr-only">Identificación</label>
                        <input type="text" id="id" name="id" class="form-control" placeholder="Identificación" required autofocus><br>
                        <label for="password" class="sr-only">Contraseña</label>
                        <input type="password" id="password" name="password" class="form-control" placeholder="Contraseña" required><br>
                        <input name="action" class="btn btn-lg btn-primary btn-block" type="submit" value="Ingresar">
                    </form>
                </div>
            </div>     
            <div class="row">
                <div class="col-md-4 col-md-offset-4" style="text-align: center">
                    <span class="label label-danger">${errores}</span>
                </div>
            </div>
        </div> <!-- /container -->
    </body>
</html>
