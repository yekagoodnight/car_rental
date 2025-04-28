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


import com.dao.ShigushangbaoDao;
import com.entity.ShigushangbaoEntity;
import com.service.ShigushangbaoService;
import com.entity.vo.ShigushangbaoVO;
import com.entity.view.ShigushangbaoView;

@Service("shigushangbaoService")
public class ShigushangbaoServiceImpl extends ServiceImpl<ShigushangbaoDao, ShigushangbaoEntity> implements ShigushangbaoService {
	
	
    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        Page<ShigushangbaoEntity> page = this.selectPage(
                new Query<ShigushangbaoEntity>(params).getPage(),
                new EntityWrapper<ShigushangbaoEntity>()
        );
        return new PageUtils(page);
    }
    
    @Override
	public PageUtils queryPage(Map<String, Object> params, Wrapper<ShigushangbaoEntity> wrapper) {
		  Page<ShigushangbaoView> page =new Query<ShigushangbaoView>(params).getPage();
	        page.setRecords(baseMapper.selectListView(page,wrapper));
	    	PageUtils pageUtil = new PageUtils(page);
	    	return pageUtil;
 	}

    
    @Override
	public List<ShigushangbaoVO> selectListVO(Wrapper<ShigushangbaoEntity> wrapper) {
 		return baseMapper.selectListVO(wrapper);
	}
	
	@Override
	public ShigushangbaoVO selectVO(Wrapper<ShigushangbaoEntity> wrapper) {
 		return baseMapper.selectVO(wrapper);
	}
	
	@Override
	public List<ShigushangbaoView> selectListView(Wrapper<ShigushangbaoEntity> wrapper) {
		return baseMapper.selectListView(wrapper);
	}

	@Override
	public ShigushangbaoView selectView(Wrapper<ShigushangbaoEntity> wrapper) {
		return baseMapper.selectView(wrapper);
	}

    @Override
    public List<Map<String, Object>> selectValue(Map<String, Object> params, Wrapper<ShigushangbaoEntity> wrapper) {
        return baseMapper.selectValue(params, wrapper);
    }

    @Override
    public List<Map<String, Object>> selectTimeStatValue(Map<String, Object> params, Wrapper<ShigushangbaoEntity> wrapper) {
        return baseMapper.selectTimeStatValue(params, wrapper);
    }

    @Override
    public List<Map<String, Object>> selectGroup(Map<String, Object> params, Wrapper<ShigushangbaoEntity> wrapper) {
        return baseMapper.selectGroup(params, wrapper);
    }




}
