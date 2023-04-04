package com.example.money.pojo;

/**
 * @author lzx
 * @date 2023-01-28 23:37
 * @description
 */
public class DateDetails {
    private Integer id;
    private String date;
    private String price;
    private String rate;
    private String text;
    private String sixMonth;

    public DateDetails() {
    }

    public DateDetails(Integer id, String date, String price, String rate, String text) {
        this.id = id;
        this.date = date;
        this.price = price;
        this.rate = rate;
        this.text = text;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getRate() {
        return rate;
    }

    public void setRate(String rate) {
        this.rate = rate;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    @Override
    public String toString() {
        return "Details{" +
                "id=" + id +
                ", date='" + date + '\'' +
                ", price='" + price + '\'' +
                ", rate='" + rate + '\'' +
                ", text='" + text + '\'' +
                '}';
    }
}
