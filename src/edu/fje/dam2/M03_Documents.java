package edu.fje.dam2;

import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * Classe que crea realitza operacions amb documents d'una col·lecció
 * a MongoDB
 *
 * @author sergi.grau@fje.edu
 * @version 1.0 25.02.2016
 */
public class M03_Documents {
    private final static String HOST = "127.0.0.1";
    private final static int PORT = 27017;

    public static void main(String args[]) {
        try {
            MongoClient mongoClient = new MongoClient(HOST, PORT);
            MongoDatabase db = mongoClient.getDatabase("exemple");
            MongoCollection<Document> coll = db.getCollection("usuaris");
            //afegeix un document
            Document doc;
            doc = new Document("nom", "sergi").append("anys", 45).append("telf", "321-654-987");
            coll.insertOne(doc);

            //afegeix un document encastat
            doc = new Document("nom", "Joan").append("anys", 29).append("info",
                    new Document("email", "joan@mail.com").append("telf", "876-134-667"));
            coll.insertOne(doc);

            //inserció múltiple creant l'ID
            List<Document> documents = new ArrayList<>();
            for (int i = 0; i < 10; i++) {
                documents.add(new Document("key", UUID.randomUUID().toString()));
            }
            coll.insertMany(documents);

            //consulta un conjunt de documents
            try (MongoCursor<Document> cursor = coll.find().iterator()) {
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
