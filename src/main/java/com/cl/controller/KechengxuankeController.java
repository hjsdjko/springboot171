package com.cl.controller;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Map;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Date;
import java.util.List;
import javax.servlet.http.HttpServletRequest;

import com.cl.utils.ValidatorUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.cl.annotation.IgnoreAuth;

import com.cl.entity.KechengxuankeEntity;
import com.cl.entity.view.KechengxuankeView;

import com.cl.service.KechengxuankeService;
import com.cl.service.TokenService;
import com.cl.utils.PageUtils;
import com.cl.utils.R;
import com.cl.utils.MPUtil;
import com.cl.utils.CommonUtil;
import java.io.IOException;

/**
 * 课程选课
 * 后端接口
 * @author 
 * @email 
 * @date 2024-03-12 10:57:06
 */
@RestController
@RequestMapping("/kechengxuanke")
public class KechengxuankeController {
    @Autowired
    private KechengxuankeService kechengxuankeService;



    


    /**
     * 后端列表
     */
    @RequestMapping("/page")
    public R page(@RequestParam Map<String, Object> params,KechengxuankeEntity kechengxuanke,
		HttpServletRequest request){
		String tableName = request.getSession().getAttribute("tableName").toString();
		if(tableName.equals("jiaoshi")) {
			kechengxuanke.setGonghao((String)request.getSession().getAttribute("username"));
		}
		if(tableName.equals("xuesheng")) {
			kechengxuanke.setZhanghao((String)request.getSession().getAttribute("username"));
		}
        EntityWrapper<KechengxuankeEntity> ew = new EntityWrapper<KechengxuankeEntity>();

		PageUtils page = kechengxuankeService.queryPage(params, MPUtil.sort(MPUtil.between(MPUtil.likeOrEq(ew, kechengxuanke), params), params));

        return R.ok().put("data", page);
    }
    
    /**
     * 前端列表
     */
	@IgnoreAuth
    @RequestMapping("/list")
    public R list(@RequestParam Map<String, Object> params,KechengxuankeEntity kechengxuanke, 
		HttpServletRequest request){
        EntityWrapper<KechengxuankeEntity> ew = new EntityWrapper<KechengxuankeEntity>();

		PageUtils page = kechengxuankeService.queryPage(params, MPUtil.sort(MPUtil.between(MPUtil.likeOrEq(ew, kechengxuanke), params), params));
        return R.ok().put("data", page);
    }

	/**
     * 列表
     */
    @RequestMapping("/lists")
    public R list( KechengxuankeEntity kechengxuanke){
       	EntityWrapper<KechengxuankeEntity> ew = new EntityWrapper<KechengxuankeEntity>();
      	ew.allEq(MPUtil.allEQMapPre( kechengxuanke, "kechengxuanke")); 
        return R.ok().put("data", kechengxuankeService.selectListView(ew));
    }

	 /**
     * 查询
     */
    @RequestMapping("/query")
    public R query(KechengxuankeEntity kechengxuanke){
        EntityWrapper< KechengxuankeEntity> ew = new EntityWrapper< KechengxuankeEntity>();
 		ew.allEq(MPUtil.allEQMapPre( kechengxuanke, "kechengxuanke")); 
		KechengxuankeView kechengxuankeView =  kechengxuankeService.selectView(ew);
		return R.ok("查询课程选课成功").put("data", kechengxuankeView);
    }
	
    /**
     * 后端详情
     */
    @RequestMapping("/info/{id}")
    public R info(@PathVariable("id") Long id){
        KechengxuankeEntity kechengxuanke = kechengxuankeService.selectById(id);
		kechengxuanke = kechengxuankeService.selectView(new EntityWrapper<KechengxuankeEntity>().eq("id", id));
        return R.ok().put("data", kechengxuanke);
    }

    /**
     * 前端详情
     */
	@IgnoreAuth
    @RequestMapping("/detail/{id}")
    public R detail(@PathVariable("id") Long id){
        KechengxuankeEntity kechengxuanke = kechengxuankeService.selectById(id);
		kechengxuanke = kechengxuankeService.selectView(new EntityWrapper<KechengxuankeEntity>().eq("id", id));
        return R.ok().put("data", kechengxuanke);
    }
    



    /**
     * 后端保存
     */
    @RequestMapping("/save")
    public R save(@RequestBody KechengxuankeEntity kechengxuanke, HttpServletRequest request){
    	kechengxuanke.setId(new Date().getTime()+new Double(Math.floor(Math.random()*1000)).longValue());
    	//ValidatorUtils.validateEntity(kechengxuanke);
        kechengxuankeService.insert(kechengxuanke);
        return R.ok();
    }
    
    /**
     * 前端保存
     */
    @RequestMapping("/add")
    public R add(@RequestBody KechengxuankeEntity kechengxuanke, HttpServletRequest request){
    	kechengxuanke.setId(new Date().getTime()+new Double(Math.floor(Math.random()*1000)).longValue());
    	//ValidatorUtils.validateEntity(kechengxuanke);
        kechengxuankeService.insert(kechengxuanke);
        return R.ok();
    }



    /**
     * 修改
     */
    @RequestMapping("/update")
    @Transactional
    public R update(@RequestBody KechengxuankeEntity kechengxuanke, HttpServletRequest request){
        //ValidatorUtils.validateEntity(kechengxuanke);
        kechengxuankeService.updateById(kechengxuanke);//全部更新
        return R.ok();
    }

    /**
     * 审核
     */
    @RequestMapping("/shBatch")
    @Transactional
    public R update(@RequestBody Long[] ids, @RequestParam String sfsh, @RequestParam String shhf){
        List<KechengxuankeEntity> list = new ArrayList<KechengxuankeEntity>();
        for(Long id : ids) {
            KechengxuankeEntity kechengxuanke = kechengxuankeService.selectById(id);
            kechengxuanke.setSfsh(sfsh);
            kechengxuanke.setShhf(shhf);
            list.add(kechengxuanke);
        }
        kechengxuankeService.updateBatchById(list);
        return R.ok();
    }


    

    /**
     * 删除
     */
    @RequestMapping("/delete")
    public R delete(@RequestBody Long[] ids){
        kechengxuankeService.deleteBatchIds(Arrays.asList(ids));
        return R.ok();
    }
    
	








}
