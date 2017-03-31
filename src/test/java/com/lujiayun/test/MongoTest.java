package com.lujiayun.test;

import com.jylu.utils.MongoDBUtil;
import com.mongodb.client.MongoCollection;
import org.bson.Document;
import org.junit.jupiter.api.Test;

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
    public void testGetMongo(){
        MongoCollection<Document> collection = MongoDBUtil.getMongoCollection();
        System.out.println(collection.getNamespace());
    }
}