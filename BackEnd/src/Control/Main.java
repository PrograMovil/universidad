
package Control;

import AccesoDatos.AccesoDatos;
import AccesoDatos.Carreras;
import AccesoDatos.Ciclos;
import AccesoDatos.Cursos;
import LogicaNegocio.Carrera;
import LogicaNegocio.Ciclo;
import LogicaNegocio.Curso;
import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;


public class Main {
   
   public static void main(String[] args) throws Exception {
        
        
        Carrera car = new Carrera("001","Informatica","Ingenieria en Sistemas");
        Ciclo ci=new Ciclo(2017, 3, new java.util.Date(2017, 11, 20), new java.util.Date(2018, 2, 2));
        Control ctrl = new Control();
        
        //agregar ciclo
        if(ctrl.addCiclo(ci) == 1){
            System.out.println("Ciclo agregado!");
        }else{
            System.out.println("ERROR: Ciclo NO agregada!");
        }
        
        //agregar carrera
        if(ctrl.addCarrera(car) == 1){
            System.out.println("Carrera agregada!");
        }else{
            System.out.println("ERROR: Carrera NO agregada!");
        }
        
        //agregar Estudiante
        if(ctrl.addCarrera(car) == 1){
            System.out.println("Carrera agregada!");
        }else{
            System.out.println("ERROR: Carrera NO agregada!");
        }

//        update
//        car.setNombre("Ingenieria en Informatica");
//        if(ctrl.updateCarrera(car) == 1){
//            System.out.println("Carrera actualizada!");
//        }else{
//            System.out.println("ERROR: Carrera NO actualizada!");
//        }

//        delete
//        if(ctrl.deleteCarrera(car) == 1){
//            System.out.println("Carrera eliminada!");
//        }else{
//            System.out.println("ERROR: Carrera NO eliminada!");
//        }
        
//if(ctrl.ciclos.eliminar(ci)==1) System.out.println("Carrera actualizada!");
        //Ciclo car2 = ctrl.getCiclo(2017,3);
        //System.out.println(car2.getAnio());
   } 
    
}
