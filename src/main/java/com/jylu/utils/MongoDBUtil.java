package com.jylu.utils;

import com.mongodb.MongoClient;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;

import java.util.ArrayList;
import java.util.List;

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

    public static void main(String[] args){
        MongoClient mongoClient = new MongoClient("localhost", 27017);
        MongoDatabase mongoDatabase = mongoClient.getDatabase("MarlboroJylu");
        MongoCollection<Document> mongoCollection = mongoDatabase.getCollection("user");
//        Document document;
//        List<Document> list = new ArrayList<>(100000);
//        for(int index = 1; index <= 100000; index++){
//            document = new Document("_id", index).append("name", "lujiayun"+index);
//            list.add(document);
//        }
//        mongoCollection.insertMany(list);
//        System.out.println("插入10万条数据成功");
        FindIterable<Document> iterable = mongoCollection.find();
        MongoCursor<Document> cursor = iterable.iterator();
        while(cursor.hasNext()){
            System.out.println(cursor.next());
        }
    }
}