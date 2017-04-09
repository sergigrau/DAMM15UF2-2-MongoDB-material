package edu.fje.dam2;

import com.mongodb.MongoClient;
import com.mongodb.client.MongoDatabase;

/**
 * Classe que connecta amb MongoDB utilitzant el driver 3.x
 *
 * @author sergi.grau@fje.edu
 * @version 1.0 25.02.2016
 */
public class M01_ConnexioMongoDB {

    private final static String HOST = "127.0.0.1";
    private final static int PORT = 27017;

    public static void main(String args[]) {
        try {
            MongoClient mongoClient = new MongoClient(HOST, PORT);
            MongoDatabase db = mongoClient.getDatabase("exemple");
            System.out.println("Connectat a la base de dades");
        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " +e.getMessage());
        }
    }
}
