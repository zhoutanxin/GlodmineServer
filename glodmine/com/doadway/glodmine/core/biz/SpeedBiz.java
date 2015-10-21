package com.doadway.glodmine.core.biz;

import java.util.List;
import java.util.Map;

import com.doadway.framework.pager.Page;
import com.doadway.glodmine.core.model.Speed;

public interface SpeedBiz {
	public boolean saveSpeed(Speed speed);
	
	public boolean updateSpeed(Speed speed);
	
	public Speed findSpeed(Integer id);

	public boolean delById(Integer id);
	
	/**
	 * 根据开始时间,结束时间,产品类型,分页查询收入情况
	 * */
	public List<Speed> findSpeedByPage(Page page,Map<String, Object> params);
	/**
	 * 根据开始时间,结束时间,统计收入情况
	 * */
	public List<Speed> countSpeedByDate(Map<String, Object> params);
}
