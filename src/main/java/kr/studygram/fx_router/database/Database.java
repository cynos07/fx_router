package kr.studygram.fx_router.database;

import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;

/**
 * Created by production on 2017-04-06.
 */
public enum Database {
    INSTANCE;
    private final String DB_URL = "mongodb://" + SecureConfig.INSTANCE.getString("database.id") + ":" + SecureConfig.INSTANCE.getString("database.password") + "@studygram-shard-00-00-csfwe.mongodb.net:27017,studygram-shard-00-01-csfwe.mongodb.net:27017,studygram-shard-00-02-csfwe.mongodb.net:27017/" + SecureConfig.INSTANCE.getString("database.name") + "?ssl=true&replicaSet=studygram-shard-0&authSource=admin";
    private final String DEFAULT_DB_NAME = "fx_router";

    private MongoClientURI uri;
    private MongoClient mongoClient;
    private MongoDatabase mongoDatabase;
    private MongoCollection<Document> collection;
    private Document doc;

    Database() {
        System.out.println(DB_URL);
        uri = new MongoClientURI(DB_URL);
        mongoClient = new MongoClient(uri);
        mongoDatabase = mongoClient.getDatabase(DEFAULT_DB_NAME);
    }

    private void createCountCollection(String collectionName) {
        Document insertDoc = new Document();
        insertDoc.append("_id", collectionName);
        insertDoc.append("seq", 0);

        mongoDatabase.getCollection("counters").insertOne(insertDoc);

    }

    public void remove(String collection, Document doc)
    {
        this.collection = mongoDatabase.getCollection(collection);
        this.collection.deleteOne(doc);
    }

    public void insert(String collection, Document doc)
    {
        this.collection = mongoDatabase.getCollection(collection);
        Document searchCounters = new Document().append("_id", collection);
        if(mongoDatabase.getCollection("counters").count(searchCounters) == 0)
        {
            createCountCollection(collection);
        }
        doc.append("_id", getNextSequence(collection));
        this.collection.insertOne(doc);
    }

    public Object getNextSequence(String name)
    {
        Document searchQuery = new Document("_id", name);
        Document increase = new Document("seq", 1);
        Document updateQuery = new Document("$inc", increase);
        Document result = mongoDatabase.getCollection("counters").findOneAndUpdate(searchQuery, updateQuery);

        return result.get("seq");
    }
    public static Database getInstance()
    {
        return INSTANCE;
    }
}