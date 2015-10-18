package com.doadway.glodmine.core.biz;

import java.util.List;
import java.util.Map;

import com.doadway.framework.pager.Page;
import com.doadway.glodmine.core.model.Income;

public interface IncomeBiz {
	public boolean saveIncome(Income income);
	
	public boolean updateIncome(Income income);
	
	public Income findIncome(Integer id);

	public boolean delById(Integer id);
	
	/**
	 * 根据开始时间,结束时间,产品类型,分页查询收入情况
	 * */
	public List<Income> findIncomeByPage(Page page,Map<String, Object> params);
	/**
	 * 根据开始时间,结束时间,统计收入情况
	 * */
	public List<Income> countIncomeByDate(Map<String, Object> params);
}
