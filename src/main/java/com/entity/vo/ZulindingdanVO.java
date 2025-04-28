package com.entity.vo;

import com.entity.ZulindingdanEntity;

import com.baomidou.mybatisplus.annotations.TableName;
import java.util.Date;
import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;
import java.io.Serializable;
 

/**
 * 租赁订单
 * @author 
 * @email 
 * @date 2025-02-12 16:12:12
 */
public class ZulindingdanVO  implements Serializable {
	private static final long serialVersionUID = 1L;

	 			
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
	 * 年份
	 */
	
	private String nianfen;
		
	/**
	 * 日租金
	 */
	
	private Double rizujin;
		
	/**
	 * 天数
	 */
	
	private Integer tianshu;
		
	/**
	 * 应付价格
	 */
	
	private Double yingfujiage;
		
	/**
	 * 汽车图片
	 */
	
	private String qichetupian;
		
	/**
	 * 租车时间
	 */
		
	@JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat 
	private Date zucheshijian;
		
	/**
	 * 租车备注
	 */
	
	private String zuchebeizhu;
		
	/**
	 * 车商账号
	 */
	
	private String cheshangzhanghao;
		
	/**
	 * 用户账号
	 */
	
	private String yonghuzhanghao;
		
	/**
	 * 用户姓名
	 */
	
	private String yonghuxingming;
		
	/**
	 * 是否支付
	 */
	
	private String ispay;
		
	/**
	 * 用户id
	 */
	
	private Long userid;
				
	
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
	 * 设置：年份
	 */
	 
	public void setNianfen(String nianfen) {
		this.nianfen = nianfen;
	}
	
	/**
	 * 获取：年份
	 */
	public String getNianfen() {
		return nianfen;
	}
				
	
	/**
	 * 设置：日租金
	 */
	 
	public void setRizujin(Double rizujin) {
		this.rizujin = rizujin;
	}
	
	/**
	 * 获取：日租金
	 */
	public Double getRizujin() {
		return rizujin;
	}
				
	
	/**
	 * 设置：天数
	 */
	 
	public void setTianshu(Integer tianshu) {
		this.tianshu = tianshu;
	}
	
	/**
	 * 获取：天数
	 */
	public Integer getTianshu() {
		return tianshu;
	}
				
	
	/**
	 * 设置：应付价格
	 */
	 
	public void setYingfujiage(Double yingfujiage) {
		this.yingfujiage = yingfujiage;
	}
	
	/**
	 * 获取：应付价格
	 */
	public Double getYingfujiage() {
		return yingfujiage;
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
	 * 设置：租车时间
	 */
	 
	public void setZucheshijian(Date zucheshijian) {
		this.zucheshijian = zucheshijian;
	}
	
	/**
	 * 获取：租车时间
	 */
	public Date getZucheshijian() {
		return zucheshijian;
	}
				
	
	/**
	 * 设置：租车备注
	 */
	 
	public void setZuchebeizhu(String zuchebeizhu) {
		this.zuchebeizhu = zuchebeizhu;
	}
	
	/**
	 * 获取：租车备注
	 */
	public String getZuchebeizhu() {
		return zuchebeizhu;
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
				
	
	/**
	 * 设置：用户id
	 */
	 
	public void setUserid(Long userid) {
		this.userid = userid;
	}
	
	/**
	 * 获取：用户id
	 */
	public Long getUserid() {
		return userid;
	}
			
}
