
package AccesoDatos;

import LogicaNegocio.Grupo;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class Grupos extends AccesoDatos {
    
    public int agregar(Grupo c) throws SQLException{
        String query = "'%s','%s','%s','%s','%s','%s'";
        String tableAndParams="Grupo(numero,Horario_id,Curso_id,Profesor_cedula,Ciclo_anio,Ciclo_numero)";
        
        
        
        query = String.format(query,c.getNumero(),new Horarios().obtenerId(c.getHorario()),new Cursos().obtenerId(c.getCurso()),c.getProfesor().getCedula(),c.getCiclo().getAnio(),c.getCiclo().getNumero());
        return super.agregar(tableAndParams, query);
    }
    
    public int eliminar(Grupo c) throws SQLException{
        String tableName = "Grupo";
        String query = "id = '%s'";
        query = String.format(query, obtenerId(c));
        return super.eliminar(tableName, query);
    }
    
    public int actualizar(Grupo c) throws SQLException{
        String tableName = "Grupo";
        String tableParams = "numero = '%s',Horario_id='%s', Curso_id='%s', Profesor_cedula='%s', Ciclo_anio='%s', Ciclo_numero='%s' where id='%s'";
        tableParams = String.format(tableParams, c.getNumero(), new Horarios().obtenerId(c.getHorario()), new Cursos().obtenerId(c.getCurso()), c.getProfesor().getCedula(),c.getCiclo().getAnio(),c.getCiclo().getNumero(), obtenerId(c));
        return super.actualizar(tableName, tableParams);
    }
    
    private Grupo toGrupo(ResultSet rs) throws Exception {
        Grupo obj = new Grupo();
        obj.setNumero(rs.getInt("numero"));
        obj.setHorario(new Horarios().obtenerPorId(rs.getInt("Horario_id")));
        obj.setCurso(new Cursos().obtenerPorId(rs.getInt("id")));
        obj.setProfesor(new Profesores().obtener(rs.getString("Profesor_cedula")));
        obj.setCiclo(new Ciclos().obtener(rs.getInt("Ciclo_anio"),rs.getInt("Ciclo_numero")));
        return obj;
    }
    
    public Grupo obtener(Grupo c) throws SQLException, Exception{
        String tableName = "Grupo";
        String param = "id = '%s'";
        param = String.format(param, obtenerId(c));
        ResultSet rs = super.obtener(tableName, param);
        if (rs.next()) {
            return toGrupo(rs);
        } else {
            return null;
        }
    }
    
    
    public int obtenerId(Grupo c) throws SQLException{
        
        //obtener id de Grupo manualmente:
        String param2 = "numero = '%s' and Horario_id = '%s' and Curso_id = '%s' and Profesor_cedula = '%s' and Ciclo_anio='%s' and Ciclo_numero='%s'";
        param2 = String.format(param2, c.getNumero(), new Horarios().obtenerId(c.getHorario()), new Cursos().obtenerId(c.getCurso()), c.getProfesor().getCedula(),c.getCiclo().getAnio(),c.getCiclo().getNumero());
        String sql2 = "select * from Grupo where " + param2;
        ResultSet rs2 = db.executeQuery(sql2);
        int id=-1;
        if (rs2.next()) {
            id=rs2.getInt("id");
        }
        //fin de obtener id de Gupo desde BD
        return id;
    }
    
    
    public ArrayList<Grupo> gruposPorProfesor(String cedula) throws Exception{
        
        String tableName = "Grupo";
        String param = "Profesor_cedula = '%s'";
        param = String.format(param, cedula);
        ResultSet rs = super.obtener(tableName, param);
        ArrayList<Grupo> lista=new ArrayList<>();
        while (rs.next()) {
            Grupo obj = new Grupo();
            obj.setNumero(rs.getInt("numero"));
            obj.setHorario(new Horarios().obtenerPorId(rs.getInt("Horario_id")));
            obj.setCurso(new Cursos().obtenerPorId(rs.getInt("id")));
            obj.setProfesor(new Profesores().obtener(rs.getString("Profesor_cedula")));
            lista.add(obj);
            
        }
        return lista;
    }

    
    
}
