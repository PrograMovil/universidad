
package AccesoDatos;

import LogicaNegocio.Ciclo;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;


public class Ciclos extends AccesoDatos{
    
    private ArrayList<Ciclo> ciclos;
    
    public Ciclos() {
        
    }

    public Ciclo buscar(String id){
        return null;
    }
    
    public boolean eliminar(String id){
        return true;
    }
    
    public boolean agregar(Ciclo ciclo) {
        ConexionBD conex = new ConexionBD();
        try {
            Statement estatuto = conex.getConnection().createStatement();
            estatuto.executeUpdate("INSERT INTO Ciclo(anio,numero,fecha_Inicio,fecha_Finalizacion) VALUES ('" + ciclo.getAnio() + "', '"
                    + ciclo.getNumero() + "', '" + ciclo.getFechaInicio() + "', '"
                    + ciclo.getFechaFinalizacion() + "')");
            
            
            PreparedStatement consulta = conex.getConnection().prepareStatement("SELECT DISTINCT LAST_INSERT_ID() FROM Curso");
            ResultSet res = consulta.executeQuery();
            ciclo.setId(Integer.parseInt(res.getString("id")));
            res.close();
            consulta.close();
            
            //ciclo.setId(0);
            estatuto.close();
            conex.desconectar();
            return true;

        } catch (SQLException e) {
            return false;
        }
    }
    
    
    
}