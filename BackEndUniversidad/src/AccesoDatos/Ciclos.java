
package AccesoDatos;

import LogicaNegocio.Ciclo;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;


public class Ciclos extends AccesoDatos {
    
    public int agregar(Ciclo c){
        String tableAndParams = "Ciclo(anio,numero,fecha_Inicio,fecha_Finalizacion)";
        String values = "'%s','%s','%s','%s'";
        
        java.sql.Date fechaInicio = new java.sql.Date(c.getFechaInicio().getTimeInMillis());
        java.sql.Date fechaFinal = new java.sql.Date(c.getFechaFinalizacion().getTimeInMillis());
        values = String.format(values,c.getAnio(),c.getNumero(),fechaInicio,fechaFinal);
        return super.agregar(tableAndParams, values);
    }
    
    public int eliminar(Ciclo c){
        String tableName = "Ciclo";
        String query = "anio='%s' and numero='%s'";
        query = String.format(query, c.getAnio(),c.getNumero());
        return super.eliminar(tableName, query);
    }
    
    public int actualizar(Ciclo c){//actualizar fecha de inicio y fecha final
        String tableName = "ciclo";
        String tableParams = "fecha_Inicio='%s', fecha_Finalizacion='%s' where anio='%s' and numero='%s'";
        
        java.sql.Date fechaInicio = new java.sql.Date(c.getFechaInicio().getTimeInMillis());
        java.sql.Date fechaFinal = new java.sql.Date(c.getFechaFinalizacion().getTimeInMillis());
        tableParams = String.format(tableParams, fechaInicio, fechaFinal, c.getAnio(), c.getNumero());
        return super.actualizar(tableName, tableParams);
    }
    
    private Ciclo toCiclo(ResultSet rs) throws Exception {
        Ciclo obj = new Ciclo();
        obj.setAnio(rs.getInt("anio"));
        obj.setNumero(rs.getInt("numero"));
        Calendar horaIni = new GregorianCalendar();
        horaIni.setTime(rs.getDate("fecha_Inicio"));
        Calendar horaFin = new GregorianCalendar();
        horaFin.setTime(rs.getDate("fecha_Finalizacion"));
        obj.setFechaInicio(horaIni);
        obj.setFechaFinalizacion(horaFin);
        return obj;
    }
    
    public Ciclo obtenerPorAnioYNumero(int anio, int numero) throws SQLException, Exception{
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
    
    public Ciclo obtener(int anio) throws SQLException, Exception{
        String tableName = "ciclo";
        String param = "anio = '%s'";
        param = String.format(param, anio);
        ResultSet rs = super.obtener(tableName, param);
        if (rs.next()) {
            return toCiclo(rs);
        } else {
            return null;
        }
    }
    
    public ArrayList<Ciclo> obtenerTodo() throws Exception{
        
        String tableName = "Ciclo";
        ResultSet rs = super.obtenerTodo(tableName);
        ArrayList<Ciclo> lista=new ArrayList();
        while (rs.next()) {
            lista.add(toCiclo(rs));
        }
        return lista;
    }
    
}
