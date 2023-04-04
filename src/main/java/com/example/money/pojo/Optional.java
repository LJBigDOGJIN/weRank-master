package com.example.money.pojo;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;

import java.sql.Date;

/**
 * @author lzx
 * @date 2023-02-07 22:33
 * @description
 */
public class Optional {

    private String name;
    private String code;
    @TableField(value = "netWorth")
    private String netWorth;
    @TableField(value = "dayGrowth")
    private String dayGrowth;
    @TableField(value = "createTime")
    private Date createTime;
    @TableField(value = "openid")
    private String openid;

    private int status;

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public Optional() {
    }

    public String getOpenid() {
        return openid;
    }

    public void setOpenid(String openid) {
        this.openid = openid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getNetWorth() {
        return netWorth;
    }

    public void setNetWorth(String netWorth) {
        this.netWorth = netWorth;
    }

    public String getDayGrowth() {
        return dayGrowth;
    }

    public void setDayGrowth(String dayGrowth) {
        this.dayGrowth = dayGrowth;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }




}
