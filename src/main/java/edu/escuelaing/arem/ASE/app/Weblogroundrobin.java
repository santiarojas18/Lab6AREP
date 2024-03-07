package edu.escuelaing.arem.ASE.app;
import static spark.Spark.*;
/**
 * Hello world!
 *
 */
public class Weblogroundrobin
{
    public static void main( String[] args ) {
        staticFiles.location("/public");
        get("/log",(req, res) -> RRInvoker.consult());
    }
}
