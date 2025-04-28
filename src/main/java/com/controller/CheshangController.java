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
import com.entity.TokenEntity;
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

import com.entity.CheshangEntity;
import com.entity.view.CheshangView;

import com.service.CheshangService;
import com.service.TokenService;
import com.utils.PageUtils;
import com.utils.R;
import com.utils.EncryptUtil;
import com.utils.MPUtil;
import com.utils.MapUtils;
import com.utils.CommonUtil;
import java.io.IOException;

/**
 * 车商
 * 后端接口
 * @author 
 * @email 
 * @date 2025-02-12 16:12:11
 */
@RestController
@RequestMapping("/cheshang")
public class CheshangController {
    @Autowired
    private CheshangService cheshangService;






    
	@Autowired
	private TokenService tokenService;
	
	/**
	 * 登录
	 */
	@IgnoreAuth
	@RequestMapping(value = "/login")
	public R login(String username, String password, String captcha, HttpServletRequest request) {
		// 根据登录查询用户信息
        CheshangEntity u = cheshangService.selectOne(new EntityWrapper<CheshangEntity>().eq("cheshangzhanghao", username));
        // 当用户不存在或md5方式验证密码不通过时
        if(u==null || !u.getMima().equals(EncryptUtil.md5(password))) {
            //账号或密码不正确提示
			return R.error("账号或密码不正确");
		}
        // 如果用户是待审核状态，返回请联系管理员审核提示
        if(!"是".equals(u.getSfsh())) return R.error("账号已锁定，请联系管理员审核。");
        // 获取登录token
		String token = tokenService.generateToken(u.getId(), username,"cheshang",  "车商" );
        //返回token
		return R.ok().put("token", token);
	}


	
	/**
     * 注册
     */
	@IgnoreAuth
    @RequestMapping("/register")
    public R register(@RequestBody CheshangEntity cheshang){
    	//ValidatorUtils.validateEntity(cheshang);
        //根据登录账号获取用户信息判断是否存在该用户，否则返回错误信息
    	CheshangEntity u = cheshangService.selectOne(new EntityWrapper<CheshangEntity>().eq("cheshangzhanghao", cheshang.getCheshangzhanghao()));
		if(u!=null) {
			return R.error("注册用户已存在");
		}
        //判断是否存在相同车商账号，否则返回错误信息
        if(cheshangService.selectCount(new EntityWrapper<CheshangEntity>().eq("cheshangzhanghao", cheshang.getCheshangzhanghao()))>0) {
            return R.error("车商账号已存在");
        }
		Long uId = new Date().getTime();
		cheshang.setId(uId);
        //设置登录密码md5方式加密
        cheshang.setMima(EncryptUtil.md5(cheshang.getMima()));
        //保存用户
        cheshangService.insert(cheshang);
        return R.ok();
    }

	
	/**
	 * 退出
	 */
	@RequestMapping("/logout")
	public R logout(HttpServletRequest request) {
		request.getSession().invalidate();
		return R.ok("退出成功");
	}
	
	/**
     * 获取用户的session用户信息
     */
    @RequestMapping("/session")
    public R getCurrUser(HttpServletRequest request){
    	Long id = (Long)request.getSession().getAttribute("userId");
        CheshangEntity u = cheshangService.selectById(id);
        return R.ok().put("data", u);
    }
    
    /**
     * 密码重置
     */
    @IgnoreAuth
	@RequestMapping(value = "/resetPass")
    public R resetPass(String username, HttpServletRequest request){
    	//根据登录账号判断是否存在用户信息，否则返回错误信息
        CheshangEntity u = cheshangService.selectOne(new EntityWrapper<CheshangEntity>().eq("cheshangzhanghao", username));
    	if(u==null) {
    		return R.error("账号不存在");
    	}
        //重置密码为123456，并使用md5方式加密
        u.setMima(EncryptUtil.md5("123456"));
        cheshangService.updateById(u);
        return R.ok("密码已重置为：123456");
    }



