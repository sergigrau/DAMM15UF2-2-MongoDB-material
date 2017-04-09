package edu.fje.dam2;

import com.mongodb.*;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import static com.mongodb.client.model.Projections.*;

import org.bson.Document;


/**
 * Classe que mostra com realitzar projeccions
 *
 * @author sergi.grau@fje.edu
 * @version 1.0 08.03.2016
 */

public class M08_Projeccions{

    private final static String HOST = "127.0.0.1";
    private final static int PORT = 27017;

    public static void main(String[] args) {

        MongoClient mongoClient = new MongoClient(HOST, PORT);
        MongoDatabase db = mongoClient.getDatabase("exemple");
        MongoCollection<Document> col1 = db.getCollection("usuaris");

        Document doc;
        //exclusió del ID
        try (MongoCursor<Document> cursor = col1.find().projection(excludeId()).iterator()) {
            while (cursor.hasNext()) {
                doc = cursor.next();
                System.out.println(doc.toJson());
            }
        }
        //Exclusió de nom i anys
        try (MongoCursor<Document> cursor = col1.find().projection(exclude("nom","anys")).iterator()) {
            while (cursor.hasNext()) {
                doc = cursor.next();
                System.out.println(doc.toJson());
            }
        }

    }
}