package AccesoDatos;

import LogicaNegocio.Carrera;
import LogicaNegocio.Estudiante;
import LogicaNegocio.Usuario;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;


public class Estudiantes extends AccesoDatos{

    public Estudiantes() {
    }
    
    public int agregar(Estudiante c){
        String tableAndParams = "Estudiante(cedula,nombre,telefono,email,fechaNac,Usuario_id,Carrera_Codigo)";
        String values = "'%s','%s','%s','%s','%s','%s','%s'";
        java.sql.Date fechaNa = new java.sql.Date(c.getFechaNac().getTime());
        values = String.format(values,c.getCedula(),c.getNombre(),c.getTelefono(),c.getEmail(),fechaNa,c.getUsuario().getId(),c.getCarrera().getCodigo());
        return super.agregar(tableAndParams, values);
    }
    
    public int eliminar(Estudiante c){
        String tableName = "Estudiante";
        String query = "cedula='%s'";
        query = String.format(query, c.getCedula());
        return super.eliminar(tableName, query);
    }
    
    public int actualizar(Estudiante c){
        String tableName = "Estudiante";
        String tableParams = "nombre='%s', telefono='%s', email='%s', fechaNac='%s', Usuario_id='%s', Carrera_Codigo='%s', Ciclo_numero='%s' where cedula='%s'";
        java.sql.Date fechaNa = new java.sql.Date(c.getFechaNac().getTime());
        tableParams = String.format(tableParams, c.getNombre(),c.getTelefono(),c.getEmail(),fechaNa,c.getUsuario().getId(),c.getCarrera().getCodigo());
        return super.actualizar(tableName, tableParams);
    }
    
    private Estudiante toEstudiante(ResultSet rs) throws Exception {
        Estudiante obj = new Estudiante();
        obj.setCedula(rs.getString("cedula"));
        obj.setNombre(rs.getString("nombre"));
        obj.setTelefono(rs.getString("telefono"));
        obj.setEmail(rs.getString("email"));
        Date fechaNa = rs.getTimestamp("fechaNac");
        obj.setFechaNac(fechaNa);
        obj.setCarrera(rs.getObject("Carrera_Codigo", Carrera.class));
        obj.setUsuario(rs.getObject("Usuario_id", Usuario.class));
        
        
        return obj;
    }
    
    public Estudiante obtener(String cedula) throws SQLException, Exception{
        String tableName = "Estudiante";
        String param = "cedula = '%s'";
        param = String.format(param, cedula);
        ResultSet rs = super.obtener(tableName, param);
        if (rs.next()) {
            return toEstudiante(rs);
        } else {
            return null;
        }
    }
    
    
    
}
