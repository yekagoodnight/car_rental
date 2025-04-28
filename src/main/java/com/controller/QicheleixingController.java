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

import com.entity.QicheleixingEntity;
import com.entity.view.QicheleixingView;

import com.service.QicheleixingService;
import com.service.TokenService;
import com.utils.PageUtils;
import com.utils.R;
import com.utils.EncryptUtil;
import com.utils.MPUtil;
import com.utils.MapUtils;
import com.utils.CommonUtil;
import java.io.IOException;

/**
 * 汽车类型
 * 后端接口
 * @author 
 * @email 
 * @date 2025-02-12 16:12:12
 */
@RestController
@RequestMapping("/qicheleixing")
public class QicheleixingController {
    @Autowired
    private QicheleixingService qicheleixingService;






    



    /**
     * 后台列表
     */
    @RequestMapping("/page")
    public R page(@RequestParam Map<String, Object> params,QicheleixingEntity qicheleixing,
		HttpServletRequest request){
        //设置查询条件
        EntityWrapper<QicheleixingEntity> ew = new EntityWrapper<QicheleixingEntity>();


        //查询结果
		PageUtils page = qicheleixingService.queryPage(params, MPUtil.sort(MPUtil.between(MPUtil.likeOrEq(ew, qicheleixing), params), params));
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
    public R list(@RequestParam Map<String, Object> params,QicheleixingEntity qicheleixing, 
		HttpServletRequest request){
        //设置查询条件
        EntityWrapper<QicheleixingEntity> ew = new EntityWrapper<QicheleixingEntity>();

        //查询结果
		PageUtils page = qicheleixingService.queryPage(params, MPUtil.sort(MPUtil.between(MPUtil.likeOrEq(ew, qicheleixing), params), params));
        Map<String, String> deSens = new HashMap<>();
        //给需要脱敏的字段脱敏
        DeSensUtil.desensitize(page,deSens);
        return R.ok().put("data", page);
    }



	/**
     * 列表
     */
    @RequestMapping("/lists")
    public R list( QicheleixingEntity qicheleixing){
       	EntityWrapper<QicheleixingEntity> ew = new EntityWrapper<QicheleixingEntity>();
      	ew.allEq(MPUtil.allEQMapPre( qicheleixing, "qicheleixing")); 
        return R.ok().put("data", qicheleixingService.selectListView(ew));
    }

	 /**
     * 查询
     */
    @RequestMapping("/query")
    public R query(QicheleixingEntity qicheleixing){
        EntityWrapper< QicheleixingEntity> ew = new EntityWrapper< QicheleixingEntity>();
 		ew.allEq(MPUtil.allEQMapPre( qicheleixing, "qicheleixing")); 
		QicheleixingView qicheleixingView =  qicheleixingService.selectView(ew);
		return R.ok("查询汽车类型成功").put("data", qicheleixingView);
    }
	
    /**
     * 后台详情
     */
    @RequestMapping("/info/{id}")
    public R info(@PathVariable("id") Long id){
        QicheleixingEntity qicheleixing = qicheleixingService.selectById(id);
        Map<String, String> deSens = new HashMap<>();
        //给需要脱敏的字段脱敏
        DeSensUtil.desensitize(qicheleixing,deSens);
        return R.ok().put("data", qicheleixing);
    }

    /**
     * 前台详情
     */
	@IgnoreAuth
    @RequestMapping("/detail/{id}")
    public R detail(@PathVariable("id") Long id){
        QicheleixingEntity qicheleixing = qicheleixingService.selectById(id);
        Map<String, String> deSens = new HashMap<>();
        //给需要脱敏的字段脱敏
        DeSensUtil.desensitize(qicheleixing,deSens);
        return R.ok().put("data", qicheleixing);
    }
    



    /**
     * 后台保存
     */
    @RequestMapping("/save")
    @SysLog("新增汽车类型") 
    public R save(@RequestBody QicheleixingEntity qicheleixing, HttpServletRequest request){
        //ValidatorUtils.validateEntity(qicheleixing);
        qicheleixingService.insert(qicheleixing);
        return R.ok().put("data",qicheleixing.getId());
    }
    
    /**
     * 前台保存
     */
    @SysLog("新增汽车类型")
    @RequestMapping("/add")
    public R add(@RequestBody QicheleixingEntity qicheleixing, HttpServletRequest request){
        //ValidatorUtils.validateEntity(qicheleixing);
        qicheleixingService.insert(qicheleixing);
        return R.ok().put("data",qicheleixing.getId());
    }





    /**
     * 修改
     */
    @RequestMapping("/update")
    @Transactional
    @SysLog("修改汽车类型")
    public R update(@RequestBody QicheleixingEntity qicheleixing, HttpServletRequest request){
        //ValidatorUtils.validateEntity(qicheleixing);
        //全部更新
        qicheleixingService.updateById(qicheleixing);
        return R.ok();
    }



    

    /**
     * 删除
     */
    @RequestMapping("/delete")
    @SysLog("删除汽车类型")
    public R delete(@RequestBody Long[] ids){
        qicheleixingService.deleteBatchIds(Arrays.asList(ids));
        return R.ok();
    }
    











}
