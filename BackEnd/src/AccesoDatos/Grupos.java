
package AccesoDatos;

import LogicaNegocio.Grupo;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Grupos extends AccesoDatos {
    
    public int agregar(Grupo c) throws SQLException{
        String tableAndParams = "Grupo(numero,Horario_id,Curso_id,Profesor_cedula)";
        String values = "'%s','%s','%s','%s'";
        
        
        
        values = String.format(values,c.getNumero(),new Horarios().obtenerId(c.getHorario()),new Cursos().obtener(c.ge));
        return super.agregar(tableAndParams, values);
    }
    
    public int eliminar(Grupo c){
        String tableName = "Grupo";
        String query = "numero='%s'";
        query = String.format(query, c.getNumero());
        return super.eliminar(tableName, query);
    }
    
    public int actualizar(Grupo c){
        String tableName = "Grupo";
        String tableParams = "Horario_id='%s', Curso_id='%s', Matriuclador_cedula='%s', Profesor_cedula='%s'";
        tableParams = String.format(tableParams, c.getHorario.id,c.getMatriculador().getCedula(),c.getHorasSemanales(),c.getNivel(),c.getCarrera().getCodigo(),c.getCiclo().getAnio(),c.getCiclo().getNumero());
        return super.actualizar(tableName, tableParams);
    }
    
    private Grupo toGrupo(ResultSet rs) throws Exception {
        Grupo obj = new Grupo();
        obj.setCodigo(rs.getString("numero"));
        obj.setNombre(rs.getString("Horario_id"));
        obj.setCreditos(rs.getInt("Curso_id"));
        obj.setHorasSemanales(rs.getInt("Matriuclador_cedula"));
        obj.setNivel(rs.getString("Profesor_cedula"));
        obj.setCarrera(new Carreras().obtener(rs.getString("Carrera_codigo")));
        obj.setCiclo(new Ciclos().obtener(rs.getInt("Ciclo_anio"),rs.getInt("Ciclo_numero")));
        
        return obj;
    }
    
    public Grupo obtener(String numero) throws SQLException, Exception{
        String tableName = "Grupo";
        String param = "numero = '%s'";
        param = String.format(param, numero);
        ResultSet rs = super.obtener(tableName, param);
        if (rs.next()) {
            return toGrupo(rs);
        } else {
            return null;
        }
    }
    
    
    
}
