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

import com.entity.ShiguleixingEntity;
import com.entity.view.ShiguleixingView;

import com.service.ShiguleixingService;
import com.service.TokenService;
import com.utils.PageUtils;
import com.utils.R;
import com.utils.EncryptUtil;
import com.utils.MPUtil;
import com.utils.MapUtils;
import com.utils.CommonUtil;
import java.io.IOException;

/**
 * 事故类型
 * 后端接口
 * @author 
 * @email 
 * @date 2025-02-12 16:12:13
 */
@RestController
@RequestMapping("/shiguleixing")
public class ShiguleixingController {
    @Autowired
    private ShiguleixingService shiguleixingService;






    



    /**
     * 后台列表
     */
    @RequestMapping("/page")
    public R page(@RequestParam Map<String, Object> params,ShiguleixingEntity shiguleixing,
		HttpServletRequest request){
        //设置查询条件
        EntityWrapper<ShiguleixingEntity> ew = new EntityWrapper<ShiguleixingEntity>();


        //查询结果
		PageUtils page = shiguleixingService.queryPage(params, MPUtil.sort(MPUtil.between(MPUtil.likeOrEq(ew, shiguleixing), params), params));
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
    public R list(@RequestParam Map<String, Object> params,ShiguleixingEntity shiguleixing, 
		HttpServletRequest request){
        //设置查询条件
        EntityWrapper<ShiguleixingEntity> ew = new EntityWrapper<ShiguleixingEntity>();

        //查询结果
		PageUtils page = shiguleixingService.queryPage(params, MPUtil.sort(MPUtil.between(MPUtil.likeOrEq(ew, shiguleixing), params), params));
        Map<String, String> deSens = new HashMap<>();
        //给需要脱敏的字段脱敏
        DeSensUtil.desensitize(page,deSens);
        return R.ok().put("data", page);
    }



	/**
     * 列表
     */
    @RequestMapping("/lists")
    public R list( ShiguleixingEntity shiguleixing){
       	EntityWrapper<ShiguleixingEntity> ew = new EntityWrapper<ShiguleixingEntity>();
      	ew.allEq(MPUtil.allEQMapPre( shiguleixing, "shiguleixing")); 
        return R.ok().put("data", shiguleixingService.selectListView(ew));
    }

	 /**
     * 查询
     */
    @RequestMapping("/query")
    public R query(ShiguleixingEntity shiguleixing){
        EntityWrapper< ShiguleixingEntity> ew = new EntityWrapper< ShiguleixingEntity>();
 		ew.allEq(MPUtil.allEQMapPre( shiguleixing, "shiguleixing")); 
		ShiguleixingView shiguleixingView =  shiguleixingService.selectView(ew);
		return R.ok("查询事故类型成功").put("data", shiguleixingView);
    }
	
    /**
     * 后台详情
     */
    @RequestMapping("/info/{id}")
    public R info(@PathVariable("id") Long id){
        ShiguleixingEntity shiguleixing = shiguleixingService.selectById(id);
        Map<String, String> deSens = new HashMap<>();
        //给需要脱敏的字段脱敏
        DeSensUtil.desensitize(shiguleixing,deSens);
        return R.ok().put("data", shiguleixing);
    }

    /**
     * 前台详情
     */
	@IgnoreAuth
    @RequestMapping("/detail/{id}")
    public R detail(@PathVariable("id") Long id){
        ShiguleixingEntity shiguleixing = shiguleixingService.selectById(id);
        Map<String, String> deSens = new HashMap<>();
        //给需要脱敏的字段脱敏
        DeSensUtil.desensitize(shiguleixing,deSens);
        return R.ok().put("data", shiguleixing);
    }
    



    /**
     * 后台保存
     */
    @RequestMapping("/save")
    @SysLog("新增事故类型") 
    public R save(@RequestBody ShiguleixingEntity shiguleixing, HttpServletRequest request){
        //ValidatorUtils.validateEntity(shiguleixing);
        shiguleixingService.insert(shiguleixing);
        return R.ok().put("data",shiguleixing.getId());
    }
    
    /**
     * 前台保存
     */
    @SysLog("新增事故类型")
    @RequestMapping("/add")
    public R add(@RequestBody ShiguleixingEntity shiguleixing, HttpServletRequest request){
        //ValidatorUtils.validateEntity(shiguleixing);
        shiguleixingService.insert(shiguleixing);
        return R.ok().put("data",shiguleixing.getId());
    }





    /**
     * 修改
     */
    @RequestMapping("/update")
    @Transactional
    @SysLog("修改事故类型")
    public R update(@RequestBody ShiguleixingEntity shiguleixing, HttpServletRequest request){
        //ValidatorUtils.validateEntity(shiguleixing);
        //全部更新
        shiguleixingService.updateById(shiguleixing);
        return R.ok();
    }



    

    /**
     * 删除
     */
    @RequestMapping("/delete")
    @SysLog("删除事故类型")
    public R delete(@RequestBody Long[] ids){
        shiguleixingService.deleteBatchIds(Arrays.asList(ids));
        return R.ok();
    }
    











}
