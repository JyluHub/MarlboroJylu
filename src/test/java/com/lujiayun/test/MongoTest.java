package com.lujiayun.test;

import org.bson.Document;
import org.junit.Test;

import com.jylu.utils.MongoDBUtil;
import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;
import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

/**
 * ClassName: MongoTest <br/>
 * Description: MongoTest <br/>
 * Date: 17-3-31 下午9:36 <br/>
 * Company: Jylu <br/>
 *
 * @author lujiayun(1170798780@qq.com) <br/>
 * @version 1.0 <br/>
 */
public class MongoTest {

    @Test
    public void testGetMongoCollection(){
        MongoCollection<Document> collection = MongoDBUtil.getMongoCollection();
        System.out.println(collection.getNamespace());
    }

    @Test
    public void testInsertOne(){
        MongoCollection<Document> collection = MongoDBUtil.getMongoCollection();
        Document document = null;
        for(int i = 0; i < 20; i++){
        	document = new Document();
        	document.append("name", "jylu"+i).append("age", 22).append("likes", "Java");
        	MongoDBUtil.insertOne(collection, document);
        }
    }
    
    @Test
    public void testQuery(){
    	MongoClient mongo = MongoDBUtil.getMongoConnection();
    	MongoDatabase database = mongo.getDatabase("jylu");
    	MongoCollection<Document> collection = database.getCollection("student");
    	BasicDBObject queryObject = new BasicDBObject("name", "jylu");
    	DBObject result = (DBObject) collection.find(queryObject);
    	System.out.println(result);
    	MongoDBUtil.closeMongoConnection(mongo);
    }
}