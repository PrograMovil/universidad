
package AccesoDatos;

import LogicaNegocio.Curso;
import LogicaNegocio.Estudiante;
import LogicaNegocio.Nota;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Notas  extends AccesoDatos {
    
    public int agregar(Nota c) throws SQLException{
        String tableAndParams = "Nota(calificacion,Estudiante_cedula,Curso_id)";
        String values = "'%s','%s','%s'";
        
        //obtener id del curso manualmente:
        String param = "codigo = '%s'";
        param = String.format(param, c.getCurso().getCodigo());
        String sql = "select * from Curso o where o." + param;
        ResultSet rs = db.executeQuery(sql);
        int idCurso=rs.getInt("id");
        //fin de obtener id de curso desde BD
        
        values = String.format(values,c.getCalificacion(),c.getEstudiante().getCedula(),idCurso);
        return super.agregar(tableAndParams, values);
    }
    
    public int eliminar(Nota c) throws SQLException{
        String tableName = "Nota";
        
        //**********************************************************************
        //obtener id del curso manualmente:
        String param = "codigo = '%s'";
        param = String.format(param, c.getCurso().getCodigo());
        String sql = "select * from Curso o where o." + param;
        ResultSet rs = db.executeQuery(sql);
        int idCurso=rs.getInt("id");
        //fin de obtener id de curso desde BD
        
        //obtener id de nota manualmente:
        String param2 = "calificacion = '%s', Estudiante_cedula = '%s', Curso_id = '%s'";
        param2 = String.format(param2, c.getCalificacion(),c.getEstudiante().getCedula(),idCurso);
        String sql2 = "select * from Nota o where o." + param2;
        ResultSet rs2 = db.executeQuery(sql2);
        int idNota=rs2.getInt("id");
        //fin de obtener id de nota desde BD
        //**********************************************************************
        
        String query = "id="+idNota;
        return super.eliminar(tableName, query);
    }
    
    public int actualizar(Nota c) throws SQLException{
        String tableName = "Nota";
        String tableParams = "calificacion='%s' where id='%s'";
        
        //**********************************************************************
        //obtener id del curso manualmente (se ocupa para obtener el id de nota)
        String param = "codigo = '%s'";
        param = String.format(param, c.getCurso().getCodigo());
        String sql = "select * from Curso o where o." + param;
        ResultSet rs = db.executeQuery(sql);
        int idCurso=rs.getInt("id");
        //fin de obtener id de curso desde BD
        
        //obtener id de nota manualmente:
        String param2 = "calificacion = '%s', Estudiante_cedula = '%s', Curso_id = '%s'";
        param2 = String.format(param2, c.getCalificacion(),c.getEstudiante().getCedula(),idCurso);
        String sql2 = "select * from Nota o where o." + param2;
        ResultSet rs2 = db.executeQuery(sql2);
        int idNota=rs2.getInt("id");
        //fin de obtener id de nota desde BD
        //**********************************************************************
        
        
        tableParams = String.format(tableParams, c.getCalificacion(), idNota);
        return super.actualizar(tableName, tableParams);
    }
    
    private Nota toCiclo(ResultSet rs) throws Exception {
        Nota obj = new Nota();
        
        obj.setCalificacion(rs.getFloat("calificacion"));
        obj.setEstudiante(new Estudiantes().obtener(rs.getString("Estudiante_cedula")));
        obj.setCurso(new Cursos().obtener(rs.getString("Curso_id")));
        return obj;
    }
    
    public Nota obtener(float calificacion, String Estudiante_cedula, String Codigo_Curso) throws SQLException, Exception{
        String tableName = "Nota";
        String param = "calificacion = '%s' and Estudiante_cedula= '%s' and Curso_id= '%s'";
        
        
        //obtener id del curso manualmente:
        String param2 = "codigo = '%s'";
        param2 = String.format(param2, Codigo_Curso);
        String sql = "select * from Curso o where o." + param2;
        ResultSet rs2 = db.executeQuery(sql);
        int idCurso=rs2.getInt("id");
        //fin de obtener id de curso desde BD
        
        param = String.format(param, Estudiante_cedula, idCurso);
        ResultSet rs = super.obtener(tableName, param);
        if (rs.next()) {
            return toCiclo(rs);
        } else {
            return null;
        }
    }
    
}

