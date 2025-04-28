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


import com.dao.GuzhangleixingDao;
import com.entity.GuzhangleixingEntity;
import com.service.GuzhangleixingService;
import com.entity.vo.GuzhangleixingVO;
import com.entity.view.GuzhangleixingView;

@Service("guzhangleixingService")
public class GuzhangleixingServiceImpl extends ServiceImpl<GuzhangleixingDao, GuzhangleixingEntity> implements GuzhangleixingService {
	
	
    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        Page<GuzhangleixingEntity> page = this.selectPage(
                new Query<GuzhangleixingEntity>(params).getPage(),
                new EntityWrapper<GuzhangleixingEntity>()
        );
        return new PageUtils(page);
    }
    
    @Override
	public PageUtils queryPage(Map<String, Object> params, Wrapper<GuzhangleixingEntity> wrapper) {
		  Page<GuzhangleixingView> page =new Query<GuzhangleixingView>(params).getPage();
	        page.setRecords(baseMapper.selectListView(page,wrapper));
	    	PageUtils pageUtil = new PageUtils(page);
	    	return pageUtil;
 	}

    
    @Override
	public List<GuzhangleixingVO> selectListVO(Wrapper<GuzhangleixingEntity> wrapper) {
 		return baseMapper.selectListVO(wrapper);
	}
	
	@Override
	public GuzhangleixingVO selectVO(Wrapper<GuzhangleixingEntity> wrapper) {
 		return baseMapper.selectVO(wrapper);
	}
	
	@Override
	public List<GuzhangleixingView> selectListView(Wrapper<GuzhangleixingEntity> wrapper) {
		return baseMapper.selectListView(wrapper);
	}

	@Override
	public GuzhangleixingView selectView(Wrapper<GuzhangleixingEntity> wrapper) {
		return baseMapper.selectView(wrapper);
	}


}
