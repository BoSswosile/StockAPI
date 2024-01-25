package org.example;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static com.mongodb.client.model.Filters.eq;

public class Main {
    public static void main(String[] args) throws IOException {
        Logger logger = LoggerFactory.getLogger("StockAPI");
        logger.error("Logging an Error");
        Properties prop = new Properties();
        InputStream input = null;
        input = new FileInputStream("src/config.properties");
        prop.load(input);
        String uri = prop.getProperty("uri");
        try (MongoClient mongoClient = MongoClients.create(uri)) {
            MongoDatabase database = mongoClient.getDatabase("stockdb");
            MongoCollection<Document> collection = database.getCollection("stock");
            Document myDoc = collection.find(eq("name", "Table")).first();
        }
    }
}