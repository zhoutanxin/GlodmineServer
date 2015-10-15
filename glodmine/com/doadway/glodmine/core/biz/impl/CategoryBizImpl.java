package com.doadway.glodmine.core.biz.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.doadway.glodmine.core.biz.CategoryBiz;
import com.doadway.glodmine.core.dao.IncomeTypeMapper;
import com.doadway.glodmine.core.dao.SpeedTypeMapper;
import com.doadway.glodmine.core.model.IncomeType;
import com.doadway.glodmine.core.model.IncomeTypeExample;
import com.doadway.glodmine.core.model.IncomeTypeExample.Criteria;
import com.doadway.glodmine.core.model.SpeedType;
import com.doadway.glodmine.core.model.SpeedTypeExample;
@Service
public class CategoryBizImpl implements CategoryBiz {
	@Resource
	IncomeTypeMapper incomeTypeDAO;
	@Resource
	SpeedTypeMapper  speedTypeDAO;
	@Override
	public boolean saveIncomeType(IncomeType incomeType) {
		return incomeTypeDAO.insert(incomeType)>0;
	}

	@Override
	public boolean updateIncomeType(IncomeType incomeType) {
		return incomeTypeDAO.updateByPrimaryKey(incomeType)>0;
	}

	@Override
	public IncomeType findIncomeTypeById(Integer id) {
		return incomeTypeDAO.selectByPrimaryKey(id);
	}

	@Override
	public List<IncomeType> findAll4IncomeTypeByMemberId(Integer memberId) {
		IncomeTypeExample example =new IncomeTypeExample();
		example.setOrderByClause("id");
		Criteria incomeTypeCri=example.createCriteria();
		incomeTypeCri.andMemberIdEqualTo(memberId);
		return incomeTypeDAO.selectByExample(example);
	}

	@Override
	public boolean delIncomeTypeById(Integer id) {
		return incomeTypeDAO.deleteByPrimaryKey(id)>0;
	}

	@Override
	public boolean saveSpeedType(SpeedType speedType) {
		return speedTypeDAO.insert(speedType)>0;
	}

	@Override
	public boolean updateSpeedType(SpeedType speedType) {
		return speedTypeDAO.updateByPrimaryKey(speedType)>0;
	}

	@Override
	public SpeedType findSpeedTypeById(Integer id) {
		return speedTypeDAO.selectByPrimaryKey(id);
	}

	@Override
	public List<SpeedType> findAll4SpeedTypeByMemberId(Integer memberId) {
		SpeedTypeExample example =new SpeedTypeExample();
		example.setOrderByClause("id");
		com.doadway.glodmine.core.model.SpeedTypeExample.Criteria speedTypeCri=example.createCriteria();
		speedTypeCri.andMemberIdEqualTo(memberId);
		return speedTypeDAO.selectByExample(example);
	}

	@Override 
	public boolean delSpeedTypeById(Integer id) {
		return speedTypeDAO.deleteByPrimaryKey(id)>0;
	}

}
