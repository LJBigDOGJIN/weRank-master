package com.example.money.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.money.dao.DetailMapper;
import com.example.money.dao.FundMapper;
import com.example.money.pojo.*;
import com.example.money.service.Impl.DateDetailsServiceImpl;
import com.example.money.service.Impl.DetailServiceImpl;
import com.example.money.service.Impl.FundServiceImpl;
import com.example.money.service.Impl.OptionalServiceImpl;
import jakarta.servlet.http.HttpServletRequest;
import org.apache.catalina.mapper.Mapper;
import org.apache.commons.lang3.StringUtils;
import org.apache.ibatis.javassist.compiler.ast.CondExpr;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.util.DigestUtils;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author lzx
 * @date 2023-01-10 20:40
 * @description
 */
@RestController
@RequestMapping("/funds")
public class FundController {

    @Value("${Bath.Code1}")
    private String sh985;
    @Value("${Bath.Code2}")
    private String sh300;
    @Value("${Bath.Code3}")
    private String sh001;

    @Autowired
    private FundServiceImpl fundService;
    @Autowired
    private FundMapper fundMapper;
    @Autowired
    private DetailServiceImpl detailService;
    @Autowired
    private DateDetailsServiceImpl detailsService;
    @Autowired
    private DetailMapper mapper;
@Autowired
private OptionalServiceImpl optionalService;

    /**
     * 获取接口数据，保存到数据库中
     * @return
     */
//    @GetMapping("/test")
//    public List<Detail> get(){
//        List<Detail> details=new ArrayList<>();
//        List<String> num = mapper.getNum();
//        for (int i=0;i< num.size();i++){
//            String s = num.get(i);
//            Detail list = detailService.getList(s);
//            if (list!=null){
//                details.add(list);
//            }
//        }
//        return details;
//    }
    @GetMapping ("/add")
   public String addInfo(){
        List<FundInfo> info = fundService.getInfo();
        boolean b = fundService.saveBatch(info);
        if (!b){
            return "失败";
        }
        return "成功";
    }
    @GetMapping("/detail")
    public Detail select(String code){
        LambdaQueryWrapper<Detail> queryWrapper=new LambdaQueryWrapper<>();
        queryWrapper.eq(Detail::getCode,code);
        Detail detail = detailService.getOne(queryWrapper);
        return detail;
    }

    /**
     * 列表内 搜索信息
     * @return
     */
    @GetMapping("/select")
    public List<FundInfo> selectByType(){
        LambdaQueryWrapper<FundInfo> queryWrapper=new LambdaQueryWrapper<>();
        queryWrapper.like(FundInfo::getType,"%指数%");
        List<FundInfo> list = fundService.list(queryWrapper);
        return list;
    }
    /**
     * 所有指数基金分页查询
     * @param currentPage
     * @param pageSize
     * @return
     */
    @GetMapping("/pageInfo")
    public Page<Detail> pageInfo(int currentPage,int pageSize ,String condition){
        System.out.println("条件在这"+condition);
        Page<Detail> fundInfoPage=new Page<>(currentPage,pageSize);
        if(condition!= null && condition != ""){
            LambdaQueryWrapper<Detail> queryWrapper=new LambdaQueryWrapper<>();
            if(condition.equals("low")) {
                queryWrapper.gt(StringUtils.isNotEmpty(condition), Detail::getLastSixMonthGrowth, "-20")
                        .lt(StringUtils.isNotEmpty(condition), Detail::getLastSixMonthGrowth, "0")
                        .isNotNull(Detail::getLastSixMonthGrowth).orderByDesc(Detail::getLastSixMonthGrowth);
                Page<Detail> page = detailService.page(fundInfoPage, queryWrapper);
                return page;
            }else {
                queryWrapper
                        .gt(StringUtils.isNotEmpty(condition), Detail::getLastSixMonthGrowth, condition)
                        .isNotNull(Detail::getLastSixMonthGrowth).orderByDesc(Detail::getLastSixMonthGrowth);
                Page<Detail> page = detailService.page(fundInfoPage, queryWrapper);
                return page;
            }
        }
        Page<Detail> page = detailService.page(fundInfoPage);
        return page;
    }

    /**
     * 查询基金的基本信息
     * @param num
     * @return
     */
    @GetMapping("/details")
    public  Map<String, Object> getDetails(String num,String ways){
        Map<String, Object> detail = detailsService.getDetail(num, ways);
        return detail;
    }

    /**
     * 对比详细数据
     * @param code1
     * @param code2
     * @return
     */
    @GetMapping("/pkdetails")
    public List<Detail> getPKDetails(String code1,String code2){
        LambdaQueryWrapper<Detail> queryWrapper=new LambdaQueryWrapper<>();
        LambdaQueryWrapper<Detail> queryWrapper2=new LambdaQueryWrapper<>();
        queryWrapper2.eq(Detail::getCode,code2);
        queryWrapper.eq(Detail::getCode,code1);
        List<Detail> list=new ArrayList<>();
        Detail one = detailService.getOne(queryWrapper);
        Detail one1 = detailService.getOne(queryWrapper2);
        if(one!=null){
            list.add(one);
        }
        if (one1!=null){
            list.add(one1);
        }
        System.out.println(list);
        return list;
    }

    /**
     * 行情页 中间部分点击事件
     * @param currentPage
     * @param pageSize
     * @param txt
     * @return
     */
    @GetMapping  ("/search")
    public Page<Detail> getSearch(int currentPage,int pageSize , String txt){
        System.out.println(txt);
        Page<Detail> pageSearch=new Page<>(currentPage,pageSize);
        if (txt.isBlank()||txt.isEmpty()){
            Page<Detail> page = detailService.page(pageSearch);
            return page;
        }
        LambdaQueryWrapper<Detail> queryWrapper=new LambdaQueryWrapper<>();
        queryWrapper.like(Detail::getName,txt).or().like(Detail::getCode,txt);
        Page<Detail> page = detailService.page(pageSearch, queryWrapper);
        return page;

    }

