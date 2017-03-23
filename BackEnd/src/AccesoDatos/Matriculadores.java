
package AccesoDatos;

import LogicaNegocio.Estudiante;
import LogicaNegocio.Matriculador;
import LogicaNegocio.Usuario;
import java.sql.ResultSet;
import java.sql.SQLException;


public class Matriculadores extends AccesoDatos{

    public Matriculadores() {
    }
    
    public int agregar(Matriculador c){
        String tableAndParams = "Matriculador(cedula,nombre,telefono,email,Usuario_id,Estudiante_cedula)";
        String values = "'%s','%s','%s','%s','%s','%s'";
        values = String.format(values,c.getCedula(),c.getNombre(),c.getTelefono(),c.getEmail(),c.getUsuario().getId(),c.getEstudiante().getCedula());
        return super.agregar(tableAndParams, values);
    }
    
    public int eliminar(Matriculador c){
        String tableName = "Matriculador";
        String query = "cedula='%s'";
        query = String.format(query, c.getCedula());
        return super.eliminar(tableName, query);
    }
    
    public int actualizar(Matriculador c){
        String tableName = "Matriculador";
        String tableParams = "nombre='%s', telefono='%s', email='%s', Usuario_id='%s', Estudiante_cedula='%s' where id='%s'";
        tableParams = String.format(tableParams, c.getNombre(),c.getTelefono(),c.getEmail(),c.getUsuario().getId(),c.getEstudiante().getCedula(), c.getCedula());
        return super.actualizar(tableName, tableParams);
    }
    
    private Matriculador toMatriculador(ResultSet rs) throws Exception {
        Matriculador obj = new Matriculador();
        obj.setCedula(rs.getString("cedula"));
        obj.setNombre(rs.getString("nombre"));
        obj.setTelefono(rs.getString("telefono"));
        obj.setEmail(rs.getString("email"));
        Estudiante e=new Estudiantes().obtener(rs.getString("Estudiante_cedula"));
        obj.setEstudiante(e);
        Usuario u=new Usuarios().obtener(rs.getString("Usuario_id"));
        obj.setUsuario(u);
        
        
        return obj;
    }
    
    public Matriculador obtener(String cedula) throws SQLException, Exception{
        String tableName = "Matriculador";
        String param = "cedula = '%s'";
        param = String.format(param, cedula);
        ResultSet rs = super.obtener(tableName, param);
        if (rs.next()) {
            return toMatriculador(rs);
        } else {
            return null;
        }
    }
    
    
    
}