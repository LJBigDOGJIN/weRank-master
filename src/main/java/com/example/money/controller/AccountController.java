package com.example.money.controller;

import com.example.money.pojo.Account;
import com.example.money.pojo.Concern;
import com.example.money.service.Impl.ConcernServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author lzx
 * @date 2023-03-20 10:34
 * @description
 */
@RestController
@RequestMapping("/account")
public class AccountController {

    @Autowired
    private ConcernServiceImpl concernService;

    @GetMapping("/one")
    private List<Concern> getTest(){
        List<Concern> list = concernService.list();
        return list;
    }
}
