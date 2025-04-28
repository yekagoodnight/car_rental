package com.service;

import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.service.IService;
import com.utils.PageUtils;
import com.entity.CheliangbaoxianEntity;
import java.util.List;
import java.util.Map;
import com.entity.vo.CheliangbaoxianVO;
import org.apache.ibatis.annotations.Param;
import com.entity.view.CheliangbaoxianView;


/**
 * 车辆保险
 *
 * @author 
 * @email 
 * @date 2025-02-12 16:12:13
 */
public interface CheliangbaoxianService extends IService<CheliangbaoxianEntity> {

    PageUtils queryPage(Map<String, Object> params);
    
   	List<CheliangbaoxianVO> selectListVO(Wrapper<CheliangbaoxianEntity> wrapper);
   	
   	CheliangbaoxianVO selectVO(@Param("ew") Wrapper<CheliangbaoxianEntity> wrapper);
   	
   	List<CheliangbaoxianView> selectListView(Wrapper<CheliangbaoxianEntity> wrapper);
   	
   	CheliangbaoxianView selectView(@Param("ew") Wrapper<CheliangbaoxianEntity> wrapper);
   	
   	PageUtils queryPage(Map<String, Object> params,Wrapper<CheliangbaoxianEntity> wrapper);

   	

}

