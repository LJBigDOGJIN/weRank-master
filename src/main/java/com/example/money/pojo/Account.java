package com.example.money.pojo;

/**
 * @author lzx
 * @date 2023-03-20 10:29
 * @description
 */
public class Account {
    private String  appid;
    private String surplus;
    private String income;

    public Account() {
    }

    public String getAppid() {
        return appid;
    }

    public void setAppid(String appid) {
        this.appid = appid;
    }

    public String getSurplus() {
        return surplus;
    }

    public void setSurplus(String surplus) {
        this.surplus = surplus;
    }

    public String getIncome() {
        return income;
    }

    public void setIncome(String income) {
        this.income = income;
    }
}
