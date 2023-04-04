package com.example.money.service.Impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.money.dao.DetailMapper;
import com.example.money.pojo.Detail;
import com.example.money.pojo.FundInfo;
import com.example.money.service.DetailService;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

/**
 * @author lzx
 * @date 2023-01-30 16:07
 * @description
 */
@Service
public class DetailServiceImpl extends ServiceImpl<DetailMapper, Detail> implements DetailService {
    @Override
    public Detail getList(String urlCode) {

            CloseableHttpClient httpClient= HttpClientBuilder.create().build();
            HttpGet get=null;
//            for (int i=0;i<num.size()
            get=new HttpGet("https://api.doctorxiong.club/v1/fund/detail?code="+urlCode+"&token=hZ35hdndlP");
            try {
                HttpResponse response=httpClient.execute(get);
                if (response.getStatusLine().getStatusCode()== HttpStatus.SC_OK){
                    String res= EntityUtils.toString(response.getEntity(), StandardCharsets.UTF_8);
                    JSONObject object= JSON.parseObject(res);
                    Object data = object.get("data");
                    if (data != null){

                        JSONObject object1=JSON.parseObject(data.toString());
                        Detail detail=new Detail();

                        detail.setCode(object1.get("code").toString());
                        detail.setName(object1.get("name").toString());
                        detail.setType(object1.get("type").toString());
                        detail.setNetWorth(object1.get("netWorth").toString());
                        detail.setExpectWorth(object1.get("expectWorth").toString());
                        detail.setTotalWorth(object1.get("totalWorth").toString());
                        detail.setExpectGrowth(object1.get("expectGrowth").toString());
                        detail.setDayGrowth(object1.get("dayGrowth").toString());
                        detail.setLastWeekGrowth(object1.get("lastWeekGrowth").toString());
                        detail.setLastMonthGrowth(object1.get("lastMonthGrowth").toString());
                        detail.setLastSixMonthGrowth(object1.get("lastSixMonthsGrowth").toString());
                        detail.setLastYearGrowth(object1.get("lastYearGrowth").toString());
                        detail.setBuyMin(object1.get("buyMin").toString());
                        detail.setBuySourceRate(object1.get("buySourceRate").toString());
                        detail.setBuyRate(object1.get("buyRate").toString());
                        detail.setManager(object1.get("manager").toString());
                        detail.setFundScale(object1.get("fundScale").toString());
                        detail.setNetWorthDate(object1.get("netWorthDate").toString());
                        detail.setExpectWorthDate(object1.get("expectWorthDate").toString());
//                        i++;
                        return detail;
                    }

                }

            } catch (Exception e) {
                e.printStackTrace();
            }
//    }
//        System.out.println("sdxfcgggggggggggggggggggggggggggggg");
            return null;
    }
}
