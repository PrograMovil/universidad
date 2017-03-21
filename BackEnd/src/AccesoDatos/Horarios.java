
package AccesoDatos;

import LogicaNegocio.Horario;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

public class Horarios  extends AccesoDatos {
    
    public int agregar(Horario c){
        String tableAndParams = "Horario(dias,horaInicial,horaFinal)";
        String values = "'%s','%s','%s'";
        java.sql.Date horaInicial = new java.sql.Date(c.getHoraInicial().getTime()); //conversion de java date a sql date
        java.sql.Date horaFinal = new java.sql.Date(c.getHoraFinal().getTime());
        values = String.format(values,c.getDias(),horaInicial,horaFinal);
        return super.agregar(tableAndParams, values);
    }
    
    public int eliminar(Horario c) throws SQLException{
        String tableName = "Horario";
        String query = "idHorario='%s'";
        
        int idHorario=obtenerId(c);
        
        query = String.format(query, idHorario);
        return super.eliminar(tableName, query);
    }
    
    public int actualizar(Horario c) throws SQLException{
        String tableName = "Horario";
        String tableParams = "dias='%s', horaInicial='%s', horaFinal='%s' where id='%s'";
        int idHorario=obtenerId(c);
        tableParams = String.format(tableParams, c.getDias(), c.getHoraInicial(), c.getHoraFinal(),idHorario);
        return super.actualizar(tableName, tableParams);
    }
    
    private Horario toCarrera(ResultSet rs) throws Exception {
        Horario obj = new Horario();
        obj.setDias(rs.getString("dias"));
        Date horaIni = rs.getTimestamp("horaInicial");
        Date horaFin = rs.getTimestamp("horaFinal");
        obj.setHoraInicial(horaIni);
        obj.setHoraFinal(horaFin);
        return obj;
    }
    
    public Horario obtener(String dias, Date horaInicial, Date horaFinal) throws SQLException, Exception{
        String tableName = "Horario";
        String param = "dias = '%s', horaInicial = '%s', horaFinal = '%s'";
        param = String.format(param, dias);
        ResultSet rs = super.obtener(tableName, param);
        if (rs.next()) {
            return toCarrera(rs);
        } else {
            return null;
        }
    }
    
    public int obtenerId(Horario h) throws SQLException{
        //obtener id de horario manualmente:
        String param2 = "dias = '%s', horaInicial = '%s', HoraFinal = '%s'";
        param2 = String.format(param2, h.getDias(),h.getHoraInicial(),h.getHoraFinal());
        String sql2 = "select * from Horario o where o." + param2;
        ResultSet rs2 = db.executeQuery(sql2);
        int idHorario=rs2.getInt("id");
        //fin de obtener id de horario desde BD
        return idHorario;
    }
    
}
