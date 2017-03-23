
package Control;

import LogicaNegocio.*;


public class Main {
   
   public static void main(String[] args) throws Exception {
        
        
        Carrera car = new Carrera("001","Informatica","Ingenieria en Sistemas");
        Ciclo ci=new Ciclo(2017, 3, new java.util.Date(2017, 11, 20), new java.util.Date(2018, 2, 2));
        Usuario us= new Usuario("abc", "123", 1);
        Estudiante es= new Estudiante("Juan", "2-222-222", "9998888", "fdsfsf",new java.util.Date(2015, 2, 2) , us, car);
        Control ctrl = new Control();
        
        
        //agregar usuario
        if(ctrl.addUsuario(us) == 1){
            System.out.println("Usuario agregado");
        }else{
            System.out.println("ERROR: Usuario NO agregada!");
        }
        
        
        
        
        //agregar carrera
        if(ctrl.addCarrera(car) == 1){
            System.out.println("Carrera agregada!");
        }else{
            System.out.println("ERROR: Carrera NO agregada!");
        }
        
        
        if(ctrl.addEstudiante(es) == 1){
            System.out.println("Estudiante agregado");
        }else{
            System.out.println("ERROR: Estudiante NO agregada!");
        }
        
        //ctrl.deleteEstudiante(es);
   } 
    
}
