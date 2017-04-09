package edu.fje.dam2;

import com.mongodb.BasicDBObject;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;
import com.mongodb.client.result.DeleteResult;
import com.mongodb.client.result.UpdateResult;
import org.bson.Document;

/**
 * Classe que mostra com utilitzar DBObject de manera bàsica
 *
 * @author sergi.grau@fje.edu
 * @version 1.0 29.02.2016
 */
public class M06_CRUDBasic {
    private final static String HOST = "127.0.0.1";
    private final static int PORT = 27017;

    public static void main(String[] args) {
        try {
            //MongoCredential credential = MongoCredential.createCredential(userName, database, password);
            //MongoClient mongoClient = new MongoClient(new ServerAddress(), Arrays.asList(credential));

            MongoClient mongoClient = new MongoClient(HOST, PORT);
            MongoDatabase db = mongoClient.getDatabase("exemple");
            MongoCollection<Document> col1 = db.getCollection("usuaris");

            Document doc;
            doc = new Document("nom", "sergi").append("anys", 45)
                    .append("telf", "321-654-987");
            col1.insertOne(doc);

            //creem un objevte que representa un document
            BasicDBObject cerca = new BasicDBObject();
            cerca.put("nom", "sergi");

            try (MongoCursor<Document> cursor = col1.find(cerca).iterator()) {
                while (cursor.hasNext()) {
                    doc = cursor.next();
                    System.out.println(doc.toJson());
                }
            }

        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " +e.getMessage());
        }
    }
}
