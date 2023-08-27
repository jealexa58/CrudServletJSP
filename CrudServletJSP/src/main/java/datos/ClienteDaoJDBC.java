/*DAO= Data access object objeto de acceso a los datos*/
package datos;

import dominio.Cliente;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class ClienteDaoJDBC {
    private static final String SQL_SELECT = "SELECT idCliente,nombre,apellido,email,telefono,saldo FROM cliente";
    private static final String SQL_SELECT_BY_ID= "SELECT idCliente,nombre,apellido,email,telefono,saldo FROM cliente WHERE id_cliente = 2";
    private static final String SQL_INSERT="INSERT INTO cliente(nombre,apellido,email,telefono,saldo) VALUES (?,?,?,?,?)";
    private static final String SQL_UPDATE= "UPDATE cliente SET nombre=?, apellido=?, email=?, telefono=?, saldo=? WHERE id_cliente=?";
    private static final String SQL_DELETE="DELETE FROM cliente WHERE id_cliente=?";
    
    /*Método para listar objetos (clientes) */
    public List<Cliente> listar(){
        Connection conn= null;
        PreparedStatement stmt  = null;
        ResultSet rs= null;
        List<Cliente> clientes = new ArrayList<Cliente>();
        
        /*Manejo de las excepciones al conectar con una base de datos*/
        try {
            conn= Conexion.getConnection();
            stmt= conn.prepareStatement(SQL_SELECT);
            rs= stmt.executeQuery();
            while (rs.next()){
                int idCliente = rs.getInt("idCliente");
                String nombre = rs.getString("nombre");
                String apellido = rs.getString("apellido");
                String email = rs.getString("email");
                String telefono = rs.getString("telefono");
                double saldo = rs.getDouble("saldo");
                
                System.out.println(nombre+" "+apellido+" "+email);
                
                clientes.add(new Cliente(idCliente,nombre,apellido,email,telefono,saldo));
            }
            
        } catch (Exception e) {
            e.printStackTrace(System.out);
        }finally{
            Conexion.Close(rs);
            Conexion.Close(stmt);
            Conexion.Close(conn);
        }
        return clientes;
        
    }
    
    /*Método para buscar un cliente*/
    public Cliente  buscar(Cliente cliente){
        Connection conn= null;
        PreparedStatement stmt  = null;
        ResultSet rs= null;
        
        /*Manejo de las excepciones al conectar con una base de datos*/
        try {
            conn= Conexion.getConnection();
            stmt= conn.prepareStatement(SQL_SELECT_BY_ID);
            stmt.setInt(1, cliente.getIdCliente());
            
            rs= stmt.executeQuery();
                rs.next();
                int idCliente = rs.getInt("idCliente");
                String nombre = rs.getString("nombre");
                String apellido = rs.getString("apellido");
                String email = rs.getString("email");
                String telefono = rs.getString("telefono");
                double saldo = rs.getDouble("saldo");
                          
        } catch (Exception e) {
            
            e.printStackTrace(System.out);
            
        }finally{
            
            Conexion.Close(rs);
            Conexion.Close(stmt);
            Conexion.Close(conn);
            
        }
        return cliente;
        
    }
    
     /*Método para insertar un cliente*/
    public int  insertar (Cliente cliente){
        Connection conn= null;
        PreparedStatement stmt  = null;
        int rows=0;
        
        /*Manejo de las excepciones al conectar con una base de datos*/
        try {
            conn= Conexion.getConnection();
            stmt= conn.prepareStatement(SQL_INSERT);
            
            stmt.setString(1, cliente.getNombre());
            stmt.setString(2, cliente.getApellido());
            stmt.setString(3, cliente.getEmail());
            stmt.setString(4, cliente.getTelefono());
            stmt.setDouble(5, cliente.getSaldo());
            
            rows = stmt.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace(System.out);
        }finally{
            Conexion.Close(stmt);
            Conexion.Close(conn);
        }
        
        return rows;
        
    }
    
    /*Método para actualizar un cliente*/
    public int actualizar (Cliente cliente){
             Connection conn= null;
        PreparedStatement stmt  = null;
        int rows=0;
        
        /*Manejo de las excepciones al conectar con una base de datos*/
        try {
            conn= Conexion.getConnection();
            stmt= conn.prepareStatement(SQL_UPDATE);
            
            stmt.setString(1, cliente.getNombre());
            stmt.setString(2, cliente.getApellido());
            stmt.setString(3, cliente.getEmail());
            stmt.setString(4, cliente.getTelefono());
            stmt.setDouble(5, cliente.getSaldo());
            stmt.setInt(6,cliente.getIdCliente());
            
            rows = stmt.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace(System.out);
        }finally{
            Conexion.Close(stmt);
            Conexion.Close(conn);
        }
        
        return rows;
    }
    
    /*Método para eliminar un cliente*/
    public int eliminar(Cliente cliente){
        Connection conn= null;
        PreparedStatement stmt  = null;
        int rows=0;
        
        /*Manejo de las excepciones al conectar con una base de datos*/
        try {
            conn= Conexion.getConnection();
            stmt= conn.prepareStatement(SQL_DELETE);
            
            stmt.setInt(1,cliente.getIdCliente());
            
            rows = stmt.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace(System.out);
        }finally{
            Conexion.Close(stmt);
            Conexion.Close(conn);
        }
        
        return rows;
    }
    
    /*PRUEBA*/
    public static void main(String[] args) {
        ClienteDaoJDBC cliente= new ClienteDaoJDBC();
        cliente.listar(); 
    }
    
}
