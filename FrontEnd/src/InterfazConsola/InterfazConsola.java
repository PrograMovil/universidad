package InterfazConsola;

import Control.Control;
import Control_Sockets.ConexionServidor;
import java.io.*;
import java.net.*;
import java.util.Scanner;

import LogicaNegocio.*;





public class InterfazConsola extends Thread implements Runnable {

    ConexionServidor conexion;


    public InterfazConsola() {
        
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

            nivel=conexion.login(new Usuario(usuario, contrasena, 2));

        } while (nivel == 0);
        
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



