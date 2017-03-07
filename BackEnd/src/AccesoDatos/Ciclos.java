
package AccesoDatos;

import LogicaNegocio.Ciclo;
import java.sql.ResultSet;
import java.sql.SQLException;


public class Ciclos extends AccesoDatos {
    
    public int agregar(Ciclo c){
        String tableAndParams = "ciclo(anio,numero,fecha_Inicio,fecha_Finalizacion)";
        String values = "'%s','%s','%s','%s'";
        
        java.sql.Date fechaInicio = new java.sql.Date(c.getFechaInicio().getTime());
        java.sql.Date fechaFinal = new java.sql.Date(c.getFechaFinalizacion().getTime());
        values = String.format(values,c.getAnio(),c.getNumero(),fechaInicio,fechaFinal);
        return super.agregar(tableAndParams, values);
    }
    
    public int eliminar(Ciclo c){
        String tableName = "ciclo";
        String query = "anio="+c.getAnio()+" AND numero="+c.getNumero();
        return super.eliminar(tableName, query);
    }
    
    public int actualizar(Ciclo c){//actualizar fecha de inicio y fecha final
        String tableName = "ciclo";
        String tableParams = "fecha_Inicio='%s', fecha_Finalizacion='%s' where anio='%s' and numero='%s'";
        
        java.sql.Date fechaInicio = new java.sql.Date(c.getFechaInicio().getTime());
        java.sql.Date fechaFinal = new java.sql.Date(c.getFechaFinalizacion().getTime());
        tableParams = String.format(tableParams, fechaInicio, fechaFinal, c.getAnio(), c.getNumero());
        return super.actualizar(tableName, tableParams);
    }
    
    private Ciclo toCiclo(ResultSet rs) throws Exception {
        Ciclo obj = new Ciclo();
        obj.setAnio(rs.getInt("anio"));
        obj.setNumero(rs.getInt("numero"));
        java.util.Date fechaInicio = rs.getTimestamp("fecha_Inicio");
        java.util.Date fechaFinal = rs.getTimestamp("fecha_Finalizacion");
        obj.setFechaInicio(fechaInicio);
        obj.setFechaFinalizacion(fechaFinal);
        return obj;
    }
    
    public Ciclo obtener(int anio, int numero) throws SQLException, Exception{
        String tableName = "ciclo";
        String param = "anio = '%s' and numero= '%s'";
        param = String.format(param, anio, numero);
        ResultSet rs = super.obtener(tableName, param);
        if (rs.next()) {
            return toCiclo(rs);
        } else {
            return null;
        }
    }
    
}
