package InterfazConsola;

import Control_Sockets.Control_Sockets;
import java.util.Scanner;






public class InterfazConsola{

    Control_Sockets control;


    public InterfazConsola() {
        
       control=new Control_Sockets();
        
    }

    public int login(){
        
        int nivel = 0;
        
        do {
            Scanner menuEscaner = new Scanner(System.in);
            System.out.print("<---------- Gestion Academica ---------->"
                    + "\n\n Usuario: ");
            String usuario = menuEscaner.nextLine();

            System.out.print("\n Contraseña: ");
            String contrasena = menuEscaner.nextLine();

            nivel=control.login(usuario, contrasena);

        } while (nivel == 0);
        
        return nivel;
        
    }
    

}



