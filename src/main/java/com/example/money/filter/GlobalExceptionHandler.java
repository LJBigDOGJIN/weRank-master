package com.example.money.filter;

import cn.hutool.db.handler.StringHandler;
import com.example.money.pojo.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.sql.SQLIntegrityConstraintViolationException;


//@ControllerAdvice(annotations = {RestController.class, Service.class})
@ControllerAdvice
@ResponseBody
public class GlobalExceptionHandler {
    @ExceptionHandler(TokenException.class)
    public Result exceptionHandler(TokenException e){
        Result result=new Result();
        result.setCode("000");
        result.setStatus("000");
        result.setMessage(e.getMessage());
        return result;
    }
}