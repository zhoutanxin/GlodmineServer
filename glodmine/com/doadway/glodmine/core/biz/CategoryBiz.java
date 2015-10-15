package com.doadway.glodmine.core.biz;

import java.util.List;

import com.doadway.glodmine.core.model.IncomeType;
import com.doadway.glodmine.core.model.SpeedType;

public interface CategoryBiz {

	public boolean saveIncomeType(IncomeType incomeType);
	public boolean updateIncomeType(IncomeType incomeType);
	public IncomeType findIncomeTypeById(Integer id);
	public List<IncomeType> findAll4IncomeTypeByMemberId(Integer memberId);
	public boolean delIncomeTypeById(Integer id);
	
	public boolean saveSpeedType(SpeedType speedType);
	public boolean updateSpeedType(SpeedType speedType);
	public SpeedType findSpeedTypeById(Integer id);
	public List<SpeedType> findAll4SpeedTypeByMemberId(Integer memberId);
	public boolean delSpeedTypeById(Integer id);
	
}
