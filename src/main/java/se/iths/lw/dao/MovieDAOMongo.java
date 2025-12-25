package se.iths.lw.dao;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.result.InsertOneResult;
import org.bson.Document;
import org.bson.types.ObjectId;


import java.util.ArrayList;
import java.util.List;

public class MovieDAOMongo implements MovieDAO{
    private final MongoCollection<Document> collection;

    public MovieDAOMongo(MongoCollection<Document> collection) {
        this.collection = collection;
    }

    @Override
    public void insert (String title, int year) {

        Document movie = new Document ("_id",new ObjectId())
                .append("title",title)
                .append("year", year);

        InsertOneResult result =collection.insertOne(movie);

        if (result.wasAcknowledged()) {
            System.out.println("Inserted movie with title: " + title + " (ID: " + result.getInsertedId() + ")");
        } else {
            System.out.println("Failed to insert movie with title: " + title );
        }

    }

    @Override
    public List<Document> findAll() {
        List<Document> movies = collection.find().into(new ArrayList<Document>());

        return movies;
    }

}
