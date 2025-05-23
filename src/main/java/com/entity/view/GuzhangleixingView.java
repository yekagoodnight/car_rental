package com.entity.view;

import com.entity.GuzhangleixingEntity;

import com.baomidou.mybatisplus.annotations.TableName;
import org.apache.commons.beanutils.BeanUtils;
import java.lang.reflect.InvocationTargetException;
import java.math.BigDecimal;

import java.io.Serializable;
import com.utils.EncryptUtil;
 

/**
 * 故障类型
 * 后端返回视图实体辅助类   
 * （通常后端关联的表或者自定义的字段需要返回使用）
 * @author 
 * @email 
 * @date 2025-02-12 16:12:12
 */
@TableName("guzhangleixing")
public class GuzhangleixingView  extends GuzhangleixingEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	public GuzhangleixingView(){
	}
 
 	public GuzhangleixingView(GuzhangleixingEntity guzhangleixingEntity){
 	try {
			BeanUtils.copyProperties(this, guzhangleixingEntity);
		} catch (IllegalAccessException | InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
 		
	}


}
