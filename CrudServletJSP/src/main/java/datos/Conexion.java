
package datos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.sql.DataSource;
import org.apache.commons.dbcp2.BasicDataSource;

public class Conexion {
    
    /*Creando las variables para la conexión*/
    private static final String JDBC_URL = "jdbc:mysql://localhost:3306/crudServletJPS?useSSL=false&useTimezone=true&serverTimeZone=UTC&allowPublicKeyRetrieval=true ";
    private static final String JDBC_USER = "root";
    private static final String JDBC_PASSWORD = "root";
    private static BasicDataSource dataSource ;
    
    /*Creando Métodos para la conexión*/
    public static DataSource getDataSource(){
    if(dataSource == null){
            dataSource =new BasicDataSource();
            dataSource .setUrl(JDBC_URL);
            dataSource.setUsername(JDBC_USER);
            dataSource.setPassword(JDBC_PASSWORD);
            dataSource.setInitialSize(50);                
    }
    return dataSource;
    }
    
    public static Connection getConnection()throws SQLException{
        return getDataSource().getConnection();
    }
    
        /*Método para cerrar la conexión*/
    public static void Close(ResultSet rs){
        try {
            rs.close();
        } catch (Exception e) {
            e.printStackTrace(System.out);
        }
    }
    public static void Close(PreparedStatement stmt){
        try {
            stmt.close();
        } catch (Exception e) {
            e.printStackTrace(System.out);
        }
    }

    public static void Close(Connection conn){
        try {
            conn.close();
        } catch (Exception e) {
            e.printStackTrace(System.out);
        }
    }
}