
package AccesoDatos;

import java.sql.ResultSet;


public class AccesoDatos {
    static Database db;
    
//    static initializer block
//    ejecuta la inicializacion de la base cuando se cargue la clase
//    static {
//        initdb();
//    }

    public AccesoDatos() {
        System.out.println("Hola desde Acceso a Datos");
//        initdb();
        db = new Database();
    }
    
    
    
//    private static void initdb() {
//        db = new Database(null, null, null);
//    }
    
//    public AccesoDatos(){}
    
    public int agregar(String tableAndParams,String values){
        String sql = "insert into "+ tableAndParams + " values(" + values + ")";
        int count = db.executeUpdate(sql);
        return count; //0 = fallo o registro repetido, 1 = exitoso
    }
    
    public int eliminar(String tableName, String query){
        String sql = "delete from " + tableName + " where " + query;
        int count = db.executeUpdate(sql);
        return count;
    }
    
    public int actualizar(String tableName, String tableParams){
        String sql = "update " + tableName + " set " + tableParams;
        int count = db.executeUpdate(sql);
        return count;
    }
    
    public ResultSet obtener(String tableName, String param){
        String sql = "select * from " + tableName + " where " + param;
        ResultSet rs = db.executeQuery(sql);
        return rs;        
    }
    
    public ResultSet obtenerTodo(String tableName){
        String sql = "select * from " + tableName;
        ResultSet rs = db.executeQuery(sql);
        return rs;        
    }
//    public int updateData(String ){
//        
//    }

    
}
