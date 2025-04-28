package com.service;

import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.service.IService;
import com.utils.PageUtils;
import com.entity.BaoxianleixingEntity;
import java.util.List;
import java.util.Map;
import com.entity.vo.BaoxianleixingVO;
import org.apache.ibatis.annotations.Param;
import com.entity.view.BaoxianleixingView;


/**
 * 保险类型
 *
 * @author 
 * @email 
 * @date 2025-02-12 16:12:13
 */
public interface BaoxianleixingService extends IService<BaoxianleixingEntity> {

    PageUtils queryPage(Map<String, Object> params);
    
   	List<BaoxianleixingVO> selectListVO(Wrapper<BaoxianleixingEntity> wrapper);
   	
   	BaoxianleixingVO selectVO(@Param("ew") Wrapper<BaoxianleixingEntity> wrapper);
   	
   	List<BaoxianleixingView> selectListView(Wrapper<BaoxianleixingEntity> wrapper);
   	
   	BaoxianleixingView selectView(@Param("ew") Wrapper<BaoxianleixingEntity> wrapper);
   	
   	PageUtils queryPage(Map<String, Object> params,Wrapper<BaoxianleixingEntity> wrapper);

   	

}

