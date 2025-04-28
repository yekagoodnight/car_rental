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


import com.dao.CheliangbaoxianDao;
import com.entity.CheliangbaoxianEntity;
import com.service.CheliangbaoxianService;
import com.entity.vo.CheliangbaoxianVO;
import com.entity.view.CheliangbaoxianView;

@Service("cheliangbaoxianService")
public class CheliangbaoxianServiceImpl extends ServiceImpl<CheliangbaoxianDao, CheliangbaoxianEntity> implements CheliangbaoxianService {
	
	
    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        Page<CheliangbaoxianEntity> page = this.selectPage(
                new Query<CheliangbaoxianEntity>(params).getPage(),
                new EntityWrapper<CheliangbaoxianEntity>()
        );
        return new PageUtils(page);
    }
    
    @Override
	public PageUtils queryPage(Map<String, Object> params, Wrapper<CheliangbaoxianEntity> wrapper) {
		  Page<CheliangbaoxianView> page =new Query<CheliangbaoxianView>(params).getPage();
	        page.setRecords(baseMapper.selectListView(page,wrapper));
	    	PageUtils pageUtil = new PageUtils(page);
	    	return pageUtil;
 	}

    
    @Override
	public List<CheliangbaoxianVO> selectListVO(Wrapper<CheliangbaoxianEntity> wrapper) {
 		return baseMapper.selectListVO(wrapper);
	}
	
	@Override
	public CheliangbaoxianVO selectVO(Wrapper<CheliangbaoxianEntity> wrapper) {
 		return baseMapper.selectVO(wrapper);
	}
	
	@Override
	public List<CheliangbaoxianView> selectListView(Wrapper<CheliangbaoxianEntity> wrapper) {
		return baseMapper.selectListView(wrapper);
	}

	@Override
	public CheliangbaoxianView selectView(Wrapper<CheliangbaoxianEntity> wrapper) {
		return baseMapper.selectView(wrapper);
	}


}
