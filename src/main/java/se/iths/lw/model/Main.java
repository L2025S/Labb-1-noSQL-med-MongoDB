package se.iths.lw.model;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;
import se.iths.lw.dao.MovieDAOMongo;

public class Main {
    static void main(String[] args) {


        String uri = "mongodb+srv://<database_username>:<db_password>.tpnei1u.mongodb.net/?appName=Cluster0";
        try (MongoClient client = MongoClients.create(uri)) {
            MongoDatabase database = client.getDatabase("my_database");
            MongoCollection<Document> collection = database.getCollection("movies");

            MovieDAOMongo movieDAOmongo = new MovieDAOMongo(collection);

            movieDAOmongo.insert("Harry Potter and the Sorcerer's Stone", 2001);
            movieDAOmongo.insert("Harry Potter and the Chamber of Secrets", 2002);

            movieDAOmongo.findAll().forEach(System.out::println);

        }
    }
}
