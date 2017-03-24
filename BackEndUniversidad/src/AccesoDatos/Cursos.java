
package AccesoDatos;

import LogicaNegocio.Curso;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class Cursos extends AccesoDatos {
    
    public int agregar(Curso c){
        String tableAndParams = "curso(codigo,nombre,creditos,horas_semanales,nivel,Carrera_codigo,Ciclo_anio,Ciclo_numero)";
        String values = "'%s','%s','%s','%s','%s','%s','%s','%s'";
        values = String.format(values,c.getCodigo(),c.getNombre(),c.getCreditos(),c.getHorasSemanales(),c.getNivel(),c.getCarrera().getCodigo(),c.getCiclo().getAnio(),c.getCiclo().getNumero());
        return super.agregar(tableAndParams, values);
    }
    
    public int eliminar(Curso c){
        String tableName = "curso";
        String query = "codigo='%s'";
        query = String.format(query, c.getCodigo());
        return super.eliminar(tableName, query);
    }
    
    public int actualizar(Curso c) throws SQLException{
        String tableName = "curso";
        String tableParams = "codigo='%s', nombre='%s', creditos='%s', horas_semanales='%s', nivel='%s', Carrera_codigo='%s', Ciclo_anio='%s', Ciclo_numero='%s' where id='%s'";
        tableParams = String.format(tableParams, c.getCodigo(), c.getNombre(),c.getCreditos(),c.getHorasSemanales(),c.getNivel(),c.getCarrera().getCodigo(),c.getCiclo().getAnio(),c.getCiclo().getNumero(),obtenerId(c));
        return super.actualizar(tableName, tableParams);
    }
    
    private Curso toCurso(ResultSet rs) throws Exception {
        Curso obj = new Curso();
        obj.setCodigo(rs.getString("codigo"));
        obj.setNombre(rs.getString("nombre"));
        obj.setCreditos(rs.getInt("creditos"));
        obj.setHorasSemanales(rs.getInt("horas_semanales"));
        obj.setNivel(rs.getString("nivel"));
        obj.setCarrera(new Carreras().obtener(rs.getString("Carrera_codigo")));
        obj.setCiclo(new Ciclos().obtener(rs.getInt("Ciclo_anio"),rs.getInt("Ciclo_numero")));
        
        return obj;
    }
    
    public Curso obtener(String codigo) throws SQLException, Exception{
        String tableName = "Curso";
        String param = "codigo = '%s'";
        param = String.format(param, codigo);
        ResultSet rs = super.obtener(tableName, param);
        if (rs.next()) {
            return toCurso(rs);
        } else {
            return null;
        }
    }
    
    public Curso obtenerPorId(int id) throws Exception{
        String tableName = "Curso";
        String param = "id = '%s'";
        param = String.format(param, id);
        ResultSet rs = super.obtener(tableName, param);
        if (rs.next()) {
            return toCurso(rs);
        } else {
            return null;
        }
    }
    
    
    public int obtenerId(Curso c) throws SQLException{
        
        //obtener id de Curso manualmente:
        String param2 = "codigo = '%s'";
        param2 = String.format(param2, c.getCodigo());
        String sql2 = "select * from Curso o where o." + param2;
        ResultSet rs2 = db.executeQuery(sql2);
        int idHorario=rs2.getInt("id");
        //fin de obtener id de Curso desde BD
        return idHorario;
    }
    
    
    
    
    
}
