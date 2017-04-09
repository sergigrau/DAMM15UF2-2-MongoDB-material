package edu.fje.dam2;

import com.mongodb.*;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;

import static com.mongodb.client.model.Accumulators.*;
import static com.mongodb.client.model.Aggregates.*;
import static com.mongodb.client.model.Filters.*;
import static com.mongodb.client.model.Projections.exclude;

import java.util.Arrays;
import java.util.Collections;

import org.bson.Document;


/**
 * Classe que mostra com realitzar agregadors
 *
 * @author sergi.grau@fje.edu
 * @version 1.0 08.03.2016
 */

public class M09_Agregadors {

    private final static String HOST = "127.0.0.1";
    private final static int PORT = 27017;

    public static void main(String[] args) {

        MongoClient mongoClient = new MongoClient(HOST, PORT);
        MongoDatabase db = mongoClient.getDatabase("exemple");
        MongoCollection<Document> col1 = db.getCollection("usuaris");

        Block<Document> printBlock = new Block<Document>() {
            @Override
            public void apply(final Document document) {
                System.out.println(document.toJson());
            }
        };

        //crea una nova propietat a una operació calculada de manera agregada
        col1.aggregate(Arrays.asList(
                match(gt("anys", 0)),
                project(Document.parse("{producte: {$multiply: ['$anys', 10]}}")))
        ).forEach(printBlock);

        Document doc;

        //calcula la suma dels anys
        try (MongoCursor<Document> cursor = col1.aggregate(Collections.
                singletonList(group(null, sum("anys", "$anys")))).iterator()) {
            while (cursor.hasNext()) {
                doc = cursor.next();
                System.out.println(doc.toJson());
            }
        }
    }
}