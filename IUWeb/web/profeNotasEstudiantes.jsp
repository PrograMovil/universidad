<%-- 
    Document   : profeNotasEstudiantes
    Created on : May 8, 2017, 11:22:01 PM
    Author     : SheshoVega
--%>

<%@page import="LogicaNegocio.Nota"%>
<%@page import="Control.Control"%>
<%@page import="LogicaNegocio.Grupo"%>
<%@page import="java.util.ArrayList"%>
<%@page import="LogicaNegocio.Estudiante"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <%@ include file="imports.jspf" %> 
        <title>Registro de Notas</title>
    </head>
    <body>
        <%@ include file="header.jspf" %>
        <%  
            ArrayList<Estudiante> listaEstudiantesDelGrupo = (ArrayList<Estudiante>) session.getAttribute("listaEstudiantesDelGrupo"); 
            Grupo grupoCurrent = (Grupo) session.getAttribute("grupoCurrent"); 
            Control ctrl = new Control();
        %>
        <div class="container">
            <div class="row">
                <div class="col-md-2">
                    <%@ include file="profeMenu.jspf" %>                    
                </div>
                <div class="col-md-10">
                    <div class="label label-danger col-md-12">${errores}</div>
                    <div class="row">
                        <h2>Notas de los estudiantes del grupo <%= grupoCurrent.getNumero() %>.</h2>
                        <div class="col-md-12" >
                            <table class="table" style="text-align: center">
                                <tr>
                                    <td>Cédula</td>
                                    <td>Nombre</td>
                                    <td>e-mail</td>
                                    <td>Carrera</td>
                                    <td>Nota</td>
                                </tr>                            
                            <%  for( Estudiante est : listaEstudiantesDelGrupo ){ %>
                                <tr>
                                    <td><%= est.getCedula() %></td>
                                    <td><%= est.getNombre() %></td>
                                    <td><%= est.getEmail() %></td>
                                    <td><%= est.getCarrera().getNombre() %></td>
                                    <td hidden="hidden"><%= grupoCurrent.getCurso().getCodigo() %></td>
                                    <% if(ctrl.getNota(est.getCedula(), grupoCurrent.getCurso().getId()) != null){ %>
                                    <td><%= ctrl.getNota(est.getCedula(), grupoCurrent.getCurso().getId()).getCalificacion() %></td>
                                    <% } else { %>
                                    <td>Sin asignar</td>
                                    <td><a href="#notaModal" data-toggle="modal" class="btn btn-default" id="<%= est.getCedula() %>" onclick="cargarDataModal(this)">Asignar Nota</a></td>
                                    <% } %>                                    
                                </tr>
                            <%  } %>
                            </table>
                        </div>
                    </div>
                </div>
            </div>
        </div>  
        <div class="modal fade" id="notaModal" role="dialog">
            <div class="modal-dialog">
                <div class="modal-content">
                    <form action="Servlet">
                        <div class="modal-header">
                            <h4>Asignar nota al Estudiante</h4>                        
                        </div>
                        <div class="modal-body">
                            <input type="text" name="idEstudiante" id="idEstudiante" hidden="" />
                            <input type="text" name="idCurso" id="idCurso" hidden="" />
                            <div class="form-group">
                                <input type="number" class="form-control" name="notaEstudiante" id="notaEstudiante" placeholder="Nota del estudiante">
                            </div>
                        </div>
                        <div class="modal-footer">
                            <a class="btn btn-danger" data-dismiss="modal">Cancelar</a>
                            <button type="submit" class="btn btn-default" name="action" value="RegistrarNota">Registrar Nota</button>
                        </div>
                    </form>                    
                </div>
            </div>
        </div>
    </body>
    <script>
        $(document).ready(function () {
            $('#notaEstudiante').tooltip({'trigger':'focus', 'title': 'Nota del estudiante'});
            $('[data-toggle="tooltip"]').tooltip();
        });
        function cargarDataModal(element){
            var id = element.id;
            var idCurso = document.getElementById("idCurso");
            var idEstudiante = document.getElementById("idEstudiante");
            
            var TD = element.parentNode;
            var TR = TD.parentNode;
            
            idEstudiante.value = id;
            idCurso.value = TR.childNodes[9].innerHTML;
            console.log(idCurso.value);
            console.log(idEstudiante.value);
        }
    </script>
</html>
