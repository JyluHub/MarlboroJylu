package com.jylu.utils;

import redis.clients.jedis.Jedis;

/**
 * ClassName: RedisUtil <br/>
 * Description: RedisUtil <br/>
 * Date: 17-4-2 下午8:19 <br/>
 * Company: Jylu <br/>
 *
 * @author lujiayun(1170798780@qq.com) <br/>
 * @version 1.0 <br/>
 */
public class RedisUtil {

    private RedisUtil(){}

    public static Jedis getRedisConnection(){
        return new Jedis("localhost", 6379);
    }
    
    public static void closeRedisConnection(Jedis jRedis){
    	jRedis.close();
    }
}