package com.service;

import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.service.IService;
import com.utils.PageUtils;
import com.entity.DiscussqichechuzuEntity;
import java.util.List;
import java.util.Map;
import com.entity.vo.DiscussqichechuzuVO;
import org.apache.ibatis.annotations.Param;
import com.entity.view.DiscussqichechuzuView;


/**
 * 汽车租赁评论表
 *
 * @author 
 * @email 
 * @date 2025-02-12 16:12:14
 */
public interface DiscussqichechuzuService extends IService<DiscussqichechuzuEntity> {

    PageUtils queryPage(Map<String, Object> params);
    
   	List<DiscussqichechuzuVO> selectListVO(Wrapper<DiscussqichechuzuEntity> wrapper);
   	
   	DiscussqichechuzuVO selectVO(@Param("ew") Wrapper<DiscussqichechuzuEntity> wrapper);
   	
   	List<DiscussqichechuzuView> selectListView(Wrapper<DiscussqichechuzuEntity> wrapper);
   	
   	DiscussqichechuzuView selectView(@Param("ew") Wrapper<DiscussqichechuzuEntity> wrapper);
   	
   	PageUtils queryPage(Map<String, Object> params,Wrapper<DiscussqichechuzuEntity> wrapper);

   	

}

