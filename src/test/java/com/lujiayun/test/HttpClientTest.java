package com.lujiayun.test;

import com.jylu.utils.HttpClientUtil;
import org.junit.Test;

/**
 * ClassName: HttpClientTest <br/>
 * Description: 测试HttpClient <br/>
 * Date: 17-3-22 下午11:00 <br/>
 * Company: Jylu <br/>
 *
 * @author lujiayun(1170798780@qq.com) <br/>
 * @version 1.0 <br/>
 */
public class HttpClientTest {

    @Test
    public void HttpClientTest() {
        String responseContent = HttpClientUtil.getInstance()
                .sendHttpsGet("https://www.baidu.com");
        System.out.println(responseContent);
    }
}