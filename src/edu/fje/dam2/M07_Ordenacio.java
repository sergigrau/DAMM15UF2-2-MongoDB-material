package edu.fje.dam2;

import com.mongodb.*;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import static com.mongodb.client.model.Filters.*;
import static com.mongodb.client.model.Sorts.*;

import org.bson.Document;


/**
 * Classe que mostra com altres operacions
 * com operar amb cada document recuperat o ordenar
 *
 * @author sergi.grau@fje.edu
 * @version 1.0 08.03.2016
 */

public class M07_Ordenacio {

    private final static String HOST = "127.0.0.1";
    private final static int PORT = 27017;

    public static void main(String[] args) {

        MongoClient mongoClient = new MongoClient(HOST, PORT);
        MongoDatabase db = mongoClient.getDatabase("exemple");
        MongoCollection<Document> col1 = db.getCollection("usuaris");

        Document doc = col1.find(exists("nom")).sort(descending("nom")).first();
        System.out.println(doc.toJson());


        Block<Document> printBlock = new Block<Document>() {
            @Override
            public void apply(final Document document) {
                System.out.println(document.toJson());
            }
        };

        col1.find(and(gt("anys", 20), lte("anys", 100))).forEach(printBlock);

    }
}