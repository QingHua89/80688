package mx.uv;
import java.util.*;

//import javax.annotation.processing.SupportedOptions;

import com.google.gson.Gson;
import static spark.Spark.*;

/**
 * Hello world!
 *
 */
public class App {

    public static Gson gson = new Gson();
    private static Map<String, Usuario> usuarios = new HashMap<>();
    private static Conexion c;
    
    public static void main(String[] args) {
        //intentar conectar a la BD
        c = new Conexion();
        c.getConnection();

        port(80);
        options("/*", (request, response) -> {
                String accessControlRequestHeaders = request.headers("Access-Control-Request-Headers");
                System.out.println(accessControlRequestHeaders);
                if (accessControlRequestHeaders != null) {
                    response.header("Access-Control-Allow-Headers", accessControlRequestHeaders);
                }
                String accessControlRequestMethod = request.headers("Access-Control-Request-Method");
                System.out.println(accessControlRequestMethod); 
                if (accessControlRequestMethod != null) {
                    response.header("Access-Control-Allow-Methods", accessControlRequestMethod);
                }
                return "OK";
            });
            
        before((req, res)-> res.header("Access-Control-Allow-Origin", "*"));
        System.out.println("Hello World!");
        Usuario u1 = new Usuario("1", "Pedro", "1234");
        Usuario u2 = new Usuario("2", "Pablo", "7890");

        usuarios.put(u1.getId(), u1);
        usuarios.put(u2.getId(), u2);

        before((req, res)-> res.type("application/json"));
        // devolver un usuario
        // get("/", (req, res) -> gson.toJson(u1) ); 
        // devolver lista de usuarios
        get("/", (req, res) -> gson.toJson(usuarios) );
        // get("/", (req, res) -> u1.getNombre() );

        post("/", (req,res)->{
            String datosCliente = req.body();
            Usuario u = gson.fromJson(datosCliente, Usuario.class);
            usuarios.put(u.getId(), u);
            return "listo se ingreso el usuario "+u.getId();
        });


    }
}