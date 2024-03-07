package edu.escuelaing.arem.ASE.app;
import static spark.Spark.*;

public class LogService {
    public static void main(String[] args) {
        port(5000);
        get("/logservice", (req, res) -> "{\"msg\":\"Hello log\"}");
    }
}
