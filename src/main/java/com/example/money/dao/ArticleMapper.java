package com.example.money.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.money.pojo.Article;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author lzx
 * @date 2023-04-03 22:22
 * @description
 */
@Mapper
public interface ArticleMapper extends BaseMapper<Article> {
}
