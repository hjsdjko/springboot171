package com.cl.dao;

import com.cl.entity.KechengxuankeEntity;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import java.util.List;
import java.util.Map;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.plugins.pagination.Pagination;

import org.apache.ibatis.annotations.Param;
import com.cl.entity.view.KechengxuankeView;


/**
 * 课程选课
 * 
 * @author 
 * @email 
 * @date 2024-03-12 10:57:06
 */
public interface KechengxuankeDao extends BaseMapper<KechengxuankeEntity> {
	
	List<KechengxuankeView> selectListView(@Param("ew") Wrapper<KechengxuankeEntity> wrapper);

	List<KechengxuankeView> selectListView(Pagination page,@Param("ew") Wrapper<KechengxuankeEntity> wrapper);
	
	KechengxuankeView selectView(@Param("ew") Wrapper<KechengxuankeEntity> wrapper);
	

}
