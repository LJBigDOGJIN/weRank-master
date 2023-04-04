package com.example.money.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.example.money.pojo.FundInfo;
import org.apache.ibatis.annotations.Select;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * @author lzx
 * @date 2023-01-10 21:23
 * @description
 */
public interface FundService extends IService<FundInfo> {
    List<FundInfo> getInfo();


}
