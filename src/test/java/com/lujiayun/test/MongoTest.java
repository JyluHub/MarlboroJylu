package com.lujiayun.test;

import com.jylu.utils.MongoDBUtil;
import com.mongodb.client.MongoCollection;
import org.bson.Document;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

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
        Document document = new Document();
        document.append("_id", 4).append("name", "毕玄").append("age", 35);
        MongoDBUtil.insertOne(collection, document);
    }
}