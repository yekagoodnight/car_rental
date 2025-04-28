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

import com.entity.DiscussqichechuzuEntity;
import com.entity.view.DiscussqichechuzuView;

import com.service.DiscussqichechuzuService;
import com.service.TokenService;
import com.utils.PageUtils;
import com.utils.R;
import com.utils.EncryptUtil;
import com.utils.MPUtil;
import com.utils.MapUtils;
import com.utils.CommonUtil;
import java.io.IOException;

/**
 * 汽车租赁评论表
 * 后端接口
 * @author 
 * @email 
 * @date 2025-02-12 16:12:14
 */
@RestController
@RequestMapping("/discussqichechuzu")
public class DiscussqichechuzuController {
    @Autowired
    private DiscussqichechuzuService discussqichechuzuService;






    



    /**
     * 后台列表
     */
    @RequestMapping("/page")
    public R page(@RequestParam Map<String, Object> params,DiscussqichechuzuEntity discussqichechuzu,
		HttpServletRequest request){
        //设置查询条件
        EntityWrapper<DiscussqichechuzuEntity> ew = new EntityWrapper<DiscussqichechuzuEntity>();


        //查询结果
		PageUtils page = discussqichechuzuService.queryPage(params, MPUtil.sort(MPUtil.between(MPUtil.likeOrEq(ew, discussqichechuzu), params), params));
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
    public R list(@RequestParam Map<String, Object> params,DiscussqichechuzuEntity discussqichechuzu, 
		HttpServletRequest request){
        //设置查询条件
        EntityWrapper<DiscussqichechuzuEntity> ew = new EntityWrapper<DiscussqichechuzuEntity>();

        //查询结果
		PageUtils page = discussqichechuzuService.queryPage(params, MPUtil.sort(MPUtil.between(MPUtil.likeOrEq(ew, discussqichechuzu), params), params));
        Map<String, String> deSens = new HashMap<>();
        //给需要脱敏的字段脱敏
        DeSensUtil.desensitize(page,deSens);
        return R.ok().put("data", page);
    }



	/**
     * 列表
     */
    @RequestMapping("/lists")
    public R list( DiscussqichechuzuEntity discussqichechuzu){
       	EntityWrapper<DiscussqichechuzuEntity> ew = new EntityWrapper<DiscussqichechuzuEntity>();
      	ew.allEq(MPUtil.allEQMapPre( discussqichechuzu, "discussqichechuzu")); 
        return R.ok().put("data", discussqichechuzuService.selectListView(ew));
    }

	 /**
     * 查询
     */
    @RequestMapping("/query")
    public R query(DiscussqichechuzuEntity discussqichechuzu){
        EntityWrapper< DiscussqichechuzuEntity> ew = new EntityWrapper< DiscussqichechuzuEntity>();
 		ew.allEq(MPUtil.allEQMapPre( discussqichechuzu, "discussqichechuzu")); 
		DiscussqichechuzuView discussqichechuzuView =  discussqichechuzuService.selectView(ew);
		return R.ok("查询汽车租赁评论表成功").put("data", discussqichechuzuView);
    }
	
    /**
     * 后台详情
     */
    @RequestMapping("/info/{id}")
    public R info(@PathVariable("id") Long id){
        DiscussqichechuzuEntity discussqichechuzu = discussqichechuzuService.selectById(id);
        Map<String, String> deSens = new HashMap<>();
        //给需要脱敏的字段脱敏
        DeSensUtil.desensitize(discussqichechuzu,deSens);
        return R.ok().put("data", discussqichechuzu);
    }

    /**
     * 前台详情
     */
	@IgnoreAuth
    @RequestMapping("/detail/{id}")
    public R detail(@PathVariable("id") Long id){
        DiscussqichechuzuEntity discussqichechuzu = discussqichechuzuService.selectById(id);
        Map<String, String> deSens = new HashMap<>();
        //给需要脱敏的字段脱敏
        DeSensUtil.desensitize(discussqichechuzu,deSens);
        return R.ok().put("data", discussqichechuzu);
    }
    



    /**
     * 后台保存
     */
    @RequestMapping("/save")
    @SysLog("新增汽车租赁评论表") 
    public R save(@RequestBody DiscussqichechuzuEntity discussqichechuzu, HttpServletRequest request){
        //ValidatorUtils.validateEntity(discussqichechuzu);
        discussqichechuzuService.insert(discussqichechuzu);
        return R.ok().put("data",discussqichechuzu.getId());
    }
    
    /**
     * 前台保存
     */
    @SysLog("新增汽车租赁评论表")
    @RequestMapping("/add")
    public R add(@RequestBody DiscussqichechuzuEntity discussqichechuzu, HttpServletRequest request){
        //ValidatorUtils.validateEntity(discussqichechuzu);
        discussqichechuzuService.insert(discussqichechuzu);
        return R.ok().put("data",discussqichechuzu.getId());
    }



     /**
     * 获取用户密保
     */
    @RequestMapping("/security")
    @IgnoreAuth
    public R security(@RequestParam String username){
        DiscussqichechuzuEntity discussqichechuzu = discussqichechuzuService.selectOne(new EntityWrapper<DiscussqichechuzuEntity>().eq("", username));
        return R.ok().put("data", discussqichechuzu);
    }


    /**
     * 修改
     */
    @RequestMapping("/update")
    @Transactional
    @IgnoreAuth
    public R update(@RequestBody DiscussqichechuzuEntity discussqichechuzu, HttpServletRequest request){
        //ValidatorUtils.validateEntity(discussqichechuzu);
        //全部更新
        discussqichechuzuService.updateById(discussqichechuzu);
        return R.ok();
    }



    

    /**
     * 删除
     */
    @RequestMapping("/delete")
    @SysLog("删除汽车租赁评论表")
    public R delete(@RequestBody Long[] ids){
        discussqichechuzuService.deleteBatchIds(Arrays.asList(ids));
        return R.ok();
    }
    
	/**
     * 前台智能排序
     */
	@IgnoreAuth
    @RequestMapping("/autoSort")
    public R autoSort(@RequestParam Map<String, Object> params,DiscussqichechuzuEntity discussqichechuzu, HttpServletRequest request,String pre){
        EntityWrapper<DiscussqichechuzuEntity> ew = new EntityWrapper<DiscussqichechuzuEntity>();
        Map<String, Object> newMap = new HashMap<String, Object>();
        Map<String, Object> param = new HashMap<String, Object>();
		Iterator<Map.Entry<String, Object>> it = param.entrySet().iterator();
		while (it.hasNext()) {
			Map.Entry<String, Object> entry = it.next();
			String key = entry.getKey();
			String newKey = entry.getKey();
			if (pre.endsWith(".")) {
				newMap.put(pre + newKey, entry.getValue());
			} else if (StringUtils.isEmpty(pre)) {
				newMap.put(newKey, entry.getValue());
			} else {
				newMap.put(pre + "." + newKey, entry.getValue());
			}
		}
		params.put("sort", "clicktime");
        params.put("order", "desc");
		PageUtils page = discussqichechuzuService.queryPage(params, MPUtil.sort(MPUtil.between(MPUtil.likeOrEq(ew, discussqichechuzu), params), params));
        return R.ok().put("data", page);
    }











}
