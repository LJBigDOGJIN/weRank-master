package com.example.money.service.Impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.money.dao.FundMapper;
import com.example.money.pojo.FundInfo;
import com.example.money.service.FundService;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author lzx
 * @date 2023-01-10 21:26
 * @description
 */
@Service
public class FundServiceImpl extends ServiceImpl<FundMapper, FundInfo> implements FundService {
    @Override
    public List<FundInfo> getInfo(){
        CloseableHttpClient httpClient = HttpClientBuilder.create().build();
        HttpGet get = new HttpGet("http://fund.eastmoney.com/js/fundcode_search.js");
        try{
            //这里可以设置请求参数，token
            HttpResponse response = httpClient.execute(get);//执行获取响应
            if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK){//根据状态码处理
                //返回字符串
                String res = EntityUtils.toString(response.getEntity(), StandardCharsets.UTF_8);
                String su = res.substring(0, 8);
                String info= res.replace(su," ").replace(";"," ");
//                Object parse = JSON.parse(info);
                JSONArray jsonArray=JSON.parseArray(info);
                List list1=JSONArray.parseArray(jsonArray.toJSONString());
//                System.out.println(jsonArray.get(1));
//                System.out.println(jsonArray.get);
                List<FundInfo> list=new ArrayList<>();

//                String o1 = (String) o.get(1);
                for (int i=0;i<list1.size();i++){
                    List o = (List) list1.get(i);
                    FundInfo fundInfo=new FundInfo();
                    for (int j=0;j<o.size();j++){
                        fundInfo.setfName(o.get(0).toString());
                        fundInfo.setsName(o.get(1).toString());
                        fundInfo.setName(o.get(2).toString());
                        fundInfo.setType(o.get(3).toString());
                        fundInfo.setqName(o.get(4).toString());
                    }
                    list.add(fundInfo);
                }
//                System.out.println(o1);
                return list;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public Object getData(String code){
        CloseableHttpClient httpClient = HttpClientBuilder.create().build();
        Date date=new Date();
        SimpleDateFormat dateFormat=new SimpleDateFormat("YYY-MM-dd");
        HttpGet get = new HttpGet("https://web.ifzq.gtimg.cn/appstock/app/fqkline/get?param="+code+",day," +
                dateFormat.format(date)+",,640,qfq");
        try{
            //这里可以设置请求参数，token
            HttpResponse response = httpClient.execute(get);//执行获取响应
            if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK){//根据状态码处理
                //返回字符串
                String res = EntityUtils.toString(response.getEntity(), StandardCharsets.UTF_8);
                JSONObject object=JSON.parseObject(res);
                System.out.println(res);
                return object;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

}
