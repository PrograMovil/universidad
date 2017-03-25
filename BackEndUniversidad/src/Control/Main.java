
package Control;

import LogicaNegocio.*;

import java.io.*;
import java.net.*;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;


class Server implements Runnable {

    public Server() {
        Thread miHilo = new Thread(this);
        miHilo.start();
    }

    @Override
    public void run() {
//        Variables
        String ipClient ="127.0.0.1";
        Control control = new Control();
        String responseMsg = "";
        
        System.out.println("Server Corriendo!");
        
        try {
//            Server socket para recibir las peticiones
            ServerSocket miServicio = new ServerSocket(9999);
            
            
            
            while(true){
                
                int nivel=0;
                do{
                //Verificar Usuario y contraseña en la base
                Socket socketConectado = miServicio.accept();
                ObjectInputStream usuario = new ObjectInputStream(socketConectado.getInputStream());
                Usuario usuarioRecibido=(Usuario) usuario.readObject();
                
                
                Socket toCliente = new Socket(ipClient, 9090);
                //devolviendo el resultado del login
                DataOutputStream flujoToClient = new DataOutputStream(toCliente.getOutputStream());
                nivel=control.verificaUsuario(usuarioRecibido.getId(), usuarioRecibido.getClave());
                toCliente.close();
                flujoToClient.writeInt(nivel);
                }while(nivel==0);
                
            miServicio.close();
            
               
            } 

        } catch (IOException ex) {
            System.err.println("Exception: " + ex );
        } catch (ClassNotFoundException ex) {
            System.err.println("Exception: " + ex );
        }

    }

}

public class Main {
    
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws Exception {

        Control control=new Control();
        Carrera carrera=new Carrera("1", "Informatica", "Bachillerato");
        Ciclo ciclo=new Ciclo(2016, 1, new GregorianCalendar(2016,1,17), new GregorianCalendar(2016,5,12));
        
        Curso curso=new Curso("EIF-200", "Funda", 10, 5, carrera, "1ero");
        Horario horario=new Horario("Lunes", new GregorianCalendar(2016,1,2), new GregorianCalendar(2016,1,2));
        
        Profesor profesor=new Profesor(new Usuario("profesor", "profesor", 3), "Georges", "111", "88995566", "j@g.c");
        Grupo grupo=new Grupo(3, horario, profesor, curso, ciclo);
        
        profesor.setNombre("Juanmito");
        
        control.updateProfesor(profesor);
        
        
//        if(control.updateGrupo(grupo)!=null)
//            System.out.println("Grupo eliminado");
        
//        ArrayList<Profesor> profesores=control.obtenerTodosLosProfesores();
//        
//        for(int i=0;i<profesores.size();i++)
//            System.out.println(""+profesores.get(i).getNombre());
//        
//        ArrayList<Carrera> carreras = control.obtenerCarreraPorNombre("ca");
//        for(int i=0;i<carreras.size();i++)
//            System.out.println(""+carreras.get(i).getNombre());
//        
//        ArrayList<Curso> cursos = control.getCursoPorCarrera(carrera);
//        for(int i=0;i<carreras.size();i++)
//            System.out.println(""+carreras.get(i).getNombre());
        
        
        //Server server = new Server();
        
//        Sockets para la conexion con las interfaces.
//        Server servidor = new Server();
        

    
    }
    
}