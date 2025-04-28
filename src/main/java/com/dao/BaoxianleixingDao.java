package com.dao;

import com.entity.BaoxianleixingEntity;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import java.util.List;
import java.util.Map;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.plugins.pagination.Pagination;

import org.apache.ibatis.annotations.Param;
import com.entity.vo.BaoxianleixingVO;
import com.entity.view.BaoxianleixingView;


/**
 * 保险类型
 * 
 * @author 
 * @email 
 * @date 2025-02-12 16:12:13
 */
public interface BaoxianleixingDao extends BaseMapper<BaoxianleixingEntity> {
	
	List<BaoxianleixingVO> selectListVO(@Param("ew") Wrapper<BaoxianleixingEntity> wrapper);
	
	BaoxianleixingVO selectVO(@Param("ew") Wrapper<BaoxianleixingEntity> wrapper);
	
	List<BaoxianleixingView> selectListView(@Param("ew") Wrapper<BaoxianleixingEntity> wrapper);

	List<BaoxianleixingView> selectListView(Pagination page,@Param("ew") Wrapper<BaoxianleixingEntity> wrapper);

	
	BaoxianleixingView selectView(@Param("ew") Wrapper<BaoxianleixingEntity> wrapper);
	

}
