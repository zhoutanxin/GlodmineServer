package com.doadway.glodmine.core.biz.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.doadway.framework.pager.Page;
import com.doadway.glodmine.core.biz.SpeedBiz;
import com.doadway.glodmine.core.dao.SpeedMapper;
import com.doadway.glodmine.core.model.Speed;
@Service
public class SpeedBizImpl implements SpeedBiz {
	@Resource
	SpeedMapper speedDAO;
	@Override
	public boolean saveSpeed(Speed income) {
		// TODO Auto-generated method stub
		return speedDAO.insert(income)>0;
	}

	@Override
	public boolean updateSpeed(Speed income) {
		// TODO Auto-generated method stub
		return speedDAO.updateByPrimaryKey(income)>0;
	}

	@Override
	public Speed findSpeed(Integer id) {
		// TODO Auto-generated method stub
		return speedDAO.selectByPrimaryKey(id);
	}

	@Override
	public boolean delById(Integer id) {
		// TODO Auto-generated method stub
		return speedDAO.deleteByPrimaryKey(id)>0;
	}

	@Override
	public List<Speed> findSpeedByPage(Page page, Map<String, Object> params) {
		// TODO Auto-generated method stub
		return speedDAO.selectSpeedByPage(page, params);
	}

	@Override
	public List<Speed> countSpeedByDate(Map<String, Object> params) {
		// TODO Auto-generated method stub
		return speedDAO.countSpeedByDate(params);
	}

}
