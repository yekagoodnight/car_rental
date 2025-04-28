package com.service;

import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.service.IService;
import com.utils.PageUtils;
import com.entity.ShigushangbaoEntity;
import java.util.List;
import java.util.Map;
import com.entity.vo.ShigushangbaoVO;
import org.apache.ibatis.annotations.Param;
import com.entity.view.ShigushangbaoView;


/**
 * 事故上报
 *
 * @author 
 * @email 
 * @date 2025-02-12 16:12:12
 */
public interface ShigushangbaoService extends IService<ShigushangbaoEntity> {

    PageUtils queryPage(Map<String, Object> params);
    
   	List<ShigushangbaoVO> selectListVO(Wrapper<ShigushangbaoEntity> wrapper);
   	
   	ShigushangbaoVO selectVO(@Param("ew") Wrapper<ShigushangbaoEntity> wrapper);
   	
   	List<ShigushangbaoView> selectListView(Wrapper<ShigushangbaoEntity> wrapper);
   	
   	ShigushangbaoView selectView(@Param("ew") Wrapper<ShigushangbaoEntity> wrapper);
   	
   	PageUtils queryPage(Map<String, Object> params,Wrapper<ShigushangbaoEntity> wrapper);

   	

    List<Map<String, Object>> selectValue(Map<String, Object> params,Wrapper<ShigushangbaoEntity> wrapper);

    List<Map<String, Object>> selectTimeStatValue(Map<String, Object> params,Wrapper<ShigushangbaoEntity> wrapper);

    List<Map<String, Object>> selectGroup(Map<String, Object> params,Wrapper<ShigushangbaoEntity> wrapper);



}

