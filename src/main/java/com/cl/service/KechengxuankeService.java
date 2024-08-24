package com.cl.service;

import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.service.IService;
import com.cl.utils.PageUtils;
import com.cl.entity.KechengxuankeEntity;
import java.util.List;
import java.util.Map;
import org.apache.ibatis.annotations.Param;
import com.cl.entity.view.KechengxuankeView;


/**
 * 课程选课
 *
 * @author 
 * @email 
 * @date 2024-03-12 10:57:06
 */
public interface KechengxuankeService extends IService<KechengxuankeEntity> {

    PageUtils queryPage(Map<String, Object> params);
    
   	List<KechengxuankeView> selectListView(Wrapper<KechengxuankeEntity> wrapper);
   	
   	KechengxuankeView selectView(@Param("ew") Wrapper<KechengxuankeEntity> wrapper);
   	
   	PageUtils queryPage(Map<String, Object> params,Wrapper<KechengxuankeEntity> wrapper);
   	

}

