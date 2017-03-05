
package AccesoDatos;

import LogicaNegocio.Grupo;
import java.util.ArrayList;

public class Grupos extends AccesoDatos{
    
    private ArrayList<Grupo> grupos;
    
    public Grupos() {
        
    }

    public Grupo buscar(String id){
        return null;
    }
    
    public boolean eliminar(String id){
        return true;
    }
    
    public boolean agregar(Grupo grupo) {
        return true;
    }
    
    
    
}
