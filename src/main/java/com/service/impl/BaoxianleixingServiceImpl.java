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


import com.dao.BaoxianleixingDao;
import com.entity.BaoxianleixingEntity;
import com.service.BaoxianleixingService;
import com.entity.vo.BaoxianleixingVO;
import com.entity.view.BaoxianleixingView;

@Service("baoxianleixingService")
public class BaoxianleixingServiceImpl extends ServiceImpl<BaoxianleixingDao, BaoxianleixingEntity> implements BaoxianleixingService {
	
	
    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        Page<BaoxianleixingEntity> page = this.selectPage(
                new Query<BaoxianleixingEntity>(params).getPage(),
                new EntityWrapper<BaoxianleixingEntity>()
        );
        return new PageUtils(page);
    }
    
    @Override
	public PageUtils queryPage(Map<String, Object> params, Wrapper<BaoxianleixingEntity> wrapper) {
		  Page<BaoxianleixingView> page =new Query<BaoxianleixingView>(params).getPage();
	        page.setRecords(baseMapper.selectListView(page,wrapper));
	    	PageUtils pageUtil = new PageUtils(page);
	    	return pageUtil;
 	}

    
    @Override
	public List<BaoxianleixingVO> selectListVO(Wrapper<BaoxianleixingEntity> wrapper) {
 		return baseMapper.selectListVO(wrapper);
	}
	
	@Override
	public BaoxianleixingVO selectVO(Wrapper<BaoxianleixingEntity> wrapper) {
 		return baseMapper.selectVO(wrapper);
	}
	
	@Override
	public List<BaoxianleixingView> selectListView(Wrapper<BaoxianleixingEntity> wrapper) {
		return baseMapper.selectListView(wrapper);
	}

	@Override
	public BaoxianleixingView selectView(Wrapper<BaoxianleixingEntity> wrapper) {
		return baseMapper.selectView(wrapper);
	}


}
