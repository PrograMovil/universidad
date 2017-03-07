
package AccesoDatos;

import LogicaNegocio.Curso;
import java.sql.ResultSet;
import java.sql.SQLException;

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
    
    public int actualizar(Curso c){
        String tableName = "curso";
        String tableParams = "nombre='%s', creditos='%s', horas_semanales='%s', nivel='%s', Carrera_codigo='%s', Ciclo_anio='%s', Ciclo_numero='%s' where codigo='%s'";
        tableParams = String.format(tableParams, c.getNombre(),c.getCreditos(),c.getHorasSemanales(),c.getNivel(),c.getCarrera().getCodigo(),c.getCiclo().getAnio(),c.getCiclo().getNumero());
        return super.actualizar(tableName, tableParams);
    }
    
    private Curso toCurso(ResultSet rs) throws Exception {
        Curso obj = new Curso();
        obj.setCodigo(rs.getString("codigo"));
        obj.setNombre(rs.getString("nombre"));
        obj.setCreditos(rs.getInt("creditos"));
        obj.setHorasSemanales(rs.getInt("horas_semanales"));
        obj.setNivel(rs.getString("nivel"));
        //obj.setCarrera();
        //obj.setCiclo(c);
        
        
        return obj;
    }
    
    public Curso obtener(String codigo) throws SQLException, Exception{
        String tableName = "curso";
        String param = "codigo = '%s'";
        param = String.format(param, codigo);
        ResultSet rs = super.obtener(tableName, param);
        if (rs.next()) {
            return toCurso(rs);
        } else {
            return null;
        }
    }
    
    
    
}
