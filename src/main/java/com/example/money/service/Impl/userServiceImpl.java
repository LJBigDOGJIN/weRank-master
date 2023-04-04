package com.example.money.service.Impl;

import cn.hutool.http.HttpUtil;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.money.dao.userMapper;
import com.example.money.pojo.User;
import com.example.money.service.userService;
import com.example.money.utils.JWTUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author lzx
 * @date 2023-01-11 15:19
 * @description
 */
@Service
public class userServiceImpl extends ServiceImpl<userMapper, User> implements userService {

  @Autowired
  private userMapper userMapper;


  public User getUser(String openid){
    User info = userMapper.getInfo(openid);
    return info;
  }
    public Map<String,Object> getUserInfo(User user,String secret){
      String  url = "https://api.weixin.qq.com/sns/jscode2session?grant_type=authorization_code&appid=wxed03c12b08714e64&secret=1ba90cdacdcb2a28aaa103f61a4fe908"+ "&js_code=" +user.getCode();
      String s = HttpUtil.get(url);
      JSONObject jsonObject= JSONUtil.parseObj(s);
        System.out.println(jsonObject);
        String openid = jsonObject.getStr("openid");
        String session_key = jsonObject.getStr("session_key");
        Map<String, Object> tokenInfo = JWTUtil.createToken(openid, secret);
        Map<String,Object> map=new HashMap<>();
        map.put("token",tokenInfo.get("token").toString());
        map.put("openid",openid);
        map.put("session_key",session_key);
        map.put("etime",tokenInfo.get("etime").toString());
        return map;
    }


}
