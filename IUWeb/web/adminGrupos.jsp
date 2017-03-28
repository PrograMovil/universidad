<%-- 
    Document   : adminGrupos
    Created on : Mar 26, 2017, 9:24:33 PM
    Author     : SheshoVega
--%>

<%@page import="java.text.SimpleDateFormat"%>
<%@page import="LogicaNegocio.Grupo"%>
<%@page import="java.util.ArrayList"%>
<%@page import="LogicaNegocio.Profesor"%>
<%@page import="LogicaNegocio.Curso"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Grupos</title>
        <%@ include file="imports.jspf" %> 
        <meta name="viewport" content="width=device-width, initial-scale=1.0"/>
    </head>
    <body>
        <%@ include file="header.jspf" %>
        <%  Curso cursoCurrent = (Curso) session.getAttribute("cursoCurrent"); %>
        <%  ArrayList<Profesor> allProfesores = (ArrayList<Profesor>) session.getAttribute("allProfesores"); %>
        <%  ArrayList<Grupo> grupos = (ArrayList<Grupo>) session.getAttribute("grupos"); %>
        <div class="container">
            <div class="row">
                <div class="col-md-2">
                    <%@ include file="adminMenu.jspf" %>                    
                </div>
                <div class="col-md-10">
                    <div class="label label-danger col-md-12">${errores}</div>
                    <div class="row">
                        <div class="col-md-10" >
                            <h1>Curso: <%= cursoCurrent.getCodigo() %> <%= cursoCurrent.getNombre() %></h1>
                            <h2>Lista de Grupos:</h2>
                        </div>
                        <div class="col-md-2" >
                            <br><br>
                            <a href="#agregarModal" data-toggle="modal" class="btn btn-primary pull-right" >Agregar Grupo</a>
                        </div>
                    </div>
                    <div class="row">
                        <div class="col-md-12">
                            <table class="table" style="text-align: center">
                                <tr>
                                    <td>Número</td>
                                    <td hidden=""></td>
                                    <td hidden=""></td>
                                    <td hidden=""></td>
                                    <td>Horario</td>
                                    <td hidden=""></td>
                                    <td>Profesor</td>
                                    <td>Curso</td>
                                    <td>Ciclo</td>
                                    <td hidden=""></td>
                                    <td></td>
                                </tr>
                                <% for( Grupo g : grupos ){ %>
                                <tr>
                                    <td><%= g.getNumero() %></td>
                                    <td hidden=""><%= g.getHorario().getDias() %></td>
                                    <td hidden=""><%= g.getHorario().getHoraInicial() %></td>
                                    <td hidden=""><%= g.getHorario().getHoraFinal() %></td>
                                    <td><%= g.getHorario().toString() %></td>
                                    <td hidden="" ><%= g.getProfesor().getCedula() %></td>
                                    <td><%= g.getProfesor().getNombre() %></td>
                                    <td><%= g.getCurso().getNombre() %></td>
                                    <td><%= g.getCiclo().getNumero() %></td> 
                                    <td hidden=""><%= g.getCiclo().getAnio() %></td>
                                    <td><a href="#editarModal" data-toggle="modal" class="btn btn-default" id="<%= g.getId() %>" onclick="cargarDataModal(this)">Editar</a></td>
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
                            <h4>Editar Grupo</h4>                        
                        </div>
                        <div class="modal-body">
                            <input type="text" name="idGrupo" id="idGrupoEdit" hidden="" >
                            <input type="text" name="idCurso" id="codigoCursoEdit" value="<%= cursoCurrent.getCodigo() %>" hidden="" >
                            <input type="text" name="numeroCiclo" id="numeroCicloEdit" value="<%= cursoCurrent.getCiclo()%>" hidden="" >
                            <div class="form-group">
                                <input type="text" name="numero" class="form-control" id="numeroEdit" placeholder="Número">
                            </div>
                            <select class="selectpicker form-control" name="dias" multiple id="diasEdit" title="Seleccione los Días" >
                                <option value="Lunes">Lunes</option>
                                <option value="Martes">Martes</option>
                                <option value="Miércoles">Miércoles</option>
                                <option value="Jueves">Jueves</option>
                                <option value="Viernes">Viernes</option>
                            </select>
                            <br><br>
                            <div class="form-group">
                                <input type="text" name="horaInicio" class="form-control bootstrap-timepicker timepicker" id="horaInicioEdit" placeholder="hh:mm">
                            </div>
                            <div class="form-group">
                                <input type="text" name="horaFinal" class="form-control bootstrap-timepicker timepicker" id="horaFinalEdit" placeholder="hh:mm">
                            </div>
                            <select class="form-control" name="idProfesor" id="idProfesorEdit" >
                                <option value="" >Seleccione el Profesor</option>
                            <% for (Profesor p : allProfesores) { %>
                                <option value="<%= p.getCedula() %>"><%= p.getNombre() %></option>
                            <% } %>
                            </select>
                            <br>
                            <div class="form-group">
                                <label for="cursoEdit" >Curso de la Clase:</label>
                                <input type="text" class="form-control" id="cursoEdit" value="<%= cursoCurrent.getNombre()%>" disabled="" >
                            </div>
                            <div class="form-group">
                                <label for="cicloEdit" >Ciclo en que se imparte la Clase:</label>
                                <input type="text" class="form-control" id="cicloEdit" value="<%= cursoCurrent.getCiclo()%>" disabled="" >
                            </div>
                            <div class="form-group">
                                <input type="number" name="anioCiclo" class="form-control" id="anioCicloEdit" placeholder="Año en que se imparte la Clase">
                            </div>
                        </div>
                        <div class="modal-footer">
                            <a class="btn btn-danger" data-dismiss="modal">Cancelar</a>
                            <button type="submit" class="btn btn-default" name="action" value="EditarGrupo">Editar Grupo</button>
                        </div>
                    </form>                    
                </div>
            </div>
        </div>
        <div class="modal fade" id="agregarModal" role="dialog">
            <div class="modal-dialog">
                <div class="modal-content">
                    <form action="Servlet" method="get">
                        <div class="modal-header">
                            <h4>Agregar Grupo</h4>                        
                        </div>
                        <div class="modal-body">
                            <input type="text" name="idCurso" id="codigoCursoForm" value="<%= cursoCurrent.getCodigo() %>" hidden="" >
                            <input type="text" name="numeroCiclo" id="numeroCicloForm" value="<%= cursoCurrent.getCiclo()%>" hidden="" >
                            <div class="form-group">
                                <input type="text" name="numero" class="form-control" id="numeroForm" placeholder="Número">
                            </div>
                            <select class="selectpicker form-control" name="dias" multiple id="diasForm" title="Seleccione los Días" >
                                <option value="Lunes">Lunes</option>
                                <option value="Martes">Martes</option>
                                <option value="Miércoles">Miércoles</option>
                                <option value="Jueves">Jueves</option>
                                <option value="Viernes">Viernes</option>
                            </select>
                            <br><br>
                            <div class="form-group">
                                <input type="text" name="horaInicio" class="form-control bootstrap-timepicker timepicker" id="horaInicioForm" placeholder="hh:mm">
                            </div>
                            <div class="form-group">
                                <input type="text" name="horaFinal" class="form-control bootstrap-timepicker timepicker" id="horaFinalForm" placeholder="hh:mm">
                            </div>
                            <select class="form-control" name="idProfesor" id="idProfesorForm" >
                                <option value="" >Seleccione el Profesor</option>
                            <% for (Profesor p : allProfesores) { %>
                                <option value="<%= p.getCedula() %>"><%= p.getNombre() %></option>
                            <% } %>
                            </select>
                            <br>
                            <div class="form-group">
                                <label for="cursoForm" >Curso de la Clase:</label>
                                <input type="text" class="form-control" id="cursoForm" value="<%= cursoCurrent.getNombre()%>" disabled="" >
                            </div>
                            <div class="form-group">
                                <label for="cicloForm" >Ciclo en que se imparte la Clase:</label>
                                <input type="text" class="form-control" id="cicloForm" value="<%= cursoCurrent.getCiclo()%>" disabled="" >
                            </div>
                            <div class="form-group">
                                <input type="number" name="anioCiclo" class="form-control" id="anioCicloForm" placeholder="Año en que se imparte la Clase">
                            </div>
                        </div>
                        <div class="modal-footer">
                            <a class="btn btn-danger" data-dismiss="modal">Cancelar</a>
                            <button type="submit" class="btn btn-default" name="action" value="AgregarGrupo">Agregar Grupo</button>
                        </div>
                    </form>                    
                </div>
            </div>
        </div>
    </body>
    <script>
        $('#horaInicioForm').datetimepicker({
            format: 'HH:mm A',
            showClear: true
        });
        $('#horaFinalForm').datetimepicker({
            format: 'HH:mm A',
            showClear: true
        });
        $('#horaInicioEdit').datetimepicker({
            format: 'HH:mm A',
            showClear: true
        });
        $('#horaFinalEdit').datetimepicker({
            format: 'HH:mm A',
            showClear: true
        });
        $(document).ready(function () {
            $('#numeroForm').tooltip({'trigger':'focus', 'title': 'Número'});
            $('#horaInicioForm').tooltip({'trigger':'focus', 'title': 'Hora de Inicio de la Clase'});
            $('#horaFinalForm').tooltip({'trigger':'focus', 'title': 'Hora de Finalización de la Clase'});
            $('#idProfesorForm').tooltip({'trigger':'focus', 'title': 'Profesor que imparte la Clase'});
            $('#cursoForm').tooltip({'trigger':'focus', 'title': 'Curso de la Clase'});
            $('#cicloForm').tooltip({'trigger':'focus', 'title': 'Ciclo en que se imparte la Clase'});
            $('#anioCicloForm').tooltip({'trigger':'focus', 'title': 'Año en que se imparte la Clase'});
            
            $('#numeroEdit').tooltip({'trigger':'focus', 'title': 'Número'});
            $('#horaInicioEdit').tooltip({'trigger':'focus', 'title': 'Hora de Inicio de la Clase'});
            $('#horaFinalEdit').tooltip({'trigger':'focus', 'title': 'Hora de Finalización de la Clase'});
            $('#idProfesorEdit').tooltip({'trigger':'focus', 'title': 'Profesor que imparte la Clase'});
            $('#cursoEdit').tooltip({'trigger':'focus', 'title': 'Curso de la Clase'});
            $('#cicloEdit').tooltip({'trigger':'focus', 'title': 'Ciclo en que se imparte la Clase'});
            $('#anioCicloEdit').tooltip({'trigger':'focus', 'title': 'Año en que se imparte la Clase'});
            
            
            $('[data-toggle="tooltip"]').tooltip();
            
        });
        function cargarDataModal(element){
            var id = element.id;
            var idGrupo = document.getElementById("idGrupoEdit");
            var numeroInput = document.getElementById("numeroEdit");
            var diasInput = document.getElementById("diasEdit");
            var horaInicioInput = document.getElementById("horaInicioEdit");
            var horaFinalInput = document.getElementById("horaFinalEdit");
            var profesorInput = document.getElementById("idProfesorEdit");
            var anioCicloInput = document.getElementById("anioCicloEdit");
            
            var TD = element.parentNode;
            var TR = TD.parentNode;
            
            idGrupo.value = id;
            numeroInput.value = TR.childNodes[1].innerHTML;
            
            var diasStr = TR.childNodes[3].innerHTML;
            var diasArray = diasStr.split(" ");
            $('.selectpicker').selectpicker('val', diasArray);
            horaInicioInput.value = TR.childNodes[5].innerHTML;
            horaFinalInput.value = TR.childNodes[7].innerHTML;
            profesorInput.value = TR.childNodes[11].innerHTML;
            anioCicloInput.value = TR.childNodes[19].innerHTML;
        }
    </script>
</html>
