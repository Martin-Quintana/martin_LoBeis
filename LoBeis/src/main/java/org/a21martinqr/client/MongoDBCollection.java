package org.a21martinqr.client;

import com.mongodb.client.MongoDatabase;

public class MongoDBCollection {

    MongoDBCollection mongoClient = new MongoDBCollection("localhost", 27017);
    MongoDatabase database = mongoClient.getDatabase("LoBeis");

    public MongoDBCollection() {
    }


    public MongoDBCollection(String localhost, int i) {
    }

    public MongoDatabase getDatabase(String loBeis) {
        return database;
    }


}