    /**
     * 后台列表
     */
    @RequestMapping("/page")
    public R page(@RequestParam Map<String, Object> params,CheshangEntity cheshang,
		HttpServletRequest request){
        //设置查询条件
        EntityWrapper<CheshangEntity> ew = new EntityWrapper<CheshangEntity>();


        //查询结果
		PageUtils page = cheshangService.queryPage(params, MPUtil.sort(MPUtil.between(MPUtil.likeOrEq(ew, cheshang), params), params));
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
    public R list(@RequestParam Map<String, Object> params,CheshangEntity cheshang, 
		HttpServletRequest request){
        //设置查询条件
        EntityWrapper<CheshangEntity> ew = new EntityWrapper<CheshangEntity>();

        //查询结果
		PageUtils page = cheshangService.queryPage(params, MPUtil.sort(MPUtil.between(MPUtil.likeOrEq(ew, cheshang), params), params));
        Map<String, String> deSens = new HashMap<>();
        //给需要脱敏的字段脱敏
        DeSensUtil.desensitize(page,deSens);
        return R.ok().put("data", page);
    }



	/**
     * 列表
     */
    @RequestMapping("/lists")
    public R list( CheshangEntity cheshang){
       	EntityWrapper<CheshangEntity> ew = new EntityWrapper<CheshangEntity>();
      	ew.allEq(MPUtil.allEQMapPre( cheshang, "cheshang")); 
        return R.ok().put("data", cheshangService.selectListView(ew));
    }

	 /**
     * 查询
     */
    @RequestMapping("/query")
    public R query(CheshangEntity cheshang){
        EntityWrapper< CheshangEntity> ew = new EntityWrapper< CheshangEntity>();
 		ew.allEq(MPUtil.allEQMapPre( cheshang, "cheshang")); 
		CheshangView cheshangView =  cheshangService.selectView(ew);
		return R.ok("查询车商成功").put("data", cheshangView);
    }
	
    /**
     * 后台详情
     */
    @RequestMapping("/info/{id}")
    public R info(@PathVariable("id") Long id){
        CheshangEntity cheshang = cheshangService.selectById(id);
        Map<String, String> deSens = new HashMap<>();
        //给需要脱敏的字段脱敏
        DeSensUtil.desensitize(cheshang,deSens);
        return R.ok().put("data", cheshang);
    }

    /**
     * 前台详情
     */
	@IgnoreAuth
    @RequestMapping("/detail/{id}")
    public R detail(@PathVariable("id") Long id){
        CheshangEntity cheshang = cheshangService.selectById(id);
        Map<String, String> deSens = new HashMap<>();
        //给需要脱敏的字段脱敏
        DeSensUtil.desensitize(cheshang,deSens);
        return R.ok().put("data", cheshang);
    }
    



    /**
     * 后台保存
     */
    @RequestMapping("/save")
    @SysLog("新增车商") 
    public R save(@RequestBody CheshangEntity cheshang, HttpServletRequest request){
        //验证字段唯一性，否则返回错误信息
        if(cheshangService.selectCount(new EntityWrapper<CheshangEntity>().eq("cheshangzhanghao", cheshang.getCheshangzhanghao()))>0) {
            return R.error("车商账号已存在");
        }
        //ValidatorUtils.validateEntity(cheshang);
        //验证账号唯一性，否则返回错误信息
        CheshangEntity u = cheshangService.selectOne(new EntityWrapper<CheshangEntity>().eq("cheshangzhanghao", cheshang.getCheshangzhanghao()));
        if(u!=null) {
            return R.error("用户已存在");
        }
    	cheshang.setId(new Date().getTime()+new Double(Math.floor(Math.random()*1000)).longValue());
		cheshang.setId(new Date().getTime());
        //密码使用md5方式加密
        cheshang.setMima(EncryptUtil.md5(cheshang.getMima()));
        cheshangService.insert(cheshang);
        return R.ok().put("data",cheshang.getId());
    }
    
    /**
     * 前台保存
     */
    @SysLog("新增车商")
    @RequestMapping("/add")
    public R add(@RequestBody CheshangEntity cheshang, HttpServletRequest request){
        //验证字段唯一性，否则返回错误信息
        if(cheshangService.selectCount(new EntityWrapper<CheshangEntity>().eq("cheshangzhanghao", cheshang.getCheshangzhanghao()))>0) {
            return R.error("车商账号已存在");
        }
        //ValidatorUtils.validateEntity(cheshang);
        //验证账号唯一性，否则返回错误信息
        CheshangEntity u = cheshangService.selectOne(new EntityWrapper<CheshangEntity>().eq("cheshangzhanghao", cheshang.getCheshangzhanghao()));
        if(u!=null) {
            return R.error("用户已存在");
        }
    	cheshang.setId(new Date().getTime()+new Double(Math.floor(Math.random()*1000)).longValue());
		cheshang.setId(new Date().getTime());
        //密码使用md5方式加密
        cheshang.setMima(EncryptUtil.md5(cheshang.getMima()));
        cheshangService.insert(cheshang);
        return R.ok().put("data",cheshang.getId());
    }





    /**
     * 修改
     */
    @RequestMapping("/update")
    @Transactional
    @SysLog("修改车商")
    public R update(@RequestBody CheshangEntity cheshang, HttpServletRequest request){
        //ValidatorUtils.validateEntity(cheshang);
        //验证字段唯一性，否则返回错误信息
        if(cheshangService.selectCount(new EntityWrapper<CheshangEntity>().ne("id", cheshang.getId()).eq("cheshangzhanghao", cheshang.getCheshangzhanghao()))>0) {
            return R.error("车商账号已存在");
        }
	    CheshangEntity cheshangEntity = cheshangService.selectById(cheshang.getId());
        //如果密码不为空，则判断是否和输入密码一致，不一致则重新设置
        if(StringUtils.isNotBlank(cheshang.getMima()) && !cheshang.getMima().equals(cheshangEntity.getMima())) {
            //密码使用md5方式加密
            cheshang.setMima(EncryptUtil.md5(cheshang.getMima()));
        }
        //全部更新
        cheshangService.updateById(cheshang);
        if(null!=cheshang.getCheshangzhanghao())
        {
            // 修改token
            TokenEntity tokenEntity = new TokenEntity();
            tokenEntity.setUsername(cheshang.getCheshangzhanghao());
            tokenService.update(tokenEntity, new EntityWrapper<TokenEntity>().eq("userid", cheshang.getId()));
        }
        return R.ok();
    }

    /**
     * 审核
     */
    @RequestMapping("/shBatch")
    @Transactional
    @SysLog("审核车商")
    public R update(@RequestBody Long[] ids, @RequestParam String sfsh, @RequestParam String shhf){
        List<CheshangEntity> list = new ArrayList<CheshangEntity>();
        for(Long id : ids) {
            CheshangEntity cheshang = cheshangService.selectById(id);
            cheshang.setSfsh(sfsh);
            cheshang.setShhf(shhf);
            list.add(cheshang);
        }
        cheshangService.updateBatchById(list);
        return R.ok();
    }


    

    /**
     * 删除
     */
    @RequestMapping("/delete")
    @SysLog("删除车商")
    public R delete(@RequestBody Long[] ids){
        cheshangService.deleteBatchIds(Arrays.asList(ids));
        return R.ok();
    }
    











}
