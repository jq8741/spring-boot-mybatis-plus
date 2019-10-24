package com.jh.springbootmybatisplus.dao;

import com.jh.springbootmybatisplus.entity.User;
import com.jh.springbootmybatisplus.entity.login.UserLogin;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface UserDao {

    /**
     * 用户登录
     * @param logins
     * @return
     */
    User userlogin(UserLogin logins);

    String getUserPassWord(String userName);
}
