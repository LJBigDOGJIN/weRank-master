package com.example.money.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.money.pojo.Detail;
import com.example.money.pojo.FundInfo;
import com.example.money.pojo.Optional;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

/**
 * @author lzx
 * @date 2023-01-10 20:40
 * @description
 */
@Mapper
public interface FundMapper extends BaseMapper<FundInfo> {

//    @Select("select name,code,netWorth,dayGrowth where dayGrowth=MAX(dayGrowth)");
    @Select("select name,code,netWorth,dayGrowth from optional where dayGrowth=(select MAX(dayGrowth) from optional)")
    Optional getBest();
    @Select("select code, name, type, netWorth, expectWorth, totalWorth, expectGrowth, dayGrowth, lastWeekGrowth, lastMonthGrowth, lastSixMonthGrowth, lastYearGrowth, buyMin, buySourceRate, buyRate, manager, fundScale, netWorthDate, expectWorthDate, status from details where LastYearGrowth=(select MAX(LastYearGrowth) from details)")
    Detail getYearBest();

    @Select("select code, name, type, netWorth, expectWorth, totalWorth, expectGrowth, dayGrowth, lastWeekGrowth, lastMonthGrowth, lastSixMonthGrowth, lastYearGrowth, buyMin, buySourceRate, buyRate, manager, fundScale, netWorthDate, expectWorthDate, status from details where lastSixMonthGrowth=(select MAX(lastSixMonthGrowth) from details)")
    Detail getSixMonthBest();

}
