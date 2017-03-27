package InterfazConsola;

import Control.Control;
import java.io.*;
import java.net.*;
import java.util.Scanner;

import LogicaNegocio.Carrera;
import LogicaNegocio.Usuario;

class ResponseListener implements Runnable {

    public ResponseListener() {
        Thread miHilo = new Thread(this);
        miHilo.start();
    }

    @Override
    public void run() {
        try {
            ServerSocket responseListener = new ServerSocket(9090);

            while (true) {
                Socket socketResponses = responseListener.accept();
                DataInputStream flujoEntrada = new DataInputStream(socketResponses.getInputStream());
                String dataRecibida = flujoEntrada.readUTF();
                System.out.println(dataRecibida);
            }

        } catch (IOException ex) {
            System.err.println("Exception: " + ex);
        }
    }

}

public class InterfazConsola extends Thread implements Runnable {

    String menuOption = "";
    String responseServer = "Conectando...";
    String ipServidor = "127.0.0.1";
    Control control;
    //private DataOutputStream salidaDatos;
   // private DataInputStream entradaDatos;
    private ObjectOutputStream salidaObjetos;
    private ObjectInputStream entradaObjetos;
    private Socket socket;
    private int puerto = 9090;
    private String host = "127.0.0.1";

    public void setResponseServer(String responseServer) {
        this.responseServer = responseServer;
    }

    public InterfazConsola() {
        Thread miHilo = new Thread(this);
        miHilo.start();
        
        
        control = new Control();
    }

    @Override
    public void run() {
        try {
            ServerSocket responseListener = new ServerSocket(9090);

            while (true) {
                Socket socketResponses = responseListener.accept();
            }

        } catch (IOException ex) {
            System.err.println("Exception: " + ex);
        }
    }

    public int login() throws Exception {
        
        int nivel = 0;
        
        do {
            Scanner menuEscaner = new Scanner(System.in);
            System.out.print("<---------- Gestion Academica ---------->"
                    + "\n\n Usuario: ");
            String usuario = menuEscaner.nextLine();

            System.out.print("\n Contraseña: ");
            String contrasena = menuEscaner.nextLine();

            salidaObjetos.writeObject(new Usuario(usuario, contrasena, 2));

            nivel = (int) entradaObjetos.readInt();

        } while (nivel == 0);
        
        entradaObjetos.close();
        salidaObjetos.close();
        return nivel;
        
    }

    public static void main(String[] args) throws Exception {

        try {
            
            InterfazConsola interfaz = new InterfazConsola();
            interfaz.login();
            
        } catch (Exception e) {
            System.err.println("Exception: " + e);
        }

    }

}



