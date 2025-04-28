package com.service;

import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.service.IService;
import com.utils.PageUtils;
import com.entity.CheshangEntity;
import java.util.List;
import java.util.Map;
import com.entity.vo.CheshangVO;
import org.apache.ibatis.annotations.Param;
import com.entity.view.CheshangView;


/**
 * 车商
 *
 * @author 
 * @email 
 * @date 2025-02-12 16:12:11
 */
public interface CheshangService extends IService<CheshangEntity> {

    PageUtils queryPage(Map<String, Object> params);
    
   	List<CheshangVO> selectListVO(Wrapper<CheshangEntity> wrapper);
   	
   	CheshangVO selectVO(@Param("ew") Wrapper<CheshangEntity> wrapper);
   	
   	List<CheshangView> selectListView(Wrapper<CheshangEntity> wrapper);
   	
   	CheshangView selectView(@Param("ew") Wrapper<CheshangEntity> wrapper);
   	
   	PageUtils queryPage(Map<String, Object> params,Wrapper<CheshangEntity> wrapper);

   	

}

