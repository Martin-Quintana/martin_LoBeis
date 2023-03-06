package org.a21martinqr.client;

import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.mongodb.client.MongoDatabase;

public class MongoDBCollection {

    private static String host = "localhost";

    private static int port = 27017;

    private static String database = "LoBeis";

    private static MongoClient mongoClient;
    private static MongoDatabase mongoDatabase;
    private static String connectionString = "mongodb://localhost:27017/LoBeis";

    public static MongoDatabase getConexion() {
        if (mongoClient == null) {
            MongoClientURI uri = new MongoClientURI(connectionString);
            mongoClient = new MongoClient(uri);
            mongoDatabase = mongoClient.getDatabase("LoBeis");
        }
        return mongoDatabase;
    }

    public MongoDBCollection(String host, int port, String database) {
        this.host = host;
        this.port = port;
        this.database = database;
    }

    public static void cerrarConexion() {
        mongoClient.close();
    }
}
