package com.cl.service.impl;

import org.springframework.stereotype.Service;
import java.util.Map;
import java.util.List;

import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.cl.utils.PageUtils;
import com.cl.utils.Query;


import com.cl.dao.KechengxuankeDao;
import com.cl.entity.KechengxuankeEntity;
import com.cl.service.KechengxuankeService;
import com.cl.entity.view.KechengxuankeView;

@Service("kechengxuankeService")
public class KechengxuankeServiceImpl extends ServiceImpl<KechengxuankeDao, KechengxuankeEntity> implements KechengxuankeService {
	
	
    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        Page<KechengxuankeEntity> page = this.selectPage(
                new Query<KechengxuankeEntity>(params).getPage(),
                new EntityWrapper<KechengxuankeEntity>()
        );
        return new PageUtils(page);
    }
    
    @Override
	public PageUtils queryPage(Map<String, Object> params, Wrapper<KechengxuankeEntity> wrapper) {
		  Page<KechengxuankeView> page =new Query<KechengxuankeView>(params).getPage();
	        page.setRecords(baseMapper.selectListView(page,wrapper));
	    	PageUtils pageUtil = new PageUtils(page);
	    	return pageUtil;
 	}
    
	@Override
	public List<KechengxuankeView> selectListView(Wrapper<KechengxuankeEntity> wrapper) {
		return baseMapper.selectListView(wrapper);
	}

	@Override
	public KechengxuankeView selectView(Wrapper<KechengxuankeEntity> wrapper) {
		return baseMapper.selectView(wrapper);
	}


}
