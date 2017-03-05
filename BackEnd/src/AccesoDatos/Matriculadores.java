
package AccesoDatos;

import LogicaNegocio.Matriculador;
import java.util.ArrayList;


public class Matriculadores extends AccesoDatos{
    
    private ArrayList<Matriculador> matriculadores;
    
    public Matriculadores() {
        
    }

    public Matriculador buscar(String id){
        return null;
    }
    
    public boolean eliminar(String id){
        return true;
    }
    
    public boolean agregar(Matriculador matriculador) {
        return true;
    }
    
    
    
}