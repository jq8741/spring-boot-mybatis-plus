package com.jh.springbootmybatisplus.service.impl;


import com.jh.springbootmybatisplus.dao.UserDao;
import com.jh.springbootmybatisplus.exception.UserNameException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;


import java.util.Arrays;
import java.util.List;

@Service
public class UserDetailServiceImpl implements UserDetailsService {

    @Autowired
    UserDao userDao;

    @Override
    public UserDetails loadUserByUsername(String userName)throws UsernameNotFoundException{


        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        String pw = userDao.getUserPassWord(userName);
        if(pw==null||pw.equals(" ")){
            throw new UserNameException("用户名为空或不存在！");
        }
        String password = encoder.encode(pw);
//        String password = "$2a$10$udnNGFNVZrxRAcmiveEZl.6R87CfjqPkSrqRCRhqUuTKn.Aq/Djt6";
        return new User(userName,password,getAuthority());
    }

    private List getAuthority(){
        return Arrays.asList(new SimpleGrantedAuthority("ROLE_ADMIN"));
    }
}
