
package AccesoDatos;

import LogicaNegocio.Curso;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class Cursos extends AccesoDatos{
    
    private ArrayList<Curso> cursos;
    
    public Cursos() {
        
    }

    public Curso buscar(String id){
        return null;
    }
    
    public boolean eliminar(String id){
        return true;
    }
    
    public boolean agregar(Curso curso) {
        ConexionBD conex = new ConexionBD();
        try {
            Statement estatuto = conex.getConnection().createStatement();
            estatuto.executeUpdate("INSERT INTO Curso VALUES ('" + curso.getCodigo() + "', '"
                    + curso.getNombre() + "', '" + curso.getCreditos() + "', '"
                    + curso.getHorasSemanales() + "', '" + curso.getNivel() + "', '" + curso.getCiclo().getId() + "')");
            estatuto.close();
            conex.desconectar();
            
            return true;

        } catch (SQLException e) {
            return false;
        }
    }
    
    
    
}
