package com.example.money.service.Impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.money.dao.DateDetailsMapper;
import com.example.money.pojo.DateDetails;
import com.example.money.service.DateDetailsService;
import org.apache.commons.lang3.StringUtils;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author lzx
 * @date 2023-01-28 23:58
 * @description
 */
@Service
public class DateDetailsServiceImpl extends ServiceImpl<DateDetailsMapper, DateDetails> implements DateDetailsService {
    @Override
    public Map<String,Object> getDetail(String num,String ways) {
        CloseableHttpClient httpClient = HttpClientBuilder.create().build();
        HttpGet get = new HttpGet("https://api.doctorxiong.club/v1/fund/detail?code="+num);
        try{
            //这里可以设置请求参数，token
            HttpResponse response = httpClient.execute(get);//执行获取响应
            if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK){//根据状态码处理
                //返回字符串
                String res = EntityUtils.toString(response.getEntity(), StandardCharsets.UTF_8);
                JSONObject object=JSON.parseObject(res);
                String data = object.get("data").toString();
                JSONObject obt=JSON.parseObject(data);
                List list1=JSONArray.parseArray(obt.get("netWorthData").toString());
                System.out.println("这是日期数组"+list1);
                List<DateDetails> list=new ArrayList<>();
                Object startDay = list1.get(0);
                //筛选时间
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyy-MM-dd");
                LocalDate date=LocalDate.now();
                LocalDate tya =null;
                if (ways.equals("近一周")){
                    tya=date.minusWeeks(1);
                }
                else if(ways.equals("近一月")){
                    tya=date.minusMonths(1);
                }else if (ways.equals("近半年")){
                    tya=date.minusMonths(6);
                }else if(ways.equals("近一年")){
                    tya=date.minusYears(1);
                }
                for (int i=0;i< list1.size();i++){
                    DateDetails details=new DateDetails();
                    List infoList=JSONArray.parseArray(list1.get(i).toString());
                    String o = infoList.get(0).toString();
                    LocalDate localDate = LocalDate.parse(o, formatter);
                    if (localDate.isAfter(tya)) {
                        details.setId(i);
                        details.setDate(infoList.get(0).toString());
                        details.setPrice(infoList.get(1).toString());
                        details.setRate(infoList.get(2).toString());
                        details.setText(infoList.get(3).toString());
                        list.add(details);
                    }
                }
                Map<String,Object> map=new HashMap<>();
                map.put("list",list);
                map.put("startDay",startDay);
                return map;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
    @Override
    public Object getHoldDetail(String num) {
        float percent= (float) 0.01;
        CloseableHttpClient httpClient = HttpClientBuilder.create().build();
        HttpGet get = new HttpGet("https://api.doctorxiong.club/v1/fund/position?code="+num+"&token=hZ35hdndlP");
        try{
            //这里可以设置请求参数，token
            HttpResponse response = httpClient.execute(get);//执行获取响应
            if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK){//根据状态码处理
                //返回字符串
                String res = EntityUtils.toString(response.getEntity(), StandardCharsets.UTF_8);
                JSONObject object=JSON.parseObject(res);
                String data = object.get("data").toString();
                JSONObject obt=JSON.parseObject(data);
                System.out.println(obt);
//                String stock = obt.get("stock").toString();
//                String stock = obt.get("stock").toString();
                String s=null;
                String c=null;
                String b=null;
                String t=null;
                float stockRate=0;
                float cashRate=0;
                float bondRate=0;
                float total=0;
                if (obt.get("stock")==null){
                    stockRate=0;
                }else {
                    s=obt.get("stock").toString().replace("%","");
                }
                if (obt.get("cash")==null){

                }else {
                    c=obt.get("cash").toString().replace("%","");
                }
                if (obt.get("bond")==null){

                }else {
                    b=obt.get("bond").toString().replace("%","");
                }
                if (obt.get("total")==null){

                }else {
                    t=obt.get("total").toString().replace("%","");
                }
                if(isNumber(s)){
                    System.out.println("正确");
                    stockRate = Float.parseFloat(s);
                }else {
                    System.out.println("错误");
                    stockRate=0;
                }
                if(isNumber(c)){
                    cashRate = Float.parseFloat(c);
                }else {
                    cashRate=0;
                }
                if(isNumber(b)){
                    bondRate = Float.parseFloat(b);
                }else {
                    bondRate=0;
                }
                if(isNumber(t)){
                    total = Float.parseFloat(t);
                }else {
                    total=0;
                }


                float stock=Math.abs(total*stockRate*percent);
                float cash=Math.abs(total*cashRate*percent);
                float bond=Math.abs(total*bondRate*percent);
                float another=Math.abs(total-stock-cash-bond);
                String v = Float.toString(Math.abs(100 - stockRate - cashRate - bondRate));
                int i = v.indexOf(".");
                String peranother =v.substring(0,i+3)+"%";
                Map<String,Float> map1=new HashMap<>();
                map1.put("stock",stock);
                map1.put("cash",cash);
                map1.put("bond",bond);
                map1.put("another",another);
                List<Object> list=new ArrayList();
                list.add(map1);
                Map<String,Object> map=new HashMap<>();
                map.put("num",list);
                map.put("holdInfo",obt);
                map.put("peranother",peranother);
                return map;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
    private boolean isNumber(String str){
               if(StringUtils.isEmpty(str)){
                      return false;
                    }
                 String reg = "\\d+(\\.\\d+)?";
               return str.matches(reg);

    }
}
