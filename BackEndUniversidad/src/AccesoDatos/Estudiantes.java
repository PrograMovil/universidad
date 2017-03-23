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
        String tableAndParams = "Estudiante(cedula,nombre,telefono,email,fechaNac,Usuario_id,Carrera_id)";
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
        String tableParams = "nombre='%s', telefono='%s', email='%s', fechaNac='%s', Usuario_id='%s', Carrera_id='%s', Ciclo_numero='%s' where cedula='%s'";
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
        Carrera ca=new Carreras().obtener(rs.getString("Carrera_id"));
        obj.setCarrera(ca);
        Usuario u=new Usuarios().obtener(rs.getString("Usuario_id"));
        obj.setUsuario(u);
        
        
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