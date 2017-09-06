package com.lujiayun.dao;

import com.jylu.dao.mapper.UsersMapper;
import com.jylu.entity.Users;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * ClassName: UserDaoTest <br/>
 * Description: UserDaoTest <br/>
 * Date: 17-4-4 下午8:34 <br/>
 * Company: Jylu <br/>
 *
 * @author lujiayun(1170798780@qq.com) <br/>
 * @version 1.0 <br/>
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:conf/application.xml"})
public class UserDaoTest {

    @Autowired
    private UsersMapper usersMapper;

    @Test
    public void testInsert() throws Exception {
        Users users = new Users();
        users.setAge(25);
        users.setEmail("15000@qq.com");
        users.setInterest("分布式系统");
        users.setPassword("scronaldo1994");
        users.setUserName("lujiayun");
        users.setNikeName("marlboro");
        usersMapper.insertUser(users);
        System.out.println(users.toString());
    }

    @Test
    public void testSelectOne() throws Exception {
        Users users = usersMapper.selectUserById(1);
        System.out.println(users);
    }

}