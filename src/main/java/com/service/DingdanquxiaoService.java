package com.service;

import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.service.IService;
import com.utils.PageUtils;
import com.entity.DingdanquxiaoEntity;
import java.util.List;
import java.util.Map;
import com.entity.vo.DingdanquxiaoVO;
import org.apache.ibatis.annotations.Param;
import com.entity.view.DingdanquxiaoView;


/**
 * 订单取消
 *
 * @author 
 * @email 
 * @date 2025-02-12 16:12:12
 */
public interface DingdanquxiaoService extends IService<DingdanquxiaoEntity> {

    PageUtils queryPage(Map<String, Object> params);
    
   	List<DingdanquxiaoVO> selectListVO(Wrapper<DingdanquxiaoEntity> wrapper);
   	
   	DingdanquxiaoVO selectVO(@Param("ew") Wrapper<DingdanquxiaoEntity> wrapper);
   	
   	List<DingdanquxiaoView> selectListView(Wrapper<DingdanquxiaoEntity> wrapper);
   	
   	DingdanquxiaoView selectView(@Param("ew") Wrapper<DingdanquxiaoEntity> wrapper);
   	
   	PageUtils queryPage(Map<String, Object> params,Wrapper<DingdanquxiaoEntity> wrapper);

   	

}

