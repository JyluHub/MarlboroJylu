package com.jylu.utils;

import java.util.List;

import org.bson.BsonDocument;
import org.bson.Document;
import org.bson.codecs.configuration.CodecRegistry;
import org.bson.conversions.Bson;

import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

/**
 * ClassName: MongoDBUtil <br/>
 * Description: Mongo工具类 <br/>
 * Date: 17-3-29 下午10:29 <br/>
 * Company: Jylu <br/>
 *
 * @author lujiayun(1170798780@qq.com) <br/>
 * @version 1.0 <br/>
 */
public class MongoDBUtil {

    private MongoDBUtil(){}
    
    public static MongoClient getMongoConnection(){
    	return new MongoClient("localhost", 27017);
    }
    
    public static void closeMongoConnection(MongoClient mongo){
    	mongo.close();
    }

    /**
     * 获取MongoCollection
     * @return
     */
    public static MongoCollection<Document> getMongoCollection(){
        MongoClient mongoClient = getMongoConnection();
        MongoDatabase mongoDatabase = mongoClient.getDatabase("jylu");
        MongoCollection<Document> mongoCollection = mongoDatabase.getCollection("student");
        closeMongoConnection(mongoClient);
        return mongoCollection;
    }

    /**
     * 批量插入
     * @param mongoCollection
     * @param documentList
     */
    public static void insertManyDocument(MongoCollection<Document> mongoCollection, List<Document> documentList){
        mongoCollection.insertMany(documentList);
    }

    /**
     * 插入一条数据
     * @param mongoCollection
     * @param document
     */
    public static void insertOne(MongoCollection<Document> mongoCollection, Document document){
        mongoCollection.insertOne(document);
    }

    public static List<Document> findList(MongoCollection<Document> mongoCollection){
        Bson filter = new Bson() {
            @Override
            public <TDocument> BsonDocument toBsonDocument(Class<TDocument> tDocumentClass, CodecRegistry codecRegistry) {
                return null;
            }
        };
        mongoCollection.find(filter);
        return null;
    }
}