package com.lujiayun.test;

import com.jylu.utils.RedisUtil;
import org.junit.jupiter.api.Test;

/**
 * ClassName: RedisTest <br/>
 * Description: RedisTest <br/>
 * Date: 17-4-2 下午8:21 <br/>
 * Company: Jylu <br/>
 *
 * @author lujiayun(1170798780@qq.com) <br/>
 * @version 1.0 <br/>
 */
public class RedisTest {

    @Test
    public void getRedisConn(){
        RedisUtil.getRedisConnection();
    }
}