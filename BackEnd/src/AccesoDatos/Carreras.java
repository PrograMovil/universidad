
package AccesoDatos;

import LogicaNegocio.Carrera;
import java.sql.ResultSet;
import java.sql.SQLException;


public class Carreras extends AccesoDatos {
    
    public int agregar(Carrera c){
        String tableAndParams = "Carrera(codigo,nombre,titulo)";
        String values = "'%s','%s','%s'";
        values = String.format(values,c.getCodigo(),c.getNombre(),c.getTitulo());
        return super.agregar(tableAndParams, values);
    }
    
    public int eliminar(Carrera c){
        String tableName = "Carrera";
        String query = "codigo='%s'";
        query = String.format(query, c.getCodigo());
        return super.eliminar(tableName, query);
    }
    
    public int actualizar(Carrera c){
        String tableName = "Carrera";
        String tableParams = "nombre='%s', titulo='%s' where codigo='%s'";
        tableParams = String.format(tableParams, c.getNombre(), c.getTitulo(), c.getCodigo());
        return super.actualizar(tableName, tableParams);
    }
    
    private Carrera toCarrera(ResultSet rs) throws Exception {
        Carrera obj = new Carrera();
        obj.setCodigo(rs.getString("codigo"));
        obj.setNombre(rs.getString("nombre"));
        obj.setTitulo(rs.getString("titulo"));
        return obj;
    }
    
    public Carrera obtener(String codigo) throws SQLException, Exception{
        String tableName = "Carrera";
        String param = "codigo = '%s'";
        param = String.format(param, codigo);
        ResultSet rs = super.obtener(tableName, param);
        if (rs.next()) {
            return toCarrera(rs);
        } else {
            return null;
        }
    }
    
}
