
package Control;

import AccesoDatos.Carreras;
import AccesoDatos.Ciclos;
import LogicaNegocio.Carrera;
import LogicaNegocio.Ciclo;

public class Control {
    Carreras carreras;
    Ciclos ciclos;
    
    
    public Control() {
        this.carreras = new Carreras();
        this.ciclos=new Ciclos();
    }
    
    public int addCarrera(Carrera ca){
        return this.carreras.agregar(ca);
    }
    
    public int deleteCarrera(Carrera ca){
        return this.carreras.eliminar(ca);
    }
    
    public int updateCarrera(Carrera ca){
        return this.carreras.actualizar(ca);
    }
    
    public Carrera getCarrera(String id) throws Exception{
        return this.carreras.obtener(id);
    }
    
    public int addCiclo(Ciclo ca){
        return this.ciclos.agregar(ca);
    }
    
    public int deleteCiclo(Ciclo ca){
        return this.ciclos.eliminar(ca);
    }
    
    public int updateCiclo(Ciclo ca){
        return this.ciclos.actualizar(ca);
    }
    
    public Ciclo getCiclo(int anio, int numero) throws Exception{
        return this.ciclos.obtener(anio,numero);
    }
}
