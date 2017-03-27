package Control;

import LogicaNegocio.Usuario;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class Sockets {

    ServerSocket serverSocket;
    final int PUERTO = 5000;
    Socket socket;
    Control control;

    public Sockets() throws IOException {

        try {

            serverSocket = new ServerSocket(PUERTO, 5);
            control = new Control();

            while (true) {

                socket = serverSocket.accept();
                System.out.println("Cliente Conectado");
                Conexion conexion = new Conexion(socket);
                conexion.start();
            }

        } catch (Exception e) {
            serverSocket.close();
            //  socket.close();
            System.err.println("Error en iniciar Server");
        }

    }

    public class Conexion extends Thread {

        Socket socket;
        //private DataOutputStream salidaDatos;
        //private DataInputStream entradaDatos;
        private ObjectInputStream entradaObjetos;
        private ObjectOutputStream salidaObjetos;

        public Conexion(Socket socket) {

            this.socket = socket;
            try {
                entradaObjetos = new ObjectInputStream(socket.getInputStream());
                salidaObjetos = new ObjectOutputStream(socket.getOutputStream());

            } catch (Exception ex) {
                System.err.printf(ex.toString());
            }

        }

        public void run() {

            try {
                while (true) {

                    System.out.println("Esperando String");
                    String action = entradaObjetos.readUTF();
                    System.out.println("String Recibido");
                    System.out.println(action);

                    switch (action) {
                        case "login": {
                            Usuario usuarioRecibido = (Usuario) entradaObjetos.readObject();
                            //devolviendo el resultado del login
                            int nivel = control.verificaUsuario(usuarioRecibido.getId(), usuarioRecibido.getClave());
                            salidaObjetos.writeInt(nivel);

                        }
                        break;

                        case "verEstudiantes":

                            break;
                    }

                }
            } catch (Exception ex) {

                try {

                    entradaObjetos.close();
                    salidaObjetos.close();
                    this.socket.close();

                } catch (IOException ex2) {

                }
            }

        }

    }

}
