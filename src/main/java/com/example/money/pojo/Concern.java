package com.example.money.pojo;

import com.baomidou.mybatisplus.annotation.TableField;

/**
 * @author lzx
 * @date 2023-03-20 10:36
 * @description
 */
public class Concern {
    private String name;
    private String code;
    private String hold;
    @TableField(value = "netWorth")
    private String netWorth;
    @TableField(value = "dayGrowth")
    private String dayGrowth;
    @TableField(value = "creatTime")
    private String creatTime;
    @TableField(value = "appid")

    private String appid;

    public Concern() {
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

    public String getHold() {
        return hold;
    }

    public void setHold(String hold) {
        this.hold = hold;
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

    public String getCreatTime() {
        return creatTime;
    }

    public void setCreatTime(String creatTime) {
        this.creatTime = creatTime;
    }

    public String getAppid() {
        return appid;
    }

    public void setAppid(String appid) {
        this.appid = appid;
    }
}
