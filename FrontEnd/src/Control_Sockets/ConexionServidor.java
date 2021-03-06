package Control_Sockets;

import LogicaNegocio.*;
import java.io.DataOutputStream;
import java.io.DataInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.Scanner;

public class ConexionServidor extends Thread {

    private Socket socket;
    private int puerto = 5000;
    private String host = "localhost";

    private ObjectOutputStream salidaObjetos;
    private ObjectInputStream entradaObjetos;
    //private DataOutputStream salidaDatos;
    //private DataInputStream entradaDatos;
    private Control_Sockets control;
    

    private volatile boolean esperando = true;
    private volatile String action;
    
    private int nivel;
    

    public ConexionServidor(String IP) throws Exception {

        host = IP;

        try {

            nivel=0;
            this.socket = new Socket(host, puerto);
            //this.salidaDatos = new DataOutputStream(socket.getOutputStream());
            //this.entradaDatos = new DataInputStream(socket.getInputStream());
            this.entradaObjetos = new ObjectInputStream(socket.getInputStream());
           this.salidaObjetos = new ObjectOutputStream(socket.getOutputStream());
            this.control = new Control_Sockets();
            this.start();

        } catch (Exception ex) {

            System.out.println(ex.getClass());
            System.out.println(ex.toString());

            throw ex;
        }
    }

    @Override
    public void run() {

        try {

            System.out.println("Esperando");

            while (esperando) {

                switch (action) {
                    case "login": {
                        //envia el tipo de accion
                        salidaObjetos.writeUTF("login");

                        //envia el objeto
                        salidaObjetos.writeObject(control.getUsuario());

                        //obtiene el resultado
                        control.setNivel((int) entradaObjetos.readInt());

                    }
                    break;

                }

            }

            System.out.println("Dejo de Esperar");

            salidaObjetos.writeUTF("OUT");

        } catch (Exception e) {
            try {
                entradaObjetos.close();
                salidaObjetos.close();
                this.socket.close();

            } catch (IOException ex2) {
            }
        }

    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public void Cerrar() {

        esperando = false;
        System.out.println("Cerrar TRUE");
    }

}
