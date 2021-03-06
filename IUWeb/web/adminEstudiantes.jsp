<%-- 
    Document   : adminEstudiantes
    Created on : Mar 25, 2017, 6:49:13 PM
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
        <title>Estudiantes</title>
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
                    <%@ include file="adminMenu.jspf" %>                    
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
                                <button type="submit" class="btn btn-default" name="action" value="BuscarEstudiante">Buscar Estudiante</button>
                            </form>                                
                        </div>
                        <div class="col-md-2" >
                            <a href="#agregarModal" data-toggle="modal" class="btn btn-primary pull-right" >Agregar Estudiante</a>
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
                                    <td><a href="#editarModal" data-toggle="modal" class="btn btn-default" id="<%= es.getCedula() %>" onclick="cargarDataModal(this)">Editar</a></td>
                                <form action="Servlet" method="POST">
                                    <input type="text" name="idEstudiante" value="<%= es.getCedula() %>" hidden="" />
                                    <td><button type="submit" class="btn btn-default" data-toggle="tooltip" title="Ver o Realizar Matrícula" id="matriculaBtn" name="router" value="adminMatricula">Matrícula</button></td>
                                </form>
                                <form action="Servlet" method="POST">
                                    <input type="text" name="cedulaEstudiante" value="<%= es.getCedula() %>" hidden="" />
                                    <td><button type="submit" class="btn btn-default" data-toggle="tooltip" title="Ver Expediente Académico" id="expedienteBtn" name="router" value="expediente">Expediente Académico</button></td>
                                </form>
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
                            <h4>Editar Estudiante</h4>                        
                        </div>
                        <div class="modal-body">
                            <input type="text" name="cedula" id="cedulaEstudianteEdit" hidden="" /><!-- Usar este en la peticion xq el del input #cedulaEdit como tiene disabled envia el dato null-->
                            <div class="form-group">
                                <input type="text" class="form-control" id="cedulaEdit" placeholder="Cédula" disabled><!--Solo es para que se muestre la cedula-->
                            </div>
                            <div class="form-group">
                                <input required="" type="text" name="fechaNac" class="form-control datepicker" id="fechaNacEdit" placeholder="dd/mm/aaaa" pattern="^(?:(?:31(\/|-|\.)(?:0?[13578]|1[02]))\1|(?:(?:29|30)(\/|-|\.)(?:0?[1,3-9]|1[0-2])\2))(?:(?:1[6-9]|[2-9]\d)?\d{2})$|^(?:29(\/|-|\.)0?2\3(?:(?:(?:1[6-9]|[2-9]\d)?(?:0[48]|[2468][048]|[13579][26])|(?:(?:16|[2468][048]|[3579][26])00))))$|^(?:0?[1-9]|1\d|2[0-8])(\/|-|\.)(?:(?:0?[1-9])|(?:1[0-2]))\4(?:(?:1[6-9]|[2-9]\d)?\d{2})$">
                            </div>
                            <div class="form-group">
                                <input required="" type="text" name="nombre" class="form-control" id="nombreEdit" placeholder="Nombre">
                            </div>                            
                            <select required="" class="form-control" name="idCarrera" id="idCarreraEdit">
                                <option value="ninguna" >Seleccione la carrera</option>
                            <% for (Carrera c : allCarreras) { %>
                                <option value="<%= c.getCodigo() %>"><%= c.getNombre() %></option>
                            <% } %>
                            </select>
                            <br>
                            <div class="form-group">
                                <input required="" type="number" name="telefono" class="form-control" id="telefonoEdit" placeholder="Teléfono" min="10000000">
                            </div>
                            <div class="form-group">
                                <input required="" type="email" name="email" class="form-control" id="emailEdit" placeholder="e-mail">
                            </div>
                            <div class="form-group">
                                <input required="" type="text" name="password" class="form-control" id="passwordEdit" placeholder="Ingrese la nueva Contraseña del Usuario">
                            </div>
                        </div>
                        <div class="modal-footer">
                            <a class="btn btn-danger" data-dismiss="modal">Cancelar</a>
                            <button type="submit" class="btn btn-default" name="action" value="EditarEstudiante">Editar Estudiante</button>
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
                            <h4>Agregar Estudiante</h4>                        
                        </div>
                        <div class="modal-body">
                            <div class="form-group">
                                <input required="" type="number" name="cedula" class="form-control" id="cedulaForm" placeholder="Cédula" min="100000000">
                            </div>
                            <div class="form-group">
                                <input required="" type="text" name="fechaNac" class="form-control datepicker" id="fechaNacForm" placeholder="dd/mm/aaaa" pattern="^(?:(?:31(\/|-|\.)(?:0?[13578]|1[02]))\1|(?:(?:29|30)(\/|-|\.)(?:0?[1,3-9]|1[0-2])\2))(?:(?:1[6-9]|[2-9]\d)?\d{2})$|^(?:29(\/|-|\.)0?2\3(?:(?:(?:1[6-9]|[2-9]\d)?(?:0[48]|[2468][048]|[13579][26])|(?:(?:16|[2468][048]|[3579][26])00))))$|^(?:0?[1-9]|1\d|2[0-8])(\/|-|\.)(?:(?:0?[1-9])|(?:1[0-2]))\4(?:(?:1[6-9]|[2-9]\d)?\d{2})$">
                            </div>
                            <div class="form-group">
                                <input required="" type="text" name="nombre" class="form-control" id="nombreForm" placeholder="Nombre">
                            </div>                            
                            <select required="" class="form-control" name="idCarrera" id="idCarreraForm" >
                                <option value="ninguna" >Seleccione la carrera</option>
                            <% for (Carrera c : allCarreras) { %>
                                <option value="<%= c.getCodigo() %>"><%= c.getNombre() %></option>
                            <% } %>
                            </select>
                            <br>
                            <div class="form-group">
                                <input required="" type="number" name="telefono" class="form-control" id="telefonoForm" placeholder="Teléfono sin (-)" min="10000000">
                            </div>
                            <div class="form-group">
                                <input required="" type="email" name="email" class="form-control" id="emailForm" placeholder="e-mail">
                            </div>
                            <div class="form-group">
                                <input required="" type="text" name="password" class="form-control" id="passwordForm" placeholder="Contraseña del Usuario">
                            </div>
                        </div>
                        <div class="modal-footer">
                            <a class="btn btn-danger" data-dismiss="modal">Cancelar</a>
                            <button type="submit" class="btn btn-default" name="action" value="AgregarEstudiante">Agregar Estudiante</button>
                        </div>
                    </form>                    
                </div>
            </div>
        </div>
    </body>
    <script>
        $(document).ready(function () {
            $('#cedulaForm').tooltip({'trigger':'focus', 'title': 'Cédula'});
            $('#fechaNacForm').tooltip({'trigger':'focus', 'title': 'Fecha de Nacimiento'});
            $('#nombreForm').tooltip({'trigger':'focus', 'title': 'Nombre'});
            $('#idCarreraForm').tooltip({'trigger':'focus', 'title': 'Seleccione la Carrera'});
            $('#telefonoForm').tooltip({'trigger':'focus', 'title': 'Teléfono'});
            $('#emailForm').tooltip({'trigger':'focus', 'title': 'e-mail'});
            $('#passwordForm').tooltip({'trigger':'focus', 'title': 'Contraseña del Usuario'});
            $('#cedulaEdit').tooltip({'trigger':'focus', 'title': 'Código'});
            $('#fechaNacEdit').tooltip({'trigger':'focus', 'title': 'Fecha de Nacimiento'});
            $('#nombreEdit').tooltip({'trigger':'focus', 'title': 'Nombre'});
            $('#idCarreraEdit').tooltip({'trigger':'focus', 'title': 'Seleccione la Carrera'});
            $('#telefonoEdit').tooltip({'trigger':'focus', 'title': 'Teléfono'});
            $('#emailEdit').tooltip({'trigger':'focus', 'title': 'e-mail'});
            $('#passwordEdit').tooltip({'trigger':'focus', 'title': 'Ingrese la nueva Contraseña del Usuario'});
            $('#cedulaSearch').tooltip({'trigger':'focus', 'title': 'Cédula'});
            $('#nombreSearch').tooltip({'trigger':'focus', 'title': 'Nombre'});
            $('[data-toggle="tooltip"]').tooltip();
        });
        function cargarDataModal(element){
            var id = element.id;
            var cedulaEstudiante = document.getElementById("cedulaEstudianteEdit");
            var cedulaInput = document.getElementById("cedulaEdit");
            var fechaInput = document.getElementById("fechaNacEdit");
            var nombreInput = document.getElementById("nombreEdit");
            var carreraInput = document.getElementById("idCarreraEdit");
            var telefonoInput = document.getElementById("telefonoEdit");
            var emailInput = document.getElementById("emailEdit");
            var passwordInput = document.getElementById("passwordEdit");
            
            var TD = element.parentNode;
            var TR = TD.parentNode;
            
            cedulaEstudiante.value = id;
            cedulaInput.value = id;
            nombreInput.value = TR.childNodes[3].innerHTML;
            telefonoInput.value = TR.childNodes[5].innerHTML;
            emailInput.value = TR.childNodes[7].innerHTML;
            passwordInput.value = TR.childNodes[9].innerHTML;
            fechaInput.value = TR.childNodes[11].innerHTML;
            carreraInput.value = TR.childNodes[13].innerHTML;
        }
    </script>
</html>
