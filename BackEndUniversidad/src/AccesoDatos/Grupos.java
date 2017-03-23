
package AccesoDatos;

import LogicaNegocio.Grupo;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Grupos extends AccesoDatos {
    
    public int agregar(Grupo c) throws SQLException{
        String tableAndParams = "Grupo(numero,Horario_id,Curso_id,Profesor_cedula)";
        String values = "'%s','%s','%s','%s'";
        
        
        
        values = String.format(values,c.getNumero(),new Horarios().obtenerId(c.getHorario()),new Cursos().obtenerId(c.getCurso()));
        return super.agregar(tableAndParams, values);
    }
    
    public int eliminar(Grupo c) throws SQLException{
        String tableName = "Grupo";
        String query = "numero = '%s',Horario_id = '%s',Curso_id = '%s',Profesor_cedula = '%s'";
        query = String.format(query, c.getNumero(), new Horarios().obtenerId(c.getHorario()), new Cursos().obtenerId(c.getCurso()), c.getProfesor().getCedula());
        return super.eliminar(tableName, query);
    }
    
    public int actualizar(Grupo c) throws SQLException{
        String tableName = "Grupo";
        String tableParams = "numero = '%s',Horario_id='%s', Curso_id='%s', Profesor_cedula='%s' where id='%s'";
        tableParams = String.format(tableParams, c.getNumero(), new Horarios().obtenerId(c.getHorario()), new Cursos().obtenerId(c.getCurso()), c.getProfesor().getCedula(), obtenerId(c));
        return super.actualizar(tableName, tableParams);
    }
    
    private Grupo toGrupo(ResultSet rs) throws Exception {
        Grupo obj = new Grupo();
        obj.setNumero(rs.getInt("numero"));
        obj.setHorario(new Horarios().obtenerPorId(rs.getInt("Horario_id")));
        obj.setCurso(new Cursos().obtenerPorId(rs.getInt("id")));
        obj.setProfesor(new Profesores().obtener(rs.getString("Profesor_cedula")));
        return obj;
    }
    
    public Grupo obtener(String numero) throws SQLException, Exception{
        String tableName = "Grupo";
        String param = "numero = '%s' AND Horario_id = '%s' AND Curso_id = '%s' AND Profesor_cedula = '%s'";
        param = String.format(param, numero);
        ResultSet rs = super.obtener(tableName, param);
        if (rs.next()) {
            return toGrupo(rs);
        } else {
            return null;
        }
    }
    
    
    public int obtenerId(Grupo c) throws SQLException{
        
        //obtener id de Grupo manualmente:
        String param2 = "numero = '%s',Horario_id = '%s',Curso_id = '%s',Profesor_cedula = '%s'";
        param2 = String.format(param2, c.getNumero(), new Horarios().obtenerId(c.getHorario()), new Cursos().obtenerId(c.getCurso()), c.getProfesor().getCedula());
        String sql2 = "select * from Grupo o where o." + param2;
        ResultSet rs2 = db.executeQuery(sql2);
        int id=rs2.getInt("id");
        //fin de obtener id de Gupo desde BD
        return id;
    }
    
    
}
