package com.service.impl;

import org.springframework.stereotype.Service;
import java.util.Map;
import java.util.List;

import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.utils.PageUtils;
import com.utils.Query;


import com.dao.CheshangDao;
import com.entity.CheshangEntity;
import com.service.CheshangService;
import com.entity.vo.CheshangVO;
import com.entity.view.CheshangView;

@Service("cheshangService")
public class CheshangServiceImpl extends ServiceImpl<CheshangDao, CheshangEntity> implements CheshangService {
	
	
    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        Page<CheshangEntity> page = this.selectPage(
                new Query<CheshangEntity>(params).getPage(),
                new EntityWrapper<CheshangEntity>()
        );
        return new PageUtils(page);
    }
    
    @Override
	public PageUtils queryPage(Map<String, Object> params, Wrapper<CheshangEntity> wrapper) {
		  Page<CheshangView> page =new Query<CheshangView>(params).getPage();
	        page.setRecords(baseMapper.selectListView(page,wrapper));
	    	PageUtils pageUtil = new PageUtils(page);
	    	return pageUtil;
 	}

    
    @Override
	public List<CheshangVO> selectListVO(Wrapper<CheshangEntity> wrapper) {
 		return baseMapper.selectListVO(wrapper);
	}
	
	@Override
	public CheshangVO selectVO(Wrapper<CheshangEntity> wrapper) {
 		return baseMapper.selectVO(wrapper);
	}
	
	@Override
	public List<CheshangView> selectListView(Wrapper<CheshangEntity> wrapper) {
		return baseMapper.selectListView(wrapper);
	}

	@Override
	public CheshangView selectView(Wrapper<CheshangEntity> wrapper) {
		return baseMapper.selectView(wrapper);
	}


}
