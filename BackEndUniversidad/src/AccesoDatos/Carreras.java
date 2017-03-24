
package AccesoDatos;

import LogicaNegocio.Carrera;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;


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
    
    public int actualizar(Carrera c) throws SQLException{
        String tableName = "Carrera";
        String tableParams = "codigo='%s', nombre='%s', titulo='%s' where id='%s'";
        tableParams = String.format(tableParams,c.getCodigo(),c.getNombre(),c.getTitulo(),obtenerId(c));
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
    
    public int obtenerId(Carrera c) throws SQLException{
        String param2 = "codigo='%s', nombre='%s', titulo='%s'";
        param2 = String.format(param2, c.getCodigo(), c.getNombre(), c.getTitulo());
        String sql2 = "select * from Carrera o where o." + param2;
        ResultSet rs2 = db.executeQuery(sql2);
        int id=rs2.getInt("id");
        return id;
    }
    
    public ArrayList<Carrera> obtenerTodo() throws Exception{
        
        String tableName = "Carrera";
        ResultSet rs = super.obtenerTodo(tableName);
        ArrayList<Carrera> lista=new ArrayList();
        while (rs.next()) {
            lista.add(toCarrera(rs));
        }
        return lista;
    }
    
    public ArrayList<Carrera> obtenerPorNombre(String nombre) throws Exception{
        String tableName = "Carrera";
        String param = "nombre = '%s'";
        param = String.format(param, nombre);
        ResultSet rs = super.obtener(tableName, param);
        ArrayList<Carrera> lista=new ArrayList();
        while (rs.next()) {
            lista.add(toCarrera(rs));
        }
        return lista;
    }
    
}
