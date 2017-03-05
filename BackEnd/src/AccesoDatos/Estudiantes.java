package AccesoDatos;

import LogicaNegocio.Estudiante;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class Estudiantes extends AccesoDatos{
    
    private ArrayList<Estudiante> estudiantes;
    
    public Estudiantes() {
        
    }

    public Estudiante buscar(String id){
        return null;
    }
    
    public boolean eliminar(String id){
        return true;
    }
    
    public boolean agregar(Estudiante estudiante) {
        ConexionBD conex = new ConexionBD();
        try {
            Statement estatuto = conex.getConnection().createStatement();
            estatuto.executeUpdate("INSERT INTO Estudiante VALUES ('" + estudiante.getCedula() + "', '"
                    + estudiante.getNombre() + "', '" + estudiante.getTelefono() + "', '"
                    + estudiante.getEmail() + "', '" + estudiante.getFechaNac() + "')");
            estatuto.close();
            conex.desconectar();
            
            return true;

        } catch (SQLException e) {
            return false;
        }
        
        
    }
    
    
    
}