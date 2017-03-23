package AccesoDatos;

import LogicaNegocio.Profesor;
import LogicaNegocio.Usuario;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Profesores extends AccesoDatos{

    public Profesores() {
    }

    public int agregar(Profesor c){
        String tableAndParams = "Profesor(cedula,nombre,telefono,email,Usuario_id)";
        String values = "'%s','%s','%s','%s','%s'";
        values = String.format(values,c.getCedula(),c.getNombre(),c.getTelefono(),c.getEmail(),c.getUsuario().getId());
        return super.agregar(tableAndParams, values);
    }
    
    public int eliminar(Profesor c){
        String tableName = "Profesor";
        String query = "cedula='%s'";
        query = String.format(query, c.getCedula());
        return super.eliminar(tableName, query);
    }
    
    public int actualizar(Profesor c){
        String tableName = "Profesor";
        String tableParams = "nombre='%s', telefono='%s', email='%s', Usuario_id='%s' where cedula='%s'";
        tableParams = String.format(tableParams, c.getNombre(),c.getTelefono(),c.getEmail(),c.getUsuario().getId());
        return super.actualizar(tableName, tableParams);
    }
    
    private Profesor toProfesor(ResultSet rs) throws Exception {
        Profesor obj = new Profesor();
        obj.setCedula(rs.getString("cedula"));
        obj.setNombre(rs.getString("nombre"));
        obj.setTelefono(rs.getString("telefono"));
        obj.setEmail(rs.getString("email"));
        Usuario u=new Usuarios().obtener(rs.getString("Usuario_id"));
        obj.setUsuario(u);
        
        
        return obj;
    }
    
    public Profesor obtener(String cedula) throws SQLException, Exception{
        String tableName = "Profesor";
        String param = "cedula = '%s'";
        param = String.format(param, cedula);
        ResultSet rs = super.obtener(tableName, param);
        if (rs.next()) {
            return toProfesor(rs);
        } else {
            return null;
        }
    }
    
    
    
}