package com.dao;

import com.entity.CheshangEntity;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import java.util.List;
import java.util.Map;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.plugins.pagination.Pagination;

import org.apache.ibatis.annotations.Param;
import com.entity.vo.CheshangVO;
import com.entity.view.CheshangView;


/**
 * 车商
 * 
 * @author 
 * @email 
 * @date 2025-02-12 16:12:11
 */
public interface CheshangDao extends BaseMapper<CheshangEntity> {
	
	List<CheshangVO> selectListVO(@Param("ew") Wrapper<CheshangEntity> wrapper);
	
	CheshangVO selectVO(@Param("ew") Wrapper<CheshangEntity> wrapper);
	
	List<CheshangView> selectListView(@Param("ew") Wrapper<CheshangEntity> wrapper);

	List<CheshangView> selectListView(Pagination page,@Param("ew") Wrapper<CheshangEntity> wrapper);

	
	CheshangView selectView(@Param("ew") Wrapper<CheshangEntity> wrapper);
	

}
