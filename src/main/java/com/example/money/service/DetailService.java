package com.example.money.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.example.money.pojo.Detail;

import java.util.List;

/**
 * @author lzx
 * @date 2023-01-30 16:07
 * @description
 */
public interface DetailService extends IService<Detail> {
    Detail getList(String code);
}
