package mx.uv;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexion {
    private static String url = "jdbc:mysql://127.0.0.1:3306/BDejemplo";
    private static String driverName = "com,mysql.jdbc.Diver";//com.mysql.cj.jdbc.Driver
    private static String userName = "root";
    private static String password = "";

    //variable de conexion
    private static Connection connection = null;

    public static Connection getConnection(){
        try {
            Class.forName(driverName);
            connection = DriverManager.getConnection(url, userName, password);
        } catch (SQLException e) {
            System.out.println("SQL:"+e);
        } catch (ClassNotFoundException e) {
            System.out.println("Driver:"+e);
        }
        return connection;
    }
}






























