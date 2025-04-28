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


import com.dao.DiscussqichechuzuDao;
import com.entity.DiscussqichechuzuEntity;
import com.service.DiscussqichechuzuService;
import com.entity.vo.DiscussqichechuzuVO;
import com.entity.view.DiscussqichechuzuView;

@Service("discussqichechuzuService")
public class DiscussqichechuzuServiceImpl extends ServiceImpl<DiscussqichechuzuDao, DiscussqichechuzuEntity> implements DiscussqichechuzuService {
	
	
    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        Page<DiscussqichechuzuEntity> page = this.selectPage(
                new Query<DiscussqichechuzuEntity>(params).getPage(),
                new EntityWrapper<DiscussqichechuzuEntity>()
        );
        return new PageUtils(page);
    }
    
    @Override
	public PageUtils queryPage(Map<String, Object> params, Wrapper<DiscussqichechuzuEntity> wrapper) {
		  Page<DiscussqichechuzuView> page =new Query<DiscussqichechuzuView>(params).getPage();
	        page.setRecords(baseMapper.selectListView(page,wrapper));
	    	PageUtils pageUtil = new PageUtils(page);
	    	return pageUtil;
 	}

    
    @Override
	public List<DiscussqichechuzuVO> selectListVO(Wrapper<DiscussqichechuzuEntity> wrapper) {
 		return baseMapper.selectListVO(wrapper);
	}
	
	@Override
	public DiscussqichechuzuVO selectVO(Wrapper<DiscussqichechuzuEntity> wrapper) {
 		return baseMapper.selectVO(wrapper);
	}
	
	@Override
	public List<DiscussqichechuzuView> selectListView(Wrapper<DiscussqichechuzuEntity> wrapper) {
		return baseMapper.selectListView(wrapper);
	}

	@Override
	public DiscussqichechuzuView selectView(Wrapper<DiscussqichechuzuEntity> wrapper) {
		return baseMapper.selectView(wrapper);
	}


}
