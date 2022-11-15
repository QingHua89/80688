package mx.uv;

import java.sql.Connection;
import java.sql.ResultSet;
import java.util.List;

//Data Access Object
public class DAO {
    //en el DAO se establece la conexion a la BD
    private static Conexion c = new Conexion();
    
    //este metodo regresa un conjunto de usuarios que cumpla un criterio
    public static List<Usuario> dameUsuarios(){
        Statement stmt = null;
        ResultSet rs = null;
        Connection conn = null;

        conn = c.getConnection();

        try{
            Srting sql = "SELECT * from usuarios80688";
            stm = conn.createStatement();
            rs = stm.executeQuery(sql);
            while (rs.next()){
                Usuario u = new Usuario(rs.getString("id"), rs.getEstring("nombre"), rs.getString("password"))
            }
        } catch (Exception e){
            System.out.println("Driver:"+ e);

        } finally{
            if (rs != null)
            try {
                rs.close();
            } catch (SQLException e) {
                System.out.println(e);
            }
        }
    }
        return null;
    }
}
