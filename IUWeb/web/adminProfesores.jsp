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
        <%ArrayList<Profesor> profesores = (ArrayList<Profesor>) session.getAttribute("profesores"); %>
        <div class="container">
            <div class="row">
                <div class="col-md-2">
                    <%@ include file="adminMenu.jspf" %>                    
                </div>
                <div class="col-md-10">
                    <div class="row">
                        <h2>Lista de Profesores</h2>
                        <div class="col-md-10" >
                            <form action="Servlet" method="POST" class="form-inline">
                                <div class="form-group">
                                    <input type="text" name="cedula" class="form-control" id="cedulaSearch" placeholder="Cédula">
                                </div>
                                <div class="form-group">
                                    <input type="text" name="nombre" class="form-control" id="nombreSearch" placeholder="Nombre">
                                </div>
                                <button type="submit" class="btn btn-default" name="action" value="BuscarProfesor">Buscar Profesor</button>
                            </form>
                        </div>
                        <div class="col-md-2" >
                            <a href="#agregarModal" data-toggle="modal" class="btn btn-primary pull-right" >Agregar Profesor</a>
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
                                    <td></td>
                                </tr>
                                <% for( Profesor pro : profesores ){ %>
                                <tr>
                                    <td><%= pro.getCedula() %></td>
                                    <td><%= pro.getNombre() %></td>
                                    <td><%= pro.getTelefono()%></td>
                                    <td><%= pro.getEmail() %></td>
                                    <td hidden="hidden"><%= pro.getUsuario().getClave() %></td>
                                    <td><a href="#editarModal" data-toggle="modal" class="btn btn-default" id="<%= pro.getCedula() %>" onclick="cargarDataModal(this)">Editar</a></td>
                                </tr>
                                <%}%>                           
                            </table>
                        </div>                        
                    </div>
                </div>
            </div>            
        </div>
        <div class="modal fade" id="editarModal" role="dialog">
            <div class="modal-dialog">
                <div class="modal-content">
                    <form action="Servlet">
                        <div class="modal-header">
                            <h4>Editar Profesor</h4>                        
                        </div>
                        <div class="modal-body">
                            <input type="text" name="cedula" id="cedulaProfesorEdit" hidden="" /><!-- Usar este en la peticion xq el del input #cedulaEdit como tiene disabled envia el dato null-->
                            <div class="form-group">
                                <input type="text" class="form-control" id="cedulaEdit" placeholder="Cédula" disabled><!--Solo es para que se muestre el codigo-->
                            </div>
                            <div class="form-group">
                                <input type="text" name="nombre" class="form-control" id="nombreEdit" placeholder="Nombre">
                            </div>
                            <div class="form-group">
                                <input type="text" name="telefono" class="form-control" id="telefonoEdit" placeholder="Teléfono">
                            </div>
                            <div class="form-group">
                                <input type="text" name="email" class="form-control" id="emailEdit" placeholder="e-mail">
                            </div>
                            <div class="form-group">
                                <input type="text" name="password" class="form-control" id="passwordEdit" placeholder="Ingrese la nueva Contraseña del Usuario">
                            </div>
                        </div>
                        <div class="modal-footer">
                            <a class="btn btn-danger" data-dismiss="modal">Cancelar</a>
                            <button type="submit" class="btn btn-default" name="action" value="EditarProfesor">Editar Profesor</button>
                        </div>
                    </form>                    
                </div>
            </div>
        </div>
        <div class="modal fade" id="agregarModal" role="dialog">
            <div class="modal-dialog">
                <div class="modal-content">
                    <form action="Servlet" method="POST">
                        <div class="modal-header">
                            <h4>Agregar Profesor</h4>                        
                        </div>
                        <div class="modal-body">
                            <div class="form-group">
                                <input type="text" name="cedula" class="form-control" id="cedulaForm" placeholder="Cédula">
                            </div>
                            <div class="form-group">
                                <input type="text" name="nombre" class="form-control" id="nombreForm" placeholder="Nombre">
                            </div>
                            <div class="form-group">
                                <input type="text" name="telefono" class="form-control" id="telefonoForm" placeholder="Teléfono">
                            </div>
                            <div class="form-group">
                                <input type="email" name="email" class="form-control" id="emailForm" placeholder="e-mail">
                            </div>
                            <div class="form-group">
                                <input type="text" name="password" class="form-control" id="passwordForm" placeholder="Contraseña del Usuario">
                            </div>
                        </div>
                        <div class="modal-footer">
                            <a class="btn btn-danger" data-dismiss="modal">Cancelar</a>
                            <button type="submit" class="btn btn-default" name="action" value="AgregarProfesor">Agregar Profesor</button>
                        </div>
                    </form>                    
                </div>
            </div>
        </div>
    </body>
    <script>
        $(document).ready(function () {
            $('#cedulaForm').tooltip({'trigger':'focus', 'title': 'Código'});
            $('#nombreForm').tooltip({'trigger':'focus', 'title': 'Nombre'});
            $('#telefonoForm').tooltip({'trigger':'focus', 'title': 'Teléfono'});
            $('#emailForm').tooltip({'trigger':'focus', 'title': 'e-mail'});
            $('#passwordForm').tooltip({'trigger':'focus', 'title': 'Contraseña del Usuario'});
            $('#cedulaEdit').tooltip({'trigger':'focus', 'title': 'Código'});
            $('#nombreEdit').tooltip({'trigger':'focus', 'title': 'Nombre'});
            $('#telefonoEdit').tooltip({'trigger':'focus', 'title': 'Teléfono'});
            $('#emailEdit').tooltip({'trigger':'focus', 'title': 'e-mail'});
            $('#passwordEdit').tooltip({'trigger':'focus', 'title': 'Ingrese la nueva Contraseña del Usuario'});
            $('#cedulaSearch').tooltip({'trigger':'focus', 'title': 'Cédula'});
            $('#nombreSearch').tooltip({'trigger':'focus', 'title': 'Nombre'});
        });
        function cargarDataModal(element){
            var id = element.id;
            var cedulaProfesor = document.getElementById("cedulaProfesorEdit");
            var cedulaInput = document.getElementById("cedulaEdit");
            var nombreInput = document.getElementById("nombreEdit");
            var telefonoInput = document.getElementById("telefonoEdit");
            var emailInput = document.getElementById("emailEdit");
            var passwordInput = document.getElementById("passwordEdit");
            
            var TD = element.parentNode;
            var TR = TD.parentNode;
            
            cedulaProfesor.value = id;
            cedulaInput.value = id;
            nombreInput.value = TR.childNodes[3].innerHTML;
            telefonoInput.value = TR.childNodes[5].innerHTML;
            emailInput.value = TR.childNodes[7].innerHTML;
            passwordInput.value = TR.childNodes[9].innerHTML;
        }
    </script>
</html>
