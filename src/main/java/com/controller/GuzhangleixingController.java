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

import com.entity.GuzhangleixingEntity;
import com.entity.view.GuzhangleixingView;

import com.service.GuzhangleixingService;
import com.service.TokenService;
import com.utils.PageUtils;
import com.utils.R;
import com.utils.EncryptUtil;
import com.utils.MPUtil;
import com.utils.MapUtils;
import com.utils.CommonUtil;
import java.io.IOException;

/**
 * 故障类型
 * 后端接口
 * @author 
 * @email 
 * @date 2025-02-12 16:12:12
 */
@RestController
@RequestMapping("/guzhangleixing")
public class GuzhangleixingController {
    @Autowired
    private GuzhangleixingService guzhangleixingService;






    



    /**
     * 后台列表
     */
    @RequestMapping("/page")
    public R page(@RequestParam Map<String, Object> params,GuzhangleixingEntity guzhangleixing,
		HttpServletRequest request){
        //设置查询条件
        EntityWrapper<GuzhangleixingEntity> ew = new EntityWrapper<GuzhangleixingEntity>();


        //查询结果
		PageUtils page = guzhangleixingService.queryPage(params, MPUtil.sort(MPUtil.between(MPUtil.likeOrEq(ew, guzhangleixing), params), params));
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
    public R list(@RequestParam Map<String, Object> params,GuzhangleixingEntity guzhangleixing, 
		HttpServletRequest request){
        //设置查询条件
        EntityWrapper<GuzhangleixingEntity> ew = new EntityWrapper<GuzhangleixingEntity>();

        //查询结果
		PageUtils page = guzhangleixingService.queryPage(params, MPUtil.sort(MPUtil.between(MPUtil.likeOrEq(ew, guzhangleixing), params), params));
        Map<String, String> deSens = new HashMap<>();
        //给需要脱敏的字段脱敏
        DeSensUtil.desensitize(page,deSens);
        return R.ok().put("data", page);
    }



	/**
     * 列表
     */
    @RequestMapping("/lists")
    public R list( GuzhangleixingEntity guzhangleixing){
       	EntityWrapper<GuzhangleixingEntity> ew = new EntityWrapper<GuzhangleixingEntity>();
      	ew.allEq(MPUtil.allEQMapPre( guzhangleixing, "guzhangleixing")); 
        return R.ok().put("data", guzhangleixingService.selectListView(ew));
    }

	 /**
     * 查询
     */
    @RequestMapping("/query")
    public R query(GuzhangleixingEntity guzhangleixing){
        EntityWrapper< GuzhangleixingEntity> ew = new EntityWrapper< GuzhangleixingEntity>();
 		ew.allEq(MPUtil.allEQMapPre( guzhangleixing, "guzhangleixing")); 
		GuzhangleixingView guzhangleixingView =  guzhangleixingService.selectView(ew);
		return R.ok("查询故障类型成功").put("data", guzhangleixingView);
    }
	
    /**
     * 后台详情
     */
    @RequestMapping("/info/{id}")
    public R info(@PathVariable("id") Long id){
        GuzhangleixingEntity guzhangleixing = guzhangleixingService.selectById(id);
        Map<String, String> deSens = new HashMap<>();
        //给需要脱敏的字段脱敏
        DeSensUtil.desensitize(guzhangleixing,deSens);
        return R.ok().put("data", guzhangleixing);
    }

    /**
     * 前台详情
     */
	@IgnoreAuth
    @RequestMapping("/detail/{id}")
    public R detail(@PathVariable("id") Long id){
        GuzhangleixingEntity guzhangleixing = guzhangleixingService.selectById(id);
        Map<String, String> deSens = new HashMap<>();
        //给需要脱敏的字段脱敏
        DeSensUtil.desensitize(guzhangleixing,deSens);
        return R.ok().put("data", guzhangleixing);
    }
    



    /**
     * 后台保存
     */
    @RequestMapping("/save")
    @SysLog("新增故障类型") 
    public R save(@RequestBody GuzhangleixingEntity guzhangleixing, HttpServletRequest request){
        //ValidatorUtils.validateEntity(guzhangleixing);
        guzhangleixingService.insert(guzhangleixing);
        return R.ok().put("data",guzhangleixing.getId());
    }
    
    /**
     * 前台保存
     */
    @SysLog("新增故障类型")
    @RequestMapping("/add")
    public R add(@RequestBody GuzhangleixingEntity guzhangleixing, HttpServletRequest request){
        //ValidatorUtils.validateEntity(guzhangleixing);
        guzhangleixingService.insert(guzhangleixing);
        return R.ok().put("data",guzhangleixing.getId());
    }





    /**
     * 修改
     */
    @RequestMapping("/update")
    @Transactional
    @SysLog("修改故障类型")
    public R update(@RequestBody GuzhangleixingEntity guzhangleixing, HttpServletRequest request){
        //ValidatorUtils.validateEntity(guzhangleixing);
        //全部更新
        guzhangleixingService.updateById(guzhangleixing);
        return R.ok();
    }



    

    /**
     * 删除
     */
    @RequestMapping("/delete")
    @SysLog("删除故障类型")
    public R delete(@RequestBody Long[] ids){
        guzhangleixingService.deleteBatchIds(Arrays.asList(ids));
        return R.ok();
    }
    











}
