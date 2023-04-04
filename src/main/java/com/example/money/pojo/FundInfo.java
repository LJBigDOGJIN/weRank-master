package com.example.money.pojo;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * @author lzx
 * @date 2023-01-10 16:33
 * @description
 */
@Data
@TableName(value = "fundsInfo")
public class FundInfo {

    private String fName;
    private String sName;
    private String name;
    private String type;
    private String qName;

    public FundInfo(String fName, String sName, String name, String type, String qName) {
        this.fName = fName;
        this.sName = sName;
        this.name = name;
        this.type = type;
        this.qName = qName;
    }

    public FundInfo() {
    }



    public String getfName() {
        return fName;
    }

    public void setfName(String fName) {
        this.fName = fName;
    }

    public String getsName() {
        return sName;
    }

    public void setsName(String sName) {
        this.sName = sName;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getqName() {
        return qName;
    }

    public void setqName(String qName) {
        this.qName = qName;
    }

    @Override
    public String toString() {
        return "pojo{" +

                ", fName='" + fName + '\'' +
                ", sName='" + sName + '\'' +
                ", name='" + name + '\'' +
                ", type='" + type + '\'' +
                ", qName='" + qName + '\'' +
                '}';
    }
}
