package com.dao;

import com.entity.ShigushangbaoEntity;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import java.util.List;
import java.util.Map;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.plugins.pagination.Pagination;

import org.apache.ibatis.annotations.Param;
import com.entity.vo.ShigushangbaoVO;
import com.entity.view.ShigushangbaoView;


/**
 * 事故上报
 * 
 * @author 
 * @email 
 * @date 2025-02-12 16:12:12
 */
public interface ShigushangbaoDao extends BaseMapper<ShigushangbaoEntity> {
	
	List<ShigushangbaoVO> selectListVO(@Param("ew") Wrapper<ShigushangbaoEntity> wrapper);
	
	ShigushangbaoVO selectVO(@Param("ew") Wrapper<ShigushangbaoEntity> wrapper);
	
	List<ShigushangbaoView> selectListView(@Param("ew") Wrapper<ShigushangbaoEntity> wrapper);

	List<ShigushangbaoView> selectListView(Pagination page,@Param("ew") Wrapper<ShigushangbaoEntity> wrapper);

	
	ShigushangbaoView selectView(@Param("ew") Wrapper<ShigushangbaoEntity> wrapper);
	

    List<Map<String, Object>> selectValue(@Param("params") Map<String, Object> params,@Param("ew") Wrapper<ShigushangbaoEntity> wrapper);

    List<Map<String, Object>> selectTimeStatValue(@Param("params") Map<String, Object> params,@Param("ew") Wrapper<ShigushangbaoEntity> wrapper);

    List<Map<String, Object>> selectGroup(@Param("params") Map<String, Object> params,@Param("ew") Wrapper<ShigushangbaoEntity> wrapper);



}
