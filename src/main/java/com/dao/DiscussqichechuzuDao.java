package com.dao;

import com.entity.DiscussqichechuzuEntity;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import java.util.List;
import java.util.Map;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.plugins.pagination.Pagination;

import org.apache.ibatis.annotations.Param;
import com.entity.vo.DiscussqichechuzuVO;
import com.entity.view.DiscussqichechuzuView;


/**
 * 汽车租赁评论表
 * 
 * @author 
 * @email 
 * @date 2025-02-12 16:12:14
 */
public interface DiscussqichechuzuDao extends BaseMapper<DiscussqichechuzuEntity> {
	
	List<DiscussqichechuzuVO> selectListVO(@Param("ew") Wrapper<DiscussqichechuzuEntity> wrapper);
	
	DiscussqichechuzuVO selectVO(@Param("ew") Wrapper<DiscussqichechuzuEntity> wrapper);
	
	List<DiscussqichechuzuView> selectListView(@Param("ew") Wrapper<DiscussqichechuzuEntity> wrapper);

	List<DiscussqichechuzuView> selectListView(Pagination page,@Param("ew") Wrapper<DiscussqichechuzuEntity> wrapper);

	
	DiscussqichechuzuView selectView(@Param("ew") Wrapper<DiscussqichechuzuEntity> wrapper);
	

}
