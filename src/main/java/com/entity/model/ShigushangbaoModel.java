package com.entity.model;

import com.entity.ShigushangbaoEntity;

import com.baomidou.mybatisplus.annotations.TableName;
import java.util.Date;
import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;
import java.io.Serializable;
 

/**
 * 事故上报
 * 接收传参的实体类  
 *（实际开发中配合移动端接口开发手动去掉些没用的字段， 后端一般用entity就够用了） 
 * 取自ModelAndView 的model名称
 * @author 
 * @email 
 * @date 2025-02-12 16:12:12
 */
public class ShigushangbaoModel  implements Serializable {
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
	 * 事故时间
	 */
		
	@JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat 
	private Date shigushijian;
		
	/**
	 * 事故地点
	 */
	
	private String shigudidian;
		
	/**
	 * 事故类型
	 */
	
	private String shiguleixing;
		
	/**
	 * 事故描述
	 */
	
	private String shigumiaoshu;
		
	/**
	 * 现场照片
	 */
	
	private String xianchangzhaopian;
		
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
	 * 是否审核
	 */
	
	private String sfsh;
		
	/**
	 * 审核回复
	 */
	
	private String shhf;
				
	
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
	 * 设置：事故时间
	 */
	 
	public void setShigushijian(Date shigushijian) {
		this.shigushijian = shigushijian;
	}
	
	/**
	 * 获取：事故时间
	 */
	public Date getShigushijian() {
		return shigushijian;
	}
				
	
	/**
	 * 设置：事故地点
	 */
	 
	public void setShigudidian(String shigudidian) {
		this.shigudidian = shigudidian;
	}
	
	/**
	 * 获取：事故地点
	 */
	public String getShigudidian() {
		return shigudidian;
	}
				
	
	/**
	 * 设置：事故类型
	 */
	 
	public void setShiguleixing(String shiguleixing) {
		this.shiguleixing = shiguleixing;
	}
	
	/**
	 * 获取：事故类型
	 */
	public String getShiguleixing() {
		return shiguleixing;
	}
				
	
	/**
	 * 设置：事故描述
	 */
	 
	public void setShigumiaoshu(String shigumiaoshu) {
		this.shigumiaoshu = shigumiaoshu;
	}
	
	/**
	 * 获取：事故描述
	 */
	public String getShigumiaoshu() {
		return shigumiaoshu;
	}
				
	
	/**
	 * 设置：现场照片
	 */
	 
	public void setXianchangzhaopian(String xianchangzhaopian) {
		this.xianchangzhaopian = xianchangzhaopian;
	}
	
	/**
	 * 获取：现场照片
	 */
	public String getXianchangzhaopian() {
		return xianchangzhaopian;
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
	 * 设置：是否审核
	 */
	 
	public void setSfsh(String sfsh) {
		this.sfsh = sfsh;
	}
	
	/**
	 * 获取：是否审核
	 */
	public String getSfsh() {
		return sfsh;
	}
				
	
	/**
	 * 设置：审核回复
	 */
	 
	public void setShhf(String shhf) {
		this.shhf = shhf;
	}
	
	/**
	 * 获取：审核回复
	 */
	public String getShhf() {
		return shhf;
	}
			
}
