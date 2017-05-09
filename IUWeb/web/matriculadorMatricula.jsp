<%-- 
    Document   : matricula
    Created on : Mar 27, 2017, 4:08:39 PM
    Author     : SheshoVega
--%>

<%@page import="LogicaNegocio.Ciclo"%>
<%@page import="LogicaNegocio.Grupo"%>
<%@page import="java.util.ArrayList"%>
<%@page import="LogicaNegocio.Curso"%>
<%@page import="LogicaNegocio.Carrera"%>
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
        <%  Estudiante estudianteCurrent = (Estudiante) session.getAttribute("estudianteCurrent"); 
          Carrera carreraEstudianteCurrent = (Carrera) session.getAttribute("carreraEstudianteCurrent"); 
          Ciclo cicloDefault = (Ciclo) session.getAttribute("cicloDefault");
          ArrayList< ArrayList<Grupo> > listaGrupos = (ArrayList< ArrayList<Grupo> >) session.getAttribute("listaGrupos");
          ArrayList< Grupo > gruposMatriculados = (ArrayList< Grupo>) session.getAttribute("gruposMatriculados");
        %>
        <div class="container">
            <div class="row">
                <div class="col-md-2">
                    <%@ include file="matriculadorMenu.jspf" %>                    
                </div>
                <div class="col-md-10">
                    <div class="label label-danger col-md-12">${errores}</div>
                    
                    <div class="row">
                        <h2>Matrícula de <%= estudianteCurrent.getNombre() %> </h2>
                        <div class="col-md-12" >
                            <%if(gruposMatriculados.size() == 0) {%>
                            <h4 style="color:red; width: 100%; text-align: center;">Sin matricula.</h4>
                            <% } else { %>
                            <table class="table" style="text-align: center">
                                <tr>
                                    <td>Curso</td>
                                    <td># Grupo</td>
                                    <td>Horario</td>
                                    <td>Profesor</td>
                                    <td>Ciclo</td>
                                    <td></td>
                                </tr>                            
                            <%  for( Grupo g : gruposMatriculados ){ %>
                                <tr>
                                    <td><%= g.getCurso().getNombre() %></td>
                                    <td><%= g.getNumero() %></td>
                                    <td><%= g.getHorario().toString() %></td>
                                    <td><%= g.getProfesor().getNombre() %></td>
                                    <td><%= g.getCiclo().getNumero() %></td>
                                <form action="Servlet" method="POST">
                                    <input type="text" name="idEstudiante" value="<%= estudianteCurrent.getCedula() %>" hidden="" />
                                    <input type="text" name="idGrupo" value="<%= g.getId() %>" hidden="" />
                                    <td><button type="submit" class="btn btn-default" data-toggle="tooltip" title="Retirar" id="matriculaBtn" name="action" value="RetirarEnMatriculador">Retirar</button></td>
                                </form>
                                </tr>
                            <% } %>                            
                            </table>
                            <% } %>   
                        </div>
                        <div class="col-md-12" >
                            <h2>Lista de Grupos: </h2>
                            <table class="table" style="text-align: center">
                                <tr>
                                    <td>Curso</td>
                                    <td># Grupo</td>
                                    <td>Horario</td>
                                    <td>Profesor</td>
                                    <td>Ciclo</td>
                                    <td></td>
                                </tr>                            
                            <%  for( int i=0; i<listaGrupos.size(); i++ ){ 
                                    ArrayList<Grupo> gruposList = listaGrupos.get(i);
                                    for( int j=0; j<gruposList.size(); j++ ){ 
                                        Grupo grupo = gruposList.get(j);
                                        if((grupo.getCiclo().getNumero().equals(cicloDefault.getNumero())) && (grupo.getCiclo().getAnio() == (cicloDefault.getAnio()))){
                            %>
                                <tr>
                                    <td><%= grupo.getCurso().getNombre() %></td>
                                    <td><%= grupo.getNumero() %></td>
                                    <td><%= grupo.getHorario().toString() %></td>
                                    <td><%= grupo.getProfesor().getNombre() %></td>
                                    <td><%= grupo.getCiclo().getNumero() %></td>
                                <form action="Servlet" method="POST">
                                    <input type="text" name="idEstudiante" value="<%= estudianteCurrent.getCedula() %>" hidden="" />
                                    <input type="text" name="idGrupo" value="<%= grupo.getId() %>" hidden="" />
                                    <td><button type="submit" class="btn btn-default" data-toggle="tooltip" title="Matricular" id="matriculaBtn" name="action" value="MatricularEnMatriculador">Matrícular</button></td>
                                </form>
                                </tr>
                            <%      }
                                } 
                             } %>
                            </table>
                        </div>
                    </div>
                </div>
            </div>
        </div>                    
    </body>
</html>
