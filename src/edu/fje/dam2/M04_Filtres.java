package edu.fje.dam2;

import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;

import static com.mongodb.client.model.Filters.*;

import org.bson.Document;


/**
 * Classe que mostra com utilitzar els filtres en consultes
 * a MongoDB
 * <p>
 * La llista de filtres està disponible a
 * http://api.mongodb.org/java/3.2/com/mongodb/client/model/Filters.html
 *
 * @author sergi.grau@fje.edu
 * @version 1.0 25.02.2016
 */
public class M04_Filtres {
    private final static String HOST = "127.0.0.1";
    private final static int PORT = 27017;

    public static void main(String args[]) {
        try {
            MongoClient mongoClient = new MongoClient(HOST, PORT);
            MongoDatabase db = mongoClient.getDatabase("exemple");
            MongoCollection<Document> col1 = db.getCollection("usuaris");
            Document doc = new Document("nom", "sergi").append("anys", 45).append("telf", "321-654-987");
            col1.insertOne(doc);

            try (MongoCursor<Document> cursor = col1.find(eq("nom", "sergi")).iterator()) {
                while (cursor.hasNext()) {
                    doc = cursor.next();
                    System.out.println(doc.toJson());
                }
            }
            //dos condicions operador AND
            try (MongoCursor<Document> cursor = col1.
                    find(and(gt("anys", 20), lte("anys", 40))).iterator()) {
                while (cursor.hasNext()) {
                    doc = cursor.next();
                    System.out.println(doc.toJson());
                }
            }

        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
        }
    }
}
