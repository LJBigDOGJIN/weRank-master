package com.example.money.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.example.money.pojo.Article;
import com.example.money.service.Impl.ArticleServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author lzx
 * @date 2023-04-03 22:32
 * @description
 */
@RestController
@RequestMapping("/article")
public class ArticleController {


    @Autowired
    private ArticleServiceImpl articleService;

    @GetMapping("/txt")
    public List<Article> getTxtList(){
        List<Article> list = articleService.list();
        return list;
    }
    @GetMapping("/list")
    public List<Article> getList(int type){
        LambdaQueryWrapper<Article> queryWrapper=new LambdaQueryWrapper<>();
        queryWrapper.eq(Article::getType,type);
        List<Article> list = articleService.list(queryWrapper);
//        List<Article> list = articleService.list();
        return list;
    }
    @GetMapping("/txtnum")
    public String addNum(int id,int times){
        LambdaUpdateWrapper<Article> updateWrapper=new LambdaUpdateWrapper<>();
        updateWrapper.eq(Article::getId,id).set(Article::getTimes,++times);
        boolean update = articleService.update(updateWrapper);
        return "success";
    }
    @GetMapping("/load")
    public Article getTxt(int id){
        LambdaQueryWrapper<Article> queryWrapper=new LambdaQueryWrapper<>();
        queryWrapper.eq(Article::getId,id);
        Article one = articleService.getOne(queryWrapper);
        return one;
    }
}
