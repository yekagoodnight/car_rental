package com.controller;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Map;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Date;
import java.util.List;
import java.util.Collections;

import java.util.stream.Collectors;
import javax.servlet.http.HttpServletRequest;
import com.utils.ValidatorUtils;
import com.utils.DeSensUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.annotation.IgnoreAuth;
import com.annotation.SysLog;

import com.entity.DingdanquxiaoEntity;
import com.entity.view.DingdanquxiaoView;

import com.service.DingdanquxiaoService;
import com.service.TokenService;
import com.utils.PageUtils;
import com.utils.R;
import com.utils.EncryptUtil;
import com.utils.MPUtil;
import com.utils.MapUtils;
import com.utils.CommonUtil;
import java.io.IOException;

/**
 * 订单取消
 * 后端接口
 * @author 
 * @email 
 * @date 2025-02-12 16:12:12
 */
@RestController
@RequestMapping("/dingdanquxiao")
public class DingdanquxiaoController {
    @Autowired
    private DingdanquxiaoService dingdanquxiaoService;






    



    /**
     * 后台列表
     */
    @RequestMapping("/page")
    public R page(@RequestParam Map<String, Object> params,DingdanquxiaoEntity dingdanquxiao,
		HttpServletRequest request){
		String tableName = request.getSession().getAttribute("tableName").toString();
		if(tableName.equals("cheshang")) {
			dingdanquxiao.setCheshangzhanghao((String)request.getSession().getAttribute("username"));
		}
		if(tableName.equals("yonghu")) {
			dingdanquxiao.setYonghuzhanghao((String)request.getSession().getAttribute("username"));
		}
        //设置查询条件
        EntityWrapper<DingdanquxiaoEntity> ew = new EntityWrapper<DingdanquxiaoEntity>();


        //查询结果
		PageUtils page = dingdanquxiaoService.queryPage(params, MPUtil.sort(MPUtil.between(MPUtil.likeOrEq(ew, dingdanquxiao), params), params));
        Map<String, String> deSens = new HashMap<>();
        //给需要脱敏的字段脱敏
        DeSensUtil.desensitize(page,deSens);
        return R.ok().put("data", page);
    }
    
    /**
     * 前台列表
     */
	@IgnoreAuth
    @RequestMapping("/list")
    public R list(@RequestParam Map<String, Object> params,DingdanquxiaoEntity dingdanquxiao, 
		HttpServletRequest request){
        //设置查询条件
        EntityWrapper<DingdanquxiaoEntity> ew = new EntityWrapper<DingdanquxiaoEntity>();

        //查询结果
		PageUtils page = dingdanquxiaoService.queryPage(params, MPUtil.sort(MPUtil.between(MPUtil.likeOrEq(ew, dingdanquxiao), params), params));
        Map<String, String> deSens = new HashMap<>();
        //给需要脱敏的字段脱敏
        DeSensUtil.desensitize(page,deSens);
        return R.ok().put("data", page);
    }



	/**
     * 列表
     */
    @RequestMapping("/lists")
    public R list( DingdanquxiaoEntity dingdanquxiao){
       	EntityWrapper<DingdanquxiaoEntity> ew = new EntityWrapper<DingdanquxiaoEntity>();
      	ew.allEq(MPUtil.allEQMapPre( dingdanquxiao, "dingdanquxiao")); 
        return R.ok().put("data", dingdanquxiaoService.selectListView(ew));
    }

	 /**
     * 查询
     */
    @RequestMapping("/query")
    public R query(DingdanquxiaoEntity dingdanquxiao){
        EntityWrapper< DingdanquxiaoEntity> ew = new EntityWrapper< DingdanquxiaoEntity>();
 		ew.allEq(MPUtil.allEQMapPre( dingdanquxiao, "dingdanquxiao")); 
		DingdanquxiaoView dingdanquxiaoView =  dingdanquxiaoService.selectView(ew);
		return R.ok("查询订单取消成功").put("data", dingdanquxiaoView);
    }
	
    /**
     * 后台详情
     */
    @RequestMapping("/info/{id}")
    public R info(@PathVariable("id") Long id){
        DingdanquxiaoEntity dingdanquxiao = dingdanquxiaoService.selectById(id);
        Map<String, String> deSens = new HashMap<>();
        //给需要脱敏的字段脱敏
        DeSensUtil.desensitize(dingdanquxiao,deSens);
        return R.ok().put("data", dingdanquxiao);
    }

    /**
     * 前台详情
     */
	@IgnoreAuth
    @RequestMapping("/detail/{id}")
    public R detail(@PathVariable("id") Long id){
        DingdanquxiaoEntity dingdanquxiao = dingdanquxiaoService.selectById(id);
        Map<String, String> deSens = new HashMap<>();
        //给需要脱敏的字段脱敏
        DeSensUtil.desensitize(dingdanquxiao,deSens);
        return R.ok().put("data", dingdanquxiao);
    }
    



    /**
     * 后台保存
     */
    @RequestMapping("/save")
    @SysLog("新增订单取消") 
    public R save(@RequestBody DingdanquxiaoEntity dingdanquxiao, HttpServletRequest request){
        //ValidatorUtils.validateEntity(dingdanquxiao);
        dingdanquxiaoService.insert(dingdanquxiao);
        return R.ok().put("data",dingdanquxiao.getId());
    }
    
    /**
     * 前台保存
     */
    @SysLog("新增订单取消")
    @RequestMapping("/add")
    public R add(@RequestBody DingdanquxiaoEntity dingdanquxiao, HttpServletRequest request){
        //ValidatorUtils.validateEntity(dingdanquxiao);
        dingdanquxiaoService.insert(dingdanquxiao);
        return R.ok().put("data",dingdanquxiao.getId());
    }





    /**
     * 修改
     */
    @RequestMapping("/update")
    @Transactional
    @SysLog("修改订单取消")
    public R update(@RequestBody DingdanquxiaoEntity dingdanquxiao, HttpServletRequest request){
        //ValidatorUtils.validateEntity(dingdanquxiao);
        //全部更新
        dingdanquxiaoService.updateById(dingdanquxiao);
        return R.ok();
    }



    

    /**
     * 删除
     */
    @RequestMapping("/delete")
    @SysLog("删除订单取消")
    public R delete(@RequestBody Long[] ids){
        dingdanquxiaoService.deleteBatchIds(Arrays.asList(ids));
        return R.ok();
    }
    











}
