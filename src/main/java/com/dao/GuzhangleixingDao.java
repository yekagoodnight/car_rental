package com.dao;

import com.entity.GuzhangleixingEntity;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import java.util.List;
import java.util.Map;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.plugins.pagination.Pagination;

import org.apache.ibatis.annotations.Param;
import com.entity.vo.GuzhangleixingVO;
import com.entity.view.GuzhangleixingView;


/**
 * 故障类型
 * 
 * @author 
 * @email 
 * @date 2025-02-12 16:12:12
 */
public interface GuzhangleixingDao extends BaseMapper<GuzhangleixingEntity> {
	
	List<GuzhangleixingVO> selectListVO(@Param("ew") Wrapper<GuzhangleixingEntity> wrapper);
	
	GuzhangleixingVO selectVO(@Param("ew") Wrapper<GuzhangleixingEntity> wrapper);
	
	List<GuzhangleixingView> selectListView(@Param("ew") Wrapper<GuzhangleixingEntity> wrapper);

	List<GuzhangleixingView> selectListView(Pagination page,@Param("ew") Wrapper<GuzhangleixingEntity> wrapper);

	
	GuzhangleixingView selectView(@Param("ew") Wrapper<GuzhangleixingEntity> wrapper);
	

}
