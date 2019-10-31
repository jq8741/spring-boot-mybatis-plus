package com.jh.springbootmybatisplus.exception;

public class UserNameException extends RuntimeException {
    public UserNameException(String err){
        super(err);
    }
}
