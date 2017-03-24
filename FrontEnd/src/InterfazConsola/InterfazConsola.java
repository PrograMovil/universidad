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

public class InterfazConsola implements Runnable {

    String menuOption = "";
    String responseServer = "Conectando...";
    String ipServidor = "127.0.0.1";
    Control control;
    private DataOutputStream salidaDatos;
    private DataInputStream entradaDatos;
    private Socket socket;
    private int puerto = 9090;
    private String host="localhost";

    public void setResponseServer(String responseServer) {
        this.responseServer = responseServer;
    }

    public InterfazConsola() {
        Thread miHilo = new Thread(this);
        miHilo.start();
        control = new Control();
        try{
            
        this.socket=new Socket(host,puerto);
        
        this.salidaDatos = new DataOutputStream(socket.getOutputStream());
        this.entradaDatos = new DataInputStream(socket.getInputStream());
        this.start();
        
        }catch (Exception ex) {
             
            System.out.println(ex.getClass());
            System.out.println(ex.toString());
            
            throw ex;
        }
    }

    @Override
    public void run() {
        try {
            ServerSocket responseListener = new ServerSocket(9090);

            while (true) {
                Socket socketResponses = responseListener.accept();
                DataInputStream flujoEntrada = new DataInputStream(socketResponses.getInputStream());
                String dataRecibida = flujoEntrada.readUTF();
//                this.setResponseServer(dataRecibida);
                System.out.println("\n" + dataRecibida + "\n");
            }

        } catch (IOException ex) {
            System.err.println("Exception: " + ex);
        }
    }

    public int login() throws Exception {
            Socket cliente = new Socket(this.ipServidor, 9999);

            
            
        Thread.sleep(300);
        int nivel = 0;
        ObjectOutputStream out = new ObjectOutputStream(cliente.getOutputStream());
            ObjectInputStream in = new ObjectInputStream(cliente.getInputStream());
        do {
            Scanner menuEscaner = new Scanner(System.in);
            System.out.print("<---------- Gestion Academica ---------->"
                    + "\n\n Usuario: ");
            String usuario = menuEscaner.nextLine();

            System.out.print("\n Contraseña: ");
            String contrasena = menuEscaner.nextLine();

            
            out.writeObject(new Usuario(usuario, contrasena, 2));

            
            nivel = (int) in.readInt();

        } while (nivel == 0);
        in.close();
        out.close();
        return nivel;
        
    }

    public void menu() throws InterruptedException {
        Thread.sleep(300);
        Scanner menuEscaner = new Scanner(System.in);
//        System.out.println(this.responseServer);
        System.out.print("<---------- MENU ---------->"
                + "\n 1 - Regristrar Carrera."
                + "\n 2 - Eliminar Carrera."
                + "\n 3 - Actualizar Carrera."
                + "\n 4 - Buscar Carrera."
                + "\n 5 - Salir."
                + "\n\n Seleccione una opción:");
        this.menuOption = menuEscaner.nextLine();
    }

    public void routerMenu() throws InterruptedException {

//        this.menu();
        switch (this.menuOption) {
            case "1": {
                System.out.println("< ---------- Registrar Carrera ---------->.");

                this.registrarCarrera();

                this.menu();
                this.routerMenu();
            }
            break;
            case "2": {
                System.out.println("Opcion 2.");
                this.menu();
                this.routerMenu();
            }
            break;
            case "3": {
                System.out.println("Opcion 3.");
                this.menu();
                this.routerMenu();
            }
            break;
            case "4": {
                System.out.println("Opcion 4.");
                this.menu();
                this.routerMenu();
            }
            break;
            case "5": {
                System.exit(0);
            }
            break;
            default: {
                System.out.println("Error, opcion invalida.");
                this.menu();
                this.routerMenu();
            }
            break;
        }
    }

    public void registrarCarrera() {
        try {
            Scanner entradaEscaner = new Scanner(System.in);
            Carrera carrera = new Carrera();

            Socket cliente = new Socket(this.ipServidor, 9999);

            //Mandar una Carrera
            System.out.println("Ingrese el Codigo de la carrera:");
            carrera.setCodigo(entradaEscaner.nextLine());
            System.out.println("Ingrese el nombre de la carrera:");
            carrera.setNombre(entradaEscaner.nextLine());
            System.out.println("Ingrese el titulo de la carrera:");
            carrera.setTitulo(entradaEscaner.nextLine());

            ObjectOutputStream carreraDatos = new ObjectOutputStream(cliente.getOutputStream());
            carreraDatos.writeObject(carrera);
            carreraDatos.close();

        } catch (UnknownHostException ex) {
            System.err.println("Exception: " + ex);
        } catch (Exception e) {
            System.err.println("Exception: " + e);
        }
    }

    public static void main(String[] args) throws Exception {
//        Sockets para la conexion con las interfaces.
//        ResponseListener resposes = new ResponseListener();

        try {
//            Scanner entradaEscaner = new Scanner(System.in);
//            Carrera carrera = new Carrera();
            InterfazConsola interfaz = new InterfazConsola();
            //Socket cliente = new Socket("127.0.0.1", 9999);
            interfaz.login();

//            
//            //Mandar una Carrera
//            System.out.println("Ingrese el Codigo de la carrera:");
//            carrera.setCodigo(entradaEscaner.nextLine());
//            System.out.println("Ingrese el nombre de la carrera:");
//            carrera.setNombre(entradaEscaner.nextLine());
//            System.out.println("Ingrese el titulo de la carrera:");
//            carrera.setTitulo(entradaEscaner.nextLine());
//            
//            ObjectOutputStream carreraDatos = new ObjectOutputStream(cliente.getOutputStream());
//            carreraDatos.writeObject(carrera);
//            carreraDatos.close();      
        } catch (UnknownHostException ex) {
            System.err.println("Exception: " + ex);
        } catch (Exception e) {
            System.err.println("Exception: " + e);
        }

    }

}
