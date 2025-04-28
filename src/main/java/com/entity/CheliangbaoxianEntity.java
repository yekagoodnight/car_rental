package com.entity;

import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.lang.reflect.InvocationTargetException;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import org.springframework.format.annotation.DateTimeFormat;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.beanutils.BeanUtils;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.enums.FieldFill;
import com.baomidou.mybatisplus.enums.IdType;


/**
 * 车辆保险
 * 数据库通用操作实体类（普通增删改查）
 * @author 
 * @email 
 * @date 2025-02-12 16:12:13
 */
@TableName("cheliangbaoxian")
public class CheliangbaoxianEntity<T> implements Serializable {
	private static final long serialVersionUID = 1L;


	public CheliangbaoxianEntity() {
		
	}
	
	public CheliangbaoxianEntity(T t) {
		try {
			BeanUtils.copyProperties(this, t);
		} catch (IllegalAccessException | InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/**
	 * 主键id
	 */
    @TableId(type = IdType.AUTO)
	private Long id;
	/**
	 * 保单编号
	 */
					
	private String baodanbianhao;
	
	/**
	 * 汽车车牌
	 */
					
	private String qichechepai;
	
	/**
	 * 汽车类型
	 */
					
	private String qicheleixing;
	
	/**
	 * 品牌
	 */
					
	private String pinpai;
	
	/**
	 * 型号
	 */
					
	private String xinghao;
	
	/**
	 * 保险名称
	 */
					
	private String baoxianmingcheng;
	
	/**
	 * 保险类型
	 */
					
	private String baoxianleixing;
	
	/**
	 * 办理时间
	 */
				
	@JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat 		
	private Date banlishijian;
	
	/**
	 * 到期时间
	 */
				
	@JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat 		
	private Date daoqishijian;
	
	/**
	 * 保险描述
	 */
					
	private String baoxianmiaoshu;
	
	/**
	 * 保险费用
	 */
					
	private Integer baoxianfeiyong;
	
	/**
	 * 保险附件
	 */
					
	private String baoxianfujian;
	
	/**
	 * 汽车图片
	 */
					
	private String qichetupian;
	
	/**
	 * 用户账号
	 */
					
	private String yonghuzhanghao;
	
	/**
	 * 用户姓名
	 */
					
	private String yonghuxingming;
	
	/**
	 * 车商账号
	 */
					
	private String cheshangzhanghao;
	
	/**
	 * 是否支付
	 */
					
	private String ispay;
	
	
	@JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat
	private Date addtime;

	public Date getAddtime() {
		return addtime;
	}
	public void setAddtime(Date addtime) {
		this.addtime = addtime;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	/**
	 * 设置：保单编号
	 */
	public void setBaodanbianhao(String baodanbianhao) {
		this.baodanbianhao = baodanbianhao;
	}
	/**
	 * 获取：保单编号
	 */
	public String getBaodanbianhao() {
		return baodanbianhao;
	}
	/**
	 * 设置：汽车车牌
	 */
	public void setQichechepai(String qichechepai) {
		this.qichechepai = qichechepai;
	}
	/**
	 * 获取：汽车车牌
	 */
	public String getQichechepai() {
		return qichechepai;
	}
	/**
	 * 设置：汽车类型
	 */
	public void setQicheleixing(String qicheleixing) {
		this.qicheleixing = qicheleixing;
	}
	/**
	 * 获取：汽车类型
	 */
	public String getQicheleixing() {
		return qicheleixing;
	}
	/**
	 * 设置：品牌
	 */
	public void setPinpai(String pinpai) {
		this.pinpai = pinpai;
	}
	/**
	 * 获取：品牌
	 */
	public String getPinpai() {
		return pinpai;
	}
	/**
	 * 设置：型号
	 */
	public void setXinghao(String xinghao) {
		this.xinghao = xinghao;
	}
	/**
	 * 获取：型号
	 */
	public String getXinghao() {
		return xinghao;
	}
	/**
	 * 设置：保险名称
	 */
	public void setBaoxianmingcheng(String baoxianmingcheng) {
		this.baoxianmingcheng = baoxianmingcheng;
	}
	/**
	 * 获取：保险名称
	 */
	public String getBaoxianmingcheng() {
		return baoxianmingcheng;
	}
	/**
	 * 设置：保险类型
	 */
	public void setBaoxianleixing(String baoxianleixing) {
		this.baoxianleixing = baoxianleixing;
	}
	/**
	 * 获取：保险类型
	 */
	public String getBaoxianleixing() {
		return baoxianleixing;
	}
	/**
	 * 设置：办理时间
	 */
	public void setBanlishijian(Date banlishijian) {
		this.banlishijian = banlishijian;
	}
	/**
	 * 获取：办理时间
	 */
	public Date getBanlishijian() {
		return banlishijian;
	}
	/**
	 * 设置：到期时间
	 */
	public void setDaoqishijian(Date daoqishijian) {
		this.daoqishijian = daoqishijian;
	}
	/**
	 * 获取：到期时间
	 */
	public Date getDaoqishijian() {
		return daoqishijian;
	}
	/**
	 * 设置：保险描述
	 */
	public void setBaoxianmiaoshu(String baoxianmiaoshu) {
		this.baoxianmiaoshu = baoxianmiaoshu;
	}
	/**
	 * 获取：保险描述
	 */
	public String getBaoxianmiaoshu() {
		return baoxianmiaoshu;
	}
	/**
	 * 设置：保险费用
	 */
	public void setBaoxianfeiyong(Integer baoxianfeiyong) {
		this.baoxianfeiyong = baoxianfeiyong;
	}
	/**
	 * 获取：保险费用
	 */
	public Integer getBaoxianfeiyong() {
		return baoxianfeiyong;
	}
	/**
	 * 设置：保险附件
	 */
	public void setBaoxianfujian(String baoxianfujian) {
		this.baoxianfujian = baoxianfujian;
	}
	/**
	 * 获取：保险附件
	 */
	public String getBaoxianfujian() {
		return baoxianfujian;
	}
	/**
	 * 设置：汽车图片
	 */
	public void setQichetupian(String qichetupian) {
		this.qichetupian = qichetupian;
	}
	/**
	 * 获取：汽车图片
	 */
	public String getQichetupian() {
		return qichetupian;
	}
	/**
	 * 设置：用户账号
	 */
	public void setYonghuzhanghao(String yonghuzhanghao) {
		this.yonghuzhanghao = yonghuzhanghao;
	}
	/**
	 * 获取：用户账号
	 */
	public String getYonghuzhanghao() {
		return yonghuzhanghao;
	}
	/**
	 * 设置：用户姓名
	 */
	public void setYonghuxingming(String yonghuxingming) {
		this.yonghuxingming = yonghuxingming;
	}
	/**
	 * 获取：用户姓名
	 */
	public String getYonghuxingming() {
		return yonghuxingming;
	}
	/**
	 * 设置：车商账号
	 */
	public void setCheshangzhanghao(String cheshangzhanghao) {
		this.cheshangzhanghao = cheshangzhanghao;
	}
	/**
	 * 获取：车商账号
	 */
	public String getCheshangzhanghao() {
		return cheshangzhanghao;
	}
	/**
	 * 设置：是否支付
	 */
	public void setIspay(String ispay) {
		this.ispay = ispay;
	}
	/**
	 * 获取：是否支付
	 */
	public String getIspay() {
		return ispay;
	}

}
