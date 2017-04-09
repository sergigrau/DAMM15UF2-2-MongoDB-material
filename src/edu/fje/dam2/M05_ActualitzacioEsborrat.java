package edu.fje.dam2;

import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import static com.mongodb.client.model.Filters.*;
import com.mongodb.client.result.DeleteResult;
import com.mongodb.client.result.UpdateResult;
import org.bson.Document;

/**
 * Classe que mostra com actualitzar i esborrar documents de MongoDB
 *
 * @author sergi.grau@fje.edu
 * @version 1.0 25.02.2016
 */
public class M05_ActualitzacioEsborrat {
    private final static String HOST = "127.0.0.1";
    private final static int PORT = 27017;

    public static void main(String args[]) {
        try {
            MongoClient mongoClient = new MongoClient(HOST, PORT);
            MongoDatabase db = mongoClient.getDatabase("exemple");
            MongoCollection<org.bson.Document> col1 = db.getCollection("usuaris");
            Document doc;
            //actualizació única
            col1.updateOne(eq("nom", "sergi"), new Document("$set", new Document("anys", 50)));
            //actualització multiple
            UpdateResult resultats = col1.updateMany(eq("nom", "sergi"),
                    new Document("$set", new Document("anys", 50)));
            System.out.println(resultats.getModifiedCount());

            try (MongoCursor<Document> cursor = col1.find(eq("nom", "sergi")).iterator()) {
                while (cursor.hasNext()) {
                    doc = cursor.next();
                    System.out.println(doc.toJson());
                }
            }
            //esborrat únic
            col1.deleteOne(eq("nom", "sergi"));
            //esborrat multiple
            DeleteResult resultats2 = col1.deleteMany(eq("nom", "sergi"));
            System.out.println(resultats2.getDeletedCount());

            try (MongoCursor<Document> cursor = col1.find(eq("nom", "sergi")).iterator()) {
                while (cursor.hasNext()) {
                    doc = cursor.next();
                    System.out.println(doc.toJson());
                }
            }

            //reemplaçar document encastat
            //col1.replaceOne(eq("_id", document.get("_id")), document);

        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " +e.getMessage());
        }
    }
}
