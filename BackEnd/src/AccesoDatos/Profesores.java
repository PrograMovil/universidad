package AccesoDatos;

import LogicaNegocio.Profesor;
import java.util.ArrayList;

public class Profesores extends AccesoDatos{
    
    private ArrayList<Profesor> profesores;
    
    public Profesores() {
        
    }

    public Profesor buscar(String id){
        return null;
    }
    
    public boolean eliminar(String id){
        return true;
    }
    
    public boolean agregar(Profesor profesor) {
        return true;
    }
    
    
    
}