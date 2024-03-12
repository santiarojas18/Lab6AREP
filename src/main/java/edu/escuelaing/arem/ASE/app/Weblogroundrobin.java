package edu.escuelaing.arem.ASE.app;
import static spark.Spark.*;
/**
 * Hello world!
 *
 */
public class Weblogroundrobin {
    private static final String[] LOG_SERVICE_URL = {"http://logservice1:46001/logservice", "http://logservice2:46002/logservice", "http://logservice3:46003/logservice"};
    private static int service = 0;
    public static void main( String[] args ) {
        staticFiles.location("/public");
        port(getPort());

        post("/log",(req, res) -> {
            res.type("application/json");
            String response = RRInvoker.makePost(LOG_SERVICE_URL[service], req.body());
            service += 1;
            if (service == 3) {
                service = 0;
            }
            return response;
        });
    }

    private static int getPort() {
        if (System.getenv("PORT") != null) {
            return Integer.parseInt(System.getenv("PORT"));
        }
        return 4567;
    }
}
