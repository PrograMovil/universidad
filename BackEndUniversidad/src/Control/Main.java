
package Control;

import LogicaNegocio.*;

import java.io.*;
import java.net.*;


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

        
        Server server = new Server();
        
//        Sockets para la conexion con las interfaces.
//        Server servidor = new Server();
        

     

//                          PRUEBAS DE CRUD
//
//        Carrera car = new Carrera("001","Informatica","Ingenieria en Sistemas");
//        Control ctrl = new Control();
//
//
//        add
//        if(ctrl.addCarrera(car) == 1){
//            System.out.println("Carrera agregada!");
//        }else{
//            System.out.println("ERROR: Carrera NO agregada!");
//        }

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

//        Carrera car2 = ctrl.getCarrera("100");
//        System.out.println(car2.toString());

    }
    
}