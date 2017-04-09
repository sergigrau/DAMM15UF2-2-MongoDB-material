package edu.fje.dam2;

import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.MongoIterable;

/**
 * Classe que crea, cerca i selecciona col·leccions
 * a MongoDB
 *
 * @author sergi.grau@fje.edu
 * @version 1.0 25.02.2016
 */

public class M02_Colleccions {
    private final static String HOST = "127.0.0.1";
    private final static int PORT = 27017;

    public static void main(String args[]) {
        try {
            MongoClient mongoClient = new MongoClient(HOST, PORT);
            MongoDatabase db = mongoClient.getDatabase("exemple");
            System.out.println("Connectat a la base de dades");
            //crea una col·leccio
            //db.createCollection("colleccio1");

            //recupera totes les colleccions existents
            MongoIterable<String> colleccions = db.listCollectionNames();
            MongoCursor<String> cursor = colleccions.iterator();
            while (cursor.hasNext()) {
                System.out.println(cursor.next());
            }
            //selecciona una collecció
            MongoCollection col = db.getCollection("usuaris");
        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
        }
    }
}


