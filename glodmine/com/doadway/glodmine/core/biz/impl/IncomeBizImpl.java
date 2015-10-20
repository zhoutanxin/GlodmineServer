package com.doadway.glodmine.core.biz.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.doadway.framework.pager.Page;
import com.doadway.glodmine.core.biz.IncomeBiz;
import com.doadway.glodmine.core.dao.IncomeMapper;
import com.doadway.glodmine.core.model.Income;
@Service
public class IncomeBizImpl implements IncomeBiz {
	@Resource
	IncomeMapper incomeDAO;
	@Override
	public boolean saveIncome(Income income) {
		// TODO Auto-generated method stub
		return incomeDAO.insert(income)>0;
	}

	@Override
	public boolean updateIncome(Income income) {
		// TODO Auto-generated method stub
		return incomeDAO.updateByPrimaryKey(income)>0;
	}

	@Override
	public Income findIncome(Integer id) {
		// TODO Auto-generated method stub
		return incomeDAO.selectByPrimaryKey(id);
	}

	@Override
	public boolean delById(Integer id) {
		// TODO Auto-generated method stub
		return incomeDAO.deleteByPrimaryKey(id)>0;
	}

	@Override
	public List<Income> findIncomeByPage(Page page, Map<String, Object> params) {
		// TODO Auto-generated method stub
		return incomeDAO.selectIncomeByPage(page, params);
	}

	@Override
	public List<Income> countIncomeByDate(Map<String, Object> params) {
		// TODO Auto-generated method stub
		return incomeDAO.countIncomeByDate(params);
	}

}
