package com.service;

import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.service.IService;
import com.utils.PageUtils;
import com.entity.GuzhangleixingEntity;
import java.util.List;
import java.util.Map;
import com.entity.vo.GuzhangleixingVO;
import org.apache.ibatis.annotations.Param;
import com.entity.view.GuzhangleixingView;


/**
 * 故障类型
 *
 * @author 
 * @email 
 * @date 2025-02-12 16:12:12
 */
public interface GuzhangleixingService extends IService<GuzhangleixingEntity> {

    PageUtils queryPage(Map<String, Object> params);
    
   	List<GuzhangleixingVO> selectListVO(Wrapper<GuzhangleixingEntity> wrapper);
   	
   	GuzhangleixingVO selectVO(@Param("ew") Wrapper<GuzhangleixingEntity> wrapper);
   	
   	List<GuzhangleixingView> selectListView(Wrapper<GuzhangleixingEntity> wrapper);
   	
   	GuzhangleixingView selectView(@Param("ew") Wrapper<GuzhangleixingEntity> wrapper);
   	
   	PageUtils queryPage(Map<String, Object> params,Wrapper<GuzhangleixingEntity> wrapper);

   	

}

