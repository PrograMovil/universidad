
package Control_Sockets;

import LogicaNegocio.Usuario;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Control_Sockets extends Thread{
    
    Usuario usuario;
    ConexionServidor conexion;
    int nivel;

    public Control_Sockets() {
        try {
            usuario=null;
            nivel=0;
            conexion= new ConexionServidor("localhost");
        } catch (Exception ex) {
            Logger.getLogger(Control_Sockets.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    
    public int login(String usuario, String contrasena){
        
        try {
            this.usuario=new Usuario(usuario,contrasena,0);
            conexion.setAction("login");
            Thread.sleep(4000);
            return nivel;
            
        } catch (Exception ex) {
            Logger.getLogger(Control_Sockets.class.getName()).log(Level.SEVERE, null, ex);
        }
        return 0;
    }
    
    
    
    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public int getNivel() {
        return nivel;
    }

    public void setNivel(int nivel) {
        this.nivel = nivel;
    }
    
    
    
}
