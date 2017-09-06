package com.lujiayun.test;

import com.jylu.utils.DateUtil;
import org.junit.Test;

import java.util.Date;

/**
 * ClassName: DateTest <br/>
 * Description: 日期测试 <br/>
 * Date: 17-3-23 下午9:39 <br/>
 * Company: Jylu <br/>
 *
 * @author lujiayun(1170798780@qq.com) <br/>
 * @version 1.0 <br/>
 */
public class DateTest {

    @Test
    public void DateTests() {
        String StringDate = "2017-06-05 17:55:00";
        Date date = DateUtil.String2Date(StringDate);
        System.out.println(date);
        Date now = new Date();
        System.out.println(DateUtil.Date2String(now));
    }
}