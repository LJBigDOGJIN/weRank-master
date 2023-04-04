package com.example.money.filter;

import com.auth0.jwt.exceptions.TokenExpiredException;

/**
 * @author lzx
 * @date 2022-11-15 16:29
 * @description
 */
public class TokenException extends TokenExpiredException {
    public TokenException(String message) {
        super(message);
    }
//    public TokenException(){
//        super();
//    }
//    public TokenException(String message){
//        super(message);
//    }
}
