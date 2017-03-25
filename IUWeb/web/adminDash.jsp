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
        <% ArrayList<Carrera> carreras = (ArrayList<Carrera>) session.getAttribute("carreras"); %>
        <div class="container">
            <div class="row">
                <div class="col-md-2">
                    <%@ include file="adminMenu.jspf" %>
                </div>
                <div class="col-md-10">
                    <div class="row">
                        <h2>Lista de Carreras</h2>
                        <div class="col-md-10" >
                            <form action="Servlet" method="POST" class="form-inline">
                                <div class="form-group">
                                    <input type="text" name="codigo" class="form-control" id="codigoSearch" placeholder="Código">
                                </div>
                                <div class="form-group">
                                    <input type="text" name="nombre" class="form-control" id="nombreSearch" placeholder="Nombre">
                                </div>
                                <button type="submit" class="btn btn-default" name="action" value="BuscarCarrera">Buscar Carrera</button>
                            </form>
                        </div>
                        <div class="col-md-2" >
                            <a href="#agregarModal" data-toggle="modal" class="btn btn-primary pull-right" >Agregar Carrera</a>
                        </div>                        
                    </div>
                    <br>
                    <div class="row">
                        <div class="col-md-12">
                            <table class="table" style="text-align: center">
                                <tr>
                                    <td>Código</td>
                                    <td>Nombre</td>
                                    <td>Título</td>
                                    <td></td>
                                </tr>
                                <% for( Carrera ca : carreras ){ %>
                                <tr>
                                    <td><%= ca.getCodigo() %></td>
                                    <td><%= ca.getNombre() %></td>
                                    <td><%= ca.getTitulo() %></td>
                                    <td><a href="#editarModal" data-toggle="modal" class="btn btn-default" id="<%= ca.getCodigo() %>" onclick="cargarDataModal(this)">Editar</a></td>
                                </tr>
                                <%}%>                           
                            </table>
                        </div>                        
                    </div>
                </div>
            </div>
        </div><!--container-->
        <div class="modal fade" id="editarModal" role="dialog">
            <div class="modal-dialog">
                <div class="modal-content">
                    <form action="Servlet">
                        <div class="modal-header">
                            <h4>Editar Carrera</h4>                        
                        </div>
                        <div class="modal-body">
                            <input type="text" name="codigo" id="codigoCarreraEdit" hidden="" /><!-- Usar este en la peticion xq el del input #codigoEdit como tiene disabled envia el dato null-->
                            <div class="form-group">
                                <input type="text" class="form-control" id="codigoEdit" placeholder="Código" disabled><!--Solo es para que se muestre el codigo-->
                            </div>
                            <div class="form-group">
                                <input type="text" name="nombre" class="form-control" id="nombreEdit" placeholder="Nombre">
                            </div>
                            <div class="form-group">
                                <input type="text" name="titulo" class="form-control" id="tituloEdit" placeholder="Título">
                            </div>
                        </div>
                        <div class="modal-footer">
                            <a class="btn btn-danger" data-dismiss="modal">Cancelar</a>
                            <button type="submit" class="btn btn-default" name="action" value="EditarCarrera">Editar Carrera</button>
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
                            <h4>Agregar Carrera</h4>                        
                        </div>
                        <div class="modal-body">
                            <div class="form-group">
                                <input type="text" name="codigo" class="form-control" id="codigoForm" placeholder="Código">
                            </div>
                            <div class="form-group">
                                <input type="text" name="nombre" class="form-control" id="nombreForm" placeholder="Nombre">
                            </div>
                            <div class="form-group">
                                <input type="text" name="titulo" class="form-control" id="tituloForm" placeholder="Título">
                            </div>
                        </div>
                        <div class="modal-footer">
                            <a class="btn btn-danger" data-dismiss="modal">Cancelar</a>
                            <button type="submit" class="btn btn-default" name="action" value="AgregarCarrera">Agregar Carrera</button>
                        </div>
                    </form>                    
                </div>
            </div>
        </div>
    </body>
    <script>
        $(document).ready(function () {
            $('#codigoForm').tooltip({'trigger':'focus', 'title': 'Código'});
            $('#nombreForm').tooltip({'trigger':'focus', 'title': 'Nombre'});
            $('#tituloForm').tooltip({'trigger':'focus', 'title': 'Título'});
            $('#codigoSearch').tooltip({'trigger':'focus', 'title': 'Código'});
            $('#nombreSearch').tooltip({'trigger':'focus', 'title': 'Nombre'});
        });
        function cargarDataModal(element){
            var id = element.id;
            var codigoCarrera = document.getElementById("codigoCarreraEdit")
            var codigoInput = document.getElementById("codigoEdit")
            var nombreInput = document.getElementById("nombreEdit")
            var tituloInput = document.getElementById("tituloEdit")
            
            var TD = element.parentNode;
            var TR = TD.parentNode;
            codigoCarrera.value = id;
            codigoInput.value = id;
            nombreInput.value = TR.childNodes[3].innerHTML;
            tituloInput.value = TR.childNodes[5].innerHTML;
        }
    </script>
</html>
