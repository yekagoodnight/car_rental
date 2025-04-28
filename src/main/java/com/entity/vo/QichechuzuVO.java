package com.entity.vo;

import com.entity.QichechuzuEntity;

import com.baomidou.mybatisplus.annotations.TableName;
import java.util.Date;
import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;
import java.io.Serializable;
 

/**
 * 汽车租赁
 * @author 
 * @email 
 * @date 2025-02-12 16:12:12
 */
public class QichechuzuVO  implements Serializable {
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
	 * 地区
	 */
	
	private String diqu;
		
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
	 * 状态
	 */
	
	private String zhuangtai;
		
	/**
	 * 日租金
	 */
	
	private Double rizujin;
		
	/**
	 * 检测报告
	 */
	
	private String jiancebaogao;
		
	/**
	 * 汽车配置
	 */
	
	private String qichepeizhi;
		
	/**
	 * 汽车图片
	 */
	
	private String qichetupian;
		
	/**
	 * 车商账号
	 */
	
	private String cheshangzhanghao;
		
	/**
	 * 车商姓名
	 */
	
	private String cheshangxingming;
		
	/**
	 * 最近点击时间
	 */
		
	@JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat 
	private Date clicktime;
		
	/**
	 * 点击次数
	 */
	
	private Integer clicknum;
		
	/**
	 * 评论数
	 */
	
	private Integer discussnum;
		
	/**
	 * 用户id
	 */
	
	private Long userid;
		
	/**
	 * 收藏数
	 */
	
	private Integer storeupnum;
				
	
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
	 * 设置：地区
	 */
	 
	public void setDiqu(String diqu) {
		this.diqu = diqu;
	}
	
	/**
	 * 获取：地区
	 */
	public String getDiqu() {
		return diqu;
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
	 * 设置：状态
	 */
	 
	public void setZhuangtai(String zhuangtai) {
		this.zhuangtai = zhuangtai;
	}
	
	/**
	 * 获取：状态
	 */
	public String getZhuangtai() {
		return zhuangtai;
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
	 * 设置：检测报告
	 */
	 
	public void setJiancebaogao(String jiancebaogao) {
		this.jiancebaogao = jiancebaogao;
	}
	
	/**
	 * 获取：检测报告
	 */
	public String getJiancebaogao() {
		return jiancebaogao;
	}
				
	
	/**
	 * 设置：汽车配置
	 */
	 
	public void setQichepeizhi(String qichepeizhi) {
		this.qichepeizhi = qichepeizhi;
	}
	
	/**
	 * 获取：汽车配置
	 */
	public String getQichepeizhi() {
		return qichepeizhi;
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
	 * 设置：车商姓名
	 */
	 
	public void setCheshangxingming(String cheshangxingming) {
		this.cheshangxingming = cheshangxingming;
	}
	
	/**
	 * 获取：车商姓名
	 */
	public String getCheshangxingming() {
		return cheshangxingming;
	}
				
	
	/**
	 * 设置：最近点击时间
	 */
	 
	public void setClicktime(Date clicktime) {
		this.clicktime = clicktime;
	}
	
	/**
	 * 获取：最近点击时间
	 */
	public Date getClicktime() {
		return clicktime;
	}
				
	
	/**
	 * 设置：点击次数
	 */
	 
	public void setClicknum(Integer clicknum) {
		this.clicknum = clicknum;
	}
	
	/**
	 * 获取：点击次数
	 */
	public Integer getClicknum() {
		return clicknum;
	}
				
	
	/**
	 * 设置：评论数
	 */
	 
	public void setDiscussnum(Integer discussnum) {
		this.discussnum = discussnum;
	}
	
	/**
	 * 获取：评论数
	 */
	public Integer getDiscussnum() {
		return discussnum;
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
				
	
	/**
	 * 设置：收藏数
	 */
	 
	public void setStoreupnum(Integer storeupnum) {
		this.storeupnum = storeupnum;
	}
	
	/**
	 * 获取：收藏数
	 */
	public Integer getStoreupnum() {
		return storeupnum;
	}
			
}
