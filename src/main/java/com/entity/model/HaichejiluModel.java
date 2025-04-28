package com.entity.model;

import com.entity.HaichejiluEntity;

import com.baomidou.mybatisplus.annotations.TableName;
import java.util.Date;
import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;
import java.io.Serializable;
 

/**
 * 还车记录
 * 接收传参的实体类  
 *（实际开发中配合移动端接口开发手动去掉些没用的字段， 后端一般用entity就够用了） 
 * 取自ModelAndView 的model名称
 * @author 
 * @email 
 * @date 2025-02-12 16:12:12
 */
public class HaichejiluModel  implements Serializable {
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
	 * 日租金
	 */
	
	private Double rizujin;
		
	/**
	 * 租车时间
	 */
		
	@JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat 
	private Date zucheshijian;
		
	/**
	 * 还车时间
	 */
		
	@JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat 
	private Date haicheshijian;
		
	/**
	 * 还车备注
	 */
	
	private String haichebeizhu;
		
	/**
	 * 汽车图片
	 */
	
	private String qichetupian;
		
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
	 * 跨表用户id
	 */
	
	private Long crossuserid;
		
	/**
	 * 跨表主键id
	 */
	
	private Long crossrefid;
		
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
	 * 设置：还车时间
	 */
	 
	public void setHaicheshijian(Date haicheshijian) {
		this.haicheshijian = haicheshijian;
	}
	
	/**
	 * 获取：还车时间
	 */
	public Date getHaicheshijian() {
		return haicheshijian;
	}
				
	
	/**
	 * 设置：还车备注
	 */
	 
	public void setHaichebeizhu(String haichebeizhu) {
		this.haichebeizhu = haichebeizhu;
	}
	
	/**
	 * 获取：还车备注
	 */
	public String getHaichebeizhu() {
		return haichebeizhu;
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
	 * 设置：跨表用户id
	 */
	 
	public void setCrossuserid(Long crossuserid) {
		this.crossuserid = crossuserid;
	}
	
	/**
	 * 获取：跨表用户id
	 */
	public Long getCrossuserid() {
		return crossuserid;
	}
				
	
	/**
	 * 设置：跨表主键id
	 */
	 
	public void setCrossrefid(Long crossrefid) {
		this.crossrefid = crossrefid;
	}
	
	/**
	 * 获取：跨表主键id
	 */
	public Long getCrossrefid() {
		return crossrefid;
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
