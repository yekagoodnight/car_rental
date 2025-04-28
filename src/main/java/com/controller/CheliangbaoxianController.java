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

import com.entity.CheliangbaoxianEntity;
import com.entity.view.CheliangbaoxianView;

import com.service.CheliangbaoxianService;
import com.service.TokenService;
import com.utils.PageUtils;
import com.utils.R;
import com.utils.EncryptUtil;
import com.utils.MPUtil;
import com.utils.MapUtils;
import com.utils.CommonUtil;
import java.io.IOException;

/**
 * 车辆保险
 * 后端接口
 * @author 
 * @email 
 * @date 2025-02-12 16:12:13
 */
@RestController
@RequestMapping("/cheliangbaoxian")
public class CheliangbaoxianController {
    @Autowired
    private CheliangbaoxianService cheliangbaoxianService;






    



    /**
     * 后台列表
     */
    @RequestMapping("/page")
    public R page(@RequestParam Map<String, Object> params,CheliangbaoxianEntity cheliangbaoxian,
		HttpServletRequest request){
		String tableName = request.getSession().getAttribute("tableName").toString();
		if(tableName.equals("yonghu")) {
			cheliangbaoxian.setYonghuzhanghao((String)request.getSession().getAttribute("username"));
		}
		if(tableName.equals("cheshang")) {
			cheliangbaoxian.setCheshangzhanghao((String)request.getSession().getAttribute("username"));
		}
        //设置查询条件
        EntityWrapper<CheliangbaoxianEntity> ew = new EntityWrapper<CheliangbaoxianEntity>();


        //查询结果
		PageUtils page = cheliangbaoxianService.queryPage(params, MPUtil.sort(MPUtil.between(MPUtil.likeOrEq(ew, cheliangbaoxian), params), params));
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
    public R list(@RequestParam Map<String, Object> params,CheliangbaoxianEntity cheliangbaoxian, 
		HttpServletRequest request){
        //设置查询条件
        EntityWrapper<CheliangbaoxianEntity> ew = new EntityWrapper<CheliangbaoxianEntity>();

        //查询结果
		PageUtils page = cheliangbaoxianService.queryPage(params, MPUtil.sort(MPUtil.between(MPUtil.likeOrEq(ew, cheliangbaoxian), params), params));
        Map<String, String> deSens = new HashMap<>();
        //给需要脱敏的字段脱敏
        DeSensUtil.desensitize(page,deSens);
        return R.ok().put("data", page);
    }



	/**
     * 列表
     */
    @RequestMapping("/lists")
    public R list( CheliangbaoxianEntity cheliangbaoxian){
       	EntityWrapper<CheliangbaoxianEntity> ew = new EntityWrapper<CheliangbaoxianEntity>();
      	ew.allEq(MPUtil.allEQMapPre( cheliangbaoxian, "cheliangbaoxian")); 
        return R.ok().put("data", cheliangbaoxianService.selectListView(ew));
    }

	 /**
     * 查询
     */
    @RequestMapping("/query")
    public R query(CheliangbaoxianEntity cheliangbaoxian){
        EntityWrapper< CheliangbaoxianEntity> ew = new EntityWrapper< CheliangbaoxianEntity>();
 		ew.allEq(MPUtil.allEQMapPre( cheliangbaoxian, "cheliangbaoxian")); 
		CheliangbaoxianView cheliangbaoxianView =  cheliangbaoxianService.selectView(ew);
		return R.ok("查询车辆保险成功").put("data", cheliangbaoxianView);
    }
	
    /**
     * 后台详情
     */
    @RequestMapping("/info/{id}")
    public R info(@PathVariable("id") Long id){
        CheliangbaoxianEntity cheliangbaoxian = cheliangbaoxianService.selectById(id);
        Map<String, String> deSens = new HashMap<>();
        //给需要脱敏的字段脱敏
        DeSensUtil.desensitize(cheliangbaoxian,deSens);
        return R.ok().put("data", cheliangbaoxian);
    }

    /**
     * 前台详情
     */
	@IgnoreAuth
    @RequestMapping("/detail/{id}")
    public R detail(@PathVariable("id") Long id){
        CheliangbaoxianEntity cheliangbaoxian = cheliangbaoxianService.selectById(id);
        Map<String, String> deSens = new HashMap<>();
        //给需要脱敏的字段脱敏
        DeSensUtil.desensitize(cheliangbaoxian,deSens);
        return R.ok().put("data", cheliangbaoxian);
    }
    



    /**
     * 后台保存
     */
    @RequestMapping("/save")
    @SysLog("新增车辆保险") 
    public R save(@RequestBody CheliangbaoxianEntity cheliangbaoxian, HttpServletRequest request){
        //ValidatorUtils.validateEntity(cheliangbaoxian);
        cheliangbaoxianService.insert(cheliangbaoxian);
        return R.ok().put("data",cheliangbaoxian.getId());
    }
    
    /**
     * 前台保存
     */
    @SysLog("新增车辆保险")
    @RequestMapping("/add")
    public R add(@RequestBody CheliangbaoxianEntity cheliangbaoxian, HttpServletRequest request){
        //ValidatorUtils.validateEntity(cheliangbaoxian);
        cheliangbaoxianService.insert(cheliangbaoxian);
        return R.ok().put("data",cheliangbaoxian.getId());
    }





    /**
     * 修改
     */
    @RequestMapping("/update")
    @Transactional
    @SysLog("修改车辆保险")
    public R update(@RequestBody CheliangbaoxianEntity cheliangbaoxian, HttpServletRequest request){
        //ValidatorUtils.validateEntity(cheliangbaoxian);
        //全部更新
        cheliangbaoxianService.updateById(cheliangbaoxian);
        return R.ok();
    }



    

    /**
     * 删除
     */
    @RequestMapping("/delete")
    @SysLog("删除车辆保险")
    public R delete(@RequestBody Long[] ids){
        cheliangbaoxianService.deleteBatchIds(Arrays.asList(ids));
        return R.ok();
    }
    











}
