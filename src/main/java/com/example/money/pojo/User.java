package com.example.money.pojo;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.util.Date;

/**
 * @author lzx
 * @date 2023-01-11 14:44
 * @description
 */
@Data
@TableName("userinfo")
public class User {
    private Integer id;
    private String code;
    @TableField(value = "openid")
    private String openid;
//    @TableField(value = "sessionkey")
//    private String sessionkey;
    private String img;
    private String name;

    public User() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getOpenid() {
        return openid;
    }

    public void setOpenid(String openid) {
        this.openid = openid;
    }





    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", code='" + code + '\'' +
                ", openid='" + openid + '\'' +
                ", img='" + img + '\'' +
                ", name='" + name + '\'' +
                '}';
    }
}
