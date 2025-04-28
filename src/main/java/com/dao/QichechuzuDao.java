package com.dao;

import com.entity.QichechuzuEntity;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import java.util.List;
import java.util.Map;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.plugins.pagination.Pagination;

import org.apache.ibatis.annotations.Param;
import com.entity.vo.QichechuzuVO;
import com.entity.view.QichechuzuView;


/**
 * 汽车租赁
 * 
 * @author 
 * @email 
 * @date 2025-02-12 16:12:12
 */
public interface QichechuzuDao extends BaseMapper<QichechuzuEntity> {
	
	List<QichechuzuVO> selectListVO(@Param("ew") Wrapper<QichechuzuEntity> wrapper);
	
	QichechuzuVO selectVO(@Param("ew") Wrapper<QichechuzuEntity> wrapper);
	
	List<QichechuzuView> selectListView(@Param("ew") Wrapper<QichechuzuEntity> wrapper);

	List<QichechuzuView> selectListView(Pagination page,@Param("ew") Wrapper<QichechuzuEntity> wrapper);

	
	QichechuzuView selectView(@Param("ew") Wrapper<QichechuzuEntity> wrapper);
	

    List<Map<String, Object>> selectValue(@Param("params") Map<String, Object> params,@Param("ew") Wrapper<QichechuzuEntity> wrapper);

    List<Map<String, Object>> selectTimeStatValue(@Param("params") Map<String, Object> params,@Param("ew") Wrapper<QichechuzuEntity> wrapper);

    List<Map<String, Object>> selectGroup(@Param("params") Map<String, Object> params,@Param("ew") Wrapper<QichechuzuEntity> wrapper);



}
