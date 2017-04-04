package com.jylu.dao;

import com.jylu.entity.Users;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

/**
 * ClassName: UsersMapper <br/>
 * Description: Employee的Mapper <br/>
 * Date: 17-3-27 下午10:15 <br/>
 * Company: Jylu <br/>
 *
 * @author lujiayun(1170798780@qq.com) <br/>
 * @version 1.0 <br/>
 */
public interface UsersMapper {

    /**
     * 根据ID查询用户
     * @param id
     * @return
     * @throws Exception
     */
    Users selectUserById(int id) throws Exception;

    /**
     * 插入用户
     * @param users
     * @throws Exception
     */
    void insertUser(Users users) throws Exception;

}