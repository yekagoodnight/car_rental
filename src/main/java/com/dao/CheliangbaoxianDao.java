package com.dao;

import com.entity.CheliangbaoxianEntity;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import java.util.List;
import java.util.Map;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.plugins.pagination.Pagination;

import org.apache.ibatis.annotations.Param;
import com.entity.vo.CheliangbaoxianVO;
import com.entity.view.CheliangbaoxianView;


/**
 * 车辆保险
 * 
 * @author 
 * @email 
 * @date 2025-02-12 16:12:13
 */
public interface CheliangbaoxianDao extends BaseMapper<CheliangbaoxianEntity> {
	
	List<CheliangbaoxianVO> selectListVO(@Param("ew") Wrapper<CheliangbaoxianEntity> wrapper);
	
	CheliangbaoxianVO selectVO(@Param("ew") Wrapper<CheliangbaoxianEntity> wrapper);
	
	List<CheliangbaoxianView> selectListView(@Param("ew") Wrapper<CheliangbaoxianEntity> wrapper);

	List<CheliangbaoxianView> selectListView(Pagination page,@Param("ew") Wrapper<CheliangbaoxianEntity> wrapper);

	
	CheliangbaoxianView selectView(@Param("ew") Wrapper<CheliangbaoxianEntity> wrapper);
	

}
