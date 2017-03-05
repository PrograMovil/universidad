
package AccesoDatos;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


public class ConexionBD {
   static String bd = "universidad";
   static String login = "root";
   static String password = "root";
   static String url = "jdbc:mysql://localhost:3306/"+bd;
 
   Connection conexion = null;
    
   
   public ConexionBD() {
      try{
         //obtenemos el driver de para mysql
         Class.forName("com.mysql.jdbc.Driver");
         //obtenemos la conexión
         conexion = DriverManager.getConnection(url,login,password);
 
         if (conexion!=null){
            System.out.println("Conexión a base de datos "+bd+" OK\n");
         }
      }
      catch(SQLException e){
         System.out.println(e);
      }catch(ClassNotFoundException e){
         System.out.println(e);
      }catch(Exception e){
         System.out.println(e);
      }
   }
   
   
   public Connection getConnection(){
      return conexion;
   }
 
   public void desconectar(){
      conexion = null;
   }
    
    
}
