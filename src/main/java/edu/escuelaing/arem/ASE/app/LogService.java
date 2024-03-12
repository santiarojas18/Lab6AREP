package edu.escuelaing.arem.ASE.app;
import com.mongodb.client.MongoDatabase;

import static spark.Spark.*;

public class LogService {
    public static void main(String[] args) {
        port(getPort());
        MongoDatabase database = MongoUtil.getDB();
        LogDAO logDAO = new LogDAO(database);
        get("/logservice", (req, res) -> {
            res.type("application/json");
            return logDAO.listLogs();
        });

        post("/logservice", (req, res) ->{
            String information = req.body();
            res.type("application/json");
            logDAO.addLog(information);
            return logDAO.listLogs();
        });

        delete("/logservice", (req, res) -> {
            return logDAO.deleteLog(req.body());
        });
    }

    private static int getPort() {
        if (System.getenv("PORT") != null) {
            return Integer.parseInt(System.getenv("PORT"));
        }
        return 4567;
    }
}
