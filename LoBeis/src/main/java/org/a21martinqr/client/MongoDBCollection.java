package org.a21martinqr.client;

import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.mongodb.client.MongoDatabase;

public class MongoDBCollection {

    private static MongoClient mongoClient;
    private static MongoDatabase mongoDatabase;
    private static String connectionString = "mongodb://localhost:27017/";

    public static MongoDatabase getConexion() {
        if (mongoClient == null) {
            MongoClientURI uri = new MongoClientURI(connectionString);
            mongoClient = new MongoClient(uri);
            mongoDatabase = mongoClient.getDatabase("LoBeis");
        }
        return mongoDatabase;
    }
}
