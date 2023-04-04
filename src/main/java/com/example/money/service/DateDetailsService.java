package com.example.money.service;

import com.example.money.pojo.DateDetails;

import java.util.List;
import java.util.Map;

/**
 * @author lzx
 * @date 2023-01-28 23:50
 * @description
 */
public interface DateDetailsService {
    Map<String,Object> getDetail(String num, String ways);
    Object getHoldDetail(String num);
}
