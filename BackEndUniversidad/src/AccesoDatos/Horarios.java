package AccesoDatos;

import LogicaNegocio.Horario;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class Horarios extends AccesoDatos {

    public int agregar(Horario c) {
        String tableAndParams = "Horario(dias,horaInicial,horaFinal)";
        String values = "'%s','%s','%s'";
        java.sql.Date horaInicial = new java.sql.Date(c.getHoraInicial().getTimeInMillis());
        java.sql.Date horaFinal = new java.sql.Date(c.getHoraFinal().getTimeInMillis());
        values = String.format(values, c.getDias(), horaInicial, horaFinal);
        return super.agregar(tableAndParams, values);
    }

    public int eliminar(Horario c) throws SQLException {
        String tableName = "Horario";
        String query = "dias='%s' and horaInicial='%s' and horaFinal='%s'";

        query = String.format(query, c.getDias(), c.getHoraInicial(), c.getHoraFinal());
        return super.eliminar(tableName, query);
    }

    public int actualizar(Horario c) throws SQLException {
        String tableName = "Horario";
        String tableParams = "dias='%s', horaInicial='%s', horaFinal='%s' where id='%s'";
        int idHorario = obtenerId(c);
        tableParams = String.format(tableParams, c.getDias(), c.getHoraInicial(), c.getHoraFinal(), idHorario);
        return super.actualizar(tableName, tableParams);
    }

    private Horario toCarrera(ResultSet rs) throws Exception {
        Horario obj = new Horario();
        obj.setDias(rs.getString("dias"));

        Calendar horaIni = new GregorianCalendar();
        horaIni.setTime(rs.getDate("horaInicial"));
        Calendar horaFin = new GregorianCalendar();
        horaFin.setTime(rs.getDate("horaFinal"));
        obj.setHoraInicial(horaIni);
        obj.setHoraFinal(horaFin);
        return obj;
    }

    public Horario obtener(String dias, Date horaInicial, Date horaFinal) throws SQLException, Exception {
        String tableName = "Horario";
        String param = "dias = '%s' and horaInicial = '%s' and horaFinal = '%s'";
        param = String.format(param, dias);
        ResultSet rs = super.obtener(tableName, param);
        if (rs.next()) {
            return toCarrera(rs);
        } else {
            return null;
        }
    }

    public int obtenerId(Horario h) throws SQLException {
        //obtener id de horario manualmente:
        String param2 = "dias = '%s' AND horaInicial = '%s' AND HoraFinal = '%s'";

        java.sql.Date horaInicial = new java.sql.Date(h.getHoraInicial().getTimeInMillis());
        java.sql.Date horaFinal = new java.sql.Date(h.getHoraFinal().getTimeInMillis());

        param2 = String.format(param2, h.getDias(), horaInicial, horaFinal);
        String sql2 = "select * from Horario o where o." + param2;
        ResultSet rs2 = db.executeQuery(sql2);
        int idHorario = -1;
        if (rs2.next()) {
            idHorario = rs2.getInt("id");
        }
        //fin de obtener id de horario desde BD
        return idHorario;
    }

    public Horario obtenerPorId(int id) throws Exception {
        String tableName = "Horario";
        String param = "id = '%s'";
        param = String.format(param, id);
        ResultSet rs = super.obtener(tableName, param);
        if (rs.next()) {
            return toCarrera(rs);
        } else {
            return null;
        }
    }

}
