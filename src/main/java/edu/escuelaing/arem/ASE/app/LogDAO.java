package edu.escuelaing.arem.ASE.app;

import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.result.DeleteResult;
import org.bson.Document;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;

import static com.mongodb.client.model.Filters.eq;

public class LogDAO {
    private final MongoCollection<Document> logsCollection;

    public LogDAO(MongoDatabase database) {
        logsCollection = database.getCollection("logs");
    }

    public String addLog(String information) {
        LocalDateTime currentDate = LocalDateTime.now();
        Document newLog = new Document("info", information)
                .append("date", currentDate.toString());
        logsCollection.insertOne(newLog);
        return newLog.toJson();
    }

    public ArrayList<String> listLogs() {
        FindIterable<Document> logs = logsCollection.find();
        ArrayList<String> temporalList = new ArrayList<>();
        for (Document log : logs) {
            temporalList.add(log.toJson());
        }
        if (temporalList.size() > 10) {
            ArrayList<String> finalList = new ArrayList<>();
            for (int i = 0; i < 10; i++) {
                finalList.add(temporalList.get(temporalList.size() - 10 + i));
            }
            return finalList;
        } else {
            return temporalList;
        }
    }

    public DeleteResult deleteLog(String information) {
        return logsCollection.deleteOne(eq("info", information));
    }
}
