package com.example.money.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.money.pojo.Account;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author lzx
 * @date 2023-03-20 10:32
 * @description
 */
@Mapper
public interface AccountMapper extends BaseMapper<Account> {
}
