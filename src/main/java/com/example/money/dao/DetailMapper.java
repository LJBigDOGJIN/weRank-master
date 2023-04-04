package com.example.money.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.money.pojo.Detail;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @author lzx
 * @date 2023-01-30 16:06
 * @description
 */
@Mapper
public interface DetailMapper extends BaseMapper<Detail> {
    @Select("select f_name from fundsinfo where type like '%指数%'")
    List<String> getNum();
}
