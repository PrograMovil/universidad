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
                                <select class="form-control" name="idCarrera">
                                    <option value="" >Seleccione la carrera</option>
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
                                    <td hidden="" ><%= cur.getCarrera().getCodigo() %></td>
                                    <td><%= cur.getCarrera().getNombre() %></td>
                                    <td><%= cur.getNivel() %></td>
                                    <td><a href="#editarModal" data-toggle="modal" class="btn btn-default" id="<%= cur.getCodigo() %>" onclick="cargarDataModal(this)">Editar</a></td>
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
                            <h4>Editar Curso</h4>                        
                        </div>
                        <div class="modal-body">
                            <input type="text" name="codigo" id="codigoCursoEdit" hidden="" /><!-- Usar este en la peticion xq el del input #cedulaEdit como tiene disabled envia el dato null-->
                            <div class="form-group">
                                <input type="text" class="form-control" id="codigoEdit" placeholder="Código" disabled><!--Solo es para que se muestre el codigo-->
                            </div>
                            <div class="form-group">
                                <input type="text" name="nombre" class="form-control" id="nombreEdit" placeholder="Nombre">
                            </div>
                            <div class="form-group">
                                <input type="number" name="creditos" class="form-control" id="creditosEdit" placeholder="Créditos">
                            </div>
                            <div class="form-group">
                                <input type="number" name="horasSemanales" class="form-control" id="horasSemanalesEdit" placeholder="Horas Semanales">
                            </div>
                            <select class="form-control" name="idCarrera" id="idCarreraEdit" >
                                <option value="" >Seleccione la carrera</option>
                            <% for (Carrera c : allCarreras) { %>
                                <option value="<%= c.getCodigo() %>"><%= c.getNombre() %></option>
                            <% } %>
                            </select>
                            <br>
                            <select class="form-control" name="nivel" id="nivelEdit" >
                                <option value="" >Seleccione el Nivel</option>
                                <option value="I NIVEL" >I Nivel</option>
                                <option value="II NIVEL" >II Nivel</option>
                                <option value="III NIVEL" >III Nivel</option>
                                <option value="IV NIVEL<" >IV Nivel</option> 
                            </select>
                        </div>
                        <div class="modal-footer">
                            <a class="btn btn-danger" data-dismiss="modal">Cancelar</a>
                            <button type="submit" class="btn btn-default" name="action" value="EditarCurso">Editar Curso</button>
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
                            <h4>Agregar Curso</h4>                        
                        </div>
                        <div class="modal-body">
                            <div class="form-group">
                                <input type="text" name="codigo" class="form-control" id="codigoForm" placeholder="Código">
                            </div>
                            <div class="form-group">
                                <input type="text" name="nombre" class="form-control" id="nombreForm" placeholder="Nombre">
                            </div>
                            <div class="form-group">
                                <input type="number" name="creditos" class="form-control" id="creditosForm" placeholder="Créditos">
                            </div>
                            <div class="form-group">
                                <input type="number" name="horasSemanales" class="form-control" id="horasSemanalesForm" placeholder="Horas Semanales">
                            </div>
                            <select class="form-control" name="idCarrera" id="idCarreraForm" >
                                <option value="" >Seleccione la carrera</option>
                            <% for (Carrera c : allCarreras) { %>
                                <option value="<%= c.getCodigo() %>"><%= c.getNombre() %></option>
                            <% } %>
                            </select>
                            <br>
                            <select class="form-control" name="nivel" id="nivelForm" >
                                <option value="" >Seleccione el Nivel</option>
                                <option value="I NIVEL" >I Nivel</option>
                                <option value="II NIVEL" >II Nivel</option>
                                <option value="III NIVEL" >III Nivel</option>
                                <option value="IV NIVEL<" >IV Nivel</option> 
                            </select>
                        </div>
                        <div class="modal-footer">
                            <a class="btn btn-danger" data-dismiss="modal">Cancelar</a>
                            <button type="submit" class="btn btn-default" name="action" value="AgregarCurso">Agregar Curso</button>
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
            $('#creditosForm').tooltip({'trigger':'focus', 'title': 'Créditos'});
            $('#horasSemanalesForm').tooltip({'trigger':'focus', 'title': 'Horas Semanales'});
            $('#idCarreraForm').tooltip({'trigger':'focus', 'title': 'Seleccione la Carrera'});
            $('#nivelForm').tooltip({'trigger':'focus', 'title': 'Seleccione el Nivel'});
            $('#codigoEdit').tooltip({'trigger':'focus', 'title': 'Código'});
            $('#nombreEdit').tooltip({'trigger':'focus', 'title': 'Nombre'});
            $('#creditosEdit').tooltip({'trigger':'focus', 'title': 'Créditos'});
            $('#horasSemanalesEdit').tooltip({'trigger':'focus', 'title': 'Horas Semanales'});
            $('#idCarreraEdit').tooltip({'trigger':'focus', 'title': 'Seleccione la Carrera'});
            $('#nivelEdit').tooltip({'trigger':'focus', 'title': 'Seleccione el Nivel'});
            $('#codigoSearch').tooltip({'trigger':'focus', 'title': 'Código'});
            $('#nombreSearch').tooltip({'trigger':'focus', 'title': 'Nombre'});
        });
        function cargarDataModal(element){
            var id = element.id;
            var codigoCurso = document.getElementById("codigoCursoEdit");
            var codigoInput = document.getElementById("codigoEdit");
            var nombreInput = document.getElementById("nombreEdit");
            var creditosInput = document.getElementById("creditosEdit");
            var horasInput = document.getElementById("horasSemanalesEdit");
            var carreraInput = document.getElementById("idCarreraEdit");
            var nivelInput = document.getElementById("nivelEdit");
            
            var TD = element.parentNode;
            var TR = TD.parentNode;
            
            codigoCurso.value = id;
            codigoInput.value = id;
            nombreInput.value = TR.childNodes[3].innerHTML;
            creditosInput.value = TR.childNodes[5].innerHTML;
            horasInput.value = TR.childNodes[7].innerHTML;
            carreraInput.value = TR.childNodes[9].innerHTML;
//            fechaInput.value = TR.childNodes[11].innerHTML;
            nivelInput.value = TR.childNodes[13].innerHTML;
        }
    </script>
</html>
