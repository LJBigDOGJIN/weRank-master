package com.example.money.pojo;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;

/**
 * @author lzx
 * @date 2023-01-30 15:53
 * @description
 */
@TableName(value = "details")
public class Detail {

    private String code;
    private String name;
    private String type;
    @TableField(value = "netWorth")
    private String netWorth;
    @TableField(value = "expectWorth")
    private String expectWorth;
    @TableField(value = "totalWorth")
    private String totalWorth;
    @TableField(value = "expectGrowth")
    private String expectGrowth;
    @TableField(value = "dayGrowth")
    private String dayGrowth;
    @TableField(value = "lastWeekGrowth")
    private String lastWeekGrowth;
    @TableField(value = "lastMonthGrowth")
    private String lastMonthGrowth;
    @TableField(value = "lastSixMonthGrowth")
    private String lastSixMonthGrowth;
    @TableField(value = "lastYearGrowth")
    private String lastYearGrowth;
    @TableField(value = "buyMin")
    private String buyMin;
    @TableField(value = "buySourceRate")
    private String buySourceRate;
    @TableField(value = "buyRate")
    private String buyRate;
    @TableField(value = "manager")
    private String manager;
    @TableField(value = "fundScale")
    private String fundScale;
    @TableField(value = "netWorthDate")
    private String netWorthDate;
    @TableField(value = "expectWorthDate")
    private String expectWorthDate;

    private int status;
    public Detail() {
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
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

    public String getNetWorth() {
        return netWorth;
    }

    public void setNetWorth(String netWorth) {
        this.netWorth = netWorth;
    }

    public String getExpectWorth() {
        return expectWorth;
    }

    public void setExpectWorth(String expectWorth) {
        this.expectWorth = expectWorth;
    }

    public String getTotalWorth() {
        return totalWorth;
    }

    public void setTotalWorth(String totalWorth) {
        this.totalWorth = totalWorth;
    }

    public String getExpectGrowth() {
        return expectGrowth;
    }

    public void setExpectGrowth(String expectGrowth) {
        this.expectGrowth = expectGrowth;
    }

    public String getDayGrowth() {
        return dayGrowth;
    }

    public void setDayGrowth(String dayGrowth) {
        this.dayGrowth = dayGrowth;
    }

    public String getLastWeekGrowth() {
        return lastWeekGrowth;
    }

    public void setLastWeekGrowth(String lastWeekGrowth) {
        this.lastWeekGrowth = lastWeekGrowth;
    }

    public String getLastMonthGrowth() {
        return lastMonthGrowth;
    }

    public void setLastMonthGrowth(String lastMonthGrowth) {
        this.lastMonthGrowth = lastMonthGrowth;
    }

    public String getLastSixMonthGrowth() {
        return lastSixMonthGrowth;
    }

    public void setLastSixMonthGrowth(String lastSixMonthGrowth) {
        this.lastSixMonthGrowth = lastSixMonthGrowth;
    }

    public String getLastYearGrowth() {
        return lastYearGrowth;
    }

    public void setLastYearGrowth(String lastYearGrowth) {
        this.lastYearGrowth = lastYearGrowth;
    }

    public String getBuyMin() {
        return buyMin;
    }

    public void setBuyMin(String buyMin) {
        this.buyMin = buyMin;
    }

    public String getBuySourceRate() {
        return buySourceRate;
    }

    public void setBuySourceRate(String buySourceRate) {
        this.buySourceRate = buySourceRate;
    }

    public String getBuyRate() {
        return buyRate;
    }

    public void setBuyRate(String buyRate) {
        this.buyRate = buyRate;
    }

    public String getManager() {
        return manager;
    }

    public void setManager(String manager) {
        this.manager = manager;
    }

    public String getFundScale() {
        return fundScale;
    }

    public void setFundScale(String fundScale) {
        this.fundScale = fundScale;
    }

    public String getNetWorthDate() {
        return netWorthDate;
    }

    public void setNetWorthDate(String netWorthDate) {
        this.netWorthDate = netWorthDate;
    }

    public String getExpectWorthDate() {
        return expectWorthDate;
    }

    public void setExpectWorthDate(String expectWorthDate) {
        this.expectWorthDate = expectWorthDate;
    }

    @Override
    public String toString() {
        return "detail{" +
                ", code='" + code + '\'' +
                ", name='" + name + '\'' +
                ", type='" + type + '\'' +
                ", netWorth='" + netWorth + '\'' +
                ", expectWorth='" + expectWorth + '\'' +
                ", totalWorth='" + totalWorth + '\'' +
                ", expectGrowth='" + expectGrowth + '\'' +
                ", dayGrowth='" + dayGrowth + '\'' +
                ", lastWeekGrowth='" + lastWeekGrowth + '\'' +
                ", lastMonthGrowth='" + lastMonthGrowth + '\'' +
                ", lastSixMonthGrowth='" + lastSixMonthGrowth + '\'' +
                ", lastYearGrowth='" + lastYearGrowth + '\'' +
                ", buyMin='" + buyMin + '\'' +
                ", buySourceRate='" + buySourceRate + '\'' +
                ", buyRate='" + buyRate + '\'' +
                ", manager='" + manager + '\'' +
                ", fundScale='" + fundScale + '\'' +
                ", netWorthDate='" + netWorthDate + '\'' +
                ", expectWorthDate='" + expectWorthDate + '\'' +
                '}';
    }
}
