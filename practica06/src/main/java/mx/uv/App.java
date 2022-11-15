package mx.uv;
import static spark.Spark.*;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

/**
 * Hello world!
 *
 */
public class App {
    public static void main(String[] args) {

        // fuente: https://gist.github.com/saeidzebardast/e375b7d17be3e0f4dddf
        //esto me marca error: options("/*", (request, response) -> {
            String accessControlRequestHeaders = request.headers("Access-Control-Request-Headers");
            if (accessControlRequestHeaders != null) {
                response.header("Access-Control-Allow-Headers", accessControlRequestHeaders);
            }
            String accessControlRequestMethod = request.headers("Access-Control-Request-Method");
            if (accessControlRequestMethod != null) {
                response.header("Access-Control-Allow-Methods", accessControlRequestMethod);
            }
            return "OK";
        });
        before((request, response) -> response.header("Access-Control-Allow-Origin", "*"));

        System.out.println("Hello World!");
        get("/aaron", (req, res) -> "Hello Aaron");
        get("/omar", (req, res) -> "Hello omar");
        get("/eduardo", (req, res) -> "Hello eduardo");
        get("/", (req, res) -> "<h1>Hola Sistemas Web</h1><img src='https://www.uv.mx/v2/images/logouv.jpg'>");
        post("/", (req, res) -> {
            // System.out.println("login: " + req.queryParams("login"));
            // System.out.println("contraseña: " + req.queryParams("password"));
            System.out.println(req.body());

            // procesar petición json
            JsonParser parser = new JsonParser();
            JsonElement arbol = parser.parse(req.body());
            JsonObject peticionDelCliente = arbol.getAsJsonObject();
            System.out.println(peticionDelCliente.get("login"));
            System.out.println(peticionDelCliente.get("password"));

            // String usuario = req.queryParams("login");
            String usuario = peticionDelCliente.get("login").getAsString();

            JsonObject respuesta = new JsonObject();
            respuesta.addProperty("msj", "bienvenido");
            respuesta.addProperty("usuario", usuario);

            if (usuario.equals("omar")) {
                res.status(200);
                return respuesta;
            } else {
                res.status(404);
                return "Quien eres tu? " + usuario;
            }
        });
    }
}