    /**
     * 每日晚9：30 更新表数据信息
     * @return
     */
    @RequestMapping("/update")
    public String updateInfo(){
        List<String> num = mapper.getNum();
        for(int i=0;i<num.size();i++){
            String s = num.get(i);
            Detail list = detailService.getList(s);
            if(list!=null) {
                LambdaUpdateWrapper<Detail> updateWrapper = new LambdaUpdateWrapper<>();
                updateWrapper.set(Detail::getNetWorth, list.getNetWorth()).eq(Detail::getCode, s);
                updateWrapper.set(Detail::getType, list.getType());
                updateWrapper.set(Detail::getName, list.getName()).eq(Detail::getCode, s);
                updateWrapper.set(Detail::getExpectWorth, list.getExpectWorth());
                updateWrapper.set(Detail::getTotalWorth, list.getTotalWorth());
                updateWrapper.set(Detail::getExpectGrowth, list.getExpectGrowth());
                updateWrapper.set(Detail::getDayGrowth, list.getDayGrowth());
                updateWrapper.set(Detail::getLastWeekGrowth, list.getLastWeekGrowth());
                updateWrapper.set(Detail::getLastMonthGrowth, list.getLastMonthGrowth());
                updateWrapper.set(Detail::getLastSixMonthGrowth, list.getLastSixMonthGrowth());
                updateWrapper.set(Detail::getLastYearGrowth, list.getLastYearGrowth());
                updateWrapper.set(Detail::getBuySourceRate, list.getBuySourceRate());
                updateWrapper.set(Detail::getBuyMin, list.getBuyMin());
                updateWrapper.set(Detail::getBuyRate, list.getBuyRate());
                updateWrapper.set(Detail::getManager, list.getManager());
                updateWrapper.set(Detail::getFundScale, list.getFundScale());
                updateWrapper.set(Detail::getNetWorthDate, list.getNetWorthDate());
                updateWrapper.set(Detail::getExpectWorthDate, list.getExpectWorth());
                boolean update = detailService.update(updateWrapper);
                if (!update) {
                    return "success";
                }
            }
        }

        return "error";
    }

    /**
     * 添加自选列表
     * @param info
     * @param request
     * @return
     */
    @PostMapping ("/addConcern")
    public String addFund(@RequestBody Optional info, HttpServletRequest request){
        Object openid = request.getSession().getAttribute("openid");
        System.out.println(info.getStatus());
        info.setOpenid(openid.toString());
        try {
            boolean save = optionalService.save(info);
            LambdaUpdateWrapper<Detail> updateWrapper=new LambdaUpdateWrapper<>();
            updateWrapper.set(Detail::getStatus,info.getStatus()).eq(Detail::getCode,info.getCode());
            detailService.update(updateWrapper);
        }catch (Exception e){
            return "该产品已在自选列表";
        }

        return "关注成功";
    }

    /**
     * 根据code值移除自选列表
     * @param code
     * @return
     */
    @GetMapping("/remove")
    public String remove(String code){
        LambdaQueryWrapper<Optional> queryWrapper=new LambdaQueryWrapper<>();
        LambdaUpdateWrapper<Detail> updateWrapper=new LambdaUpdateWrapper<>();
        updateWrapper.eq(Detail::getCode,code).set(Detail::getStatus,0);
        boolean update = detailService.update(updateWrapper);
        queryWrapper.eq(Optional::getCode,code);
        boolean remove = optionalService.remove(queryWrapper);
        if (remove && update){
            return "success";
        }
        return "error";
    }

    /**
     * 行情页 每日推荐产品
     * @return
     */
    @GetMapping("/recommond")
    public Map<String,Detail> getRecd(){
        Map<String,Detail> map=new HashMap<>();
        Detail yearBest = fundMapper.getYearBest();
        Detail sixMonthBest = fundMapper.getSixMonthBest();
        map.put("yearBest",yearBest);
        map.put("sixMonthBest",sixMonthBest);
        return map;
    }

    /**
     * 查询自选列表信息
     * @return
     */
    @PostMapping("/gzList")
    public List<Optional> gzList(){
        LambdaQueryWrapper<Optional> queryWrapper=new LambdaQueryWrapper<>();
        List<Optional> list = optionalService.list(queryWrapper);
        return list;
    }

    /**
     * 自选列表每日表现最好的产品
     * @return
     */
    @PostMapping("/dailyBest")
    public Optional getBest(){
        Optional best = fundMapper.getBest();
        return best;
    }

    /**
     * 三只股票的每日涨跌幅
     * @return
     */
    @PostMapping("/hs")
    public List<Object> info(){
        List<Object> data=new ArrayList<>();
        Object data1 = fundService.getData(sh985);
        Object data2 = fundService.getData(sh300);
        Object data3 = fundService.getData(sh001);
        data.add(data1);
        data.add(data2);
        data.add(data3);
        System.out.println(data);
        return data;
    }

    /**
     * 基金持仓详细信息
     * @param num
     * @return
     */
    @GetMapping("/hold")
    public Object getHold(String num){
        System.out.println(num);
        Object holdDetail = detailsService.getHoldDetail(num);
//        System.out.println(holdDetail);
        return holdDetail;
    }
//    @GetMapping("/caculate")
//    public String caIncome(){
//        detailsService.getDetail();
//
//        return null;
//    }
}



