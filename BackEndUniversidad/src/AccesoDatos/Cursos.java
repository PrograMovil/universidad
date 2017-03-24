
package AccesoDatos;

import LogicaNegocio.Carrera;
import LogicaNegocio.Curso;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class Cursos extends AccesoDatos {
    
    public int agregar(Curso c){
        String tableAndParams = "Curso(codigo,nombre,creditos,horas_semanales,nivel,Carrera_codigo)";
        String values = "'%s','%s','%s','%s','%s','%s','%s','%s'";
        values = String.format(values,c.getCodigo(),c.getNombre(),c.getCreditos(),c.getHorasSemanales(),c.getNivel(),c.getCarrera().getCodigo());
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
        String tableParams = "codigo='%s', nombre='%s', creditos='%s', horas_semanales='%s', nivel='%s', Carrera_codigo='%s' where id='%s'";
        tableParams = String.format(tableParams, c.getCodigo(), c.getNombre(),c.getCreditos(),c.getHorasSemanales(),c.getNivel(),c.getCarrera().getCodigo(),obtenerId(c));
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
        int idCurso=-1;
        if(rs2.next())
            idCurso=rs2.getInt("id");
        //fin de obtener id de Curso desde BD
        return idCurso;
    }
    
    
    public ArrayList<Curso> obtenerCursosPorNombre(String nombre) throws Exception{
        String tableName = "Curso";
        String param = "nombre = '%s'";
        param = String.format(param, nombre);
        ResultSet rs = super.obtener(tableName, param);
        ArrayList<Curso> lista=new ArrayList();
        while (rs.next()) {
            lista.add(toCurso(rs));
        }
        return lista;
    }
    
    public ArrayList<Curso> obtenerCursosPorCarrera(Carrera carrera) throws Exception{
        String tableName = "Curso";
        String param = "Carrera_id = '%s'";
        
        param = String.format(param, new Carreras().obtenerId(carrera));
        ResultSet rs = super.obtener(tableName, param);
        ArrayList<Curso> lista=new ArrayList();
        while (rs.next()) {
            lista.add(toCurso(rs));
        }
        return lista;
    }
    
    
}
