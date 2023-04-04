package com.example.money.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.money.pojo.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;


/**
 * @author lzx
 * @date 2023-01-11 14:48
 * @description
 */
@Mapper
public interface userMapper extends BaseMapper<User> {
    @Insert("insert into userinfo (openid,img,name,createtime) values(#{openid},#{img},#{name},SYSDATE())")
    void save1(String openid,String img,String name);
    @Select("select img,name,openid from userinfo where openid=#{openid}")
     User getInfo(String openid);
}
