package com.example.money.controller;

import cn.binarywang.wx.miniapp.api.WxMaService;
import cn.binarywang.wx.miniapp.bean.WxMaJscode2SessionResult;
import cn.hutool.json.JSONObject;
import com.alibaba.druid.sql.ast.statement.SQLForeignKeyImpl;
import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.example.money.dao.userMapper;
import com.example.money.filter.TokenException;
import com.example.money.pojo.Result;
import com.example.money.pojo.User;
import com.example.money.service.Impl.userServiceImpl;
import com.example.money.service.userService;

import com.example.money.utils.JWTUtil;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import me.chanjar.weixin.common.error.WxErrorException;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.info.InfoProperties;
import org.springframework.util.DigestUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

/**
 * @author lzxlllllll
 * @date 2023-01-11 15:22
 * @description
 */
@RestController
@RequestMapping("/user")
public class userController {

    @Value("${Bath.Path}")
    private  String basePath;
    @Autowired
    private userServiceImpl userService;

    @Value("${Bath.secret}")
    private  String secret;
    @Autowired
    private userMapper userMapper;
    @PostMapping ("/token")
    public String getWxInfo(@RequestBody User user){
        System.out.println(user);
        Map<String, Object> userInfo = userService.getUserInfo(user,secret);
        return userInfo.get("token").toString();
    }
//@PostMapping ("/picUpload")
//public String upload(MultipartFile file){
////http://tmp/dkfbaBFCEj4f99f2373911acf8c065bc0cd6802a5bb2.jpeg
//    String filename = file.getOriginalFilename();
//    System.out.println(filename);
//    String suffix = filename.substring(filename.lastIndexOf("."));
//    String newFilename = UUID.randomUUID().toString()+suffix;
//    String replace = newFilename.replace("-","");
//    File dir=new File(basePath);
//    if (!dir.exists()){
//        dir.mkdir();
//    }
//    try {
//        file.transferTo(new File(basePath+replace));
//    } catch (IOException e) {
//        throw new RuntimeException(e);
//    }
//
//    return basePath+replace;
//}
    @PostMapping ("/login")
     public Map<String,Object> login(@RequestBody User user) {
     Map<String, Object> userInfo = userService.getUserInfo(user,secret);
        String openid = userInfo.get("openid").toString();
        System.out.println(openid+"oooooooooooooo");
        userMapper.save1(openid,user.getImg(),user.getName());
//        String openid = userInfo.get("openid").toString();
//        User user1 = userMapper.getInfo(openid);
    Map<String, Object> loginInfo = new HashMap<>();
    loginInfo.put("token", userInfo.get("token"));
    loginInfo.put("etime", userInfo.get("etime"));
              return loginInfo;
    }
@GetMapping("/modify")
    public String modify(User user){

        return null;
}

}
